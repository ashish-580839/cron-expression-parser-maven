package cronparser.parser;

public class DayOfWeekSegmentParser extends AbstractSegmentParser {

    public DayOfWeekSegmentParser(String expression){
        super(expression, 1, 7, "Day of week");
    }
}
