package com.overclock.web.entity;

import java.util.Date;

public class Comment {
	
	private int id;
	private String content;
	private Date regdate;
	private String nickname;
	private int noticeId;
	private String writerId;

	public Comment() {
	}
	
	public Comment(int id, String content, Date regdate, String nickname, int noticeId, String writerId ) {
		this.id = id;
		this.content = content;
		this.regdate = regdate;
		this.nickname= nickname;
		this.noticeId = noticeId;
		this.writerId = writerId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getNickname() {
		return nickname;
	}

	public void setWriterId_(String nickname) {
		this.nickname = nickname;
	}

	public int getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(int noticeId) {
		this.noticeId = noticeId;
	}
	
	public String getWriterId() {
		return writerId;
	}
	
	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", content=" + content + ", regdate=" + regdate + ", nickname=" + nickname
				+ ", noticeId=" + noticeId + ", writerId=" + writerId + "]";
	}

	
	





	
	
	
	
	
}
