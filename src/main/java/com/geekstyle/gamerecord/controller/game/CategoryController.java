package com.geekstyle.gamerecord.controller.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.geekstyle.gamerecord.model.common.Response;
import com.geekstyle.gamerecord.service.common.ResponseService;
import com.geekstyle.gamerecord.service.game.CategoryService;

@Controller
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	CategoryService categoryService;
	
	@RequestMapping(value="/{gameId}",method=RequestMethod.GET,headers={"Accept=application/json"})
	public @ResponseBody Response getCategoryList(@PathVariable("gameId") String gameId) {
		
		Response response = new Response();
		response.setCode(ResponseService.OK);
		response.setData(categoryService.getCategoryList(Integer.valueOf(gameId)));
		return response;
	}
		
}
