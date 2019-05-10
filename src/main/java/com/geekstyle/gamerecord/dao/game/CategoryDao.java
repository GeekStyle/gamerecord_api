package com.geekstyle.gamerecord.dao.game;

import java.util.List;

import com.geekstyle.gamerecord.model.game.Category;

public interface CategoryDao {
	
	public List<Category> getCategoryList(Integer gameId);
	
}
