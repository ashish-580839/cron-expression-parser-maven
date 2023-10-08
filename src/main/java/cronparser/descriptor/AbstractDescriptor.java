package cronparser.descriptor;

import cronparser.exception.IllegalCronException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public abstract  class AbstractDescriptor {

    String expression;

    String label;

    int MIN;
    int MAX;

    public AbstractDescriptor(String expression, int min, int max, String label){
        this.expression = expression;
        this.MIN = min;
        this.MAX = max;
        this.label = label;
    }

    public boolean isValid() throws IllegalCronException {
        if(Objects.isNull(expression) || expression.length() ==0){
            throw new IllegalCronException("Expression is required");
        }

        // check wildcard character
        if(expression.equals("*")){
            return true;
        }

        // check expression containing '/'   eg: */12  , 1/2
        if(expression.matches("(\\*|\\d+)/\\d+") ){
            String[] parts = expression.split("/");

            if(parts[0].equals("*")){
                if( Integer.valueOf(parts[1]) > MAX ){
                    throw new IllegalCronException(label+" values must be between "+MIN+" and "+MAX);
                }
            }else{
                if( Integer.valueOf(parts[0]) > MAX || Integer.valueOf(parts[1]) > MAX ){
                    throw new IllegalCronException(label+" values must be between "+MIN+" and "+MAX);
                }
            }

            return  true;
        }

        // check expression containing '-'   eg: 1-6
        if(expression.matches("\\d+-\\d+") ){
            int[] parts = Arrays.stream(expression.split("-")).mapToInt(Integer::parseInt).toArray();

            if(parts[0] > MAX || parts[1]> MAX ){
                throw new IllegalCronException(label+" values must be between "+MIN+" and "+MAX);
            }

            if(parts[0]>parts[1]){
                throw new IllegalCronException("("+label+") - Unsupported value '"+expression+"' for range. Accepted values are "+MIN+"-"+MAX);
            }

            return  true;
        }

        // check expression containing ','   eg: 0,2,4,6
        if(expression.matches("(\\d+,)*\\d+") ){
            int[] parts = Arrays.stream(expression.split(",")).mapToInt(Integer::parseInt).toArray();
            for(int part:parts){
                if(part>MAX){
                    throw new IllegalCronException(label+" values must be between "+MIN+" and "+MAX);
                }
            }

            return  true;
        }

        throw new IllegalCronException("("+label+") - Invalid expression: "+expression);
    }

    public List<Integer> describe() throws IllegalCronException {
        if(!isValid()){
            return null;
        }

        List<Integer> occurrences = new ArrayList<>();

        if(expression.equals("*")){
            for(int i=MIN;i<=MAX;i++){
                occurrences.add(i);
            }
        } else if(expression.matches("(\\*|\\d+)/\\d+") ){

            String[] parts = expression.split("/");
            int start;
            int incrBy = Integer.valueOf(parts[1]);

            start = parts[0].equals("*") ? MIN : Integer.valueOf(parts[0]);

            while (start<=MAX){
                occurrences.add(start);
                start += incrBy;
            }

        }else if(expression.matches("\\d+-\\d+") ){
            int[] parts = Arrays.stream(expression.split("-")).mapToInt(Integer::parseInt).toArray();

            for (int i=parts[0];i<=parts[1];i++){
                occurrences.add(i);
            }
        }else if(expression.matches("(\\d+,)*\\d+") ){
            int[] parts = Arrays.stream(expression.split(",")).mapToInt(Integer::parseInt).toArray();
            for(int part:parts){
                occurrences.add(part);
            }
        }

        return occurrences;
    }

}
