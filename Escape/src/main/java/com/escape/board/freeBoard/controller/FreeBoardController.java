package com.escape.board.freeBoard.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.escape.board.freeBoard.dao.FreeBoardDao;

@WebServlet(urlPatterns = {"/freeBoardSearch"})
public class FreeBoardController extends HttpServlet{

	@Override
	public void init(ServletConfig config) throws ServletException {
	};
	
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String method = request.getMethod();
		
		System.out.println("요청 메소드 :" + method);
		
		if(method.equalsIgnoreCase("post")) {
			doPost(request, response);
		}else {
			doGet(request, response);
		}
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(this.getClass() + "doGet()");
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(this.getClass() + "doPost()");
	
		String text = request.getParameter("srchText");
		
		FreeBoardDao dao = new FreeBoardDao();
		
		System.out.println("text:::::::" + text);
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
