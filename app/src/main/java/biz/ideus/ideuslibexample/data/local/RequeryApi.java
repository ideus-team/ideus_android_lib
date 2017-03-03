package biz.ideus.ideuslibexample.data.local;

import android.annotation.SuppressLint;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import biz.ideus.ideuslibexample.data.model.response.response_model.AutorisationEntity;
import biz.ideus.ideuslibexample.data.model.response.response_model.MessageEntity;
import biz.ideus.ideuslibexample.data.model.response.response_model.PeopleEntity;
import biz.ideus.ideuslibexample.injection.scopes.PerApplication;
import biz.ideus.ideuslibexample.ui.chat_screen.MessageViewModel;
import io.requery.Persistable;
import io.requery.query.Result;
import io.requery.rx.SingleEntityStore;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

//import io.requery.query.Result;

/**
 * Created by user on 18.11.2016.
 */

@PerApplication
@SuppressLint("NewApi")
public class RequeryApi implements IRequeryApi {

    private final SingleEntityStore<Persistable> data;

    public SingleEntityStore<Persistable> getData() {
        return data;
    }

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
    public Observable<PeopleEntity> getPeopleEntity(String peopleId) {
        return data.select(PeopleEntity.class).where(PeopleEntity.IDENT.equal(peopleId)).get()
                .toObservable().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public List<PeopleEntity> getPeopleEntityList() {
        List<PeopleEntity> peopleEntitiesList = new ArrayList<>();
        data.select(PeopleEntity.class).get()
                .toSelfObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(peopleEntities -> {
                    peopleEntitiesList.addAll(peopleEntities.toList());
                });
        return peopleEntitiesList;
    }

    public Observable<Result<PeopleEntity>> getPeopleEntity() {


        return data.select(PeopleEntity.class).get()
                .toSelfObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    @Override
    public void storeAutorisationInfo(AutorisationEntity autorisationEntity) {
        data.delete(AutorisationEntity.class).get().value();
        data.insert(autorisationEntity).subscribe();
    }


    @Override
    public void updateAutorisationInfo(AutorisationEntity autorisationEntity) {
        data.upsert(autorisationEntity).subscribe();
    }

    @Override
    public void storePeopleListPagination(Iterable<PeopleEntity> peopleEntityList) {
        data.upsert(peopleEntityList).subscribe();
    }

    @Override
    public void storePeopleList(Iterable<PeopleEntity> peopleEntityList) {
        data.delete(PeopleEntity.class).get().value();
        data.insert(peopleEntityList).subscribe();
    }

    @Override
    public void updateCurrentPeopleEntity(PeopleEntity peopleEntity) {
        data.upsert(peopleEntity).subscribe();
    }


    @Override
    public void deletePeopleList() {
        data.delete(PeopleEntity.class).get().value();
    }

    @Override
    public void storeMessageList(Iterable<MessageEntity> messageEntitiesList) {
        data.upsert(messageEntitiesList).subscribe();
    }

    @Override
    public List<MessageViewModel> getMessageList(String userId) {
        List<MessageEntity> messageListEntities;
        ArrayList<MessageViewModel> messageViewModelList = new ArrayList<>();

        messageListEntities = data.select(MessageEntity.class)
                .where(MessageEntity.USER_ID.eq(userId)).orderBy(MessageEntity.CREATED_AT.asc()).get().toList();
        
        for(int i = 0; i < messageListEntities.size();i++){
            messageViewModelList.add(new MessageViewModel(messageListEntities.get(i)));
        }
        return messageViewModelList;
    }

    @Override
    public Observable<MessageEntity> storeMessage(MessageEntity messageEntity) {
        return data.upsert(messageEntity).toObservable();
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<MessageEntity> updateMessage(MessageEntity messageEntity) {
        return null;
    }

    @Override
    public Observable<Void> deleteMessage(MessageEntity messageEntity) {
        return data.delete(messageEntity).toObservable();
    }

}


