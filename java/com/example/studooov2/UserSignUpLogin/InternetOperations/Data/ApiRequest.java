package com.example.studooov2.UserSignUpLogin.InternetOperations.Data;

import android.util.Log;

import com.example.studooov2.UserSignUpLogin.Models.Comment;
import com.example.studooov2.UserSignUpLogin.Models.Posts;
import com.example.studooov2.UserSignUpLogin.Models.regularUser;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiRequest {

    private Disposable disposable;

    // sending a get request to api and getting back a response
    public void getRequest(String usernameEposta, OnUsersLoadedListener listener) {

        listener.OnUsersLoading(true);
        // sending the user infos to getRegularUsers and it sending a get request to api
        Single<List<regularUser>> observable = apiSettings().getRegularUsers(usernameEposta, usernameEposta);

        // with observable proccessing this request in background(io)
        disposable = observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        users -> {
                            // sending the reponse to listener for anlyzing
                            listener.OnUsersLoaded(users);
                        },
                        throwable -> {
                            Log.d("t", throwable.getMessage());
                            // possible error handling
                            listener.OnUsersLoadFailed(true);
                        }

                );

    }


    // sending a post request to api and getting back a repsonse
    public void postRequest(regularUser user, OnUserLoadedListener listener) {

        listener.OnUserLoading(true);
        // sending the user object to postRegularUsers and it sending a post request
        Single<regularUser> observable = apiSettings().postRegularUsers(user);

        // with observable proccessing this request in background(io)
        disposable = observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userResponse -> {
                            // sending the response to listener for anlyzing
                            listener.OnUserLoaded(userResponse);
                        },
                        throwable -> {
                            // possible error handling
                            listener.OnUserLoadFailed(true);
                        });

    }


    // sending a put request to api and getting back a response
    public void putRequest(String username, regularUser user, OnUserLoadedListener listener) {

        listener.OnUserLoading(true);
        // sending the user object to putRegularUsers and it sending a put request
        Single<regularUser> observable = apiSettings().putRegularUsers(username, user);

        // with observable proccessing this request in background(io)
        disposable = observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userResponse -> {
                    // sending to response to listener for anlyzing
                    listener.OnUserLoaded(userResponse);
                },throwable -> {
                    // possible error handling
                    listener.OnUserLoadFailed(true);
                });

    }


    // get request method for posts
    public void getRequest(int postId, String username, OnPostsLoadedListener listener) {

        listener.OnPostsLoading(true);
        Single<List<Posts>> observable = apiSettings().getPosts(postId, username);

        disposable = observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(posts -> {
                    listener.OnPostsLoaded(posts);
                }, throwable -> {
                    Log.d("t", throwable.getMessage());
                    listener.OnPostsLoadFailed(true);
                });
    }


    // post request method for posts
    public void postRequest(Posts posts, OnPostLoadedListener listener) {

        listener.OnPostLoading(true);
        Single<Posts> observable = apiSettings().sendPosts(posts);

        disposable = observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(postResponse -> {
                    listener.OnPostLoaded(postResponse);
                },throwable -> {
                    listener.OnPostLoadFailed(true);
                });
    }

    public void putRequest(Posts post,OnPostLoadedListener listener) {
        listener.OnPostLoading(true);
        // sending the user object to putRegularUsers and it sending a put request
        Single<Posts> observable = apiSettings().putPost(post.getPostId(),post);

        disposable = observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(postResponse -> {
                    // sending to response to listener for anlyzing
                    listener.OnPostLoaded(postResponse);
                },throwable -> {
                    // possible error handling
                    listener.OnPostLoadFailed(true);
                });
    }


    // delete request method for posts
    public void deleteRequest(int postId, OnPostLoadedListener listener) {

        listener.OnPostLoading(true);
        Single<Posts> observable = apiSettings().deletePosts(postId);

        disposable = observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(postResponse -> {
                    listener.OnPostLoaded(postResponse);
                },throwable -> {
                    Log.d("t", throwable.getMessage());
                    listener.OnPostLoadFailed(true);
                });
    }


    public void getRequestByFilter(int offset, int pageSize, String letter, OnUsersLoadedListener listener) {

        listener.OnUsersLoading(true);
        // sending the user infos to getRegularUsers and it sending a get request to api
        Single<List<regularUser>> observable = apiSettings().getRegularUsersByFilter(offset, pageSize, letter);

        // with observable proccessing this request in background(io)
        disposable = observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        users -> {
                            // sending the reponse to listener for anlyzing
                            listener.OnUsersLoaded(users);
                        },
                        throwable -> {
                            Log.d("t", throwable.getMessage());
                            // possible error handling
                            listener.OnUsersLoadFailed(true);
                        }

                );

    }

    public void getRequest(int postId, OnCommentsLoadedListener listener) {

        listener.OnCommentsLoading(true);
        // sending the user infos to getRegularUsers and it sending a get request to api
        Single<List<Comment>> observable = apiSettings().getComments(postId);

        // with observable proccessing this request in background(io)
        disposable = observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        comments -> {
                            // sending the reponse to listener for anlyzing
                            listener.OnCommentsLoaded(comments);
                        },
                        throwable -> {
                            Log.d("t", throwable.getMessage());
                            // possible error handling
                            listener.OnCommentsLoadFailed(true);
                        }

                );

    }

    public void postRequest(Comment comment, OnCommentLoadedListener listener) {

        listener.OnCommentLoading(true);
        // sending the user object to postRegularUsers and it sending a post request
        Single<Comment> observable = apiSettings().postComment(comment);

        // with observable proccessing this request in background(io)
        disposable = observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(commentResponse -> {
                            // sending the response to listener for anlyzing
                            listener.OnCommentLoaded(commentResponse);
                        },
                        throwable -> {
                            // possible error handling
                            listener.OnCommentLoadFailed(true);
                        });

    }

    public void deleteRequest(int comment_id, OnCommentLoadedListener listener) {

        listener.OnCommentLoading(true);
        Single<Comment> observable = apiSettings().deleteComment(comment_id);

        disposable = observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(commentResponse -> {
                    listener.OnCommentLoaded(commentResponse);
                },throwable -> {
                    Log.d("t", throwable.getMessage());
                    listener.OnCommentLoadFailed(true);
                });
    }

    // setting the request settings and returning a JsonPlaceHolderApi object
    public JsonPlaceHolderApi apiSettings() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit.create(JsonPlaceHolderApi.class);

    }



    public Disposable getDisposable() {
        return disposable;
    }

    public void setDisposable(Disposable disposable) {
        this.disposable = disposable;
    }
}
