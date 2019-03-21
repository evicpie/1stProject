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
  #backimage2 {
						margin: 0 auto;
            width: 100%;
            height:80%;
            background-size:cover;
            background-image: url(${conPath}/css/배경2.jpg);
            position: absolute;
            top:350px;
            background-repeat:no-repeat;
            opacity:0.2;
            z-index:-99;
        }
  #content{
  	margin: 20px auto;
  	width: 1000px;
  }
  form{width:300px; margin: 0 auto;}
  .btnwrap{text-align: center;}
  .btn{width: 65px; text-align: center; background-color: lightyellow; border:1px solid gray;
  margin: 5px 10px;}
  </style>
</head>
<body>
		<jsp:include page="../main/header.jsp"/>
<div id="content">
	<form action="${conPath }/bModify.do" method="post">
		<input type="hidden" name="pageNum" value="${param.pageNum }">
		<input type="hidden" name="bNum" value="${modify_view.bNum }">
		<input type="hidden" name="mId" value="${modify_view.mId }">
		<table>
			<caption>${modify_view.bNum }번 글 수정</caption>
			<tr><td>제목</td><td><input type="text" name="bTitle"
							required="required" size="30" value="${modify_view.bTitle }"></td></tr>
			<tr><td>본문</td><td><textarea name="bContent" rows="14" 
							cols="32">${modify_view.bContent }</textarea></td></tr>
			<tr><td colspan="2" class="btnwrap">
						<input type="submit" value="수정">
						<input type="button" value="목록" 
							onclick="location='${conPath}/bList.do?pageNum=${param.pageNum }'">
						<input type="reset" value="취소"
						  onclick="history.back()">
		</table>
	</form>
</div>
<div id="backimage2"></div>
<jsp:include page="../main/footer.jsp"/>
</body>
</html>