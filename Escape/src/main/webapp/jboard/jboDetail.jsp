<%@page language="java" contentType="text/html; charset=UTF-8"
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
      .btn-mini{
      opacity:0.6;
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
      .btn-minib{
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
   <style type="text/css">
      /* 댓글들을 위한 스타일 지정 */
      * {
         padding: 0;
         margin: 0;
         color: #333;
      }
      ul { list-style: none; }
      #container { padding: 30px 20px; }
      #insertComment {
         padding: 20px 15px;
         border-bottom: 1px solid #7BAEB5;
      }

      #insertComment label {
         display: inline-block;
         width: 80px;
         font-size: 14px;
         font-weight: bold;
         margin-bottom: 10px;
      }

      #insertComment input[type='text'], #insertComment textarea {
         border: 1px solid #ccc;
         vertical-align: middle;
         padding: 3px 10px;
         font-size: 12px;
         line-height: 150%;
      }

      #insertComment textarea {
         width: 450px;
         height: 120px ;
      }

      .commentItem {
         font-size: 13px;
         color: #333;
         padding: 15px;
         border-bottom: 1px dotted #ccc;
         line-height: 150%;
      }

      .commentItem .writer {
         color: #555;
         line-height: 200%;
      }

      .commentItem .writer input {
         vertical-align: middle;
      }

      .commentItem .writer .name {
         color: #222;
         font-weight: bold;
         font-size: 14px;
      }
      
      .form-group {
         margin-bottom: 3px;
      }
      
      .form-control {
         height: 25px;
      }
      .btn-primary{opacity: 0.8;}
      
   </style>
   
   <script type="text/javascript">
      function addNewItem(cnum, writer, content, regdate){
         /* 댓글 1개를 추가해 주는 함수 */
         var litag = $('<li>'); /* 새 댓글이 추가될 li 태그 */
         litag.addClass('commentItem');
         
         var ptag = $('<p>'); /* 작성자 정보가 들어갈 p 태그 */
         ptag.addClass('writer');
         
         var spantag = $('<span>'); /* 작성자 이름이 들어갈 span 태그 */
         spantag.addClass('nickname');
         spantag.html(writer+'님') ;
         
         var spandate = $('<span>'); /* 작성 일시가 들어갈 span 태그 */
         spandate.html('&nbsp;&nbsp;/&nbsp;&nbsp;' + regdate + ' ') ;
         
         if(writer == '${sessionScope.loginfo.id}')
         {
            var inputtag = $('<input>'); /* 삭제하기 버튼 */
            var attrlist = {'id':writer, 'class':'btn-mini', 'type':'button', 'value':'삭제', 'pmkey':cnum};   
            inputtag.attr(attrlist);
            inputtag.addClass('delete_btn');
            inputtag.html('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;');
            var inputtag2 = $('<input>'); /* 수정하기 버튼 */
               var attrlist2 = {'id':writer, 'class':'btn-minib', 'type':'button', 'value':'수정', 'pmkey':cnum};
               inputtag2.attr(attrlist2);
               inputtag2.addClass('update_btn');
         }else{
            var inputtag = ''; 
            var inputtag2 = '';
         }
         
         var content_p = $('<p>'); /* 작성한 댓글 내용 */
         content_p.html(content) ;
         
         // 조립하기
         ptag.append(spantag).append(spandate).append(inputtag).append(inputtag2) ;
         litag.append(ptag).append(content_p);
         
         $('#comment_list').append(litag) ;
      }
   
      function getListComment(){
         $('#comment_list').empty();
         $.ajax({
            url : '<%=notWithFormTag%>cmList',
            data : 'no=' + '${bean.jno}',
            type : 'get',
            dataType : 'json',
            success : function(result, status){
               /* result : 넘어온 결과 값 */
               $.each(result, function(idx){
                  var cnum = result[idx].cnum ;
                  var writer = result[idx].writer ;
                  var content = result[idx].content ;
                  var regdate = result[idx].regdate ;
                  addNewItem(cnum, writer, content, regdate);
               })
            }      
         });
      }
      
      /* [삭제] 버튼 클릭 */
      /* data 영역의 'pmkey'는 임의의 이름으로 내가 지정한 코멘트의 기본 키입니다. */      
      /* on() 메소드는 선택된 요소에 이벤트 핸들러 함수를 연결 시켜 줍니다. */
      /* 이전 페이지로 복원시 no 파라미터가 반드시 필요합니다. */
      $(document).on('click', '.delete_btn', function(){
         if(confirm('선택하신 항목을 삭제하시겠습니까?')){
            $.ajax({
               url : '<%=notWithFormTag%>cmDelete',
               data : 'cnum=' + $(this).attr('pmkey') + "&no=" + '${bean.jno}',
               type : 'get',
               dataType : 'text',
               success : function(result, status){ /* 댓글 삭제 성공시 */
                  getListComment();
               }      
            });            
         }
      });
      
      $(document).on('click', '.update_btn', function() {
            var btn_idx = $(this).parent().parent().index();
            
            $('#comment_list li').each(function(index, item) {
               var li_idx = $(item).index();
               if(li_idx == btn_idx){
                  var chdivtag = $(this).find('div').length;
                  if(chdivtag == 0){
                     var length = $(this).children().length;
                     var chinputtag = $(this).find('input');
                     var pmkey = chinputtag.attr('pmkey');
                                                   
                     if(length < 3){
                        var attrlist = "";
                        
                        var divTag = $('<div>');
                        
                        var formTag = $('<form>');
                        attrlist = {'class':'form-horizontal', 'role':'form'};
                        formTag.attr(attrlist);
                        
                        var tableTag = $('<table>');
                        attrlist = {'class':'table table-borderless'};
                        tableTag.attr(attrlist);
                        
                        var theadTag = $('<thead>');
                        var tbodyTag = $('<tbody>');
                        
                        var trTag1 = $('<tr>');
                        var tdTag1 = $('<td>');
                        attrlist = {'width':'40%','align':'left'};
                        tdTag1.attr(attrlist);
                        var textTag = $('<textarea>');
                        attrlist = {'id':'updateArea', 'rows':5, 'cols':30};
                        textTag.attr(attrlist);
                        
                        tdTag1.append(textTag);
                        trTag1.append(tdTag1);
                        
                        var trTag2 = $('<tr>');
                        var tdTag2 = $('<td>');
                        attrlist = {'width':'20%','align':'justify'};
                        tdTag2.attr(attrlist);
                        var inputtag = $('<input/>');
                        attrlist = {'id':'btnUpdate', 'class':'btn-minib', 'type':'button', 'value':'등록', 'pmkey': pmkey};
                        inputtag.attr(attrlist);
                        inputtag.addClass('insert_btn');
                        
                        tdTag2.append(inputtag);
                        trTag2.append(tdTag2);
                        
                        tbodyTag.append(trTag1).append(trTag2);
                        tableTag.append(theadTag).append(tbodyTag);
                        formTag.append(tableTag);
                        divTag.append(formTag);
                        $(this).append(divTag);
                     }
                  }else{
                     $(this).find('div').remove();
                  }
               }
            });
         });
         
         $(document).on('click', '.insert_btn', function() {
            if(confirm('댓글을 수정하시겠습니까?')){
               if($('#updateArea').val() == ""){
                  alert('수정할 내용을 입력해 주세요.');
                  $('#updateArea').focus();
               }else{
                  $.ajax({
                     url : '<%=notWithFormTag%>cmUpdate',
                     data : 'cnum=' + $(this).attr('pmkey') + "&no=" + '${bean.jno}' + "&content=" + $('#updateArea').val(),
                     type : 'post',
                     dataType : 'text',
                     success : function(result, status) {
                        getListComment();
                     }
                  });
               }
            }
         });

   
      $(document).ready(function(){
         getListComment(); /* 문서가 모두 읽힌 다음, 코멘트 목록 가져 오기 */
         
         /** 댓글 내용 저장 이벤트 */
         $("#comment_form").submit(function(){
            // 작성자 이름에 대한 입력 여부 검사
            if (!$("#writer").val()) {
               alert("이름을 입력하세요.");
               $("#writer").focus();
               return false;
            }
   
            // 내용에 대한 입력 여부 검사
            if (!$("#content").val()) {
               alert("내용을 입력하세요.");
               $("#content").focus();
               return false;
            }      
            
            var url = '<%=notWithFormTag%>cmInsert' ;
            var parameter = $('#comment_form').serialize() ;
            $.post(url, parameter, function( data ) {
               /* alert( '댓글이 작성되었습니다.' ) ; */
               getListComment() ; /* 목록 갱신 */
               $("#writer").val('') ;
               $("#content").val('') ;
               
            }).fail(function() {
               alert("댓글 작성에 실패했습니다. 잠시 후에 다시 시도해 주세요.");
               return false ;
            });
            return true ;
         });
         
         
      });   
   </script>
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
      <h2>글번호 ${requestScope.bean.jno}의 게시물 정보</h2>

      <table class="table">
         <thead>
         </thead>
         <tbody>
            <tr>
               <td class="table-info" >분류</td>
               <td >${bean.jcategory}</td>
               <td class="table-info" align="center" >글제목</td>
               <td >${bean.jsubject}</td>
               
               
            </tr>
            <tr>
               <td class="table-info" align="center" width="15%">작성자</td>
               <td width="35%" colspan="2">${bean.jwriter}</td>
               <td></td>
            </tr>
            <tr>
            
               <td class="table-info" align="center" width="15%">작성 일자</td>
               <td width="35%">${bean.jregdate}</td>
               <td class="table-info" align="center">조회수</td>
               <td >${bean.jreadhit}</td>
               
            </tr>
            <tr>
               <td class="table-info" align="center" width="15%">글내용</td>
               <td colspan="2" align="left"><pre>${bean.jcontent}</pre></td>
               <td></td>
            </tr>
         </tbody>
      </table>
         
      </div>
      <div class="col-sm-2"></div>
      
      <div class="col-sm-2"></div>
      <div class="col-sm-8">
      <div id="backButton" align="right">
         <button type="button" class="btn-gradient" onclick="comback();">
            돌아 가기
         </button>
      </div>
      <div>
         <!-- 댓글 영역 -->
         <div class="col-sm-12">               
            <ul id="comment_list">
               <!-- 여기에 동적 생성 요소가 들어가게 됩니다. -->
            </ul>
            <div id="insertComment">
               <form id="comment_form" method="post" role="form" class="form-horizontal" >
                  <table class="table table-borderless">
                      <thead>
                      </thead>
                      <tbody>
                        <tr>
                          <td valign="middle">
                             <label for="content" class="col-xs-3 col-lg-3 control-label">작성자</label>              
                          </td>
                          <td>
                           <input type="hidden" name="no" value="${bean.jno}" />
                           <input type="text" name="fakewriter" id="fakewriter" class="form-control" size="10" 
                              disabled="disabled" value="${sessionScope.loginfo.nickname}(${sessionScope.loginfo.id})님">                            
                           <input type="text" name="writer" id="writer" value="${sessionScope.loginfo.id}">
                           
                          </td>
                        </tr>
                        <tr>
                          <td valign="middle">
                             <label for="content" class="col-xs-3 col-lg-3 control-label">댓글 내용</label>
                          </td>
                          <td>
                             <textarea name="content" rows="3" cols="50" id="content" ></textarea>
                          </td>
                        </tr>
                        <tr>
                          <td colspan="2" align="right">
                             <button type="submit" class="btn-gradient">저장하기</button> 
                        </td>
                        </tr>
                      </tbody>
                  </table>
               </form>   
               </div>
            </div>
         </div>   
      </div>   
      
      <div class="col-sm-2"></div>
      </div>
      </div>
</body>
</html>