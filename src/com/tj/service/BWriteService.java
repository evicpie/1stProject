package com.tj.service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.dao.BoardDao;

public class BWriteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
			HttpSession session = request.getSession();
			String mId = (String)session.getAttribute("mId");
			String bTitle = request.getParameter("bTitle");
			String bContent = request.getParameter("bContent");
			String bIp = request.getRemoteAddr();
			BoardDao bDao = new BoardDao();
			int result = bDao.write(mId, bTitle, bContent, bIp);
			if(result == BoardDao.SUCCESS) { // 글쓰기 진행
				request.setAttribute("resultMsg", "글쓰기 성공");
			}else {
				request.setAttribute("resultMsg", "글쓰기 실패");
			}
		}
	}
