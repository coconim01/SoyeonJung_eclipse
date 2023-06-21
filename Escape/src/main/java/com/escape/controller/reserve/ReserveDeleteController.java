package com.escape.controller.reserve;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.escape.controller.SuperClass;
import com.escape.dao.ReserveDao;

public class ReserveDeleteController extends SuperClass{

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		ReserveDao rvDao = new ReserveDao() ;
		int timenum = Integer.parseInt(request.getParameter("timenum"));
		String id = request.getParameter("id");
		
		int cnt=-1;
		
		try {
			cnt = rvDao.DeleteData(timenum);
			if(cnt == -1) { // 삭제 실패
				super.setAlertMessage("삭제 실패");
				super.GotoPage("reserve/rvHistoryDetail.jsp");
				
			}else { // 삭제 성공
				request.setAttribute("id", id);
				new ReserveHistoryController().doGet(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			super.GotoPage("reserve/rvHistoryDetail.jsp");
		}

	}
}
