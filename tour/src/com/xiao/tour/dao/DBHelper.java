package com.xiao.tour.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {
	public static final String url="jdbc:mysql://127.0.0.1:3306/db_tour"
		       +"?useUnicode=true&characterEncoding=utf8";
		 public static final String user= "root";
		 public static final String password="123456";
		 //jdbc连接数据库访问数据需要该包的支持
		 public static final String name="com.mysql.jdbc.Driver";
		 private Connection connection;//连接
		 public java.sql.PreparedStatement pst;
		 
		 
		 public DBHelper(String sql){
			 //光标放在要操作的地方，ctrl+1出现提示
			 
			 try {
				 //使用工具类加载
				Class.forName(name);
				System.out.println("驱动加载成功");

				//获取数据连接
				connection=DriverManager.getConnection(url,user,password);
				System.out.println("成功获取连接");
				//准备可执行的sql语句
				pst=connection.prepareStatement(sql);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("驱动加载失败");
		   } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("获取连接失败");
			}			 
		 }
		 
		 /*关闭数据库连接*/
		 public void close(){
			 if(connection!=null){
				 try {
					connection.close();
					pst.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					connection=null;
					pst=null;
				}			 
			 }
		 }
		 
		 
}
