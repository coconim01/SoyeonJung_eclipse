<!-- html 주석입니다. -->
<%-- jsp 주석입니다. --%>
<%-- 골뱅이로 시작하는 항목은 지시어입니다. --%>
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
@import url('https://fonts.googleapis.com/css2?family=Dongle:wght@300&display=swap');
/*body {font-family: 'Dongle', sans-serif;} */
@import url('https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap');
/*나눔고딕*/
   
   .input-group{margin: 7px; accent-color: #03D0B7;} /* 체크박스 */
   
   .input-group-text{ /* 아이디 이름 비밀번호...  */
      display:block;
      margin-left:auto;
      margin-right:auto;
      font-weight: Bold;
      font-family: 'Nanum Gothic', sans-serif;
   }
   .form-control{ /* 체크박스랑 라디오 글자 */
      display:block;
      margin-left:auto;
      margin-right:auto;
      font-weight: Bold;
      font-family: 'Nanum Gothic', sans-serif;
   }
   
   #buttonset{margin-top:15px; font-family: 'Nanum Gothic', sans-serif;}
   .radio_gender, .chk_theme{font-size:15px;} 
   .btn{border: none; width:100px;
         height:30px;font-size:16px;padding-bottom:12px; padding-top:5px; 
         background-color :#03D0B7; color: white; font-weight:600;}
   h1 { margin-bottom: 10px;}      
   
</style>
   <link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
   
     <link rel="stylesheet" href="/resources/demos/style.css">
          
     <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
     <script type="text/javascript">
        $(document).ready(function(){
             /* $('#hiredate').datepicker(); */
             $('#hiredate').datepicker({dateFormat: "yy/mm/dd"}); 
                          
             /* value 속성의 값이 일치하는 항목에 대하여 체크 on 시킵니다. */ 
             
             $("input[value='${bean.gender}']").attr('checked', true) ; 
             
             var themeCheck = '${requestScope.bean.theme}'; /* 과거 선택했던 Checkbox 목록 */
             
         /* 과거 선택했던 Checkbox 목록 */
             var checkThemes = $("input[name='theme']") ; 
                            
              for(var i=0 ; i < checkThemes.length ; i++){
                 
                 var idx = themeCheck.indexOf(checkThemes[i].value);
                 
                 if(idx >= 0){ /* 해당 종목이 문자열에 포함 되어 있으면 반환 값이 0이상입니다. */
                 checkThemes[i].checked= true; 
                 }
              }
        });
        
        function validCheck(){
           /* alert('반환 값이 false이면 이벤트 전파가 되지 않습니다.'); */
      		var msg = confirm(" 수정하시겠습니까?")
      		
			if (msg == true) {	var nickname = $('#nickname').val();           
            if(nickname.length < 1 || nickname.length > 10){              
                $('#nickname').focus();
                swal('별명은 1자리 이상 8자리 이하로 입력해 주세요.');
                return false ;
             }           
       
             var password = $('#password').val();           
             if(password.length < 5 || password.length > 12){
            	 swal('비밀 번호는 첫글자는 소문자로 5자리 이상 12자리 이하로 입력해 주세요.');
                $('#password').focus();
                return false ;
             }              
           
            var regex = /^[a-z]\S{4,11}$/ ; /* 정규 표현식 */
            
            var result = regex.test(password) ;
            
          if(result == false){
             swal('첫 글자는 반드시 소문자로 작성되어야 합니다.');
             return false ; 
          }
          
            
            var radioList = $("input[type='radio']:checked");
            if(radioList.length == 0){
            	swal('성별을 선택해 주세요.'); 
               return false ;
            }
            
            var checkList = $("input[type='checkbox']:checked");
            if(checkList.length < 2){
            	swal('취미는 최소 2개 이상 반드시 선택해야 합니다.'); 
               return false ;
            }
            
            var phone = $('#phone').val();           
           var regex = /^(?:(010\-\d{4})|(01[1|6|7|8|9]\-\d{3,4}))\-(\d{4})$/;
           var result = regex.test(콜) ;
           
            if(result == false){
              swal('핸드폰 번호를 확인 해주세요');
               return false;
             } 
            
            /* alert(id + '/' ) ; */
                 
            
         return true ;	//확인 버튼 클릭 시
		
			}else {		//취소 버튼 클릭 시
				return false;
			}     

        }
     </script>
     <!-- css로 연결해주는 링크 -->
   <link rel="stylesheet" href="./freeBoardDetail.css">
</head>
<body>
   <div class="container mt-3 col-md-5">
      <h1>회원 정보 수정</h1>
      <p>나의 정보를 수정하는 페이지입니다.</p>
      
      <form action="<%=withFormTag%>" method="post">
      <input type="hidden" name="command" value="meUpdate">
      
         <div class="input-group">
            <span class="input-group-text col-md-2">아이디</span> 
            <input id="fakeid" name="fakeid" disabled="disabled" type="text" class="form-control" placeholder="" value="${requestScope.bean.id}">
            <input id="id" name="id" type="hidden" value="${requestScope.bean.id}"> 
         </div> 
         <div class="input-group">
            <span class="input-group-text col-md-2">이름</span> 
            <input id="nickname" name="nickname" type="text" class="form-control" placeholder="" value="${requestScope.bean.nickname}">
         </div>
         <div class="input-group">
            <span class="input-group-text col-md-2">비밀 번호</span> 
            <input id="password" name="password" type="password" class="form-control" placeholder="" value="${requestScope.bean.password}">
         </div>
         <div class="input-group">
            <span class="input-group-text col-md-2">전화 번호</span> 
            <input id="phone" name="phone" type="text" class="form-control" placeholder="" value="${requestScope.bean.phone}">
         </div>

         
         <div class="input-group" >
            <span class="input-group-text col-md-2">테마</span> 
            <div class="form-control">         
               <label class="checkbox-inline chk_theme">
                  &nbsp;<input type="checkbox" name="theme" value="공포">공포
               </label>
               &nbsp;
               <label class="checkbox-inline chk_theme">
                  &nbsp;<input type="checkbox" name="theme" value="스릴러">스릴러
               </label>
               &nbsp;
               <label class="checkbox-inline chk_theme">
                  &nbsp;<input type="checkbox" name="theme" value="코믹">코믹
               </label>
               &nbsp;
               <label class="checkbox-inline chk_theme">
                  &nbsp;<input type="checkbox" name="theme" value="역사">역사
               </label>
               <label class="checkbox-inline chk_theme">
                  &nbsp;<input type="checkbox" name="theme" value="판타지">판타지
               </label>
               &nbsp;
               <label class="checkbox-inline chk_theme">
                  &nbsp;<input type="checkbox" name="theme" value="감동">감동
               </label>
               &nbsp;
               <label class="checkbox-inline chk_theme">
                  &nbsp;<input type="checkbox" name="theme" value="SF">SF
               </label>
               &nbsp;
               <label class="checkbox-inline chk_theme">
                  &nbsp;<input type="checkbox" name="theme" value="동화">동화
               </label>
                  <label class="checkbox-inline chk_theme">
                  &nbsp;<input type="checkbox" name="theme" value="야외">야외
               </label>
               
            </div>
         </div>         

         <div class="input-group"  >
            <span class="input-group-text col-md-2">성별</span> 
            <div class="form-control">         
               <label class="radio-inline radio_gender">
                  &nbsp;<input type="radio" name="gender" value="남자">남자
               </label>
               &nbsp;
               <label class="radio-inline radio_gender">
                  &nbsp;<input type="radio" name="gender" value="여자">여자
               </label>
            </div>
         </div>
      
            
         <div id="buttonset" class="input-group">
            <button type="submit" class="btn btn-dark btn-lg" onclick="return validCheck();"> 
               수정
            </button>
            
            &nbsp;&nbsp;&nbsp;
            
            <button type="reset" class="btn btn-dark btn-lg">초기화</button>
         </div>
      </form>
   </div>
   <script type="text/javascript">

	</script>
</body>
</html>