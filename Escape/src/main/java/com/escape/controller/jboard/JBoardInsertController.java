package com.escape.controller.jboard;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.escape.controller.SuperClass;
import com.escape.dao.JBoardDao;
import com.escape.dao.JCategoryDao;
import com.escape.model.JBoard;
import com.escape.model.JCategory;

public class JBoardInsertController extends SuperClass{
	private final String PREFIX = "jboard/";
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		// 로그인필요
		if(super.loginfo==null) {
			super.youNeededLogin();
			return ;
		}
		
		JCategoryDao jcdao = new JCategoryDao();
		List<JCategory> jcategories = null;
		try {
			jcategories = jcdao.GetCategoryList();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("jcategories", jcategories);
		
		
		String gotopage = PREFIX + "jboInsertForm.jsp";
		
		super.GotoPage(gotopage);
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		
		String jwriter = request.getParameter("jwriter");
		String jcategory = request.getParameter("jcategory");
		String jsubject = request.getParameter("jsubject");
		String jcontent = request.getParameter("jcontent");
		String jregdate = request.getParameter("jregdate");
		String jremark = request.getParameter("jremark");
		
		JBoard bean = new JBoard();
		
		bean.setJwriter(jwriter);
		bean.setJcategory(jcategory);
		bean.setJsubject(jsubject);
		bean.setJcontent(jcontent);
		bean.setJregdate(jregdate);
		bean.setJremark(jremark);
		
		JBoardDao dao = new JBoardDao();
		
		int cnt = -1;
		
		try {
			cnt = dao.InsertData(bean);
			if(cnt == -1) {
				new JBoardInsertController().doGet(request, response);
			}else {
				new JBoardListController().doGet(request, response);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
