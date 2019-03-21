package com.tj.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.dao.BoardDao;
import com.tj.dao.NoticeDao;

public class NModifyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		int nNum = Integer.parseInt(request.getParameter("nNum"));
		String aId = (String)session.getAttribute("aId");
		String nTitle = request.getParameter("nTitle");
		String nContent = request.getParameter("nContent");
		NoticeDao nDao = new NoticeDao();
		int result = nDao.modify(nNum, nTitle, nContent);
		if(result == BoardDao.SUCCESS) { // 글쓰기 진행
			request.setAttribute("resultMsg", "글쓰기 성공");
		}else {
			request.setAttribute("resultMsg", "글쓰기 실패");
		}
	}
}
