package com.escape.controller.reserve;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.escape.controller.SuperClass;
import com.escape.controller.member.MemberDetailController;
import com.escape.dao.ReserveDao;
import com.escape.model.ReservTimes;


public class ReserveHistoryController extends SuperClass  {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		String id = request.getParameter("id");
		ReserveDao redao = new ReserveDao() ;
		
		try {
			List<ReservTimes> lists = redao.getHistory(id);
			if(lists.size()==0) {
				super.setAlertMessage("이전 예약 정보가 존재하지 않습니다.");	
				new	MemberDetailController().doGet(request, response);
			}else {
				request.setAttribute("lists", lists);
				request.setAttribute("id", id);
				super.GotoPage("reserve/rvHistory.jsp"); 
			}
		} catch (Exception e) {
			super.setAlertMessage("예약 정보가 존재하지 않습니다.");	
			e.printStackTrace();
		}
	}
}