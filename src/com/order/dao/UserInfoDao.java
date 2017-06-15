package com.order.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.order.model.UserInfo;
import com.order.model.UserInfoVo;
import com.order.model.Page;
@Repository
public class UserInfoDao extends BaseDAO{
	public void save(UserInfo userInfo){
		super.saveOrUpdate(userInfo);
	}
	public void delete(UserInfo userInfo){
		super.delete(userInfo);
	}
	public UserInfo findById(int id){
		return (UserInfo)super.findById(id, UserInfo.class);
	}
	public List<UserInfo> list(String name,Page page){
		String queryStr="select a.*,b.name as department from userInfo as a left join department as b on b.id=a.departmentId  where a.name like '%"+name+"%'";
		return queryAll(queryStr, page);
	}
	public Integer count(String name){
		String queryStr="select count(*) from userInfo as a where a.name like'%"+name+"%'";
		String b=super.count(queryStr).toString();
		int a=Integer.valueOf(b);
		return a;
	}
	public List<UserInfo> findBy(String name,String id,Page page){
		String queryStr="from UserInfo as a where a.departmentId ="+id
				+ " and a.name like'%"+name+"%'";
		return super.queryPage(queryStr, page);
	}
	public Integer count(String name,String id){
		String queryStr="select count(*) from userInfo as a where a.departmentId ="+id
				+ " and a.name like'%"+name+"%'";
		String b=super.count(queryStr).toString();
		int a=Integer.valueOf(b);
		return a;
	}
	//联合部门查询
	protected List queryAll(String queryStr, Page page) {
		Query query = super.getCurrentSession().createSQLQuery(queryStr).addEntity(UserInfoVo.class);
		if (page != null)
		{
			query.setFirstResult(page.getEveryPage() * (page.getCurrentPage() - 1));
			query.setMaxResults(page.getEveryPage());
		}
		return query.list();
	}
}
