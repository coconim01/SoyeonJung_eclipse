<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="com.escape.board.reviewBoard.dao.ReviewBoardDao"%>
<%@ page import="com.escape.board.reviewBoard.model.ReviewBoard"%>
<%@ include file="./../common/common.jsp"%>
<%@ include file="./../common/bootstrap5.jsp"%>
<%
	// TODO: 세션정보에 담긴 로그인 멤버아이디 세팅 해줘야함.
	// 나중에 로그인정보 연결시 다시 세팅.(하드코딩)
	// String loginId = "duddn1870";
	// loginId = request.getParameter("memberId");
	


	//FreeBoardDetailDao 클래스를 선언 조회하하기위해. 다오안에 조회메서드가 있음
	// FreeBoard클래스를 선언

	ReviewBoardDao dao = new ReviewBoardDao();
	System.out.println("1");
	List<ReviewBoard> reviewBoardList = dao.selectAll();
	System.out.println("2");
	request.setAttribute("reviewBoardList", reviewBoardList);
	
	
	//String loginId = "a123";
	//request.setAttribute("loginId", loginId);
	//int length = reviewBoardList.size();
	//System.out.println("3");
	
%>
<!--id 가져다쓰기  ${sessionScope.loginfo.id}-->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<!-- jquery를 사용할 수 있게 연결해주는 링크 -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
	<!-- js로 연결해주는 링크 -->
	<script type="text/javascript" src="./reviewBoard.js"></script>
	<!-- css로 연결해주는 링크 -->
	<link rel="stylesheet" href="./reviewBoard.css">
	<!-- ajax를 사용할 수 있게 연결해주는 링크 -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<div class="container mt-3 background1" align="center">
		<br/>
		<h1 align="left" style="color:#03D0B7; margin-bottom: 10px;">탈출의민족_리뷰게시판</h1>
		<div align="left" style="margin-bottom: -10px;">리뷰 게시판입니다.</div>
		<br>
		<br>
		<!-- 로그인한 회원아이디 숨김  -->
		<input type="hidden" id="loginId" value="${sessionScope.loginfo.id}"></input>
<%-- 		<input type="hidden" id="loginId" value="${sessionScope.loginfo.id}"></input> --%>
		<!-- 리뷰게시판 순번 -->
		<input type="hidden" id="reviewSn" value=""></input>
		
		<div align="right">
			<a>장르 검색</a>
			<select name=genreCd style="width:200px;height:30px;">
				<option value="0" selected="selected">전체</option>
				<option value="1">공포</option>	
				<option value="2">스릴러</option>	
				<option value="3">코믹</option>	
				<option value="4">역사</option>	
				<option value="5">판타지</option>	
				<option value="6">감동</option>	
				<option value="7">SF</option>	
				<option value="8">동화</option>	
				<option value="9">야외</option>	
			</select>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			
			<a>테마 검색</a>
			<select name=themeCd style="width:200px;height:30px;">
				<option value="0" selected="selected">전체</option>
				<option value="괴담수집가">괴담수집가</option>	
				<option value="악마를 보았다">악마를 보았다</option>	
				<option value="성 안의 이야기">성 안의 이야기</option>	
				<option value="대영어시대">대영어시대</option>	
				<option value="거짓말">거짓말</option>	
				<option value="파노라마">파노라마</option>	
				<option value="사라민">사라민</option>	
				<option value="어린왕자">어린왕자</option>	
			</select>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			
			<a>평점순 검색</a>
			<select name=scoreOrderBy style="width:200px;height:30px;">
				<option value="0" selected="selected">선택하세요</option>
				<option value="1">평점높은순</option>	
				<option value="2">평점낮은순</option>	
			</select>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			
			<a>검색조건</a>
			<select name=srchTextGubun style="width:200px;height:30px;">
				<option value="0" selected="selected">전체</option>
				<option value="1">작성자</option>	
				<option value="2">리뷰내용</option>	
			</select>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			
			<input type="text" id="srchData" name="srchText" style="width:170px;height:25px;">
			<input type="button" value="조회" class="selectBtn" style="border: none; width:50px;height:30px;font-size:16px;padding-bottom:0px; padding-top:0px; background-color :#03D0B7; color: white;">
		</div>		
		
		<table id="reviewTable_id"class="table" width="100%" style="margin-top:10px">
			<thead>
				<tr> 
					<th>NO</th>
					<th>장르명</th>
					<th>테마명</th>
					<th width="600px">리뷰내용</th>
					<th>닉네임</th>
					<th>리뷰 점수</th>
					<th>등록일자</th>
				</tr>
			</thead>
			<tbody id="tbody_id">
			<c:forEach var="reviewBoard" items="${reviewBoardList}">
				<tr id="reviewList" value=""> 
					<td style="display: none;type="hidden">${reviewBoard.reviewSn}</td>
					<td style="display: none;type="hidden">${reviewBoard.themeGenre}</td>
					<td style="display: none;type="hidden">${reviewBoard.memberId}</td>
					<td style="display: none;type="hidden">${reviewBoard.themeSn}</td>
					<td>${reviewBoard.rowNum}</td>
					<td>${reviewBoard.themeGenreNm}</td>
					<td>${reviewBoard.themeName}</td>
					<td width="600px">${reviewBoard.reviewThemeContents}</td>
					<td>${reviewBoard.nickname}</td>
					<td>${reviewBoard.reviewThemeScore}</td>
					<td>${reviewBoard.regDt}</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	
	<div align="right">
	<!-- 리뷰작성버튼 -->
	<input type="button" value="리뷰작성" id="reviewRegBtn" class="reviewRegBtn" style="border: none; width:75px;
	height:25px;font-size:16px;padding-bottom:0px; padding-top:0px; margin-top:10px;
	background-color :#03D0B7; color: white;">
	</div>	
	</div>	
</body>
</html>