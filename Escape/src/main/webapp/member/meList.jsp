<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ page import="com.escape.model.Member"%>
<%@ page import="com.escape.dao.MemberDao"%>	
	
<%@ include file="./../common/bootstrap5.jsp" %>
<%@ include file="./../common/common.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<title>Bootstrap Example</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- css로 연결해주는 링크 -->
	<link rel="stylesheet" href="./freeBoardDetail.css">
	
	<style type="text/css">
		@import url('https://fonts.googleapis.com/css2?family=Dongle:wght@300&display=swap');
		/*body {font-family: 'Dongle', sans-serif;} */
		/*나눔고딕*/
		@import url('https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap');
		
		#backButton{margin:auto;}
		.updateBtn{border: none; width:100px; height:30px;font-size:16px;padding-bottom:12px; padding-top:5px; 
					background-color :#03D0B7; color: white; font-weight:500;border-radius: 3px;
					font-family: 'Nanum Gothic', sans-serif;font-weight:bolder;   } /* 민트 컬러칩 #03D0B7 */
		a {text-decoration: none;} 
		p{font-family: 'Nanum Gothic', sans-serif; /* 글꼴 */}
	
		.page-link {
  color: #000; 
  background-color: #fff;
  border: 1px solid #ccc; 
}

.page-item.active .page-link {
 z-index: 1;
 color: #555;
 font-weight:bold;
 background-color: #f1f1f1;
 border-color: #ccc;
 
}

.page-link:focus, .page-link:hover {
  color: #000;
  background-color: #fafafa; 
  border-color: #ccc;
}
	</style>	
	

		
	</style>
</head>
<body>

	<div class="container mt-3">
	
	
    
		<h2>회원 목록</h2>
		<p>회원 목록을 보여 주는 페이지</p>

		<table class="table table-striped">
			<thead>
				<tr>
					<th>아이디</th>
					<th>가입일자</th>					
					<th>별명</th>
					<th>비밀번호</th>
					<th>성별</th>
					<th>선호하는 테마</th>
					<th>회원 정보</th>
					<th>회원 정보 삭제</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="bean" items="${datalist}"> 
				<tr>
					<td align="center">${bean.id}</td>
					<td>${bean.hiredate}</td>					
					<td>${bean.nickname}</td>
					<td>${bean.password}</td>
					<td>${bean.gender}</td>					
					<td>${bean.theme}</td>
					
					<td>
					<div id="backButton">
						
						<a href="<%=notWithFormTag%>meDetail&id=${bean.id}">
						<input type="button" value="상세보기" id="updateBtn" onclick="history.back();" class="updateBtn" ></a>
						
						
					</div>
					</td>
				
					<td>
					<div id="backButton">
						<a href="<%=notWithFormTag%>meDelete2&id=${bean.id}${requestScope.pageInfo.flowParameter}">
						<input type="button" value="회원탈퇴" id="updateBtn" onclick="return memberDelete()" class="updateBtn" ></a>
					</div>
					</td>
				
				
				</tr>
				</c:forEach>				
			</tbody>
		</table>
		${requestScope.pageInfo.pagingHtml}
	</div>
	
	<script type="text/javascript">
   /* 필드 검색시 입력했던 콤보 박스(mode)의 내용과 키워드(keyword) 입력 상자에 있던 내용은 보존되어야 합니다. */
   $(document).ready(function(){
      var myoptions = $('#mode option');
      
      for(var i=0; i<myoptions.length; i++){
         if(myoptions[i].value == '${requestScope.pageInfo.mode}'){
            myoptions[i].selected = true;
         }
      }
      $('#keyword').val('${requestScope.pageInfo.keyword}');
   });
   
	function memberDelete() {
		var msg = confirm(" 해당 회원을 탈퇴시키시겠습니까?")
		
		if (msg == true) {		//확인 버튼 클릭 시
			alert(" 해당 회원이 탈퇴되었습니다.")
		
		}else {		//취소 버튼 클릭 시
			return false;
		}
	}
	</script>
</script>

</body>
</html>
