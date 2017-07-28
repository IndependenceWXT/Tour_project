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
//		判断表单是否含有文件 根据表头entype进行判断
		boolean isContent = ServletFileUpload.isMultipartContent(request);
		if(!isContent){
//			验证，如果不包含entype，结束
			return null;
		}
		File file=new File(path);
//		判断文件目录是否存在
		if (!file.exists()&&!file.isDirectory()) {
			file.mkdir();
		}
//		 创建一个上传工具类
		ServletFileUpload upload=new ServletFileUpload(new DiskFileItemFactory());
//		给上传文件的路径进行编码
		upload.setHeaderEncoding("utf-8");
		try {
//			通过upload解析请求码
			List<FileItem> list = upload.parseRequest(request);
//			使用增强for循环，遍历集合
			for (FileItem fileItem : list) {
				System.out.println("----"+fileItem.getFieldName());
//				判断当前文件是否为普通文本表单
				if(fileItem.isFormField()){
//					getFieldName():获取的就是input的name
					String name = fileItem.getFieldName();
					System.out.println("**"+name);
//					 获取文本表单的内容，utf-8解决中文乱码
					String value = fileItem.getString("utf-8");
					System.out.println("&&&"+value);
					map.put(name, value);
				}else{
//					 文件表单
					 String filename = fileItem.getName().replace(" ", "");
//					如果文件名为空
					 if(filename==null||filename.equals("")){
//						  结束当前循环，继续下一次
						 continue;
					 }
					 
//					  截取文件名 ＋1:不包含“／”
					 filename = filename.substring(filename.lastIndexOf("/")+1);
//					 图片正则
					 Pattern pattern=Pattern.compile(".+(jpg|JPG|png|PNG|gif|GIF|jpeg|JPEG)");
					 Matcher m1 = pattern.matcher(filename);
//					 如果匹配成功
					 if(m1.matches()){
						 map.put("photoName", filename);
						 map.put("photoUrl", Config.PATHURL+filename);
						System.out.println("图片文件名："+filename); 
					 }
//					 音乐正则
					 Pattern pattern2=Pattern.compile(".+(MP3|mp3|WAV|wav|MPEG-4)");
					 Matcher m2 = pattern2.matcher(filename);
					 if(m2.matches()){
						 map.put("musicName", filename);
						 System.out.println("音乐文件名："+filename);
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
			//关闭流
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
//		获取音乐路径的拼接地址
		String path=Config.PATHURL+musicName;

			try {
				URL url = new URL(path);
		        URLConnection connection;
           	connection = url.openConnection();	
		    connection.setConnectTimeout(5*1000);
		    connection.connect();
             File file=new File(Config.DOWNLOADPATH);
//		    判断文件目录是否存在
		    if(!file.exists()&&!file.isDirectory()){
//		    	创建目录
		    	file.mkdir();
		    }
		    InputStream is = connection.getInputStream();
		    FileOutputStream os=new FileOutputStream(Config.DOWNLOADPATH+"/"+musicName);
		    byte[] buffer=new byte[1024];
			int len=0;
			while((len=is.read(buffer))>0){
				os.write(buffer,0,len);
			}
			//关闭流
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
