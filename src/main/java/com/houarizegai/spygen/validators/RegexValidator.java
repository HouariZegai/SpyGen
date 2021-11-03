package com.houarizegai.spygen.validators;

public class RegexValidator {

    private static final String EMAIL_REGEX = "[a-zA-Z_][\\w]*[-]{0,4}[\\w]+@[a-zA-Z0-9]+.[a-zA-Z]{2,6}";

    public static boolean isEmail(String email) {
        return validate(email, EMAIL_REGEX);
    }

    private static boolean validate(String str, String pattern) {
        if(str == null)
            return false;

        return str.trim().matches(pattern);
    }
}
