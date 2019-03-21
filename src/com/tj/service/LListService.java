package com.tj.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.dao.LibraryDao;
import com.tj.dto.LibraryDto;

public class LListService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// pageNum받고, startRow, endRow 계산해서
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null)pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		final int PAGESIZE = 5, BLOCKSIZE=5;
		int startRow = (currentPage-1)*PAGESIZE +1;
		int endRow = startRow + PAGESIZE -1;
		// dao의 list(startRow, endRow)실행결과를 request.setAttribute
		LibraryDao lDao = new LibraryDao();
		ArrayList<LibraryDto> dtos = lDao.bookList(startRow, endRow);
		request.setAttribute("list", dtos);
		// totCnt, pageCnt, startPage, endPage, BLOCKSIZE
		//   => request.setAttribute
		int totCnt = lDao.getBookCnt();
		int pageCnt = (int)Math.ceil((double)totCnt/PAGESIZE);
		int startPage = ((currentPage-1)/BLOCKSIZE)*BLOCKSIZE+1;
		int endPage = startPage + BLOCKSIZE-1;
		if(endPage > pageCnt) {
			endPage = pageCnt;
		}
		request.setAttribute("totCnt", totCnt);
		request.setAttribute("pageCnt", pageCnt);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("BLOCKSIZE", BLOCKSIZE);
		request.setAttribute("pageNum", currentPage);
	}
}

