package com.escape.controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.escape.controller.SuperClass;
import com.escape.dao.MemberDao;

public class MemberDelete2Controller extends SuperClass{
	   @Override
	   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {   
	      super.doGet(request, response);
	      
	      String id = request.getParameter("id");
	      MemberDao dao = new MemberDao() ;
	      int cnt = -1 ;

	      try {
	         cnt = dao.DeleteData(id) ;
	         
	         new MemberListController().doGet(request, response);
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	   }
	}