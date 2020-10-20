package com.overclock.web.entity;

import java.util.Date;

public class CpuView extends Cpu {
	private int cmtCount;
	
	public int getCmtCount() {
		return cmtCount;
	}
	
	public void setCmtCount(int cmtCount) {
		this.cmtCount = cmtCount;
	}
		
	public CpuView(int id, String thumbnail, String title, String writerId, String nickName, Date regdate, String hit, String cpu, boolean pub, int cmtCount) {
		super(id, thumbnail, title, writerId, nickName,"", regdate, hit, cpu, pub);
		this.cmtCount = cmtCount;
	}

	public CpuView(int id, String title, String writerId, Date regdate, String hit, String files, int cmtCount2,
			boolean pub) {
		// TODO Auto-generated constructor stub
	}
}
