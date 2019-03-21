package com.tj.dto;

import java.sql.Date;

public class MemberDto {
	private String mId;
    private String mPw;
    private String mName;
    private String mTel;
    private Date mBirth;
    private String mEmail;
    private String mGender;
    private String mAddress;
    private String mDel;
	public MemberDto() {}
	public MemberDto(String mId, String mPw, String mName, String mTel, Date mBirth, String mEmail, String mGender,
			String mAddress, String mDel) {
		this.mId = mId;
		this.mPw = mPw;
		this.mName = mName;
		this.mTel = mTel;
		this.mBirth = mBirth;
		this.mEmail = mEmail;
		this.mGender = mGender;
		this.mAddress = mAddress;
		this.mDel = mDel;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getmPw() {
		return mPw;
	}
	public void setmPw(String mPw) {
		this.mPw = mPw;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public String getmTel() {
		return mTel;
	}
	public void setmTel(String mTel) {
		this.mTel = mTel;
	}
	public Date getmBirth() {
		return mBirth;
	}
	public void setmBirth(Date mBirth) {
		this.mBirth = mBirth;
	}
	public String getmEmail() {
		return mEmail;
	}
	public void setmEmail(String mEmail) {
		this.mEmail = mEmail;
	}
	public String getmGender() {
		return mGender;
	}
	public void setmGender(String mGender) {
		this.mGender = mGender;
	}
	public String getmAddress() {
		return mAddress;
	}
	public void setmAddress(String mAddress) {
		this.mAddress = mAddress;
	}
	public String getmDel() {
		return mDel;
	}
	public void setmDel(String mDel) {
		this.mDel = mDel;
	}
	@Override
	public String toString() {
		return "MemberDto [mId=" + mId + ", mPw=" + mPw + ", mName=" + mName + ", mTel=" + mTel + ", mBirth=" + mBirth
				+ ", mEmail=" + mEmail + ", mGender=" + mGender + ", mAddress=" + mAddress + "]";
	}
}
