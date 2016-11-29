package biz.ideus.ideuslib.Utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by blackmamba on 15.11.16.
 */

public class Utils {

    public static void toast(Context context,String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
