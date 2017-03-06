package biz.ideus.ideuslib.Utils;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.TypedValue;
import android.view.WindowManager;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by blackmamba on 15.11.16.
 */

public class Utils {

    private Utils() {}

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

    /**
     * @param context used to get system services
     * @return screenWidth in pixels
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Point size = new Point();
        wm.getDefaultDisplay().getSize(size);
        return size.x;
    }

    /**
     * This method can be extended to get all android attributes color, string, dimension ...etc
     *
     * @param context          used to fetch android attribute
     * @param androidAttribute attribute codes like R.attr.colorAccent
     * @return in this case color of android attribute
     */
    public static int fetchContextColor(Context context, int androidAttribute) {
        TypedValue typedValue = new TypedValue();

        TypedArray a = context.obtainStyledAttributes(typedValue.data, new int[]{androidAttribute});
        int color = a.getColor(0, 0);

        a.recycle();

        return color;
    }

    /**
     * @param context used to fetch display metrics
     * @param dp      dp value
     * @return pixel value
     */
    public static int dp2px(Context context, float dp) {
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
        return Math.round(px);
    }

}
