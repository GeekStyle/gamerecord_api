package com.geekstyle.gamerecord.service.game;

import java.util.List;

import com.geekstyle.gamerecord.model.game.CategoryItem;

public interface CategoryItemService {
	
	public List<CategoryItem> getCategoryItemList(Integer categoryId);
	
}
