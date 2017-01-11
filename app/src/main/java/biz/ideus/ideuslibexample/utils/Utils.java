package biz.ideus.ideuslibexample.utils;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.File;

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

    public static Drawable convertBitmapToDrawable(Context context, String imagePath){
        return new BitmapDrawable(context.getResources(), BitmapFactory.decodeFile(imagePath));
    }
}
