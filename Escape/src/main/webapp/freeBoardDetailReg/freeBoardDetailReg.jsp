<!-- 
 페이지명 : 자유게시판 글쓰기 페이지 JSP
 작성일자 : 2023-03-07
 작성자 : 정영우
-->

<%@page import="java.util.ArrayList"%>
<%@page import="com.escape.board.freeBoard.dao.FreeBoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="com.escape.board.freeBoard.dao.FreeBoardDetailDao"%>
<%@ page import="com.escape.board.freeBoard.model.FreeBoard"%>

<%
	// 로그인한 memberId 값 받아오기(임시로 하드코딩) memberId:a123, nickname:홍길동
	String memberId = request.getParameter("loginId");
	FreeBoardDetailDao dao = new FreeBoardDetailDao();
	String nickName = dao.getNickname(memberId);   
	System.out.println("nickName 가져왔나(자바 -> jsp)::" + nickName);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- jquery를 사용할 수 있게 연결해주는 링크 -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
	<!-- js로 연결해주는 링크 -->
	<script type="text/javascript" src="./freeBoardDetailReg.js"></script>
	<!-- css로 연결해주는 링크 -->
	<link rel="stylesheet" href="./freeBoardDetailReg.css">
	<!-- ajax를 사용할 수 있게 연결해주는 링크 -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<div class="container mt-3 background1" align="center">
		<br/>
		<h1 align="left" style="color:#03D0B7; margin-bottom: 10px;">자유게시판 글쓰기 페이지</h1>
		<div class = "dtlsubject"align="left" style="margin-bottom: -10px;">자유게시판에서 글쓰기 버튼을 누르면 글을 쓸 수 있는 페이지.</div>
		<br/>
 		<div align="left" width="100%" height="600px" style="margin-top:30px;">
 			<!-- 로그인한 아이디(숨김) -->                 
			<input type="hidden" class= "memberId" id= "memberId" name="memberId" value="<%=memberId%>" style="float: left; width: 89%;" disabled="disabled"><br/><p>
			<!-- 제목 -->
			<span class="span" style="float: left; width: 10%; "> 제목  </span> 
			<input type="text" class= "subject" id= "subject" name="subject" value="" style="float: left; width: 89%;"><br/><p>
			<!-- 닉네임 -->
			<span class="span" style="float: left; width: 10%;"> 닉네임  </span> 
			<input type="text" class= "nickname" id= "nickname" name="nickname" value="<%=nickName%>" style="float: left; width: 89%;"readonly="readonly"><br/><p>
			<!-- 내용 -->
			<span class="span" style="float: left; width: 10%;"> 내용  </span>
			<textarea id="content" class= "content" style="float: left; width: 89%;" rows="20" cols="50" name="content"></textarea>
			<br/><p>
 		</div><br/>
		<div align="right" width="100%" height="100px" style="margin-top:100px;"></div>
		<div align="right" width="100%" height="100px" style="margin-top:250px;">
		
			<!-- 등록버튼 -->
			<input type="button" value="등록" id="insertBtn" class="insertBtn" style="border: none; width:50px;
			height:30px;font-size:16px;padding-bottom:12px; padding-top:5px; 
			background-color :#03D0B7; color: white;">
			
			
			<!-- 취소버튼 -->
			<input type="button" value="취소" id="cancelBtn" class="cancelBtn" style="border: none; width:50px;
			height:30px;font-size:16px;padding-bottom:12px; padding-top:5px; 
			background-color :#03D0B7; color: white;">
			
	</div>
</body>
</html>