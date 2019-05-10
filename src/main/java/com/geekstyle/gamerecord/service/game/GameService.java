package com.geekstyle.gamerecord.service.game;

import java.util.List;
import java.util.Map;

import com.geekstyle.gamerecord.model.game.Game;

public interface GameService {
	
	public List<Game> getAllGames();
	
	public String getTableNameById(Integer id);
	
	public boolean isNameExist(String name);
	
	public String createGame(Map<String,Object> game);
	
}
