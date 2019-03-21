package com.tj.service;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.dao.BoardDao;
import com.tj.dao.MemberDao;
import com.tj.dto.MemberDto;

public class MModifyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String mId = (String)session.getAttribute("mId");
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
		int result = mDao.modifyMember(dto);
		if(result == MemberDao.SUCCESS) { // 수정 진행
			request.setAttribute("resultMsg", "수정 성공");
		}else {
			request.setAttribute("resultMsg", "수정 실패");
		}
	}

}
