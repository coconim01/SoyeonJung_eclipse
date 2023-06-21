package com.escape.controller.member;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.escape.utility.Paging;
import com.escape.controller.SuperClass;
import com.escape.dao.MemberDao;
import com.escape.model.Member;




public class MemberListController extends SuperClass {
	@Override // 회원 목록을 조회합니다.
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response); 
		
		MemberDao dao = new MemberDao() ;
		List<Member> lists = null ;
		
		//페이징 처리
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		String mode = request.getParameter("mode");
		String keyword = request.getParameter("keyword");
		
		if(mode==null) {mode="all";}
		if(keyword==null) {keyword="";}

	
		boolean isGrid = false;
				
		try {
			
			//페이징 처리
			int totoalCount = dao.GetTotalRecordCount(mode, keyword);
	    	String url = request.getContextPath() + "/EscapePeople?command=meList";
	    	  
	    	Paging pageInfo = new Paging(pageNumber, pageSize, totoalCount, url, mode, keyword, isGrid);
	    	  
	        lists = dao.SelectAll(pageInfo);
	         
	        request.setAttribute("datalist", lists);
	        request.setAttribute("pageInfo", pageInfo);
	         
	        String gotopage = "member/meList.jsp";
	        super.GotoPage(gotopage);
			
			
		} catch (Exception e) {			
			e.printStackTrace();
		} 

	}
}