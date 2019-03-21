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
	<link href="${conPath}/css/style.css" rel="stylesheet">
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
	<style>
		#content{width:800px; margin: 0 auto;}
		caption{font-size: 2em; padding: 15px;}
		.searchBtn{width: 500px; margin:0 auto;}
		.search{width:400px; height: 40px; border: 10px solid lightgray; font-size: 2em;}
		.submit{width: 60px; height: 60px; background-image: url('${conPath}/css/search.png'); margin: 20px 5px;}
		.booklist table{border: 1px solid lightgray; padding: 15px; width:798px; margin: 5px auto;}
		.booklist tr:nth-child(1) td:nth-child(1){width: 160px;}
		.booklist tr:nth-child(1) td:nth-child(2){width: 100px;}
		.booklist tr td a{text-decoration: none; color:blue;}
		.booklist tr td img{padding: 15px;}
		.booklist tr td{padding:2px;}
		#space{height: 120px;}
		.paging{width:800px; margin:5px auto 100px; text-align: center;}
	</style>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<div id="content">
	<form class="searchBtn" action="${conPath }/lSearch.do">
		<table>
			<caption>도 서 검 색</caption>
			<tr><td>
						<input type="text" name="slTitle" class="search">
						</td>
						<td>
						<input type="submit" value="" class="submit">
			</td></tr>
		</table>
	</form>
	<div class="booklist">
	
			<c:if test="${totCnt!=0 }">
				<c:forEach items="${list }" var="dto">
					<table>
					<tr>
							<td rowspan="7"><img src="${conPath }/libraryUp/${dto.lImage }" width="115px" height="155px" alt="${dto.lTitle }이미지"></td>
							<td>책번호</td>
							<td>${dto.lCallnum }</td>
						</tr>
						<tr>
							<td>책제목</td>
							<td>
								<a href="${conPath }/lContent_view.do?lCallnum=${dto.lCallnum}&pageNum=${pageNum}">${dto.lTitle }</a>
							</td>
						</tr>
						<tr>
						<td>저자</td>
							<td>${dto.lWriter }</td>
							</tr>
						<tr>
							<td>출판년도</td>
							<td>${dto.lYear }</td>
							</tr>
						<tr>
							<td>출판사</td>
							<td>${dto.lPublicsher }</td>
							</tr>
						<tr>
							<td>책위치</td>
							<td>${dto.lLocation }</td>
							</tr>
						<tr>
							<td>대출여부</td>
							<td><c:if test="${dto.lBorrow eq 'Y'}"><b style="color:blue">대출가능</b></c:if>
								<c:if test="${dto.lBorrow eq 'N'}"><b style="color:red">대출중</b></c:if>
							</td>
					</tr>
				</table>
			</c:forEach>
		</c:if>
	</div>
	<div>
	<div class="paging">
		<c:if test="${startPage > BLOCKSIZE }">
			[ <a href="${conPath }/lList.do?pageNum=${startPage-1}"> 이전 </a> ]
		</c:if>
		<c:forEach var="i" begin="${startPage }" end="${endPage }">
			<c:if test="${i==pageNum }">
				<b> [ ${i } ] </b>
			</c:if>
			<c:if test="${i != pageNum }">
				[ <a href="${conPath }/lList.do?pageNum=${i}"> ${i } </a> ]
			</c:if>
		</c:forEach>
		<c:if test="${endPage<pageCnt }">
		  [ <a href="${conPath }/lList.do?pageNum=${endPage+1}"> 다음 </a> ]
		</c:if>
	</div>
</div>
</div>
<div id="space"></div>
<jsp:include page="../main/footer.jsp"/>
</body>
</html>