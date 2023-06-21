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
   /* box model 공부할 것 */
   .input-group{margin: 7px;}
   
   .input-group-text{
   display:block;
   margin-left:auto;
   margin-right:auto;
   }
   #buttonset{margin-top: 15px;}
   #boardNo{display : none; visibility : hidden;}   
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
      padding: 10px 30px;
      display: inline-block;
      position: relative;
      border: 1px solid rgba(0,0,0,0.21);
      border-bottom: 4px solid rgba(0,0,0,0.21);
      border-radius: 4px;
      text-shadow: 0 1px 0 rgba(0,0,0,0.15);
      background-color:gray; color:white;}
      .form-controling{width:200px;}
      #sub{justify-content:center; align-items:center;display:flex;}
</style>
   <link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
     <link rel="stylesheet" href="/resources/demos/style.css">
     <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
     <script type="text/javascript">
     $(document).ready(function(){
        $('#regdate').datepicker({dateFormat: "yy/mm/dd"});
        
     });
     
     function validCheck(){/* 폼 유효성 검사 */
        var subject = $('#qsubject').val();
        if(subject.length < 3 || subject.length > 20){
           alert('답변의 제목은 3자 이상 20자 이하여야 합니다.');
           $('#qsubject').focus();
           return false;
        }   
        var qcategory = $('#qcategory').val();
           if(qcategory == '-'){/* 코딩할 때 option의 value 속성을 하이픈으로 설정했습니다. */
              alert('카테고리를 선택해주세요');
              $('#qcategory').focus();
              return false;
           
           }
        
        
         return true;           
     }
     </script>
</head>
<body>
   <div class="container mt-3 col-md-6">
   <div class="col-sm-2"></div>
   <div class="col-sm-8">
      <h2>게시물 답글</h2>
      <p>특정 게시물에 대한 답글을 작성하는 페이지입니다.</p>
      
      <form action="<%=withFormTag%>" method="post">
      <input type="hidden" name="command" value="qboReply">
      
      <input type="hidden" name="pageNumber" value="<%=request.getParameter("pageNumber")%>">
      <input type="hidden" name="pageSize" value="<%=request.getParameter("pageSize")%>">
      <input type="hidden" name="mode" value="<%=request.getParameter("mode")%>">
      <input type="hidden" name="keyword" value="<%=request.getParameter("keyword")%>">
      
      <input type="hidden" name="qgroupno" value="${requestScope.bean.qgroupno}">
      <input type="hidden" name="qorderno" value="<%=request.getParameter("qorderno")%>">
      <input type="hidden" name="qdepth" value="<%=request.getParameter("qdepth")%>">
         
         <div id="boardNo" class="input-group">
            <span class="input-group-text col-md-2">글 번호</span> 
            <input id="qno" name="qno" type="number" class="form-control" placeholder="">
            
         </div>
         <div class="input-group">
            <span class="input-group-text col-md-2" style="background-color:#81FAE5">작성자</span> 
            <c:set var="userInfo" value="${sessionScope.loginfo.nickname}(${sessionScope.loginfo.id})"/>
            <input id="fakeqwriter" name="fakeqwriter" disabled="disabled" type="text" class="form-control" placeholder="" value="${userInfo}">
            <input id="qwriter" name="qwriter" type="hidden" value="${sessionScope.loginfo.id}">
         </div>
         <div class="input-group">
            <span class="input-group-text col-md-2" style="background-color:#81FAE5">글 제목</span> 
            <select class="form-controling" id="qcategory" name="qcategory">
                           <option value="-" selected="selected">--- 문의답변 ---
                           <option class="myoption" value="방탈출 문의">방탈출 관련
                           <option class="myoption" value="예약 문의">예약 문의
                           <option class="myoption" value="기타 문의">기타 문의사항
                        </select>
            <input id="qsubject" name="qsubject" type="text" class="form-control" placeholder="">
         </div>
         <div class="input-group">
            <span class="input-group-text col-md-2" id="sub" style="background-color:#81FAE5">문의 답변</span> 
            <textarea id="qcontent" name="qcontent"  class="form-control" placeholder="문의 내용을 입력해주세요." cols=85 rows=15 required="required"></textarea>
         </div>
         
         <div id="buttonset" class="input-group" >
            <button type="submit" class="btn-gradient"
            onclick="return validCheck();">답글 작성</button>
            &nbsp;&nbsp;&nbsp;   
            <button type="reset" class="btn-gradien">초기화</button>
         </div>
      </form>
      </div>   
      
      <div class="col-sm-2"></div>
   </div>
</body>
</html>