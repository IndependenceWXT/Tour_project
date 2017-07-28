function addHistoryDiary(data,diary){
	
	var imgdiv=document.createElement("div");
	imgdiv.className="imgbox";
	diary.appendChild(imgdiv);
	
	//第二层盒子
	var leftdiv=document.createElement("div");
	leftdiv.className="imgbox-left";
	var rightdiv=document.createElement("div");
	rightdiv.className="imgbox-right";
	imgdiv.appendChild(leftdiv);
	imgdiv.appendChild(rightdiv);
	var img=document.createElement("img");
	img.className="images";
	img.src="images/5.jpg";
	if(!jQuery.isEmptyObject(data.photoUrl)){
//	把从服务器获取的图片地址设置给img的src
	      img.src=data.photoUrl;
	}
	leftdiv.appendChild(img);
	
	var a1=document.createElement("a");
	a1.className="delete";
	a1.innerHTML="删除";
	a1.href="#";
	rightdiv.appendChild(a1);
	a1.onclick=function(){
		//移除当前发布的内容
	}
	
	var p1=document.createElement("p");
	p1.className="descript";
	if(!jQuery.isEmptyObject(data.describe)){
		p1.innerHTML=data.describe;
	}
	rightdiv.appendChild(p1);
	
	//创建外层div
	var icondiv=document.createElement("div");
	icondiv.className="iconbox";
	rightdiv.appendChild(icondiv);
	
	//头像
	var iconimg=document.createElement("img");
	iconimg.src="images/5.jpg";
	icondiv.appendChild(iconimg);
	
	//用户名
	var userspan=document.createElement("span");
	userspan.className="users";
	userspan.innerHTML=data.username;
	icondiv.appendChild(userspan);
	
	//时间
	var timespan=document.createElement("span");
	timespan.className="users";
	timespan.innerHTML=data.date;
	icondiv.appendChild(timespan);
	
	//分享的盒子
	var sharediv=document.createElement("div");
	sharediv.className="share";
	rightdiv.appendChild(sharediv);
	
	//分享音乐
	var musicspan=document.createElement("span");
	musicspan.className="sharemusic";
	musicspan.innerHTML="分享音乐，暂无音乐分享";
	if(!jQuery.isEmptyObject(data.musicUrl)){
		musicspan.innerHTML="分享音乐，"+data.musicName;
	}
	sharediv.appendChild(musicspan);
	
	//下载
	var downa=document.createElement("a");
	downa.className="downloads";
	downa.href="#";
	downa.innerHTML="下载";
	sharediv.appendChild(downa);
	
	//给下载添加点击事件
	downa.onclick = function() {
	if (!jQuery.isEmptyObject(data.musicName)) {
		$.ajax({
			type: "post",
			data: fromData,
			url: "http://127.0.0.1:8080/tour/servlet/TourServlet?method=downData",
			async: false,
			dataType: "text",
			data: {
				music: data.musicName
			},
			success: function(data) {
				//data:后台打印回来的数据
				alert(data);
			},
			error: function(xhr, textState) {
				alert("请求错误");
			}
		});
	} else {
		alert("很抱歉，音乐文件不存在");
	}

}

}