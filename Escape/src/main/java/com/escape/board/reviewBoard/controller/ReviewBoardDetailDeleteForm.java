/**
 * 페이지명 : 자유게시판 상세페이지 등록 Controller
 * 
 * 작성일자 : 2023-03-05
 * 작성자 : 정영우
 */

package com.escape.board.reviewBoard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.escape.board.reviewBoard.dao.ReviewBoardDetailDao;

@WebServlet("/ReviewBoardDetailDeleteForm")
public class ReviewBoardDetailDeleteForm extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String reviewBoardSn = request.getParameter("reviewBoardSn");
		String regMode = request.getParameter("regMode");
		
		System.out.println("reviewBoardSn:" + reviewBoardSn);
		System.out.println("regMode:" + regMode);

		ReviewBoardDetailDao dao = new ReviewBoardDetailDao();
		
		int deleteCnt = 0;
		// 레그모드가 삭제일때
		if("delete".equals(regMode)) {
		
			// 다오로 간다 1개의 값을 가지고.
			try {
				deleteCnt = dao.deleteReviewBoardDetailDto(reviewBoardSn);
				System.out.println("갔다왓냐?"+deleteCnt);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		response.setContentType("text/html;charset=utf-8");
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("deleteCnt", deleteCnt); 
		
		
				
	
		response.setContentType("application/x-json; charst=utf-8");
		response.getWriter().print(jsonObj); 
	}
	
}
