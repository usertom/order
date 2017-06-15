package com.order.controller;

import java.io.File;
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
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.order.model.Food;
import com.order.model.Page;
import com.order.service.FoodService;
@RequestMapping("/food")
@Controller
public class FoodController {
	private Log log = LogFactory.getLog(Food.class);
	@Autowired
	private FoodService foodService;
	@RequestMapping("/index.do")
	public String index(){
		return "foodIndex";
	}
	@RequestMapping(value="/list.do",method = {RequestMethod.POST})
	@ResponseBody
	public Map<String,Object> list(HttpServletRequest request){
		String name=request.getParameter("name");
		Page page=new Page(request);
		List<Food> foods=foodService.list(name,page);
		int count=foodService.count(name);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put(Page.ROWS, foods);
		map.put(Page.TOTAL, count);
		return map;
	}
	@RequestMapping("/view.do")
	public String view(@RequestParam int id,Model model){
		Food food=foodService.find(id);
		model.addAttribute("food", food);
		return "foodView";
	}
	//����
	@RequestMapping("/add.do")
	public String add(){
		return "foodAdd";
	}
	//����
	@RequestMapping("/update.do")
	public String update(@RequestParam int id,Model model){
		Food food=foodService.find(id);
		model.addAttribute("food",food);
		return "foodUpdate";
	}
	//保存或新增
	@RequestMapping(value="/doSave.do" ,method = {RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> doSave(@RequestBody String json){
		Food food=JSON.parseObject(json, Food.class);
		Map<String, Object> map = new HashMap<String, Object>();
		try
			{
				foodService.save(food);
				map.put("result", "success");
			}
		catch (Exception e)
			{
				log.error("", e);
				map.put("result", "error");
			}
		return map;
	}
	@RequestMapping(value="/doDelete.do" ,method = {RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> doDelete(@RequestParam int id){
		Map<String, Object> map = new HashMap<String, Object>();
		Food food=foodService.find(id);
		try
		{
			foodService.delete(food);
			map.put("result", "success");
		}
		catch (Exception e)
		{
			log.error("", e);
			map.put("result", "error");
		}
		return map;
	}
	@RequestMapping("/day.do")
	public String day(){
		return "day";
	}
	@RequestMapping("/dayList.do")
	@ResponseBody
	public Object dayList(@RequestParam String day,Model model){
		List<Food> foods=foodService.listDay(day);
		return foods;
	}
	@RequestMapping("/uploadIndex.do")
	public String uploadIndex(@RequestParam int id,Model model){
		model.addAttribute("id",id);
		return "upload";
	}
	@SuppressWarnings("deprecation")
	@RequestMapping("/upload.do")
	public String upload(@RequestParam(value="file",required=false) MultipartFile file,HttpServletRequest request,HttpServletResponse response,Model model){
		String path=request.getRealPath("/upload");
		String id=request.getParameter("id");
		System.out.println(id);
		String fileName=file.getOriginalFilename();
		System.out.println(path);
		System.out.println(fileName);
		File targetFile=new File(path,fileName);
		if(!targetFile.exists()){
			targetFile.mkdirs();
		}
		try{
			file.transferTo(targetFile);
			Food food=foodService.find(Integer.valueOf(id));
			food.setUrl(fileName);
			foodService.save(food);
		}catch(Exception e){
			log.error("",e);
		}
		return "foodIndex";
	}
}
