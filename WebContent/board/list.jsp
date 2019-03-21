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
	<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script>
	$(document).ready(function(){
		$('tr').click(function(){
			var bNum = $(this).children(0).eq(0).text(); // 0번째 td 안에 있는 text를 가져옴
			if(!isNaN(bNum)){
				location.href = '${conPath}/bContent_view.do?bNum='+bNum+'&pageNum=${pageNum}';
			}
		});
	});
	</script>
	<link rel="stylesheet" href="css/bootstrap.css">
	<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
	<style>
		.writeBtn{width:1000px; margin:5px auto;}
		.writeBtn table{margin:0 auto;}
		.writeBtn a{text-decoration: none; color: blue;}
		.boardlist{width:1000px; margin:5px auto; text-align: center;}
		.boardlist thead{font-weight: bold;}
		.boardlist tbody tr td:nth-child(1){width:60px; }
		.boardlist tbody tr td:nth-child(2){text-align: left; padding: 9px 0 0 15px; }
		.boardlist tbody tr td:nth-child(3){width:120px; }
		.boardlist tbody tr td:nth-child(4){width:60px; }
		.boardlist tbody tr td:nth-child(5){width:100px; }
		.boardlist tbody tr td:nth-child(6){width:150px; }
		.paging{width:1000px; margin:5px auto 100px; text-align: center;}
	</style>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
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
	<div class="writeBtn">
		<table>
			<tr><td>
						<c:if test="${not empty member }"><a href="${conPath }/bWrite_view.do">글쓰기</a></c:if>
						<c:if test="${empty member }"><a href="${conPath }/loginView.do"">글쓰기는 사용자 로그인 이후에만 가능합니다</a></c:if>
			</td></tr>
		</table>
	</div>
	<div class="boardlist">
	<table class="table table-hover">
		<thead>
		<tr><td>글번호</td><td>글제목</td><td>작성자</td><td>조회수</td>
				<td>날짜</td><td>ip</td></tr>
		</thead>
		<tbody>
			<c:if test="${totCnt==0 }">
				<tr><td colspan="6">글이 없습니다</td></tr>
			</c:if>
			<c:if test="${totCnt!=0 }">
				<c:forEach items="${list }" var="dto">
					<tr><td>${dto.bNum }</td>
							<td class="left">
								<c:forEach var="i" begin="1" end="${dto.bIndent }">
									<c:if test="${i==dto.bIndent }">└─</c:if>
									<c:if test="${i!=dto.bIndent }"> &nbsp; &nbsp; </c:if>
								</c:forEach>
								<a href="${conPath }/bContent_view.do?bNum=${dto.bNum}&pageNum=${pageNum}">${dto.bTitle }</a>
							</td>
							<td>${dto.mId }</td>
							<td>${dto.bHIT }</td>
							<td><fmt:formatDate value="${dto.bDate }" 
												type="date" dateStyle="short"/></td>
							<td>${dto.bIp }</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
	</div>
	<div>
	<div class="paging">
		<c:if test="${startPage > BLOCKSIZE }">
			[ <a href="${conPath }/bList.do?pageNum=${startPage-1}"> 이전 </a> ]
		</c:if>
		<c:forEach var="i" begin="${startPage }" end="${endPage }">
			<c:if test="${i==pageNum }">
				<b> [ ${i } ] </b>
			</c:if>
			<c:if test="${i != pageNum }">
				[ <a href="${conPath }/bList.do?pageNum=${i}"> ${i } </a> ]
			</c:if>
		</c:forEach>
		<c:if test="${endPage<pageCnt }">
		  [ <a href="${conPath }/bList.do?pageNum=${endPage+1}"> 다음 </a> ]
		</c:if>
	</div>
</div>
</div>
<jsp:include page="../main/footer.jsp"/>
</body>
</html>