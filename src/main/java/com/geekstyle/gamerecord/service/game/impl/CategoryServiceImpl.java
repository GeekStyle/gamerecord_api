package com.geekstyle.gamerecord.service.game.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geekstyle.gamerecord.dao.game.CategoryDao;
import com.geekstyle.gamerecord.model.game.Category;
import com.geekstyle.gamerecord.service.game.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	CategoryDao categoryDao;

	@Override
	public List<Category> getCategoryList(Integer gameId) {
		return categoryDao.getCategoryList(gameId);
	}

}
