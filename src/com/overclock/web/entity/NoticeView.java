package com.overclock.web.entity;

import java.util.Date;

public class NoticeView extends Notice {
	
	private int cmtCount;
	
	public int getCmtCount() {
		return cmtCount;
	}

	public void setCmtCount(int cmtCount) {
		this.cmtCount = cmtCount;
	}

	public NoticeView() {
	}
	
	public NoticeView(int id, String title, String writerId, Date regdate, String hit, String files, int cmtCount, boolean pub) {
		super(id, title, writerId, regdate, hit, files, "", pub);
		this.cmtCount = cmtCount;
	}
	
}
