<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./../common/bootstrap5.jsp"%>
<%@ include file="./../common/common.jsp"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style type="text/css">
		<!-- css로 연결해주는 링크 -->
	<link rel="stylesheet" href="./freeBoardDetail.css">
	@import url('https://fonts.googleapis.com/css2?family=Dongle:wght@300&display=swap');
	/*body {font-family: 'Dongle', sans-serif;} */
	/*나눔고딕*/
	@import url('https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap');
	span {font-size:1.2rem;}
	
	.detailButton{border: none; width:100px; height:30px;font-size:16px;padding-bottom:12px; padding-top:5px; 
				  background-color :#03D0B7; color: white; font-weight:500;border-radius: 3px;
				  font-family: 'Nanum Gothic', sans-serif;font-weight:bolder;   } /* 민트 컬러칩 #03D0B7 */
	</style>
</head>
<body>
	<div class="container mt-3">
		<h2>
			${requestScope.id}
			님의 예약 정보
		</h2>
		<p>나의 예약내역을 최근 날짜부터 보여줍니다.</p>
		<table class="table table-striped">
			<thead>
				<tr align="center">
					<th>예약 번호</th>
					<th>구매 일자</th>
					<th>상세 보기</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="bean" items="${requestScope.lists}">
				<tr align="center">
					<td>${bean.timenum}</td>
					<td>${bean.purchasedate}</td>
					
					<td >
					<div id="detailButton">
						<a href="<%=notWithFormTag%>rvDetail&timenum=${bean.timenum}">
						<input type="button" value="상세보기" id="detailButton" onclick="history.back();" class="detailButton" >
							
						</a>			
					</div>				
					</td>
					
				</tr>					
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>