package com.geekstyle.gamerecord.dao.game;

import java.util.List;

import com.geekstyle.gamerecord.model.game.Game;

public interface GameDao {
	
	public List<Game> getAllGames();
	
	public String getTableNameById(Integer id);
	
	public int isNameExist(String name);
	
	public void createGame(Game game);
	
}