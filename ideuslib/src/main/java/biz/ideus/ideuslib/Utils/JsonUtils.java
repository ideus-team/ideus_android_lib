package biz.ideus.ideuslib.Utils;

import com.google.gson.JsonObject;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by user on 28.02.2017.
 */

public  class JsonUtils {
    static SimpleDateFormat formatterWithTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    static SimpleDateFormat formatterWithoutTime = new SimpleDateFormat("yyyy-MM-dd");
    public static String getString(JsonObject jo, String name) {
        if (!jo.has(name)) return "";
        return jo.get(name).isJsonNull() ? "" : jo.get(name).getAsString();
    }

    public static boolean getBool(JsonObject jo, String name) {
        if (!jo.has(name) || jo.get(name).isJsonNull()) {
            return false;
        } else {
            return jo.get(name).getAsBoolean();
        }
    }

    public static long getLong(JsonObject jo, String name) {
        if (!jo.has(name)) return 0;
        if (jo.get(name).isJsonNull()) return 0;
        return  jo.get(name).getAsLong();
    }

    public static Date getDateWithTime(JsonObject jo, String name) {
        Date result;

        if (!jo.has(name)) return null;
        if (jo.get(name).isJsonNull()) return null;
        try {
            String s = jo.get(name).getAsString();
            result = formatterWithTime.parse(s);
        } catch (Exception e) {
            result = null;
        }
        return  result;
    }

    public static Date getDateWithOutTime(JsonObject jo, String name) {
        Date result;

        if (!jo.has(name)) return null;
        if (jo.get(name).isJsonNull()) return null;
        try {
            String s = jo.get(name).getAsString();
            result = formatterWithoutTime.parse(s);
        } catch (Exception e) {
            result = null;
        }
        return  result;
    }

    public static int getInt(JsonObject jo, String name) {
        if (!jo.has(name)) return 0;
        if (jo.get(name).isJsonNull()) return 0;
        int result;
        try {
            result = jo.get(name).getAsInt();
        } catch (Exception e) {
            try {
                result = Integer.valueOf(jo.get(name).getAsString());
            }
            catch (Exception e2) {
                result = 0;
            }

        }
        return result;
    }

    public static int getIntForAge(JsonObject jo, String name) {
        if (!jo.has(name)) return 0;
        if (jo.get(name).getAsString().equals("")) return -1;
        if (jo.get(name).isJsonNull()) return 0;
        int result;
        try {
            result = jo.get(name).getAsInt();
        } catch (Exception e) {
            try {
                result = Integer.valueOf(jo.get(name).getAsString());
            }
            catch (Exception e2) {
                result = 0;
            }

        }
        return result;
    }
}
