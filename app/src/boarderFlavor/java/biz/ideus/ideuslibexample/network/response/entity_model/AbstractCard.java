package biz.ideus.ideuslibexample.network.response.entity_model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import io.requery.Entity;
import io.requery.Key;
import io.requery.ManyToOne;
import io.requery.OneToMany;

/**
 * Created by user on 10.03.2017.
 */
@Entity
public abstract class AbstractCard {
    @JsonProperty("ident")
    @Key
    protected int ident;

    @JsonProperty("name")
    protected String name;

    @JsonProperty("color")
    protected String color;

    @ManyToOne
    protected Story story;

    @OneToMany
    protected List<CardItem> files;

    public abstract int getIdent();
    public abstract void setIdent(int ident);
    public abstract String getName();
    public abstract void setName(String name);
    public abstract List<CardItem> getFiles();

//    public void TransformTo(AbstractCard dest) {
//        dest.setIdent(this.getIdent());
//        dest.setName(this.getName());
//        for(CardItem c : this.getFiles()) {
//            CardItem newCardItem = new CardItem();
//            c.TransformTo(newCardItem);
//            newCardItem.setCard((Card) dest);
//            dest.getFiles().add(newCardItem);
//        }
//    }

    public Card getTransformed(){
        Card dest = new Card();
        dest.setIdent(this.getIdent());
        dest.setName(this.getName());
        for(CardItem c : this.getFiles()) {
            CardItem newCardItem = c.getTransformed();
            newCardItem.setCard((Card) dest);
            dest.getFiles().add(newCardItem);
        }
        return dest;
    }
}
