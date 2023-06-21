package com.escape.controller.qboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.escape.controller.SuperClass;
import com.escape.dao.QBoardDao;
import com.escape.model.QBoard;

public class QBoardDetailController extends SuperClass{
	 @Override
	   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      super.doGet(request, response);
	      
	    // 로그인필요
		if(super.loginfo==null) {
			super.youNeededLogin();
			return ;
		}
	      
	      int qno = Integer.parseInt(request.getParameter("qno"));
	      
	      QBoardDao dao = new QBoardDao();
	      QBoard bean = null;
	      try {
	          bean = dao.getDataByPk(qno);
	          
	      } catch (Exception e) {
	         
	         e.printStackTrace();
	      }
			
			if(bean==null) { 
				super.setAlertMessage("잘못된 게시글 입니다.");
				super.GotoPage("common/home.jsp"); 
			  }else { 
				  request.setAttribute("bean",bean); 
			  super.GotoPage("qboard/qboDetail.jsp"); 
			  }
			
	   }

}
