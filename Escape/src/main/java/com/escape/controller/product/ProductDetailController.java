package com.escape.controller.product;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.escape.controller.SuperClass;
import com.escape.dao.ProductDao;
import com.escape.model.Product;

public class ProductDetailController extends SuperClass{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		int productnum = Integer.parseInt(request.getParameter("productnum"));
		
		ProductDao dao = new ProductDao();
		
		try {
			Product bean = new Product();
			
			bean = dao.GetDataByPk(productnum);
			request.setAttribute("bean", bean);
			super.GotoPage("product/prDetail.jsp");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
