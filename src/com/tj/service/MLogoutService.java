package com.tj.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MLogoutService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// session attribute 데이터 다 날리기
		HttpSession session = request.getSession();
		session.invalidate();
	}
}
