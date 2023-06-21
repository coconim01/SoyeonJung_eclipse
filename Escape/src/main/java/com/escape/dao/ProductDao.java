package com.escape.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.escape.model.Product;
import com.escape.utility.Paging;

public class ProductDao extends SuperDao{

	public int InsertData(Product bean) throws Exception{
		// bean 객체 1개를 등록합니다.
		System.out.println(bean);
		int cnt = -1 ;
		
		String sql = " insert into products(productnum, title, genre , themalevel, recommendpeople, usetime, area, price,";
		sql += " image01, image02, comments, address, uploaddate, time01, time02, time03, time04, time05)" ; 		
		sql += " values(myproduct.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)" ; 
		
		Connection conn = null ;
		PreparedStatement pstmt = null ;		
		if(conn==null) {conn=super.getConnection();}		
		conn.setAutoCommit(false); 		
		pstmt=conn.prepareStatement(sql);
		
		pstmt.setString(1, bean.getTitle()) ;
		pstmt.setString(2, bean.getGenre()) ;
		pstmt.setInt(3, bean.getThemalevel()) ;
		pstmt.setString(4, bean.getRecommendpeople()) ;
		pstmt.setInt(5, bean.getUsetime()) ;
		pstmt.setString(6, bean.getArea()) ;
		pstmt.setInt(7, bean.getPrice()) ;
		pstmt.setString(8, bean.getImage01()) ;
		pstmt.setString(9, bean.getImage02()) ;
		pstmt.setString(10, bean.getComments()) ;
		pstmt.setString(11, bean.getAddress()) ;
		pstmt.setString(12, bean.getUploaddate()) ;
		pstmt.setString(13, bean.getTime01());
		pstmt.setString(14, bean.getTime02());
		pstmt.setString(15, bean.getTime03());
		pstmt.setString(16, bean.getTime04());
		pstmt.setString(17, bean.getTime05());
		
		
		cnt = pstmt.executeUpdate() ;		
		conn.commit();		
		if(pstmt!=null) {pstmt.close();}
		if(conn!=null) {conn.close();}		
		return cnt ;
	}
	
	public List<Product> SelectAll() throws Exception{
		// 상품 목록을 조회하여 반환해 줍니다.
		String sql = " select * from products order by productnum desc " ;
		
		List<Product> lists = new ArrayList<Product>() ;
		
		Connection conn = null; 
		PreparedStatement pstmt = null ;
		ResultSet rs = null ; 
		
		if(conn==null){conn=super.getConnection();}
		pstmt = conn.prepareStatement(sql) ;
		rs=pstmt.executeQuery();
		
		while (rs.next()){
			lists.add(this.makeBean(rs)) ;			
		}
		
		if(rs!=null) {rs.close();}
		if(pstmt!=null) {pstmt.close();}
		if(conn!=null) {conn.close();}		
		
		return lists;
	}	

	
	private Product makeBean(ResultSet rs) throws Exception{
		Product bean = new Product();
		
		bean.setAddress(rs.getString("address"));
		bean.setArea(rs.getString("area"));
		bean.setComments(rs.getString("comments"));
		bean.setGenre(rs.getString("genre"));
		bean.setImage01(rs.getString("image01"));
		bean.setImage02(rs.getString("image02"));
		bean.setPrice(rs.getInt("price"));
		bean.setProductnum(rs.getInt("productnum"));
		bean.setRecommendpeople(rs.getString("recommendpeople"));
		bean.setThemalevel(rs.getInt("themalevel"));
		bean.setTitle(rs.getString("title"));
		bean.setUploaddate(String.valueOf(rs.getDate("uploaddate")));
		bean.setUsetime(rs.getInt("usetime"));
		bean.setTime01(rs.getString("time01"));
		bean.setTime02(rs.getString("time02"));
		bean.setTime03(rs.getString("time03"));
		bean.setTime04(rs.getString("time04"));
		bean.setTime05(rs.getString("time05"));
		
		return bean;
	}
	
	public Product GetDataByPk(int productnum) throws Exception {
	      //해당 상품 번호를 이용하여 상품 bean 객체를 반환
	      System.out.println("찾고자 하는 상품 번호 : "+ productnum);
	      
	      
	      String sql = " select*from products";
	      sql += " where productnum = ?";
	      // ?는 placeholder 이라고 하며, 치환될 대상입니다.

	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      Connection conn = null;
	      
	      if (conn == null) {
	         conn = super.getConnection();
	      }
	      pstmt = conn.prepareStatement(sql);
	      
	      // ? 치환은 반드시 실행 전에 해야 한다.
	      //자바는 0부터 시작이지만 여기는 1부터 시작
	      pstmt.setInt(1, productnum);
	      
	      rs = pstmt.executeQuery();
	      
	      Product bean = null;
	      
	      if (rs.next()) {   //데이터 1건을 조회할 때는 if 구문을 쓰고, 여러 데이터를 조회할 때는 while 구문을 사용 
	         bean = this.makeBean(rs);         
	      }
	      
	      if (rs!=null) {
	         rs.close();
	      }
	      if (pstmt!=null) {
	         pstmt.close();
	      }
	      if (conn != null) {
	         conn.close();
	      }
	      
	      return bean;
	   }

