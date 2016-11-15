package biz.ideus.ideuslib.application;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import biz.ideus.ideuslib.Models;
import io.requery.Persistable;
import io.requery.android.BuildConfig;
import io.requery.android.sqlite.DatabaseSource;
import io.requery.rx.RxSupport;
import io.requery.rx.SingleEntityStore;
import io.requery.sql.Configuration;
import io.requery.sql.EntityDataStore;
import io.requery.sql.SchemaModifier;
import io.requery.sql.TableCreationMode;

/**
 * Created by user on 09.11.2016.
 */

public abstract class DLibApplication extends Application {
    protected SingleEntityStore<Persistable> dataStore;
    protected static DLibApplication appInstance;
    protected Configuration configuration;


    protected abstract void setupFonts();
    protected abstract void setupFaceBookSDK();
    protected abstract void setupTwitterSDK();

    public static synchronized DLibApplication getInstance() {
        return appInstance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
     //   MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appInstance = this;
        setupFonts();
        setupFaceBookSDK();
        setupTwitterSDK();
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .defaultDisplayImageOptions(defaultOptions)
                .build();

        ImageLoader.getInstance().init(config);

        DatabaseSource source = new DatabaseSource(this, Models.DEFAULT, 1);
        source.setLoggingEnabled(true);
        if (BuildConfig.DEBUG) {
            // use this in development mode to drop and recreate the tables on every upgrade
            source.setTableCreationMode(TableCreationMode.DROP_CREATE);
        }
        configuration = source.getConfiguration();
        dataStore = RxSupport.toReactiveStore(new EntityDataStore<Persistable>(configuration));
    }



    public void deleteAllTables() {
        SchemaModifier tables = new SchemaModifier(configuration);
        try {
            tables.dropTables();
        } catch (Exception e) {
            Log.d("deleteAllTables", "exception" + e.getMessage());
        }
        TableCreationMode mode = TableCreationMode.DROP_CREATE;
        tables.createTables(mode);
    }
}
