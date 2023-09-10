package com.example.studooov2.UserSignUpLogin.InternetOperations.Data;

public interface OnSuccessListener {

    // getting the success infos while getting the data
    void onSuccessLoading(Boolean loadingSuccess);
    void onSuccessLoaded(Boolean success);
    void onSuccessFailed(Boolean errorSuccess);

}
