package biz.ideus.ideuslibexample.data.model.response.response_model;

import android.databinding.Bindable;
import android.databinding.Observable;
import android.os.Parcelable;

import io.requery.Entity;
import io.requery.Key;
import io.requery.Persistable;

/**
 * Created by blackmamba on 22.12.16.
 */
@Entity
public interface Autorisation extends Observable, Parcelable, Persistable {
    @Key
    String getIdent();

    String getApi_token();

    void setApi_token(String api_token);

    @Bindable
    String getEmail();

    void setEmail(String email);

    @Bindable
    String getFriendsCount();

    void setFriendsCount(String friendsCount);

    @Bindable
    String getPhoto();

    void setPhoto(String photo);

    @Bindable
    String getFirst_name();

    void setFirst_name(String first_name);

    @Bindable
    String getLast_name();

    void setLast_name(String last_name);
}
