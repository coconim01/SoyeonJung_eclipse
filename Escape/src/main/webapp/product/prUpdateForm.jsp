<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./../common/bootstrap5.jsp"%>
<%@ include file="./../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
<style type="text/css">
@import url('https://fonts.googleapis.com/css2?family=Dongle:wght@300&display=swap');
/*body {font-family: 'Dongle', sans-serif;} */
@import url('https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap');
/*나눔고딕*/

#buttonset{margin-top:15px; font-family: 'Nanum Gothic', sans-serif;}
.radio_gender, .chk_theme{font-size:15px;} 
.btn{border: none; width:100px;
	 height:30px;font-size:16px;padding-bottom:12px; padding-top:5px; 
	 background-color :#03D0B7; color: white; font-weight:600;}
 h1{margin-bottom: 10px;}		
.input-group-text{ 
	font-weight: Bold;
	font-family: 'Nanum Gothic', sans-serif;
}
.input-group{margin: 7px;}
</style>
<!-- css로 연결해주는 링크 -->
<link rel="stylesheet" href="./freeBoardDetail.css">
     <script type="text/javascript">
        $(document).ready(function(){
             $('#uploaddate').datepicker({dateFormat: "yy/mm/dd"});
             
             /* 이전에 사용자가 선택했던 카테고리들이 자동 클릭되도록... */
             
             /* 장르 */
             var genre = '${requestScope.bean.genre}';
             
             var genrelist = $('.th_genre') ;
             genrelist.each(function(){
                if($(this).val() == genre){
                   $("option[value='" + genre + "']").attr('selected', true);      
                }
             });
             
             /* 난이도 */
             var themalevel = '${requestScope.bean.themalevel}';
             
             var levellist = $('.th_level') ;
             levellist.each(function(){
                if($(this).val() == themalevel){
                   $("option[value='" + themalevel + "']").attr('selected', true);      
                }
             });
             
             /* 추천인원 */
             var recommendpeople = '${requestScope.bean.recommendpeople}';
             
             var peoplelist = $('.th_people') ;
             peoplelist.each(function(){
                if($(this).val() == recommendpeople){
                   $("option[value='" + recommendpeople + "']").attr('selected', true);      
                }
             });
             
             /* 지역 */
             var area = '${requestScope.bean.area}';
             
             var arealist = $('.th_area') ;
             arealist.each(function(){
                if($(this).val() == area){
                   $("option[value='" + area + "']").attr('selected', true);      
                }
             });
           
        });
        
        function validCheck(){
           
           /* 테마명 */
           var title = $('#title').val();
           if(title.length < 1 || title.length > 100){
              alert('테마명을 입력하세요.');
              $('#title').focus() ;
              return false ;
           }
           
           /* 장르선택 */
           var genre = $('#genre').val();
           if(genre == '-'){ /* 코딩할 때 option의 value 속성을 하이폰으로 설정했습니다. */
              alert('장르를 선택해 주세요.');
              $('#genre').focus();
              return false ;
           }
           
           /* 난이도 선택 */
           var themalevel = $('#themalevel').val();
           if(themalevel == '-'){ 
              alert('난이도를 선택해 주세요.');
              $('#themalevel').focus();
              return false ;
           }
           
           /* 추천인원 선택 */
           var recommendpeople = $('#recommendpeople').val();
           if(recommendpeople == '-'){ 
              alert('추천인원을 선택해 주세요.');
              $('#recommendpeople').focus();
              return false ;
           }
           
           
           /* 이미지 체크 */
           /* 확장자 체크에 충족하면 true가 됩니다. */
           var image01 = $('#image01').val();
           if(image01 == ''){
              alert('대표 이미지는 필수 사항입니다.');
              return false ;
           }
           
           var isCheck = false ; /* 확장자 체크에 충족하면 true가 됩니다.
           const imgCheck = ['.png', '.jpg', '.PNG', '.JPG'] ; /* 확장자 체크용 배열 */
           for(var i=0 ; i < imgCheck.length ; i++){
              if(image01.endsWith(imgCheck[i])){
                 /* 왜 안됨?? equalsIgnoreCase(endsWith(imgCheck[i]))*/
                 isCheck = true ;
                 break ;
              }   
           }
           
           if(isCheck == false){
              alert('이미지의 확장자는 png 또는 jpg 형식이어야 합니다.');
              return false ;
           }
           
           
           
           
           /* 소요시간 */
           const NumberMsg = '는(은) 숫자 형식이어야 합니다.' ;
         var usetime = $('#usetime').val();
           
           if(usetime == '' || isNaN(usetime) == true){
              alert('소요시간' + NumberMsg);
              $('#usetime').focus();
              return false ;
           }else{
              var numUsetime = Number(usetime);
              if(numUsetime == ''){
                 alert('분단위 시간을 입력해주세요.');
                   $('#usetime').focus();
                   return false ;
              }
           }           
           
           /* 지역선택 */
           var area = $('#area').val();
           if(area == '-'){ /* 코딩할 때 option의 value 속성을 하이폰으로 설정했습니다. */
              alert('지역을 선택해 주세요.');
              $('#area').focus();
              return false ;
           }
           
           /* 상세주소 */
           var address = $('#address').val();
           if(address.length < 1 || address.length > 100){
              alert('상세주소를 입력해주세요.');
              $('#address').focus() ;
              return false ;
           }
           
           /* 이용금액 */
           var price = $('#price').val();           
           if(price == '' || isNaN(price) == true){
              alert('이용금액' + NumberMsg);
              $('#price').focus();
              return false ;
           }else{
              var numPrice = Number(price);
              if(numPrice == ''){
                 alert('가격을 입력해주세요.');
                   $('#price').focus();
                   return false ;
              }
           }           
           
           /* 테마설명 */
           var comments = $('#comments').val();
           if(comments.length < 1 || comments.length > 100){
              alert('테마설명을 입력해주세요.');
              $('#comments').focus() ;
              return false ;
           }        
           
           /* 등록일 */
           var uploaddate = $('#uploaddate').val();
           
           var regex = /^\d{4}[\/-][01]\d{1}[\/-][0123]\d{1}$/ ;
           var result = regex.test(uploaddate) ;
           
           if(result == false){
              alert('날짜 형식은 반드시 yyyy/mm/dd 형식으로 작성해 주세요.');
              $('#uploaddate').focus() ;
              return false ;
           } 
           
           alert(${requestScope.bean.productnum}+"의 수정이 완료되었습니다.");
        }
     </script>
     
</head>
<body>
   <div class="container mt-3 col-md-5">
      <h2>상품수정</h2>
      <p>상품을 수정하는 페이지입니다.</p>
      
      <form action="<%=withFormTag%>" method="post" enctype="multipart/form-data">
         <input type="hidden" name="command" value="prUpdate">
         <input type="text" name="pageNumber" value="<%=request.getParameter("pageNumber")%>">
         <input type="text" name="pageSize" value="<%=request.getParameter("pageSize")%>">
         <input type="text" name="mode" value="<%=request.getParameter("mode")%>">
         <input type="text" name="keyword" value="<%=request.getParameter("keyword")%>">

         <div class="input-group">
            <span class="input-group-text col-md-2">상품 번호</span> 
            <input id="fakeproductnum" name="fakeproductnum" type="number" disabled="disabled" class="form-control" placeholder="" value="${requestScope.bean.productnum}">
            <input id="productnum" name="productnum" type="hidden" value="${requestScope.bean.productnum}">
         </div> 
         <div class="input-group">
            <span class="input-group-text col-md-2">테마명</span> 
            <input id="title" name="title" type="text" class="form-control" placeholder="" 
            value="${requestScope.bean.title}">
         </div>
         <div class="input-group">
            <span class="input-group-text col-md-2">장르</span> 
            <select id="genre" name="genre" class="form-select form-select-lg">
               <option value="-">-- 장르를 선택해 주세요.</option>
               <option class="th_genre" value="공포">공포</option>
                 <option class="th_genre" value="스릴러">스릴러</option>
                 <option class="th_genre" value="코믹"> 코믹</option>
                 <option class="th_genre" value="역사">역사</option>
                 <option class="th_genre" value="판타지">판타지</option>
                 <option class="th_genre" value="감동">감동</option>
                 <option class="th_genre" value="SF">SF</option>
                 <option class="th_genre" value="동화">동화</option>
                 <option class="th_genre" value="야외">야외</option>
                 <option class="th_genre" value="기타">기타</option>
                 
            </select>
         </div>
         <!-- value값 숫자로 치환한번 해야함 (sql에서는 number임) -->
         <div class="input-group">
            <span class="input-group-text col-md-2">난이도</span> 
            <select id="themalevel" name="themalevel" class="form-select form-select-lg">
               <option value="-">-- 난이도를 선택해 주세요.</option>
               <option class="th_level" value="1">1</option>
                 <option class="th_level" value="2">2</option>
                 <option class="th_level" value="3">3</option>
                 <option class="th_level" value="4">4</option>
                 <option class="th_level" value="5">5</option>
                 <option class="th_level" value="6">6</option>
            </select>
         </div>
         <div class="input-group">
            <span class="input-group-text col-md-2">추천인원</span>
            <select id="recommendpeople" name="recommendpeople" class="form-select form-select-lg">
               <option value="-">-- 추천인원을 선택해 주세요.</option>
               <option class="th_people" value="1인">1인</option>
                 <option class="th_people" value="2인">2인</option>
                 <option class="th_people" value="3인">3인</option>
                 <option class="th_people" value="4인">4인</option>
                 <option class="th_people" value="5인">5인</option>
                 <option class="th_people" value="6인">6인</option>
              </select>
         </div>
         <div class="input-group">
            <input type="hidden" name="deleteImage01" value="${requestScope.bean.image01}">
            <span class="input-group-text col-md-2">이미지01</span> 
            <input id="image01" name="image01" type="file" class="form-control" placeholder="">
         </div>
         <div class="input-group">
            <input type="hidden" name="deleteImage02" value="${requestScope.bean.image02}">
            <span class="input-group-text col-md-2">이미지02</span> 
            <input id="image02" name="image02" type="file" class="form-control" placeholder="">
         </div>
         <div class="input-group">
            <span class="input-group-text col-md-2">소요시간</span> 
            <input id="usetime" name="usetime" type="text" class="form-control" placeholder="분 단위로 입력해주세요." 
            value="${requestScope.bean.usetime}">
            <span class="input-group-text col-md-2">분</span>
         </div>         
         <div class="input-group">
            <span class="input-group-text col-md-2">지역</span> 
            <select id="area" name="area" class="form-select form-select-lg">
               <option value="-">-- 지역을 선택해 주세요.</option>
               <option class="th_area" value="홍대">홍대</option>
                 <option class="th_area" value="강남">강남</option>
                 <option class="th_area" value="논현">논현</option>
              </select>
         </div>
         <div class="input-group">
            <span class="input-group-text col-md-2">상세주소</span> 
            <input id="address" name="address" type="text" class="form-control" placeholder=""
            value="${requestScope.bean.address}">
         </div>   
         <div class="input-group">
            <span class="input-group-text col-md-2">이용금액</span> 
            <input id="price" name="price" type="text" class="form-control" placeholder="" 
            value="${requestScope.bean.price}">
         </div>      
         <div class="input-group">
            <span class="input-group-text col-md-2">테마설명</span> 
            <textarea class="form-control" id="comments" name="comments" rows="10" cols="50">
               "${requestScope.bean.comments}"
            </textarea>
         </div>   
         
         <div class="input-group">
            <span class="input-group-text col-md-2">테마시간</span> 
               <div>
               첫번째 테마시간 : <input id="time01" name="time01" class="thema_time" type="text" class="form-control" placeholder="" value="${requestScope.bean.time01}"><br/>
               두번째 테마시간 : <input id="time02" name="time02" class="thema_time" type="text" class="form-control" placeholder="" value="${requestScope.bean.time02}"><br/>
               세번째 테마시간 : <input id="time03" name="time03" class="thema_time" type="text" class="form-control" placeholder="" value="${requestScope.bean.time03}"><br/>
               네번째 테마시간 : <input id="time04" name="time04" class="thema_time" type="text" class="form-control" placeholder="" value="${requestScope.bean.time04}"><br/>
               다섯번째 테마시간 : <input id="time05" name="time05" class="thema_time" type="text" class="form-control" placeholder="" value="${requestScope.bean.time05}"><br/>
               </div>
         </div>   
         
         <div class="input-group">
            <span class="input-group-text col-md-2">등록일</span> 
            <input id="uploaddate" name="uploaddate" type="datetime" class="form-control" placeholder=""
            value="${requestScope.bean.uploaddate}">
         </div>
         <div id="buttonset" class="input-group">
            <button type="submit" class="btn btn-primary btn-lg"
               onclick="return validCheck();"> 
               수정
            </button>
            &nbsp;&nbsp;&nbsp;
            <button type="reset" class="btn btn-secondary btn-lg">초기화</button>
         </div>
      </form>
   </div>
</body>
</html>