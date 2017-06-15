package com.order.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.order.model.UserInfo;
import com.order.model.Page;
import com.order.service.UserInfoService;
@RequestMapping("/userInfo")
@Controller
public class UserInfoController {
	private Log log = LogFactory.getLog(UserInfo.class);
	@Autowired
	private UserInfoService userInfoService;
	//人员管理首页
	@RequestMapping("/userIndex.do")
	public String userIndex(){
		return "userIndex";
	}
	//人员管理列表
	@RequestMapping(value="/userList.do",method = {RequestMethod.POST})
	@ResponseBody
	public Map<String,Object> userList(HttpServletRequest request){
		String name=request.getParameter("name");
		Page page=new Page(request);
		List<UserInfo> userInfos=userInfoService.list(name,page);
		int count=userInfoService.count(name);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put(Page.ROWS, userInfos);
		map.put(Page.TOTAL, count);
		return map;
	}
	//新增
	@RequestMapping("/add.do")
	public String add(){
		return "userAdd";
	}
	//更新
	@RequestMapping("/view.do")
	public String view(@RequestParam int id,Model model){
		UserInfo userInfo=userInfoService.find(id);
		model.addAttribute("userInfo",userInfo);
		return "view";
	}
	//执行更新或新增
	@RequestMapping(value="/doSave.do" ,method = {RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> doSave(@RequestBody String json){
		UserInfo userInfo=JSON.parseObject(json, UserInfo.class);
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{
			userInfoService.save(userInfo);
			map.put("result", "success");
		}
		catch (Exception e)
		{
			log.error("", e);
			map.put("result", "error");
		}
		return map;
	}
	//删除
	@RequestMapping(value="/doDelete.do" ,method = {RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> doDelete(@RequestBody String json){
		int id=JSON.parseObject(json, UserInfo.class).getId();
		Map<String, Object> map = new HashMap<String, Object>();
		UserInfo userInfo=userInfoService.find(id);
		try
		{
			userInfoService.delete(userInfo);
			map.put("result", "success");
		}
		catch (Exception e)
		{
			log.error("", e);
			map.put("result", "error");
		}
		return map;
	}
}
