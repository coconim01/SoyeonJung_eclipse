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

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.escape.board.reviewBoard.dao.ReviewBoardDetailDao;
import com.escape.board.reviewBoard.model.ReviewBoard;

@WebServlet("/ReviewBoardDetailUpdateForm")
public class ReviewBoardDetailUpdateForm extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String reviewContent = req.getParameter("reviewContent");
		String reviewBoardSn = req.getParameter("reviewBoardSn");
		String regMode = req.getParameter("regMode");
		
		System.out.println("reviewContent:" + reviewContent);
		System.out.println("reviewBoardSn:" + reviewBoardSn);
		System.out.println("regMode:" + regMode);

		ReviewBoardDetailDao dao = new ReviewBoardDetailDao();
		
		// 수정일때
		if("update".equals(regMode)) {	
			try {
				int reviewUpdateCnt = dao.updateReviewBoardDetailDto(reviewContent, reviewBoardSn);
				System.out.println("리뷰업데이트수 갔다왓냐?"+reviewUpdateCnt);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}// 새글쓰기일때
		//else if ("insert".equals(regMode)){
			
		//}
		
		resp.setContentType("text/html;charset=utf-8");
		// 재조회
//		ReviewBoard resultDto = dao.getReviewBoardDetail(reviewBoardSn);
		JSONArray jsonArr = new JSONArray(); 
		JSONObject jsonObj = new JSONObject();
//		
//		jsonObj.put("themeName",resultDto.getThemeName()); 
//		jsonObj.put("nickname", resultDto.getNickname()); 
//		jsonObj.put("reviewThemeScore", resultDto.getReviewThemeScore()); 
//		jsonObj.put("reviewThemeContents", resultDto.getReviewThemeContents()); 
//		jsonArr.add(jsonObj);
				
	
		resp.setContentType("application/x-json; charst=utf-8");
		resp.getWriter().print(jsonArr); 
	}
	
}
