package com.ajou.nise.security.model;

public class User {

	private int userNumSeq;
	private String userID;
	private String userPassword;
	private String userName;
	private String userCompany;
	private String userRegdate;
	private int userShcount;
	private int userAscount;
	
	// 생성자
	public User() {
	}

	public int getUserNumSeq() {
		return userNumSeq;
	}

	public void setUserNumSeq(int userNumSeq) {
		this.userNumSeq = userNumSeq;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserCompany() {
		return userCompany;
	}

	public void setUserCompany(String userCompany) {
		this.userCompany = userCompany;
	}

	public String getUserRegdate() {
		return userRegdate;
	}

	public void setUserRegdate(String userRegdate) {
		this.userRegdate = userRegdate;
	}

	public int getUserShcount() {
		return userShcount;
	}

	public void setUserShcount(int userShcount) {
		this.userShcount = userShcount;
	}

	public int getUserAscount() {
		return userAscount;
	}

	public void setUserAscount(int userAscount) {
		this.userAscount = userAscount;
	}

}
	