	public int UpdateData(Product bean) throws Exception{
		// 관리자가 상품 정보를 수정합니다.
		System.out.println("업데이트" + bean);
		
		int cnt = -1 ;
		
		String sql = " update products set ";
		sql += " title = ?, genre = ?, themalevel = ?,";
		sql += " recommendpeople = ?, usetime = ?,";
		sql += " area = ?,  price = ?, image01 = ?, image02 = ?,";
		sql += " comments = ?, address = ?, uploaddate = ?,";
		sql += " time01 = ?, time02 = ?, time03 = ?, time04 = ?, time05 = ?";
		sql += " where productnum = ?" ; 
		
		Connection conn = null ;
		PreparedStatement pstmt = null ;		
		if(conn==null) {conn=super.getConnection();}		
		conn.setAutoCommit(false); 		
		pstmt=conn.prepareStatement(sql);
		
		pstmt.setString(1, bean.getTitle()) ;		
		pstmt.setString(2, bean.getGenre()) ;		
		pstmt.setInt(3, bean.getThemalevel()) ;
		pstmt.setString(4, bean.getRecommendpeople()) ;
		pstmt.setInt(5, bean.getUsetime()) ;
		pstmt.setString(6, bean.getArea()) ;
		pstmt.setInt(7, bean.getPrice()) ;
		pstmt.setString(8, bean.getImage01()) ;
		pstmt.setString(9, bean.getImage02()) ;
		pstmt.setString(10, bean.getComments()) ;
		pstmt.setString(11, bean.getAddress()) ;
		pstmt.setString(12, bean.getUploaddate()) ;
		pstmt.setString(13, bean.getTime01()) ;
		pstmt.setString(14, bean.getTime02()) ;
		pstmt.setString(15, bean.getTime03()) ;
		pstmt.setString(16, bean.getTime04()) ;
		pstmt.setString(17, bean.getTime05()) ;
		pstmt.setInt(18, bean.getProductnum()) ;
		
		cnt = pstmt.executeUpdate() ;		
		conn.commit();		
		if(pstmt!=null) {pstmt.close();}
		if(conn!=null) {conn.close();}		
		
		return cnt ;
	}

	public int GetTotalRecordCount(String mode, String keyword) throws Exception {
		//상품 몇개있는지 확인
		
		System.out.print("검색할 필드명 : " + mode);
		System.out.print(", 검색할 키워드 : " + keyword + "\n");
		
		String sql = " select count(*) as cnt from products " ;
		
		if(mode.equals("all") == false) { // 전체 보기 모드가 아니면
			sql += " where " + mode + " like '%" + keyword + "%'" ;
		}
		
		PreparedStatement pstmt = null ;
		ResultSet rs= null ;
		Connection conn = null ;
		int cnt = -1 ;
		
		if(conn==null) {conn = super.getConnection() ;}		
		pstmt = conn.prepareStatement(sql);					
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
			cnt = rs.getInt("cnt") ;
		}
		
		if(rs!=null){rs.close();}
		if(pstmt!=null){pstmt.close();}
		if(conn!=null){conn.close();}
		
