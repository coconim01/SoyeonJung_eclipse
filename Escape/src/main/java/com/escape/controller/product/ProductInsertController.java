package com.escape.controller.product;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.escape.controller.SuperClass;
import com.escape.dao.GenreDao;
import com.escape.dao.ProductDao;
import com.escape.model.Genre;
import com.escape.model.Product;
import com.oreilly.servlet.MultipartRequest;

public class ProductInsertController extends SuperClass{
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		// 상품 등록 화면이 보이기 전에 genre 목록 테이블을 읽어서 콤보 박스에 채워 넣기
				GenreDao dao = new GenreDao();
				List<Genre> genres = null ;
				try {
					genres = dao.GetGenreList();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				request.setAttribute("genres", genres); 
				
				String gotopage = "product/prInsertForm.jsp";
				super.GotoPage(gotopage);
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		
		MultipartRequest mr = (MultipartRequest)request.getAttribute("mr");
		
		Product bean = new Product(); 
		
		bean.setAddress(mr.getParameter("address"));
		bean.setArea(mr.getParameter("area"));
		bean.setComments(mr.getParameter("comments"));
		bean.setGenre(mr.getParameter("genre"));
		bean.setRecommendpeople(mr.getParameter("recommendpeople"));
		bean.setTitle(mr.getParameter("title"));
		bean.setUploaddate(String.valueOf(mr.getParameter("uploaddate")));
		
		bean.setImage01(mr.getFilesystemName("image01"));
		bean.setImage02(mr.getFilesystemName("image02"));
		
		bean.setPrice(getNumberData(mr.getParameter("price")));
		bean.setThemalevel(getNumberData(mr.getParameter("themalevel")));
		bean.setProductnum(getNumberData(mr.getParameter("productnum")));
		bean.setUsetime(getNumberData(mr.getParameter("usetime")));
		
		bean.setTime01(mr.getParameter("time01"));
		bean.setTime02(mr.getParameter("time02"));
		bean.setTime03(mr.getParameter("time03"));
		bean.setTime04(mr.getParameter("time04"));
		bean.setTime05(mr.getParameter("time05"));
		
		
		ProductDao dao = new ProductDao() ;
		int cnt = -1 ; 
		try {
			cnt = dao.InsertData(bean);
			
			if(cnt==-1) {
				super.GotoPage("product/prInsertForm.jsp");
				
			}else {
				new ProductAllListController().doGet(request, response);
				System.out.println("등록 포스트 빈" + bean);
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
