package com.escape.controller.product;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.escape.controller.SuperClass;
import com.escape.dao.ProductDao;
import com.escape.model.Product;
import com.escape.utility.Paging;

public class ProductAllListController03 extends SuperClass{

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 상품 목록을 조회합니다.
		super.doGet(request, response);

		String pageNumber = request.getParameter("pageNumber") ;
		String pageSize = request.getParameter("pageSize") ;
		String modeGenre = request.getParameter("modeGenre") ;
		String modeLevel = request.getParameter("modeLevel") ;
		String modeArea = request.getParameter("modeArea") ;
		String keyword = request.getParameter("keyword") ;

		if(modeGenre==null) {modeGenre="all";}
		if(modeLevel==null) {modeLevel="all";}
		if(modeArea==null) {modeArea="all";}
		if(keyword==null) {keyword="";}

		boolean isGrid = false ;

		ProductDao dao = new ProductDao();
		List<Product> lists = null ;

		try {
			int totalCount = dao.GetTotalRecordCountPr(modeGenre, modeLevel, modeArea, keyword);
			String url = super.getUrlInfo("prAllList");

			Paging pageInfo = new Paging(pageNumber, pageSize, totalCount, url, modeGenre, modeLevel, modeArea, keyword, isGrid);
			System.out.println(this.getClass());
			System.out.println(pageInfo);


			/* lists = dao.SelectAll(); */
			try {
				lists = dao.SelectAll(pageInfo);
				request.setAttribute("datalist", lists);
				request.setAttribute("pageInfo", pageInfo);

				super.GotoPage("product/prAllList.jsp");

				System.out.println(lists);
				
			}catch (SQLException e) {
				int error = e.getErrorCode();
					System.out.println(error);
				
					if(error == 907) {
					System.out.println("조건에 맞는 뭐시깽이가 없음");

					super.setAlertMessage("조건에 맞는 테마가 없습니다.");

					super.GotoPage("product/prAllList.jsp");
					}
				}
		}catch (Exception e) {
				e.printStackTrace();
			}
	}
}
