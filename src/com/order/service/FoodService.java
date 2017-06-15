package com.order.service;

import java.util.List;
import java.io.File; 
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;  

import com.order.dao.FoodDao;
import com.order.model.Food;
import com.order.model.Page;

import org.apache.commons.logging.Log;

@Service
public class FoodService {
	@Autowired
	private FoodDao foodDao;
		public void save(Food food){
			foodDao.save(food);
		}
		public Food find(int id){
			return foodDao.findById(id);
		}
		public List<Food> list(String name,Page page){
			return foodDao.list(name,page);
		}
		public int count(String name){
			return foodDao.count(name);
		}
		public List<Food> listDay(String day){
			return foodDao.listDay(day);
		}
		public void delete(Food food){
			foodDao.delete(food);
		}
		//下载文件
		public void download(HttpServletResponse response,HttpServletRequest request) throws FileNotFoundException,IOException{
			String realPath = request.getRealPath("/upload/1.png");
			String fileName = realPath.substring(realPath.lastIndexOf("\\")+1);//获取要下载的文件名
			//设置content-disposition响应头控制浏览器以下载的形式打开文件
			response.setHeader("content-disposition","attachment;filename="+URLEncoder.encode(fileName,"UTF-8"));
			InputStream in = new FileInputStream(realPath);//获取文件输入流
			int len = 0;
			byte[] buffer = new byte[1024];
			OutputStream out = response.getOutputStream();
			while((len = in.read(buffer))>0){
				out.write(buffer,0,len);
			}
			in.close();
		}
}
