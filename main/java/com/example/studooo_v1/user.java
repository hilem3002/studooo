package com.example.studooo_v1;

import android.os.Parcelable;

import java.sql.SQLException;
import java.util.ArrayList;



public abstract class user implements Parcelable {
    private String name;
    private String username;
    private String eposta;
    private String password;
    private dataOperator operator;

    public user() throws SQLException, ClassNotFoundException {
        this.name = null;
        this.username = null;
        this.eposta = null;
        this.password = null;
        setOperator(new dataOperator());
    }

    public user(String name, String username, String eposta, String password) throws SQLException, ClassNotFoundException { // sign_up constructer
        this.name = name;
        this.username = username;
        this.eposta = eposta;
        this.password = password;
        setOperator(new dataOperator());
    }

    public user(String username_eposta, String password) throws SQLException, ClassNotFoundException { // log_in constructer
        this.username = username_eposta;
        this.password = password;
        this.eposta = null;
        this.name = null;
        setOperator(new dataOperator());
    }

    public abstract void sign_up() throws SQLException; // signing up to system

    public boolean log_in() throws SQLException { //loging in to system

        ArrayList<ArrayList<String>> userData = new ArrayList<ArrayList<String>>(1);
        // getting user data from database
        userData = getOperator().selectUser("username='"+getUsername()+"' OR eposta='"+getUsername()+"'");

        if (!userData.isEmpty()) {
            ArrayList<String> dataList = userData.get(0); // getting the main data list
            String dataUsername = dataList.get(2);
            String dataEposta = dataList.get(3);
            String dataPassword = dataList.get(4);

            // looking for info are equals or not
            if (dataUsername.equals(getUsername()) && dataPassword.equals(getPassword())) {
                return true;
            }
            else if (dataEposta.equals(getUsername()) && dataPassword.equals(getPassword())) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }

    }

    public boolean changePass(String userInfo, String newPass, String newPassAgain) throws SQLException { // updating the password

        if (newPass.equals(newPassAgain)) {
            getOperator().update("pass", newPass, "eposta='"+userInfo+"' OR username='"+userInfo+"'");
            return true;
        }
        else {
            return false;
        }
    }

    public boolean verification(String sendedCode, String receivedCode) { // looking for if sended code equals to recieved code or not

        if (sendedCode.equals(receivedCode)) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isUsernameValid(String username) throws SQLException {

        // looking for is there a user which has this infos or not
        ArrayList<ArrayList<String>> userData = new ArrayList<ArrayList<String>>(1);
        userData = getOperator().selectUser("username='"+username+"'");

        if (userData.isEmpty()) {
            return true;
        }
        else {
            return false;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEposta() {
        return eposta;
    }

    public void setEposta(String eposta) {
        this.eposta = eposta;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public dataOperator getOperator() {
        return operator;
    }

    public void setOperator(dataOperator operator) {
        this.operator = operator;
    }

}
