package com.overclock.web.entity;

import java.util.Date;

public class Cpu {
	private int id;
	private String thumbnail;	
	private String title;
	private String writerId;
	private String nickName;
	private String content;
	private Date regdate;
	private String hit;
	private String cpu;
	private boolean pub;
	
	
	public Cpu() {
		
	}

	public Cpu(int id, String thumbnail, String title, String writerId, String nickName,String content, Date regdate, String hit, String cpu, boolean pub) {
		this.id = id;
		this.thumbnail = thumbnail;
		this.title = title;
		this.writerId = writerId;
		this.nickName = nickName;
		this.content = content;
		this.regdate = regdate;
		this.hit = hit;
		this.cpu = cpu;
		this.pub = pub;
	}
	
	
	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public String getThumbnail() {
		return thumbnail;
	}




	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}




	public String getTitle() {
		return title;
	}




	public void setTitle(String title) {
		this.title = title;
	}




	public String getWriterId() {
		return writerId;
	}

	


	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}




	public String getContent() {
		return content;
	}




	public void setContent(String content) {
		this.content = content;
	}




	public Date getRegdate() {
		return regdate;
	}




	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}




	public String getHit() {
		return hit;
	}




	public void setHit(String hit) {
		this.hit = hit;
	}




	public String getCpu() {
		return cpu;
	}




	public void setCpu(String cpu) {
		this.cpu = cpu;
	}




	public boolean isPub() {
		return pub;
	}




	public void setPub(boolean pub) {
		this.pub = pub;
	}

	@Override
	public String toString() {
		return "Cpu [id=" + id + ", thumbnail=" + thumbnail + ", title=" + title + ", writerId=" + writerId
				+ ", nickName=" + nickName + ", content=" + content + ", regdate=" + regdate + ", hit=" + hit + ", cpu="
				+ cpu + ", pub=" + pub + "]";
	}




	
	
	
	
}
