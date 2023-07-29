package com.example.studooov2.UserSignUpLogin.InternetOperations.Data;

import com.example.studooov2.UserSignUpLogin.Models.regularUser;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {

    // sending a get request to api with username and eposta veriables
    @GET("regularUsers")
    Single<List<regularUser>> getRegularUsers(@Query("username") String usernameEposta, @Query("eposta") String usernameEposta2);

    // sending a post request to api with a RegularUser object
    @POST("regularUsers")
    Single<regularUser> postRegularUsers(@Body regularUser user);

    // sending a put request to api with a path and RegularUser object
    @PUT("regularUsers/{username}")
    Single<regularUser> putRegularUsers(@Path("username") String username, @Body regularUser user);

}

