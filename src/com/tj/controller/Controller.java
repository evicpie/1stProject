package com.tj.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.service.MJoinService;
import com.tj.service.MLoginService;
import com.tj.service.MLogoutService;
import com.tj.service.MModifyService;
import com.tj.service.MModifyViewService;
import com.tj.service.NContentService;
import com.tj.service.NDeleteService;
import com.tj.service.NListService;
import com.tj.service.NListService5;
import com.tj.service.NModifyService;
import com.tj.service.NModifyViewService;
import com.tj.service.NWriteService;
import com.tj.service.BDeleteService;
import com.tj.service.BReplyService;
import com.tj.service.BReplyViewService;
import com.tj.service.AdminLoginService;
import com.tj.service.BContentService;
import com.tj.service.BWriteService;
import com.tj.service.BListService;
import com.tj.service.BModifyService;
import com.tj.service.BModifyViewService;
import com.tj.service.IdConfirmService;
import com.tj.service.LContentService;
import com.tj.service.LDeleteService;
import com.tj.service.LInsertService;
import com.tj.service.LListService;
import com.tj.service.LListService4;
import com.tj.service.LModifyService;
import com.tj.service.LModifyViewService;
import com.tj.service.LMyBorrowService;
import com.tj.service.LRedateViewService;
import com.tj.service.LSearchService;
import com.tj.service.LbBorrowBookService;
import com.tj.service.LbListService;
import com.tj.service.LbReturnBookService;
import com.tj.service.MDeleteService;
import com.tj.service.Service;

