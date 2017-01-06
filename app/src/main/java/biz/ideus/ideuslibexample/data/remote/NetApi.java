package biz.ideus.ideuslibexample.data.remote;


import com.squareup.okhttp.RequestBody;

import java.util.Map;

import biz.ideus.ideuslibexample.data.model.request.RequestWithToken;
import biz.ideus.ideuslibexample.data.model.request.LoginModel;
import biz.ideus.ideuslibexample.data.model.request.ResetPasswordRequest;
import biz.ideus.ideuslibexample.data.model.request.SaveFiles;
import biz.ideus.ideuslibexample.data.model.request.SignUpModel;
import biz.ideus.ideuslibexample.data.model.request.SocialsAutorisationModel;
import biz.ideus.ideuslibexample.data.model.response.AutorisationAnswer;
import biz.ideus.ideuslibexample.data.model.response.ResetPasswordAnswer;
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
    Observable<UserFilesAnswer> getUserFiles(@Body RequestWithToken requestWithToken);

    @POST("upload/save_files")
    Observable<ServerAnswer> saveFiles(@Body SaveFiles saveFilesModel);

    @POST("user/delete")
    Observable<ServerAnswer> deleteAccount(@Body RequestWithToken requestWithToken);

    @POST("user/reset_password")
    Observable<ResetPasswordAnswer> resetPassword(@Body ResetPasswordRequest resetPasswordRequest);


    @Multipart
    @POST("upload/send")
    Observable<UploadFileAnswer> uploadFile(@PartMap Map<String, RequestBody> params, @Query("api_token") String token) ;

}
