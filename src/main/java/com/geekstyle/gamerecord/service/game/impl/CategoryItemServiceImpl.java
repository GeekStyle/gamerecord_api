package com.geekstyle.gamerecord.service.game.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geekstyle.gamerecord.dao.game.CategoryItemDao;
import com.geekstyle.gamerecord.model.game.CategoryItem;
import com.geekstyle.gamerecord.service.game.CategoryItemService;

@Service
public class CategoryItemServiceImpl implements CategoryItemService {
	@Autowired
	CategoryItemDao categoryItemDao;

	@Override
	public List<CategoryItem> getCategoryItemList(Integer categoryId) {
		return categoryItemDao.getCategoryItemList(categoryId);
	}

}
