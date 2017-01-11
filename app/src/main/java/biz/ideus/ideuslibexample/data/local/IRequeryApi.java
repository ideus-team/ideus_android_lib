package biz.ideus.ideuslibexample.data.local;

import biz.ideus.ideuslibexample.data.model.response.response_model.AutorisationEntity;
import rx.Observable;

/**
 * Created by user on 21.11.2016.
 */

public interface IRequeryApi {
    Observable<String> getFavoriteChangeObservable();
    Observable<AutorisationEntity> getAutorisationInfo();
    void storeAutorisationInfo(AutorisationEntity autorisationEntity);
    void updateAutorisationInfo(AutorisationEntity autorisationEntity);
}
