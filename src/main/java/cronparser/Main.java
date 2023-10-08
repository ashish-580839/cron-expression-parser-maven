package cronparser;

import cronparser.exception.IllegalCronException;

import java.util.List;

public class Main {

    public static void main(String[] args){

        try {
            ExpressionParser parser = new ExpressionParser();
            List<List<String> > parsed = parser.parse("4/15 6 1,15 * 1-5 /usr/bin/find");

            for(List<String> row: parsed){
                System.out.printf("%-14s%s%n",row.get(0), row.get(1));
            }
        } catch (IllegalCronException e) {
            e.printStackTrace();
        }
    }
}
