package com.example.studooov2.UserSignUpLogin.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.studooov2.UserSignUpLogin.InternetOperations.Data.ApiRequest;
import com.example.studooov2.UserSignUpLogin.InternetOperations.Data.OnPostLoadedListener;
import com.example.studooov2.UserSignUpLogin.InternetOperations.Data.OnPostsLoadedListener;
import com.example.studooov2.UserSignUpLogin.InternetOperations.Data.OnSuccessListener;
import com.example.studooov2.UserSignUpLogin.InternetOperations.Data.OnUserLoadedListener;
import com.example.studooov2.UserSignUpLogin.InternetOperations.Data.OnUsersLoadedListener;

import java.util.List;

public abstract class User implements Parcelable {

    private int user_id;
    private String name;
    private String username;
    private String eposta;
    private String pass;
    private String profile_photo;
    private String bio;
    private String uni = "Ege Üniversitesi";

    private ApiRequest request = new ApiRequest();

    public User(){ // No parameter constructer
    }

    public User(int user_id, String name, String username, String eposta, String pass, String profile_photo, String bio, String uni) {
        this.user_id = user_id;
        this.name = name;
        this.username = username;
        this.eposta = eposta;
        this.pass = pass;
        this.profile_photo = profile_photo;
        this.bio = bio;
        this.uni = uni;
    }

    public User(int user_id, String name, String username, String eposta, String pass) { // request constructer
        this.user_id = user_id;
        this.name = name;
        this.username = username;
        this.eposta = eposta;
        this.pass = pass;
    }

    // contructer to sign up not used user_id cause its auto increment in database
    public User(String name, String username, String eposta, String pass) { // sign_up constructer
        this.name = name;
        this.username = username;
        this.eposta = eposta;
        this.pass = pass;
    }

    // log_in constructer getting usernameEposta parameter cause user is able to user both username
    // and eposta informations
    public User(String usernameEposta, String pass) {
        this.username = usernameEposta;
        this.pass = pass;
    }

    public User(String pass) {
        this.pass = pass;
    }

    // sending the users to another activity with this constructer
    public User(Parcel in) {
        this.user_id = in.readInt();
        this.name = in.readString();
        this.username = in.readString();
        this.eposta = in.readString();
        this.pass = in.readString();
        this.profile_photo = in.readString();
        this.bio = in.readString();
        this.uni = in.readString();
    }

    public abstract void signUp(regularUser user, OnSuccessListener listener);

    public void login(OnSuccessListener listener) {

        // sending a get request
        request.getRequest(getUsername(), new OnUsersLoadedListener() {
            @Override
            public void OnUsersLoading(Boolean loadingSuccess) {
                listener.onSuccessLoading(true);
            }

            @Override
            public void OnUsersLoaded(List<regularUser> users) {

                if (users.isEmpty()) {
                    // if list is empty success is false
                    listener.onSuccessLoaded(false);
                }
                else {
                    // if it is not looking for passwords are equals. If equals success is true
                    // if it not success is false
                    listener.onSuccessLoaded(users.get(0).getPass().equals(getPass()));
                }
            }

            @Override
            public void OnUsersLoadFailed(Boolean errorSuccess) {
                listener.onSuccessFailed(errorSuccess);
            }
        });

    }

    public void changePass(regularUser user, OnSuccessListener listener) {

        // sending a put request
        request.putRequest(user.getUsername(), user, new OnUserLoadedListener() {
            @Override
            public void OnUserLoading(Boolean loadingSuccess) {
                listener.onSuccessLoading(true);
            }

            @Override
            public void OnUserLoaded(regularUser userResponse) {
                // if response user's id is not 0 success it true if it is not success false
                listener.onSuccessLoaded(userResponse.getUser_id() != 0);
            }

            @Override
            public void OnUserLoadFailed(Boolean errorSuccess) {
                listener.onSuccessFailed(true);
            }
        });

    }

    public boolean verification(String sendedCode, String receivedCode) {

        // if sendedCode equals to receivedCode returning true if its not returning false
        return sendedCode.equals(receivedCode);

    }

    public void isUsernameValid(String username, OnSuccessListener listener) {

        // sending a get request
        request.getRequest(username, new OnUsersLoadedListener() {
            @Override
            public void OnUsersLoading(Boolean loadingSuccess) {
                listener.onSuccessLoading(true);
            }

            @Override
            public void OnUsersLoaded(List<regularUser> users) {
                // if list is empty it means that there is not other user with this username
                listener.onSuccessLoaded(users.isEmpty());
            }

            @Override
            public void OnUsersLoadFailed(Boolean errorSuccess) {
                listener.onSuccessFailed(true);
            }
        });

    }

    public void isEpostaValid(String eposta, OnSuccessListener listener) {

        // sending a get request
        request.getRequest(eposta, new OnUsersLoadedListener() {
            @Override
            public void OnUsersLoading(Boolean loadingSuccess) {
                listener.onSuccessLoading(true);
            }

            @Override
            public void OnUsersLoaded(List<regularUser> users) {
                // if list is empty it means that there is not other user with this eposta
                listener.onSuccessLoaded(users.isEmpty());
            }

            @Override
            public void OnUsersLoadFailed(Boolean errorSuccess) {
                listener.onSuccessFailed(true);
            }
        });

    }

    public void sendPost(Posts posts, OnSuccessListener listener) {

        request.postRequest(posts, new OnPostLoadedListener() {
            @Override
            public void OnPostLoading(Boolean loadingSuccess) {
                listener.onSuccessLoading(loadingSuccess);
            }

            @Override
            public void OnPostLoaded(Posts posts) {
                listener.onSuccessLoaded(posts.getPostId() != 0);
            }

            @Override
            public void OnPostLoadFailed(Boolean errorSuccess) {
                listener.onSuccessFailed(errorSuccess);
            }
        });
    }

    public void deletePost(int postId, OnSuccessListener listener) {

        request.deleteRequest(postId, new OnPostLoadedListener() {
            @Override
            public void OnPostLoading(Boolean loadingSuccess) {
                listener.onSuccessLoading(loadingSuccess);
            }

            @Override
            public void OnPostLoaded(Posts posts) {
                listener.onSuccessLoaded(posts == null);
            }

            @Override
            public void OnPostLoadFailed(Boolean errorSuccess) {
                listener.onSuccessFailed(errorSuccess);
            }
        });
    }

    public abstract void changeProfileInfo(String username, regularUser user, OnSuccessListener listener);




    public int getUser_id() {
        return user_id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getEposta() {
        return eposta;
    }

    public String getPass() {
        return pass;
    }

    public ApiRequest getRequest() {
        return request;
    }

    public String getProfile_photo() {
        return profile_photo;
    }

    public String getBio() {
        return bio;
    }

    public String getUni() {
        return uni;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEposta(String eposta) {
        this.eposta = eposta;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setRequest(ApiRequest request) {
        this.request = request;
    }

    public void setProfile_photo(String profile_photo) {
        this.profile_photo = profile_photo;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setUni(String uni) {
        this.uni = uni;
    }
}
