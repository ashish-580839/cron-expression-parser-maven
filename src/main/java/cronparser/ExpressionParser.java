package cronparser;

import cronparser.descriptor.*;
import cronparser.exception.IllegalCronException;
import cronparser.util.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ExpressionParser {

    int LENGTH = 5;

    public List<List<String> > parse(String expression) throws IllegalCronException {

        // base case
        if( Objects.isNull(expression) || expression.length() ==0 ){
            throw new IllegalCronException("Cron expression is required");
        }

        String[] segments = expression.split(" ");
        if( segments.length != (LENGTH+1) ){
            throw new IllegalCronException("Invalid cron expression: "+expression);
        }

        String[] labels = {"minute", "hour", "day of month", "month", "day of week", "command"};

        List<Integer>[] parsed = new List[LENGTH];
        parsed[0] = new MinuteDescriptor(segments[0]).describe();
        parsed[1] = new HourDescriptor(segments[1]).describe();
        parsed[2] = new DayOfMonthDescriptor(segments[2]).describe();
        parsed[3] = new MonthDescriptor(segments[3]).describe();
        parsed[4] = new DayOfWeekDescriptor(segments[4]).describe();

        List<List<String> > response = new ArrayList<>();

        for(int i=0;i<=LENGTH;i++){
            List<String> row = new ArrayList<>();
            row.add(labels[i]);

            if(i<LENGTH)
                row.add(StringUtil.formatSpaceSeparated(parsed[i]));
            else row.add(segments[LENGTH]);

            response.add(row);
        }
        return response;
    }
}
