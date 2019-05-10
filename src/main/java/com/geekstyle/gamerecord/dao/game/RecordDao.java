package com.geekstyle.gamerecord.dao.game;

import java.util.List;
import java.util.Map;

import com.geekstyle.gamerecord.model.game.Record;

public interface RecordDao {
	
	public Record getRecordById(Map<String,Object> params);
	
	public List<Record> getRecordByParam(Map<String,Object> params);
	
	public int getTotalRecordByParam(Map<String,Object> params);
	
	public List<Record> getRecordByUserId(Integer userId);
	
	public void createRecord(Map<String,Object> params);
	
	public void insertBaseRecord(Record record);
}