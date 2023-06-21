<!-- 
 페이지명 : 리뷰게시판 상세페이지 JSP
 작성일자 : 2023-03-14
 작성자 : 정영우
-->


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="./../common/common.jsp"%>
<%@ include file="./../common/bootstrap5.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- jquery를 사용할 수 있게 연결해주는 링크 -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
	<!-- js로 연결해주는 링크 -->
	<script type="text/javascript" src="./reviewBoardDetailReg.js"></script>
	<!-- css로 연결해주는 링크 -->
	<link rel="stylesheet" href="./reviewBoardDetailReg.css">
	<!-- ajax를 사용할 수 있게 연결해주는 링크 -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<div class="container mt-3 background1" align="center">
		<br/>
		<h1 align="left" style="color:#03D0B7; margin-bottom: 10px;">탈출의민족_리뷰게시판 글작성페이지</h1>
		<div class = "dtlsubject"align="left" style="margin-bottom: -10px;">리뷰를 등록, 수정, 삭제할 수 있습니다.</div>
		<br/>
		<!-- 로그인한 회원아이디 숨김 -->
		<input type="hidden" id="loginId" value="${sessionScope.loginfo.id}"></input>
		<!-- 작성자 아이디 -->
		<input type="hidden" id="writerId" value=""></input>
 		<div align="left" width="100%" height="600px" style="margin-top:30px;">
			<!-- 리뷰글 순번 -->
			<input type="hidden" class= "reviewBoardSn" id= "reviewBoardSn" name="reviewBoardSn" value="" style="float: left; width: 89%;" disabled="disabled"><br/><p>
			<!-- 테마명 -->
			<span class="span" style="float: left; width: 10%; margin-top:10px"> 테마명  </span> 
			<%-- <input type="text" class= "subject" id= "subject" name="subject" value="${resultDto.subject}" style="float: left; width: 89%; height:40px; margin-bottom:10px; font-size:14pt;"  disabled="disabled"><br/><p> --%>
			
			<select name=themeNm style="width:200px;height:30px;">
				<option value="0" selected="selected">선택하세요</option>
				<option value="괴담수집가">괴담수집가</option>	
				<option value="악마를 보았다">악마를 보았다</option>	
				<option value="성 안의 이야기">성 안의 이야기</option>	
				<option value="대영어시대">대영어시대</option>	
				<option value="거짓말">거짓말</option>	
				<option value="파노라마">파노라마</option>	
				<option value="사라민">사라민</option>	
				<option value="어린왕자">어린왕자</option>	
			</select>
			
		  	<hr width = "100%" color = "#03D0B7" style="margin-bottom:15px;">
			
			<!-- 닉네임 -->
			<span class="span" style="float: left; width: 10%;"> 닉네임  </span> 
			<input type="text" class= "nickname" id= "nickname" name="nickname" value="${sessionScope.loginfo.nickname}"  style="float: left; width: 89%;font-size:14pt;"disabled="disabled"><br/><p>
			
			<hr width = "100%" color = "#03D0B7" style="margin-bottom:15px;">
			
			<!-- 별점 -->
			<span class="span" id="starScore" style="float: left; width: 10%; margin-top:10px"> 별점 </span> 
			<div style="float: left">
			<input type="radio" class= "scoreStar" id= "scoreStar" name="scoreStar" value="1" font-size:14pt;" checked="checked">1</input>
			<input type="radio" class= "scoreStar" id= "scoreStar" name="scoreStar" value="2" font-size:14pt;">2</input> 
			<input type="radio" class= "scoreStar" id= "scoreStar" name="scoreStar" value="3" font-size:14pt;">3</input> 
			<input type="radio" class= "scoreStar" id= "scoreStar" name="scoreStar" value="4" font-size:14pt;">4</input> 
			<input type="radio" class= "scoreStar" id= "scoreStar" name="scoreStar" value="5" font-size:14pt;">5</input> 
			<input type="radio" class= "scoreStar" id= "scoreStar" name="scoreStar" value="6" font-size:14pt;">6</input> 
			<input type="radio" class= "scoreStar" id= "scoreStar" name="scoreStar" value="7" font-size:14pt;">7</input> 
			<input type="radio" class= "scoreStar" id= "scoreStar" name="scoreStar" value="8" font-size:14pt;">8</input> 
			<input type="radio" class= "scoreStar" id= "scoreStar" name="scoreStar" value="9" font-size:14pt;">9</input> 
			<input type="radio" class= "scoreStar" id= "scoreStar" name="scoreStar" value="10" font-size:14pt;">10</input> 
			</div>		
			
			<hr width = "100%" color = "#03D0B7" style="margin-bottom:15px;">
			
			<!-- 조회수 -->
			<%-- <span class="span" style="float: left; width: 10%;"> 조회수  </span> 
			<input type="text" class= "regDt" id= "regDt" name="regDt" value="${resultDto.readCnt}" style="float: left; width: 89%;font-size:14pt;" disabled="disabled"><br/><p>
			
			<hr width = "100%" color = "#03D0B7" style="margin-bottom:15px;"> --%>
			
			<!-- 리뷰내용 -->
			<span class="span" style="float: left; width: 10%;"> 리뷰내용  </span>
			<textarea id="reviewContent" class= "reviewContent" style="font-size:14pt;float: left; width: 89%;" rows="20" cols="50" name="reviewContent"></textarea>
			<br/><p>
			
			
 		</div><br/>
		<div align="right" width="100%" height="100px" style="margin-top:100px;"></div>
		<div align="right" width="100%" height="100px" style="margin-top:250px;">
