package com.geekstyle.gamerecord.controller.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.geekstyle.gamerecord.model.common.Response;
import com.geekstyle.gamerecord.service.common.ResponseService;
import com.geekstyle.gamerecord.service.game.CategoryItemService;

@Controller
@RequestMapping("/categoryItem")
public class CategoryItemController {
	
	@Autowired
	CategoryItemService categoryItemService;
	
	@RequestMapping(value="/{categoryId}",method=RequestMethod.GET,headers={"Accept=application/json"})
	public @ResponseBody Response getCategoryItemList(@PathVariable("categoryId") String categoryId) {
		
		Response response = new Response();
		response.setCode(ResponseService.OK);
		response.setData(categoryItemService.getCategoryItemList(Integer.valueOf(categoryId)));
		return response;
		
	}
	
}
