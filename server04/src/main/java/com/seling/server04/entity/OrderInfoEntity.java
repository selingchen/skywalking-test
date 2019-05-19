package com.seling.server04.entity;

import java.io.Serializable;

public class OrderInfoEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	
	private String userId;
	
	private String userName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	

}
