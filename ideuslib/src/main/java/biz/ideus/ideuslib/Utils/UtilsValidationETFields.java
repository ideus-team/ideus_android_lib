package biz.ideus.ideuslib.Utils;

import android.text.TextUtils;

/**
 * Created by blackmamba on 15.11.16.
 */

public class UtilsValidationETFields {

   private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

  public boolean validateEmail(String email) {
        if (TextUtils.isEmpty(email))
            return false;
        return email.matches(emailPattern);
    }

    public boolean validatePassword(String password) {
        return password.length() > 5;
    }

}
