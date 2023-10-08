package cronparser.util;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class StringUtil {

    public static <E> String formatSpaceSeparated(List<E> list){
        return list.stream().map(Objects::toString).collect(Collectors.joining(" "));
    }
}
