package com.xiao.tour.dao;

import java.sql.SQLException;

public class createRegist {
	public static void main(String[] args) {
		String sql="create table loginInfo(id int not null auto_increment,"
    +"userName varchar(45),password varchar(45),imgName varchar(255),imgUrl varchar(255),primary key(id))"
	+"default charset=utf8";
		DBHelper db=new DBHelper(sql);
		
		try {
			int update = db.pst.executeUpdate();
			System.out.println(update);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
}
