/**
 * freeBoard.js
 * 리뷰게시판 목록 자바스크립트
 * 작성일자 : 2023-03-13
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
		var selectGenreValue = $("select[name=genreCd]").val();
		var selectThemeValue = $("select[name=themeCd]").val();
		var selectScoreValue = $("select[name=scoreOrderBy]").val();
		var srchTextGubun = $("select[name=srchTextGubun]").val();
		
		$.ajax({
			  type : "get"
			, url : "/Escape/ReviewBoardSearchParam"
			// 데이터를 받는 방식
			, dataType : "JSON"
			, data : {
				  srchData :  inputText  // 검색값
				, selectGenreData : selectGenreValue // 장르CD
				, selectThemeData : selectThemeValue // 테마명
				, selectScoreData : selectScoreValue // 평점순 0:없음, 1:평점높은순, 2:평점낮은순
				, srchTextGubun : srchTextGubun // 0:전체, 1:작성자, 2:리뷰내용
			}
			,async : false 
			, success : function(result){
				
				var length = result.length;
				$('#totNum').text('(총 ' + length + '건)');
				
				
				var html = '';
				html += '<table id="reviewTable_id"class="table" width="100%" id="test" style="margin-top:10px">';
				html += '<thead>';
				html += '<tr>';
				html += '<th>NO</th>';
				html += '<th>장르명</th>';
				html += '<th>테마명</th>';
				html += '<th width="600px">리뷰내용</th>';
				html += '<th>닉네임</th>';
				html += '<th>리뷰 점수</th>';
				html += '<th>등록일자</th>';
				html += '</tr>';
				html += '</thead>';
				html += '<tbody id="tbody_id">';
				html += '<c:forEach var="reviewBoard" items="${reviewBoardList}">';
				
				if(result.length > 0){
					$.each(result,function(idx, obj){
						html += '<tr id="reviewList" value="">'; 
						html += '<td style="display: none; type="hidden">'+obj.reviewSn+'</td>';//0
						html += '<td style="display: none; type="hidden">'+obj.themeGenre+'</td>';//0
						html += '<td style="display: none; type="hidden">'+obj.memberId+'</td>';//1
						html += '<td style="display: none; type="hidden">'+obj.themeSn+'</td>';//1
						html += '<td>'+obj.rowNum+'</td>';//2
						html += '<td>'+obj.themeGenreNm+'</td>';//2
						html += '<td>'+obj.themeName+'</td>';//3
						html += '<td width="600px">'+obj.reviewThemeContents+'</td>';//3
						html += '<td>'+obj.nickname+'</td>';//4
						html += '<td>'+obj.reviewThemeScore+'</td>';//5
						html += '<td>'+obj.regDt+'</td>';
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

				$('#reviewTable_id').html(html);
			}
		}); // ajax
	
	}); // click
	
	/***********************
	 * 리뷰작성 버튼 클릭 이벤트
	 ***********************/
	// 
	$('#reviewRegBtn').click(function(){
		
		var loginId = $('#loginId').val();
		
		// 로그인하지 않은 상태이면
		if(loginId == '' || loginId == null || loginId == undefined){
			alert("로그인을 먼저 한 후 글작성이 가능합니다.")
		}// 로그인한 상태이면
		else{
		// 리뷰게시판 글작성 페이지로 이동.(freeBoardDetailReg.jsp)	 
		location.href = "../reviewBoardDetailReg/reviewBoardDetailReg.jsp?loginId="+ loginId;
		}
	
	});

	/***********************
	 * 행 클릭 이벤트
	 ***********************/
//	$('#tbody_id').on('click', 'tr', function(){
	$('#reviewTable_id').on('click', 'tr', function(){
		
		var table =document.getElementById('reviewTable_id');
  		var rowList = table.rows; // *1)rows collection
		
		var tr = $(this);
		var me = $(this).closest('tr');
        var td = tr.children();
		// 로그인한 아이디
		var loginId =  $('#loginId').val();  // duddn1870
	
		
		//테스트
	    // 로우넘      리뷰내용        장르        테마명       작성자   별점    등록일자   장르코드  회원아이디 테마순번  리뷰순번
		// 12	 너무 스릴있었어요	 스릴러	  악마를 보았다	홍길순	  7.5	23/03/13	2	 b123    	2	    2
		
		// 11	판타지노잼이었어요    스릴러	   악마를 보았다	이순신	  4.5	23/03/13	2	  c123	    2      	3
		
		// 10	너무 재밌었어요	        감동		 거짓말		정영우	  9.0	23/03/13	6	duddn1870	5	    4
		
		// 5	이야기가 재밌어요	판타지	성 안의 이야기	    정영우	  3.0	23/03/14	5	duddn1870	3	    14
		
		var reviewSn =  $(this).find("td:eq(0)").text(); // 리뷰순번   14
		var aaaaaaa =  $(this).find("td:eq(1)").text(); //    " "
		var memberId =  $(this).find("td:eq(2)").text(); // 작성자 아이디  duddn1870
		var themeSn =  $(this).find("td:eq(3)").text(); // 테마순번        3  
		var rownum =  $(this).find("td:eq(4)").text(); // 로우넘        5
		var genre =  $(this).find("td:eq(5)").text(); // 장르         판타지
		var themeName=  $(this).find("td:eq(6)").text(); // 테마명    성 안의 이야기
		var reviewContent=  $(this).find("td:eq(7)").text(); // 리뷰내용   이야기가 재밌어요
		var nickname =  $(this).find("td:eq(8)").text(); // 닉네임      정영우

		location.href = "../reviewBoardDetail/reviewBoardDetail.jsp?reviewSn=" + reviewSn + "&loginId=" + loginId ;
			
			
	});
	
});
