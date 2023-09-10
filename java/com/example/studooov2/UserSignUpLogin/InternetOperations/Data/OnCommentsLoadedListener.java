package com.example.studooov2.UserSignUpLogin.InternetOperations.Data;

import com.example.studooov2.UserSignUpLogin.Models.Comment;

import java.util.List;

public interface OnCommentsLoadedListener {

    void OnCommentsLoading(Boolean loadingSuccess);
    void OnCommentsLoaded(List<Comment> comments);
    void OnCommentsLoadFailed(Boolean errorSuccess);
}
