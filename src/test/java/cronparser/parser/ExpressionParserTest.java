package cronparser;

import cronparser.exception.IllegalCronException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ExpressionParserTest {

    @Test
    void parseNullValue(){
        ExpressionParser parser = new ExpressionParser();
        IllegalCronException thrown = assertThrows(IllegalCronException.class, () -> parser.parse(null) );
        assertTrue(thrown.getMessage().contains("Cron expression is required"));
    }

    @Test
    void parseInvalidLengthExpression(){
        ExpressionParser parser = new ExpressionParser();
        IllegalCronException thrown = assertThrows(IllegalCronException.class, () -> parser.parse("* * * 0 /usr/bin/find") );
        assertTrue(thrown.getMessage().contains("Invalid cron expression"));
    }

    @Test
    void validCronExpression() throws IllegalCronException {
        ExpressionParser parser = new ExpressionParser();
        List<String> minute = Arrays.asList("minute","0 15 30 45");
        List<String> hour = Arrays.asList("hour","0");
        List<String> dayOfMonth = Arrays.asList("day of month","1 15");
        List<String> month = Arrays.asList("month","1 2 3 4 5 6 7 8 9 10 11 12");
        List<String> dayOfWeek = Arrays.asList("day of week","1 2 3 4 5");
        List<String> command = Arrays.asList("command","/usr/bin/find");

        List<List<String>> rows = Arrays.asList( minute, hour, dayOfMonth,  month, dayOfWeek, command);

        assertIterableEquals(parser.parse("*/15 0 1,15 * 1-5 /usr/bin/find"), rows);
    }
}
