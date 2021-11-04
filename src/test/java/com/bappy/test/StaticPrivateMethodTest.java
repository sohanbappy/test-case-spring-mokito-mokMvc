package com.bappy.test;


import com.bappy.test.helper.Converter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.powermock.reflect.Whitebox;
import org.springframework.test.util.ReflectionTestUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class StaticPrivateMethodTest {

    @Mock
    Converter converter;

    @BeforeEach
    public void createObject() {
        converter = new Converter();
    }


    @Test
    public void privateMethodTest() throws Exception {
        String actualResult = Whitebox.invokeMethod(converter, "getResult", 70);
        String expectedResult = "Pass";

        Assertions.assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    public void privateMethodTestUsingReflectionUtils() {
        ReflectionTestUtils.invokeMethod(converter, "getResult", 50);
    }

    @Test
    public void privateMethodTestUsingReflection() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class[] params = new Class[1];//no. of parameters
        params[0] = int.class;//Don't Use Integer, plz
        Method method = converter.getClass().getDeclaredMethod("getResult", params);
        method.setAccessible(true);

        Object[] methodArgs = new Object[1];
        methodArgs[0] = 20;//marks
        String actual = (String) method.invoke(converter, methodArgs);
        String expected = "Fail";

        Assertions.assertThat(actual).isEqualTo(expected);
    }
}
