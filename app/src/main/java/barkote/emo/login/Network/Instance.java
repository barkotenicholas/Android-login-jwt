package barkote.emo.login.Network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Instance {

    private static final String BASE_URL ="http://10.42.0.1:3000";
    private static Retrofit retrofit = null;

    public static NetworkCalls getApiClient(){

        if(retrofit ==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit.create(NetworkCalls.class);
    }
}
