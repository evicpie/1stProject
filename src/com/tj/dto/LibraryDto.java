package com.tj.dto;

public class LibraryDto {
	private String lCallnum;
	private String lTitle;
	private String lWriter;
	private int lYear;
	private String lPublicsher;
	private String lLocation;
	private String lImage;
	private String lKeyword;
	private String lBorrow;
	private int lMany;
	private int bNumber;
	public LibraryDto() {}
	public LibraryDto(String lCallnum, String lTitle, String lWriter, int lYear, String lPublicsher, String lLocation,
			String lImage, String lKeyword, String lBorrow, int lMany, int bNumber) {
		this.lCallnum = lCallnum;
		this.lTitle = lTitle;
		this.lWriter = lWriter;
		this.lYear = lYear;
		this.lPublicsher = lPublicsher;
		this.lLocation = lLocation;
		this.lImage = lImage;
		this.lKeyword = lKeyword;
		this.lBorrow = lBorrow;
		this.lMany = lMany;
		this.bNumber = bNumber;
	}
	public String getlCallnum() {
		return lCallnum;
	}
	public void setlCallnum(String lCallnum) {
		this.lCallnum = lCallnum;
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
	public int getlYear() {
		return lYear;
	}
	public void setlYear(int lYear) {
		this.lYear = lYear;
	}
	public String getlPublicsher() {
		return lPublicsher;
	}
	public void setlPublicsher(String lPublicsher) {
		this.lPublicsher = lPublicsher;
	}
	public String getlLocation() {
		return lLocation;
	}
	public void setlLocation(String lLocation) {
		this.lLocation = lLocation;
	}
	public String getlImage() {
		return lImage;
	}
	public void setlImage(String lImage) {
		this.lImage = lImage;
	}
	public String getlKeyword() {
		return lKeyword;
	}
	public void setlKeyword(String lKeyword) {
		this.lKeyword = lKeyword;
	}
	public String getlBorrow() {
		return lBorrow;
	}
	public void setlBorrow(String lBorrow) {
		this.lBorrow = lBorrow;
	}
	public int getlMany() {
		return lMany;
	}
	public void setlMany(int lMany) {
		this.lMany = lMany;
	}
	public int getbNumber() {
		return bNumber;
	}
	public void setbNumber(int bNumber) {
		this.bNumber = bNumber;
	}
	@Override
	public String toString() {
		return "LibraryDto [lCallnum=" + lCallnum + ", lTitle=" + lTitle + ", lWriter=" + lWriter + ", lYear=" + lYear
				+ ", lPublicsher=" + lPublicsher + ", lLocation=" + lLocation + ", lImage=" + lImage + ", lKeyword="
				+ lKeyword + ", lBorrow=" + lBorrow + ", lMany=" + lMany + ", bNumber=" + bNumber + "]";
	}
}
