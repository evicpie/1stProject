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
	<style>
	#content{width:1000px; margin:0 auto;
						background-size:cover;
            z-index:-99;
            background-image: url(css/배경2.jpg);}
		form{ width:330px; height:500px; margin:0 auto; }
		h1{margin:0 auto;}
		caption{font-size: 2em; padding: 12px;}
		tr,td{height: 30px; text-align: center;}
		.button{width:70px; height: 30px; margin: 0 auto;}
		input{height: 20px;}
		#space{height: 80px;}
	</style>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<div id="content">
	<form action="${conPath }/lModify.do" method="post" enctype="multipart/form-data">
		<input type="hidden" name="pageNum" value="${param.pageNum }">
		<input type="hidden" name=lCallnum value="${modify_view.lCallnum }">
		<input type="hidden" name="dblImage" value="${modify_view.lImage }">
		<table>
			<caption>${modify_view.lTitle } 책 수정</caption>
			<tr><td>제목</td>
					<td><input type="text" name="lTitle" required="required" size="30"
								value="${modify_view.lTitle }"></td>
			</tr>
			<tr><td>저자</td><td><input type="text" name="lWriter" size="30" value="${modify_view.lWriter }"></td></tr>
			<tr><td>출판년도</td><td><input type="text" name="lYear" size="30" value="${modify_view.lYear }"></td></tr>
			<tr><td>출판사</td><td><input type="text" name="lPublicsher" size="30" value="${modify_view.lPublicsher }"></td></tr>
			<tr><td>비치된곳</td><td><input type="text" name="lLocation" value="${modify_view.lLocation }"
							required="required" size="30"></td></tr>
			<tr><td>첨부파일</td>
					<td><input type="file" name="lImage"> <br>원첨부파일:
							<c:if test="${not empty modify_view.lImage }">
						 		${modify_view.lImage}
						 	</c:if>
						 	<c:if test="${empty modify_view.lImage }">
						 		첨부파일없음
						 	</c:if>
					</td>
			</tr>
			<tr><td>키워드</td><td><input type="text" name="lKeyword" size="30" value="${modify_view.lKeyword }"></td></tr>
			<tr><td>분류번호</td><td><input type="text" name="bNumber" value="${modify_view.bNumber }"
							required="required" size="30" placeholder="제대로된 숫자를 입력해야합니다"></td></tr>
			<tr><td colspan="2"></td></tr>
			<tr><td colspan="2">
						<input type="submit" value="수정">
						<input type="button" value="목록" 
							onclick="location='${conPath}/lList.do?pageNum=${param.pageNum }'">
						<input type="reset" value="취소"
						  onclick="history.back()">
					</td>
			</tr>
		</table>
	</form>
	</div>
	<div id="space"></div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>