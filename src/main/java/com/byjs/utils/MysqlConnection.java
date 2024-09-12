package com.byjs.utils;

import java.sql.*;

public class MysqlConnection {
    private static final String JDBC_Driver = "com.mysql.cj.jdbc.Driver";//jdbc驱动名称
    private static final String DB_Url = "jdbc:mysql://120.55.90.66:3306/byjs?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";//数据库url
    private static final String DB_User = "root";//数据库的用户名
    private static final String DB_Password = "f0778d946755a09f";//数据库的密码

    public static Connection CreateConnection(){
        Connection connection = null;
        try{
            //1- 注册JDBC驱动
            Class.forName(JDBC_Driver);
            //2- 连接数据库
            System.out.println("开始连接数据库...");
            connection = DriverManager.getConnection(DB_Url,DB_User,DB_Password);
            return connection;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}

