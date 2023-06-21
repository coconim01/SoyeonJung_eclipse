package com.escape.controller.reserve;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.escape.controller.SuperClass;
import com.escape.controller.product.ProductAllListController;
	import com.escape.controller.product.ProductDetailController;
import com.escape.dao.ReserveDao;



public class ReserveCalculateController extends SuperClass{
	@Override // 카트 목록 내용을 이용하여 결제를 진행합니다.
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		super.doGet(request, response);
		ReserveDao rvDao = new ReserveDao() ;
		
		try {			
			String reservtime =request.getParameter("productnum")+"_"+
					request.getParameter("revdate")+","+request.getParameter("revtime");
			System.out.println("resevTime:"+reservtime);
			int idx = reservtime.indexOf("_"); // _까지의 열수...
			System.out.println(reservtime.substring(0,idx));
			int people = Integer.parseInt(request.getParameter("people"));
			
			
			// Calculate(로그인한사람, 테마번호_날짜,시간, 사람수)
			
			int cnt = -1 ;// 가정) -1은 결제 실패 
			try {
				cnt = rvDao.Calculate(super.loginfo, reservtime, people) ; 

				
				if(cnt == -1) { // 걸제 실패
					super.setAlertMessage("예약이 불가합니다.");
					//상품 예약페이지로...
				}else { // 결제 성공
					super.GotoPage("home.jsp");
					//홈으로...			
				}
				
			} catch (SQLException e) {
				int error = e.getErrorCode();
				if(error==1) {
					super.setAlertMessage("중복되는 예약이 존재합니다.");
					new ProductAllListController().doGet(request, response);				
				}
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
