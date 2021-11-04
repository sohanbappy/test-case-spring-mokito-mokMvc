package com.bappy.test;


import com.bappy.test.helper.Converter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.powermock.reflect.Whitebox;
import org.springframework.test.util.ReflectionTestUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;

public class StaticPrivateMethodTest {

    @Mock
    Converter converter;

    @BeforeEach
    public void createObject() {
        converter = new Converter();
    }


    /*
    Private Method Testing
     */
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

    /*
    Static Method Testing
     */
    @Test
    void staticMethodTest() {
        //Before mock
        assertNotEquals(2345, Converter.stringToInt("1234"));

        //Mocking (main)
        try (MockedStatic mocked = mockStatic(Converter.class)) {
            mocked.when(() -> Converter.stringToInt("1234")).thenReturn(1234);
            //mocked.when(() -> Converter.stringToInt("1234")).thenReturn(3456);//fail-case

            //Mocked behavior
            assertEquals(1234, Converter.stringToInt("1234"));
            //Verifying mocks
            mocked.verify(times(1), () -> Converter.stringToInt("1234"));
        }
    }
}
