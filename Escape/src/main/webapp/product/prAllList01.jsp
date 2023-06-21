<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="./../common/bootstrap5.jsp"%>
<%@ include file="./../common/common.jsp"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<script type="text/javascript">
		$(document).ready(function(){ 
			
		});
		
		function searchAll(){ /* 전체 검색 */
			location.href = '<%=notWithFormTag%>prAllList' ;
		}
		
	</script>

	<!-- Semantic UI(간결하고 직관적인 CSS 프레임워크) https://semantic-ui.com/ -->
<link rel="stylesheet" type="text/css" href="semantic/dist/semantic.min.css">
<script
  src="https://code.jquery.com/jquery-3.1.1.min.js"
  integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
  crossorigin="anonymous"></script>
<script src="semantic/dist/semantic.min.js"></script>

<!-- CSS -->
<style type="text/css">
	@import url('https://fonts.googleapis.com/css2?family=Dongle:wght@300&display=swap');
	/*body {font-family: 'Dongle', sans-serif;} */
	
	/*나눔고딕*/
	@import url('https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap');

/* 카드 각각을 감싸고 있는 div */
.card {
    display: flex;
    flex-direction: row;
    justify-content: center;
    position: relative;
    overflow: hidden;
}

/* 대표이미지 크기 */
#pic {
	width: 18rem;
	float: left;
	box-sizing: border-box;
}

#pic>img{
	width: 100%;
}

/* 테마 설명란 */
#themeinfo {
float: left;
box-sizing: border-box;
}

/* 테마 제목 */
.card-title-wrap {
	width: 1080px;
	height: 60px;
	position: relative;
	background-color: #03D0B7;
	box-shadow: 1px 3px 3px rgba(0, 0, 0, 0.5);
}

#title {
	color: white;
	margin-left: 15px;
	font-size: 26px;
	font-weight: bolder;
	text-shadow: 1px 1px 3px rgba(0, 0, 0, 0.5);
}

/* 테마 세부정보 */
.themedetail, .themetext {
	border-bottom: 1px solid #03D0B7;
}

.card-body:last-child {
	border-bottom: none;
}

.card-text {
	margin-left: 15px;
	margin-right: 30px;
}

.themedetail {
	display: flex;
	padding-bottom: 0;
}

#themetext {
	height: 150px;
}

#price {
	font-size: 24px;
	font-weight: bolder;
	
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
</style>
</head>
<body>
	<div class="container mt-3">
	
	  <table class="table table-borderess">
         <thead>
         </thead>
         <tbody>
         	<tr>
				<td align="center">			
					<form name="myform" action="<%=withFormTag%>" method="get">
						<div class="row">
						<input type="hidden" name="command" value="prAllList01">
						
						<div class="col">							
							<select class="form-control" id="modeGenre" name="modeGenre">
								<option value="genreAll" selected="selected">-- 장르 --
								<option class="th_genre" value="공포">공포</option>
							  	<option class="th_genre" value="스릴러">스릴러</option>
							  	<option class="th_genre" value="코믹"> 코믹</option>
							  	<option class="th_genre" value="역사">역사</option>
							  	<option class="th_genre" value="판타지">판타지</option>
							  	<option class="th_genre" value="감동">감동</option>
							  	<option class="th_genre" value="SF">SF</option>
							  	<option class="th_genre" value="동화">동화</option>
							  	<option class="th_genre" value="야외">야외</option>
							</select>
						</div>
						
						<div class="col">							
							<select class="form-control" id="modeLevel" name="modeLevel">
								<option value="levelAll">-- 난이도 --</option>
								<option class="th_level" value="1">1</option>
							  	<option class="th_level" value="2">2</option>
							  	<option class="th_level" value="3">3</option>
							  	<option class="th_level" value="4">4</option>
							  	<option class="th_level" value="5">5</option>
							  	<option class="th_level" value="6">6</option>
							</select>
						</div>
						
						<div class="col">							
							<select class="form-control" id="modeLevel" name="modeLevel">
							<option value="areaAll">-- 지역 --</option>
							<option class="th_area" value="홍대">홍대</option>
					  		<option class="th_area" value="강남">강남</option>
					  		<option class="th_area" value="논현">논현</option>
							</select>
						</div>
						
						<div class="col">
							<button type="submit" class="btn btn-warning" onclick="">검색</button>
						</div>
						<div class="col">				
							<button type="button" class="btn btn-warning" onclick="searchAll();">전체검색</button>
						</div>
						<div class="col">
						</div>
						</div>
					</form>
				</td>
				</tr>	
	         <c:forEach var="bean" items="${requestScope.datalist}">
	       		<tr>
		            <td>
		               <div class="card">
		               	<div id="pic">
		                  <img
		                     src="<%=contextPath%>/image/${bean.image01}"
		                     class="card-img-top" style="width: 18rem;" alt="이미지">
		               	</div>
		                     <div class="card-body" id="th_body">
		                        <h5 class="card-title">${bean.title}</h5>
		                        <p class="card-text">장르: ${bean.genre}</p>
		                        <p class="card-text">난이도: ${bean.themalevel}</p>
		                        <p class="card-text">추천인원: ${bean.recommendpeople}</p>
		                        <p class="card-text">소요시간: ${bean.usetime}</p>
		                        <p class="card-text">설명: ${bean.comments}</p>
		                        <p class="card-text">지역: ${bean.area}</p>
		                        <p class="card-text">가격: ${bean.price}원</p>
		                     </div>
		                  <div class="buttonBox">
		                     <div id="buttonset" class="input-group">
		                      <a href = "<%=notWithFormTag%>prDetail&productnum=${bean.productnum}"> 
		                        <button type="submit" class="btn btn-primary" onclick="return validCheck();">
		                        	상세보기
		                        </button>
		                        </a>
		                     </div>
		                     
		                     <%-- <c:if test="${whologin == 2}"> --%>
		                     <div id="updateButton">
		                        <a id="updateAnchor" class="btn btn-secondary"
		                           href="<%=notWithFormTag%>prUpdate&productnum=${bean.productnum}${requestScope.pageInfo.flowParameter}">
		                           수정 
	                           </a>
		                     </div>
		                  </div>
		               </div>
		            </td>
	            </tr>
	       	</c:forEach>
         </tbody>
      </table>
      ${requestScope.pageInfo.pagingHtml}
	</div>
	
	<!-- 자바 스크립트 (js) -->
	<!-- 삭제 버튼 클릭 시 confirm 창 띄우기, 현재 이슈 있음(이슈 내용: 취소를 눌러도 DB 삭제 연동 됨) -->
   <script type="text/javascript">
      function removeCheck() {
         var msg = confirm(" 테마를 삭제하시겠습니까?")
         
         if (msg == true) {      //확인 버튼 클릭 시
        	 document.removeData.remove();
             alert(" 테마가 삭제되었습니다.")
         
         }else {      //취소 버튼 클릭 시
             alert(" 테마 삭제가 취소되었습니다.")
             return false;
         }
      }
   </script>
</body>
</html>