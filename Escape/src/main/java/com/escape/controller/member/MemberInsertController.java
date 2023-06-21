package com.escape.controller.member;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.escape.controller.SuperClass;
import com.escape.dao.MemberDao;
import com.escape.model.Member;


// 회원 가입
public class MemberInsertController extends SuperClass{
	private final String PREFIX = "member/" ;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response); 
		String gotopage = PREFIX + "meInsertForm.jsp" ; 
		
		super.GotoPage(gotopage);
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		
		String id = request.getParameter("id") ;
		String nickname = request.getParameter("nickname") ;
		String password = request.getParameter("password") ;
		String phone = request.getParameter("phone") ;
		
		String gender = request.getParameter("gender") ;
		
		String theme = "" ;
		String[] themes = request.getParameterValues("theme") ;
		if(themes==null) {
			theme = "" ;
		}else {
			for(int i=0;i<themes.length;i++) {
				theme += themes[i] + "," ;
			}
			theme=theme.substring(0, theme.length()-1) ;
		}
		
		System.out.println(theme);
		
		String hiredate = request.getParameter("hiredate") ;
		Member bean = new Member();
		
		bean.setId(id);
		bean.setGender(gender);
		bean.setHiredate(hiredate);
		bean.setPhone(phone);
		bean.setNickname(nickname);
		bean.setPassword(password);
		bean.setTheme(theme);


		
		MemberDao dao = new MemberDao();		
		int cnt = -1 ;// 가정) -1은 가입 실패 
		try {
			try {
				cnt = dao.InsertData(bean);
				if(cnt == -1) { // 가입 실패
					request.setAttribute("bean", bean);
					new MemberInsertController().doGet(request, response);
					
				}else { // 가입 성공
					new MemberLoginController().doGet(request, response);
				}
				
			} catch (SQLException e) {
				int error = e.getErrorCode();
				if(error==1) {
					super.setAlertMessage("id가 중복됩니다.");
					new MemberInsertController().doGet(request, response);
				}
			}
		}catch (Exception e) {
				e.printStackTrace();
				new MemberInsertController().doGet(request, response);

			}	
	}
}