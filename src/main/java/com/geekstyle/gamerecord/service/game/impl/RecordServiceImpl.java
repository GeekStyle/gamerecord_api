package com.geekstyle.gamerecord.service.game.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.geekstyle.gamerecord.dao.game.RecordDao;
import com.geekstyle.gamerecord.model.game.CategoryParam;
import com.geekstyle.gamerecord.model.game.Record;
import com.geekstyle.gamerecord.service.game.CategoryService;
import com.geekstyle.gamerecord.service.game.GameService;
import com.geekstyle.gamerecord.service.game.RecordService;
import com.geekstyle.gamerecord.util.JSONUtil;
import com.geekstyle.gamerecord.util.StringUtil;

@Service
public class RecordServiceImpl implements RecordService {
	
	@Autowired
	RecordDao recordDao;
	@Autowired
	GameService gameService;
	@Autowired
	CategoryService categoryService;
	
	private RestTemplate restTemplate = new RestTemplate();
	
	@Override
	public Record getRecordById(Integer gameId,Integer id) {
		String gameRecordTableName = gameService.getTableNameById(gameId);
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("tableName", gameRecordTableName);
		params.put("id", id);
		return recordDao.getRecordById(params);
	}

	@Override
	public Map<String,Object> getRecordListByParam(Map<String, String> params) {
		Map<String,Object> responseData = new HashMap<String,Object>();
		List<CategoryParam> categoryParamList = new ArrayList<CategoryParam>();
		for(String key : params.keySet()) {
			if(key.equals("gameId") || key.equals("page")) {
				continue;
			}
			CategoryParam categoryParam = new CategoryParam();
			categoryParam.setColumnName(key);
			categoryParam.setColumnValue(params.get(key));
			categoryParamList.add(categoryParam);
		}
      
        Map<String,Object> categoryParams = new HashMap<String, Object>();  
        String gameRecordTableName = gameService.getTableNameById(Integer.parseInt(params.get("gameId")));
        categoryParams.put("tableName", gameRecordTableName);
        int startIndex = (Integer.parseInt(params.get("page")) - 1)  * RecordService.SINGLE_PAGE_COUNT;
        int singlePageCount = RecordService.SINGLE_PAGE_COUNT;
        categoryParams.put("startIndex", startIndex);
        categoryParams.put("singlePageCount", singlePageCount);
        categoryParams.put("list", categoryParamList); 
		responseData.put("recordList", recordDao.getRecordByParam(categoryParams));
		int totalPage = (recordDao.getTotalRecordByParam(categoryParams)-1) / RecordService.SINGLE_PAGE_COUNT + 1;
		responseData.put("totalPage", totalPage);
		return responseData;
	}
	
	public String getVideoAddr(String userCopiedVideoAddr) {
		String videoAddr = userCopiedVideoAddr;
		if(userCopiedVideoAddr.startsWith("<")) {
			int startIndex = userCopiedVideoAddr.indexOf("src=");
			int endIndex =  userCopiedVideoAddr.indexOf(" ", startIndex);
			videoAddr = userCopiedVideoAddr.substring(startIndex + 5, endIndex - 1);
		}
		return videoAddr;
	}

	@Override
	public String getCoverAddr(String videoAddr) {
		String imgAddr = null;
		String[] addrSpiltedArray = videoAddr.split("/");
		String videoWebsite = addrSpiltedArray[2].split("\\.")[1];
		if(videoWebsite.equals("youku")) {
			String videoId = addrSpiltedArray[4];
			HashMap map = restTemplate.getForObject("https://openapi.youku.com/v2/videos/show_basic.json?client_id=b4b01626ddf6855c&video_id=" + videoId, HashMap.class);
			imgAddr = (String)map.get("thumbnail");
		}else if(videoWebsite.equals("tudou")) {
			//http://www.tudou.com/v/axGIQtb3oEI/&rpid=68167636&resourceId=68167636_04_05_99/v.swf
			int startIndex = videoAddr.indexOf("&code=");
			int endIndex =  videoAddr.indexOf("&", startIndex+1);
			String videoId = videoAddr.substring(startIndex + 6, endIndex);
			String url = "http://api.tudou.com/v6/video/info?app_key=cde55208995acb65&format=json&itemCodes=" + videoId;
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.TEXT_PLAIN));
			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
			String responseBody = response.getBody();
			HashMap map = JSONUtil.toObject(responseBody, HashMap.class);
			imgAddr = (String)((HashMap)((ArrayList)map.get("results")).get(0)).get("bigPicUrl");
		}
		return imgAddr;
	}
	
	@Override
	public List<Record> getRecordByUserId(Integer userId) {
		return recordDao.getRecordByUserId(userId);
	}

	@Override
	public void createRecord(Integer gameId, Map<String, String> recordMap) {
		String videoAddr = getVideoAddr(recordMap.get("video_addr"));
		recordMap.put("video_addr", videoAddr);
		if(StringUtil.isEmpty(recordMap.get("cover_addr"))) {
			recordMap.put("cover_addr", getCoverAddr(videoAddr));
		}
		
		List<CategoryParam> categoryParamList = new ArrayList<CategoryParam>();
		for(String key : recordMap.keySet()) {
			CategoryParam categoryParam = new CategoryParam();
			categoryParam.setColumnName(key);
			if(key.equals("title") || key.equals("video_addr") || key.equals("cover_addr")) {
				categoryParam.setColumnValue("'" + recordMap.get(key) + "'");
			}else if(key.equals("time")) {
				String[] timeArray = recordMap.get(key).split(":");
				int minutes = Integer.parseInt(timeArray[0]) * 60000;
				int seconds = Integer.parseInt(timeArray[1]) * 1000;
				int milliseconds = 0;
				if(timeArray[2].length() == 2) {
					milliseconds =  Integer.parseInt(timeArray[2]) * 10;
				}else {
					milliseconds =  Integer.parseInt(timeArray[2]);
				}
				int totalMilliSeconds = minutes + seconds + milliseconds;
				categoryParam.setColumnValue(totalMilliSeconds);
			}else {
				categoryParam.setColumnValue(recordMap.get(key));
			}
			categoryParamList.add(categoryParam);
		}
		
		
		CategoryParam categoryParam = new CategoryParam();
		categoryParam.setColumnName("game_id");
		categoryParam.setColumnValue(gameId);
		categoryParamList.add(categoryParam);
		
        Map<String,Object> categoryParams = new HashMap<String, Object>();  
        String gameRecordTableName = gameService.getTableNameById(gameId);
        categoryParams.put("tableName", gameRecordTableName);  
        categoryParams.put("list", categoryParamList);
        recordDao.createRecord(categoryParams);
        
        Record record = new Record();
        record.setGameId(gameId);
        record.setSubTableId(((Long)categoryParams.get("id")).intValue());
        record.setCoverAddr(recordMap.get("cover_addr"));
        record.setTitle(recordMap.get("title"));
        record.setVideoAddr(recordMap.get("video_addr"));
        record.setUserId(Integer.parseInt(recordMap.get("user_id")));
        record.setCreateTime(new Date());
        recordDao.insertBaseRecord(record);
	}
	
}
