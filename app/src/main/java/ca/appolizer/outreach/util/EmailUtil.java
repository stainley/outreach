package ca.appolizer.outreach.util;

import java.util.regex.Pattern;

public class EmailUtil {
    public static boolean isValidEmailAddrRegex(String emailValidationRegex, String emailAddrToValidate) {
        return Pattern.compile(emailValidationRegex)
                .matcher(emailAddrToValidate)
                .matches();
    }
}
