package com.example.studooo_v1;

import android.annotation.SuppressLint;
import android.os.Parcel;

import androidx.annotation.NonNull;

import java.sql.SQLException;
import java.util.ArrayList;

@SuppressLint("ParcelCreator")
public class regularUser extends user {

    public regularUser() throws SQLException, ClassNotFoundException {
        super();
    }

    public regularUser(String name, String username, String eposta, String password) throws SQLException, ClassNotFoundException {
        super(name, username, eposta, password);
    }

    public regularUser(String username_eposta, String password) throws SQLException, ClassNotFoundException {
        super(username_eposta, password);
    }

    @Override
    public void sign_up() throws SQLException { // signing up to system
        getOperator().insert("name", getName(), "username", getUsername(), "eposta", getEposta(), "pass", getPassword());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {

    }
}
