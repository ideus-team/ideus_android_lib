package biz.ideus.ideuslib.Utils;

import android.widget.Toast;

import biz.ideus.ideuslib.application.DLibApplication;

/**
 * Created by blackmamba on 15.11.16.
 */

public class Utils {

    public static void toast(String message) {
        Toast.makeText(DLibApplication.getInstance().getApplicationContext()
                , message, Toast.LENGTH_SHORT).show();
    }
}
