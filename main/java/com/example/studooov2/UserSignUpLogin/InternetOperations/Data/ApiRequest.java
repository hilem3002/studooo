package com.example.studooov2.UserSignUpLogin.InternetOperations.Data;

import android.util.Log;

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
    public void putRequest(regularUser user, OnUserLoadedListener listener) {

        listener.OnUserLoading(true);
        // sending the user object to putRegularUsers and it sending a put request
        Single<regularUser> observable = apiSettings().putRegularUsers(user.getUsername(), user);

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


    // setting the request settings and returning a JsonPlaceHolderApi object
    private JsonPlaceHolderApi apiSettings() {

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
