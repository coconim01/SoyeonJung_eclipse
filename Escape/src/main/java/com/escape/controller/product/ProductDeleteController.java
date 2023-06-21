package com.escape.controller.product;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.escape.controller.SuperClass;
import com.escape.dao.ProductDao;

public class ProductDeleteController extends SuperClass{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//상품 삭제 (prAllList에서 가능)
		super.doGet(request, response);
		
		int num = Integer.parseInt(request.getParameter("productnum"));	//상품번호
		
		ProductDao dao = new ProductDao();
		int cnt = -1;
		
		try {
			cnt = dao.DeleteData(num);
			
			new ProductAllListController().doGet(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
