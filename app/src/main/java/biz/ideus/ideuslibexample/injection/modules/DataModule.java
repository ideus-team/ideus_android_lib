package biz.ideus.ideuslibexample.injection.modules;

import android.content.Context;

import biz.ideus.ideuslibexample.BuildConfig;
import biz.ideus.ideuslibexample.data.Models;
import biz.ideus.ideuslibexample.data.local.IRequeryApi;
import biz.ideus.ideuslibexample.data.local.RequeryApi;
import biz.ideus.ideuslibexample.injection.qualifier.AppContext;
import biz.ideus.ideuslibexample.injection.scopes.PerApplication;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import io.requery.Persistable;
import io.requery.android.sqlite.DatabaseSource;
import io.requery.rx.RxSupport;
import io.requery.rx.SingleEntityStore;
import io.requery.sql.Configuration;
import io.requery.sql.EntityDataStore;
import io.requery.sql.TableCreationMode;

/**
 * Created by user on 21.11.2016.
 */

@Module
public abstract class DataModule {


    @Provides
    @PerApplication
    static SingleEntityStore<Persistable> provideDataStore(@AppContext Context context) {
        DatabaseSource source = new DatabaseSource(context, Models.DEFAULT, 2);
        source.setLoggingEnabled(true);
        if (BuildConfig.DEBUG) {
            // use this in development mode to drop and recreate the tables on every upgrade
            source.setTableCreationMode(TableCreationMode.DROP_CREATE);
        }
        Configuration configuration = source.getConfiguration();
        return RxSupport.toReactiveStore(new EntityDataStore<Persistable>(configuration));
    }

    @Binds
    abstract IRequeryApi binddataApi(RequeryApi requeryApi);

}
