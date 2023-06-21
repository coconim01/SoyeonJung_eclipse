/**
 * 페이지명 : 리뷰게시판 상세페이지 JavaScript
 * 작성일자 : 2023-03-14
 * 작성자 : 정영우
 */
// 화면을 준비시킨다.
$(document).ready(function(){
    /*********************
	 * 삭제버튼 클릭했을때
	 *********************/
    $('#deleteBtn').click(function(){
		
		
		// 로그인 아이디
		var loginId = $('#loginId').val();
		// 작성자 아이디
		var writerId = $('#writerId').val();
		
		// 로그인 아이디와 작성자의 아이디가 맞지 않으면	
		if(loginId != writerId){
			
			alert("해당글의 작성자만 삭제할 수 있습니다");
			
		}// 로그인 아이디와 작성자의 아이디가 맞으면
		else{
			
			var confirmMsg = confirm("게시글을 정말로 삭제하시겠습니까?");
			
			// 확인
			if(confirmMsg){
				// 순번값
				var reviewBoardSn = $('#reviewBoardSn').val();
				
				// 등록방식 - '삭제'
				var regMode = "delete";
				
				// Ajax택배회사에 위 3개의 값을 담아서 url의 주소로 보낸다.
				$.ajax({
					// 1. get/post
					// 2. url
					// 3. 받는 타입 json
					// 4. 보내줄 데이터 3개
					// 5. 서버 갔다왔을때 실행할 함수
					type : "get"
					, url : "/Escape/ReviewBoardDetailDeleteForm"
					, dataType :"JSON"
					, data:{
					  
						  reviewBoardSn : reviewBoardSn		// 게시물 순번
						, regMode : regMode				// 등록방식
					}
					, async : false		
					, success : function(result){
						
						// 게시물 삭제수가 0보다 크면
						if(result.deleteCnt > 0){
							alert("게시물 삭제가 완료되었습니다.");
						    location.replace("../reviewBoard/reviewBoard.jsp");
						}
					}				
				});				
				
				}// 취소
				else{
	
				}
		     }
		});
	/*********************
	 * 수정 버튼 클릭했을때
	 *********************/
	
	$('#updateBtn').click(function(){
	var confirmMsg = confirm("게시글을 정말로 수정하시겠습니까?");
	// update
//	UPDATE REVIEW_THEME SET
//REVIEW_THEME_SCORE =  7  /*파라미터 1_별점(숫자)*/
//,REVIEW_THEME_CONTENTS = '내용입니다.'  /*파라미터 2_리뷰내용(문자)*/
//WHERE REVIEW_SN = 11  /*파라미터 3_ 리뷰게시판테이블 순번*/
//;

	// 1. 리뷰게시판테이블 순번
	// var reviewBoardSn = $('#reviewBoardSn').val();
	
	// 2. 리뷰내용(문자)
	// var reviewContent = $('#reviewContent').val();
	
	// 3. 별점(숫자)
	// var scoreStar = $('#scoreStar').val();
	
	
	
		if(confirmMsg){
			// 로그인 아이디
			var loginId = $('#loginId').val();
			// 작성자 아이디
			var writerId = $('#writerId').val();
			
			// 로그인 아이디와 작성자의 아이디가 맞지 않으면	
			if(loginId != writerId){
				
				alert("해당글의 작성자만 수정할 수 있습니다");
			}
			
			
			// 리뷰내용의 disabled를 풀어준다.
			$('#reviewContent').prop('readonly', false);
			
			// 등록버튼이 보여진다. 
			$('#insertBtn').attr("type","button");
			
			// 수정버튼은 안보이게한다.
			$('#updateBtn').attr("type","hidden");
			
			// 글내용에 포커스를 준다.
			var len = $('#reviewContent').val().length;
			$('#reviewContent').focus();
			$('#reviewContent')[0].setSelectionRange(len, len);
		}
	});
	
	
//	var score = $('#scoreStarHidden').val();
//	
//	$("input:radio[name='scoreStar']:radio[value="+score+"]").prop('checked', true); // 선택하기
//

	/*********************
	 * 등록 버튼 클릭했을때
	 *********************/
    $('#insertBtn').click(function(){
		
		var confirmMsg = confirm("게시글을 정말로 등록하시겠습니까?");
		
		// 확인
		if(confirmMsg){	
			
			// 테마명값가져오기
			var themeNm = $('select[name=themeNm] option:selected').val();
			
			// 로그인한 아이디값 가져오기
			var loginId = $('#loginId').val();
			
			// 별점값 가져오기
			var scoreStar = $('input[name=scoreStar]:checked').val();
						
			// 리뷰내용값 가져오기
			var reviewContent = $('#reviewContent').val();
			
			// 리뷰순번값 가져오기
			var reviewBoardSn = $('#reviewBoardSn').val();
			// 등록방식 -수정
			var regMode = "update";
			if(themeNm == 0){
				alert("테마명을 선택해주세요");
			}
			
			$.ajax({
				type : "get"
				, url : "/Escape/ReviewBoardDetailUpdateForm"
				, data:{
					 themeNm : themeNm
					, loginId : loginId
					, scoreStar : scoreStar
					, reviewContent : reviewContent
					, reviewBoardSn : reviewBoardSn
					, regMode : regMode
				}
				, async : false
				, success : function(result){
					// 등록버튼 숨김. 
					$('#insertBtn').attr("type","hidden");
					// 수정버튼 보임.
					$('#updateBtn').attr("type","button");
					// 리뷰내용 외곽선 없애기 (outline 처리)
					$("#reviewContent").css("outline","0px solid");
					// 테마명을 리드온리로 바꿔줌
					$('#themeNm').prop('readonly', true);
					// 리뷰내용을 리드온리로 바꿔줌
					$('#reviewContent').prop('readonly', true);
					
					
					// 리뷰글 등록이 완료되었습니다. 알러트
					alert("리뷰글 등록이 완료되었습니다.");
					location.replace("../reviewBoard/reviewBoard.jsp");
				}
				
			});			
		} // 컨펌
		
	
		
	});
});

	
