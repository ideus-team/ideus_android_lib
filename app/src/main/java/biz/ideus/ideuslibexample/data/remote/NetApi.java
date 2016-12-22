package biz.ideus.ideuslibexample.data.remote;


import biz.ideus.ideuslibexample.data.model.request.LoginModel;
import biz.ideus.ideuslibexample.data.model.request.SignUpModel;
import biz.ideus.ideuslibexample.data.model.request.SocialsAutorisationModel;
import biz.ideus.ideuslibexample.data.model.response.AutorisationAnswer;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;


public interface NetApi {
    @POST("user/login")
    Observable<AutorisationAnswer> login(@Body LoginModel loginModel);

    @POST("user/signup")
    Observable<AutorisationAnswer> signUp(@Body SignUpModel signUpModel);

    @POST("user/loginSocial")
    Observable<AutorisationAnswer> autorisationSocial(@Body SocialsAutorisationModel socialsAutorisationModel);
}
