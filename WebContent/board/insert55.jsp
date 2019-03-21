<%@page import="com.tj.dao.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<%
		for(int i=0 ; i<55 ; i++){
			BoardDao dao = new BoardDao();
			dao.write("aaa", "제목"+i, i+"번째 본문", "192.168.10.151");
		}
		response.sendRedirect("../bList.do");
	%>
</body>
</html>