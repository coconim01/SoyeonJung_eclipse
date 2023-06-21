<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
   
<%@ include file="./../common/bootstrap5.jsp" %>
<%@ include file="./../common/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
   <title>Bootstrap Example</title>
   <meta charset="utf-8">
   <meta name="viewport" content="width=device-width, initial-scale=1">
   <style type="text/css">
   thead {background-color: #03D0B7; color:white;opacity:0.7;}
   </style>
   <style type="text/css">
   .btn-gradient {  
      margin: 5px; 
      text-decoration: none;
        color: white;
        padding: 8px 18px;
        display: inline-block;
      position: relative;
      border: 1px solid rgba(0,0,0,0.21);
      border-bottom: 4px solid rgba(0,0,0,0.21);
      border-radius: 4px;
      text-shadow: 0 1px 0 rgba(0,0,0,0.15);
      background-color:#03D0B7; color:white;}
   .btn-skyblue{  
      margin: 5px; 
      text-decoration: none;
        color: white;
        padding: 8px 18px;
        display: inline-block;
      position: relative;
      border: 1px solid rgba(0,0,0,0.21);
      border-bottom: 4px solid rgba(0,0,0,0.21);
      border-radius: 4px;
      text-shadow: 0 1px 0 rgba(0,0,0,0.15);
      background-color:#54E4EE; color:white;}
   .btn-blue{  
      margin: 5px; 
      text-decoration: none;
        color: white;
        padding: 8px 18px;
        display: inline-block;
      position: relative;
      border: 1px solid rgba(0,0,0,0.21);
      border-bottom: 4px solid rgba(0,0,0,0.21);
      border-radius: 4px;
      text-shadow: 0 1px 0 rgba(0,0,0,0.15);
      background-color:#01C0CE; color:white;}   
      #noUnderLine{text-decoration-line: none;}
      .btn-mini1{
      top: 4px;
      padding: 4px 12px;  
      font-size: 12px
      position: relative;
      border: 1px solid rgba(0,0,0,0.21);
      border-bottom-color: rgba(0,0,0,0.34);
      display: inline-block;
      text-align: center;
      text-shadow:0 1px 0 rgba(0,0,0,0.15);
      color: white;
      box-shadow:0 1px 0 rgba(255,255,255,0.34) inset, 
                    0 2px 0 -1px rgba(0,0,0,0.13), 
                   0 3px 0 -1px rgba(0,0,0,0.08), 
                    0 3px 13px -1px rgba(0,0,0,0.21);
      background-color: #ff4c4b;}
      .btn-minib1{
      top: 4px;
      padding: 4px 12px;  
      font-size: 12px
      position: relative;
      border: 1px solid rgba(0,0,0,0.21);
      border-bottom-color: rgba(0,0,0,0.34);
      display: inline-block;
      text-align: center;
      text-shadow:0 1px 0 rgba(0,0,0,0.15);
      color: white;
      box-shadow:0 1px 0 rgba(255,255,255,0.34) inset, 
                    0 2px 0 -1px rgba(0,0,0,0.13), 
                   0 3px 0 -1px rgba(0,0,0,0.08), 
                    0 3px 13px -1px rgba(0,0,0,0.21);
      background-color: #73B9C9;}
   </style>
   
   <script type="text/javascript">
      $(document).ready(function(){ 
         
      });
      
      function searchAll(){ /* 전체 보기 */
         location.href = '<%=notWithFormTag%>jboList' ;
      }
      
      function writeForm(){ /* 글쓰기 */
         location.href = '<%=notWithFormTag%>jboInsert' ;
      }
      
      function goJoin(){
         location.href = '<%=notWithFormTag%>jboList&mode=jcategory&keyword=구해요';
      }
      
      function alJoin() {
         location.href = '<%=notWithFormTag%>jboList&mode=jcategory&keyword=구했어요' ;
      }
      
      
      
      </script>
   <script type="text/javascript">
      /* 필드 검색시 입력 했던 콤보 박스(mode)의 내용과 키워드(keyword) 입력 상자에 있던 내용은 보존되어야 합니다. */
      $(document).ready(function(){ 
         var myoptions = $('#mode option');
         
         for(var i=0 ; i<myoptions.length ; i++){
            if(myoptions[i].value == '${requestScope.pageInfo.mode}'){
               myoptions[i].selected = true ; 
            }
         }
         
         $('#keyword').val('${requestScope.pageInfo.keyword}') ;
      });
      
         
      </script>
      
<% String alertMessage = (String)request.getAttribute("alertMessage");
if (alertMessage != null) {
%>
<script>
<%=alertMessage %>
</script>
<% } %>
      
      
</head>
<body>
   <div class="container mt-3">
      <h1>일행 모집</h1>
      <br>
      <div class="col">   <%-- 카테고리별로 조회하기위한 0314 수정내용 --%>         
         <button type="button" class="btn-skyblue" onclick="searchAll();">전체</button>
         <button type="button" class="btn-skyblue" onclick="goJoin()">구해요</button>
         <button type="button" class="btn-skyblue" onclick="alJoin()">구했어요</button>
      </div>
      
      

      <table class="table">
         <thead>
            <tr>
               <th>글번호</th>
               <th>분류</th>
               <th>제목</th>
               <th>작성자</th>
               <th>작성일자</th>
               <th>조회수</th>
               <th>수정</th>
               <th>삭제</th>
            </tr>
         </thead>
         <tbody>
      <%-- <c:if test="${not empty datalist}"></c:if>--%>
            <c:forEach var="bean" items="${datalist}"> 
            <tr align="center">
               <td>${bean.jno}</td>
               <td>${bean.jcategory}</td>
               
               <td>
                  <a id="noUnderLine" href="<%=notWithFormTag%>jboDetail&jno=${bean.jno}">                  
                     ${bean.jsubject}                     
                  </a>
               </td>
               <td>${bean.jwriter}</td>
               <td>${bean.jregdate}</td>
               <td>
                  <c:if test="${bean.jreadhit >= 50}">
                     <span class="badge rounded-pill bg-secondary">${bean.jreadhit}</span>
                  </c:if>                                    
                  <c:if test="${bean.jreadhit < 50}">
                     <span class="badge rounded-pill bg-secondary">${bean.jreadhit}</span>
                  </c:if>
               </td>
               <td align="center">
              <c:if test="${sessionScope.loginfo.id==bean.jwriter}">
               <a id="noUnderLine" href="<%=notWithFormTag%>jboUpdate&jno=${bean.jno}${requestScope.pageInfo.flowParameter}">
               수정하기
               </a>
                </c:if>
               </td>
               <td align="center">
                <c:if test="${sessionScope.loginfo.id==bean.jwriter}">
               <a id="noUnderLine" href="<%=notWithFormTag%>jboDelete&jno=${bean.jno}${requestScope.pageInfo.flowParameter}">
                  삭제하기
               </a>
               </c:if>
               </td>
            </tr>
            
            </c:forEach>
            
            <tr>
               <td colspan="9" align="center" valign="middle">         
               
                  <form name="myform" action="<%=withFormTag%>" method="get">
                     <div class="row">
                     <input type="hidden" name="command" value="jboList">
                        
                     <div class="col">                     
                        <select class="form-control" id="mode" name="mode">
                           <option value="all" selected="selected">--- 선택해 주세요 ---
                           <option value="jsubject">글제목</option>
                           <option value="jcontent">글내용</option>
                        </select>
                     </div>
                     <div class="col">
                        <input class="form-control" type="text" name="keyword" id="keyword"
                           placeholder="키워드를 입력해 주세요.">
                     </div>                     
                     <div class="col">
                        <button type="submit" class="btn-gradient" onclick="">검색</button>
                     </div>
                     <div class="col">
                        <button type="button" class="btn-blue" onclick="writeForm();">글 쓰기</button>
                     </div>
                     <div class="col">
                        ${requestScope.pageInfo.pagingStatus}
                     </div>
                     </div>
                  </form>
               </td>
            </tr>                            
         </tbody>
      </table>
      ${requestScope.pageInfo.pagingHtml}
      </div>
      
   </body>
</html>

