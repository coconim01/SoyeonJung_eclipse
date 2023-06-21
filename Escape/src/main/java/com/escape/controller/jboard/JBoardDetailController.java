package com.escape.controller.jboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.escape.controller.SuperClass;
import com.escape.dao.JBoardDao;
import com.escape.model.JBoard;

public class JBoardDetailController extends SuperClass{
		
		@Override
		public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			this.doGet(request, response);
		}
	@Override
	   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      super.doGet(request, response);
	      
		// 로그인필요
		if(super.loginfo==null) {
			super.youNeededLogin();
			return ;
		}
					 
	      
	      int jno = Integer.parseInt(request.getParameter("jno"));
	      
	      JBoardDao dao = new JBoardDao();
	      JBoard bean = null;
	      try {
	          bean = dao.getDataByPk(jno);
	          
	      } catch (Exception e) {
	         
	         e.printStackTrace();
	      }
			
			if(bean==null) { 
				super.setAlertMessage("잘못된 게시글 입니다.");
				super.GotoPage("common/home.jsp"); 
			  }else { 
				  request.setAttribute("bean",bean); 
			  super.GotoPage("jboard/jboDetail.jsp"); 
			 
			  }
	}
}
