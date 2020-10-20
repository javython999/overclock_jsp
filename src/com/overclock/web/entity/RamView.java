package com.overclock.web.entity;

import java.util.Date;

public class RamView extends Ram {
	private int cmtCount;
	
	public int getCmtCount() {
		return cmtCount;
	}
	
	public void setCmtCount(int cmtCount) {
		this.cmtCount = cmtCount;
	}
	
	public RamView(int id, String thumbnail, String title, String writerId, Date regdate, String hit, RamView ram, boolean pub, int cmtCount) {
		
	}
	
	public RamView(int id, String thumbnail, String title, String writerId, String nickName, Date regdate, String hit, String ram, boolean pub, int cmtCount) {
		super(id, thumbnail, title, writerId, nickName,"", regdate, hit, ram, pub);
		this.cmtCount = cmtCount;
	}
}
