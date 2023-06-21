<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ page import="com.escape.dao.QBoardDao" %>   
<%@ page import="com.escape.model.QBoard" %>   
<%@ include file="./../common/bootstrap5.jsp"%>
<%@ include file="./../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
   /* box model 공부할 것 */
   .input-group{margin: 7px;}
   
   .input-group-text{
   display:block;
   margin-left:auto;
   margin-right:auto;
   }
   #buttonset{margin-top: 15px;}
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
      .btn-gradien {
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
      background-color:gray; color:white;}
      .form-controling{width:200px;}
      #sub{justify-content:center; align-items:center;display:flex;}
      #qcontent{height:200px;}
</style>
   <link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
     <link rel="stylesheet" href="/resources/demos/style.css">
     <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
     <script type="text/javascript">
     $(document).ready(function(){
        $('#regdate').datepicker({dateFormat: "yy/mm/dd"});
        
      var qcategory = '${requestScope.bean.qcategory}';
        
        var optionlist = $('.myoption')
        optionlist.each(function(){
           if($(this).val() == qcategory){
              $("option[value='"+qcategory+"']").attr('selected',true);
           }
        });
     });
     
        function validCheck(){/* 폼 유효성 검사 */
         var qsubject = $('#qsubject').val();
         if(qsubject.length < 3 || qsubject.length > 20){
            alert('문의글 제목은 3자 이상 20자 이하로 작성해주시기 바랍니다.');
            $('#qsubject').focus() ;
            return false ;
         }
         
         var qcontent = $('#qcontent').val();
         if(qcontent.length < 5 || qcontent.length > 150){
            alert('글 내용은 5자 이상 150자 이하로 작성해주시기 바랍니다.');
            $('#qcontent').focus() ;
            return false ;
         }
         
         var qcategory = $('#qcategory').val();
           if(qcategory =='-'){/* 코딩할 때 option의 value 속성을 하이픈으로 설정했습니다. */
              alert('카테고리를 선택해주세요');
              $('#qcategory').focus();
              return false;
           
           }
         
         return true ;
      }
     </script>
</head>
<body>
   <div class="container mt-3 col-md-6">
     <div class="col-sm-2"></div>
	<div class="col-sm-8">
      <h2>Q&A 게시물 수정</h2>
      
      <form action="<%=withFormTag%>" method="post">
         <div class="input-group">
            <input type="hidden" name="command" value="qboUpdate">
         <input type="hidden" name="pageNumber" value="<%=request.getParameter("pageNumber") %>">
         <input type="hidden" name="pageSize" value="<%=request.getParameter("pageSize")  %>">
         <input type="hidden" name="mode" value="<%=request.getParameter("mode")  %>">
         <input type="hidden" name="keyword" value="<%=request.getParameter("keyword")  %>">
         
         <input type="hidden" name="qreadhit" value="${bean.qreadhit}">
         <input type="hidden" name="qgroupno" value="${bean.qgroupno}">
         <input type="hidden" name="qorderno" value="${bean.qorderno}">
         <input type="hidden" name="qdepth" value="${bean.qdepth}">
         
            <span class="input-group-text col-md-2" style="background-color:#81FAE5">게시물 번호</span> 
            <input id="fakeqno" name="fakeqno" disabled="disabled" type="number" class="form-control" placeholder="" value="${requestScope.bean.qno}" >
            <input type="hidden" id="qno" name="qno" value="${requestScope.bean.qno}">
         </div>
         <div class="input-group">
            <span class="input-group-text col-md-2" style="background-color:#81FAE5">작성자</span> 
            <input id="fakeqwriter" name="fakeqwriter" disabled="disabled" type="text" class="form-control" placeholder="" value="${bean.qwriter}">
            <input type="hidden" id="qwriter" name="qwriter" value="${bean.qwriter}">
         </div>
         <div class="input-group">
         <span class="input-group-text col-md-2" style="background-color:#81FAE5">제목</span> 
            <select class="form-controling" id="qcategory" name="qcategory">
                           <option value="-">--- 문의종류 ---
                           <option class="myoption" value="방탈출 문의">방탈출 관련
                           <option class="myoption" value="예약 문의">예약 문의
                           <option class="myoption" value="기타 문의">기타 문의사항
                        </select>
            <input id="qsubject" name="qsubject" type="text" class="form-control" placeholder="" value="${bean.qsubject}">
         </div>
         <div class="input-group">
            <span class="input-group-text col-md-2" id="sub" style="background-color:#81FAE5">글 내용</span> 
            <input id="qcontent" name="qcontent" type="text" class="form-control" placeholder="" value="${bean.qcontent}">
         </div>
         <div id="buttonset" class="input-group">
            <button type="submit" class="btn-gradient"
            onclick="return validCheck();">수정</button>
            &nbsp;&nbsp;&nbsp;   
            <button type="reset" class="btn-gradien">초기화</button>
         </div>
      </form>
      </div>   
      
      <div class="col-sm-2"></div>
   </div>
</body>
</html>