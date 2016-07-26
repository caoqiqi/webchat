<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>聊天页面</title>
	<link rel="stylesheet" href="css/style.css" type="text/css">
	<script src="http://www.imooc.com/data/jquery-1.8.2.min.js" type="text/javascript"></script>
	<script src="js/chat.js" type="text/javascript"></script>
</head>
<body>

	<div> 当前用户：</div>
	<div id="username"><%=request.getParameter("user")%></div>
	<div id="chat">
		<div id="Content">
			<h3>聊天窗口</h3>
			<div id="report"></div>
			<div id="send">
				<table>
				<tr><td  id="face"></td></tr>
				<tr>
					<td><textarea name="txtcontent" id="txtcontent" cols="64" rows="3"></textarea></td>
					<td><input type="button" id="btn" value="发送"></td>
				</tr>
				<tr><td id="alarm">发送内容不能为空</td></tr>
			</table>
			</div>
			<span id="meg"></span>
		</div>
		<div id="online">
			<h3>在线人员</h3>
			<div id="pepole"></div>
		</div>
	</div>
</body>
</html>