package biz.ideus.ideuslibexample.network.response.entity_model;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.requery.Entity;
import io.requery.Key;
import io.requery.ManyToOne;

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

    public void TransformTo(AbstractCard dest) {

    }
}
