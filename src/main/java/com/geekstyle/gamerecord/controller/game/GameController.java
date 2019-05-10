package com.geekstyle.gamerecord.controller.game;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.geekstyle.gamerecord.model.common.Response;
import com.geekstyle.gamerecord.service.common.ResponseService;
import com.geekstyle.gamerecord.service.game.GameService;
import com.geekstyle.gamerecord.util.UUIDUtil;

@Controller
@RequestMapping("/game")
public class GameController {
	@Autowired
	GameService gameService;
	
	@RequestMapping(value="/all",method=RequestMethod.GET,headers={"Accept=application/json"})
	public @ResponseBody Response getAllGame() {
		Response response = new Response();
		response.setCode(ResponseService.OK);
		response.setData(gameService.getAllGames());
		return response;
	}
	
	/**
	 * upload customization request image
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "/icon", method = RequestMethod.POST)
	public @ResponseBody Response uploadFileHandler(@RequestParam("file") MultipartFile file) {
		Response response = new Response();
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				String basePath = "E:/!testfilefolder/img/";
				//give a name of the file uuid+"."+type
				int dotIndex = file.getOriginalFilename().lastIndexOf(".");
				String name = UUIDUtil.getUUID();
				String fullName = name +"."+file.getOriginalFilename().substring(dotIndex+1).toLowerCase();
				//write file
				FileOutputStream fileOutputStream = new FileOutputStream(new File(basePath + fullName));
				fileOutputStream.write(bytes);
				fileOutputStream.close();
				response.setCode(ResponseService.SUCCESS);
				response.setData(fullName);
				return response;
			} catch (IOException e) {
				e.printStackTrace();
				response.setCode(ResponseService.SERVER_ERROR);
				return response;
			}
		} else {
			response.setCode(ResponseService.INVALID);
			return response;
		}
	}
	
	@RequestMapping(value = "/isExist", method = RequestMethod.POST)
	public @ResponseBody Response isExist(@RequestBody Map<String,String> map) {
		Response response = new Response();
		try{
			String isExist = String.valueOf(gameService.isNameExist(map.get("name")));
			response.setCode(isExist);
		}catch(Exception e) {
			e.printStackTrace();
			//TOTO
			//exception handle
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST,headers={"Accept=application/json"})
	public @ResponseBody Response createGame(@RequestBody Map<String,Object> map) {
		System.out.println(map);
		//validate
		Response response = new Response();
		try{
			gameService.createGame(map);
		}catch(Exception e) {
			e.printStackTrace();
			//TOTO
			//exception handle
		}
		return response;
		
	}
}
