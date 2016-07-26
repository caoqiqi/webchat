package com.tgb.web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tgb.entity.Chaty;
import com.tgb.entity.User;
import com.tgb.manager.UserManager;

@Controller
@RequestMapping("/user")
public class UserController {
	private int i = 1;
	@Resource(name="userManager")
	private UserManager userManager;

	@RequestMapping("/getAllUser")
	public String getAllUser(HttpServletRequest request){
		
		request.setAttribute("userList", userManager.getAllUser());
		
		return "/index";
	}
	
	@RequestMapping("/login")
	public void test(HttpServletRequest request, HttpServletResponse response) throws IOException {
	
		System.out.println("jinlaile");
		String userName = request.getParameter("name");
		String pass = request.getParameter("pass");
		User u = userManager.checkUser(userName);
		String checkpass = u.getPass();
		String check;
		
		if(pass.equals(checkpass)){
			check="1";
		}
		else{
			check="2";
		}
		
		PrintWriter writer = response.getWriter();
		writer.print(check);
		writer.close();
	}
	
	
	@RequestMapping("/addChaty")
	public void addChaty(Chaty chaty,HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		String userName = request.getParameter("name");
		String content = request.getParameter("content");
		System.out.println(userName+content);
		
		chaty.setContent(content);
		chaty.setUserName(userName);
		userManager.addChaty(chaty);
		PrintWriter writer = response.getWriter();
		writer.print("1");
		writer.close();
		
	}
	
	@RequestMapping("/getAllChaty")
	public String getAllChaty(HttpServletResponse response) throws IOException{
		
		java.util.List<Chaty> list = userManager.getAllChaty();
		String content = "你好<br />";
		for(int i = 0;i<list.size();i++){
			content = content+"<div>"+list.get(i).getUserName()+":"+list.get(i).getContent()+"<br />"+"</div>";
		}
		System.out.println(content);
		PrintWriter write  = response.getWriter();
		write.print(content);
		write.close();
		return "/index";
	}
	
	@RequestMapping("/getUser")
	public String getUser(String id,HttpServletRequest request){
		
		request.setAttribute("user", userManager.getUser(id));
	
		return "/editUser";
	}
	
	@RequestMapping("/toAddUser")
	public String toAddUser(){
		return "/addUser";
	}
	
	@RequestMapping("/addUser")
	public String addUser(User user,HttpServletRequest request){
		
		userManager.addUser(user);
		
		return "redirect:/user/getAllUser";
	}
	
	@RequestMapping("/delUser")
	public void delUser(String id,HttpServletResponse response){
		
		String result = "{\"result\":\"error\"}";
		
		if(userManager.delUser(id)){
			result = "{\"result\":\"success\"}";
		}
		
		response.setContentType("application/json");
		
		try {
			PrintWriter out = response.getWriter();
			out.write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/updateUser")
	public String updateUser(User user,HttpServletRequest request){
		
		if(userManager.updateUser(user)){
			user = userManager.getUser(user.getId());
			request.setAttribute("user", user);
			return "redirect:/user/getAllUser";
		}else{
			return "/error";
		}
	}
}