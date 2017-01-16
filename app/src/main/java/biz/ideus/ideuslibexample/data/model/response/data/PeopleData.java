package biz.ideus.ideuslibexample.data.model.response.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import biz.ideus.ideuslibexample.data.model.response.response_model.PeopleEntity;

/**
 * Created by blackmamba on 16.01.17.
 */

public class PeopleData {
    @SerializedName("find_users")
    private List<PeopleEntity> peopleEntities;

    @SerializedName("count")
    private String count;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<PeopleEntity> getPeopleEntities() {
        return peopleEntities;
    }

    public void setPeopleEntities(List<PeopleEntity> peopleEntities) {
        this.peopleEntities = peopleEntities;
    }
}
