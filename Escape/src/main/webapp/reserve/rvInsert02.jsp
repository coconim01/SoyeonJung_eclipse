<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./../common/bootstrap5.jsp"%>
<%@ include file="./../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- css로 연결해주는 링크 -->
<link rel="stylesheet" href="./freeBoardDetail.css">
<title>Insert title here</title>
<style type="text/css">
   @import url('https://fonts.googleapis.com/css2?family=Dongle:wght@300&display=swap');
   /*body {font-family: 'Dongle', sans-serif;} */
   @import url('https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap');
   /*나눔고딕*/

   #buttonset{margin-top:15px;}
   .btn{border: none; width:100px;height:30px;font-size:16px;padding-bottom:12px; 
        padding-top:5px;background-color :#03D0B7; color: white; font-weight:600;}

   input[type="text"]:disabled{background: white;} /* 여기서만 쓰기~ */
   .input-group-text, .form-control {font-family: 'Nanum Gothic', sans-serif;font-weight: Bold; /* 글꼴 */}


</style>

<script type="text/javascript">
		
		const maxPurchaseSize = 6 ; /* 최대 참여인원 */
	
		$(document).ready(function(){	 
			
			$('#totalprice').text('${requestScope.bean.price*2}'+"원") ; /* 최초 시작시 금액 2인 기준으로 셋팅 */
			
			var price = '${requestScope.bean.price}' ;
			
			$('#people').innerWidth($('.minus').innerWidth() - 3) ;
			
			$('.plus').click(function(){ /* 사용자가 + 버튼을 클릭함 */
				var people = $('#people').val();
				if(people == maxPurchaseSize){
					swal('최대 인원수는 ' + maxPurchaseSize + '명 입니다.'); 
					return ; /* return 구문으로 함수 더이상 실행 않하도록 함*/
				}
				/* Number는 Integer.parseInt()와 동일한 효과 */
				var newpeople = Number(people) + 1;
				if(people == ''){
					$('#people').val('1');
				}else{
					$('#people').val(newpeople);
				}
				
				var amount = newpeople*price ;				
				$('#totalprice').text(amount.toLocaleString()) ;
			});
			
	  		$('.minus').click(function(){ /* 사용자가 - 버튼을 클릭함 */
	  			var people = $('#people').val() ;
	  			if(people == '1'){
	  				swal('최소 인원수는 1명 입니다.');
	  				return ;
	  			}

	  			var newpeople = Number(people) - 1 ;
	  			if(people == ''){
	  				$('#people').val('0');
	  				$('#totalprice').text(0);
	  			}else{
	  				$('#people').val(newpeople);
	  				
	  				var amount = newpeople*price ;				
					$('#totalprice').text(amount.toLocaleString()) ;	  				
	  			}
	  		});
			
			/* blur 이벤트는 포커스를 잃어 버릴때 반응합니다. */
			$('#people').blur(function(){
				var people = $('#people').val() ;
				
				/* isNaN() 함수는 숫자가 아니면 true를 반환해 줍니다. */
				if(people == '' || isNaN(people) == true){
					swal('1이상 ' + maxPurchaseSize + '이하의 숫자만 가능합니다.') ;
					$('#people').val('0') ;
					$('#people').focus();
					return ;
				}
				
				if(isNaN(people) == false){ /* 숫자 형식으로 입력된 경우 */
					var newpeople = Number(people) ;
					if(newpeople < 1 || newpeople > maxPurchaseSize){
						swal('1이상 ' + maxPurchaseSize + '이하의 숫자만 가능합니다.') ;
						$('#people').val('0') ;
						$('#people').focus();
						return ;	
					}
				}
				
			});
		});
	</script>

</head>
<body>
<form action="<%=withFormTag%>" method="get">
<input type="hidden" name="command" value="rvCalculate">
<div class="container mt-3 col-md-5">
      <h1>테마 예약 페이지</h1>
      
         <div class="input-group">
            <span class="input-group-text col-md-2">예약일 </span> 
            <input id="revdate" name="revdate" disabled="disabled" type="text" class="form-control" placeholder="" value="${requestScope.revdate}">
         </div> 
         <div class="input-group">
            <span class="input-group-text col-md-2">예약시간</span> 
            <input id="revtime" name="revtime" disabled="disabled" type="text" class="form-control" placeholder="" value="${requestScope.revtime}">
         </div>
         <div class="input-group">
            <span class="input-group-text col-md-2">테마명</span> 
            <input id="title" name="title" disabled="disabled" type="text" class="form-control" placeholder="" value="${requestScope.bean.title}">
         </div>
         <div class="input-group">
            <span class="input-group-text col-md-2">예약자명</span> 
            <input id="name" name="name" disabled="disabled" type="text" class="form-control" placeholder="" value="${sessionScope.loginfo.nickname}">
         </div>
         <div class="input-group">
            <span class="input-group-text col-md-2">핸드폰번호</span> 
            <input id="phone" name="phone" disabled="disabled" type="text" class="form-control" placeholder="" value="${sessionScope.loginfo.phone}">
         </div>

         <div class="input-group">
            <span class="input-group-text col-md-2">이용가격</span> 
            <input id="price" name="price" disabled="disabled" type="text" class="form-control" placeholder="" value="${requestScope.bean.price}">
         </div>
         <div class="input-group">
            <span class="input-group-text col-md-2">주소</span> 
            <input id="address" name="address" disabled="disabled" type="text" class="form-control" placeholder="" value="${requestScope.bean.address}">
         </div>
         
         <div class="input-group">
            <span class="input-group-text col-md-2">참여인원</span>
            <ul class="pagination" id="people" style="margin:10px;">
               <li class="page-item"><a class="page-link minus" href="#"> - </a></li>
               <li class="page-item"><a class="page-link" href="#">
                   <input type="text" id="people" name="people" size=2 maxlength=6 value="">
               </a></li>
               <li class="page-item"><a class="page-link plus" href="#"> + </a></li>
            </ul>
         </div>
         
         <div class="input-group">
            <span class="input-group-text col-md-2">총 결제금액</span> 
            <span class="form-control" id="totalprice"></span>
         </div>
         
         <div id="buttonset" class="input-group">
            <button type="submit" class="btn btn-primary btn-lg" onclick="return validCheck();"> 
               예약완료
            </button>
         </div>
      </form>
   </div>
         
<%--    <p>예약일 : ${requestScope.revdate}</p>
   <p>예약시간 : ${requestScope.revtime} </p>
   <p>테마명 : ${requestScope.bean.title}</p>
   <p>예약자명 : {로그인포}</p>
   <p>핸드폰번호 : {로그인포} </p> --%>
   
   

</form>


</body>
</html>