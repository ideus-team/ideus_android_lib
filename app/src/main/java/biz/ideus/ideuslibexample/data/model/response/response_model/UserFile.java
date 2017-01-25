package biz.ideus.ideuslibexample.data.model.response.response_model;

import android.databinding.Observable;
import android.os.Parcelable;

import io.requery.Entity;
import io.requery.Key;
import io.requery.Persistable;

/**
 * Created by blackmamba on 21.12.16.
 */
@Entity
public interface UserFile extends Observable, Parcelable, Persistable {
    @Key
    String getIdent();


    String getFile();
    void setFile(String file);


    String getCreated_at();
    void setCreated_at(String created_at);
}
