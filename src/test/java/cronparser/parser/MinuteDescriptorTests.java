package cronparser.descriptor;

import cronparser.exception.IllegalCronException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MinuteDescriptorTests {

    @Test
    void forwardSlashInvalidRangeExpression(){
        MinuteDescriptor descriptor = new MinuteDescriptor("*/60");
        IllegalCronException thrown = assertThrows(IllegalCronException.class, () -> descriptor.isValid() );
        assertTrue(thrown.getMessage().contains("Minute values must be between 0 and 59"));
    }


    @Test
    void hyphenInvalidRangeExpression(){
        MinuteDescriptor descriptor = new MinuteDescriptor("20-70");
        IllegalCronException thrown = assertThrows(IllegalCronException.class, () -> descriptor.isValid() );
        assertTrue(thrown.getMessage().contains("Minute values must be between 0 and 59"));
    }

    @Test
    void hyphenInvalidRange2Expression(){
        MinuteDescriptor descriptor = new MinuteDescriptor("40-20");
        IllegalCronException thrown = assertThrows(IllegalCronException.class, () -> descriptor.isValid() );
        assertTrue(thrown.getMessage().contains("(Minute) - Unsupported value "));
    }

    @Test
    void commaInvalidRangeExpression(){
        MinuteDescriptor descriptor = new MinuteDescriptor("1,60");
        IllegalCronException thrown = assertThrows(IllegalCronException.class, () -> descriptor.isValid() );
        assertTrue(thrown.getMessage().contains("Minute values must be between 0 and 59"));
    }

    @Test
    void forwardSlashValidExpression() throws IllegalCronException {
        MinuteDescriptor descriptor = new MinuteDescriptor("*/15");
        List<Integer> occurances = Arrays.asList(0,15,30,45);
        assertIterableEquals(descriptor.describe(), occurances);
    }

    @Test
    void hyphenValidExpression() throws IllegalCronException {
        MinuteDescriptor descriptor = new MinuteDescriptor("1-5");
        List<Integer> occurances = Arrays.asList(1,2,3,4,5);
        assertIterableEquals(descriptor.describe(), occurances);
    }

    @Test
    void commaValidExpression() throws IllegalCronException {
        MinuteDescriptor descriptor = new MinuteDescriptor("5,10,15");
        List<Integer> occurances = Arrays.asList(5,10,15);
        assertIterableEquals(descriptor.describe(), occurances);
    }

    @Test
    void numberValidExpression() throws IllegalCronException {
        MinuteDescriptor descriptor = new MinuteDescriptor("30");
        List<Integer> occurances = Arrays.asList(30);
        assertIterableEquals(descriptor.describe(), occurances);
    }
}
