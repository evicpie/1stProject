package com.tj.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.dao.AdminDao;
import com.tj.dao.MemberDao;
import com.tj.dto.AdminDto;
import com.tj.dto.MemberDto;

public class AdminLoginService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String aId = (String)session.getAttribute("mId");
		AdminDao aDao = new AdminDao();
		int result = aDao.aIdConfirm(aId);
		if(result == AdminDao.SUCCESS) {
			HttpSession httpSession = request.getSession();
			AdminDto dto = aDao.getAdmin(aId);
			httpSession.setAttribute("aId", aId);
			httpSession.setAttribute("admin", dto);
		}
	}

}
