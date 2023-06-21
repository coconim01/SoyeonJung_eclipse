package com.escape.controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.escape.controller.SuperClass;
import com.escape.dao.MemberDao;
import com.escape.model.Member;



public class MemberLoginController extends SuperClass{
	private final String PREFIX = "member/" ;
	
	@Override // 사용자가 common.jsp 파일에서 [로그인] 링크를 클릭했습니다.
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response); 
		
		// meLoginForm.jsp 파일로 이동하면 됩니다.
		String gotopage = PREFIX + "meLoginForm.jsp" ; 
		
		super.GotoPage(gotopage);
	}
	
	@Override // 사용자가 로그인 폼에서 전송 버튼을 클릭하면 여기로 옵니다.
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response); 
		
		String id = request.getParameter("id") ;
		String password = request.getParameter("password") ;		
		//System.out.println(id + "/" + password); 
		
		MemberDao dao = new MemberDao();
		
		Member bean = null;
		try {
			bean = dao.SelectData(id, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String gotopage = "home.jsp" ; 
		
		if(bean==null) {
			// 사용자에게 로그인 실패임을 알려 주고, 로그인 페이지로 이동합니다.
			String message = "로그인 정보가 잘못 되었습니다.";
			super.setAlertMessage(message);
			new MemberLoginController().doGet(request, response);
		}else {
			// 세션에 나의 로그인 정보를 저장하고, 메인 홈 페이지로 이동하겠습니다.
			super.session.setAttribute("loginfo", bean);
			
			// go to home page
			super.GotoPage(gotopage);
			//new HomeController().doGet(request, response);
		}
	}
}







