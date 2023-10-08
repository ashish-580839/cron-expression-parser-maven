package cronparser.descriptor;

public class DayOfMonthDescriptor extends AbstractDescriptor {

    public DayOfMonthDescriptor(String expression){
        super(expression, 1, 31, "Day of month");
    }
}
