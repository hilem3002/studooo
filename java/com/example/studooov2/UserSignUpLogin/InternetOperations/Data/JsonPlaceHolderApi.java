package com.example.studooov2.UserSignUpLogin.InternetOperations.Data;

import com.example.studooov2.UserSignUpLogin.Models.Comment;
import com.example.studooov2.UserSignUpLogin.Models.Posts;
import com.example.studooov2.UserSignUpLogin.Models.regularUser;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
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

    // sending a get request to api with postId and regularUser object
    @GET("posts")
    Single<List<Posts>> getPosts(@Query("postId") int postId, @Query("username") String username);

    // sending a post to database
    @POST("posts")
    Single<Posts> sendPosts(@Body Posts posts);

    @DELETE("posts/{postId}")
    Single<Posts> deletePosts(@Path("postId") int postId);

    @PUT("posts/{postId}")
    Single<Posts> putPost(@Path("postId") int postId, @Body  Posts post);

    @GET("regularUsers/filterSearch/{offset}/{pageSize}")
    Single<List<regularUser>> getRegularUsersByFilter(@Path("offset") int offset, @Path("pageSize") int pageSize, @Query("letter") String letter);

    @GET("comments")
    Single<List<Comment>> getComments(@Query("postId") int postId);

    @POST("comments")
    Single<Comment> postComment(@Body Comment comment);

    @DELETE("comments/{comment_id}")
    Single<Comment> deleteComment(@Path("comment_id") int comment_id);



}

