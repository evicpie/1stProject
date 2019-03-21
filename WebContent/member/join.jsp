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
		form{ height:500px; margin:0 0 0 380px;}
		h1{margin:0 auto;}
		caption{font-size: 2em; padding: 12px;}
		tr,td{height: 30px; text-align: center;}
		.button{width:70px; height: 30px; margin: 0 auto;}
		input{height: 20px;}
		#space{height: 80px;}
	</style>
	<link href="${conPath }/WebContent/css/backgroundimage.css" rel="stylesheet">
	<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script>
	function joinInfoChk(){
		if(joinFrm.mId.value.length<3){
			alert('아이디가 너무 짧습니다');
			joinFrm.mId.value = '';
			joinFrm.mId.focus();
			return false;
		}
		if(joinFrm.mPw.value.length<3){
			alert('비밀번호가 너무 짧습니다');
			joinFrm.mPw.value = '';
			joinFrm.mPw.focus();
			return false;
		}
		if(joinFrm.mPw.value!=joinFrm.pwChk.value){
			alert('비밀번호가 일치해야 합니다');
			joinFrm.mPw.value = '';
			joinFrm.pwChk.value = '';
			joinFrm.mPw.focus();
			return false;
		}
	}
	 $(document).ready(function(){
	$('#mIdConfirm').click(function(){
		var mId = $('input[name="mId"]').val();
		$.ajax({
			url : '${conPath}/mIdConfirm.do',
			type : 'get',
			dataType : 'html',
			data : "mId="+mId,
			success : function(data){
				$('#idConfirmMsg').html("<b>"+data+"</b>");
			}
		});
	});
}); 
	</script>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<div id="backimage2"></div>
	<div id="content">
	<form action="${conPath}/join.do" method="post" name="joinFrm"
		onsubmit="return joinInfoChk()">
		<h1>&nbsp; &nbsp; 회 원 가 입</h1>
		<br>
		<table>
			<tr><th>아이디 </th>
				<td><input type="text" name="mId" required="required" placeholder="아이디는 3자이상"></td>
				<td><input type="button" id="mIdConfirm" value="중복확인" class="button"></td>
				<td><div id="idConfirmMsg"> &nbsp; &nbsp; &nbsp;</div></td>
			</tr>
			<tr><th>비밀번호 </th>
				<td><input type="password" name="mPw" required="required" placeholder="비밀번호는 3자이상"></td>
			</tr>
			<tr><th>비번확인 </th>
				<td><input type="password" name="pwChk" required="required"></td>
			</tr>
			<tr><th>이름 </th>
				<td><input type="text" name="mName" required="required"></td>
			</tr>
			<tr><th>전화번호 </th>
				<td><input type="text" name="mTel"></td>
			<tr><th>생년월일 </th>
				<td><input type="date" name="mBirth"></td>
			</tr>
			<tr><th>메일주소 </th>
				<td><input type="email" name="mEmail"></td>
			</tr>
			<tr>
				<th>성별 </th>
				<td>
					<input type="radio" id="m" name="mGender" value="m" checked="checked"><label for="m">남</label> &nbsp;
					<input type="radio" id="f" name="mGender" value="f"><label for="f">여</label>
				</td>
			</tr>
			<tr>
				<th>
					주소 
				</th>
				<td>
					<input type="text" name="mAddress">
				</td>
				</tr>
				<tr>	
					<td colspan="2">
						
					</td>
				</tr>
				<tr>	
					<td colspan="2">
						<input type="submit" value="회원가입" class="button">
						<input type="button" value="로그인" class="button"
						onclick="location='${conPath}/loginView.do'"> 
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div id="space"></div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>