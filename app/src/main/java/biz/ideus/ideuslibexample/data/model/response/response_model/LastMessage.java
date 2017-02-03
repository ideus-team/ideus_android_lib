package biz.ideus.ideuslibexample.data.model.response.response_model;

import android.databinding.Bindable;
import android.databinding.Observable;
import android.os.Parcelable;

import io.requery.Entity;
import io.requery.Key;
import io.requery.Persistable;

/**
 * Created by blackmamba on 17.01.17.
 */
@Entity
public interface LastMessage extends Observable, Parcelable, Persistable {
    @Key
    int getIdent();
    void setIdent(int ident);

    @Bindable
    String getCreatedAt();
    void setCreatedAt(String created_at);


    @Bindable
    boolean isMine();
    void setMine(boolean is_mine);

    @Bindable
    String getMessage();
    void setMessage(String message);

}

