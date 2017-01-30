package biz.ideus.ideuslibexample.data.remote;


import biz.ideus.ideuslibexample.data.model.request.AddAndDeleteFavoriteRequest;
import biz.ideus.ideuslibexample.data.model.request.BaseRequestModel;
import biz.ideus.ideuslibexample.data.model.request.GetPeopleRequest;
import biz.ideus.ideuslibexample.data.model.request.GetUserMessagesRequest;
import biz.ideus.ideuslibexample.data.model.request.LoginModelRequest;
import biz.ideus.ideuslibexample.data.model.request.RequestWithToken;
import biz.ideus.ideuslibexample.data.model.request.ResetPasswordRequest;
import biz.ideus.ideuslibexample.data.model.request.SaveFilesRequest;
import biz.ideus.ideuslibexample.data.model.request.SignUpRequest;
import biz.ideus.ideuslibexample.data.model.request.SocialsAutorisationRequest;
import biz.ideus.ideuslibexample.data.model.request.UpdateProfileRequest;
import biz.ideus.ideuslibexample.data.model.response.AutorisationAnswer;
import biz.ideus.ideuslibexample.data.model.response.MessagesResponse;
import biz.ideus.ideuslibexample.data.model.response.PeopleAnswer;
import biz.ideus.ideuslibexample.data.model.response.ResetPasswordAnswer;
import biz.ideus.ideuslibexample.data.model.response.ServerAnswer;
import biz.ideus.ideuslibexample.data.model.response.UploadFileAnswer;
import biz.ideus.ideuslibexample.data.model.response.UserFilesAnswer;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;


public interface NetApi {
    @POST("user/login")
    Observable<AutorisationAnswer> login(@Body LoginModelRequest loginModelRequest);

    @POST("user/signup")
    Observable<AutorisationAnswer> signUp(@Body SignUpRequest signUpRequest);

    @POST("user/login_social")
    Observable<AutorisationAnswer> autorisationSocial(@Body SocialsAutorisationRequest socialsAutorisationRequest);

    @POST("user/update_profile")
    Observable<AutorisationAnswer> updateProfile(@Body UpdateProfileRequest updateProfileRequest);

    @POST("user/files")
    Observable<UserFilesAnswer> getUserFiles(@Body RequestWithToken requestWithToken);

    @POST("upload/save_files")
    Observable<ServerAnswer> saveFiles(@Body SaveFilesRequest saveFilesRequestModel);

    @POST("user/delete")
    Observable<ServerAnswer> deleteAccount(@Body RequestWithToken requestWithToken);

    @POST("user/reset_password")
    Observable<ResetPasswordAnswer> resetPassword(@Body ResetPasswordRequest resetPasswordRequest);

    @POST("user/contacts")
    Observable<ServerAnswer> getContacts(@Body BaseRequestModel resetPasswordRequest);

    @POST("user/find_users")
    Observable<PeopleAnswer> getPeople(@Body GetPeopleRequest getPeopleRequest);

    @POST("user/add_favorite")
    Observable<ServerAnswer> addFavorite(@Body AddAndDeleteFavoriteRequest addFavoriteRequest);

    @POST("user/delete_favorite")
    Observable<ServerAnswer> deleteFavorite(@Body AddAndDeleteFavoriteRequest deleteFavoriteRequest);

    @POST("user/messages")
    Observable<MessagesResponse> getUserMessages(@Body GetUserMessagesRequest messagesRequest);





    @Multipart
    @POST("upload/send")
    Observable<UploadFileAnswer> uploadFile(@Part MultipartBody.Part file) ;

}
