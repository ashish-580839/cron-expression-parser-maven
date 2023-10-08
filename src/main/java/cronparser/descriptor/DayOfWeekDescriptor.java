package cronparser.descriptor;

public class DayOfWeekDescriptor extends  AbstractDescriptor{

    public DayOfWeekDescriptor(String expression){
        super(expression, 1, 7, "DayOfWeek");
    }
}
