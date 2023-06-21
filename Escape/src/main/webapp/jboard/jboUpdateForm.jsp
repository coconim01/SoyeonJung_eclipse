<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ page import="com.escape.dao.JBoardDao" %>   
<%@ page import="com.escape.model.JBoard" %>   
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
      .form-controling{width:200px;}
      #sub{justify-content:center; align-items:center;display:flex;}
      #jcontent{height:200px;}
</style>
   <link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
     <link rel="stylesheet" href="/resources/demos/style.css">
     <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
     <script type="text/javascript">
     $(document).ready(function(){
        //$('#jregdate').datepicker({dateFormat: "yy/mm/dd"});
        
        var category = '${requestScope.bean.jcategory}'
        
        var optionlist = $('.myoption')
        optionlist.each(function(){
           if($(this).val() == category){
              $("option[value='"+category+"']").attr('selected',true);
           }
        });
     });
     
     function validCheck(){/* 폼 유효성 검사 */
        var subject = $('#jsubject').val();
        if(subject.length < 3 || subject.length > 20){
           alert('모집글 제목은 3자 이상 20자 이하여야 합니다.');
           $('#jsubject').focus();
           return false;
        }   
        var content = $('#jcontent').val();
        if(content.length < 5){
           alert('모집글 내용은 5글자 이상이어야 합니다.');
           $('#jcontent').focus();
           return false;
        }   
        
        var jcategory = $('#jcategory').val();
        if(jcategory =='-'){/* 코딩할 때 option의 value 속성을 하이픈으로 설정했습니다. */
           alert('카테고리를 선택해주세요');
           $('#jcategory').focus();
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
      <h2>게시물 수정</h2>
      <p>사용자가 게시물을 수정하는 페이지입니다.</p>
      
      <form action="<%=withFormTag%>" method="post">
         <div class="input-group">
            <input type="hidden" name="command" value="jboUpdate">
         <input type="hidden" name="pageNumber" value="<%=request.getParameter("pageNumber")%>">
         <input type="hidden" name="pageSize" value="<%=request.getParameter("pageSize")%>">
         <input type="hidden" name="mode" value="<%=request.getParameter("mode")%>">
         <input type="hidden" name="keyword" value="<%=request.getParameter("keyword")%>">
         
         <input type="hidden" name="jremark" value="${bean.jremark}">
         <input type="hidden" name="jreadhit" value="${bean.jreadhit}">
         <input type="hidden" name="jgroupno" value="${bean.jgroupno}">
         <input type="hidden" name="jorderno" value="${bean.jorderno}">
         
            <span class="input-group-text col-md-2" style="background-color:#81FAE5">게시물 번호</span> 
            <input  id="fakejno" name="fakejno" disabled="disabled" type="number" class="form-control" placeholder="" value="${requestScope.bean.jno}" >
            <input type="hidden" id="jno" name="jno" value="${requestScope.bean.jno}">
         </div>
         <div class="input-group">
            <span class="input-group-text col-md-2" style="background-color:#81FAE5">작성자</span> 
            <input id="fakewriter" name="fakewriter" disabled="disabled" type="text" class="form-control" placeholder="" value="${bean.jwriter}">
            <input type="hidden" id="jwriter" name="jwriter" value="${bean.jwriter}">
         </div>
         <div class="input-group">
            <span class="input-group-text col-md-2" style="background-color:#81FAE5">글 제목</span> 
            <select class="form-controling" id="jcategory" name="jcategory">
                           <option class="myoption" value="-" selected="selected">--- 선택해주세요. ---
                           <option class="myoption" value="구해요">구해요</option>
                           <option class="myoption" value="구했어요">구했어요</option>
                        </select>
            <input id="jsubject" name="jsubject" type="text" class="form-control" placeholder="" value="${bean.jsubject}">
            
         </div>
         <div class="input-group">
            <span class="input-group-text col-md-2" id="sub" style="background-color:#81FAE5">글 내용</span> 
            <input id="jcontent" name="jcontent" type="text" class="form-control" placeholder="모집하실 테마와 시간을 꼭 입력해주세요. 모집이 끝난 게시글은 구했어요로 수정 바랍니다." 
            style="vertical-align:top;text-align:left;" value="${bean.jcontent}">
         </div>
         <div id="buttonset" class="input-group">
            <button type="submit" class="btn-gradient"
            onclick="return validCheck();">수정</button>
            &nbsp;&nbsp;&nbsp;   
            <button type="reset" class="btn-blue">초기화</button>
         </div>
      </form>
      </div>   
      
      <div class="col-sm-2"></div>
   </div>
</body>
</html>