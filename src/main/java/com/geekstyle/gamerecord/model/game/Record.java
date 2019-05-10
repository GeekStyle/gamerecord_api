package com.geekstyle.gamerecord.model.game;

import java.util.Date;

public class Record {
	private Integer id;
	private Integer subTableId;
	private Integer gameId;
	private Integer userId;
	private String title;
	private String videoAddr;
	private String coverAddr;
	private Date createTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSubTableId() {
		return subTableId;
	}
	public void setSubTableId(Integer subTableId) {
		this.subTableId = subTableId;
	}
	public Integer getGameId() {
		return gameId;
	}
	public void setGameId(Integer gameId) {
		this.gameId = gameId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getVideoAddr() {
		return videoAddr;
	}
	public void setVideoAddr(String videoAddr) {
		this.videoAddr = videoAddr;
	}
	public String getCoverAddr() {
		return coverAddr;
	}
	public void setCoverAddr(String coverAddr) {
		this.coverAddr = coverAddr;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
		
}
