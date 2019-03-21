package com.tj.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.dao.MemberDao;

public class IdConfirmService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String mId = request.getParameter("mId");
		MemberDao mDao = new MemberDao();
		int result = mDao.mIdConfirm(mId);
		if(result == MemberDao.EXISTENT) {
			request.setAttribute("idConfirmMsg", "중복된 ID입니다");
		}else {
			request.setAttribute("idConfirmMsg", "사용가능한 ID입니다");
		}

	}

}
