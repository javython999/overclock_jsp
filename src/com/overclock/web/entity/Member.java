package com.overclock.web.entity;

import java.util.Date;

public class Member {
	private String id;
	private String pw;
	private String nickName;
	private String name;
	private String email;
	private Date joinDate;
	
	
	
	
	public Member() {
		
	}


	public Member(String id, String pw, String nickName, String name, String email, Date joinDate) {
		this.id = id;
		this.pw = pw;
		this.nickName = nickName;
		this.name = name;
		this.email = email;
		this.joinDate = joinDate;
	}
	
	
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getPw() {
		return pw;
	}


	public void setPw(String pw) {
		this.pw = pw;
	}


	public String getNickName() {
		return nickName;
	}


	public void setNickName(String nickName) {
		this.nickName = nickName;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Date getJoinDate() {
		return joinDate;
	}


	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}


	@Override
	public String toString() {
		return "member [id=" + id + ", pw=" + pw + ", nickName=" + nickName + ", name=" + name + ", email=" + email
				+ ", joinDate=" + joinDate + "]";
	}
}
