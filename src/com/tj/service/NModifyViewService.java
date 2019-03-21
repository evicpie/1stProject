package com.tj.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.dao.BoardDao;
import com.tj.dao.NoticeDao;
import com.tj.dto.BoardDto;
import com.tj.dto.NoticeDto;

public class NModifyViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int nNum = Integer.parseInt(request.getParameter("nNum"));
		NoticeDao nDao = new NoticeDao();
		NoticeDto dto = nDao.modifyView(nNum);
		request.setAttribute("modify_view", dto);
	}

}
