package com.tj.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.dao.LibraryBorrowDao;

public class LbReturnBookService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String lCallnum = request.getParameter("lCallnum");
		LibraryBorrowDao lbDao = new LibraryBorrowDao();
		int result = lbDao.lbReturnBook(lCallnum);
		if(result == LibraryBorrowDao.SUCCESS) { // 글쓰기 진행
			request.setAttribute("resultMsg", "반납 성공");
		}else {
			request.setAttribute("resultMsg", "반납 실패");
		}
	}
}
