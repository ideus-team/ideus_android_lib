package biz.ideus.ideuslib.interfaces;

/**
 * Created by blackmamba on 09.12.16.
 */

public interface OnValidateSettingsScreen extends OnValidateSignUpScreen{

    void isValidCurrentPassword(boolean isValid);

    void setTitleColorNewPassword(int color);
}
