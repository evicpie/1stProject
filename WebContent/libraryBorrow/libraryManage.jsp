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
	<link rel="stylesheet" href="css/bootstrap.css">
	<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script>
		$(function(){
			$('input[type="button"]').click(function(){
				location.href = '${conPath}/returnBook.do?lCallnum='+$('input[name="lCallnum"]').val();
			});
		});
	</script>
	<style>
		#table{ margin:15px auto; padding:15px;}
		#table input{margin:5px;}
		.boardlist{width:1000px; margin:5px auto; text-align: center;}
		.boardlist thead{font-weight: bold;}
		.boardlist tbody tr td:nth-child(1){width:60px; }
		.boardlist tbody tr td:nth-child(2){width:60px; }
		.boardlist tbody tr td:nth-child(3){text-align: left; padding: 9px 0 0 15px; }
		.boardlist tbody tr td:nth-child(4){width:150px; }
		.boardlist tbody tr td:nth-child(5){width:100px; }
		.boardlist tbody tr td:nth-child(6){width:100px; }
		.boardlist tbody tr td:nth-child(7){width:100px; }
		.paging{width:1000px; margin:5px auto 100px; text-align: center;}
	</style>
</head>
<body>
	<a href="${conPath }/main.do">메인으로 돌아가기</a>
	<form action="${conPath }/borrowBook.do">
		<table id="table">
			<tr>
				<td>책 번호 <input type="text" name="lCallnum" size="7" required="required"></td>
				<td>아이디 <input type="text" name="mId" size="7"></td>
				<td><input type="submit" value="대출"></td>
				<td><input type="button" value="반납"></td>
			</tr>
		</table>
	</form>
	<div id="content">
	<c:if test="${not empty resultMsg }">
		<script>alert('${resultMsg}');</script>
	</c:if>
	<c:if test="${not empty errorMsg}">
		<script>
			alert('${errorMsg}');
			history.back();
		</script>
	</c:if>
	<div class="boardlist">
	<table class="table table-hover">
		<thead>
		<tr><td>번호</td><td>책번호</td><td>책제목</td><td>빌려간사람</td>
				<td>빌려간날짜</td><td>반납예정일</td><td>반납일</td></tr>
		</thead>
		<tbody>
			<c:if test="${totCnt==0 }">
				<tr><td colspan="7">목록이 없습니다</td></tr>
			</c:if>
			<c:if test="${totCnt!=0 }">
				<c:forEach items="${list }" var="dto">
					<tr><td>${dto.lbNum }</td>
							<td>${dto.lCallnum }</td>
							<td>${dto.lTitle }</td>
							<td>${dto.mId }</td>
							<td><fmt:formatDate value="${dto.lbDate }" 
												type="date" dateStyle="short"/></td>
							<td><fmt:formatDate value="${dto.lbREdate }" 
												type="date" dateStyle="short"/></td>
							<td><fmt:formatDate value="${dto.lbREALre }" 
												type="date" dateStyle="short"/></td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
	</div>
	<div>
	<div class="paging">
		<c:if test="${startPage > BLOCKSIZE }">
			[ <a href="${conPath }/libraryManage.do?pageNum=${startPage-1}"> 이전 </a> ]
		</c:if>
		<c:forEach var="i" begin="${startPage }" end="${endPage }">
			<c:if test="${i==pageNum }">
				<b> [ ${i } ] </b>
			</c:if>
			<c:if test="${i != pageNum }">
				[ <a href="${conPath }/libraryManage.do?pageNum=${i}"> ${i } </a> ]
			</c:if>
		</c:forEach>
		<c:if test="${endPage<pageCnt }">
		  [ <a href="${conPath }/libraryManage.do?pageNum=${endPage+1}"> 다음 </a> ]
		</c:if>
	</div>
</div>
</div>
</body>
</html>