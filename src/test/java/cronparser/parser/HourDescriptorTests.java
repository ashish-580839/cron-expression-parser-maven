package cronparser.descriptor;

import cronparser.exception.IllegalCronException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class HourDescriptorTests {


    @Test
    void forwardSlashInvalidRangeExpression(){
        HourDescriptor descriptor = new HourDescriptor("*/24");
        IllegalCronException thrown = assertThrows(IllegalCronException.class, () -> descriptor.isValid() );
        assertTrue(thrown.getMessage().contains("Hour values must be between 0 and 23"));
    }


    @Test
    void hyphenInvalidRangeExpression(){
        HourDescriptor descriptor = new HourDescriptor("20-25");
        IllegalCronException thrown = assertThrows(IllegalCronException.class, () -> descriptor.isValid() );
        assertTrue(thrown.getMessage().contains("Hour values must be between 0 and 23"));
    }

    @Test
    void hyphenInvalidRange2Expression(){
        HourDescriptor descriptor = new HourDescriptor("20-10");
        IllegalCronException thrown = assertThrows(IllegalCronException.class, () -> descriptor.isValid() );
        assertTrue(thrown.getMessage().contains("(Hour) - Unsupported value "));
    }

    @Test
    void commaInvalidRangeExpression(){
        HourDescriptor descriptor = new HourDescriptor("1,24");
        IllegalCronException thrown = assertThrows(IllegalCronException.class, () -> descriptor.isValid() );
        assertTrue(thrown.getMessage().contains("Hour values must be between 0 and 23"));
    }

    @Test
    void forwardSlashValidExpression() throws IllegalCronException {
        HourDescriptor descriptor = new HourDescriptor("*/6");
        List<Integer> occurances = Arrays.asList(0,6,12,18);
        assertIterableEquals(descriptor.describe(), occurances);
    }

    @Test
    void hyphenValidExpression() throws IllegalCronException {
        HourDescriptor descriptor = new HourDescriptor("10-15");
        List<Integer> occurances = Arrays.asList(10,11,12,13,14,15);
        assertIterableEquals(descriptor.describe(), occurances);
    }

    @Test
    void commaValidExpression() throws IllegalCronException {
        HourDescriptor descriptor = new HourDescriptor("5,10,15");
        List<Integer> occurances = Arrays.asList(5,10,15);
        assertIterableEquals(descriptor.describe(), occurances);
    }

    @Test
    void numberValidExpression() throws IllegalCronException {
        HourDescriptor descriptor = new HourDescriptor("10");
        List<Integer> occurances = Arrays.asList(10);
        assertIterableEquals(descriptor.describe(), occurances);
    }
}
