package biz.ideus.ideuslib.adapter.typeface_adapters;

import android.databinding.BindingAdapter;
import android.graphics.Typeface;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 09.11.2016.
 */

public class DLibTypefaceAdapter {
    private static Map<String, Typeface> cachedTypefaces = new HashMap<>();
    private static Map<String, String> fonts = new HashMap<>();
    public static void addFontDefinition(String fontName, String fontDefinition){
        fonts.put(fontName, fontDefinition);
    }

//    public enum Font {
//        F300("normal", "fonts/MuseoSansCyrl.otf" ),
//        F500("semibold", "fonts/MuseoSansCyrl_0.otf" ),
//        F700("bold", "fonts/MuseoSansCyrl_1.otf" );
//        private String value;
//        private String fontPath;
//        Font(String value, String fontPath){
//            this.value = value;
//            this.fontPath = fontPath;
//        }
//        public static Font getFontFromIndex(String name){
//            for (Font font : values()){
//                if (font.value.equals(name)){
//                    return font;
//                }
//            }
//            return F300;
//        }
//    }

    @BindingAdapter({"font"})
    public static void setTypeface(TextView textView, String value){
        Typeface myTypeface = cachedTypefaces.get(value);
        if (myTypeface == null) {
            //Font font = Font.getFontFromIndex(value);
           // myTypeface = Typeface.createFromAsset(textView.getContext().getAssets(), font.fontPath);
            myTypeface = Typeface.createFromAsset(textView.getContext().getAssets(), fonts.get(value));
            cachedTypefaces.put(value, myTypeface);
        }
        textView.setTypeface(myTypeface);
    }
}
