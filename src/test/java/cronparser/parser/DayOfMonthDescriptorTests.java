package cronparser.descriptor;

import cronparser.exception.IllegalCronException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class DayOfMonthDescriptorTests {

    @Test
    void forwardSlashInvalidRangeExpression(){
        DayOfMonthDescriptor descriptor = new DayOfMonthDescriptor("1/32");
        IllegalCronException thrown = assertThrows(IllegalCronException.class, () -> descriptor.isValid() );
        assertTrue(thrown.getMessage().contains("Day of month values must be between 1 and 31"));
    }


    @Test
    void hyphenInvalidRangeExpression(){
        DayOfMonthDescriptor descriptor = new DayOfMonthDescriptor("15-35");
        IllegalCronException thrown = assertThrows(IllegalCronException.class, () -> descriptor.isValid() );
        assertTrue(thrown.getMessage().contains("Day of month values must be between 1 and 31"));
    }

    @Test
    void hyphenInvalidRange2Expression(){
        DayOfMonthDescriptor descriptor = new DayOfMonthDescriptor("20-10");
        IllegalCronException thrown = assertThrows(IllegalCronException.class, () -> descriptor.isValid() );
        assertTrue(thrown.getMessage().contains("(Day of month) - Unsupported value "));
    }

    @Test
    void commaInvalidRangeExpression(){
        DayOfMonthDescriptor descriptor = new DayOfMonthDescriptor("0,32");
        IllegalCronException thrown = assertThrows(IllegalCronException.class, () -> descriptor.isValid() );
        assertTrue(thrown.getMessage().contains("Day of month values must be between 1 and 31"));
    }

    @Test
    void forwardSlashValidExpression() throws IllegalCronException {
        DayOfMonthDescriptor descriptor = new DayOfMonthDescriptor("5/6");
        List<Integer> occurances = Arrays.asList(5,11,17,23,29);
        assertIterableEquals(descriptor.describe(), occurances);
    }

    @Test
    void hyphenValidExpression() throws IllegalCronException {
        DayOfMonthDescriptor descriptor = new DayOfMonthDescriptor("25-31");
        List<Integer> occurances = Arrays.asList(25,26,27,28,29,30,31);
        assertIterableEquals(descriptor.describe(), occurances);
    }

    @Test
    void commaValidExpression() throws IllegalCronException {
        DayOfMonthDescriptor descriptor = new DayOfMonthDescriptor("10,15,20");
        List<Integer> occurances = Arrays.asList(10,15,20);
        assertIterableEquals(descriptor.describe(), occurances);
    }

    @Test
    void numberValidExpression() throws IllegalCronException {
        DayOfMonthDescriptor descriptor = new DayOfMonthDescriptor("10");
        List<Integer> occurances = Arrays.asList(10);
        assertIterableEquals(descriptor.describe(), occurances);
    }
}
