package com.tj.dto;

import java.sql.Date;

public class BoardDto {
	private int bNum;
	private String bTitle;
	private String bContent;
	private Date bDate;
	private int bHIT;
	private int bGroup;
	private int bStep;
	private int bIndent;
	private String mId;
	private String bIp;
	public BoardDto() {}
	public BoardDto(int bNum, String bTitle, String bContent, Date bDate, int bHIT, int bGroup, int bStep, int bIndent,
			String mId, String bIp) {
		this.bNum = bNum;
		this.bTitle = bTitle;
		this.bContent = bContent;
		this.bDate = bDate;
		this.bHIT = bHIT;
		this.bGroup = bGroup;
		this.bStep = bStep;
		this.bIndent = bIndent;
		this.mId = mId;
		this.bIp = bIp;
	}
	public int getbNum() {
		return bNum;
	}
	public void setbNum(int bNum) {
		this.bNum = bNum;
	}
	public String getbTitle() {
		return bTitle;
	}
	public void setbTitle(String bTitle) {
		this.bTitle = bTitle;
	}
	public String getbContent() {
		return bContent;
	}
	public void setbContent(String bContent) {
		this.bContent = bContent;
	}
	public Date getbDate() {
		return bDate;
	}
	public void setbDate(Date bDate) {
		this.bDate = bDate;
	}
	public int getbHIT() {
		return bHIT;
	}
	public void setbHIT(int bHIT) {
		this.bHIT = bHIT;
	}
	public int getbGroup() {
		return bGroup;
	}
	public void setbGroup(int bGroup) {
		this.bGroup = bGroup;
	}
	public int getbStep() {
		return bStep;
	}
	public void setbStep(int bStep) {
		this.bStep = bStep;
	}
	public int getbIndent() {
		return bIndent;
	}
	public void setbIndent(int bIndent) {
		this.bIndent = bIndent;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getbIp() {
		return bIp;
	}
	public void setbIp(String bIp) {
		this.bIp = bIp;
	}
	@Override
	public String toString() {
		return "BoardDto [bNum=" + bNum + ", bTitle=" + bTitle + ", bContent=" + bContent + ", bDate=" + bDate
				+ ", bHIT=" + bHIT + ", bGroup=" + bGroup + ", bStep=" + bStep + ", bIndent=" + bIndent + ", mId=" + mId
				+ ", bIp=" + bIp + "]";
	}
}
