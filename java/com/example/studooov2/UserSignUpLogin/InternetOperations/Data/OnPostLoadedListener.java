package com.example.studooov2.UserSignUpLogin.InternetOperations.Data;

import com.example.studooov2.UserSignUpLogin.Models.Posts;

import java.util.List;

public interface OnPostLoadedListener {

    // getting the response data from api for list of post
    void OnPostLoading(Boolean loadingSuccess);
    void OnPostLoaded(Posts posts);

    void OnPostLoadFailed(Boolean errorSuccess);
}
