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
*{padding:0; margin: 0;}
header { 
	 font-size: 10pt;
}
header a {text-decoration: none; font-weight: bold;}
header li { list-style: none;}
header .gnb{width: 100%;	background-color: lightyellow;}
header .gnb ul {
	overflow: hidden;
	width:700px;
	height: 30px;
	line-height: 30px;
	margin: 0 auto;
}
header .gnb ul li {	float: right;	margin-right: 30px;}
header .gnb a { 
	color : black;
	font-size: 0.9em;
	display: block;
	padding-left:15px;
	padding-right:15px;
}
header #space{height:203px;}
header .logo{
	width:108px; text-align:center;
	margin: 100px auto 140px; font-size:2em;
	cursor: pointer;
}
header .lnb {
  width: 100%; height: 40px;
  border-top: 1px dashed #003300;
	border-bottom: 1px dashed #003300; z-index: 99;
	position:relative;
}
header .lnb ul{
	overflow: hidden;
	width:550px;
	margin: 0 auto;
}
header .lnb ul>li {
	margin: 5px;
	float:left;
	line-height: 25px;
	width:120px;
	text-align: center;
}
header .lnb li a {color: #003300;}
header .lnb li {color: #003300;}
header .lnb ul li .subMenu {display:none;
	width:120px;}
header .lnb ul li:hover .subMenu {
	width:120px;display: block; position:relative; margin: 10px 0 0 0; background-color:lightYellow;
	}
</style>
<link href="${conPath }/css/backgroundimage.css" rel="stylesheet">
</head>
<body>
	<header>
	<c:if test="${empty member and empty admin}"> <%-- 로그인 전 화면 --%>
		<div class="gnb">
			<ul>
				<li><a href="${conPath }/lList.do">책목록</a></li>
				<li><a href="${conPath }/joinView.do">회원가입</a></li>
				<li><a href="${conPath }/loginView.do">로그인</a></li>
			</ul>
		</div>
		<div id="backimage"></div>
		<div id="space">
			<div class="logo" onclick="location.href='${conPath}/main.do'">
				더조은 도서관 LOGO
			</div>
		</div>
		<div class="lnb">
			<ul>
				<li>공지사항<ol class="subMenu">
							<li><a href="${conPath}/nList.do">공지사항</a></li>
							</ol>
				</li>
				<li>도서검색<ol class="subMenu">
							<li><a href="lList.do">도서검색</a></li>
							<li><a href="${conPath }/loginView.do">내가 빌린책</a></li>
							</ol>
				</li>
				<li>자유게시판<ol class="subMenu">
							<li><a href="${conPath }/bList.do">자유게시판</a></li>
							</ol>
				</li>
				<li>my menu<ol class="subMenu">
							<li><a href="${conPath }/loginView.do">회원정보 수정</a></li>
							<li><a href="${conPath }/loginView.do">회원탈퇴</a></li>
						</ol>
				</li>
			</ul>
		</div>
	</c:if>
	<c:if test="${not empty member and empty admin}"> <%-- 사용자 모드 로그인 화면--%>
		<div class="gnb">
			<ul>
				<li><a href="${conPath }/lList.do">책목록</a></li>
				<li><a href="${conPath }/logout.do">로그아웃</a></li>
				<li><a href="${conPath }/mModifyView.do">정보수정</a></li>
				<li><a>${member.mName }님 &nbsp; ▶</a></li>	
			</ul>
		</div>
		<div id="backimage"></div>
		<div id="space">
			<div class="logo" onclick="location.href='${conPath}/main.do'">
				더조은 도서관 LOGO
			</div>
		</div>
		<div class="lnb">
			<ul>
				<li>공지사항<ol class="subMenu">
							<li><a href="${conPath}/nList.do">공지사항</a></li>
							</ol>
				</li>
				<li>도서검색<ol class="subMenu">
							<li><a href="${conPath }/lList.do">도서검색</a></li>
							<li><a href="${conPath }/myBorrowList.do">내가 빌린책</a></li>
							</ol>
				</li>
				<li><a href="${conPath }/bList.do">자유게시판</a><ol class="subMenu">
							<li><a href="${conPath }/bList.do">자유게시판</a></li>
							</ol>
				</li>
				<li>my menu<ol class="subMenu">
							<li><a href="mModifyView.do">회원정보 수정</a></li>
							<li><a href="mDelete.do">회원탈퇴</a></li>
						</ol>
				</li>
			</ul>
		</div>
	</c:if>
	<c:if test="${not empty member and not empty admin}"> <%-- 관리자 모드 로그인 화면--%>
		<div class="gnb">
			<ul>
				<li><a href="${conPath }/nList.do">공지사항</a></li>
				<li><a href="${conPath }/logout.do">관리자모드나가기</a></li>
				<li><a>${admin.aName }님 &nbsp; ▶</a></li>	
			</ul>
		</div>
		<div id="backimage"></div>
		<div id="space">
			<div class="logo" onclick="location.href='${conPath}/main.do'">
				더조은 도서관 LOGO
			</div>
		</div>
		<div class="lnb">
			<ul>
				<li>도서 관리<ol class="subMenu">
							<li><a href="${conPath}/lList.do">도서 목록</a></li> <%-- 수정 삭제는 이 안에서 --%>
							<li><a href="${conPath}/lInsert_view.do">도서 등록</a></li>
						</ol>
				</li>
				<li>대출 / 반납<ol class="subMenu">
							<li><a href="${conPath}/libraryManage.do">대출/반납 하기</a></li>
							<li><a href="#">대출된 책 보기</a></li>
							<li><a href="#">반납일 지난 책</a></li>
						</ol>
				</li>
				<li>게시판 관리<ol class="subMenu">
							<li><a href="${conPath}/nList.do">공지사항</a></li>
							<li><a href="${conPath }/bList.do">자유게시판</a></li>
						</ol>
				</li>
				<li>회원 관리<ol class="subMenu">
							<li><a href="#">회원 목록</a></li>
						</ol>
					</li>
				</ul>
			</div>
		</c:if>
	</header>
</body>
</html>