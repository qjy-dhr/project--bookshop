package com.test.nkbookshop.dao.jdbcimpl;

import com.test.nkbookshop.utils.DBUtil;

import java.sql.*;

public class GenericBaseDao {

    //声明私有静态属性，获取数据库链接配置参数
    private static String driver= DBUtil.driver;
    private static String url=DBUtil.url;
    private static String user=DBUtil.user;
    private static String password=DBUtil.psw;

    static{

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //声明基础接口对象，因为是父类，可能被子类使用
       protected Connection conn;
       protected PreparedStatement pstmt;
       protected ResultSet rs=null;
       protected int result=-1;//不能默认初始值为0

    //  2-1  获取连接
       public void getConnection() throws SQLException {
           conn=DriverManager.getConnection(url,user,password);
       }
    //  2-2   关闭
       public void closeALL(ResultSet rs,PreparedStatement pstmt,Connection con){
           try {
               if(null != rs)
               {
                   rs.close();
                   rs = null ;
               }
               if(null != pstmt)
               {
                   pstmt.close();
                   pstmt = null ;
               }
               if(null != con)
               {
                   con.close();
                   con = null ;
               }
           } catch (Exception e) {
               e.printStackTrace();
           }

       }
    //2-3 普适 增删改 方法
            //把可变参变为final
    public void execteUpdate(String sql,final Object...params) throws SQLException {
            //生成Sql语句容器对象
        pstmt=conn.prepareStatement(sql);
        if(params!=null){//有参数传递开始遍历
            for(int i=0;i<params.length;i++){
                pstmt.setObject(i+1,params[i]);
            }
            //将返回结果放入result
            result=pstmt.executeUpdate();
        }
    }
    //2-4 普适 查 方法
    public void execteQuery(String sql,final Object...params) throws SQLException {

        //生成Sql语句容器对象
        pstmt=conn.prepareStatement(sql);
        if(params!=null){//有参数传递开始遍历
            for(int i=0;i<params.length;i++){
                pstmt.setObject(i+1,params[i]);
            }
            //将返回结果放入resultset
            rs= pstmt.executeQuery();
    }
       }
}
