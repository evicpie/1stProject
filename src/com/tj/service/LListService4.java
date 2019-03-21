package com.tj.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.dao.LibraryDao;
import com.tj.dto.LibraryDto;

public class LListService4 implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		LibraryDao lDao = new LibraryDao();
		ArrayList<LibraryDto> llist = lDao.newBookList();
		request.setAttribute("llist", llist);
	}

}
