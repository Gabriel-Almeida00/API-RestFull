package com.gabriel.api.converter;

public class NumberConverters {

    public static Double convertToDouble(String Snumber) {
        if (Snumber == null) return 0D;
        String number = Snumber.replaceAll(",", ".");
        if (isNumeric(number)) return Double.parseDouble(number);
        return 0D;
    }

    public static boolean isNumeric(String Snumber) {
        if (Snumber == null) return false;
        String number = Snumber.replaceAll(",", ".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }
}
