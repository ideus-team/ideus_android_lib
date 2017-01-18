package biz.ideus.ideuslibexample;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.support.multidex.MultiDex;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.neovisionaries.ws.client.ProxySettings;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketFactory;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.NoEncryption;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import javax.net.ssl.SSLContext;

import biz.ideus.ideuslib.adapter.typeface_adapters.DLibTypefaceAdapter;
import biz.ideus.ideuslibexample.data.local.RequeryApi;
import biz.ideus.ideuslibexample.data.remote.NetApi;
import biz.ideus.ideuslibexample.injection.components.AppComponent;
import biz.ideus.ideuslibexample.injection.components.DaggerAppComponent;
import biz.ideus.ideuslibexample.injection.modules.AppModule;
import biz.ideus.ideuslibexample.utils.Constants;
import io.fabric.sdk.android.Fabric;
import io.requery.sql.Configuration;

/**
 * Created by user on 09.11.2016.
 */

public class SampleApplication extends Application {

    private RefWatcher refWatcher;
    private Configuration configuration;
    public static DisplayImageOptions imageLoaderDefaultDisplayOptions;
    public static Application mApplication;
    private WebSocketFactory factory;
    private SSLContext sslContext;
    private ProxySettings proxySettings;
    private WebSocket webSocket;
    public static RequeryApi requeryApi;
    public static NetApi netApi;

    public WebSocket getWebSocket() {
        return webSocket;
    }

    protected void setupFonts() {
        DLibTypefaceAdapter.addFontDefinition("normal", "fonts/MuseoSansCyrl.otf");
    }


    private static SampleApplication sInstance = null;

    private static AppComponent sAppComponent = null;

    @Override
    public void onCreate() {
        super.onCreate();
        refWatcher = LeakCanary.install(this);
        mApplication = this;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //Web socket
//        factory = new WebSocketFactory();
//        try {
//            sslContext = SSLContext.getInstance("TLS");
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//        factory.setSSLContext(sslContext);
//        factory.setConnectionTimeout(1000);
//
//        try {
//            webSocket = new WebSocketFactory().createSocket("ws://46.101.254.89:8080");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//        webSocket.connectAsynchronously();
//
//
//        webSocket.addListener(new WebSocketAdapter() {
//            @Override
//            public void onTextMessage(WebSocket websocket, String text) throws Exception {
//                RxChatMessageEvent.instanceOf().setRxChatMessageEvent(text);
//            }
//        });
        //

        sInstance = this;
        sAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

        requeryApi = sAppComponent.dataApi();
         netApi = sAppComponent.netApi();

        setupFaceBookSDK();
        setupTwitterSDK();
        setupUniversalImageLoaderConfig();
        Hawk.init(this)
                .setEncryption(new NoEncryption())
                .build();
       // if(BuildConfig.DEBUG) { Timber.plant(new Timber.DebugTree()); }


    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }


    private void setupUniversalImageLoaderConfig(){
        imageLoaderDefaultDisplayOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .defaultDisplayImageOptions(imageLoaderDefaultDisplayOptions)
                .build();
        ImageLoader.getInstance().init(config);
    }

    public static SampleApplication getInstance() { return sInstance; }

    public static AppComponent getAppComponent() { return sAppComponent; }

    public static Resources getRes() { return sInstance.getResources(); }

    protected void setupFaceBookSDK() {
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

    }

    protected void setupTwitterSDK() {
        TwitterAuthConfig authConfig = new TwitterAuthConfig(Constants.TWITTER_CONSUMER_KEY, Constants.TWITTER_CONSUMER_SECRET_KEY);
        Fabric.with(this, new Twitter(authConfig));
    }

    public static RefWatcher getRefWatcher(Context context) {
        SampleApplication application = (SampleApplication) context.getApplicationContext();
        return application.refWatcher;
    }



}
