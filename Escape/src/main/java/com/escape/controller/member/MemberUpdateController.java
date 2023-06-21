package com.escape.controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.escape.controller.SuperClass;
import com.escape.dao.MemberDao;
import com.escape.model.Member;
import com.oreilly.servlet.MultipartRequest;

public class MemberUpdateController extends SuperClass{
	
	
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(request, response);
		
		String	id = null;
		MemberDao dao = null ;
		Member bean = null ;
		
		try {
			dao = new MemberDao() ;			
			id = request.getParameter("id") ;
			
			bean = dao.getDataByPk(id) ;
			if(bean!=null) {
				request.setAttribute("bean", bean);
			}
			super.GotoPage("member/meUpdateForm.jsp");
			
		} catch (Exception e) {
			e.printStackTrace();
			super.GotoPage("member/meLoginForm.jsp");
	}

	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
			
		Member bean = new Member();
		
		bean.setId(request.getParameter("id"));
		String password = "";
		if(password==null) {
			
		}
		bean.setPassword(request.getParameter("password"));
		bean.setNickname(request.getParameter("nickname"));
		bean.setPhone(request.getParameter("phone"));
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
		bean.setTheme(theme);
		bean.setHiredate(request.getParameter("hiredate"));
		bean.setGender(request.getParameter("gender"));
		
		MemberDao dao = new MemberDao();
		
		int cnt = -1 ; 
		try {
			cnt = dao.UpdateData(bean);
			
			if(cnt==-1) { //실패시...
				super.GotoPage("member/meUpdateForm.jsp");
				
			}else {// 성공시
				super.GotoPage("home.jsp");
			
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			super.GotoPage("member/meUpdateForm.jsp");

		}
	}
	private int getNumberData(String parameter) {
		boolean flag = false ;				
		
		flag = parameter==null || parameter.equals("") || parameter.equals("null") ;
		
		System.out.println(this.getClass() + " getNumberData method called"); 
		
		return !flag ? Integer.parseInt(parameter) : 0; 
	}	
}
