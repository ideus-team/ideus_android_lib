package biz.ideus.ideuslib.Utils;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by blackmamba on 15.11.16.
 */

public class Utils {

    public static void toast(Context context,String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static Bitmap getBitMapImage(ContentResolver contentResolver, Uri uri) {
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public static Drawable getDrawableImage(ContentResolver contentResolver, Uri uri) {
        Drawable drawable = null;
        try {
            drawable =new BitmapDrawable(MediaStore.Images.Media.getBitmap(contentResolver, uri));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return drawable;
    }
}
