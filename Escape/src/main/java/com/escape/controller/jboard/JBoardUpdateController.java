package com.escape.controller.jboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.escape.controller.SuperClass;
import com.escape.dao.JBoardDao;
import com.escape.dao.QBoardDao;
import com.escape.model.JBoard;

public class JBoardUpdateController extends SuperClass{
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		int jno = 0;
		JBoardDao dao = null;
		JBoard bean = null;
		
		try {
			dao = new JBoardDao();
			jno = Integer.parseInt(request.getParameter("jno"));
			
			bean = dao.GetDataByPk(jno);
			if(bean != null) {
				request.setAttribute("bean", bean);
			}
			super.GotoPage("jboard/jboUpdateForm.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			super.GotoPage("jboard/jboList.jsp");
		}
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		
		JBoard bean = new JBoard();
		
		bean.setJno(Integer.parseInt(request.getParameter("jno")));
		bean.setJwriter(request.getParameter("jwriter"));
		bean.setJsubject(request.getParameter("jsubject"));
		bean.setJcategory(request.getParameter("jcategory"));
		bean.setJcontent(request.getParameter("jcontent"));
		bean.setJreadhit(Integer.parseInt(request.getParameter("jreadhit")));
		bean.setJremark(request.getParameter("jremark"));
		bean.setJgroupno(Integer.parseInt(request.getParameter("jgroupno")));
		bean.setJorderno(Integer.parseInt(request.getParameter("jorderno")));
		
		JBoardDao dao = new JBoardDao();
		
		int cnt = -1;
		try {
			cnt = dao.UpdataDate(bean);
			
			if(cnt == -1) {
				super.GotoPage("jboard/jboUpdateForm.jsp");
			}else {
				String gotopage = super.getUrlInfo("jboList");
				gotopage += "&pageNumber=" + request.getParameter("pageNumber");
				gotopage += "&pageSize=" + request.getParameter("pageSize");
				gotopage += "&mode=" + request.getParameter("mode");
				gotopage += "&keyword=" + request.getParameter("keyword");
				
				response.sendRedirect(gotopage);
			}
		} catch (Exception e) {
			e.printStackTrace();	
		}
	}

}
