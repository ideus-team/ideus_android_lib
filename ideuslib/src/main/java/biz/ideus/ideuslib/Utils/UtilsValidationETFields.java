package biz.ideus.ideuslib.Utils;

import android.text.TextUtils;

/**
 * Created by blackmamba on 15.11.16.
 */

public class UtilsValidationETFields {


    public static boolean validateEmail(String email, String pattern) {
        if (TextUtils.isEmpty(email))
            return false;
        return email.matches(pattern);
    }

    public static boolean validatePassword(String password, int countChars) {
        return password.length() > countChars;
    }
    public static boolean validateName(String name, int countChars) {
        return name.length() > countChars;
    }





}
