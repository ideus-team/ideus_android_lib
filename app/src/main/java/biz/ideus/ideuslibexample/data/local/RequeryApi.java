package biz.ideus.ideuslibexample.data.local;

import android.annotation.SuppressLint;

import javax.inject.Inject;

import biz.ideus.ideuslibexample.data.model.response.response_model.AutorisationEntity;
import biz.ideus.ideuslibexample.injection.scopes.PerApplication;
import io.requery.Persistable;
import io.requery.rx.SingleEntityStore;
import rx.Observable;

/**
 * Created by user on 18.11.2016.
 */

@PerApplication
@SuppressLint("NewApi")
public class RequeryApi implements IRequeryApi {

    private final SingleEntityStore<Persistable> data;

    @Inject
    public RequeryApi(SingleEntityStore<Persistable> data) {
        this.data = data;
    }

    @Override
    public Observable<String> getFavoriteChangeObservable() {
        return Observable.just("asd");
    }

    @Override
    public Observable<AutorisationEntity> getAutorisationInfo() {
        return data.select(AutorisationEntity.class).get().toObservable();
    }

    @Override
    public void storeAutorisationInfo(AutorisationEntity autorisationEntity) {
        data.delete(AutorisationEntity.class).get().value();
        data.insert(autorisationEntity).subscribe();
    }
}
