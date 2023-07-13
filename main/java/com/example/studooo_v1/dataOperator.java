package com.example.studooo_v1;

import java.sql.*;
import java.util.ArrayList;


public class dataOperator {

    private Connection con; // gonna get return connection object
    private Connector connector; // inner class object
    private Statement statement;
    private int userNumber;

    public dataOperator() throws SQLException, ClassNotFoundException { // connecting to database and getting the connection object
        setConnector(new Connector());
        setCon(getConnector().connection());
        setStatement(getCon().createStatement());
        setUserNumber(numberofUsers());
    }

    public int numberofUsers() throws SQLException { // getting the number of users in the database

        ResultSet resultSet = getStatement().executeQuery("SELECT user_id FROM user_info");
        int count = 0;
        while (resultSet.next()){
            count++;
        }
        return count;
        // try it with COUNT
    }

    public boolean disconnect() throws SQLException { // disconnect from database
        statement.close();
        con.close();
        return true;
    }

    public void insert(String column, String value) throws SQLException { // Inserting data to database
        getStatement().execute("INSERT INTO user_info("+column+") VALUES('"+value+"')");
    }

    public void insert(String column1, String value1, String column2, String value2) throws SQLException {
        getStatement().execute("INSERT INTO user_info("+column1+","+column2+") VALUES('"+value1+"','"+value2+"')");
    }

    public void insert(String column1, String value1, String column2, String value2, String column3, String value3) throws SQLException {
        getStatement().execute("INSERT INTO user_info("+column1+","+column2+","+column3+") VALUES('"+value1+"','"+value2+"','"+value3+"')");
    }

    public void insert(String column1, String value1, String column2, String value2, String column3, String value3, String column4, String value4) throws SQLException {
        getStatement().execute("INSERT INTO user_info("+column1+","+column2+","+column3+","+column4+") VALUES('"+value1+"','"+value2+"','"+value3+"','"+value4+"')");
    }

    public void delete(String condition) throws SQLException { // Deleting data from database
        getStatement().execute("DELETE FROM user_info WHERE "+condition);
    }

    public void update(String column, String newData, String condition) throws SQLException { // Changing data in database
        getStatement().execute("UPDATE user_info SET "+column+" = '"+newData+"' WHERE "+condition);
    }

    public ArrayList<ArrayList<String>> selectUser(String condition) throws SQLException { // Getting data from database with conditions

        // example parameter : user_id=1, name='melih'
        ArrayList<ArrayList<String>> userDataAll = new ArrayList<ArrayList<String>>();

        ResultSet resultSet = getStatement().executeQuery("SELECT * FROM user_info WHERE "+condition);

        while (resultSet.next()){
            ArrayList<String> userData = new ArrayList<String>(5);
            String data1 = resultSet.getString("user_id");
            String data2 = resultSet.getString("name");
            String data3 = resultSet.getString("username");
            String data4 = resultSet.getString("eposta");
            String data5 = resultSet.getString("pass");
            userData.add(data1);
            userData.add(data2);
            userData.add(data3);
            userData.add(data4);
            userData.add(data5);
            userDataAll.add(userData);
        }
        return userDataAll;

    }

    public ArrayList<String> selectAll(String column) throws SQLException { // Getting data from database

        ArrayList<String> dataList = new ArrayList<String>(getUserNumber());

        ResultSet resultSet = getStatement().executeQuery("SELECT "+column+" FROM user_info");

        while (resultSet.next()){
            String data = resultSet.getString(column);
            dataList.add(data);
        }
        return dataList;

    }

    public ArrayList<ArrayList<String>> selectAll(String column1, String column2) throws SQLException { // Getting data from database (overload)

        ArrayList<ArrayList<String>> alldataList = new ArrayList<ArrayList<String>>(2); // main list
        ArrayList<String> dataList1 = new ArrayList<String>(getUserNumber()); // column1 list
        ArrayList<String> dataList2 = new ArrayList<String>(getUserNumber()); // column2 list

        ResultSet resultSet = getStatement().executeQuery("SELECT "+column1+","+column2+" FROM user_info");

        while (resultSet.next()){
            String data1 = resultSet.getString(column1);
            String data2 = resultSet.getString(column2);
            dataList1.add(data1);
            dataList2.add(data2);
        }
        alldataList.add(dataList1);
        alldataList.add(dataList2);

        return alldataList;

    }

