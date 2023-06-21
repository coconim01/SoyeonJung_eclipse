/**
 * 페이지명 : 자유게시판 상세페이지 등록 Controller
 * 
 * 작성일자 : 2023-03-05
 * 작성자 : 정영우
 */

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

import com.escape.board.freeBoard.dao.FreeBoardDetailDao;
import com.escape.board.freeBoard.model.FreeBoard;

@WebServlet("/FreeBoardDetailReplyRegForm")
public class FreeBoardDetailReplyRegForm extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String freeBoardSn = request.getParameter("freeBoardSn");
		String loginId = request.getParameter("loginId");
		String replyInput = request.getParameter("replyInput");
		
		
		
		System.out.println("freeBoardSn:" + freeBoardSn);
		System.out.println("loginId:" + loginId);
		System.out.println("replyInput:" + replyInput);

		FreeBoardDetailDao dao = new FreeBoardDetailDao();
		
		// -1은 인서트 실패
		int replyRegCnt = -1;
	

		// 다오로 간다 3개의 값을 가지고.
		try {
			// 인서트 결과 (0이면 인서트개수 0개, 1이면 인서트개수 1)
			replyRegCnt = dao.FreeBoardDetailReplyRegForm(freeBoardSn, loginId, replyInput);
			System.out.println("댓글 등록 카운드수 갔다왓냐?"+replyRegCnt);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONArray jsonArr = new JSONArray();
		// 인서트가 됐으면
		if(replyRegCnt > 0) {
			// 댓글 리스트 조회
			List<FreeBoard> selectReplyList = dao.getFreeBoardDetailReplyList(freeBoardSn);
			
			if(selectReplyList.size() > 0) { 
				for(int i=0; i<selectReplyList.size(); i++) { 
					// sql에서 조회한 컬럼들 , MEMBER_ID  as memberId dto에 담아왔던것을 
					// jsonobject에 다시 담아서 화면에 보내는 작업.
					JSONObject jsonObj = new JSONObject();
					// 0. 인서트 결과 cnt 
					jsonObj.put("insertCnt",replyRegCnt); 
					// 1. 닉네임
					jsonObj.put("nickName",selectReplyList.get(i).getNickname()); 
					// 2. 등록일자
					jsonObj.put("regDt",selectReplyList.get(i).getRegDt()); 
					// 3. 댓글내용
					jsonObj.put("contents",selectReplyList.get(i).getContents()); 
					
					// json어레이에 dto 하나씩 추가
					jsonArr.add(jsonObj);
					
				} 
			}
		}else {
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("insertCnt",replyRegCnt); 
			
			// json어레이에 dto 하나씩 추가
			jsonArr.add(jsonObj);
		}
		for(int i=0; i<jsonArr.size();i++) {
			System.out.println(i+"번째 값들::"+jsonArr.get(i).toString());
		}
		response.setContentType("text/html;charset=utf-8");
		response.setContentType("application/x-json; charst=utf-8");
		response.getWriter().print(jsonArr); 
	}
	
}
