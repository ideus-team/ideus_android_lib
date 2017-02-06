package biz.ideus.ideuslib.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

import biz.ideus.ideuslib.R;




/**
 * Created by user on 09.11.2016.
 */

@BindingMethods({
        @BindingMethod(type = ImageLoaderView.class, attribute = "imageUrl",
                method = "loadImage")
        ,
        @BindingMethod(type = ImageLoaderView.class, attribute = "imageFlag",
                method = "setImageFlag")})
public class ImageLoaderView extends ImageView {
    private String url;
    private String imageFlag;

    public void setImageFlag(String imageFlag) {
        this.imageFlag = imageFlag;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ImageLoaderView(Context context) {
        super(context);
    }

    public ImageLoaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageLoaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs
                , R.styleable.ImageLoaderView, defStyleAttr, 0);
        loadImage(
                typedArray.getString(R.styleable.ImageLoaderView_imageUrl)
        );
        typedArray.recycle();
    }


    public void loadImage(String imageUrl) {
        if (imageUrl != null && !imageUrl.isEmpty()) {
            setUrl(imageUrl);
            ImageLoader.getInstance().displayImage(imageUrl, this);
        }
    }
}

