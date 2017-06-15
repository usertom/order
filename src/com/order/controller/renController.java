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
import org.apache.poi.ss.usermodel.Workbook;
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

import com.alibaba.fastjson.JSON;
import com.order.model.Article;
import com.order.model.Page;
import com.order.model.UserInfo;
import com.order.service.UserInfoService;

@RequestMapping("/renshi")
@Controller
public class renController {
	private Log log = LogFactory.getLog(UserInfo.class);
	@Autowired
	private UserInfoService userInfoService;
	@RequestMapping("/renshiIndex.do")
	public String renshiIndex(){
		return "renshiIndex";
	}
	//人员管理列表
		@RequestMapping(value="/renshiList.do",method = {RequestMethod.POST})
		@ResponseBody
		public Map<String,Object> renshiList(HttpServletRequest request){
			String name=request.getParameter("name");
			Page page=new Page(request);
			List<UserInfo> userInfos=userInfoService.findBy(name, "1",page);
			int count=userInfoService.count(name,"1");
			Map<String,Object> map = new HashMap<String, Object>();
			map.put(Page.ROWS, userInfos);
			map.put(Page.TOTAL, count);
			return map;
		}
		//新增
		@RequestMapping("/add.do")
		public String add(){
			return "renshiAdd";
		}
		//更新
		@RequestMapping("/update.do")
		public String update(@RequestParam int id,Model model){
			UserInfo userInfo=userInfoService.find(id);
			model.addAttribute("userInfo",userInfo);
			return "renshiUpdate";
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
		
		@RequestMapping(value = "/exportExcel.do")
		@ResponseBody
		public Map<String, Object> exportExcel(HttpServletRequest request, HttpServletResponse response) throws IOException
		{
			Map<String, Object> map = new HashMap<String, Object>();
			String a=request.getParameter("taskIds");
			String[] myarr = a.split(",");
			int num;
			TemplateExportParams params = new TemplateExportParams(
					"resources/excelTemplate/财务导出模板.xls");
			params.setHeadingStartRow(1);
			params.setHeadingRows(1);
			Map<String, Object> map2 = new HashMap<String, Object>();
			
			int sum=0;
			try
			{
				List<UserInfo> list=new ArrayList<UserInfo>();
					
				for(int i=0;i<myarr.length;i++){
				num=Integer.parseInt(myarr[i]);

				UserInfo userInfo=userInfoService.find(num);
				sum+=userInfo.getSalary();
				list.add(userInfo);
				}
				map2.put("shh", sum);//没用，用来填充方法
				//执行导出操作
				Workbook workbook = ExcelExportUtil.exportExcel(params, UserInfo.class, list, map2);
				File savefile = new File("d:\\Excel");
				if (!savefile.exists()) {
					savefile.mkdirs();
				}
				
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");//设置日期格式
				String date=df.format(new Date());// new Date()为获取当前系统时间
				String path="d:\\Excel\\导出_"+date+".xls";
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
