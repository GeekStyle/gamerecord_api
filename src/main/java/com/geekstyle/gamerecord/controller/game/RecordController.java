package com.geekstyle.gamerecord.controller.game;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.geekstyle.gamerecord.model.common.Response;
import com.geekstyle.gamerecord.model.game.Record;
import com.geekstyle.gamerecord.service.common.ResponseService;
import com.geekstyle.gamerecord.service.game.RecordService;

@Controller
@RequestMapping("/record")
public class RecordController {
	
	@Autowired
	RecordService recordService;
	
	@RequestMapping(value="/{gameId}/{id}",method=RequestMethod.GET,headers={"Accept=application/json"})
	public @ResponseBody Response getRecord(@PathVariable("gameId") String gameId,@PathVariable("id") String id) {
		Record record = recordService.getRecordById(Integer.parseInt(gameId),Integer.parseInt(id));
		Response response = new Response();
		response.setCode(ResponseService.OK);
		response.setData(record);
		return response;
	}
	
	@RequestMapping(value="/params",method=RequestMethod.POST,headers={"Accept=application/json"})
	public @ResponseBody Response getRecordListByParam(@RequestBody Map<String,String> params) {
		Response response = new Response();
		response.setCode(ResponseService.OK);
		response.setData(recordService.getRecordListByParam(params));
		return response;
	}
	
	@RequestMapping(value="/videoCover",method=RequestMethod.POST,headers={"Accept=application/json"})
	public @ResponseBody Response getVideoCover(@RequestBody Map<String,String> params) {
		Response response = new Response();
		response.setCode(ResponseService.OK);
		response.setData(recordService.getCoverAddr(params.get("videoAddr")));
		return response;
	}
	
	@RequestMapping(value="/userVideo/{userId}",method=RequestMethod.GET,headers={"Accept=application/json"})
	public @ResponseBody Response getRecordByUserId(@PathVariable("userId") Integer userId) {
		Response response = new Response();
		response.setCode(ResponseService.OK);
		response.setData(recordService.getRecordByUserId(userId));
		return response;
	}
	
	@RequestMapping(value="/{gameId}",method=RequestMethod.POST,headers={"Accept=application/json"})
	public @ResponseBody Response createRecord(@PathVariable("gameId") Integer gameId,@RequestBody Map<String,String> recordMap) {
		Response response = new Response();
		response.setCode(ResponseService.OK);
		recordService.createRecord(gameId,recordMap);
		return response;
	}
	
}
