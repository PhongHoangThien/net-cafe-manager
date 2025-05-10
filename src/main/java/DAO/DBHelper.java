package DAO;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBHelper {
    private static DBHelper instance;

    private static DBHelper getInstance() throws SQLException {
        instance = instance == null|| instance.connection.isClosed() ? new DBHelper() : instance;
        if (instance.connection.isClosed()){
            instance = new DBHelper();
        }
        return instance;
    }

    public static Connection getConnection() throws SQLException {
        return getInstance().connection;
    }

    private static final String SERVER = "localhost:3306";
    private static final String DATABASE_NAME = "Internet";
    private static final String USER_NAME = "root";

    private static final String PASSWORD = "admin";

    private  Connection connection = null;


    public DBHelper() throws SQLException  {
        String url = String
                .format("jdbc:mysql://%s/%s?useSSL=false&serverTimezone=UTC", SERVER, DATABASE_NAME);
        connection = DriverManager.getConnection(url, USER_NAME, PASSWORD);
    }
    public static String getConnectionString(){
        return  String
                .format("jdbc:mysql://%s/%s?useSSL=false&serverTimezone=UTC", SERVER, DATABASE_NAME);
    }

    public static <T>List<T> toList(ResultSet resultSet, Class<T> clazz) throws SQLException {//Class<T> clazz là truyền vào một class cụ thể, trong đó clazz là một lớp cụ thể
        return null;
    }

}
