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
	span {
	font-size:1.2rem;
	}
	
	.dddmain_header{
	background-color:LightGray;
	border-top-left-radius: 2em 0.5em;
	border-top-right-radius: 1em 3em;
	border-bottom-right-radius: 4em 0.5em;
	border-bottom-left-radius: 1em 3em;
	}
	
  .Button{
	border: none; 
	width:100px; 
	height:30px;
	font-size:16px;
	padding-bottom:12px; 
	padding-top:5px; 
	background-color :#03D0B7; 
	color: white; 
	font-weight:500;
	border-radius: 3px;
	font-family: 'Nanum Gothic', sans-serif;
	font-weight:bolder;   
	text-decoration: none;
	} /* 민트 컬러칩 #03D0B7 */
  
	</style>
</head>
<body>
	<div class="container mt-5">
		<div class="row">
    		<div class="col-sm-2"></div>
    		<div class="col-sm-8">
				<h2>
					${sessionScope.loginfo.nickname}(${sessionScope.loginfo.id})
					님의 예약 상세 내역
				</h2>
				<p>
					${sessionScope.loginfo.nickname} 고객님이 ${reservTimes.purchasedate}에 
					예약하신 상품에 대한 세부 결제 내역입니다.
				</p>
				
				<br/>
				<h3 class="main_header"></h3>		
				
				<table class="table table-striped">
					<thead>
						<tr align="center">
							<th>예약번호</th>
							<th>방탈출</th>
							<th>이미지</th>
							<th>단가</th>
							<th>인원수</th>
							<th>날짜</th>
							<th>시간</th>
							<th>가격</th>
							
						
						</tr>
					</thead>
					<tbody>
						<%-- 변수 : totalAmount(총금액) --%>
						<c:set var="totalAmount" value="0"/>
						
						<c:forEach var="bean" items="${requestScope.lists}">
						<tr align="center">
							<td valign="middle">${bean.timenum}</td>
							<td valign="middle">${bean.title}</td>
							
							<td valign="middle">
								<img alt="${bean.image01}" width="100" height="150" 
									src="<%=contextPath%>/image/${bean.image01}">
							</td>
							<td valign="middle">
								<fmt:formatNumber value="${bean.price}" pattern="###,###"/> 원						
							</td>
							<td valign="middle">${bean.people} 명</td>
							<td valign="middle">${bean.reservdate}</td>
							<td valign="middle">${bean.reservtime}</td>
							<td valign="middle">
								<c:set var="amount" value="${bean.price*bean.people}"/>
								<fmt:formatNumber value="${amount}" pattern="###,###"/> 원						
							</td>
							<td>
							<a href="<%=notWithFormTag %>rvDelete&timenum=${bean.timenum}&id=${bean.mid}" class="Button" onclick="return reserveDelete()">예약취소하기</a>
							</td>
						</tr>
						</c:forEach>									
					</tbody>
				</table>    		
    		</div>
    		<div class="col-sm-2"></div>
  		</div>		
	</div>
		<script type="text/javascript">
		function reserveDelete() {
			var msg = confirm(" 예약을 취소하시겠습니까?")
			
			if (msg == true) {		//확인 버튼 클릭 시
				alert(" 예약이 취소되었습니다.")
			
			}else {		//취소 버튼 클릭 시
				return false;
			}
		}
		</script>
</body>
</html>

