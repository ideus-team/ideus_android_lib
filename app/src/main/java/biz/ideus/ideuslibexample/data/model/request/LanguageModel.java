package biz.ideus.ideuslibexample.data.model.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by blackmamba on 22.12.16.
 */

public class LanguageModel {
    @SerializedName("lang")
    private String lang;

    public LanguageModel() {
        this.lang = "en";
    }
}