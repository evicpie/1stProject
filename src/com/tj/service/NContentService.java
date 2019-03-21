package com.tj.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.dao.NoticeDao;
import com.tj.dto.NoticeDto;

public class NContentService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int nNum = Integer.parseInt(request.getParameter("nNum"));
		NoticeDao noticeDao = new NoticeDao();
		NoticeDto dto = noticeDao.contentView(nNum);
		request.setAttribute("content_view", dto);
	}

}
