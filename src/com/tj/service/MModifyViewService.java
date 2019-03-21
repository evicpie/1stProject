package com.tj.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.dao.MemberDao;
import com.tj.dto.MemberDto;

public class MModifyViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String mId = (String)session.getAttribute("mId");
		MemberDao mDao = new MemberDao();
		MemberDto dto = mDao.getMember(mId);
		request.setAttribute("modify_view", dto);
	}

}
