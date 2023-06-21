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

  	<script type="text/javascript">
  		$(document).ready(function(){
  	  		/* $('#uploaddate').datepicker(); */
  	  		$('#uploaddate').datepicker({dateFormat: "yy/mm/dd"});   	  		
  	  		
  	  		/* alert('${requestScope.categories}'); */
  	  		/* var categories = '${requestScope.categories}' ; */
  	  		/* console.log(categories); */
  	    	  	
  	  		/* $('#cateogory') */
  	  	 
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
  			
  			var isCheck = false ; /* 확장자 체크에 충족하면 true가 됩니다. */
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
  			
  			var regex = /^\d{4}\/[01]\d{1}\/[0123]\d{1}$/ ;
  			var result = regex.test(uploaddate) ;
  			
  			if(result == false){
  				alert('날짜 형식은 반드시 yyyy/mm/dd 형식으로 작성해 주세요.');
  				$('#uploaddate').focus() ;
  				return false ;
  			} 
  			
  			alert('방탈출 테마 등록이 완료되었습니다 ! :-)');
  		}
  	</script>
  	
  	<style type="text/css">
  	#productNum {display: none;}
  	
  	</style>
</head>
<body>
	<div class="container mt-3 col-md-5">
		<h2>방탈출 테마 등록</h2>
		<p>방탈출 테마를 등록하는 페이지입니다.</p>
		
		<form action="<%=withFormTag%>" method="post" enctype="multipart/form-data">
			<input type="hidden" name="command" value="prInsert">
			
			<div id="productNum" class="input-group">
				<span class="input-group-text col-md-2">상품 번호</span> 
				<input id="productNum" name="productNum" type="number" class="form-control" placeholder=""
					disabled="disabled" value="${requestScope.bean.productNum}">
			</div> 
			<div class="input-group">
				<span class="input-group-text col-md-2">테마명</span> 
				<input id="title" name="title" type="text" class="form-control" placeholder="" value="">
			</div>
			<div class="input-group">
				<span class="input-group-text col-md-2">장르</span> 
				<select id="genre" name="genre" class="form-select form-select-lg">
					<option value="-">-- 장르를 선택해 주세요.</option>
					<option value="fear">공포</option>
				  	<option value="thriller">스릴러</option>
				  	<option value="comic">코믹</option>
				  	<option value="history">역사</option>
				  	<option value="fantasy">판타지</option>
				  	<option value="moved">감동</option>
				  	<option value="sf">SF</option>
				  	<option value="fairytale">동화</option>
				  	<option value="outdoor">야외</option>
				  	<option value="etc">기타</option>
				  	
				</select>
			</div>
			<!-- value값 숫자로 치환한번 해야함 (sql에서는 number임) -->
			<div class="input-group">
				<span class="input-group-text col-md-2">난이도</span> 
				<select id="themalevel" name="themalevel" class="form-select form-select-lg">
					<option value="-">-- 난이도를 선택해 주세요.</option>
					<option value="1">★</option>
				  	<option value="2">★★</option>
				  	<option value="3">★★★</option>
				  	<option value="4">★★★★</option>
				  	<option value="5">★★★★★</option>
				  	<option value="6">★★★★★★</option>
				</select>
			</div>
			<div class="input-group">
				<span class="input-group-text col-md-2">추천인원</span>
				<select id="recommendpeople" name="recommendpeople" class="form-select form-select-lg">
					<option value="-">-- 추천인원을 선택해 주세요.</option>
					<option value="one">1인</option>
				  	<option value="two">2인</option>
				  	<option value="three">3인</option>
				  	<option value="four">4인</option>
				  	<option value="five">5인</option>
				  	<option value="six">6인</option>
			  	</select>
			</div>
			<div class="input-group">
				<span class="input-group-text col-md-2">이미지01</span> 
				<input id="image01" name="image01" type="file" class="form-control" placeholder="">
			</div>
			<div class="input-group">
				<span class="input-group-text col-md-2">이미지02</span> 
				<input id="image02" name="image02" type="file" class="form-control" placeholder="">
			</div>
			
			<div class="input-group">
				<span class="input-group-text col-md-2">소요시간</span> 
				<input id="usetime" name="usetime" type="text" class="form-control" placeholder="분 단위로 입력해주세요." value="">
				<span class="input-group-text col-md-2">분</span>
			</div>			
			<div class="input-group">
				<span class="input-group-text col-md-2">지역</span> 
				<select id="area" name="area" class="form-select form-select-lg">
					<option value="-">-- 지역을 선택해 주세요.</option>
					<option value="hongdae">홍대</option>
				  	<option value="gangnam">강남</option>
				  	<option value="nonhyeon">논현</option>
			  	</select>
			</div>
			<div class="input-group">
				<span class="input-group-text col-md-2">상세주소</span> 
				<input id="address" name="address" type="text" class="form-control" placeholder="">
			</div>	
			<div class="input-group">
				<span class="input-group-text col-md-2">이용금액</span> 
				<input id="price" name="price" type="text" class="form-control" placeholder="" value="">
			</div>		
			<div class="input-group">
				<span class="input-group-text col-md-2">테마설명</span> 
				<textarea class="form-control" id="comments" name="comments" rows="10" cols="50"></textarea>
			</div>	
			
			<div class="input-group">
				<span class="input-group-text col-md-2">등록일</span> 
				<input id="uploaddate" name="uploaddate" type="datetime" class="form-control" placeholder=""
				value="">
			</div>
			<div id="buttonset" class="input-group">
				<button type="submit" class="btn btn-primary btn-lg"
					onclick="return validCheck();"> 
					등록
				</button>
				&nbsp;&nbsp;&nbsp;
				<button type="reset" class="btn btn-secondary btn-lg">초기화</button>
			</div>
		</form>
	</div>
</body>
</html>