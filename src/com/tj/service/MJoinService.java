package com.tj.service;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.dao.MemberDao;
import com.tj.dto.MemberDto;

public class MJoinService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		//mId, mPw, mName, mTel, mBirth, mEmail, mGender, mAddress, mDel 파라미터
		String mId = request.getParameter("mId");
		String mPw = request.getParameter("mPw");
		String mName = request.getParameter("mName");
		String mTel = request.getParameter("mTel");
		Date mBirth = null;
		String mBirthStr = request.getParameter("mBirth");
		if(!mBirthStr.equals("")) {
			mBirth = Date.valueOf(mBirthStr);
		}
		String mEmail = request.getParameter("mEmail");
		String mGender = request.getParameter("mGender");
		String mAddress = request.getParameter("mAddress");
		MemberDto dto = new MemberDto(mId, mPw, mName, mTel, mBirth, mEmail, mGender, mAddress, "n");
		MemberDao mDao = new MemberDao();
		int result = mDao.mIdConfirm(mId);
		if(result == MemberDao.NONEXISTENT) { // 회원가입 진행
			result = mDao.joinMember(dto);
			if(result==MemberDao.SUCCESS) {
				HttpSession httpSession = request.getSession();
				httpSession.setAttribute("mId", mId);
				request.setAttribute("msg", "회원가입이 성공되었습니다");
			}else {
				request.setAttribute("errorMsg", "회원가입 실패되었습니다");
			}
		}else {
			request.setAttribute("errorMsg", "중복된 ID라서 회원가입 불가합니다");
		}
	}
}
