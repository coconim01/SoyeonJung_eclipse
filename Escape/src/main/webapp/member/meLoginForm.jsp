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
   /*나눔고딕*/
   @import url('https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap');
   .input-group {
      margin: 7px;
   }
   
   .input-group-text {
      display: block;
      margin-left: auto;
      margin-right: auto;
   }
   
   #buttonset {
      margin-top: 15px;
   }
   
   .radio_gender, .chk_hobby {
      font-size: 1.1rem;
   } /* 주위 글꼴의 1.1배 */

   .btn{border: none; width:100px;
         height:30px;font-size:16px;padding-bottom:12px; padding-top:5px; 
         background-color :#03D0B7; color: white; font-weight:600;
         font-family: 'Nanum Gothic', sans-serif;}
         
         
   h1, p{
      font-weight: Extrabold;
      color: black;   
      font-family: 'Nanum Gothic', sans-serif; /* 글꼴 */
      }
      
      
/* 새로운 거 */      
      
      *{
    padding: 0;
    margin: 0;
    border: none;

}
body{
    font-size: 14px;
   font-family: 'Nanum Gothic', sans-serif;
    font-weight: Extrabold;
    height: 80vh;
    
}

.container-wrap {
   display: flex;
   justify-content: center;
   align-items: center;		/* 수직 정렬 */
   height: 100%;
}

.login-wrapper{
    width: 400px;
    height: 350px;
    margin: 0 auto;		/* 수평 정렬 */
    box-sizing: border-box;
}

.login-wrapper > h2{
    font-size: 24px;
    color: black;
    margin-bottom: 20px;
}

a {
   text-decoration: none;
   text-align: center;
}

a>span {
   height: 50px;
   line-height: 50px;
   
}

#login-form > input, #login-form > a{
    width: 100%;
    height: 48px;
    padding: 0;
    box-sizing: border-box;
    margin-bottom: 16px;
    border-radius: 6px;
    background-color: #F8F8F8;
}
#login-form > input::placeholder, #login-form > a::placeholder{
    color: #D2D2D2; /* 창 안 글씨 색깔 */
    
}

#login-form > input[type="submit"]{
    color: #fff;
    font-size: 16px;
   background-color :#03D0B7;
    margin-top: 1px;
}

#login-form > a[type="button"]{
    color: #fff;
    font-size: 16px;
  background-color :#03D0B7;
    margin-top: 1px;
}


/* 기존의 checkbox를 없애고 label를 이용하여  디자인 시안과 동일하게 변경시켜줍니다. */

#login-form > input[type="checkbox"]{
    display: none;
}
#login-form > label{
    color: #999999;
}
#login-form input[type="checkbox"] + label{
    cursor: pointer;
    padding-left: 26px;
    background-image: url("checkbox.png");
    background-repeat: no-repeat;
    background-size: contain;
}
#login-form input[type="checkbox"]:checked + label{
    background-image: url("checkbox-active.png");
    background-repeat: no-repeat;
    background-size: contain;
}

</style>
<link rel="stylesheet"
   href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">

<link rel="stylesheet" href="/resources/demos/style.css">

<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
<script type="text/javascript">
   $(document).ready(function() {
      /* $('#hiredate').datepicker(); */
      $('#hiredate').datepicker({
         dateFormat : "yy/mm/dd"
      });
      
      var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
      var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
         return new bootstrap.Tooltip(tooltipTriggerEl)
      });      
   });
   
   

</script>

<!-- css로 연결해주는 링크 -->
   <link rel="stylesheet" href="./freeBoardDetail.css">
</head>
<body>
   <div class="container-wrap">
      <div class="container mt-3 col-md-5">
         
         <div class="login-wrapper">
              <h1 class="login-title">Login</h1>
              <form method="post" action="<%=withFormTag%>" id="login-form">
                    <input type="hidden" name="command" value="meLogin">
                    
                  <input type="text" name="id" placeholder="  Id" id="id">
                  
                  <input type="password" name="password" placeholder="  Password" id="password">
                  


                     <input type="submit" value="로그인"></input>
                  <a type="button" href="<%=notWithFormTag %>meInsert" class="meUpdate-btn"><span>회원 가입</span></a>

              </form>
          </div>
      </div>
   </div>
</body>
</html>