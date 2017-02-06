package biz.ideus.ideuslibexample.data.model.response.response_model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by blackmamba on 17.01.17.
 */

public class PeopleModel {

    @SerializedName("ident")
   private String ident;
    @SerializedName("first_name")
    private String firstname;
    @SerializedName("last_name")
    private String lastName;
    @SerializedName("photo")
    private String photo;
    @SerializedName("last_message")
    private LastMessageModel lastMessage;
    @SerializedName("birthday")
    private String birthday;
    @SerializedName("email")
    private String email;
    @SerializedName("favorite")
    boolean isFavourite;


    public PeopleEntity getPeopleEntity() {
        PeopleEntity peopleEntity = new PeopleEntity();
        peopleEntity.setIdent(this.ident);
        peopleEntity.setFirst_name(this.firstname);
        peopleEntity.setLast_name(this.lastName);
        peopleEntity.setBirthday(this.birthday);
        peopleEntity.setEmail(this.email);
        peopleEntity.setPhoto(this.photo);
        if(this.lastMessage.getMessage() != null) {
            peopleEntity.setLastMessage(this.lastMessage.getMessage());
        }
        peopleEntity.setFavorite(this.isFavourite);
        return peopleEntity;
    }

}
