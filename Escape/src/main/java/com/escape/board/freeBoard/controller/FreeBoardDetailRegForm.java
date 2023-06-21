/**
 * 페이지명 : 자유게시판 상세페이지 등록 Controller
 * 
 * 작성일자 : 2023-03-05
 * 작성자 : 정영우
 */

package com.escape.board.freeBoard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.escape.board.freeBoard.dao.FreeBoardDetailDao;
import com.escape.board.freeBoard.model.FreeBoard;

@WebServlet("/FreeBoardDetailRegForm")
public class FreeBoardDetailRegForm extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String memberId = request.getParameter("memberId");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String regMode = request.getParameter("regMode");
		
		
		System.out.println("memberId:" + memberId);
		System.out.println("subject:" + subject);
		System.out.println("content:" + content);
		System.out.println("regmode:" + regMode);

		FreeBoardDetailDao dao = new FreeBoardDetailDao();
		
		int regCnt = -1;
	
		if("reg".equals(regMode)) {
		
			// 다오로 간다 3개의 값을 가지고.
			try {
				regCnt = dao.FreeBoardDetailRegForm(memberId, subject, content);
				System.out.println("갔다왓냐?"+regCnt);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		JSONObject jsonObj = new JSONObject();
		
		jsonObj.put("regCnt", regCnt);
	
		
		
		
		
		response.setContentType("text/html;charset=utf-8");
		
	
		response.setContentType("application/x-json; charst=utf-8");
		response.getWriter().print(jsonObj); 
	}
	
}
