package com.tj.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.dao.BoardDao;
import com.tj.dto.BoardDto;

public class BReplyViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int bNum = Integer.parseInt(request.getParameter("bNum"));
		BoardDao boardDao = new BoardDao();
		BoardDto dto      = boardDao.modifyView_replyView(bNum);
		request.setAttribute("reply_view", dto);
	}

}
