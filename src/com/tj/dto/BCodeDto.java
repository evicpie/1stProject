package com.tj.dto;

public class BCodeDto {
	private int bNumber;
	private String bCodename;
	public BCodeDto() {}
	public BCodeDto(int bNumber, String bCodename) {
		this.bNumber = bNumber;
		this.bCodename = bCodename;
	}
	public int getbNumber() {
		return bNumber;
	}
	public void setbNumber(int bNumber) {
		this.bNumber = bNumber;
	}
	public String getbCodename() {
		return bCodename;
	}
	public void setbCodename(String bCodename) {
		this.bCodename = bCodename;
	}
	@Override
	public String toString() {
		return "BCodeDto [bNumber=" + bNumber + ", bCodename=" + bCodename + "]";
	}
}
