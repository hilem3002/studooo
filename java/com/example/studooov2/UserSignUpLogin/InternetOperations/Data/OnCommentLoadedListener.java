package com.example.studooov2.UserSignUpLogin.InternetOperations.Data;

import com.example.studooov2.UserSignUpLogin.Models.Comment;

import java.util.List;

public interface OnCommentLoadedListener {

    void OnCommentLoading(Boolean loadingSuccess);
    void OnCommentLoaded(Comment comment);
    void OnCommentLoadFailed(Boolean errorSuccess);

}
