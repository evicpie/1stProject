package com.tj.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.dao.LibraryBorrowDao;

public class LbBorrowBookService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String lCallnum = request.getParameter("lCallnum");
		String mId = request.getParameter("mId");
		LibraryBorrowDao lbDao = new LibraryBorrowDao();
		int result = lbDao.lbBorrowBook(lCallnum, mId);
		if(result == LibraryBorrowDao.SUCCESS) { // 대출 진행
			request.setAttribute("resultMsg", "대출 성공");
		}else {
			request.setAttribute("resultMsg", "대출 실패");
		}
	}

}
