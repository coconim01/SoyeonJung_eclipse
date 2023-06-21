<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./../common/bootstrap5.jsp"%>
<%@ include file="./../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- CSS -->
<style type="text/css">
	@import url('https://fonts.googleapis.com/css2?family=Dongle:wght@300&display=swap');
	/*body {font-family: 'Dongle', sans-serif;} */
	
	/*나눔고딕*/
	@import url('https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap');

/* 스타일 전체 적용 */
* {
  font-family: 'Nanum Gothic', sans-serif;
  margin: 0;
  padding: 0;
}

/* 테마 제목 */
.thema_Title {
	display: flex;
	align-items: center;
	width: 100%;
	height: 60px;
	background-color: #03D0B7;
	box-shadow: 1px 1px 3px rgba(0, 0, 0, 0.5);
	border-radius: 0.375rem;
}

.h3_theme {
	margin: 0 0 0 30px;
	color: white;
	font-size: 26px;
	font-weight: bolder;
	text-shadow: 1px 1px 3px rgba(0, 0, 0, 0.5);
}

/* 테마 상세 설명 */
.thema_div {
	margin: 20px 30px 0 0;
}

.thema_div>span {
	margin-right: 30px;
}

/* 테마 예약 */
.card {
	display: flex;
	flex-direction: row;
  	align-items: center; 
    position: relative;
    overflow: hidden;
    z-index: 1000;
}

/* 예약 날짜 및 시간 */
.thema_Date {
	position: absolute;
	top: 20px;
	margin-left: 30px;
}

.select-date {
	margin-left: 30px;
}

.time_Area {
	margin-left: 30px;
}

.thema_Time {
	margin: 0 30px 10px 0;
}

/* 날짜, 라디오버튼 글씨 */
label {
	font-size: 18px;
	line-height: 2rem;
}


/* 라디오버튼 스타일*/
.time_Area input[type="radio"] {
	appearance: none;
}

.time_Area input[type="radio"]+label {
	display: inline-block;
	cursor: pointer;
	width: 90px;
	height: 35px;
	line-height: 34px;
	border: 1px solid lightgray;
	border-radius: 0.375rem;
	text-align: center;
	transition: border 0.1s ease-out;
	background-color: white;
	font-weight: bolder;
}

/* 라디오버튼 체크 상태 스타일*/
.time_Area input[type="radio"]:checked+label {
	color: white;
	background-color: #03D0B7;
	border: 1px solid lightgray;
}

/* 라디오버튼 포커스 상태 스타일*/
.time_Area input[type="radio"]:focus-visible+label {
	background-color: rgba(3, 208, 183, 0.5);
	outline-offset: max(2px, 0.1em);
	outline: max(2px, 0.2em) dotted #03D0B7;
}

/* 라디오버튼 마우스 오버 상태 스타일*/
.time_Area input[type="radio"]:hover+label {
	box-shadow: 0 0 0 max(4px, 0.2em) lightgray;
	cursor: pointer;
}

/* 라디오버튼 불가능 상태 스타일*/
.time_Area input[type="radio"]:disabled+label {
	background-color: lightgray;
	box-shadow: none;
	opacity: 0.7;
	cursor: not-allowed;
}


/* 버튼 */
.buttonBox {
	position: absolute;
	right: 20px;
	bottom: 15px;
}

.buttonBox>div {
	display: inline;
}

a {
	text-decoration: none;
}

.mint {
	background-color: #03D0B7;
	border-color: #03D0B7;
}
</style>

<script src="./../myjavascript/jquery.imagezoom.js"></script>
<script type="text/javascript">
/* input에 오늘날짜 기본값으로 넣기 */
	window.onload = function() {
		today = new Date();
		console.log("today.toISOString() >>>" + today.toISOString());
		today = today.toISOString().slice(0, 10);
		console.log("today >>>> " + today);
		bir = document.getElementById("todaybirthday");
		bir.value = today;
	}
	
	function validCheck(){
        
        var radioList = $("input[type='radio']:checked");
        if(radioList.length == 0){
           swal('테마시간을 선택해 주세요.'); 
           return false ;
        }
        
        return true ;
     }
	
<%-- 	var chk = $('input[type=checkbox]:checked').val();
	/* alert(chk); */
	/* location.href="/경로?보낼변수명=" + 값; */
	location.href="<%=notWithFormTag%>rvInsert="+chk; --%>
	
</script>
</head>
<body>
	<div class="container mt-3">
	
		<div class="thema_box">
			<div class="card-title">
				<div class="thema_Title">
					<h3 class="h3_theme">${requestScope.bean.title}</h3>
				</div>
				<div class="thema_div">
					<span>장르: ${requestScope.bean.genre}</span> 
					<span class="level">난이도: ${requestScope.bean.themalevel}</span> 
					<span>추천인원: ${requestScope.bean.recommendpeople}</span>
					<span>소요시간: ${requestScope.bean.usetime}분</span>
					<span>지역: ${requestScope.bean.area}</span>
				</div>
			</div>
			<hr>
			
			<div class="card">
				<div class="thema_image">
					<img src="<%=contextPath%>/image/${requestScope.bean.image01}" alt="">
				</div>
				
				<form class="form" name="myform" action="<%=withFormTag%>" method="post">
					<input type="hidden" name="command" value="rvInsert">
					<input type="hidden" name="productnum" value="${requestScope.bean.productnum}">	
						
					<div class="thema_Date">
						<label for="date">날짜를 선택하세요:
						  	<input class="select-date" type="date"
						         id="todaybirthday"
						         name="revdate"
						         max="2023-12-31"
						         min="2023-01-01"
						         value="">
						</label>
					</div>
		
					<div class="time_Area">
						<input type="radio" name="revtime" id="thema_Time01" value="${requestScope.bean.time01}">
							<label class="checkbox-inline thema_Time" for="thema_Time01">${requestScope.bean.time01}</label>
						<input type="radio" name="revtime" id="thema_Time02" value="${requestScope.bean.time02}">
							<label class="checkbox-inline thema_Time" for="thema_Time02">${requestScope.bean.time02}</label>
						<input type="radio" name="revtime" id="thema_Time03" value="${requestScope.bean.time03}">
							<label class="checkbox-inline thema_Time" for="thema_Time03">${requestScope.bean.time03}</label>
						<input type="radio" name="revtime" id="thema_Time04" value="${requestScope.bean.time04}">
							<label class="checkbox-inline thema_Time" for="thema_Time04">${requestScope.bean.time04}</label>
						<input type="radio" name="revtime" id="thema_Time05" value="${requestScope.bean.time05}">
							<label class="checkbox-inline thema_Time" for="thema_Time05">${requestScope.bean.time05}</label>
					</div>
					<div class="buttonBox">
						<div id="buttonset" class="input-group">
							<button type="button" class="btn btn-secondary" onclick="history.back()">뒤로가기</button>
						</div>
						<div id="buttonset" class="input-group">
							<button type="submit" class="btn btn-dark cart mint" onclick="return validCheck();">예약하기</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>