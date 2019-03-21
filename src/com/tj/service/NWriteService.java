package com.tj.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.dao.BoardDao;
import com.tj.dao.NoticeDao;

public class NWriteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String aId = (String)session.getAttribute("aId");
		String nTitle = request.getParameter("nTitle");
		String nContent = request.getParameter("nContent");
		NoticeDao nDao = new NoticeDao();
		int result = nDao.write(aId, nTitle, nContent);
		if(result == NoticeDao.SUCCESS) { // 글쓰기 진행
			request.setAttribute("resultMsg", "글쓰기 성공");
		}else {
			request.setAttribute("resultMsg", "글쓰기 실패");
		}
	}

}
