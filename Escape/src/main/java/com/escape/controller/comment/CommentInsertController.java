package com.escape.controller.comment;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.escape.controller.SuperClass;
import com.escape.controller.jboard.JBoardDetailController;
import com.escape.dao.CommentDao;
import com.escape.model.Comment;

public class CommentInsertController extends SuperClass{
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		
		Comment bean = new Comment();
		bean.setNo(Integer.parseInt(request.getParameter("no")));
		bean.setContent(request.getParameter("content"));
		bean.setWriter(request.getParameter("writer"));
		CommentDao dao = new CommentDao();
		int cnt = -1;
		
		try {
			cnt = dao.InsertData(bean);
			new JBoardDetailController().doPost(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}