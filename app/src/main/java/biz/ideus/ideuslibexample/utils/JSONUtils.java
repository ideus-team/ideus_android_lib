package biz.ideus.ideuslibexample.utils;

import com.google.gson.Gson;

/**
 * Created by blackmamba on 25.01.17.
 */

public final class JSONUtils {
    private static final Gson gson = new Gson();

    private JSONUtils(){}

    public static boolean isJSONValid(String jsonInString) {
        try {
            gson.fromJson(jsonInString, Object.class);
            return true;
        } catch(com.google.gson.JsonSyntaxException ex) {
            return false;
        }
    }
}