package com.bobe.json;

import java.math.BigDecimal;

public class User {
	private String userId;
	private String userName;
	private String sex;
	private int age;
	private BigDecimal account;
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public BigDecimal getAccount() {
		return account;
	}
	public void setAccount(BigDecimal account) {
		this.account = account;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", sex="
				+ sex + ", age=" + age + ", account=" + account + "]";
	}
	
	
}
