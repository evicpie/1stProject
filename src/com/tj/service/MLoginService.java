package com.tj.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.dao.MemberDao;
import com.tj.dto.MemberDto;

public class MLoginService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// mId, mPw 파라미터값 받아 loginCheck
		String mId = request.getParameter("mId");
		String mPw = request.getParameter("mPw");
		MemberDao mDao = new MemberDao();
		int result = mDao.loginCheck(mId, mPw);
		// loginCheck 결과가 SUCCESS면 세션에 추가
		if(result == MemberDao.LOGIN_SUCCESS) {
			HttpSession httpSession = request.getSession();
			MemberDto dto = mDao.getMember(mId);
			httpSession.setAttribute("mId", mId);
			httpSession.setAttribute("mPw", mPw);
			httpSession.setAttribute("member", dto);
		}
		// loginCheck 결과가 FAIL이면 fail이유를 request.setAttribute
		else if(result == MemberDao.LOGIN_FAIL_PW) {
			request.setAttribute("loginErrorMsg", "비밀번호를 확인하세요");
		}else if(result == MemberDao.LOGIN_FAIL_ID) {
			request.setAttribute("loginErrorMsg", "ID를 확인하세요");
		}
	}
}
