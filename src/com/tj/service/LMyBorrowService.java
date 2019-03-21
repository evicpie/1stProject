package com.tj.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.dao.LibraryDao;
import com.tj.dto.LibraryDto;

public class LMyBorrowService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("들어옴?");
		HttpSession session = request.getSession();
		String mId = (String)session.getAttribute("mId");
		LibraryDao lDao = new LibraryDao();
		ArrayList<LibraryDto> dtos = lDao.myBookList(mId);
		request.setAttribute("mylist", dtos);
		int totCnt = lDao.getMyBookCnt(mId);
		request.setAttribute("mytotCnt", totCnt);
	}

}
