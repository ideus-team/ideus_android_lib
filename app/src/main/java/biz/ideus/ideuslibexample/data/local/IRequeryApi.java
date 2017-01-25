package biz.ideus.ideuslibexample.data.local;

import java.util.List;

import biz.ideus.ideuslibexample.data.model.response.response_model.AutorisationEntity;
import biz.ideus.ideuslibexample.data.model.response.response_model.PeopleEntity;
import io.requery.query.Result;
import rx.Observable;

/**
 * Created by user on 21.11.2016.
 */

public interface IRequeryApi {
    Observable<String> getFavoriteChangeObservable();

    Observable<AutorisationEntity> getAutorisationInfo();

    Observable<PeopleEntity> getPeopleEntity(String peopleId);

    List<PeopleEntity> getPeopleEntityList();

    Observable<Result<PeopleEntity>> getPeopleEntity();

    void storeAutorisationInfo(AutorisationEntity autorisationEntity);

    void updateAutorisationInfo(AutorisationEntity autorisationEntity);

    void storePeopleListPagination(Iterable<PeopleEntity> peopleEntityList);
    void storePeopleList(Iterable<PeopleEntity> peopleEntityList);
    void updateCurrentPeopleEntity(PeopleEntity peopleEntity);
    void deletePeopleList();



}