		return cnt ;
	}
	
	//상품용
	public int GetTotalRecordCountPr(String modeGenre, String modeLevel, String modeArea, String keyword) throws Exception {
		//상품 몇개있는지 확인
		
		System.out.print("장르 : " + modeGenre );
		System.out.print(", 난이도 : " + modeLevel);
		System.out.print(", 지역 : " + modeArea);
		System.out.print(", 검색할 키워드 : " + keyword + "\n");
		
		String sql= " select count(*) as cnt from products " ;
		
		
		// 검색은 타이틀 기준
		if(modeGenre.equals("all") == false) { // 전체 보기 모드가 아니면
			sql = " select count(*) as cnt from products ";
			sql += " where (genre = '" + modeGenre + "') and title like '%" + keyword + "%'" ;
		}
		
		if(modeLevel.equals("all") == false) { // 전체 보기 모드가 아니면
			sql = " select count(*) as cnt from products ";
			sql += " where (themalevel = " + modeLevel + ") and title like '%" + keyword + "%'" ;
		}
		
		if(modeArea.equals("all") == false) { // 전체 보기 모드가 아니면
			sql = " select count(*) as cnt from products ";
			sql += " where (area = '" + modeArea + "') and title like '%" + keyword + "%'" ;
		}
		
		PreparedStatement pstmt = null ;
		ResultSet rs= null ;
		Connection conn = null ;
		int cnt = -1 ;
		
		if(conn==null) {conn = super.getConnection() ;}		
		pstmt = conn.prepareStatement(sql);					
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
			cnt = rs.getInt("cnt") ;
		}
		
		if(rs!=null){rs.close();}
		if(pstmt!=null){pstmt.close();}
		if(conn!=null){conn.close();}
		
		return cnt ;
	}

	public List<Product> SelectAll(Paging pageInfo) throws Exception{
		// TopN 구문을 이용하여 페이징 처리된 목록을 반환해 줍니다.
		String sql = " select productnum, title, genre , themalevel, recommendpeople, usetime, area, price, image01, image02, comments, address, uploaddate , time01, time02, time03, time04, time05";
		sql += " from (select productnum, title, genre , themalevel, recommendpeople, usetime, area, price, image01, image02, comments, address, uploaddate, time01, time02, time03, time04, time05,";
		sql += " rank() over(order by productnum desc) as ranking ";
		sql += " from products";
		
		String modeGenre = pageInfo.getModeGenre();
		String modeArea = pageInfo.getModeArea();
		String modeLevel = pageInfo.getModeLevel();
		String keyword = pageInfo.getKeyword();
	      
		// 장르검색
	    if(modeGenre.equals("all")==false && modeArea.equals("all")==true && modeLevel.equals("all")==true) {
	    	sql += " where (genre = '" + modeGenre + "') and title like '%" + keyword + "%'" ;}
	    
	    // 지역검색
	    if(modeArea.equals("all")==false && modeGenre.equals("all")==true && modeLevel.equals("all")==true) {
	    	sql += " where (area = '" + modeArea + "') and title like '%" + keyword + "%'" ;}  
	    
	    // 난이도검색
	    if(modeLevel.equals("all")==false && modeGenre.equals("all")==true && modeArea.equals("all")==true) {
	    	sql += " where (themalevel = '" + modeLevel + "') and title like '%" + keyword + "%'" ;}  
	    
	    // 장르+난이도
	    if(modeGenre.equals("all")==false && modeLevel.equals("all")==false && modeArea.equals("all")==true) {
	    	sql += " where ((genre = '" + modeGenre + "') and title like '%" + keyword + "%')" ;
	    	sql += " and themalevel = '" + modeLevel +"'"  ;
	    }
	    	
	    // 장르+지역
	    if(modeGenre.equals("all")==false && modeArea.equals("all")==false && modeLevel.equals("all")==true) {
	    	sql += " where ((genre = '" + modeGenre + "') and title like '%" + keyword + "%')" ;
	    	sql += " and area = '" + modeArea +"'"  ;
	    }  
	    
	    // 난이도+지역
	    if(modeLevel.equals("all")==false && modeArea.equals("all")==false && modeGenre.equals("all")==true) {
	    	sql += " where ((themalevel = '" + modeLevel + "') and title like '%" + keyword + "%')" ;
	    	sql += " and area = '" + modeArea +"'"  ;
	    }  
	    
	    // 장르+난이도+지역
	    if(modeGenre.equals("all")==false && modeLevel.equals("all")==false && modeArea.equals("all")==false) {
	    	sql += " where ((((genre = '" + modeGenre + "') and title like '%" + keyword + "%')" ;
	    	sql += " and area = '" + modeArea + "'))";
	    	sql += " and themalevel = '" + modeLevel +"'"  ;
	    }  
	            
	    sql +=" )";
	    sql += " where ranking between ? and ?";

		System.out.println("검색 sql : " + sql );
	    
		List<Product> lists = new ArrayList<Product>() ;
		
		Connection conn = null; 
		PreparedStatement pstmt = null ;
		ResultSet rs = null ; 
		
		if(conn==null){conn=super.getConnection();}
		pstmt = conn.prepareStatement(sql) ;
		
		pstmt.setInt(1, pageInfo.getBeginRow());
		pstmt.setInt(2, pageInfo.getEndRow());		
		rs=pstmt.executeQuery();
		
		while (rs.next()){
			lists.add(this.makeBean(rs)) ;			
		}
		
		if(rs!=null) {rs.close();}
		if(pstmt!=null) {pstmt.close();}
		if(conn!=null) {conn.close();}		
		
		return lists;	
	}
	
	/* 영민 수정사항 */
	public int DeleteData(int productnum) throws Exception {
		//관리자가 상품 삭제
		System.out.println("삭제할 상품 번호: " + productnum);
		int cnt = -1;
		
		//상품 테이블에서 해당 상품번호 삭제
		String sql = " delete from products";
		sql += " where productnum = ?";
				
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		if (conn == null) {
			conn=super.getConnection();
		}
		
		conn.setAutoCommit(false);
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, productnum);
		
		cnt = pstmt.executeUpdate();
		
		conn.commit();
		
		if (pstmt != null) {
			pstmt.close();
		}
		
		if (pstmt != null) {
			pstmt.close();
		}
		if (conn != null) {
			pstmt.close();
		}
		
		return cnt;
	}
}
