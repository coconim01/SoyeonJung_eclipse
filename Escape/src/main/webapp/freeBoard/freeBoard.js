/**
 * freeBoard.js
 * 자유게시판 목록 자바스크립트
 * 작성일자 : 2023-03-06
 * 작성자 : 정영우
 */

 $(document).ready(function(){
		
	/******************
	 * 조회버튼 클릭 이벤트
	 ******************/
 	$('.selectBtn').click(function(){
		
		// 1. text 값 
		var inputText = $('#srchData').val();
		// 2. select 값
		var selectValue = $("select[name=kindCd]").val();
		
		$.ajax({
			  type : "get"
			, url : "/Escape/FreeBoardSearchParam"
			// 데이터를 받는 방식
			, dataType : "JSON"
			, data : {
				  srchData :  inputText // 게시글 제목
				, selectData : selectValue // 게시글 작성자
			}
			,async : false 
			, success : function(result){
				
				var length = result.length;
				$('#totNum').text('(총 ' + length + '건)');
				
				
				var html = '';
				html += '<table id="table_id"class="table" width="100%" id="test" style="margin-top:10px">';
				html += '<thead>';
				html += '<tr>';
				html += '<th>NO</th>';
				html += '<th width="600px">제목</th>';
				html += '<th>닉네임</th>';
				html += '<th>등록일자</th>';
				html += '<th>조회수</th>';
				html += '<th>댓글수</th>';
				html += '</tr>';
				html += '</thead>';
				html += '<tbody id="tbody_id">';
				html += '<c:forEach var="freeBoard" items="${freeBoardList}">';
				if(result.length > 0){
					$.each(result,function(idx, obj){
						html += '<tr id="freeBoardList" value="">'; 
						html += '<td style="display: none; type="hidden">'+obj.memberId+'</td>';//0
						html += '<td style="display: none; type="hidden">'+obj.freeBoardSn+'</td>';//1
						html += '<td>'+obj.no+'</td>';//2
						html += '<td width="600px">'+obj.subject+'('+obj.replyCnt+')</td>';//3
						html += '<td>'+obj.nickname+'</td>';//4
						html += '<td>'+obj.regDt+'</td>';//5
						html += '<td>'+obj.readCnt+'</td>';
						html += '<td>'+obj.replyCnt+'</td>';
						html += '</tr>';
					});
				}else{
					html += '<tr>'
					html += '<td colspan="7">조회된 내용이 없습니다.</td>';
					html += '</tr>'
				}
				html += '</c:forEach>';
				html += '</tbody>';
				html += '</table>';

				$('#table_id').html(html);
			}
		}); // ajax
	
	}); // click
	
	/***********************
	 * 글작성 버튼 클릭 이벤트
	 ***********************/
	// 
	$('#regBtn').click(function(){
		
		var loginId = $('#loginId').val();
		
		// 로그인하지 않은 상태이면
		if(loginId == '' || loginId == null || loginId == undefined){
			alert("로그인을 먼저 한 후 글작성이 가능합니다.")
		}// 로그인한 상태이면
		else{
		// 자유게시판 글작성 페이지로 이동.(freeBoardDetailReg.jsp)	 
		location.href = "../freeBoardDetailReg/freeBoardDetailReg.jsp?loginId="+ loginId;
		}
	
	});

	/***********************
	 * 행 클릭 이벤트
	 ***********************/
//	$('#tbody_id').on('click', 'tr', function(){
	$('#table_id').on('click', 'tr', function(){
		
		var table =document.getElementById('table_id');
  		var rowList = table.rows; // *1)rows collection
		
		var tr = $(this);
		var me = $(this).closest('tr');
        var td = tr.children();
		// 로그인한 아이디
		var loginId =  $('#loginId').val(); 
		
		// 테이블순번
		var freeBoardSn =  $(this).find("td:eq(1)").text(); 
		
		// 글순번
		var no =  $(this).find("td:eq(2)").text(); 
		
		// 글제목
		var subject =  $(this).find("td:eq(3)").text(); 
		
		// 닉네임
		var nickname =  $(this).find("td:eq(4)").text(); 
		
		 
		location.href = "../freeBoardDetail/freeBoardDetail.jsp?freeBoardSn=" + freeBoardSn + "&loginId=" + loginId ;
			
			
	});
	
});
