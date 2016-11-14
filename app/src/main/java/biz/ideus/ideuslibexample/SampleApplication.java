package biz.ideus.ideuslibexample;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;

import biz.ideus.ideuslib.adapter.typeface_adapters.DLibTypefaceAdapter;
import biz.ideus.ideuslib.application.DLibApplication;
import biz.ideus.ideuslibexample.utils.Constants;
import io.fabric.sdk.android.Fabric;

/**
 * Created by user on 09.11.2016.
 */

public class SampleApplication extends DLibApplication {
    public static CallbackManager faceBookCallbackManager;
    public static TwitterAuthClient twitterAuthClient;
    @Override
    protected void setupFonts() {
        DLibTypefaceAdapter.addFontDefinition("normal", "fonts/MuseoSansCyrl.otf");
    }

    @Override
    protected void setupFaceBookSDK() {
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        faceBookCallbackManager = CallbackManager.Factory.create();

    }

    @Override
    protected void setupTwitterLogin() {
        TwitterAuthConfig authConfig = new TwitterAuthConfig(Constants.TWITTER_APP_KEY, Constants.TWITTER_SECRET_KEY);
        Fabric.with(this, new Twitter(authConfig));
        twitterAuthClient = new TwitterAuthClient();
    }


}
