function commitData(){
//	选中要提交的表单，并且进行格式化处理
	var fromData=new FormData($("#formdata")[0]);

//	把提交的内容追加到表单上
	fromData.append("username","李易峰");

//	获取当前日期new Date().getFullYear()+"-"+new Date().getDate()
	fromData.append("date",new Date().getFullYear()+"-"+new Date().getDate());

//	获取时间戳new Date().getTime()
	fromData.append("sign",new Date().getTime());

	$.ajax({
		type:"post",
		data:fromData,
		url:"http://127.0.0.1:8080/tour/servlet/TourServlet?method=sendData",
		async:false,
		dataType:"json",
		cache:false,
		processData:false,
		contentType:false,

		success:function(data){
			//data:后台打印回来的数据
			alert(data);
        //	切换音乐
		if(!jQuery.isEmptyObject(data.musicUrl)){
			var player=document.getElementById("player");
			player.src=data.musicUrl;
	      }
		//最外层盒子
			var diary=document.getElementById("diary");
       //			把发表的内容显示出来
          addHistoryDiary(data,diary);
		},
		error:function(xhr,textState){
			alert("请求错误");
		}
	});
}
$(document).ready(function(){
	$.ajax({
		type:"post",
		url:"http://127.0.0.1:8080/tour/servlet/TourServlet?method=getData",
		async:false,
		dataType:"json",

		success:function(data){
			//data:后台打印回来的数据
			alert(data);
		   //最外层盒子
			var diary=document.getElementById("diary");
         //	把发表的内容显示出来
            for(var i in data){
            	addHistoryDiary(data[i],diary);
            }
		},
		error:function(xhr,textState){
			alert("请求错误");
		}
	});
});

function regist(){
	var fromData=new FormData($("#register")[0]);
	var user=document.getElementById("userName").value;
	var pwd1=document.getElementById("password").value;
	var pwd2=document.getElementById("pwd").value;
		var img1=document.getElementById("img1");
	var img=document.getElementById("headimg").files;
	alert(img[0].name);
	if(user.trim()==""){
		alert("用户名不能为空");
	}else{
		
			if(/^[\d a-z A-Z]{4,8}/.test(pwd1)){ 
		
		if(pwd1==pwd2){
				$.ajax({
				type:"post",
				data:fromData,
				url:"http://127.0.0.1:8080/tour/servlet/TourServlet?method=regist_Data",
				async:false,
				dataType:"json",
				cache:false,
				processData:false,
				contentType:false,	
				success:function(data){
					//data:后台打印回来的数据
					//alert(data);
					if(data=="1"||data==1){
						alert("注册成功");
						img1.src="http://127.0.0.1:8080/tour/upload/"+img[0].name;
					}
				},
				error:function(xhr,textState){
					alert("请求错误");
				}
			});		
		}else{
			 alert("密码不一致");
		}
	}else{
	 alert("请输入4-8个字符或数字");
	}
	}
	


	

	
}
