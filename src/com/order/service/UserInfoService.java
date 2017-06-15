package com.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.dao.UserInfoDao;
import com.order.model.UserInfo;
import com.order.model.Page;
@Service
public class UserInfoService {
@Autowired
private UserInfoDao userInfoDao;
	public void save(UserInfo userInfo){
		userInfoDao.save(userInfo);
	}
	public UserInfo find(int id){
		return userInfoDao.findById(id);
	}
	public List<UserInfo> list(String name,Page page){
		return userInfoDao.list(name,page);
	}
	public int count(String name){
			return userInfoDao.count(name);
	}
	public int count(String name,String id){
		return userInfoDao.count(name,id);
}
	public List<UserInfo> findBy(String name,String id,Page page){
		return userInfoDao.findBy(name, id,page);
	}
	public void delete(UserInfo userInfo){
		userInfoDao.delete(userInfo);
	}
}
