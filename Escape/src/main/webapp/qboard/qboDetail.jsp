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
      #backButton{margin:auto;}
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
      
   </style>
   
   <script type="text/javascript">
   function comback(){
       location.href = document.referrer; // 이전 페이지로 돌아가기
   }
   </script>
</head>
<body>

   <div class="container mt-3">
   <div class="row justify-content-md-center">
   <div class="col-sm-2"></div>
   <div class="col-sm-8">
      <h2>글번호 ${requestScope.bean.qno}의 게시물 정보</h2>

      <table class="table">
         <thead>
         </thead>
         <tbody>
            <tr>
               
            </tr>
            <tr>
               <td class="table-info" align="center">문의</td>
               <td >${bean.qcategory}</td>
               <td class="table-info" align="center">글제목</td>
               <td>${bean.qsubject}</td>
               
            </tr>
            <tr>
               <td class="table-info" align="center">작성자</td>
               <td colspan="2">${bean.qwriter}</td>
               <td></td>
            </tr>
            <tr>
               
               <td class="table-info" align="center">작성 일자</td>
               <td>${bean.qregdate}</td>
               <td class="table-info" align="center">조회수</td>
               <td>${bean.qreadhit}</td>
            </tr>
            <tr>
               <td class="table-info" align="center">글내용</td>
               <td colspan="2" align="left"><pre>${bean.qcontent}</pre></td>
               <td></td>
            </tr>         
         </tbody>
      </table>
         <div  id="backButton" align="right">
         <button type="button" class="btn-gradient" onclick="comback();" align="right">
            돌아 가기
         </button>
      </div>
      </div>   
      
      <div class="col-sm-2"></div>
      </div>
      </div>
</body>
</html>