    public ArrayList<ArrayList<String>> selectAll(String column1, String column2, String column3) throws SQLException { // Getting data from database (overload)

        ArrayList<ArrayList<String>> alldataList = new ArrayList<ArrayList<String>>(3); // main list
        ArrayList<String> dataList1 = new ArrayList<String>(getUserNumber()); // column1 list
        ArrayList<String> dataList2 = new ArrayList<String>(getUserNumber()); // column2 list
        ArrayList<String> dataList3 = new ArrayList<String>(getUserNumber()); // column3 list

        ResultSet resultSet = getStatement().executeQuery("SELECT "+column1+","+column2+","+column3+" FROM user_info");

        while (resultSet.next()){
            String data1 = resultSet.getString(column1);
            String data2 = resultSet.getString(column2);
            String data3 = resultSet.getString(column3);
            dataList1.add(data1);
            dataList2.add(data2);
            dataList3.add(data3);
        }
        alldataList.add(dataList1);
        alldataList.add(dataList2);
        alldataList.add(dataList3);

        return alldataList;

    }

    public ArrayList<ArrayList<String>> selectAll(String column1, String column2, String column3, String column4) throws SQLException { // Getting data from database (overload)

        ArrayList<ArrayList<String>> alldataList = new ArrayList<ArrayList<String>>(4); // main list
        ArrayList<String> dataList1 = new ArrayList<String>(getUserNumber()); // column1 list
        ArrayList<String> dataList2 = new ArrayList<String>(getUserNumber()); // column2 list
        ArrayList<String> dataList3 = new ArrayList<String>(getUserNumber()); // column3 list
        ArrayList<String> dataList4 = new ArrayList<String>(getUserNumber()); // column4 list

        ResultSet resultSet = getStatement().executeQuery("SELECT "+column1+","+column2+","+column3+","+column4+" FROM user_info");

        while (resultSet.next()){
            String data1 = resultSet.getString(column1);
            String data2 = resultSet.getString(column2);
            String data3 = resultSet.getString(column3);
            String data4 = resultSet.getString(column4);
            dataList1.add(data1);
            dataList2.add(data2);
            dataList3.add(data3);
            dataList4.add(data4);
        }
        alldataList.add(dataList1);
        alldataList.add(dataList2);
        alldataList.add(dataList3);
        alldataList.add(dataList4);

        return alldataList;

    }

    public ArrayList<ArrayList<String>> selectAll() throws SQLException { // Getting data from database (overload)

        ArrayList<ArrayList<String>> alldataList = new ArrayList<ArrayList<String>>(5); // main list
        ArrayList<String> dataList1 = new ArrayList<String>(getUserNumber()); // column1 list
        ArrayList<String> dataList2 = new ArrayList<String>(getUserNumber()); // column2 list
        ArrayList<String> dataList3 = new ArrayList<String>(getUserNumber()); // column3 list
        ArrayList<String> dataList4 = new ArrayList<String>(getUserNumber()); // column4 list
        ArrayList<String> dataList5 = new ArrayList<String>(getUserNumber()); // column5 list

        ResultSet resultSet = getStatement().executeQuery("SELECT * FROM user_info");

        while (resultSet.next()){
            String data1 = resultSet.getString("user_id");
            String data2 = resultSet.getString("name");
            String data3 = resultSet.getString("username");
            String data4 = resultSet.getString("eposta");
            String data5 = resultSet.getString("pass");
            dataList1.add(data1);
            dataList2.add(data2);
            dataList3.add(data3);
            dataList4.add(data4);
            dataList5.add(data5);
        }
        alldataList.add(dataList1);
        alldataList.add(dataList2);
        alldataList.add(dataList3);
        alldataList.add(dataList4);
        alldataList.add(dataList5);

        return alldataList;

    }



    public Connector getConnector() {
        return connector;
    }

    public void setConnector(Connector connector) {
        this.connector = connector;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public int getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(int userNumber) {
        this.userNumber = userNumber;
    }

    private class Connector{
        final private String port = "3306";
        final private String ip = "34.29.76.183";
        final private String password = "(TtM).2300=asB+";
        final private String database = "studooo_demo";
        final private String uname = "root";

        // connecting to database end returning a Connection class object
        public Connection connection() throws SQLException, ClassNotFoundException {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://"+getIp()+":"+getPort()+"/"+getDatabase(), getUname(), getPassword());
            return connection;
        }

        public String getPort() {
            return port;
        }

        public String getPassword() {
            return password;
        }

        public String getDatabase() {
            return database;
        }

        public String getIp() {
            return ip;
        }

        public String getUname() {
            return uname;
        }
    }
}
