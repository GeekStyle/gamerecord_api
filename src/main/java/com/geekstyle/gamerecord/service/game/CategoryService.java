package com.geekstyle.gamerecord.service.game;

import java.util.List;

import com.geekstyle.gamerecord.model.game.Category;

public interface CategoryService {
	
	public List<Category> getCategoryList(Integer gameId);
	
}
