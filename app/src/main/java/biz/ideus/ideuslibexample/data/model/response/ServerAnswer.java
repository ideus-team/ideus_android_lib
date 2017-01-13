package biz.ideus.ideuslibexample.data.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 12.12.2016.
 */

public class ServerAnswer<T> {

    @SerializedName("data")
    public T data;
    @SerializedName("actions")
    public List errors = new ArrayList<BaseError>();
    @SerializedName("message")
    public String message;

    public ServerAnswer(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }
}