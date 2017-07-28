package com.xiao.tour.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {
	public static final String url="jdbc:mysql://127.0.0.1:3306/db_tour"
		       +"?useUnicode=true&characterEncoding=utf8";
		 public static final String user= "root";
		 public static final String password="123456";
		 //jdbc�������ݿ����������Ҫ�ð���֧��
		 public static final String name="com.mysql.jdbc.Driver";
		 private Connection connection;//����
		 public java.sql.PreparedStatement pst;
		 
		 
		 public DBHelper(String sql){
			 //������Ҫ�����ĵط���ctrl+1������ʾ
			 
			 try {
				 //ʹ�ù��������
				Class.forName(name);
				System.out.println("�������سɹ�");

				//��ȡ��������
				connection=DriverManager.getConnection(url,user,password);
				System.out.println("�ɹ���ȡ����");
				//׼����ִ�е�sql���
				pst=connection.prepareStatement(sql);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("��������ʧ��");
		   } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("��ȡ����ʧ��");
			}			 
		 }
		 
		 /*�ر����ݿ�����*/
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