<!-- 		<hr width = "100%" color = "#03D0B7" style="margin-bottom:15px;"> -->
			<!-- 등록버튼 -->
			<input type="button" value="등록" id="insertBtn" class="insertBtn" style="border: none; width:50px;
			height:30px;font-size:16px;padding-bottom:12px; padding-top:5px; 
			background-color :#03D0B7; color: white;">
			
			<!-- 취소버튼 -->
			<input type="button" value="취소" id="cancelBtn" class="cancelBtn" style="border: none; width:50px;
			height:30px;font-size:16px;padding-bottom:12px; padding-top:5px; 
			background-color :#03D0B7; color: white;">
			
			<!-- 돌아가기버튼 -->
			<!-- <input type="button" value="돌아가기" id="backBtn" class="backBtn" style="border: none; width:50px;
			height:30px;font-size:16px;padding-bottom:12px; padding-top:5px; 
			background-color :#03D0B7; color: white;" onclick="history.back();">&nbsp; -->
			
		
		</div>
<%-- 		<br/>
		<hr width = "100%" color = "#03D0B7" style="margin-bottom:15px;">
		<!-- 댓글수 -->
		<div class="span" style="font-size:13pt;float: left;font "> 댓글수(${resultDto.replyCnt})  </div> <br> <br>
		<input type="text" class= "replyCnt" id= "replyCnt" name="replyCnt" value="${resultDto.replyCnt}" style="float: left; width: 89%;font-size:14pt;" disabled="disabled"><br/><p>
			
		<!-- 댓글쓰기 작성하는 inputtext-->
		<div align="left" width="100%" height="600px" style="margin-top:10px;">
		<span class="span" style="float: left; width: 10%;"> 댓글쓰기  </span> 
		<input type="text" id= "replyInput" name="replyInput" value="" style="font-size:14pt;float: left; width:79%;height:40px; margin-bottom:15px;" >&nbsp;&nbsp;&nbsp;
		
		<!-- 댓글쓰기 등록버튼 input button-->
		<input type="button" value="댓글쓰기" class="replyRegBtn" id="replyRegBtn" style="margin-left:10px;margin-top:-30px;float: left;border: none; width:100px;
		height:40px;font-size:16px;
		background-color :#03D0B7; color: white;">&nbsp;
		</div>
		
		<!-- 댓글 그리드 -->
		<table class="detailTable" width=100% id="detailTable" style="margin-top:10px;">
			<thead align="left" >
				<tr>
					<th width="100px">댓글쓴닉네임</th>
					<th >댓글내용</th>
					<th width="200px">댓글등록일자</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="replyDto" items="${replyDtoList}">
					<tr>
						<td>${replyDto.nickname}</td>
						<td>${replyDto.contents}</td>
						<td>${replyDto.regDt}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table> --%>
	</div>
</body>
</html>