package com.escape.controller.qboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.escape.controller.SuperClass;
import com.escape.dao.QBoardDao;
import com.escape.model.QBoard;

public class QBoardUpdateController extends SuperClass{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		int qno = 0;
		QBoardDao dao = null;
		QBoard bean = null;
		
		try {
			dao = new QBoardDao();
			qno = Integer.parseInt(request.getParameter("qno"));
			
			bean = dao.GetDataByPk(qno);
			if(bean != null) {
				request.setAttribute("bean", bean);
			}
			super.GotoPage("qboard/qboUpdateForm.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			super.GotoPage("qboard/qboList.jsp");
		}
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		
		QBoard bean = new QBoard();
		
		bean.setQno(Integer.parseInt(request.getParameter("qno")));
		bean.setQwriter(request.getParameter("qwriter"));
		bean.setQsubject(request.getParameter("qsubject"));
		bean.setQcategory(request.getParameter("qcategory"));
		bean.setQcontent(request.getParameter("qcontent"));
		bean.setQregdate(request.getParameter("qregdate"));
		bean.setQreadhit(Integer.parseInt(request.getParameter("qreadhit")));
		bean.setQremark(request.getParameter("qremark"));
		bean.setQgroupno(Integer.parseInt(request.getParameter("qgroupno")));
		bean.setQorderno(Integer.parseInt(request.getParameter("qorderno")));
		bean.setQdepth(Integer.parseInt(request.getParameter("qdepth")));
		
		QBoardDao dao = new QBoardDao();
		
		int cnt = -1;
		try {
			cnt = dao.UpdataDate(bean);
			
			if(cnt == -1) {
				super.GotoPage("qboard/qboUpdateForm.jsp");
			}else {
				String gotopage = super.getUrlInfo("qboList");
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
