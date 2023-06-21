package com.escape.controller.jboard;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.escape.utility.Paging;
import com.escape.controller.SuperClass;
import com.escape.dao.JBoardDao;
import com.escape.dao.JCategoryDao;
import com.escape.model.JBoard;
import com.escape.model.JCategory;

public class JBoardListController extends SuperClass{

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//조인 게시판 목록보기
		super.doGet(request, response);
		
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		String mode = request.getParameter("mode");
		String keyword = request.getParameter("keyword");
		
		if(mode==null){mode="all";}
		if(keyword==null){keyword="";}
		
		JBoardDao dao = new JBoardDao();
		
		List<JBoard> lists = null;
		List<JBoard> lists01 = null;
		
		boolean isGrid = false;
		
		try {
			lists = dao.selectAll();
			
			String url = super.getUrlInfo("jboList");
			int totalCount = dao.GetTotalRecordCount(mode, keyword);
			Paging pageInfo = new Paging(pageNumber, pageSize, totalCount, url, mode, keyword, isGrid);
			
			
			System.out.print("필드 검색 파라미터 확인 : ");
			System.out.println(pageInfo.getFlowParameter());
			
			lists = dao.SelectAll(pageInfo);
			
			// 검색 결과가 없을 때0314
			if (lists.isEmpty()) {
				String message = "조회된 게시글이 없습니다.";
				String url02 = super.getUrlInfo("jboList");;
				String alertMessage = "alert('" + message + "'); location.href='" + url02 + "'";
				request.setAttribute("alertMessage", alertMessage);
			}
			
			request.setAttribute("datalist", lists);
			
			request.setAttribute("pageInfo", pageInfo);
			
			String gotopage = "jboard/jboList.jsp";
			super.GotoPage(gotopage);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		JCategoryDao jcdao = new JCategoryDao();
		
		List<JCategory> jcategories = null;
		try {
			jcategories = jcdao.GetCategoryList();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("jcategories", jcategories);
		
		
		
	}
	
}
