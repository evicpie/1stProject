<%@page import="java.sql.Timestamp"%>
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
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<link href="${conPath }/css/main.css" rel="stylesheet">
	<link href="${conPath }/WebContent/css/backgroundimage.css" rel="stylesheet">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script>
  $( function() {
    $( "#datepicker" ).datepicker({
    	dateFormat : 'yy-mm-dd',
    	monthNames : ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월',],
    	showMonthAfterYear : true,
    	yearSuffix : '년', // 2019년 3월
    	showOtherMonths : true, // 다른달도 화면에 출력
    	dayNamesMin : ['일','월','화','수','목','금','토']
    });//datepicker 함수 끝
  } );
	
  </script>
  <style>
  #backimage2 {
						margin: 0 auto;
            width: 100%;
            height:80%;
            background-size:cover;
            background-image: url(css/배경2.jpg);
            position: absolute;
            top:600px;
            background-repeat:no-repeat;
            opacity:0.2;
            z-index:-99;
        }
  </style>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<c:if test="${not empty loginErrorMsg }">
		<script>alert('${loginErrorMsg}');</script>
	</c:if>
	<c:if test="${not empty resultMsg }">
		<script>alert('${resultMsg}');</script>
	</c:if>
	<c:if test="${not empty errorMsg }">
		<script>
			alert('${errorMsg}');
			history.back();
		</script>
	</c:if>
	<div id="wrap">
		<div id="con1">
			<form id="login" action="${conPath }/login.do" method="post">
				<c:if test="${empty mPw }">
					<table>
						<tr>
							<th><input type="text" name="mId" placeholder="아이디" size=15></th>
						</tr>
						<tr>
							<th><input type="password" name="mPw" placeholder="패스워드" size=15></th>
						</tr>
						<tr>
							<td><input type="button" class="button" onclick="location.href='#'" value="아이디/비밀번호 찾기" style="cursor:pointer"></td>
						</tr>
						<tr>
							<td><input type="submit" value="로그인" class="submit" style="cursor:pointer"></td>
						</tr>
					</table>
				</c:if>
				<c:if test="${not empty mPw }">
					<table>
							<tr>
								<th style="color:#0044ff;">${member.mName }님 어서오세요</th>
							</tr>
							<tr>
								<th style="color:#0044ff">행복하세요</th>
							</tr>
							<tr>
								<td><input type="button" class="button" onclick="location='${conPath }/mModifyView.do'" style="cursor:pointer" value="회원정보수정"></td>
							</tr>
							<tr>
								<td><input type="button" class="button" onclick="location='${conPath }/logout.do'" style="cursor:pointer" value="로그아웃"></td>
							</tr>
						</table>
					</c:if>
					<p>더 조은 도서관 소개</p>
					<img alt="이용안내" width="62px" height="62px" src="${conPath }/css/box_btn01.png">
					<img alt="시설안내" width="62px" height="62px" src="${conPath }/css/box_btn02.png">
					<img alt="오시는길" width="62px" height="62px" src="${conPath }/css/box_btn03.png">
				</form>
			<div id="notice">
				<br><h3>알립니다</h3>
				<table class="table">
					<tr>
						<th>제목</th>
						<th>날짜</th>
						<th>조회수</th>
					</tr>
				<c:forEach items="${nlist }" var="ndto">
					<tr>
							<td class="left">
								<nobr><a href="${conPath }/nContent_view.do?nNum=${ndto.nNum}&pageNum=1">${ndto.nTitle }</a></nobr>
							</td>
							<td><fmt:formatDate value="${ndto.nDate }" 
												type="date" dateStyle="short"/></td>
							<td>${ndto.nHIT }</td>
					</tr>
				</c:forEach>
				</table>
			</div>
			<div id="calendar">
				<p>이번 달 안내</p>
				<div id="datepicker"></div>
				<p>오늘 날짜 : <c:set var="nowTime" value="<%=new Timestamp(System.currentTimeMillis()) %>"/>
				<fmt:formatDate value="${nowTime }" type="date" dateStyle="long"/></p>
				<p>반납 날짜 : <c:set var="returnTime" value="<%=new Timestamp(System.currentTimeMillis()+1000*60*60*24*14) %>"/>
				<fmt:formatDate value="${returnTime }" type="date" dateStyle="long"/></p>
			</div>
		</div>
		<div id="search">
			<form action="${conPath}/lSearch.do">
			<table>
			<tr>
						<td>
						<h1>도서 간편 검색</h1>
						</td>
						<td>
						<input type="text" name="slTitle" class="search">
						</td>
						<td>
						<input type="submit" value="" style="cursor:pointer" class="booksubmit">
			</td></tr>
		</table>
		</form>
		</div>
		<div id="con2">
			<fieldset><legend>신작이 들어왔어요</legend>
			<div id="book">
				
					<table>
					<tr>
						<c:forEach items="${llist }" var="dto">
							<td><img src="${conPath }/libraryUp/${dto.lImage }" width="150px" height="190px" alt="${dto.lTitle }이미지"
								onclick="location.href='${conPath }/lContent_view.do?lCallnum=${dto.lCallnum}&pageNum=1'" style="cursor:pointer"></td>
								</c:forEach>
						</tr>
						<tr>
						<c:forEach items="${llist }" var="dto">
							<td>
								<nobr><a href="${conPath }/lContent_view.do?lCallnum=${dto.lCallnum}&pageNum=1">${dto.lTitle }</a></nobr>
							</td>
							</c:forEach>
						</tr>
					</tr>
				</table>
			
			</div>
		</div>
		</fieldset>
	</div>
	<div id="backimage2"></div>
<jsp:include page="../main/footer.jsp"/>
</body>
</html>