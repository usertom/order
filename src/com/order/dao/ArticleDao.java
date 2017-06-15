package com.order.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import com.order.model.Article;
@Repository
public class ArticleDao extends BaseDAO{
	public void save(Article article){
		super.saveOrUpdate(article);
	}
	public void delete(Article article){
		super.delete(article);
	}
	public Article findById(int id){
		return (Article)super.findById(id, Article.class);
	}
	public List<Article> list(String title){
		String queryStr="from Article as a where a.title like'%"+title+"%'";
		return super.queryAll(queryStr, null);
	}
}
