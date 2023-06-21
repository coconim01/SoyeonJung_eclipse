package com.escape.controller.qboard;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.escape.controller.SuperClass;
import com.escape.dao.QBoardDao;
import com.escape.dao.QCategoryDao;
import com.escape.model.QBoard;
import com.escape.model.QCategory;

public class QBoardInsertController extends SuperClass{
	private final String PREFIX = "qboard/";
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		// 로그인필요
		if(super.loginfo==null) {
			super.youNeededLogin();
			return ;
		}
		
		QCategoryDao qcdao = new QCategoryDao();
		List<QCategory> qcategories = null;
		try {
			qcategories = qcdao.GetCategoryList();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("qcategories", qcategories);
		
		
		String gotopage = PREFIX + "qboInsertForm.jsp";
		
		super.GotoPage(gotopage);
		
	}
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		String qwriter = request.getParameter("qwriter");
		String qcategory = request.getParameter("qcategory");
		String qsubject = request.getParameter("qsubject");
		String qcontent = request.getParameter("qcontent");
		String qregdate = request.getParameter("qregdate");
		String qremark = request.getParameter("qremark");
		
		
		QBoard bean = new QBoard();
		
		bean.setQwriter(qwriter);
		bean.setQcategory(qcategory);
		bean.setQsubject(qsubject);
		bean.setQcontent(qcontent);
		bean.setQregdate(qregdate);
		bean.setQremark(qremark);
		
		QBoardDao dao = new QBoardDao();
		
		int cnt = -1;
		
		try {
			cnt = dao.InsertData(bean);
			if(cnt == -1) {
				new QBoardInsertController().doGet(request, response);
			}else {
				new QBoardListController().doGet(request, response);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
