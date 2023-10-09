package cronparser.descriptor;

public class MonthDescriptor extends AbstractDescriptor{

    public MonthDescriptor(String expression){
        super(expression, 1, 12, "Month");
    }
}
