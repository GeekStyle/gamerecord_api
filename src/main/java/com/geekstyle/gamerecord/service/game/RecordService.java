package com.geekstyle.gamerecord.service.game;

import java.util.List;
import java.util.Map;

import com.geekstyle.gamerecord.model.game.Record;

public interface RecordService {
	
	public static final int SINGLE_PAGE_COUNT = 20;
	
	public Record getRecordById(Integer gameId,Integer id);
	
	public Map<String,Object> getRecordListByParam(Map<String,String> params);
	
	public String getVideoAddr(String userCopiedVideoAddr);
	
	public String getCoverAddr(String videoAddr);
	
	public List<Record> getRecordByUserId(Integer userId);
	
	public void createRecord(Integer gameId,Map<String,String> recordMap);
}
