package biz.ideus.ideuslibexample.utils;

/**
 * Created by blackmamba on 14.11.16.
 */

public class Constants {
    public static final String TWITTER_APP_KEY = "eNDgqQzf8HhlDTvhPwgFg1GRz";
    public static final String TWITTER_SECRET_KEY = "c5oF0QumW3j9xi5wbMDkAeVFBfhZOfcdSTi7YD05Ts1dmAvE91";
    public static final String EMAIL_PATTERN = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    public static final String TERMS_OF_SERVICE_URI ="file:///android_asset/contract.html";
    public static final int SELECT_PICTURE_REQUEST = 10001;
    public static final int MAKE_PHOTO_REQUEST_CODE = 10002;

    public static final int MAIN_SCREEN_PAGES_COUNT = 3;

    public static final int HOME_TAB_POSITION = 0;
    public static final int PEOPLE_TAB_POSITION = 1;
    public static final int SETTINGS_TAB_POSITION = 2;

// FOR GET GOOGLE TOKEN
public static final int GOOGLE_SIGN_IN = 2222;
    private final static String G_PLUS_SCOPE =
            "oauth2:https://www.googleapis.com/auth/plus.me";
    private final static String USERINFO_SCOPE =
            "https://www.googleapis.com/auth/userinfo.profile";
    private final static String EMAIL_SCOPE =
            "https://www.googleapis.com/auth/userinfo.email";
    public final static String GOOGLE_SCOPES = G_PLUS_SCOPE + " " + USERINFO_SCOPE + " " + EMAIL_SCOPE;
}
