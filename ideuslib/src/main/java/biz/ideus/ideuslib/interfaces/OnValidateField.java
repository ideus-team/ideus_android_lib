package biz.ideus.ideuslib.interfaces;

/**
 * Created by blackmamba on 16.11.16.
 */

public interface OnValidateField  {
    void setVisibilityImageEmail(int visibility);

    void setVisibilityImagePassword(int visibility);

    void setTitleColorEmail(int color);

    void setTitleColorPassword(int color);

    void isValidEmail(boolean isValid);

    void isValidPassword(boolean isValid);

}
