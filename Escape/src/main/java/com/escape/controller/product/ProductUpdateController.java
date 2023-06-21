package com.escape.controller.product;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.escape.controller.SuperClass;
import com.escape.dao.ProductDao;
import com.escape.model.Product;
import com.oreilly.servlet.MultipartRequest;

public class ProductUpdateController extends SuperClass{
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		int productnum = 0;
		ProductDao dao = null;
		Product bean = null;
		
		try {
			dao = new ProductDao() ;			
			productnum = Integer.parseInt(request.getParameter("productnum")) ; // 수정할 상품 번호
			
			bean = dao.GetDataByPk(productnum) ;
			if(bean!=null) {
				request.setAttribute("bean", bean);
			}
			super.GotoPage("product/prUpdateForm.jsp");
			System.out.println("수정 겟 빈"+bean);
			
		} catch (Exception e) {
			e.printStackTrace();
			super.GotoPage("product/prAllList.jsp");
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		
		MultipartRequest mr = (MultipartRequest)request.getAttribute("mr");
		
		Product bean = new Product(); 
				
		// 상품 등록과는 다르게 수정은 상품 번호를 챙겨야 합니다.
		bean.setProductnum(getNumberData(mr.getParameter("productnum")));
		
		bean.setAddress(mr.getParameter("address"));
		bean.setArea(mr.getParameter("area"));
		bean.setComments(mr.getParameter("comments"));
		bean.setGenre(mr.getParameter("genre"));
		
		bean.setImage01(mr.getFilesystemName("image01"));
		bean.setImage02(mr.getFilesystemName("image02"));
		
		bean.setRecommendpeople(mr.getParameter("recommendpeople"));
		bean.setTitle(mr.getParameter("title"));
		bean.setUploaddate(mr.getParameter("uploaddate"));
		
		bean.setUsetime(getNumberData(mr.getParameter("usetime")));
		bean.setPrice(getNumberData(mr.getParameter("price")));
		bean.setThemalevel(getNumberData(mr.getParameter("themalevel")));
		
		bean.setTime01(mr.getParameter("time01"));
		bean.setTime02(mr.getParameter("time02"));
		bean.setTime03(mr.getParameter("time03"));
		bean.setTime04(mr.getParameter("time04"));
		bean.setTime05(mr.getParameter("time05"));
		
		System.out.println("수정 포스트 빈" + bean);
		
		ProductDao dao = new ProductDao() ;
		int cnt = -1 ; 
		try {
			cnt = dao.UpdateData(bean);
			
			if(cnt==-1) {
				super.GotoPage("product/prUpdateForm.jsp");
				
			}else {
				String gotopage = super.getUrlInfo("prAllList");
				gotopage += "&pageNumber=" + mr.getParameter("pageNumber");
				gotopage += "&pageSize=" + mr.getParameter("pageSize");
				gotopage += "&mode=" + mr.getParameter("mode");
				gotopage += "&keyword=" + mr.getParameter("keyword");

				response.sendRedirect(gotopage);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private int getNumberData(String parameter) {
		boolean flag = false ;				
		
		flag = parameter==null || parameter.equals("") || parameter.equals("null") ;
		
		System.out.println(this.getClass() + " getNumberData method called"); 
		
		return !flag ? Integer.parseInt(parameter) : 0; 
	}	
	
	
	
	
	
}
