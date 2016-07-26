$(function(){
	$("#meg").ajaxStart(function(){
		$(this).show().html("正在发送请求......");
	});
	$("#meg").ajaxStop(function(){
		$(this).html("请求成功！").hide();
	});
//鼠标单击发送按钮
	$("#btn").click(function(){
		if($("#txtcontent").val()!==""){
		sendMeg($("#txtcontent").val());
	}else{
		alert("发送内容不能为空！");
		return false;
	}
	});
//添加图片表情
	inFace();

//单击图片表情,添加到文本框
	$("img").click(function(){
		var strmsg=$("#txtcontent").val();
		strmsg+="<:"+this.id+":>";
		$("#txtcontent").val(strmsg);
	});
	var timer=setInterval("updateMenu()",3000);
	

});

//发送消息
function sendMeg(content){
	var $name=$("#username");
	var name=$name.html();
	$.ajax({
		type:"post",
		data:"name="+name+"&content="+content,
		url: "http://192.168.81.186:8080/chat/user/addChaty",
		success:function(data){
			if(data=="1"){
				getMsg();
				$("#txtcontent").val("");
			}else{
				alert("发送失败！");
				return false;
				}
		}
	});
}

//定时更新聊天窗口和在线人员内容
function updateMenu(){
	getMsg();
	getPeople();

}

function getMsg(){
	$.ajax({
		type:"get",
		data:{},
		url:"http://192.168.81.186:8080/chat/user/getAllChaty",
		success:function(data){
			data=data.replace(/<:/g,"<img src='images/");
			data=data.replace(/:>/g,".jpg' />");
			
			$("#report").html(data);
		}
	});

}

function getPeople(){
	$.ajax({
		type:"get",
		data:{},
		url:"http://192.168.81.186:8080/chat/getAllusers",
		success:function(data){
			$("#pepole").html(data);
		}
	})
}

function inFace(){
	var str="";
	for(var i=1;i<=8;i++){
		str+="<img src='images/"+i+".jpg' id='"+i+"'/>";
	}
	$("#face").html(str);
}