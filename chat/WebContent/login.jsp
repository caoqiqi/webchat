<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>聊天登录页面</title>
	<link rel="stylesheet" href="css/style.css" type="text/css">
	<script src="http://www.imooc.com/data/jquery-1.8.2.min.js" type="text/javascript"></script>
</head>
<body>
	<div id="login">
		<h3>用户登录</h3><br>
		<div id="content">
			<div><span>用户:</span><input name="userName" type="text" id="username" class="txt"/></div>
			<div><span>密码:</span><input name="pass" type="password" id="password" class="txt"/></div>
			<div>
				<input type="button" id="but1" value="登录">
				<input type="reset" id="but2" value="取消">
			</div>
		</div>
		<div id="divmsg"></div>
	</div>
	<script type="text/javascript">
	$(function(){
		$(divmsg).ajaxStart(function(){
			$(this).show().html("正在发送请求......");
		});
		$(divmsg).ajaxStop(function(){
			$(this).html("请求成功").hide();
		});

		//点击登录按钮，判断是否输入框为空，不为空，像后台请求数据；否则，提示。
		$("#but1").click(function(){
		var $name=$("#username");
		var $pass=$("#password");
		if($name.val()!=""&&$pass.val()!=""){
			userLogin($name.val(),$pass.val());
		}else{
			if($name.val()==""){
				alert("用户名不能为空!");
				$name.focus();
				return false;
			}else{
				alert("密码不能为空！");
				$pass.focus();
				return false;
			}
		}
});
		//像后台请求数据，若请求成功，进入聊天页面；否则提示。
		function userLogin(name,pass){
			$.ajax({
				type:"GET",
				url:"http://192.168.81.186:8080/chat/user/login",
				data:"name="+name+"&pass="+pass,
				success:function(data){
				if(data=="1"){
				window.location.href="chat.jsp?user="+name;}
				else{alert("用户名密码错误！");return false;}
				}
			});
		}
	})
		
	</script>
</body>
</html>