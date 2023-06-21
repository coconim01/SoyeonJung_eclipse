<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- java.util 패키지는 여러 군데서 사용할 것이므로 공용 파일에 둡니다. --%>
<%@ page import="java.util.*" %>

<%
   // 모든 문서에서 사용될 애플리케이션 이름(컨텍스트)
   String contextPath = request.getContextPath();
   String urlPatterns = "/EscapePeople" ; // in FrontController.java file

   // form 태그를 사용할 때 필요한 변수
   String withFormTag = contextPath + urlPatterns ;
   
   // form 태그가 아닌 경우에 사용할 변수
   String notWithFormTag = contextPath + urlPatterns + "?command=" ;
   
   //out.print("contextPath=" + contextPath + "<br/>");
   //out.print("notWithFormTag=" + notWithFormTag + "<br/>");
%>
 
<%--
   JSTL : Jsp Standard Tag Library
   JSP 코딩을 지원하기 위한 표준 태그 라이브러리
   준비물) jstl-1.2.jar, standard.jar
   taglib 지시어를 명시해 주어야 합니다.
   필요한 태그 라이브러리를 적절히 사용하면 됩니다.
--%>   
<%-- jstl core 라이브러리 --%>
<%-- uri는 unique한 값, prefix는 접두사(네임스페이스) --%>
<%-- 네임스페이스는 서로 다른 영역을 식별하기 위한 별칭과 같은 개념 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%-- 세션 영역에 loginfo라는 이름으로 로그인 정보가 바인딩되어 있습니다. --%>
<%-- 변수 whologin : 0(미로그인) 1(일반 사용자) 2(관리자) --%>

<c:set var="whologin" value="0" />
<c:if test="${not empty sessionScope.loginfo}">
   <c:if test="${sessionScope.loginfo.id == 'admin'}">
      <c:set var="whologin" value="2" />
   </c:if>
   <c:if test="${sessionScope.loginfo.id != 'admin'}">
      <c:set var="whologin" value="1" />
   </c:if>   
</c:if>


<!DOCTYPE html>
<html>


<head>
   <meta charset="UTF-8">
   <title>Insert title here</title>
   
   <!-- for sweet alert -->
   <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>   
   
   <!-- css로 연결해주는 링크 -->
   <link rel="stylesheet" href="./freeBoardDetail.css">
   
   <style type="text/css">
	@import url('https://fonts.googleapis.com/css2?family=Dongle:wght@300&display=swap');
	   /*body {font-family: 'Dongle', sans-serif;} */
	   
	   /*나눔고딕*/
	@import url('https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap');
	
	* {
	   font-family: 'Nanum Gothic', sans-serif; /* 글꼴 */
	}
	.navbar {
	 background-image: linear-gradient(to right, #29323c, #485563, #2b5876, #4e4376);
	}
	
	.home-title {
	  color: white;
	  font-family: 'Nanum Gothic', sans-serif; /* 글꼴 */
	  font-weight: bolder;
	}
	
	.dropdown-toggle {
	   color: white;
	   font-family: 'Nanum Gothic', sans-serif; /* 글꼴 */
	}
</style>
</head>
<body>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="<%=notWithFormTag%>home"><h2 class="home-title">EscapePeple Home</h2></a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="collapsibleNavbar">
      <ul class="navbar-nav">
      <li class="nav-item">
           <c:if test="${whologin == 0}">
              <a class="nav-link" href="<%=notWithFormTag%>meLogin">미로그인</a>
           </c:if>   

         
      </li>
        
        <!-- 회원 section -->
        <li class="nav-item dropdown">
             <c:if test="${whologin != 0}">
                <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">
                ${sessionScope.loginfo.nickname}님</a>

           </c:if>
          <ul class="dropdown-menu">
          
         <c:if test="${whologin == 0}">
              <li><a class="dropdown-item" href="<%=notWithFormTag%>meInsert">회원 가입</a></li>
            <li><a class="dropdown-item" href="<%=notWithFormTag%>meLogin">로그인</a></li>
             </c:if>
             
         <c:if test="${whologin != 0}">
              <li><a class="dropdown-item" href="<%=notWithFormTag%>meLogout">로그 아웃</a></li>
             </c:if>
             <c:if test="${whologin == 1}">
            <li><a class="dropdown-item" href="<%=notWithFormTag%>meUpdate&id=${sessionScope.loginfo.id}">정보 수정</a></li>
            <li><a class="dropdown-item" href="<%=notWithFormTag%>meDetail&id=${sessionScope.loginfo.id}">마이페이지</a></li>
             </c:if>
             
         <c:if test="${whologin == 2}">
              <li><a class="dropdown-item" href="<%=notWithFormTag%>meList">목록 보기</a></li>
             </c:if>
            
         
          </ul>
        </li>
        
        <!-- 게시물 section -->
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">커뮤니티</a>
          <ul class="dropdown-menu">
          
      <!--<c:if test="${whologin != 0}"> </c:if>-->          
              
              <li><a class="dropdown-item" href="<%=notWithFormTag%>jboList">일행 모집</a></li>
                         
            <li><a class="dropdown-item" href="<%=notWithFormTag%>qboList">Q&A 게시판</a></li>
<!--             <li><a class="dropdown-item" href="#" onclick="location.href='./freeBoard/freeBoard.jsp'">자유 게시판</a></li> -->
<!--             <li><a class="dropdown-item" href="#" onclick="location.href='./reviewBoard/reviewBoard.jsp'">리뷰 게시판</a></li> -->
            <li><a class="dropdown-item" href="<%=contextPath%>/freeBoard/freeBoard.jsp">자유 게시판</a></li>
            <li><a class="dropdown-item" href="<%=contextPath%>/reviewBoard/reviewBoard.jsp">리뷰 게시판</a></li>
            
          </ul>
        </li>
        
        <!-- 테마 section -->
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">방탈출 테마</a>
          <ul class="dropdown-menu">
          
             <c:if test="${whologin == 2}">
                <li><a class="dropdown-item" href="<%=notWithFormTag%>prInsert">상품 등록</a></li>
             </c:if>
            
            <li><a class="dropdown-item" href="<%=notWithFormTag%>prAllList">목록 보기</a></li>
            
          </ul>
        </li>        


      </ul>
    </div>
  </div>
</nav>

<%-- 사용자에게 주의/경고/오류 등을 알려 주기 위한 Alert Box --%>
<c:if test="${not empty sessionScope.alertMsg}">
   <div class="alert alert-danger alert-dismissible">
      <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
      ${sessionScope.alertMsg}
   </div>
</c:if>
<%-- session 영역의 변수 alertMsg를 빈 문자열로 셋팅합니다. --%>
<c:set var="alertMsg" value="" scope="session"/> 

<%-- session 영역의 변수 alertMsg를 제거합니다. --%>
<c:remove var="alertMsg" scope="session"/> 

</body>
</html>