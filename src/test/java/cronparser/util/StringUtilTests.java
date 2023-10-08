package cronparser.util;


import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StringUtilTests {

    @Test
    void formatSpaceSeparatedNullValue(){
        NullPointerException thrown = assertThrows(NullPointerException.class, () -> StringUtil.formatSpaceSeparated(null) );
    }

    @Test
    void formatSpaceSeparatedValidList(){
        List<Integer> values = Arrays.asList(0,15,30,45);
        assertEquals(StringUtil.formatSpaceSeparated(values), "0 15 30 45");
    }
}
