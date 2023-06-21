package com.escape.controller.reserve;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.escape.controller.SuperClass;
import com.escape.dao.ReserveDao;
import com.escape.model.ReservTimes;
import com.escape.model.ReserveItem;



public class ReserveDetailController extends SuperClass{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		super.doGet(request, response);
		
		if(super.loginfo==null) {
			super.youNeededLogin();
			return ;
		}
		
		ReserveDao redao = new ReserveDao()  ;
		
		try {
			int timenum = Integer.parseInt(request.getParameter("timenum"));
			
			ReservTimes reservTimes = redao.getDetailHistory(timenum) ; //예약이 되서 입력된 값들...
			List<ReserveItem> lists = redao.ShowDetail(timenum) ;//상세페이지에 필요한 데이터들..
			System.out.println(reservTimes);
			System.out.println("예약 정보 : " + lists);

			request.setAttribute("reservTimes", reservTimes); // 주문 정보
			if(lists.size()==0) {
				super.setAlertMessage("예약 정보가 존재하지 않습니다.");				
			}else {
				request.setAttribute("lists", lists);
				super.GotoPage("reserve/rvHistoryDetail.jsp");
 
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}




