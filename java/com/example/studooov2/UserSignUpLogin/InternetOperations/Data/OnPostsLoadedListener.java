package com.example.studooov2.UserSignUpLogin.InternetOperations.Data;

import com.example.studooov2.UserSignUpLogin.Models.Posts;
import com.example.studooov2.UserSignUpLogin.Models.regularUser;

import java.util.List;

public interface OnPostsLoadedListener {

    // getting the response data from api for list of posts
    void OnPostsLoading(Boolean loadingSuccess);
    void OnPostsLoaded(List<Posts> posts);
    void OnPostsLoadFailed(Boolean errorSuccess);
}
