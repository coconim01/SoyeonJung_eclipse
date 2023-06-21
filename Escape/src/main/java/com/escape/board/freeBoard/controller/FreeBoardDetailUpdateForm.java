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

@WebServlet("/FreeBoardDetailUpdateForm")
public class FreeBoardDetailUpdateForm extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String subject = req.getParameter("subject");
		String content = req.getParameter("content");
		String freeBoardSn = req.getParameter("freeBoardSn");
		String regMode = req.getParameter("regMode");
		
		System.out.println("subject:" + subject);
		System.out.println("content:" + content);
		System.out.println("freeBoardSn:" + freeBoardSn);
		System.out.println("regMode:" + regMode);

		FreeBoardDetailDao dao = new FreeBoardDetailDao();
		
		// 수정일때
		if("update".equals(regMode)) {
		
			// 다오로 간다. 4개의 값을 가지고.
			try {
				int updateCnt = dao.updateFreeBoardDetailDto(subject, content, freeBoardSn);
				System.out.println("업데이트수 갔다왓냐?"+updateCnt);
				
			} catch (Exception e) {
				e.printStackTrace();
			}

		}// 새글쓰기일때
		else if ("insert".equals(regMode)){
			
		}
		
		
		resp.setContentType("text/html;charset=utf-8");
		// 재조회
		FreeBoard resultDto = dao.getFreeBoardDetail(freeBoardSn);
		JSONArray jsonArr = new JSONArray(); 
		JSONObject jsonObj = new JSONObject();
		
		jsonObj.put("subject",resultDto.getSubject()); 
		jsonObj.put("content",resultDto.getContents()); 
		jsonObj.put("nickname", resultDto.getNickname()); 
		jsonObj.put("regDt", resultDto.getRegDt()); 
		jsonObj.put("readCnt", resultDto.getReadCnt()); 
		jsonObj.put("replyCnt", resultDto.getReplyCnt()); 
		jsonArr.add(jsonObj);
				
	
		resp.setContentType("application/x-json; charst=utf-8");
		resp.getWriter().print(jsonArr); 
	}
	
}
