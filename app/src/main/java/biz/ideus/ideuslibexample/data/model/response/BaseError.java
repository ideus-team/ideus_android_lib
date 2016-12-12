package biz.ideus.ideuslibexample.data.model.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 12.12.2016.
 */

public abstract class BaseError {
    @SerializedName("form")
    public String form;
    @SerializedName("element")
    public String element;
    @SerializedName("label")
    public String label;
    @SerializedName("error")
    public String error;
    @SerializedName("errno")
    public int errno;

    public BaseError(String form, String element, String label, String error, int errno) {
        this.form = form;
        this.element = element;
        this.label = label;
        this.error = error;
        this.errno = errno;
    }

    public BaseError() {
    }
}