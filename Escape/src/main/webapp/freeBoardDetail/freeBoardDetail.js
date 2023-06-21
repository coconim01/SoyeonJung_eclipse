/**
 * 페이지명 : 자유게시판 상세페이지 JavaScript
 * 작성일자 : 2023-03-05
 * 작성자 : 정영우
 */
// 화면을 준비시킨다.
$(document).ready(function(){
	
	/*********************
	 * 수정 버튼 클릭했을때
	 *********************/
	$('.updateBtn').click(function(){
		var confirmMsg = confirm("게시글을 정말로 수정하시겠습니까?");
		
		// 확인
		if(confirmMsg){
			// 로그인 아이디
			var loginId = $('#loginId').val();
			// 작성자 아이디
			var writerId = $('#writerId').val();
			
			// 로그인 아이디와 작성자의 아이디가 맞지 않으면	
			if(loginId != writerId){
				
				alert("해당글의 작성자만 수정할 수 있습니다");
				
			}// 로그인 아이디와 작성자의 아이디가 맞으면
			else{
				// 글제목의 리드온리를 풀어준다.
				$('#subject').prop('readonly', false);
				
				// 글내용의 리드온리를 풀어준다.
				$('#content').prop('readonly', false);
			
				// 글제목의 태그(접근하는방법 id='#subject' class='.subject')에 disabled가 풀린다.
				// jquery로 input태그에 disabled를 풀어준다.
				$("#subject").removeAttr("disabled");
		
				// 글내용의 태그에 disabled가 풀린다.
				$("#content").removeAttr("disabled");
			
			 	// 글제목의 태그에 input 테두리 선이 보인다.
			 	$("#subject").css("outline","1px solid");
							
				// 글내용의 태그에 테두리 선이 보인다.
				$("#content").css("outline","1px solid");
				
				// 등록버튼이 보여진다. 
				$('#insertBtn').attr("type","button");
				// 수정버튼은 안보이게한다.
				$('#updateBtn').attr("type","hidden");
			
				// 글제목에 포커스를 준다.
				var len = $('#subject').val().length;
				$('#subject').focus();
				$('#subject')[0].setSelectionRange(len, len);
			}
		}		
		
		
	});
	
	
	/*********************
	 * 등록 버튼 클릭했을때
	 *********************/
    $('#insertBtn').click(function(){
		var confirmMsg = confirm("게시글을 정말로 등록하시겠습니까?");
		
		// 확인
		if(confirmMsg){
			// 제목값을 가져온다.
			var subject = $('#subject').val();
			// 내용값을 가져온다.
			var content = $('#content').val();
			// 순번값을 가져온다.
			var freeBoardSn = $('#freeBoardSn').val();
			// 등록방식 - '수정'
			var regMode = "update";
			
			// Ajax택배회사에 위 3개의 값을 담아서 url의 주소로 보낸다.
			$.ajax({
				// 1. get/post
				// 2. url
				// 3. 받는 타입 json
				// 4. 보내줄 데이터 3개
				// 5. 서버 갔다왔을때 실행할 함수
				type : "get"
				, url : "/Escape/FreeBoardDetailUpdateForm"
				, dataType :"JSON"
				, data:{
				  	  subject : subject 			// 글제목
					, content : content				// 글내용
					, freeBoardSn : freeBoardSn		// 게시물 순번
					, regMode : regMode				// 등록방식
					
				}
				, async : false		
				, success : function(result){
	
					// 등록버튼 숨김. 
					$('#insertBtn').attr("type","hidden");
					// 수정버튼 보임.
					$('#updateBtn').attr("type","button");
					// 글제목 외곽선 없애기 (outline 처리)
					$("#subject").css("outline","0px solid");
					// 글내용 외곽선 없애기 (outline 처리)
					$("#content").css("outline","0px solid");
					// 글제목을 리드온리로 바꿔줌
					$('#subject').prop('readonly', true);
					// 글내용을 리드온리로 바꿔줌
					$('#content').prop('readonly', true);
				
					// 화면에 변경된 조회데이터 뿌리기
					$('#subject').val(result[0].subject);
					$('#content').val(result[0].content);
	
					//수정이 완료되었습니다.알러트
					alert("게시물 수정이 완료 되었습니다.");
				}				
			});
		}
	});
	
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
				// 순번값을 가져온다.
				var freeBoardSn = $('#freeBoardSn').val();
				
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
					, url : "/Escape/FreeBoardDetailDeleteForm"
					, dataType :"JSON"
					, data:{
					  
						  freeBoardSn : freeBoardSn		// 게시물 순번
						, regMode : regMode				// 등록방식
					}
					, async : false		
					, success : function(result){
						
						// 게시물 삭제수가 0보다 크면
						if(result.deleteCnt > 0){
							alert("게시물 삭제가 완료되었습니다.");
						    location.replace("../freeBoard/freeBoard.jsp");
						}
					}				
				});				
				
			}// 취소
			else{

			}
			
			
			
			
		}
	});
	
	/*********************
	 * 댓글등록버튼을 클릭했을때
	 *********************/
	$('#replyRegBtn').click(function(){
			/*파라미터1_게시글순번*/
			/*파라미터2_로그인한 회원아이디*/	
			/*파라미터3_댓글내용*/
			var freeBoardSn = $('#freeBoardSn').val()
			var loginId = $('#loginId').val()
			var replyInput = $('#replyInput').val()
			
				//로그인한 아이디가 없으면 댓글쓰기 막기.
				if(loginId == '' || loginId == null || loginId == undefined){
						alert("로그인을 해야 댓글쓰기가 가능합니다.")
						return false;
				}
				
			$.ajax({
				  type : "get"
				, url : "/Escape/FreeBoardDetailReplyRegForm"
				, dataType : "JSON"
				, data : {
					  freeBoardSn : freeBoardSn		// 게시글 순번
					, loginId : loginId				// 로그인한 회원 아이디
					, replyInput : replyInput		// 댓글 입력한 내용
				}
				, async : false
				, success : function(result){
					
					var html = '';
					html += '<table class="table" width=100% id="detailTable" style="margin-top:10px;">';
					html += '<thead align="left" >';
					html += '<tr>';
					html += '<th width="100px">댓글쓴닉네임</th>';
					html += '<th >댓글내용</th>';
					html += '<th width="200px">댓글등록일자</th>';
					html += '</tr>';
					html += '</thead>';
					html += '<tbody>';
					html += '<c:forEach var="replyDto" items="${replyDtoList}">';
					
					// 댓글 인서트수가 0보다 크면
					if(result[0].insertCnt > 0){
						$.each(result,function(idx, obj){
							html += '<tr>';
							html += '<td>'+obj.nickName+'</td>';
							html += '<td>'+obj.contents+'</td>';
							html += '<td>'+obj.regDt+'</td>';
							html += '</tr>';
						});
							alert("댓글 등록이 완료되었습니다.");
					}else{
						alert("댓글 등록에 실패하였습니다.");
					}
					html += '</c:forEach>';
					html += '</tbody>';
					html += '</table>';
					
					$('#detailTable').html(html);
				}
			});
	});
});
 