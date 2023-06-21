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
	
	  <table class="table table-borderess">
         <thead>
         </thead>
         <tbody>
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
</body>
</html>