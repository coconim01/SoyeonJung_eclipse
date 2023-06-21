<!-- 
 페이지명 : 리뷰게시판 상세페이지 JSP
 작성일자 : 2023-03-14
 작성자 : 정영우
-->

<%@page import="java.util.ArrayList"%>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.escape.board.reviewBoard.dao.ReviewBoardDetailDao"%>
<%@ page import="com.escape.board.reviewBoard.model.ReviewBoard" %>
<%@ include file="./../common/common.jsp"%>
<%@ include file="./../common/bootstrap5.jsp"%>

<% 
	// 로그인아이디의 값 받아오기
	//String loginId = request.getParameter("loginId");
	//request.getParameter("loginId");
	//request.setAttribute("loginId", loginId);
	
	// 리뷰게시글 순번의 값 받아오기
	String reviewSn = request.getParameter("reviewSn");
	request.getParameter("reviewSn");
	
	request.setAttribute("reviewSn", reviewSn);
	
	ReviewBoard reviewBoard = new ReviewBoard();
	
	ReviewBoardDetailDao dao = new ReviewBoardDetailDao();
	
	ReviewBoard dto = dao.getReviewBoardDetail(reviewSn);
	
	
	request.setAttribute("dto", dto);
	
	
	
	
	
	
	
%>	
	


<!DOCTYPE html>
<html><!--id 가져다쓰기  ${sessionScope.loginfo.id}-->
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- jquery를 사용할 수 있게 연결해주는 링크 -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
	<!-- js로 연결해주는 링크 -->
	<script type="text/javascript" src="./reviewBoardDetail.js"></script>
	<!-- css로 연결해주는 링크 -->
	<link rel="stylesheet" href="./reviewBoardDetail.css">
	<!-- ajax를 사용할 수 있게 연결해주는 링크 -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<div class="container mt-3 background1" align="center">
		<br/>
		<h1 align="left" style="color:#03D0B7; margin-bottom: 10px;">탈출의민족_리뷰게시판 상세페이지</h1>
		<div class = "dtlsubject"align="left" style="margin-bottom: -10px;">리뷰를 등록, 수정할 수 있습니다.</div>
		<br/>
		<!-- 로그인한 회원아이디 숨김 -->
