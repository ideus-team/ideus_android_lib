package biz.ideus.ideuslibexample.network.response.entity_model;

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
public interface Boards extends Observable, Parcelable, Persistable {


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
