package com.susnjara.rmaprojekt;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {

    String BASE_URL = "http://10.0.2.2:3000/";
    @GET("translate/{this}")
    Call<Translate> getTranslation(@Path("this") String input);

}
