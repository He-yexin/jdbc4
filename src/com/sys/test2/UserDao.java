package com.sys.test2;

import com.sys.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    public void addUser(User user) {
        Connection conn = DBUtil.getConn();
        PreparedStatement ps = null;
        String sql = "insert into sys_user(name,age,sex,create_time) values( ?,?,?,?)";
        try {
            ps.setObject(1, user.getName());
            ps.setObject(2, user.getAge());
            ps.setObject(3, user.getSex());
            ps.setObject(4, user.getCreateTime());
            ps.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, null);
        }
    }

    public static void main(String[] args) {
        PageBean pageBean=new PageBean();
        pageBean.setPage(3);
        listUser2(pageBean).stream().forEach(System.out::println);
    }

    public static List<User> listUser2(PageBean page) {
        List<User> list = new ArrayList<>();
        Connection conn = DBUtil.getConn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select * from sys_user where 1=1 limit ?,?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1, (page.getPage() - 1) * page.getPageSize());
            ps.setObject(2, page.getPageSize());
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
                list.add(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return list;
    }

    //pageSize 每页显示的记录数
    //page 当前页
    public static List<User> listUser(Integer pageSize, Integer page) {
        List<User> list = new ArrayList<>();
        Connection conn = DBUtil.getConn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select * from sys_user where 1=1 limit ?,?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1, (page - 1) * pageSize);
            ps.setObject(2, pageSize);
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
                list.add(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return list;
    }
}
