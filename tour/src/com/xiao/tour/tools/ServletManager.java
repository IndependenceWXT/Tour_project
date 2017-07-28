package com.xiao.tour.tools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class ServletManager {
	public Map<String,String> upload(HttpServletRequest request,String path){
		Map<String,String> map=new HashMap<String,String>();
//		�жϱ��Ƿ����ļ� ���ݱ�ͷentype�����ж�
		boolean isContent = ServletFileUpload.isMultipartContent(request);
		if(!isContent){
//			��֤�����������entype������
			return null;
		}
		File file=new File(path);
//		�ж��ļ�Ŀ¼�Ƿ����
		if (!file.exists()&&!file.isDirectory()) {
			file.mkdir();
		}
//		 ����һ���ϴ�������
		ServletFileUpload upload=new ServletFileUpload(new DiskFileItemFactory());
//		���ϴ��ļ���·�����б���
		upload.setHeaderEncoding("utf-8");
		try {
//			ͨ��upload����������
			List<FileItem> list = upload.parseRequest(request);
//			ʹ����ǿforѭ������������
			for (FileItem fileItem : list) {
				System.out.println("----"+fileItem.getFieldName());
//				�жϵ�ǰ�ļ��Ƿ�Ϊ��ͨ�ı���
				if(fileItem.isFormField()){
//					getFieldName():��ȡ�ľ���input��name
					String name = fileItem.getFieldName();
					System.out.println("**"+name);
//					 ��ȡ�ı��������ݣ�utf-8�����������
					String value = fileItem.getString("utf-8");
					System.out.println("&&&"+value);
					map.put(name, value);
				}else{
//					 �ļ���
					 String filename = fileItem.getName().replace(" ", "");
//					����ļ���Ϊ��
					 if(filename==null||filename.equals("")){
//						  ������ǰѭ����������һ��
						 continue;
					 }
					 
//					  ��ȡ�ļ��� ��1:������������
					 filename = filename.substring(filename.lastIndexOf("/")+1);
//					 ͼƬ����
					 Pattern pattern=Pattern.compile(".+(jpg|JPG|png|PNG|gif|GIF|jpeg|JPEG)");
					 Matcher m1 = pattern.matcher(filename);
//					 ���ƥ��ɹ�
					 if(m1.matches()){
						 map.put("photoName", filename);
						 map.put("photoUrl", Config.PATHURL+filename);
						System.out.println("ͼƬ�ļ�����"+filename); 
					 }
//					 ��������
					 Pattern pattern2=Pattern.compile(".+(MP3|mp3|WAV|wav|MPEG-4)");
					 Matcher m2 = pattern2.matcher(filename);
					 if(m2.matches()){
						 map.put("musicName", filename);
						 System.out.println("�����ļ�����"+filename);
						 map.put("musicUrl", Config.PATHURL+filename);
					 }
	
			try {
		    InputStream is=fileItem.getInputStream();
			FileOutputStream os=new FileOutputStream(path+"/"+filename);
			byte[] buffer=new byte[1024];
			int len=0;
			while((len=is.read(buffer))>0){
				os.write(buffer,0,len);
			}
			//�ر���
			    os.close();	
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	} catch (FileUploadException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   return map;
   }
	
	public static boolean download(String musicName){
//		��ȡ����·����ƴ�ӵ�ַ
		String path=Config.PATHURL+musicName;

			try {
				URL url = new URL(path);
		        URLConnection connection;
           	connection = url.openConnection();	
		    connection.setConnectTimeout(5*1000);
		    connection.connect();
             File file=new File(Config.DOWNLOADPATH);
//		    �ж��ļ�Ŀ¼�Ƿ����
		    if(!file.exists()&&!file.isDirectory()){
//		    	����Ŀ¼
		    	file.mkdir();
		    }
		    InputStream is = connection.getInputStream();
		    FileOutputStream os=new FileOutputStream(Config.DOWNLOADPATH+"/"+musicName);
		    byte[] buffer=new byte[1024];
			int len=0;
			while((len=is.read(buffer))>0){
				os.write(buffer,0,len);
			}
			//�ر���
		    os.close();	
			is.close();
			return true;
			} catch (MalformedURLException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
		
		e.printStackTrace();
	}
		
		return false;
	}
}
