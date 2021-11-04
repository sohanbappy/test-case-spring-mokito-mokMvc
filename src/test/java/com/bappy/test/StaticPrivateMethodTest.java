package com.bappy.test;


import com.bappy.test.helper.Converter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.powermock.reflect.Whitebox;

public class StaticPrivateMethodTest {

    @Test
    public void privateMethodTest() throws Exception {
        Converter converter = new Converter();

        String actualResult = Whitebox.invokeMethod(converter, "getResult", 70);
        String expectedResult = "Pass";
        Assertions.assertThat(expectedResult).isEqualTo(actualResult);
    }

}
