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
		div {
          float: left;
            border: 2px solid #000;
            width: auto;
            height: auto;
            text-align: center;
            line-height: 100px;
            font-weight: bold;
 }

	
	</style>
</head>
<body>
	<div class="theme_List">
		<div class="theme_box">
			<div class="theme_pic">
				<!-- image-size:408x290 -->
				<img src="<%=contextPath%>/image/youname.jpg" alt=""
				 	class="card-img-top" style="width: 18rem;" alt="이미지">
			</div>
			
			<div class="theme_Text">
				<h5 class="th_title">방탈출 게임 제목 ${bean.title}</h5>
				<p class="th-level">난이도: ★★★ ${bean.themalevel}</p>
				<p class="th-people">추천인원: 3인 ${bean.recommendpeople}</p>
				<p class="th-usetime">소요시간: 60분 ${bean.usetime}</p>
				<p class="th-comments">설명: ~~~ ${bean.comments}</p>
				<p class="th-area">지역: 강남 ${bean.area}</p>
				<p class="th-price">가격: 22000원 ${bean.price}</p>
			</div>
			<div class="buttonBox">
                <div id="buttonset" class="input-group">
                   <button type="submit" class="btn btn-primary"
                      onclick="return validCheck();">예약하기</button>
                </div>
                <%-- <c:if test="${whologin == 2}"> --%>
                <div id="updateButton">
                   <a id="updateAnchor" class="btn btn-secondary"
                      href="<%=notWithFormTag%>prUpdate&num=${bean.num}${requestScope.pageInfo.flowParameter}">
                      수정 </a>
                </div>
           	</div>
		</div>
	</div>
</body>
</html>