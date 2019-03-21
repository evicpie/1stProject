package com.tj.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.dao.LibraryBorrowDao;
import com.tj.dto.LibraryBorrowDto;

public class LRedateViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String lCallnum = request.getParameter("lCallnum");
		LibraryBorrowDao lbDao = new LibraryBorrowDao();
		LibraryBorrowDto dto = lbDao.getBookNoReturn(lCallnum);
		request.setAttribute("returnDate", dto);
	}

}