@WebServlet("*.do")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int write_view = 0;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		actionDo(request, response);
	}
	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String uri     = request.getRequestURI();
		String conPath = request.getContextPath();
		String com     = uri.substring(conPath.length()); //들어온 요청
		String viewPage = null;
		Service service = null;
		Service service1 = null;
		if(com.equals("/main.do")) {
			service = new NListService5();
			service1 = new LListService4();
			service.execute(request, response);
			service1.execute(request, response);
			viewPage = "main/main.jsp";
		}else if(com.equals("/adminloginView.do")) {
			service = new AdminLoginService();
			service.execute(request, response);
			viewPage = "main.do";
		}else if(com.equals("/joinView.do")) {
			viewPage = "member/join.jsp";
		}else if(com.equals("/mIdConfirm.do")) {
			service = new IdConfirmService();
			service.execute(request, response);
			viewPage = "member/idConfirm.jsp";
		}else if(com.equals("/join.do")) {
			service = new MJoinService();
			service.execute(request, response);
			viewPage = "member/login.jsp";
		}else if(com.equals("/loginView.do")) {
			viewPage = "member/login.jsp";
		}else if(com.equals("/login.do")) {
			service = new MLoginService();
			service.execute(request, response);
			viewPage = "main.do";
		}else if(com.equals("/logout.do")) {
			service = new MLogoutService();
			service.execute(request, response);
			viewPage = "main.do";
		}else if(com.equals("/mModifyView.do")) {
			write_view = 1;
			service = new MModifyViewService();
			service.execute(request, response);
			viewPage = "member/mModify_view.jsp";
		}else if(com.equals("/mModify.do")) {
			if(write_view == 1) {
			service = new MModifyService();
			service.execute(request, response);
			write_view = 0;
			}
			viewPage = "main.do";
		}else if(com.equals("/mDelete.do")) {
			service = new MDeleteService();
			service.execute(request, response);
			viewPage = "main.do";
		}
		//  보드관련
		else if(com.equals("/bList.do")) {
			service = new BListService();
			service.execute(request, response);
			viewPage = "board/list.jsp";
		}else if(com.equals("/bWrite_view.do")) {
			write_view = 1;
			viewPage = "board/bWrite_view.jsp";
		}else if(com.equals("/bWrite.do")) {
			if(write_view == 1) {
				service = new BWriteService();
				service.execute(request, response);
				write_view = 0;
			}
			viewPage = "bList.do";
		}else if(com.equals("/bContent_view.do")) {
			service = new BContentService();
			service.execute(request, response);
			viewPage = "board/bContent_view.jsp";
		}else if(com.equals("/bModifyView.do")) {
			write_view = 1;
			service = new BModifyViewService();
			service.execute(request, response);
			viewPage = "board/bModify_view.jsp";
		}else if(com.equals("/bModify.do")) {
			if(write_view == 1) {
			service = new BModifyService();
			service.execute(request, response);
			write_view = 0;
			}
			viewPage = "bList.do";
		}else if(com.equals("/bDelete.do")) {
			service = new BDeleteService();
			service.execute(request, response);
			viewPage = "bList.do";
		}else if(com.equals("/bReply_view.do")) {
			write_view = 1;
			service = new BReplyViewService();
			service.execute(request, response);
			viewPage = "board/bReply_view.jsp";
		}else if(com.equals("/bReply.do")) {
			if(write_view == 1) {
			service = new BReplyService();
			service.execute(request, response);
			write_view = 0;
			}
			viewPage = "bList.do";
		}
		//공지사항 관련
		else if(com.equals("/nList.do")) {
			service = new NListService();
			service.execute(request, response);
			viewPage = "notice/list.jsp";
		}else if(com.equals("/nWrite_view.do")) {
			write_view = 1;
			viewPage = "notice/nWrite_view.jsp";
		}else if(com.equals("/nWrite.do")) {
			if(write_view == 1) {
				service = new NWriteService();
				service.execute(request, response);
				write_view = 0;
			}
			viewPage = "nList.do";
		}else if(com.equals("/nContent_view.do")) {
			service = new NContentService();
			service.execute(request, response);
			viewPage = "notice/nContent_view.jsp";
		}else if(com.equals("/nModifyView.do")) {
			write_view = 1;
			service = new NModifyViewService();
			service.execute(request, response);
			viewPage = "notice/nModify_view.jsp";
		}else if(com.equals("/nModify.do")) {
			if(write_view == 1) {
			service = new NModifyService();
			service.execute(request, response);
			write_view = 0;
			}
			viewPage = "nList.do";
		}else if(com.equals("/nDelete.do")) {
			service = new NDeleteService();
			service.execute(request, response);
			viewPage = "nList.do";
		}
		//책검색관련
		else if(com.equals("/lList.do")) {
			service = new LListService();
			service.execute(request, response);
			viewPage = "library/bookList.jsp";
		}else if(com.equals("/lSearch.do")) {
			service = new LSearchService();
			service.execute(request, response);
			viewPage = "library/bookList.jsp";
		}else if(com.equals("/lInsert_view.do")) {
			write_view = 1;
			viewPage = "library/lInsert_view.jsp";
		}else if(com.equals("/lInsert.do")) {
			if(write_view == 1) {
				service = new LInsertService();
				service.execute(request, response);
				write_view = 0;
			}
			viewPage = "lList.do";
		}else if(com.equals("/lContent_view.do")) {
			service = new LContentService();
			service1 = new LRedateViewService();
			service.execute(request, response);
			service1.execute(request, response);
			viewPage = "library/lContent_view.jsp";
		}else if(com.equals("/lModifyView.do")) {
			write_view = 1;
			service = new LModifyViewService();
			service.execute(request, response);
			viewPage = "library/lModify_view.jsp";
		}else if(com.equals("/lModify.do")) {
			if(write_view == 1) {
			service = new LModifyService();
			service.execute(request, response);
			write_view = 0;
			}
			viewPage = "lList.do";
		}else if(com.equals("/lDelete.do")) {
			service = new LDeleteService();
			service.execute(request, response);
			viewPage = "lList.do";
		}else if(com.equals("/myBorrowList.do")) {
			service = new LMyBorrowService();
			service.execute(request, response);
			viewPage = "library/myBorrowList.jsp";
		}
		//도서관리
		else if(com.equals("/libraryManage.do")) {
			service = new LbListService();
			service.execute(request, response);
			viewPage = "libraryBorrow/libraryManage.jsp";
		}else if(com.equals("/borrowBook.do")) {
			service = new LbBorrowBookService();
			service.execute(request, response);
			viewPage = "libraryManage.do";
		}else if(com.equals("/returnBook.do")) {
			service = new LbReturnBookService();
			service.execute(request, response);
			viewPage = "libraryManage.do";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}
