<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style>
		table{width:500px; text-align: center; margin: 0 auto;
			background-color:#e7e230;}
		caption{padding: 10px; font-weight: bold; font-size: 1.2em;}
		table tr th {
			width: 30%;
			background-color: white;
			padding: 5px 0;
		}
		table tr td {
			padding: 10px 5px;
			width:70%;
			background-color: white;
		}
		table tr:nth-child(3) {
			height: 200px;
		}
		#space{height: 150px;}
	</style>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
<div id="content">
	<form action="${conPath}/bModifyView.do" method="post">
		<input type="hidden" name="pageNum" value="${param.pageNum }">
		<input type="hidden" name="bNum" value="${content_view.bNum }">
		<table>
				 <caption>${content_view.bNum }글 상세보기</caption>
				 <tr><th>작성자</th>
				 		 <td>${content_view.mId}님</td>
				 </tr>
				 <tr><th>제목</th>
				 		 <td>${content_view.bTitle }</td>
				 </tr>
				 <tr><th>본문</th>
				 		 <td align="left"><pre style="word-wrap: break-word;white-space: pre-wrap;white-space: -moz-pre-wrap;white-space: -pre-wrap;white-space: -o-pre-wrap;word-break:break-all;">${content_view.bContent}</pre></td>
				 </tr>
				 <tr><td colspan="2">
				 			<c:if test="${member.mId eq content_view.mId }">
				 				<input type="submit" value="수정">
				 			</c:if>
				 			<c:if test="${member.mId eq content_view.mId or not empty admin}">
				 				<input type="button" value="삭제"
				 					onclick="location='${conPath}/bDelete.do?bNum=${content_view.bNum }&pageNum=${param.pageNum }'">
				 			</c:if>
				 			<c:if test="${not empty member }">
				 				<input type="button" value="답변"
				 				onclick="location='${conPath}/bReply_view.do?bNum=${content_view.bNum}&pageNum=${param.pageNum}'">
				 			</c:if>
				 			<input type="button" value="목록"
				 	onclick="location='${conPath}/bList.do?pageNum=${param.pageNum }'">			 
		</table>
	</form>
	<div id="space"></div>
</div>
<jsp:include page="../main/footer.jsp"/>
</body>
</html>