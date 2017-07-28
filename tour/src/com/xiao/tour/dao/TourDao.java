package com.xiao.tour.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.xiao.tour.bean.TourBean;

public class TourDao {
  public static boolean setTour(Map<String,String> map){
	  String sql="insert into tourinfo(username,description,musicName,musicUrl,photoName,photoUrl,date,sign) values"
       +"(?,?,?,?,?,?,?,?)";
	  DBHelper db=new DBHelper(sql);
	  
	 
	  try {
		  db.pst.setString(1,map.get("username"));
		  db.pst.setString(2,map.get("describe"));
		  db.pst.setString(3,map.get("musicName"));
		  db.pst.setString(4,map.get("musicUrl"));
		  db.pst.setString(5,map.get("photoName"));
		  db.pst.setString(6,map.get("photoUrl"));
		  db.pst.setString(7,map.get("date"));
		  db.pst.setString(8,map.get("sign"));
		  int update = db.pst.executeUpdate();
		  if(update>0){
			  return true;
		  }
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  return false;
  }
  public static List<TourBean> getDiary(){
	  String sql="select * from tourinfo order by id desc";
	  DBHelper db=new DBHelper(sql);
	  List<TourBean> list=null;
	  try {
		ResultSet query = db.pst.executeQuery();
		list=new ArrayList<TourBean>();
		while(query.next()){
			TourBean bean=new TourBean();
			bean.setDate(query.getString("date"));
			bean.setDescription(query.getString("description"));
			bean.setMusicName(query.getString("musicName"));
			bean.setMusicUrl(query.getString("musicUrl"));
			bean.setPhotoName(query.getString("photoName"));
			bean.setPhotoUrl(query.getString("photoUrl"));
			bean.setSign(query.getString("sign"));
			bean.setUsername(query.getString("username"));
			list.add(bean);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		db.close();
	}
	return list;
  }
	  public static boolean insertInfo(Map<String,String> map){
		  String sql="insert into loginInfo(userName,password,imgName,imgUrl) values(?,?,?,?)";
		  DBHelper db=new DBHelper(sql);
		  try {
			  db.pst.setString(1,map.get("userName"));
			  db.pst.setString(2,map.get("password"));
			  db.pst.setString(3,map.get("photoName"));
			  db.pst.setString(4,map.get("photoUrl"));
			  int update = db.pst.executeUpdate();
			  if(update>0){
				  return true;
			  }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  return false;
	  }
  
}
