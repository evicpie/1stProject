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
		form{width: 350px; height: 212px; border: 2px dashed black; border-radius: 20px;
		margin: 50px auto 150px;}
		input{width: 200px; height: 30px; margin: 5px 10px; border-radius: 5px;}
		.login{background-color: lightyellow; width: 100px; height: 100px;}
		.findButton, .joinButton{width:300px; text-align: center; margin: 5px auto; border: 1px solid lightgray;
			display:block; background-color: lightYellow; height: 30px; line-height: 30px; cursor:pointer;}
	</style>
	<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script>
	 $(document).ready(function(){
		 $(".findButton").on("click", function(){
				alert("준비중이에요");
		 });
		  $(".joinButton").on("click", function(){
				location="${conPath}/joinView.do";
		 }); 
	 });
	</script>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<c:if test="${not empty msg }">
		<script>alert('${msg}');</script>
	</c:if>
	<c:if test="${not empty errorMsg }">
		<script>alert('${errorMsg}');</script>
	</c:if>
	<form id="login" action="${conPath}/login.do" method="post">
			<table>
				<tr>
					<th><input type="text" name="mId" placeholder="아이디" size=15></th>
					<td rowspan="2"><input class="login" type="submit" value="로그인"></td>
				</tr>
				<tr>
					<th><input type="password" name="mPw" placeholder="패스워드" size=15></th>
				</tr>
				<tr>
					<td colspan="2"><div class="findButton">아이디/비밀번호 찾기</div></td>
				</tr>
				<tr>
					<td colspan="2"><div class="joinButton">회원가입</div></td>
				</tr>
			</table>
		</form>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>