package biz.ideus.ideuslibexample.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

import java.io.File;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by blackmamba on 21.12.16.
 */

public class Utils {

    public static MultipartBody.Part createMultipartBody(String picturePath) {
        File file = new File(picturePath);
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", file.getName()
                , RequestBody.create(MediaType.parse("image/*"), file));
        return filePart;
    }

    public static Drawable convertBitmapToDrawable(Context context, String imagePath) {
        return new BitmapDrawable(context.getResources(), BitmapFactory.decodeFile(imagePath));
    }

    public static void toast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("SimpleDateFormat")
    public static String convertTimeChat(String dateFromServer) {
        Date currentDate = null;
        String dateOnlyHourFormat = null;
        DateFormat formatOnlyHoursDate = null;

        long timeFromServer = Long.parseLong(dateFromServer);
        String language = Locale.getDefault().getLanguage();
        if (language.equals("en")) {
            dateOnlyHourFormat = "hh:mm a";
        } else {
            dateOnlyHourFormat = "HH:mm";
        }
        Timestamp stamp = new Timestamp(timeFromServer * 1000);
        currentDate = new Date(stamp.getTime());
        formatOnlyHoursDate = new SimpleDateFormat(dateOnlyHourFormat, Locale.getDefault());

        if (timeFromServer > 0) {
            Calendar current_cal = Calendar.getInstance();
            current_cal.setTimeInMillis(stamp.getTime());

          return formatOnlyHoursDate.format(currentDate);

        } else return "";
    }


    @SuppressLint("SimpleDateFormat")
    public static String convertDate(String dateFromServer) {
        Date currentDate = null;
        String dateName = null;
        String dateFormat = null;
        DateFormat formatDate = null;
        long timeFromServer = Long.parseLong(dateFromServer);

            dateFormat = "dd, MMMM, yyyy";
        Timestamp stamp = new Timestamp(timeFromServer * 1000);
        currentDate = new Date(stamp.getTime());
        formatDate = new SimpleDateFormat(dateFormat, Locale.getDefault());
        if (timeFromServer > 0) {
            Calendar current_cal = Calendar.getInstance();
            Calendar calendar = Calendar.getInstance();
            current_cal.setTimeInMillis(stamp.getTime());

//            if (current_cal.get(Calendar.DAY_OF_YEAR) == (calendar.get(Calendar.DAY_OF_YEAR))) {
//                dateName = Calendar.
//                return dateName;
//            } else if ((calendar.get(Calendar.DAY_OF_YEAR) - 1) == (current_cal.get(Calendar.DAY_OF_YEAR))) {
//                dateName = activity.getString(R.string.yesterday);
//                return dateName;
//            } else {
                dateName = formatDate.format(currentDate);
                return dateName;
//            }
        } else return "";
    }
}
