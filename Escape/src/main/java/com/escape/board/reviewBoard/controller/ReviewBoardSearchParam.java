package com.escape.board.reviewBoard.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.escape.board.reviewBoard.dao.ReviewBoardDao;
import com.escape.board.reviewBoard.model.ReviewBoard;

@WebServlet("/ReviewBoardSearchParam")
public class ReviewBoardSearchParam extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		
		String srchData = request.getParameter("srchData");
		String selectGenreData = request.getParameter("selectGenreData");
		String selectThemeData = request.getParameter("selectThemeData");
		String selectScoreData = request.getParameter("selectScoreData");
		String srchTextGubun = request.getParameter("srchTextGubun");

		
		System.out.println("srchData::::::::::::::" + srchData);
		System.out.println("selectGenreData::::::::::::::" + selectGenreData);
		System.out.println("selectThemeData::::::::::::::" + selectThemeData);
		System.out.println("selectScoreData::::::::::::::" + selectScoreData);
		System.out.println("srchTextGubun::::::::::::::" + srchTextGubun);

		ReviewBoardDao dao = new ReviewBoardDao();
	
	try { 
		
		List<ReviewBoard> reviewList = dao.getReviewListWhere(srchData, selectGenreData, selectThemeData, selectScoreData, srchTextGubun);
	
		JSONArray jsonArr = new JSONArray(); 
		if(reviewList.size() > 0) { 
			for(int i=0; i<reviewList.size(); i++) { 
				
				// sql에서 조회한 컬럼들 , MEMBER_ID  as memberId dto에 담아왔던것을 
				// jsonobject에 다시 담아서 화면에 보내는 작업.
				JSONObject jsonObj = new JSONObject();
		
				
				jsonObj.put("themeGenre",reviewList.get(i).getThemeGenre()); 
				jsonObj.put("memberId",reviewList.get(i).getMemberId()); 
				jsonObj.put("themeSn",reviewList.get(i).getThemeSn()); 
				jsonObj.put("themeName",reviewList.get(i).getThemeName()); 
				jsonObj.put("rowNum",i+1); 
				jsonObj.put("themeGenreNm",reviewList.get(i).getThemeGenreNm()); 
				jsonObj.put("nickname", reviewList.get(i).getNickname()); 
				jsonObj.put("reviewThemeScore", reviewList.get(i).getReviewThemeScore()); 
				jsonObj.put("regDt", reviewList.get(i).getRegDt()); 
				jsonObj.put("reviewSn", reviewList.get(i).getReviewSn()); 
				jsonObj.put("reviewThemeContents", reviewList.get(i).getReviewThemeContents()); 
				jsonArr.add(jsonObj);
				
				} 
			}
		
			response.setContentType("application/x-json; charst=utf-8");
			response.getWriter().print(jsonArr); 
		
		} catch (Exception e) {
			e.printStackTrace(); 
		}
	
	}
}
