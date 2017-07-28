package com.xiao.tour.dao;

import java.sql.SQLException;

import com.xiao.tour.dao.DBHelper;

public class CreateTab {
	public static void main(String[] args) {
		String sql="create table tourinfo(id int not null auto_increment,"
    +"username varchar(45),password varchar(45),headimg varchar(255),description varchar(255),musicName varchar(45),"
	+"musicUrl varchar(255),photoName varchar(45),photoUrl varchar(255),"
    +"date varchar(45),sign varchar(45),primary key(id))"
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
