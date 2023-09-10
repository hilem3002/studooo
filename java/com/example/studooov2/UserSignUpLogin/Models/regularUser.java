package com.example.studooov2.UserSignUpLogin.Models;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.example.studooov2.UserSignUpLogin.InternetOperations.Data.OnSuccessListener;
import com.example.studooov2.UserSignUpLogin.InternetOperations.Data.OnUserLoadedListener;

@SuppressLint("ParcelCreator")
public class regularUser extends User {

    public regularUser() {
    }

    public regularUser(int user_id, String name, String username, String eposta, String pass) {
        super(user_id, name, username, eposta, pass);
    }

    public regularUser(String name, String username, String eposta, String pass) {
        super(name, username, eposta, pass);
    }

    public regularUser(String usernameEposta, String pass) {
        super(usernameEposta, pass);
    }

    public regularUser(Parcel in) {
        super(in);
    }

    @Override
    public void signUp(regularUser user, OnSuccessListener listener) {

        // sending a post request
        getRequest().postRequest(user, new OnUserLoadedListener() {
            @Override
            public void OnUserLoading(Boolean loadingSuccess) {
                listener.onSuccessLoading(true);
            }

            @Override
            public void OnUserLoaded(regularUser userResponse) {
                // if user_id is not 0 then user sign up successfully
                listener.onSuccessLoaded(userResponse.getUser_id() != 0);
            }

            @Override
            public void OnUserLoadFailed(Boolean errorSuccess) {
                listener.onSuccessFailed(true);
            }
        });
    }

    public static final Parcelable.Creator<regularUser> CREATOR = new Parcelable.Creator<regularUser>() {
        @Override
        public regularUser createFromParcel(Parcel source) {
            return new regularUser(source);
        }

        @Override
        public regularUser[] newArray(int size) {
            return new regularUser[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(getUser_id());
        parcel.writeString(getName());
        parcel.writeString(getUsername());
        parcel.writeString(getEposta());
        parcel.writeString(getPass());
        parcel.writeString(getProfile_photo());
        parcel.writeString(getBio());
        parcel.writeString(getUni());
    }

    @Override
    public void changeProfileInfo(String username, regularUser user, OnSuccessListener listener) {
        getRequest().putRequest(username, user, new OnUserLoadedListener() {
            @Override
            public void OnUserLoading(Boolean loadingSuccess) {
                listener.onSuccessLoading(true);
            }

            @Override
            public void OnUserLoaded(regularUser userResponse) {
                listener.onSuccessLoaded(userResponse.getUser_id() != 0);
            }

            @Override
            public void OnUserLoadFailed(Boolean errorSuccess) {
                listener.onSuccessFailed(true);
            }
        });
    }
}
