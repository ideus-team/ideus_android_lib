package biz.ideus.ideuslibexample.data.model.response.response_model;

import android.databinding.Bindable;
import android.databinding.Observable;
import android.os.Parcelable;

import io.requery.Entity;
import io.requery.Key;
import io.requery.Persistable;

/**
 * Created by blackmamba on 16.01.17.
 */
@Entity
public interface People extends Observable, Parcelable, Persistable {

    @Key
    int getIdent();
    void setIdent(int ident);

    @Bindable
    String getFirst_name();

    void setFirst_name(String first_name);

    @Bindable
    String getLast_name();

    void setLast_name(String last_name);

    @Bindable
    String getPhoto();

    void setPhoto(String photo);

    @Bindable
    String getLast_message();

    void setLast_message(String last_message);

    @Bindable
    String getBirthday();

    void setBirthday(String birthday);


    @Bindable
    String getEmail();

    void setEmail(String email);


    @Bindable
    boolean isFavorite();

    void setFavorite(boolean favorite);
}
