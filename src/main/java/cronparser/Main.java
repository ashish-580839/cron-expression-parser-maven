package cronparser;

import cronparser.exception.IllegalCronException;
import cronparser.parser.ExpressionParser;

import java.util.List;

public class Main {

    public static void main(String[] args){

        try {
            String input = "*/15 6 1,15 * 1-5 /usr/bin/find";
            ExpressionParser parser = new ExpressionParser();
            List<List<String> > parsed = parser.parse(input);

            for(List<String> row: parsed){
                System.out.printf("%-14s%s%n",row.get(0), row.get(1));
            }
        } catch (IllegalCronException e) {
            e.printStackTrace();
        }
    }
}
