package biz.ideus.ideuslibexample.network.response.entity_model;

import android.databinding.Bindable;
import android.databinding.Observable;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.requery.Entity;
import io.requery.Key;
import io.requery.ManyToOne;
import io.requery.Persistable;

/**
 * Created by user on 09.03.2017.
 */

@Entity
public interface BoardList  extends Observable, Parcelable, Persistable {


    @Key
    @JsonProperty("ident")
    String getIdent();

    @JsonProperty("ident")
    void setIdent(String ident);

    @Bindable
    @JsonProperty("name")
    String getName();

    @JsonProperty("name")
    void setName(String name);

    @ManyToOne
    BoardStories getBoardStories();

}
