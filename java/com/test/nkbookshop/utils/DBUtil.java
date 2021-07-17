package com.test.nkbookshop.utils;

import java.io.IOException;
import java.util.Properties;


public class DBUtil {
    private static Properties prop =new Properties();
    static{

        try {

            prop.load(DBUtil.class.getClassLoader().getResourceAsStream("dbconfig.properties"));

        } catch (IOException e) {

            e.printStackTrace();

        }

    }


    public static String driver=prop.getProperty("jdbc.driver");

    public static String url=prop.getProperty("jdbc.url");

    public static String user=prop.getProperty("jdbc.user");

    public static String psw=prop.getProperty("jdbc.password");



}
