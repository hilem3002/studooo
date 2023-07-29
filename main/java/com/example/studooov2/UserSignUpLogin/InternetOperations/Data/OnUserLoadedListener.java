package com.example.studooov2.UserSignUpLogin.InternetOperations.Data;

import com.example.studooov2.UserSignUpLogin.Models.regularUser;

public interface OnUserLoadedListener {

    // getting the response data from api for a signle users
    void OnUserLoading(Boolean loadingSuccess);
    void OnUserLoaded(regularUser userResponse);
    void OnUserLoadFailed(Boolean errorSuccess);

}
