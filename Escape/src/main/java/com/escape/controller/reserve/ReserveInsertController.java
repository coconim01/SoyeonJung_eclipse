package com.escape.controller.reserve;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.escape.controller.SuperClass;
import com.escape.dao.ProductDao;
import com.escape.model.Product;

//테마 상세 페이지에서 예약하기 버튼을 클릭했습니다.
public class ReserveInsertController extends SuperClass{
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		
		// 로그인필요
		if(super.loginfo==null) {
			super.youNeededLogin();
			return ;
		}
		
		/* 선택한 시간 및 날짜 받기 */		
		String revtime = "";
		String revdate = "";
		int productnum = 0;
		
		ProductDao dao = null;
		Product bean = null;
		
		try {
			
			revtime = request.getParameter("revtime");
			revdate = request.getParameter("revdate");
			
			dao = new ProductDao() ;			
			productnum = Integer.parseInt(request.getParameter("productnum")) ;
			
			bean = dao.GetDataByPk(productnum) ;
			
			if(bean!=null) {
				request.setAttribute("bean", bean);
				request.setAttribute("revtime", revtime);
				request.setAttribute("revdate", revdate);
			}
			super.GotoPage("reserve/rvInsert.jsp");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

}
