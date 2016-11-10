package biz.ideus.ideuslibexample;

import android.app.Application;
import android.content.res.Resources;

import biz.ideus.ideuslib.adapter.typeface_adapters.DLibTypefaceAdapter;
import biz.ideus.ideuslibexample.injection.components.AppComponent;
import biz.ideus.ideuslibexample.injection.components.DaggerAppComponent;
import biz.ideus.ideuslibexample.injection.modules.AppModule;


/**
 * Created by user on 09.11.2016.
 */

public class SampleApplication extends Application {


    protected void setupFonts() {
        DLibTypefaceAdapter.addFontDefinition("normal", "fonts/MuseoSansCyrl.otf");
    }

    private static SampleApplication sInstance = null;

    private static AppComponent sAppComponent = null;

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;
        sAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

       // if(BuildConfig.DEBUG) { Timber.plant(new Timber.DebugTree()); }
    }

    public static SampleApplication getInstance() { return sInstance; }

    public static AppComponent getAppComponent() { return sAppComponent; }

    public static Resources getRes() { return sInstance.getResources(); }
    
}
