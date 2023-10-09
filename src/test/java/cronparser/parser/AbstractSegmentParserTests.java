package cronparser.descriptor;

import cronparser.exception.IllegalCronException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class AbstractDescriptorTests {

    @Test
    void emptyExpression(){
        MinuteDescriptor descriptor = new MinuteDescriptor("");
        IllegalCronException thrown = assertThrows(IllegalCronException.class, () -> descriptor.isValid() );
        assertTrue(thrown.getMessage().contains("Expression is required"));
    }

    @Test
    void invalidExpression(){
        MinuteDescriptor descriptor = new MinuteDescriptor("%");
        IllegalCronException thrown = assertThrows(IllegalCronException.class, () -> descriptor.isValid() );
        assertTrue(thrown.getMessage().contains("Invalid expression"));
    }

    @Test
    void forwardSlashInvalidExpression(){
        MinuteDescriptor descriptor = new MinuteDescriptor("12/*");
        IllegalCronException thrown = assertThrows(IllegalCronException.class, () -> descriptor.isValid() );
        assertTrue(thrown.getMessage().contains("Invalid expression"));
    }



    @Test
    void hyphenInvalidExpression(){
        MinuteDescriptor descriptor = new MinuteDescriptor("6-3-");
        IllegalCronException thrown = assertThrows(IllegalCronException.class, () -> descriptor.isValid() );
        assertTrue(thrown.getMessage().contains("Invalid expression"));
    }

    @Test
    void commaInvalidExpression(){
        MinuteDescriptor descriptor = new MinuteDescriptor("1,2,3,");
        IllegalCronException thrown = assertThrows(IllegalCronException.class, () -> descriptor.isValid() );
        assertTrue(thrown.getMessage().contains("Invalid expression"));
    }

    @Test
    void negativeNumberInvalidExpression(){
        MinuteDescriptor descriptor = new MinuteDescriptor("-6");
        IllegalCronException thrown = assertThrows(IllegalCronException.class, () -> descriptor.isValid() );
        assertTrue(thrown.getMessage().contains("Invalid expression"));
    }

}
