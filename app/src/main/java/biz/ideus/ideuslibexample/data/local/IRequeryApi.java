package biz.ideus.ideuslibexample.data.local;

import java.util.List;

import biz.ideus.ideuslibexample.data.model.response.response_model.AutorisationEntity;
import biz.ideus.ideuslibexample.data.model.response.response_model.MessageEntity;
import biz.ideus.ideuslibexample.data.model.response.response_model.PeopleEntity;
import biz.ideus.ideuslibexample.ui.chat_screen.MessageViewModel;
import io.requery.rx.RxResult;
import rx.Observable;

/**
 * Created by user on 21.11.2016.
 */

public interface IRequeryApi {
    Observable<String> getFavoriteChangeObservable();
    void storePeopleList(Iterable<PeopleEntity> peopleEntityList);
    void updateAutorisationInfo(AutorisationEntity autorisationEntity);
    List<PeopleEntity> getPeopleEntityList();
    //Observable<Result<PeopleEntity>> getPeopleEntity();
    Observable<RxResult<PeopleEntity>> getPeopleEntity();

    Observable<AutorisationEntity> getAutorisationInfo();
    void storeAutorisationInfo(AutorisationEntity autorisationEntity);


    Observable<PeopleEntity> getPeopleEntity(String peopleId);
    void storePeopleListPagination(Iterable<PeopleEntity> peopleEntityList);
    void updateCurrentPeopleEntity(PeopleEntity peopleEntity);
    void deletePeopleList();

    void storeMessageList(Iterable<MessageEntity> messageEntitiesList);
    List<MessageViewModel> getMessageList(String userId);
    Observable<MessageEntity> storeMessage(MessageEntity messageEntity);
    Observable<MessageEntity> updateMessage(MessageEntity messageEntity);
    Observable<Void> deleteMessage(MessageEntity messageEntity);

}
