package com.tj.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.dao.LibraryDao;
import com.tj.dto.LibraryDto;

public class LContentService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String lCallnum = request.getParameter("lCallnum");
		LibraryDao lDao = new LibraryDao();
		LibraryDto dto = lDao.bookView(lCallnum);
		request.setAttribute("content_view", dto);
	}

}
