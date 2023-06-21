/**
 * 페이지명 : 자유게시판 글쓰기페이지 JavaScript
 * 작성일자 : 2023-03-07
 * 작성자 : 정영우
 */

 $(document).ready(function(){
	 
	/*********************
	 * 취소버튼 클릭 이벤트
	 *********************/ 
	$('#cancelBtn').click(function(){
		
		location.href="../freeBoard/freeBoard.jsp";
		
	});
	 
	/**********************
	 * 글쓰기등록버튼 클릭 이벤트
	 **********************/ 
	$('#insertBtn').click(function(){
		
		var confirmMsg = confirm("게시글을 정말로 등록하시겠습니까?");
		
		// 확인
		if(confirmMsg){
			
			// 1. 글제목의 값가져오기
			var subject = $('#subject').val();
			
			// 2. 글내용의 값가져오기
			var content = $('#content').val();
			
			// 3. 아이디 값가져오기
			var memberId = $('#memberId').val();
			
			// 4. 등록방식 등록
			var regMode = "reg";
			
			// 5. 글제목, 글내용 유효성 체크
			if(subject=="" || subject==null || subject==undefined){
				alert("글제목을 입력해주세요")
				return;
			}
			if(content=="" || content==null || content==undefined){
				alert("글내용을 입력해주세요")
				return;
			}
			
			
			// 6. 글제목, 글내용의 값을 컨트롤러로 넘긴다.(ajax이용)
			$.ajax({
				type : "get"
				, url : "/Escape/FreeBoardDetailRegForm"
				, dataType : "JSON"
				, data : {
					subject : subject
					, content : content
					, memberId : memberId
					, regMode : regMode
					
				}
				, async : false
				,success : function(result){
					
					// 게시물 등록수가 0보다 크면
					if(result.regCnt > 0){
						alert("게시물 등록이 완료되었습니다.");
					    location.replace("../freeBoard/freeBoard.jsp");
					}
					
					// 6. callBack함수에서 등록이 완료되면 자유게시판 목록으로 이동한다.
					
				 }
			});
	 	}
	});
	 
 });