package biz.ideus.ideuslibexample.data.model.response.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import biz.ideus.ideuslibexample.data.model.response.response_model.PeopleEntity;
import biz.ideus.ideuslibexample.data.model.response.response_model.PeopleModel;

/**
 * Created by blackmamba on 16.01.17.
 */

public class PeopleData {
    @SerializedName("users")
    private List<PeopleModel> peopleModels;

    public List<PeopleModel> getPeopleModels() {
        return peopleModels;
    }

    private List<PeopleEntity> peopleEntities;

    public List<PeopleEntity> getPeopleEntities() {
        return createPeopleEntityList(peopleModels);
    }

    private List<PeopleEntity> createPeopleEntityList(List<PeopleModel> peopleModels) {
        peopleEntities = new ArrayList<>();
        for(int i = 0; i < peopleModels.size();i++){
            peopleEntities.add(peopleModels.get(i).getPeopleEntity());
        }
        return peopleEntities;
    }
}
