package com.tj.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.dao.NoticeDao;

public class NDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int nNum = Integer.parseInt(request.getParameter("nNum"));
		NoticeDao noticeDao = new NoticeDao();
		int result = noticeDao.delete(nNum);
		request.setAttribute("resultMsg", "글삭제성공");
	}

}
