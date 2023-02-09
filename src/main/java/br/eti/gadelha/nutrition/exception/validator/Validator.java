package br.eti.gadelha.nutrition.exception.validator;

public final class Validator {

    public static boolean isNull(Object value) {
        if(value == null) {
            return true;
        } else {
            return false;
        }
    }
    public static boolean isNumber(String number) {
        if (number == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(number);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    public static boolean isName(String name) {
        if (name == null || name.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}