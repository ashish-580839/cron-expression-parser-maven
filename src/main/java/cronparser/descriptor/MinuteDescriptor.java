package cronparser.descriptor;

public class MinuteDescriptor extends AbstractDescriptor{

    public MinuteDescriptor(String expression){
        super(expression, 0, 59, "Minute");
    }

}
