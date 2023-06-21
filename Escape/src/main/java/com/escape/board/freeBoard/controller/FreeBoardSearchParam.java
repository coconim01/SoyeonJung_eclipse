package com.escape.board.freeBoard.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.escape.board.freeBoard.dao.FreeBoardDao;
import com.escape.board.freeBoard.model.FreeBoard;

@WebServlet("/FreeBoardSearchParam")
public class FreeBoardSearchParam extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		
		String srchData = request.getParameter("srchData");
		String selectData = request.getParameter("selectData");

		System.out.println("srchData::::::::::::::" + srchData);
		System.out.println("selectData::::::::::::::" + selectData);

		FreeBoardDao dao = new FreeBoardDao();
	
	try { 
		List<FreeBoard> resultList = dao.getListWhere(srchData, selectData);
	
		JSONArray jsonArr = new JSONArray(); 
		if(resultList.size() > 0) { 
			for(int i=0; i<resultList.size(); i++) { 
				
				// sql에서 조회한 컬럼들 , MEMBER_ID  as memberId dto에 담아왔던것을 
				// jsonobject에 다시 담아서 화면에 보내는 작업.
				JSONObject jsonObj = new JSONObject();
		
				jsonObj.put("memberId",resultList.get(i).getMemberId()); 
				jsonObj.put("freeBoardSn",resultList.get(i).getFreeBoardSn()); 
				jsonObj.put("no",i+1); 
				jsonObj.put("subject",resultList.get(i).getSubject()); 
				jsonObj.put("nickname", resultList.get(i).getNickname()); 
				jsonObj.put("regDt", resultList.get(i).getRegDt()); 
				jsonObj.put("readCnt", resultList.get(i).getReadCnt()); 
				jsonObj.put("replyCnt", resultList.get(i).getReplyCnt()); 
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
