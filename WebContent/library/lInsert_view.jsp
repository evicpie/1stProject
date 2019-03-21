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
		#content{width:1000px; margin:0 auto;
						background-size:cover;
            z-index:-99;
            background-image: url(css/배경2.jpg);}
		form{ width:325px; height:500px; margin:0 auto; }
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
	<form action="${conPath }/lInsert.do" method="post" enctype="multipart/form-data">
		<table>
			<caption>책등록 폼</caption>
			<tr><td>제목</td><td><input type="text" name="lTitle"
							required="required" size="30"></td></tr>
			<tr><td>저자</td><td><input type="text" name="lWriter" size="30"></td></tr>
			<tr><td>출판년도</td><td><input type="text" name="lYear" size="30"></td></tr>
			<tr><td>출판사</td><td><input type="text" name="lPublicsher" size="30"></td></tr>
			<tr><td>비치된곳</td><td><input type="text" name="lLocation"
							required="required" size="30"></td></tr>
			<tr><td>책 사진</td><td><input type="file" name="lImage"></td></tr>
			<tr><td>키워드</td><td><input type="text" name="lKeyword" size="30"></td></tr>
			<tr><td>분류번호</td><td><input type="text" name="bNumber"
							required="required" size="30" placeholder="제대로된 숫자를 입력해야합니다"></td></tr>
			<tr><td colspan="2"></td></tr>
			<tr><td colspan="2">
						<input type="submit" class="button" value="글쓰기">
						<input type="reset"class="button" value="취소">
						<input type="button"class="button" value="목록"
							onclick="location.href='${conPath}/lList.do'">
		</table>
	</form>
</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>