package biz.ideus.ideuslibexample.utils;

import com.orhanobut.hawk.Hawk;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import biz.ideus.ideuslibexample.data.model.response.UploadFileAnswer;
import biz.ideus.ideuslibexample.data.remote.NetApi;
import rx.Observable;

/**
 * Created by blackmamba on 21.12.16.
 */

public class Utils {
    
    public static Observable<UploadFileAnswer> getUploadedObservable(NetApi netApi, String picturePath) {
        MediaType MEDIA_TYPE = MediaType.parse("image/*");
        File f = new File(picturePath);
        RequestBody requestBodyfile = RequestBody.create(MEDIA_TYPE, f);
        String image_name = f.getName();
        String fileName = "file\"; filename=\"" + image_name;
        Map<String, RequestBody> map = new HashMap<>();
        map.put(fileName, requestBodyfile);
        return netApi.uploadFile(map, Hawk.get(Constants.USER_TOKEN));
    }
}
