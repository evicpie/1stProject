package com.tj.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.dao.MemberDao;

public class MDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession httpSession = request.getSession();
		String mId = (String)httpSession.getAttribute("mId");
		MemberDao mDao = new MemberDao();
		int result = mDao.deleteMember(mId);
		if(result == MemberDao.SUCCESS) { // 탈퇴진행
			request.setAttribute("resultMsg", "탈퇴 성공");
		}else {
			request.setAttribute("resultMsg", "탈퇴 실패");
		}
	}

}
