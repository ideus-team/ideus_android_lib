package biz.ideus.ideuslibexample.data.remote;


import com.squareup.okhttp.RequestBody;

import java.util.Map;

import biz.ideus.ideuslibexample.data.model.request.BaseRequestModelWithToken;
import biz.ideus.ideuslibexample.data.model.request.LoginModel;
import biz.ideus.ideuslibexample.data.model.request.SaveFilesModel;
import biz.ideus.ideuslibexample.data.model.request.SignUpModel;
import biz.ideus.ideuslibexample.data.model.request.SocialsAutorisationModel;
import biz.ideus.ideuslibexample.data.model.response.AutorisationAnswer;
import biz.ideus.ideuslibexample.data.model.response.ServerAnswer;
import biz.ideus.ideuslibexample.data.model.response.UploadFileAnswer;
import biz.ideus.ideuslibexample.data.model.response.UserFilesAnswer;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import rx.Observable;


public interface NetApi {
    @POST("user/login")
    Observable<AutorisationAnswer> login(@Body LoginModel loginModel);

    @POST("user/signup")
    Observable<AutorisationAnswer> signUp(@Body SignUpModel signUpModel);

    @POST("user/loginSocial")
    Observable<AutorisationAnswer> autorisationSocial(@Body SocialsAutorisationModel socialsAutorisationModel);

    @POST("user/files")
    Observable<UserFilesAnswer> getUserFiles(@Body BaseRequestModelWithToken baseRequestModelWithToken);


    @POST("upload/save_files")
    Observable<ServerAnswer> saveFiles(@Body SaveFilesModel saveFilesModel);

    @Multipart
    @POST("upload/send")
    Observable<UploadFileAnswer> uploadFile(@PartMap Map<String, RequestBody> params, @Query("api_token") String token) ;

}
