package com.tj.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.dao.NoticeDao;
import com.tj.dto.NoticeDto;

public class NListService5 implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		NoticeDao NoticeDao = new NoticeDao();
		ArrayList<NoticeDto> list = NoticeDao.list5();
		request.setAttribute("nlist", list);
		
		}
	}
