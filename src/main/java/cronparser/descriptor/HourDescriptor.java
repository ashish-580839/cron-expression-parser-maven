package cronparser.descriptor;

public class HourDescriptor extends AbstractDescriptor {

    public HourDescriptor(String expression){
        super(expression, 0, 23, "Hour");
    }
}
