package com.escape.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.escape.model.Member;
import com.escape.model.ReservTimes;
import com.escape.model.ReserveItem;

public class ReserveDao extends SuperDao{
	public ReservTimes getDetailHistory(int timenum) throws Exception {
		// 해당 송장 번호에 대한 주문 정보를 반환해 줍니다.
		System.out.println("예약 번호 : " + timenum);
		
		String sql = " select * from ReservTimes where timenum = ?" ;
		
		PreparedStatement pstmt = null ;
		ResultSet rs= null ;
		Connection conn = null ;
		
		if(conn==null) {conn = super.getConnection() ;}
		
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, timenum); 
		
		rs = pstmt.executeQuery();
		
		ReservTimes bean = null ;
		
		if(rs.next()) {
			bean = this.makeReservTimesBean(rs) ;
		}
		
		if(rs!=null){rs.close();}
		if(pstmt!=null){pstmt.close();}
		if(conn!=null){conn.close();}
		
		return bean;
	}
	public List<ReserveItem> ShowDetail(int timenum) throws Exception{
		System.out.println("예약 번호 : " + timenum);

		// 해당 송장 번호에 대한 세부적인 상세 내역을 컬렉션 형태로 반환해 줍니다.
		String sql = "select r.mid, r.timenum, p.title ,r.reservtime, p.price, p.image01, r.purchasedate, r.people ";
			sql += " from reservtimes r inner join products p " ;
			sql += " on r.themenum = p.productnum  and r.timenum = ? order by r.themenum desc " ; 
		
		List<ReserveItem> lists = new ArrayList<ReserveItem>() ;

		Connection conn = null ;
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		
		if(conn==null){conn=super.getConnection();}
		pstmt = conn.prepareStatement(sql);		
		pstmt.setInt(1, timenum);
		rs = pstmt.executeQuery() ;
		
		while(rs.next()) {
			lists.add(this.makeReserveItemBean(rs)); 
		}
		
		if(rs!=null){rs.close();}
		if(pstmt!=null){pstmt.close();}
		if(conn!=null){conn.close();}
		
		return lists;
	}
	
	
	private ReserveItem makeReserveItemBean(ResultSet rs) throws Exception {
		ReserveItem bean = new ReserveItem() ; 
		int idx = rs.getString("reservtime").indexOf("_"); // _까지의 열수...
		int idx2 = rs.getString("reservtime").indexOf(","); // , 까지의 열수
		
		//select r.mid, r.timenum, p.title ,r.reservtime, p.price, p.image01, r.purchasedate, r.people
		bean.setImage01(rs.getString("image01"));
		bean.setMid(rs.getString("mid"));
		bean.setPeople(rs.getInt("people"));
		bean.setPrice(rs.getInt("price"));
		bean.setPurchasedate(rs.getString("purchasedate"));
		
		bean.setReservdate(rs.getString("reservtime").substring(idx+1, idx2));
		
		bean.setReservtime(rs.getString("reservtime").substring(idx2+1));
		
		bean.setTimenum(rs.getInt("timenum"));
		bean.setTitle(rs.getString("title"));
		bean.setPrice(rs.getInt("price"));

		
		return bean ;
	}
	public List<ReservTimes> getHistory(String id) throws Exception{
		// 나의 과거 쇼핑 내역을 최신 것 부터 정렬하여 반환해 줍니다.
		String sql = " select * from ReservTimes" ;
		sql += " where mid = ? order by timenum desc " ;
		
		List<ReservTimes> lists = new ArrayList<ReservTimes>() ;
		
		Connection conn = null ;
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		
		if(conn==null){conn=super.getConnection();}
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		rs = pstmt.executeQuery() ;
		
		while(rs.next()) {
			lists.add(this.makeReservTimesBean(rs)) ;
		}
		
		if(rs!=null){rs.close();}
		if(pstmt!=null){pstmt.close();}
		if(conn!=null){conn.close();}
		
		return lists;
	}
	private ReservTimes makeReservTimesBean(ResultSet rs) throws Exception   {
		ReservTimes bean = new ReservTimes() ;
		
		bean.setMid(rs.getString("mid"));
		bean.setPeople(rs.getInt("people"));
		bean.setPurchasedate(String.valueOf(rs.getDate("purchasedate")));
		bean.setQty(rs.getInt("qty"));
		bean.setReservtime(rs.getString("reservtime"));
		bean.setStock(rs.getInt("stock"));
		bean.setThemenum(rs.getInt("themenum"));
		bean.setTimenum(rs.getInt("timenum"));
		
		return bean ;
	}
	public int Calculate(Member payer, String reservtime, int people) throws Exception {
		// payer : 계산을 하는 분
		//reservtime : 테마번호, 예약 일자, 예약 시간
		//people : 입력한 사람수
		int idx = reservtime.indexOf("_"); // _까지의 열수...
		Connection conn = null ;
		PreparedStatement pstmt = null ;
		int cnt = -1 ;
		String sql = ""  ;
		
		if(conn==null) {conn = super.getConnection() ;}
		conn.setAutoCommit(false); 		
		
		// step01 : 예약 테이블(ReservTimes)에 매출 1건 입력

		sql = " insert into reservtimes(timenum, themenum, reservtime, mid, purchasedate, people) " ;
		sql += " values(seqtime.nextval, ? , ? , ? , sysdate , ? ) " ;		
		pstmt = conn.prepareStatement(sql) ;
		pstmt.setInt(1, Integer.parseInt(reservtime.substring(0,idx)));
		pstmt.setString(2, reservtime);
		pstmt.setString(3, payer.getId());
		pstmt.setInt(4, people);

		cnt = pstmt.executeUpdate() ;
		
		conn.commit();
		
		if(pstmt!=null) {pstmt.close();}
		if(conn!=null) {conn.close();}
		
		return cnt ;

	}	
	
	//취소하기
	public int DeleteData(int timenum) throws Exception {
	      System.out.println("삭제될 예약번호 : " + timenum);
	      
	      int cnt = -1 ;
	      
	      String sql = " delete from reservtimes where timenum = ?" ; 
	      
	      Connection conn = null ;
	      PreparedStatement pstmt = null ;
	      
	      if(conn==null) {conn=super.getConnection();}
	      
	      conn.setAutoCommit(false); 
	      
	      pstmt=conn.prepareStatement(sql);
	      
	      pstmt.setInt(1, timenum);
	      
			cnt = pstmt.executeUpdate() ;
			
			conn.commit();
			
			if(pstmt!=null) {pstmt.close();}
			if(conn!=null) {conn.close();}
			
			return cnt ;
	
	}



}
