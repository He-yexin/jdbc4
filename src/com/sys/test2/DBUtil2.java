package com.sys.test2;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil2 {

    private static String driver = "";
    private static String url = "";
    private static String name = "";
    private static String password = "";

    //静态代码块
    static {
        Properties properties = new Properties();
        try {
            properties.load(DBUtil.class.getResourceAsStream("/db.properties"));
            driver = properties.getProperty("driver");
            url = properties.getProperty("url");
            name = properties.getProperty("name");
            password = properties.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        System.out.println(getConn());
    }

    /**
     * 获取连接
     *
     * @return
     */
    public static Connection getConn() {
        new DBUtil2();
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, name, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
