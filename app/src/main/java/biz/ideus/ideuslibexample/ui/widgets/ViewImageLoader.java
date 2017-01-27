package biz.ideus.ideuslibexample.ui.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

import biz.ideus.ideuslibexample.R;




/**
 * Created by blackmamba on 27.01.17.
 */


@BindingMethods({
        @BindingMethod(type = ViewImageLoader.class, attribute = "imageUrl",
                method = "loadImage")
        ,
        @BindingMethod(type = ViewImageLoader.class, attribute = "imageFlag",
                method = "setImageFlag")})
public class ViewImageLoader extends ImageView {
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

    public ViewImageLoader(Context context) {
        super(context);
    }

    public ViewImageLoader(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewImageLoader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs
                , R.styleable.ViewImageLoader, defStyleAttr, 0);
        loadImage(
                typedArray.getString(R.styleable.ViewImageLoader_imageUrl)
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
