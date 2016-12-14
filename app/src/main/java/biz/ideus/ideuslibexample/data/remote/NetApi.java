package biz.ideus.ideuslibexample.data.remote;


import biz.ideus.ideuslibexample.data.model.request.LoginModel;
import biz.ideus.ideuslibexample.data.model.request.SignUpModel;
import biz.ideus.ideuslibexample.data.model.request.SocialsAutorisationModel;
import biz.ideus.ideuslibexample.data.model.response.LoginAnswer;
import biz.ideus.ideuslibexample.data.model.response.SignUpAnswer;
import biz.ideus.ideuslibexample.data.model.response.SocialsAutorisationAnswer;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;


public interface NetApi {
    @POST("user/login")
    Observable<LoginAnswer> login(@Body LoginModel loginModel);

    @POST("user/signup")
    Observable<SignUpAnswer> signUp(@Body SignUpModel signUpModel);

    @POST("user/loginSocial")
    Observable<SocialsAutorisationAnswer> autorisationSocial(@Body SocialsAutorisationModel socialsAutorisationModel);
}
