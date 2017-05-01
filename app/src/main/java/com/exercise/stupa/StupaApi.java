package com.exercise.stupa;

import com.exercise.stupa.object.retrofit.LoginRequest;
import com.exercise.stupa.object.retrofit.LoginResponse;
import com.exercise.stupa.object.retrofit.PresenceResponse;
import com.exercise.stupa.object.retrofit.ProfilResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by anjas on 01/05/17.
 */

public interface StupaApi {
    @POST("login")
    Call<LoginResponse> postLogin(@Body LoginRequest loginRequest);
    
    @GET("presence")
    Call<PresenceResponse> getPresence();

    @GET("profil")
    Call<ProfilResponse> getProfil();
}
