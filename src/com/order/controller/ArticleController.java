package com.order.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.entity.TemplateExportParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.poi.ss.usermodel.Workbook;

import com.alibaba.fastjson.JSON;
import com.order.model.Article;
import com.order.model.UserInfo;
import com.order.service.ArticleService;
@RequestMapping("/article")
@Controller
public class ArticleController {
	private Log log = LogFactory.getLog(UserInfo.class);
	@Autowired
	private ArticleService articleService;
	@RequestMapping("/index.do")
	public String index(){
		return "articleIndex";
	}
	@RequestMapping(value="/list.do",method = {RequestMethod.POST})
	@ResponseBody
	public Object list(HttpServletRequest request){
		String title=request.getParameter("title");
		List<Article> artilces=articleService.list(title);
		return artilces;
	}
			@RequestMapping("/view.do")
			public String view(@RequestParam int id,Model model){
				Article article=articleService.find(id);
				model.addAttribute("article", article);
				return "articleView";
	}
	//新增
	@RequestMapping("/add.do")
	public String add(){
		return "articleAdd";
	}
	//更新
	@RequestMapping("/update.do")
	public String update(@RequestParam int id,Model model){
		Article article=articleService.find(id);
		model.addAttribute("article",article);
		return "articleUpdate";
	}
	//执行更新或新增
	@RequestMapping(value="/doSave.do" ,method = {RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> doSave(@RequestBody String json){
		Article article=JSON.parseObject(json, Article.class);
		Map<String, Object> map = new HashMap<String, Object>();
		try
			{
				articleService.save(article);
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
	public Map<String, Object> doDelete(@RequestBody String json){
		int id=JSON.parseObject(json, Article.class).getId();
		Map<String, Object> map = new HashMap<String, Object>();
		Article article=articleService.find(id);
		try
		{
			articleService.delete(article);
			map.put("result", "success");
		}
		catch (Exception e)
		{
			log.error("", e);
			map.put("result", "error");
		}
		return map;
	}
	
	@RequestMapping(value = "/exportExcel.do")
	@ResponseBody
	public Map<String, Object> exportExcel(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		Map<String, Object> map = new HashMap<String, Object>();
		String a=request.getParameter("taskIds");
		String[] myarr = a.split(",");
		int num;
		TemplateExportParams params = new TemplateExportParams(
				"resources/excelTemplate/文章导出模板.xls");
		params.setHeadingStartRow(0);
		params.setHeadingRows(1);
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("shh", "1216367401");//没用，用来填充方法

		try
		{
			List<Article> list=new ArrayList<Article>();
				
			for(int i=0;i<myarr.length;i++){
			num=Integer.parseInt(myarr[i]);

			Article article=articleService.find(num);
			list.add(article);
			}

			//执行导出操作
			Workbook workbook = ExcelExportUtil.exportExcel(params, Article.class, list, map);
			File savefile = new File("d:\\article");
			if (!savefile.exists()) {
				savefile.mkdirs();
			}
			
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");//设置日期格式
			String date=df.format(new Date());// new Date()为获取当前系统时间
			String path="d:\\article\\导出_"+date+".xls";
			System.out.println(path);
			FileOutputStream fos = new FileOutputStream(path);//只能导出到d盘并且会被覆盖
			workbook.write(fos);
			fos.flush();
			fos.close();
			map.put("result", "success");
		}	
		catch (Exception e)
		{
			log.error("", e);
			map.put("result", "success");
			map.put("error", e.getMessage());
		}

		return map;
		
	}
}
