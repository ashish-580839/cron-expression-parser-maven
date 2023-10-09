package cronparser.parser;

public class DayOfMonthSegmentParser extends AbstractSegmentParser {

    public DayOfMonthSegmentParser(String expression){
        super(expression, 1, 31, "Day of month");
    }
}
