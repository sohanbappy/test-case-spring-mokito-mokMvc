package com.bappy.test.helper;


public class Converter {

    public static int stringToInt(String data) {
        try {
            return Integer.parseInt(data);
        } catch (Exception e) {
            return 0;
        }
    }

    private String getResult(int marks) {
        return marks < 40 ? "Fail" : "Pass";
    }
}
