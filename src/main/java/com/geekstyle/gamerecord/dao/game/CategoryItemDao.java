package com.geekstyle.gamerecord.dao.game;

import java.util.List;

import com.geekstyle.gamerecord.model.game.CategoryItem;

public interface CategoryItemDao {
	
	public List<CategoryItem> getCategoryItemList(Integer categoryId);

}