<%-- 		<input type="hidden" id="loginId" value="${sessionScope.loginfo.id}"></input> --%>
		<input type="hidden" id="loginId" value="${sessionScope.loginfo.id}"></input>
		<!-- 작성자 아이디 -->
		<input type="hidden" id="writerId" value="${dto.memberId}"></input>
 		<div align="left" width="100%" height="600px" style="margin-top:30px;">
			<!-- 리뷰글 순번 -->
			<input type="hidden" class= "reviewBoardSn" id= "reviewBoardSn" name="reviewBoardSn" value="${reviewSn}" style="float: left; width: 89%;" disabled="disabled"><br/><p>
			<!-- 테마명 -->
			<span class="span"  style="float: left; width: 10%; margin-top:10px"> 테마명  </span> 
			<%-- <input type="text" class= "subject" id= "subject" name="subject" value="${resultDto.subject}" style="float: left; width: 89%; height:40px; margin-bottom:10px; font-size:14pt;"  disabled="disabled"><br/><p> --%>
			
			<!-- 리뷰게시글 목록에서 상세페이지 갔을때 보여지는 테마명 -->
			<select id="themeNm" value="" name="themeNm" style="width:200px;height:30px;" readonly="readonly">
				<option value="${dto.themeName}" selected="selected" disabled="disabled">${dto.themeName}</option>	
			</select>
			
		  	<hr width = "100%" color = "#03D0B7" style="margin-bottom:15px;">
			
			<!-- 닉네임 -->
			<span class="span" style="float: left; width: 10%;"> 닉네임  </span> 
			<input type="text" class= "nickname" id= "nickname" name="nickname" value="${dto.nickname}" style="float: left; width: 89%;font-size:14pt;"disabled="disabled"><br/><p>
			
			<hr width = "100%" color = "#03D0B7" style="margin-bottom:15px;">
			
			<!-- 별점 -->
			<span class="span" id="starScore" style="float: left; width: 10%; margin-top:10px"> 별점 </span> 
			<div id = "scoreStar" style="float: left">																			
				<span style="font-size:30pt;">1</span><input type="radio" class= "scoreStar" id= "scoreStar" name="scoreStar" value="1"  disabled="disabled" <c:if test ="${dto.reviewThemeScore eq '1'}">checked</c:if>  style="font-size:20pt; width:30px;height:30px">
				<span style="font-size:30pt;">2</span><input type="radio" class= "scoreStar" id= "scoreStar" name="scoreStar" value="2" disabled="disabled" <c:if test ="${dto.reviewThemeScore eq '2'}">checked</c:if>  style="font-size:20pt; width:30px;height:30px">
				<span style="font-size:30pt;">3</span><input type="radio" class= "scoreStar" id= "scoreStar" name="scoreStar" value="3" disabled="disabled" <c:if test ="${dto.reviewThemeScore eq '3'}">checked</c:if>  style="font-size:20pt; width:30px;height:30px">
				<span style="font-size:30pt;">4</span><input type="radio" class= "scoreStar" id= "scoreStar" name="scoreStar" value="4" disabled="disabled" <c:if test ="${dto.reviewThemeScore eq '4'}">checked</c:if>  style="font-size:20pt; width:30px;height:30px">
				<span style="font-size:30pt;">5</span><input type="radio" class= "scoreStar" id= "scoreStar" name="scoreStar" value="5" disabled="disabled" <c:if test ="${dto.reviewThemeScore eq '5'}">checked</c:if>  style="font-size:20pt; width:30px;height:30px">
				<span style="font-size:30pt;">6</span><input type="radio" class= "scoreStar" id= "scoreStar" name="scoreStar" value="6" disabled="disabled" <c:if test ="${dto.reviewThemeScore eq '6'}">checked</c:if>  style="font-size:20pt; width:30px;height:30px">
				<span style="font-size:30pt;">7</span><input type="radio" class= "scoreStar" id= "scoreStar" name="scoreStar" value="7" disabled="disabled" <c:if test ="${dto.reviewThemeScore eq '7'}">checked</c:if>  style="font-size:20pt; width:30px;height:30px">
				<span style="font-size:30pt;">8</span><input type="radio" class= "scoreStar" id= "scoreStar" name="scoreStar" value="8" disabled="disabled" <c:if test ="${dto.reviewThemeScore eq '8'}">checked</c:if>  style="font-size:20pt; width:30px;height:30px">
				<span style="font-size:30pt;">9</span><input type="radio" class= "scoreStar" id= "scoreStar" name="scoreStar" value="9" disabled="disabled" <c:if test ="${dto.reviewThemeScore eq '9'}">checked</c:if>  style="font-size:20pt; width:30px;height:30px">
			    <span style="font-size:30pt;">10</span><input type="radio" class= "scoreStar" id= "scoreStar" name="scoreStar" value="10" disabled="disabled" <c:if test ="${dto.reviewThemeScore eq '10'}">checked</c:if> style="font-size:20pt ; width:30px;height:30px">
			</div>		
			<hr width = "100%" color = "#03D0B7" style="margin-bottom:15px;">
			
			<!-- 조회수 -->
			<%-- <span class="span" style="float: left; width: 10%;"> 조회수  </span> 
			<input type="text" class= "regDt" id= "regDt" name="regDt" value="${resultDto.readCnt}" style="float: left; width: 89%;font-size:14pt;" disabled="disabled"><br/><p>
			
			<hr width = "100%" color = "#03D0B7" style="margin-bottom:15px;"> --%>
			
			<!-- 리뷰내용 -->
			<span class="span" style="float: left; width: 10%;"> 리뷰내용  </span>
			<textarea id="reviewContent" class= "reviewContent" style="font-size:14pt;float: left; width: 89%;" rows="20" cols="50" name="reviewContent" readonly="readonly" >${dto.reviewThemeContents}</textarea>
			<br/><p>
			
			
 		</div><br/>
		<div align="right" width="100%" height="100px" style="margin-top:100px;"></div>
		<div align="right" width="100%" height="100px" style="margin-top:250px;">
<!-- 		<hr width = "100%" color = "#03D0B7" style="margin-bottom:15px;"> -->
			<!-- 등록버튼 -->
			<input type="hidden" value="등록" id="insertBtn" class="insertBtn" style="border: none; width:50px;
			height:30px;font-size:16px;padding-bottom:12px; padding-top:5px; 
			background-color :#03D0B7; color: white;">
			
			
			<!-- 수정버튼 -->
			<input type="button" value="수정" id="updateBtn" class="updateBtn" style="border: none; width:50px;
			height:30px;font-size:16px;padding-bottom:12px; padding-top:5px; 
			background-color :#03D0B7; color: white;">
			
			
			<!-- 삭제버튼 -->
			<input type="button" value="삭제" id="deleteBtn" class="deleteBtn" style="border: none; width:50px;
			height:30px;font-size:16px;padding-bottom:12px; padding-top:5px; 
			background-color :#03D0B7; color: white;">&nbsp;
			
			<!-- 돌아가기버튼 -->
			<!-- <input type="button" value="돌아가기" id="backBtn" class="backBtn" style="border: none; width:50px;
			height:30px;font-size:16px;padding-bottom:12px; padding-top:5px; 
			background-color :#03D0B7; color: white;" onclick="history.back();">&nbsp; -->
			
		</div>
	</div>
</body>
</html>