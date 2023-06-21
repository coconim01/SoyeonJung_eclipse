/**
 * 페이지명 : 리뷰게시판 상세페이지 JavaScript
 * 작성일자 : 2023-03-14
 * 작성자 : 정영우
 */
// 화면을 준비시킨다.
$(document).ready(function(){

/*********************
	 * 취소버튼 클릭 이벤트
	 *********************/ 
	$('#cancelBtn').click(function(){
		
		location.href="../reviewBoard/reviewBoard.jsp";
		
	});


	/*********************
	 * 등록 버튼 클릭했을때
	 *********************/
    $('#insertBtn').click(function(){
		
		var confirmMsg = confirm("게시글을 정말로 등록하시겠습니까?");
		
		// 확인
		if(confirmMsg){	
			
			// 테마명값가져오기
			var themeNm = $('select[name=themeNm]').val();
			
			// 로그인한 아이디값 가져오기
			var loginId = $('#loginId').val();
			
			// 별점값 가져오기
			var scoreStar = $('input[name=scoreStar]:checked').val();
						
			// 리뷰내용값 가져오기
			var reviewContent = $('#reviewContent').val();
			
			// 리뷰순번값 가져오기
			var reviewBoardSn = $('#reviewBoardSn').val();
			
			
			if(themeNm == 0){
				alert("테마명을 선택해주세요");
			}
			
			$.ajax({
				type : "get"
				, url : "/Escape/ReviewBoardDetailRegForm"
				, data:{
					 themeNm : themeNm
					, loginId : loginId
					, scoreStar : scoreStar
					, reviewContent : reviewContent
					, reviewBoardSn : reviewBoardSn
				}
				, async : false
				, success : function(result){
					
					// 리뷰글 등록이 완료되었습니다. 알러트
					alert("리뷰글 등록이 완료되었습니다.");
					location.replace("../reviewBoard/reviewBoard.jsp");
				}
				
			});			
		} // 컨펌
	});

	
	
	
	
	
});
 