package biz.ideus.ideuslibexample.network.response.entity_model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import io.requery.Entity;
import io.requery.Key;
import io.requery.OneToMany;

/**
 * Created by user on 10.03.2017.
 */
@Entity
public abstract class AbstractBoardStories {

    @JsonProperty("ident")
    @Key
    protected int ident;


    @JsonProperty("name")
    protected String name;

    @JsonProperty("lists")
    @OneToMany
    protected List<Story> lists;


    public abstract List<Story> getLists();
    public abstract int getIdent();
    public abstract void setIdent(int ident);
    public abstract String getName();
    public abstract void setName(String name);


//    public void TransformTo(AbstractBoardStories dest){
//        dest.setIdent(this.getIdent());
//        dest.setName(this.getName());
//        for(Story c : this.getLists()) {
//            Story newCard = new Story();
//            c.TransformTo(newCard);
//            newCard.setBoardStories((BoardStories) dest);
//            dest.getLists().add(newCard);
//        }
//    }

    public BoardStories getTransformed() {
        BoardStories dest = new BoardStories();
        dest.setIdent(this.getIdent());
        dest.setName(this.getName());
        for(Story c : this.getLists()) {
            Story newCard = c.getTransformed();
            newCard.setBoardStories((BoardStories) dest);
            dest.getLists().add(newCard);
        }
        return dest;
    }
}
