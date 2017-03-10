package biz.ideus.ideuslibexample.network.response.entity_model;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.requery.Entity;
import io.requery.Key;

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
}
