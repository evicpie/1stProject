package com.tj.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.dao.BoardDao;

public class BDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int bNum = Integer.parseInt(request.getParameter("bNum"));
		BoardDao boardDao = new BoardDao();
		int result = boardDao.delete(bNum);
		request.setAttribute("resultMsg", "글삭제성공");
	}

}
