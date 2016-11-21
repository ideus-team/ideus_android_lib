package biz.ideus.ideuslibexample.data.local;

import android.annotation.SuppressLint;

import biz.ideus.ideuslibexample.injection.scopes.PerApplication;
import io.requery.Persistable;
import io.requery.rx.SingleEntityStore;
import rx.Observable;

/**
 * Created by user on 18.11.2016.
 */

@PerApplication
@SuppressLint("NewApi")
public class RequeryApi implements IRequeryApi{

    private SingleEntityStore<Persistable> dataStore;

//    @Provides
//    @Singleton
//    SingleEntityStore<Persistable> getData() {
//
//        DatabaseSource source = new DatabaseSource(this, Models.DEFAULT, 1);
//        source.setLoggingEnabled(true);
//        if (BuildConfig.DEBUG) {
//            // use this in development mode to drop and recreate the tables on every upgrade
//            source.setTableCreationMode(TableCreationMode.DROP_CREATE);
//        }
//        Configuration configuration = source.getConfiguration();
//        dataStore = RxSupport.toReactiveStore(new EntityDataStore<Persistable>(configuration));
//        return dataStore;
//    }

    @Override
    public Observable<String> getFavoriteChangeObservable() {
        return Observable.just("asd");
    }
}
