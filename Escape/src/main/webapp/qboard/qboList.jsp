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
      background-color:#7fb1bf; color:white;}
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
      .btn-miniR{
      top: 4px;
      padding: 0px 5px;  
      font-size: 7px
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
      background-color: #03D0B7;}
      #noUnderLine{text-decoration-line: none;}
      .btn-mini{
      top: 4px;
      padding: 1px 12px;  
      font-size: 10px
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
      .btn-minib{
      top: 4px;
      padding: 1px 12px;  
      font-size: 10px
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
         location.href = '<%=notWithFormTag%>qboList';
      }
      
      function writeForm(){ /* 글쓰기 */
         location.href = '<%=notWithFormTag%>qboInsert';
      }
      function roomQ() {
         location.href = '<%=notWithFormTag%>qboList&mode=qcategory&keyword=방탈출 문의';
      }
      
      function reserQ() {
         location.href = '<%=notWithFormTag%>qboList&mode=qcategory&keyword=예약 문의' ;
      }
      function etcQ() {
         location.href = '<%=notWithFormTag%>qboList&mode=qcategory&keyword=기타 문의' ;
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
      
</head>
<body>

   <div class="container mt-3">
      <h1>Q&A 게시판</h1>
      <p>문의하실 내용을 자유롭게 작성해주세요.</p>
      <br>
      
      <div class="col">   <%-- 카테고리별로 조회하기위한 0314 수정내용 --%>         
         <button type="button" class="btn-skyblue" onclick="searchAll();">전체문의</button>
         <button type="button" class="btn-skyblue" onclick="roomQ()">방탈출 문의</button>
         <button type="button" class="btn-skyblue" onclick="reserQ()">예약 문의</button>
         <button type="button" class="btn-skyblue" onclick="etcQ()">기타 문의</button>
      </div>
      
      <table class="table">
         <thead>
            <tr>
               <th>글번호</th>
               <th>문의</th>
               <th>제목</th>
               <th>작성자</th>
               <th>작성일자</th>
               <th>조회수</th>
               <th>답글</th>
               <th>수정</th>
               <th>삭제</th>
            </tr>
         </thead>
         <tbody>
            
            <c:forEach var="bean" items="${datalist}"> 
            <tr align="center">
               <td>${bean.qno}</td>
               <c:set var="no" value="${bean.qno}"></c:set>
               <td>${bean.qcategory}</td>
               
               <td>
                  <a id="noUnderLine" href="<%=notWithFormTag%>qboDetail&qno=${bean.qno}">                  
                     <c:forEach begin="1" end="${bean.qdepth}">
                        <span class="badge badge bg-info">문의 답변</span>                     
                     </c:forEach>
                     ${bean.qsubject}                     
                  </a>
               </td>
               <td>${bean.qwriter}</td>
               <td>${bean.qregdate}</td>
               <td>
                  <c:if test="${bean.qreadhit >= 50}">
                     <span class="badge rounded-pill bg-secondary">${bean.qreadhit}</span>
                  </c:if>                                    
                  <c:if test="${bean.qreadhit < 50}">
                     <span class="badge rounded-pill bg-secondary">${bean.qreadhit}</span>
                  </c:if>
               </td>
               <td>
                  <c:if test="${whologin == 2}">   
                     <c:set var="replyInfo" value="&qgroupno=${no}&qorderno=${bean.qorderno}&qdepth=${bean.qdepth}"/>                  
                     <a id="noUnderLine" href="<%=notWithFormTag%>qboReply&no=${bean.qno}${requestScope.pageInfo.flowParameter}${replyInfo}">
                        답글
                     </a>
                     </c:if>
               </td>
            
               <td>
                  <c:if test="${sessionScope.loginfo.id==bean.qwriter}">
                  <a id="noUnderLine" href="<%=notWithFormTag%>qboUpdate&qno=${bean.qno}${requestScope.pageInfo.flowParameter}">
                        수정
                     </a>
                  </c:if> 
               </td>
               <td>
                  <c:if test="${sessionScope.loginfo.id==bean.qwriter}">
                     <a id="noUnderLine" href="<%=notWithFormTag%>qboDelete&qno=${bean.qno}${requestScope.pageInfo.flowParameter}">
                        삭제
                     </a>
                     </c:if>
               </td>
            </tr>
            </c:forEach>   
            <tr>
               <td colspan="9" align="center" valign="middle">         
               
                  <form name="myform" action="<%=withFormTag%>" method="get">
                     <div class="row">
                     <input type="hidden" name="command" value="qboList">
                        
                     <div class="col">                     
                        <select class="form-control" id="mode" name="mode">
                           <option value="all" selected="selected">--- 선택해 주세요 ---
                           <option value="qsubject">글제목</option>
                           <option value="qcontent">글내용</option>
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
                        <button type="button" class="btn-gradient" onclick="writeForm();">글 쓰기</button>
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








