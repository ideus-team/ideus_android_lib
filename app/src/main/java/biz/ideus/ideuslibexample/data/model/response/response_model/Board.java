package biz.ideus.ideuslibexample.data.model.response.response_model;

import android.databinding.Bindable;
import android.databinding.Observable;
import android.os.Parcelable;

import io.requery.Entity;
import io.requery.Key;
import io.requery.Persistable;

/**
 * Created by blackmamba on 07.02.17.
 */

@Entity
public interface Board extends Observable, Parcelable, Persistable {

//    request: {"command": "get_boards"}
//
//    response ok: {"command": "boards_list", "data": {"boards": [{"cards_deadline_count": "2", "board": {"ident": "1", "name": "new board name"}, "cards_count": "4"}]}}

    @Key
    String getIdent();

    void setIdent(String ident);

    @Bindable
    String getName();

    void setName(String name);

    @Bindable
    String getCardsDeadlineCount();

    void setCardsDeadlineCount(String cardsDeadlineCount);


    @Bindable
    String getCardsCount();

    void setCardsCount(String cardsCount);

}
