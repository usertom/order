package com.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.dao.ArticleDao;
import com.order.model.Article;


@Service
public class ArticleService {
	@Autowired
	private ArticleDao articleDao;
		public void save(Article article){
			articleDao.save(article);
		}
		public Article find(int id){
			return articleDao.findById(id);
		}
		public List<Article> list(String title){
			return articleDao.list(title);
		}
		public void delete(Article article){
			articleDao.delete(article);
		}
}
