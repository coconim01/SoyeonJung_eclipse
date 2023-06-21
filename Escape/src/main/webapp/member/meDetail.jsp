<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ include file="./../common/bootstrap5.jsp" %>
<%@ include file="./../common/common.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<title>Bootstrap Example</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<style type="text/css">
	@import url('https://fonts.googleapis.com/css2?family=Dongle:wght@300&display=swap');
	/*body {
		font-family: 'Dongle', sans-serif;
	} */
	
	/*나눔고딕*/
	@import url('https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap');
	    /* 버튼 속 글자 */
		#backButton{
		margin:auto;
		font-family: 'Nanum Gothic', sans-serif;
		font-weight:bolder;
		display: flex;
		flex-direction: row;
		justify-content: flex-end;
		}
		
		/* 버튼자체 */
		.updateBtn{
		margin-left: 10px;
		border: none; 
		width:100px;
		height:40px;
		font-size:16px;
		background-color :#03D0B7; 
		color: white; 
		font-weight:600; 
		border-radius: 3px; 
		}
		
		a {
		text-decoration: none;		
		}
		
		.id{
		font-weight: Extrabold;
		color: black;	
		font-family: 'Nanum Gothic', sans-serif; /* 글꼴 */
		
		}
		
		tr {
		font-family: 'Nanum Gothic', sans-serif; /* 글꼴 */
		font-weight:600; /* 텍스트 굵기 */
		letter-spacing: 0.5px; /* 글자간격 */
		/* color: gray;  */
		
		}

	</style>
	<!-- css로 연결해주는 링크 -->
	<link rel="stylesheet" href="./freeBoardDetail.css">
</head>
<body>

	<div class="container mt-3">
	<div class="row">
    <div class="col-sm-2 "></div>
    <div class="col-sm-8 p-3 ">
    
		<h2 id="member">${requestScope.bean.nickname}님의 회원 정보</h2>
		<!-- 댓글 그리드 -->
	
		<table class="table table-striped" id="detail">
			<thead>
			</thead>
			<tbody>
				<tr>
					<td align="center" width="50%">아이디</td>
					<td width="50%">${bean.id}</td>
				</tr>
				<tr>
					<td align="center" >성별</td>
					<td>${bean.gender}</td>
				</tr>
				<tr>
					<td align="center">선호하는 테마</td>
					<td>${bean.theme}</td>
				</tr>
				<tr>
					<td align="center">가입 일자</td>
					<td>${bean.hiredate}</td>
				</tr>

				<tr>
					<td align="center">예약정보</td>
					<td><a href="<%=notWithFormTag %>rvHistory&id=${bean.id}" >>>></a></td>
				</tr>			
			</tbody>
		</table>
		<div id="backButton">
			<a href="<%=notWithFormTag %>meUpdate&id=${bean.id}" >
			<input type="button" value="수정하기" id="updateBtn" onclick="history.back();" class="updateBtn" >
			</a>
			
			<a href="<%=notWithFormTag %>meDelete1&id=${bean.id}" >
			<input type="button" value="탈퇴하기" id="updateBtn" onclick="return memberDelete()" class="updateBtn" >
			</a>
		</div>
		 </div>
   		 <div class="col-sm-2 p-3 "></div>
    	
 		 </div>
			
		
		
	
		
	</div>
	<script type="text/javascript">
		function memberDelete() {
			var msg = confirm(" 탈퇴 하시겠습니까? ")
			
			if (msg == true) {		//확인 버튼 클릭 시
				alert(" 회원이 탈퇴되었습니다.")
			
			}else {		//취소 버튼 클릭 시
				return false;
			}
		}
		</script>
</body>
</html>