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
.card {
	margin-left: auto;
	margin-right: auto;
}


</style>
<script src="./../myjavascript/jquery.imagezoom.js"></script>
<script type="text/javascript">
		
	</script>
</head>
<body>
	<div class="container mt-3">
		<div class="thema_box">
			<div class="thema_Title">
				<h3 class="h3_theme">${requestScope.bean.title}&nbsp;&nbsp; 장르 : (${requestScope.bean.genre})</h3>
				<div class="thema_div">
					<span class="level">난이도 : ${requestScope.bean.themalevel} &nbsp;&nbsp;</span> 
					<span> 추천인원 : ${requestScope.bean.recommendpeople} &nbsp;&nbsp; </span>
					<span> 소요시간 : ${requestScope.bean.usetime}분</span>
				</div>
			</div>
			
			<hr>
			
			<div class="thema_image">
				<img src="<%=contextPath%>/image/${requestScope.bean.image01}" alt="">
			</div>
			
			<!-- image-size:408x290 -->
			<div class="time_Area">
				<ul class="reserve_Time">
					<li>
						<a class="thema_TIme"> 
						<button type="button">${requestScope.bean.time01}</button>
						</a>
					</li>
				</ul>
			</div>
		</div>


	</div>
</body>
</html>