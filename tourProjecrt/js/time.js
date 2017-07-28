function playMusic(obj){
	var player=document.getElementById("player");
	if(player.paused){
		player.play();
		obj.src="images/musicBtn.png"
	}else{
		player.pause();
		obj.src="images/musicBtnOff.png"
	}
}
$(document).ready(function(e){
//	定时器，每隔一秒获取一次数据
//1.自定义方法，2.间隔时间 ms
	setInterval(function(){
		var datas=new Date();
		var h=datas.getHours();
		var m=datas.getMinutes();
		var s=datas.getSeconds();
		if(h<10){
			h="0"+h;
		}
		if(m<10){
		m="0"+m;	
		}
		if(s<10){
		s="0"+s;	
		}
		//刷新页面
		document.getElementById("time").innerHTML=h+":"+m+":"+s;
		
	},1000);
});
