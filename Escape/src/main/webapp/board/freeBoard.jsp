<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="com.escape.board.freeBoard.dao.FreeBoardDao"%>
<%@ page import="com.escape.board.freeBoard.model.FreeBoard"%>
<%
	// TODO: 세션정보에 담긴 로그인 멤버아이디 세팅 해줘야함.
	// 나중에 로그인정보 연결시 다시 세팅.(하드코딩)
	String loginId = "duddn1870";
// 	String loginId = request.getParameter('memberId');
	


//FreeBoardDetailDao 클래스를 선언 조회하하기위해. 다오안에 조회메서드가 있음
	// FreeBoard클래스를 선언

	FreeBoardDao dao = new FreeBoardDao();

	List<FreeBoard> freeBoardList = dao.selectAll();
	request.setAttribute("freeBoardList", freeBoardList);
	request.setAttribute("loginId", loginId);
	int length = freeBoardList.size();
	
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<!-- jquery를 사용할 수 있게 연결해주는 링크 -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
	<!-- js로 연결해주는 링크 -->
	<script type="text/javascript" src="./freeBoard.js"></script>
	<!-- css로 연결해주는 링크 -->
	<link rel="stylesheet" href="./freeBoard.css">
	<!-- ajax를 사용할 수 있게 연결해주는 링크 -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<div class="container mt-3 background1" align="center">
		<br/>
		<h1 align="left" style="color:#03D0B7; margin-bottom: 10px;">탈출의민족_자유게시판</h1>
		<div align="left" style="margin-bottom: -10px;">자유롭게 글쓰는 게시판입니다.</div>
		
		<!-- 로그인한 회원아이디 숨김 -->
		<input type="hidden" id="loginId" value="${loginId}"></input>
		
		<div align="right">
			<a>게시글 검색조건</a>
			<select name=kindCd style="width:200px;height:30px;">
				<option value="0" selected="selected">전체</option>
				<option value="1">제목 + 내용</option>	
				<option value="2">작성자</option>	
			
			</select>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			
		<a>게시글 조회 : </a>
		<input type="text" id="srchData" name="srchText" style="width:170px;height:25px;">
		<input type="button" value="조회" class="selectBtn" style="border: none; width:50px;
			height:30px;font-size:16px;padding-bottom:0px; padding-top:0px; 
			background-color :#03D0B7; color: white;">
		</div>		
		<table id="table_id"class="table" width="100%" style="margin-top:10px">
			<thead>
			
				<tr> 
					<th>NO</th>
					<th width="600px">제목</th>
					<th>닉네임</th>
					<th>등록일자</th>
					<th>조회수</th>
					<th>댓글수</th>
				</tr>
			</thead>
			<tbody id="tbody_id">
			<c:forEach var="freeBoard" items="${freeBoardList}">
				<tr id="freeBoardList" value=""> 
					<td style="display: none;type="hidden">${freeBoard.memberId}</td>
					<td style="display: none;type="hidden">${freeBoard.freeBoardSn}</td>
					<td>${freeBoard.no}</td>
					<td width="600px">${freeBoard.subject}(${freeBoard.replyCnt})</td>
					<td>${freeBoard.nickname}</td>
					<td>${freeBoard.regDt}</td>
					<td>${freeBoard.readCnt}</td>
					<td>${freeBoard.replyCnt}</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		<div align="right">
			<!-- 글쓰기버튼 -->
			<input type="button" value="글작성" id="regBtn" class="regBtn" style="border: none; width:75px;
			height:25px;font-size:16px;padding-bottom:0px; padding-top:0px; margin-top:10px;
			background-color :#03D0B7; color: white;">
		</div>	
	</div>
</body>
</html>