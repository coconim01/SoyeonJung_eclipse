package com.escape.controller.qboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.escape.controller.SuperClass;
import com.escape.dao.QBoardDao;

public class QBoardDeleteController extends SuperClass{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		QBoardDao dao = new QBoardDao();
		int cnt = -1;
		
		try {
			int qno = Integer.parseInt(request.getParameter("qno"));
			cnt = dao.DeleteData(qno);
			
			new QBoardListController().doGet(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
