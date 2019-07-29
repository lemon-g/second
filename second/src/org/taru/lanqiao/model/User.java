package org.taru.lanqiao.model;

public class User {
	private String userName;
	private String userPassword;
	private String userId;
	private String userTel;

	private String userAdd;
	private String userShopName;

	private String userComment;
	private String userStatus;
	public User(){}
	
	public User(String userName, String userPassword, String userId, String userTel, String userAdd,
			String userShopName, String userComment, String userStatus) {
		super();
		this.userName = userName;
		this.userPassword = userPassword;
		this.userId = userId;
		this.userTel = userTel;
		this.userAdd = userAdd;
		this.userShopName = userShopName;
		this.userComment = userComment;
		this.userStatus = userStatus;
	}


	public String getUserAdd() {
		return userAdd;
	}
	public String getUserComment() {
		return userComment;
	}
	public String getUserId() {
		return userId;
	}
	public String getUserName() {
		return userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public String getUserShopName() {
		return userShopName;
	}
	public String getUserStatus() {
		return userStatus;
	}
	public String getUserTel() {
		return userTel;
	}
	public void setUserAdd(String userAdd) {
		this.userAdd = userAdd;
	}
	public void setUserComment(String userComment) {
		this.userComment = userComment;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public void setUserShopName(String userShopName) {
		this.userShopName = userShopName;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}
	
}
