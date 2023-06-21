<!-- 
 페이지명 : 자유게시판 상세페이지 JSP
 작성일자 : 2023-03-05
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
<%@ include file="./../common/common.jsp"%>
<%@ include file="./../common/bootstrap5.jsp"%>
<%

		
	// FreeBoardDetailDao 클래스를 선언 조회하하기위해. 다오안에 조회메서드가 있음
	FreeBoardDetailDao freeBoardDetailDao = new FreeBoardDetailDao();
	// FreeBoard클래스를 선언
	FreeBoard freeBoard = new FreeBoard();
	
	
	// 게시글 조회수의 값 받아오기
	String readCnt = request.getParameter("freeBoardSn");
	int resultReadCnt = freeBoardDetailDao.readCntFreeBoardDetailDto(readCnt);
	request.setAttribute("resultReadCnt", resultReadCnt);
	System.out.println("조회수 찍히나:" + resultReadCnt);
	
	
	// 게시글 순번의 값 받아오기
	String freeBoardSn = request.getParameter("freeBoardSn");
	
	// 로그인한 사람의 아이디
	// String loginId = request.getParameter("loginId");
	// request.setAttribute("loginId", loginId);
	
	System.out.println("순번 찍히나:" + freeBoardSn);
	// System.out.println("아이디 찍히나:" +loginId);

	


	// 게시글 순번을 가지고 sql조회를 한 후 dto를 받아와서 화면에 값을 뿌린다.
	
	/***********************
	 * 게시글 조회 - 단건(하나의 row) 왜 조회해? 화면에 뿌릴려고. 어디에? jsp 태그안에 value 하나씩 뿌릴거야.그래서 가져온거야.
	 ***********************/
	FreeBoard resultDto = freeBoardDetailDao.getFreeBoardDetail(freeBoardSn);
	
	System.out.println("resultDto.getSubject():::::" + resultDto.getSubject() );
	System.out.println("resultDto.getFreeBoardSn():::::" + resultDto.getFreeBoardSn() );
	System.out.println("resultDto.getNickname():::::" + resultDto.getNickname() );
	System.out.println("resultDto.getRegDt():::::" + resultDto.getRegDt() );
	System.out.println("resultDto.getContents():::::" + resultDto.getContents() );
	System.out.println("resultDto.getReplyCnt():::::" + resultDto.getReplyCnt() );
	System.out.println("resultDto.getMemberId():::::" + resultDto.getMemberId() );
	// jsp에서 쓸수있는 변수(resultDto) 선언  
	request.setAttribute("resultDto", resultDto);
	
	
	// 게시글 순번을 가지고 sql을 조회한 후 List<dto>를 받아와서 댓글 테이블에 값을 뿌린다.
	List<FreeBoard> replyDtoList = new ArrayList<FreeBoard>();
	
	
	/***********************
	 * 댓글 조회 - 다건(여러개의 row)
	 ***********************/
	replyDtoList = freeBoardDetailDao.getFreeBoardDetailReplyList(freeBoardSn);
	System.out.println("댓글리스트 길이 ::"+replyDtoList.size());
	System.out.println("다건(댓글) 셀렉트하고 돌아옴2" );
	
	
	/************************************************************
	* request에 replyDtoList라는 이름으로 갖다씀*jsp 태그에서. java 변수는 ${replyDtoList} 이렇게 써야 jsp에서 값을 사용할 수 있다.
	************************************************************/
	request.setAttribute("replyDtoList", replyDtoList);
	
	
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- jquery를 사용할 수 있게 연결해주는 링크 -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
	<!-- js로 연결해주는 링크 -->
	<script type="text/javascript" src="./freeBoardDetail.js"></script>
	<!-- css로 연결해주는 링크 -->
	<link rel="stylesheet" href="./freeBoardDetail.css">
	<!-- ajax를 사용할 수 있게 연결해주는 링크 -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<div class="container mt-3 background1" align="center">
		<br/>
		<h1 align="left" style="color:#03D0B7; margin-bottom: 10px;">탈출의민족_자유게시판 상세페이지</h1>
		<div class = "dtlsubject"align="left" style="margin-bottom: -10px;">게시물을 등록, 수정하고 댓글을 달 수 있습니다.</div>
		<br/>
		<!-- 로그인한 회원아이디 숨김 -->
		<input type="hidden" id="loginId" value="${sessionScope.loginfo.id}"></input>
		<!-- 작성자 아이디 -->
		<input type="hidden" id="writerId" value="${resultDto.memberId}"></input>
 		<div align="left" width="100%" height="600px" style="margin-top:30px;">
			<!-- 게시글 순번 -->
			<input type="hidden" class= "freeBoardSn" id= "freeBoardSn" name="freeBoardSn" value="${resultDto.freeBoardSn}" style="float: left; width: 89%;" disabled="disabled"><br/><p>
			<!-- 제목 -->
			<span class="span" style="float: left; width: 10%; margin-top:10px"> 제목  </span> 
			<input type="text" class= "subject" id= "subject" name="subject" value="${resultDto.subject}" style="float: left; width: 89%; height:40px; margin-bottom:10px; font-size:14pt;"  disabled="disabled"><br/><p>
			
		  	<hr width = "100%" color = "#03D0B7" style="margin-bottom:15px;">
			
			<!-- 닉네임 -->
			<span class="span" style="float: left; width: 10%;"> 닉네임  </span> 
			<input type="text" class= "nickname" id= "nickname" name="nickname" value="${resultDto.nickname}"  style="float: left; width: 89%;font-size:14pt;"disabled="disabled"><br/><p>
			
			<hr width = "100%" color = "#03D0B7" style="margin-bottom:15px;">
			
			<!-- 등록일자 -->
			<span class="span" style="float: left; width: 10%;"> 등록일자  </span> 
			<input type="text" class= "regDt" id= "regDt" name="regDt" value="${resultDto.regDt}" style="float: left; width: 89%;font-size:14pt;" disabled="disabled"><br/><p>
			
			<hr width = "100%" color = "#03D0B7" style="margin-bottom:15px;">
			
			<!-- 조회수 -->
			<span class="span" style="float: left; width: 10%;"> 조회수  </span> 
			<input type="text" class= "regDt" id= "regDt" name="regDt" value="${resultDto.readCnt}" style="float: left; width: 89%;font-size:14pt;" disabled="disabled"><br/><p>
			
			<hr width = "100%" color = "#03D0B7" style="margin-bottom:15px;">
			
			<!-- 내용 -->
			<span class="span" style="float: left; width: 10%;"> 내용  </span>
			<textarea id="content" class= "content" style="font-size:14pt;float: left; width: 89%;" rows="20" cols="50" name="content" disabled="disabled">${resultDto.contents}</textarea>
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
		<br/>
		<hr width = "100%" color = "#03D0B7" style="margin-bottom:15px;">
		<!-- 댓글수 -->
		<div class="span" style="font-size:13pt;float: left;font "> 댓글수(${resultDto.replyCnt})  </div> <br> <br>
<%-- 		<input type="text" class= "replyCnt" id= "replyCnt" name="replyCnt" value="${resultDto.replyCnt}" style="float: left; width: 89%;font-size:14pt;" disabled="disabled"><br/><p> --%>
			
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
		</table>
	</div>
</body>
</html>