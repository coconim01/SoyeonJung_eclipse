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
.card>a {
   display: flex;
   position: relative;
}
.buttonBox {
   position: absolute;
   right: 10px;
   bottom: 10px;
}

</style>
</head>
<body>
   <div class="container mt-3">
      <h2>상품 목록</h2>
      <p>고객들이 구매할 상품 목록을 보여 주는 페이지</p>

      <table class="table table-borderess">
         <thead>
         </thead>
         <tbody>
            <td>
               <div class="card">
               
                  <a href="#"> 
                  <img
                     src="<%=contextPath%>/image/escape/escapeExampleimg.jpg"
                     class="card-img-top" style="width: 18rem;" alt="이미지">
                     
                     <div class="card-body">
                        <h5 class="card-title">방탈출 게임 제목</h5>
                        <p class="card-text">장르: 감동</p>
                        <p class="card-text">난이도: ★★★</p>
                        <p class="card-text">추천인원: 3인</p>
                        <p class="card-text">소요시간: 60분</p>
                        <p class="card-text">설명: ~~~</p>
                        <p class="card-text">지역: 강남</p>
                        <p class="card-text">가격: 22000원</p>
                     </div>
                  </a>
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
                     <%-- </c:if> --%>
                  </div>
               </div>
            </td>
         </tbody>
      </table>
      ${requestScope.pageInfo.pagingHtml}
   </div>
</body>
</html>