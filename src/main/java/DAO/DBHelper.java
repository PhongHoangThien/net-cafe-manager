package DAO;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBHelper {
    private static DBHelper instance;
    private Connection connection = null;

    // Thông tin kết nối đến MySQL
    private static final String SERVER = "localhost:3306";
    private static final String DATABASE_NAME = "internet";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "admin";

    private static DBHelper getInstance() throws SQLException {
        if (instance == null || instance.connection == null || instance.connection.isClosed()) {
            instance = new DBHelper();
        }
        return instance;
    }

    public static Connection getConnection() throws SQLException {
        return getInstance().connection;
    }


    // Constructor tạo kết nối
    public DBHelper() throws SQLException {
        try {
            // Đăng ký MySQL Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = String.format(
                    "jdbc:mysql://%s/%s?useUnicode=true&characterEncoding=UTF-8&characterSetResults=UTF-8&connectionCollation=utf8mb4_unicode_ci",
                    SERVER, DATABASE_NAME
            );
            connection = DriverManager.getConnection(url, USER_NAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String getConnectionString() {
        return String.format(
                "jdbc:mysql://%s/%s?useUnicode=true&characterEncoding=UTF-8&characterSetResults=UTF-8&connectionCollation=utf8mb4_unicode_ci",
                SERVER, DATABASE_NAME
        );
    }

    // Chuyển ResultSet thành List đối tượng kiểu T
    public static <T> List<T> toList(ResultSet resultSet, Class<T> clazz) throws SQLException {
        Field[] fields = clazz.getDeclaredFields();
        List<T> list = new ArrayList<>();
        while (resultSet.next()) {
            try {
                T t = clazz.getConstructor().newInstance();
                for (Field field : fields) {
                    if (field.getName().equals("serialVersionUID")) continue;

                    try {
                        Object value = resultSet.getObject(field.getName());
                        field.setAccessible(true);

                        if (value == null) continue;

                        if (field.getType().isEnum()) {
                            int ordinal = (int) value;
                            value = field.getType().getEnumConstants()[ordinal];
                        }

                        field.set(t, value);
                    } catch (Exception ignored) {
                        if (field.getType().isEnum()) {
                            ignored.printStackTrace();
                        }
                    }
                }
                list.add(t);
            } catch (Exception exception) {
                exception.printStackTrace();
                System.exit(0);
            }
        }
        resultSet.close();
        return list;

//    private static final String SERVER = "localhost:3306";
//    private static final String DATABASE_NAME = "Internet";
//    private static final String USER_NAME = "root";
//
//    private static final String PASSWORD = "admin";
//
//    private  Connection connection = null;
//
//
//    public DBHelper() throws SQLException  {
//        String url = String
//                .format("jdbc:mysql://%s/%s?useSSL=false&serverTimezone=UTC", SERVER, DATABASE_NAME);
//        connection = DriverManager.getConnection(url, USER_NAME, PASSWORD);
//    }
//    public static String getConnectionString(){
//        return  String
//                .format("jdbc:mysql://%s/%s?useSSL=false&serverTimezone=UTC", SERVER, DATABASE_NAME);
//    }
//
//    public static <T>List<T> toList(ResultSet resultSet, Class<T> clazz) throws SQLException {//Class<T> clazz là truyền vào một class cụ thể, trong đó clazz là một lớp cụ thể
//        return null;
//
//    }
    }
}
