package com.sys.test1.dao;


import com.sys.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    //ctrl+shift+u
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/test2";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) {
//        List<User> result = findAll("武");
//        result.stream().forEach(n -> {
//            System.out.println(n);
//        });
//        User user = new User();
//        user.setName("林冲");
//        user.setAge(50);
//        user.setSex("1");
//        //java.util包，不是java.sql
//        Date date = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String dateStr = sdf.format(date);
//        //日期类型的字符串 "2019-11-4 11:22:30"
//        user.setCreateTime(dateStr);
//
//        addUser(user);

        deleteById(Long.valueOf(12));
    }

    public static void deleteById(Long id) {

        String sql = "delete from sys_user where id=?";
        Connection conn = null;
        PreparedStatement ps = null;
        int count;
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            ps = conn.prepareStatement(sql);
            ps.setObject(1, id);
            count = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


    public static void addUser(User user) {

        String sql = "insert into sys_user(name,age,sex,create_time) values( ?,?,?,?)";
        Connection conn = null;
        PreparedStatement ps = null;
        int count;
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            ps = conn.prepareStatement(sql);
            ps.setObject(1, user.getName());
            ps.setObject(2, user.getAge());
            ps.setObject(3, user.getSex());
            ps.setObject(4, user.getCreateTime());
            count = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * @return findAll
     * listUser
     */
    public static List<User> findAll(String conName) {
        List<User> result = new ArrayList<>();
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/test2";
        String username = "root";
        String password = "root";
        String sql = "select * from sys_user where name like ?";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //注册驱动
            Class.forName(driver);
            //获取连接  java连接mysql
            conn = DriverManager.getConnection(url, username, password);
            //实例化PreparedStatement
            ps = conn.prepareStatement(sql);
            // 1代表第一个? ,下标从1开始，不是从0开始
            ps.setObject(1, "%" + conName + "%");

            //结果集
            rs = ps.executeQuery();
            User user = null;
            while (rs.next()) {
                //把结果集中的数据放入list集合
                user = new User();
                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                Integer age = rs.getInt("age");
                String sex = rs.getString("sex");
                String createTime = rs.getString("create_time");
                String delFlag = rs.getString("del_flag");
                user.setId(Long.valueOf(id));
                user.setName(name);
                user.setAge(age);
                user.setSex(sex);
                user.setCreateTime(createTime);
                user.setDelFlag(delFlag);
                result.add(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭对象 先开后关，后开先关
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }


}
