package barkote.emo.login.Network;


import barkote.emo.login.Network.models.Results;
import barkote.emo.login.Network.models.Signin;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface NetworkCalls {

    @POST("/users/authenticate")
    Call<Results> login(@Body Signin signin);

}