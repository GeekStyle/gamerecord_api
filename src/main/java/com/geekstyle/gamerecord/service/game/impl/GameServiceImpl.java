package com.geekstyle.gamerecord.service.game.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geekstyle.gamerecord.dao.game.GameDao;
import com.geekstyle.gamerecord.model.game.Game;
import com.geekstyle.gamerecord.service.common.ResponseService;
import com.geekstyle.gamerecord.service.game.GameService;
import com.geekstyle.gamerecord.util.QiNiuUtil;
import com.geekstyle.gamerecord.util.UUIDUtil;

@Service
public class GameServiceImpl implements GameService {
	
	private static final String GAME_LOGO_FOLDER_PATH = "E:/!testfilefolder/img/";
	
	@Autowired
	GameDao gameDao;

	@Override
	public List<Game> getAllGames() {
		return gameDao.getAllGames();
	}
	
	public String getTableNameById(Integer id) {
		return gameDao.getTableNameById(id);
	}

	@Override
	public boolean isNameExist(String name) {
		int count = gameDao.isNameExist(name);
		if(count >= 1) {
			return true;
		}
		return false;
	}

	@Override
	public String createGame(Map<String, Object> gameMap) {
		//uploadPicture
		String logoName = (String)gameMap.get("logoName");
		String filePath = GAME_LOGO_FOLDER_PATH + logoName;
		String logoDownloadLink = QiNiuUtil.upload(filePath, logoName);
		
		//createGameRecord
		Game game = new Game();
		Integer userId = Integer.parseInt((String)gameMap.get("userId"));
		game.setMasterUserId(userId);
		game.setLogoAddr(logoDownloadLink);
		game.setName((String)gameMap.get("name"));
		String gameTableName = UUIDUtil.getUUID();
		game.setTableName(gameTableName);
		game.setTotalMembers(1);
		game.setTotalRecords(0);
		game.setCreateTime(new Date());
		gameDao.createGame(game);
		//createGameTable
		
		
		//createCategory
		
		//createCategoryItems
		
		return ResponseService.SUCCESS;
	}
}
