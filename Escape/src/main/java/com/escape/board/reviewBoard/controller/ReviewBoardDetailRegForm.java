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

@WebServlet("/ReviewBoardDetailRegForm")
public class ReviewBoardDetailRegForm extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String themeNm = request.getParameter("themeNm");
		String loginId = request.getParameter("loginId");
		String scoreStar = request.getParameter("scoreStar");
		String reviewContent = request.getParameter("reviewContent");
		String reviewBoardSn = request.getParameter("reviewBoardSn");
		
		
		System.out.println("themeNm:" + themeNm);
		System.out.println("loginId:" + loginId);
		System.out.println("scoreStar:" + scoreStar);
		System.out.println("reviewContent:" + reviewContent);
		System.out.println("reviewBoardSn:" + reviewBoardSn);

		ReviewBoardDetailDao dao = new ReviewBoardDetailDao();
		
		int reviewRegCnt = -1;
		
			
			try {
				reviewRegCnt = dao.reviewBoardDetailRegForm(themeNm, loginId, scoreStar, reviewContent);
				System.out.println("갔다왓냐?"+reviewRegCnt);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
	
		JSONObject jsonObj = new JSONObject();
		
		jsonObj.put("reviewRegCnt", reviewRegCnt);
		
		response.setContentType("text/html;charset=utf-8");
		response.setContentType("application/x-json; charst=utf-8");
		response.getWriter().print(jsonObj); 
	}
	
}
