package com.order.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.order.dao.UserInfoDao;
import com.order.model.UserInfo;
import com.order.service.UserInfoService;

@RequestMapping("/login")
@Controller
public class LoginController {
	@RequestMapping("/load.do")
	public String load(){
		return "login";
	}
@RequestMapping("/login.do")
	public String login(@RequestParam("username") String username,
							@RequestParam("password") String password){
		if(username.equals("admin")&&password.equals("admin"))
		{
			return "index";
		}
		return "login";
	}

}
