package com.example.studooov2.UserSignUpLogin.InternetOperations.Data;

import com.example.studooov2.UserSignUpLogin.Models.regularUser;

import java.util.List;

public interface OnUsersLoadedListener {

    // getting the response data from api for list of users
    void OnUsersLoading(Boolean loadingSuccess);
    void OnUsersLoaded(List<regularUser> users);
    void OnUsersLoadFailed(Boolean errorSuccess);

}
