package biz.ideus.ideuslibexample.data;

import android.databinding.Bindable;
import android.databinding.Observable;
import android.os.Parcelable;

import io.requery.Entity;
import io.requery.Key;
import io.requery.Persistable;

/**
 * Created by user on 09.11.2016.
 */

@Entity
public interface Cities extends Observable, Parcelable, Persistable {
    @Key
    int getId();

    @Bindable
    String getTitle();
    void setTitle(String title);
}