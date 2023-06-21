package com.escape.controller.qboard;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.escape.utility.Paging;
import com.escape.controller.SuperClass;
import com.escape.dao.QBoardDao;
import com.escape.dao.QCategoryDao;
import com.escape.model.QBoard;
import com.escape.model.QCategory;

public class QBoardListController extends SuperClass{ //Q&A게시판 section
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Q&A게시판 목록보기
		super.doGet(request, response);
		
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		String mode = request.getParameter("mode");
		String keyword = request.getParameter("keyword");
		
		if(mode==null){mode="all";}
		if(keyword==null){keyword="";}
		
		QBoardDao dao = new QBoardDao();
		
		List<QBoard> lists = null;
		
		boolean isGrid = false;
		
		try {
			lists = dao.selectAll();
			
			String url = super.getUrlInfo("qboList");
			int totalCount = dao.GetTotalRecordCount(mode, keyword);
			Paging pageInfo = new Paging(pageNumber, pageSize, totalCount, url, mode, keyword, isGrid);
			
			
			System.out.print("필드 검색 파라미터 확인 : ");
			System.out.println(pageInfo.getFlowParameter());
			
			lists = dao.SelectAll(pageInfo);
			
			request.setAttribute("datalist", lists);
			
			request.setAttribute("pageInfo", pageInfo);
			
			
			String gotopage = "qboard/qboList.jsp";
			super.GotoPage(gotopage);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		QCategoryDao qcdao = new QCategoryDao();
		
		List<QCategory> qcategories = null;
		try {
			qcategories = qcdao.GetCategoryList();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("qcategories", qcategories);
			
	}
}
