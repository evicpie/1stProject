package com.tj.dto;

import java.sql.Date;

public class LibraryBorrowDto {
	private int lbNum;
	private String lCallnum;
	private String mId;
	private Date lbDate;
	private Date lbREdate;
	private Date lbREALre;
	private String lTitle;
	private String lWriter;
	private String lImage;
	public LibraryBorrowDto() {}
	public LibraryBorrowDto(int lbNum, String lCallnum, String mId, Date lbDate, Date lbREdate, Date lbREALre) {
		this.lbNum = lbNum;
		this.lCallnum = lCallnum;
		this.mId = mId;
		this.lbDate = lbDate;
		this.lbREdate = lbREdate;
		this.lbREALre = lbREALre;
	}
	public LibraryBorrowDto(int lbNum, String lCallnum, String mId, Date lbDate, Date lbREdate, Date lbREALre, String lTitle, String lWriter, String lImage) {
		this.lbNum = lbNum;
		this.lCallnum = lCallnum;
		this.mId = mId;
		this.lbDate = lbDate;
		this.lbREdate = lbREdate;
		this.lbREALre = lbREALre;
		this.lTitle = lTitle;
		this.lWriter = lWriter;
		this.lImage = lImage;
	}
	public int getLbNum() {
		return lbNum;
	}
	public void setLbNum(int lbNum) {
		this.lbNum = lbNum;
	}
	public String getlCallnum() {
		return lCallnum;
	}
	public void setlCallnum(String lCallnum) {
		this.lCallnum = lCallnum;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public Date getLbDate() {
		return lbDate;
	}
	public void setLbDate(Date lbDate) {
		this.lbDate = lbDate;
	}
	public Date getLbREdate() {
		return lbREdate;
	}
	public void setLbREdate(Date lbREdate) {
		this.lbREdate = lbREdate;
	}
	public Date getLbREALre() {
		return lbREALre;
	}
	public void setLbREALre(Date lbREALre) {
		this.lbREALre = lbREALre;
	}
	public String getlTitle() {
		return lTitle;
	}
	public void setlTitle(String lTitle) {
		this.lTitle = lTitle;
	}
	public String getlWriter() {
		return lWriter;
	}
	public void setlWriter(String lWriter) {
		this.lWriter = lWriter;
	}
	public String getlImage() {
		return lImage;
	}
	public void setlImage(String lImage) {
		this.lImage = lImage;
	}
	@Override
	public String toString() {
		return "LibraryBorrowDto [lbNum=" + lbNum + ", lCallnum=" + lCallnum + ", mId=" + mId + ", lbDate=" + lbDate
				+ ", lbREdate=" + lbREdate + ", lbREALre=" + lbREALre + "]";
	}
}
