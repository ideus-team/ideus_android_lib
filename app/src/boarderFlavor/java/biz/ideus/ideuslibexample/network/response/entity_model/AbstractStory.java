package biz.ideus.ideuslibexample.network.response.entity_model;

import android.databinding.BaseObservable;

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
public abstract class AbstractStory extends BaseObservable {
    @JsonProperty("ident")
    @Key
    protected int ident;

    @JsonProperty("name")
    protected String name;

    @ManyToOne
    protected BoardStories boardStories;

    @OneToMany
    protected List<Card> cards;

    public abstract int getIdent();
    public abstract void setIdent(int ident);
    public abstract String getName();
    public abstract void setName(String name);
    public abstract List<Card> getCards();


//    public void TransformTo(AbstractStory dest) {
//        dest.setIdent(this.getIdent());
//        dest.setName(this.getName());
//        for(Card c : this.getCards()) {
//            Card newCard = new Card();
//            c.TransformTo(newCard);
//            newCard.setStory((Story) dest);
//            dest.getCards().add(newCard);
//        }
//    }
    public Story getTransformed() {
        Story dest = new Story();
        dest.setIdent(this.getIdent());
        dest.setName(this.getName());
        for(Card c : this.getCards()) {
            Card newCard = c.getTransformed();
            newCard.setStory((Story) dest);
            dest.getCards().add(newCard);
        }
        return dest;
    }
}
