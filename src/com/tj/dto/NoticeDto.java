package com.tj.dto;

import java.sql.Date;

public class NoticeDto {
	private int nNum;
	private String nTitle;
	private String nContent;
	private Date nDate;
	private int nHIT;
	private String aId;
	public NoticeDto() {}
	public NoticeDto(int nNum, String nTitle, String nContent, Date nDate, int nHIT, String aId) {
		this.nNum = nNum;
		this.nTitle = nTitle;
		this.nContent = nContent;
		this.nDate = nDate;
		this.nHIT = nHIT;
		this.aId = aId;
	}
	public int getnNum() {
		return nNum;
	}
	public void setnNum(int nNum) {
		this.nNum = nNum;
	}
	public String getnTitle() {
		return nTitle;
	}
	public void setnTitle(String nTitle) {
		this.nTitle = nTitle;
	}
	public String getnContent() {
		return nContent;
	}
	public void setnContent(String nContent) {
		this.nContent = nContent;
	}
	public Date getnDate() {
		return nDate;
	}
	public void setnDate(Date nDate) {
		this.nDate = nDate;
	}
	public int getnHIT() {
		return nHIT;
	}
	public void setnHIT(int nHIT) {
		this.nHIT = nHIT;
	}
	public String getaId() {
		return aId;
	}
	public void setaId(String aId) {
		this.aId = aId;
	}
	@Override
	public String toString() {
		return "NoticeDto [nNum=" + nNum + ", nTitle=" + nTitle + ", nContent=" + nContent + ", nDate=" + nDate
				+ ", nHIT=" + nHIT + ", aId=" + aId + "]";
	}
}
