package biz.ideus.ideuslibexample.network.response.entity_model;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.requery.Entity;
import io.requery.Key;
import io.requery.ManyToOne;

/**
 * Created by user on 10.03.2017.
 */
@Entity
public abstract class AbstractCardItem {
    @JsonProperty("ident")
    @Key
    protected int ident;

    @JsonProperty("file")
    protected String file;

    @ManyToOne
    protected Card card;

    public abstract int getIdent();
    public abstract void setIdent(int ident);
    public abstract String getFile();
    public abstract void setFile(String name);

//    public void TransformTo(AbstractCardItem dest) {
//        dest.setIdent(this.getIdent());
//        dest.setFile(this.getFile());
//    }

    public CardItem getTransformed(){
        CardItem dest = new CardItem();
        dest.setIdent(this.getIdent());
        dest.setFile(this.getFile());
        return dest;
    }
}

