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
			background-color: white;
			padding: 5px 0;
		}
		table tr td {
			padding: 10px 5px;
			background-color: white;
		}
		#space{height: 150px;}
	</style>
	
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<form action="${conPath}/lModifyView.do">
	<table>
		<caption>책 상세 보기</caption>
		<tr>
			<td rowspan="9"><img src="${conPath }/libraryUp/${content_view.lImage }" width="170px" height="220px" alt="${content_view.lTitle }이미지"></td>
							<td>책번호</td>
							<td>${content_view.lCallnum }</td>
						</tr>
						<tr>
							<td>책제목</td>
							<td>
								${content_view.lTitle }
							</td>
						</tr>
						<tr>
						<td>저자</td>
							<td>${content_view.lWriter }</td>
							</tr>
						<tr>
							<td>출판년도</td>
							<td>${content_view.lYear }</td>
							</tr>
						<tr>
							<td>출판사</td>
							<td>${content_view.lPublicsher }</td>
							</tr>
						<tr>
							<td>책위치</td>
							<td>${content_view.lLocation }</td>
							</tr>
						<tr>
							<td>대출여부</td>
							<td><c:if test="${content_view.lBorrow eq 'Y'}"><b style="color:blue">대출가능</b></c:if>
								<c:if test="${content_view.lBorrow eq 'N'}"><b style="color:red">대출중</b></c:if>
							</td>
					</tr>
					<c:if test="${content_view.lBorrow eq 'N'}">
						<tr>
							<td>
								빌려간사람
							</td>
							<td>
								${returnDate.mId }
							</td>
						</tr>
						<tr>
							<td>
								반납예정일
							</td>
							<td>
								${returnDate.lbREdate }
							</td>
						</tr>
					</c:if>
					<tr>
						<td colspan="3">
							<input type="hidden" name="lCallnum" value="${content_view.lCallnum }">
							<c:if test="${not empty admin}">
	 						<input type="submit" value="수정">
	 					</c:if>
	 					<input type="button" value="이전으로"
	 					onclick="history.go(-1)">
	 					<c:if test="${not empty admin}">
	 					<input type="button" onclick="location.href='${conPath}/lDelete.do?lCallnum=${content_view.lCallnum }'" value="삭제">
	 			</c:if>
	 				</td>
 				</tr>
	</table>
	</form>
	
	<div id="space"></div>
<jsp:include page="../main/footer.jsp"/>
</body>
</html>