package biz.ideus.ideuslibexample.data.model.response.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 30.01.2017.
 */

public class CheckUpdateData {
    @SerializedName("release")
    Release release;

    public Release getRelease() {
        return release;
    }

    public class Release {
        String date;
        String version;
        boolean required;
        String description;

        public String getDate() {
            return date;
        }

        public String getVersion() {
            return version;
        }

        public boolean isRequired() {
            return required;
        }

        public String getDescription() {
            return description;
        }

        //        "date": "2016-01-05",
//                "version": "0.1",
//                "required": false,
//                "description": "First Alpha version"
    }
}
