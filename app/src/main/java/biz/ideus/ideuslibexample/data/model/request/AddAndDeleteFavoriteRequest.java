package biz.ideus.ideuslibexample.data.model.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by blackmamba on 20.01.17.
 */

public class AddAndDeleteFavoriteRequest {

    @SerializedName("user_id")
    private String user_id;

   public AddAndDeleteFavoriteRequest(String user_id){
       this.user_id = user_id;
   }
}
