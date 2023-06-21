package com.escape.controller.jboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.escape.controller.SuperClass;
import com.escape.dao.JBoardDao;

public class JBoardDeleteController extends SuperClass{

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		JBoardDao dao = new JBoardDao();
		int cnt = -1;
		
		try {
			int jno = Integer.parseInt(request.getParameter("jno"));
			cnt = dao.DeleteData(jno);
			
			new JBoardListController().doGet(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
