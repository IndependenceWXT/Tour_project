package com.xiao.tour.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.xiao.tour.bean.TourBean;
import com.xiao.tour.dao.TourDao;
import com.xiao.tour.tools.Config;
import com.xiao.tour.tools.ServletManager;

public class TourServlet extends HttpServlet {
	private static final long serialVersionUID=1L;
    private HttpServletRequest request;
    private HttpServletResponse response;
	private PrintWriter writer;
	
	public TourServlet() {
		super();
	}


	public void destroy() {
		super.destroy(); 
		
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
         doPost(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		   this.request=request;
	       this.response=response;
	       
	       this.request.setCharacterEncoding("utf-8");
	       this.response.setCharacterEncoding("utf-8");
	      //����Ӧ���� �������±���
	       this.response.setContentType("text/html;charset=utf-8");
	       //������
	       this.response.setHeader("Access-Control-Allow-Origin","*");
	       writer = this.response.getWriter();
	      
	       //
	       String method=this.request.getParameter("method");
//	       �ύ����������
	       if(method.equals("sendData")){
//	    	   ��ȡ���������ļ��Լ�ͼƬ�ļ�д����������洢�����ݿ����ļ��ķ�����·��
	    	   String path=getServletContext().getRealPath("/upload");
	    	   System.out.println(path);
	    	   Map<String,String> map=new ServletManager().upload(request,path);
	      boolean success=TourDao.setTour(map);
	      if(success){
//	    	  ���洢�ɹ���ʹ��json��map����ת��Ϊjson�ַ���
	    	  String json=new Gson().toJson(map);
	    	  writer.println(json);
	    	//�����
		       writer.flush();
	      }
	       
	       }
	       
	       if(method.equals("downData")){
	    	   String musicName=this.request.getParameter("music");
	    	   System.out.println(musicName);
	    	   boolean success=ServletManager.download(musicName);
	    	   if(success){
	    		   writer.println(Config.DOWNLOADPATH+"/"+musicName);
	    	   }else{
	    		   writer.println("������˼����ʧ��");
	    	   }
	    	   writer.flush();
	       }
	       
//	       �����ݿ��ȡ����
	       if(method.equals("getData")){
	    	   List<TourBean> list=TourDao.getDiary(); 
	   		   Gson gson=new Gson();
	   		   String json=gson.toJson(list);
	   		   System.out.println(json);
	   		   writer.println(json);
	       }
	       if(method.equals("regist_Data")){
	    	   String path=getServletContext().getRealPath("/upload");
	    	   System.out.println(path);
	    	   Map<String,String> map=new ServletManager().upload(request,path);
	    	   boolean success=TourDao.insertInfo(map);
	    	   if(success){
		    	  writer.println(1);
			      
	    	   }else{
	    		   writer.println(0);
	    	   }
	    	   writer.flush();
	       }
	       
	       
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
