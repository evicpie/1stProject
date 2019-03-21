package com.tj.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.dao.BoardDao;

public class BReplyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int bGroup   = Integer.parseInt(request.getParameter("bGroup"));
		int bStep    = Integer.parseInt(request.getParameter("bStep"));
		int bIndent  = Integer.parseInt(request.getParameter("bIndent"));
		String mId = request.getParameter("mId");
		String bTitle= request.getParameter("bTitle");
		String bContent=request.getParameter("bContent");
		String bIp = request.getRemoteAddr();
		BoardDao boardDao = new BoardDao();
		int result = boardDao.reply(mId, bTitle, bContent, bIp, bGroup, bStep, bIndent);
		request.setAttribute("resultMsg", "답변글 성공");
	}

}
