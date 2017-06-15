package com.order.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.order.model.Food;
import com.order.model.Page;
@Repository
public class FoodDao extends BaseDAO{
	public void save(Food food){
		super.saveOrUpdate(food);
	}
	public void delete(Food food){
		super.delete(food);
	}
	public Food findById(int id){
		return (Food)super.findById(id, Food.class);
	}
	public List<Food> list(String name,Page page){
		String queryStr="from Food as a where a.name like'%"+name+"%'";
		return super.queryPage(queryStr, page);
	}
	public Integer count(String name){
		String queryStr="select count(*) from food as a where a.name like'%"+name+"%'";
		String b=super.count(queryStr).toString();
		int a=Integer.valueOf(b);
		return a;
	}
	public List<Food> listDay(String day){
		String queryStr="frmo Food as a where a.day like'%"+day+"%'";
		return super.queryAll(queryStr,null);
	}
}
