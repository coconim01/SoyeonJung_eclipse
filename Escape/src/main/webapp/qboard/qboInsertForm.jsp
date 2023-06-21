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
   .btn-gradient {
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
      
   #boardNo{display:none;visibility:hidden;}
   #buttonset{text-align:right; display:inline-block;} 
   .form-controling{width:200px;}
   #sub{justify-content:center; align-items:center;display:flex;}
</style>

   <link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">      
     <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>     
     <script type="text/javascript">
        $(document).ready(function(){
             /* $('#regdate').datepicker(); */
                        
        });
        
        
        function validCheck(){/* 폼 유효성 검사 */
           var subject = $('#qsubject').val();
           if(subject.length < 3 || subject.length > 20){
              alert('문의글 제목은 3자 이상 20자 이하로 작성해주시기 바랍니다.');
              $('#qsubject').focus() ;
              return false ;
           }
           
           var content = $('#qcontent').val();
           if(content.length < 5 || content.length > 150){
              alert('글 내용은 5자 이상 150자 이하로 작성해주시기 바랍니다.');
              $('#qcontent').focus() ;
              return false ;
           }
           
           var qcategory = $('#qcategory').val();
             if(qcategory == '-'){/* 코딩할 때 option의 value 속성을 하이픈으로 설정했습니다. */
                alert('카테고리를 선택해주세요');
                $('#qcategory').focus();
                return false;
             
             }
           
           return true ;
        }
     </script>
  
   <!-- css로 연결해주는 링크 -->
   <link rel="stylesheet" href="./../common/board.css">
   
</head>
<body>
   <div class="container mt-3 background1" align="center">
   <div class="col-sm-2"></div>
   <div class="col-sm-8">
      <h2 align="left" style="color:#03D0B7; margin-bottom: 10px;">Q&A 게시판</h2>
      <div class = "dtlsubject" align="left" style="margin-bottom: 10px;">문의를 등록해주세요.</div>
      
      <form action="<%=withFormTag%>" method="post">
         <input type="hidden" name="command" value="qboInsert">
         <div id="boardNo" class="input-group">
            <span class="input-group-text col-md-2">게시물 번호</span> 
            <input id="qno" name="qno" type="number" class="form-control" placeholder="">
         </div> 
         <div class="input-group">
            <span class="input-group-text col-md-2" style="background-color:#81FAE5">작성자</span>
            
            <c:set var="userInfo" value="${sessionScope.loginfo.nickname}(${sessionScope.loginfo.id})"/> 
            <input id="fakewriter" name="fakewriter" disabled="disabled" type="text" class="form-control" 
               value="${userInfo}">
            <input id="qwriter" name="qwriter" type="hidden" value="${sessionScope.loginfo.id}">
         </div>
         <div class="input-group">
            
            <span class="input-group-text col-md-2" style="background-color:#81FAE5">제목</span> 
            <select class="form-controling" id="qcategory" name="qcategory">
                           <option value="-" selected="selected">--- 문의종류 ---
                           <option value="방탈출 문의">방탈출 관련
                           <option value="예약 문의">예약 문의
                           <option value="기타 문의">기타 문의사항
                        </select>
            <input id="qsubject" name="qsubject" type="text" class="form-control" placeholder="제목을 입력해주세요.">
         </div>
         <div class="input-group" >
            <span class="input-group-text col-md-2" id="sub" style="background-color:#81FAE5">문의내용</span> 
            <textarea id="qcontent" name="qcontent"  class="form-control" placeholder="문의 내용을 입력해주세요." cols=85 rows=15 required="required"></textarea>
         </div>  
         
         <div id="buttonset" class="input-group" align="right">
            <button type="submit" class="btn-gradient"
               onclick="return validCheck();">
               
               등록 
            </button>
            &nbsp;&nbsp;&nbsp;
            <button type="reset" class="btn-gradien">초기화</button>
         </div>
      </form>
      </div>   
      
      <div class="col-sm-2"></div>
   </div>
</body>
</html>