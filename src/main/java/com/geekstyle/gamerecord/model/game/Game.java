package com.geekstyle.gamerecord.model.game;

import java.util.Date;

public class Game {
	private Integer id;
	private Integer masterUserId;
	private String name;
	private String tableName;
	private String logoAddr;
	private Integer totalMembers;
	private Integer totalRecords;
	private Date createTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMasterUserId() {
		return masterUserId;
	}
	public void setMasterUserId(Integer masterUserId) {
		this.masterUserId = masterUserId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getLogoAddr() {
		return logoAddr;
	}
	public void setLogoAddr(String logoAddr) {
		this.logoAddr = logoAddr;
	}
	public Integer getTotalMembers() {
		return totalMembers;
	}
	public void setTotalMembers(Integer totalMembers) {
		this.totalMembers = totalMembers;
	}
	public Integer getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(Integer totalRecords) {
		this.totalRecords = totalRecords;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "Game [id=" + id + ", masterUserId=" + masterUserId + ", name="
				+ name + ", tableName=" + tableName + ", logoAddr=" + logoAddr
				+ ", totalMembers=" + totalMembers + ", totalRecords="
				+ totalRecords + ", createTime=" + createTime + "]";
	}
	
}
