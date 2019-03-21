package com.tj.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.dao.BoardDao;
import com.tj.dao.LibraryDao;

public class LDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String lCallnum = request.getParameter("lCallnum");
		LibraryDao lDao = new LibraryDao();
		int result = lDao.deleteBook(lCallnum);
		if(result == LibraryDao.SUCCESS) { // 글쓰기 진행
			request.setAttribute("resultMsg", "글쓰기 성공");
		}else {
			request.setAttribute("resultMsg", "글쓰기 실패");
		}
	}

}
