package biz.ideus.ideuslibexample.data.model.response.response_model;

import android.databinding.Bindable;
import android.databinding.Observable;
import android.os.Parcelable;

import io.requery.Entity;
import io.requery.Key;
import io.requery.Persistable;

/**
 * Created by blackmamba on 30.01.17.
 */
@Entity
public interface Message extends Observable, Parcelable, Persistable {

    @Key
    String getIdent();
    void setIdent(String ident);
    @Bindable
    String getTimeMessage();
    void setTimeMessage(String timeMessage);
    @Bindable
    String getDateMessage();
    void setDateMessage(String dateMessage);
    @Bindable
    boolean isUpdated();
    void setUpdated(boolean is_updated);

    @Bindable
    String getKind();
    void setKind(String kind);
    @Bindable
    int getCreatedAt();
    void setCreatedAt(int created_at);
    @Bindable
    String getUserId();
    void setUserId(String user_id);
    @Bindable
    String getMessage();
    void setMessage(String message);
    @Bindable
    boolean isOwner();
    void setOwner(boolean is_owner);
}


