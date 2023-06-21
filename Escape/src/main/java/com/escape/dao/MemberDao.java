package com.escape.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.escape.utility.Paging;
import com.escape.model.Member;

public class MemberDao extends SuperDao{
	// 회원 1사람의 정보를 반환해 줍니다.
	// 현재 일시적인 데이터이고, 차후 데이터 베이스에서 직접 읽어 오도록 하겠습니다. 
	public Member SelectData(String id, String password) throws Exception{
		System.out.println(id + "/" + password);
		
		// 아이디와 비번을 이용하여 로그인 가능한지 판단합니다.
		String sql = " select * from members" ;
		sql += " where id = ? and password = ?" ;
		
		// ?는 placeholder이라고 하며, 치환될 대상입니다.
		PreparedStatement pstmt = null ;
		ResultSet rs= null ;
		Connection conn = null ;
		
		if(conn==null) {conn = super.getConnection() ;}
		
		pstmt = conn.prepareStatement(sql);
		
		// ? 치환은 반드시 실행 전에 해야 합니다.
		pstmt.setString(1, id);
		pstmt.setString(2, password);
		
		rs = pstmt.executeQuery();
		
		Member bean = null ;
		
		if(rs.next()) {
			bean = this.makeBean(rs) ;
		}
		
		if(rs!=null){rs.close();}
		if(pstmt!=null){pstmt.close();}
		if(conn!=null){conn.close();}
		
		return bean;
	}

	public int InsertData(Member bean) throws Exception{
			System.out.println(bean);
			
			// 회원에 대한 1건(bean 객체) 데이터를 데이터 베이스에 추가합니다.
			int cnt = -1 ;
			
			String sql = " insert into members(id, password, nickname, phone, theme, hiredate, gender)" ; 
			sql += " values(?, ?, ?, ?, ?, default, ?) " ; 
			
			Connection conn = null ;
			PreparedStatement pstmt = null ;
			
			if(conn==null) {conn=super.getConnection();}
			
			conn.setAutoCommit(false); 
			
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, bean.getId()) ;
			pstmt.setString(2, bean.getPassword()) ;
			pstmt.setString(3, bean.getNickname()) ;
			pstmt.setString(4, bean.getPhone()) ;
			pstmt.setString(5, bean.getTheme()) ;
			pstmt.setString(6, bean.getGender()) ;

			
			cnt = pstmt.executeUpdate() ;
			
			conn.commit();
			
			if(pstmt!=null) {pstmt.close();}
			if(conn!=null) {conn.close();}
			
			return cnt ;
		}



	public List<Member> SelectAll() throws Exception{
		// 전체 회원 목록을 리스트 컬렉션에 저장시키고 반환해 줍니다.
		String sql = " select * from members" ; 
		
		List<Member> lists = new ArrayList<Member>() ;
		
		Connection conn = null ;
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		
		if(conn==null){conn=super.getConnection();}
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery() ;
		
		while(rs.next()) {
			lists.add(this.makeBean(rs)) ;
		}
		
		if(rs!=null){rs.close();}
		if(pstmt!=null){pstmt.close();}
		if(conn!=null){conn.close();}
		
		return lists;
	}

	private Member makeBean(ResultSet rs) throws Exception {
		Member bean = new Member() ;
		bean.setGender(rs.getString("gender"));
		bean.setHiredate(String.valueOf(rs.getDate("hiredate")));
		bean.setTheme(rs.getString("theme"));
		bean.setId(rs.getString("id"));
		bean.setPhone(rs.getString("phone"));
		bean.setNickname(rs.getString("nickname"));
		bean.setPassword(rs.getString("password"));
		bean.setReserv(rs.getString("reserv"));

		return bean;
	}

	public Member getDataByPk(String id) throws Exception {
		// id를 이용하여 회원을 조회합니다.
		System.out.println("찾고자 하는 아이디 : " + id);
		
		// 아이디와 비번을 이용하여 로그인 가능한지 판단합니다.
		String sql = " select * from members" ;
		sql += " where id = ? " ;
		
		// ?는 placeholder이라고 하며, 치환될 대상입니다.
		PreparedStatement pstmt = null ;
		ResultSet rs= null ;
		Connection conn = null ;
		
		if(conn==null) {conn = super.getConnection() ;}
		
		pstmt = conn.prepareStatement(sql);
		
		// ? 치환은 반드시 실행 전에 해야 합니다.
		pstmt.setString(1, id);
		
		rs = pstmt.executeQuery();
		
		Member bean = null ;
		
		if(rs.next()) {
			bean = this.makeBean(rs) ;
		}
		
		if(rs!=null){rs.close();}
		if(pstmt!=null){pstmt.close();}
		if(conn!=null){conn.close();}
		
		return bean;
	}

	public int UpdateData(Member bean) throws Exception {
		// 회원 정보를 수정합니다.
				System.out.println(bean);
				int cnt = -1 ;
				
				// remark 컬럼은 관리자가 상품 삭제시 자동으로 업데이트 됩니다.
				String sql = " update members set ";
				sql += " password = ?, nickname = ?, phone = ?, theme = ?, gender = ? ";		
				sql += " where id = ?" ; 
				
				
				
				Connection conn = null ;
				PreparedStatement pstmt = null ;		
				if(conn==null) {conn=super.getConnection();}		
				conn.setAutoCommit(false); 		
				pstmt=conn.prepareStatement(sql);
				
				pstmt.setString(1, bean.getPassword()) ;		
				pstmt.setString(2, bean.getNickname()) ;		
				pstmt.setString(3, bean.getPhone()) ;
				pstmt.setString(4, bean.getTheme()) ;
				pstmt.setString(5, bean.getGender()) ;
				pstmt.setString(6, bean.getId()) ;

				
				cnt = pstmt.executeUpdate() ;		
				conn.commit();		
				if(pstmt!=null) {pstmt.close();}
				if(conn!=null) {conn.close();}		
				return cnt ;
			}

	public int DeleteData(String id) throws Exception {
	      System.out.println("삭제될 아이디 : " + id);
	      
	      int cnt = -1 ;
	      
	      String sql = " delete from members where id = ?" ; 
	      
	      Connection conn = null ;
	      PreparedStatement pstmt = null ;
	      
	      if(conn==null) {conn=super.getConnection();}
	      
	      conn.setAutoCommit(false); 
	      
	      pstmt=conn.prepareStatement(sql);
	      
	      pstmt.setString(1, id);
	      
	      cnt = pstmt.executeUpdate() ;
	      
	      conn.commit();
	      
	      if(pstmt!=null) {pstmt.close();}
	      if(conn!=null) {conn.close();}
	      
	      return cnt ;
	   }

	public List<Member> SelectAll(Paging pageInfo) throws Exception{
		String sql = "select id, password, nickname, phone, theme, hiredate, gender, reserv";
		sql += " from (select id, password, nickname, phone, theme, hiredate, gender, reserv,";
		sql += " rank() over(order by id asc) as ranking ";
		sql += " from members)";
		sql += " where ranking between ? and ?";
		 

		
		List<Member> lists = new ArrayList<Member>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		
		if(conn==null) {conn=super.getConnection();}
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, pageInfo.getBeginRow());
		pstmt.setInt(2, pageInfo.getEndRow());
		
		//ResultSet : 메모리에 존재하는 조회dql의 조회된 데이터 집합 셋트 된 데이터의 결과물(xx행 yy열), 
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			lists.add(this.makeBean(rs));
		}
		
		if(rs!=null){rs.close();}
		if(pstmt!=null){pstmt.close();}
		if(conn!=null){conn.close();}
		
		return lists;
				
	}

	public int GetTotalRecordCount(String mode, String keyword) throws Exception{
		System.out.print("검색할 필드명 : "+mode);
	      System.out.println(", 검색할 키워드 : "+keyword);
	      
	      String sql = " select count(*) as cnt from members ";
	      
	      if(mode.equals("all")==false) {// 전체 보기가 모드가 아닐 경우
	         sql += " where " + mode + " like '%" + keyword + "%'";
	         // mode부분에는 내가 선택한 옵션이 들어가고 keyword부분에는 내가 검색한 글자가 들어감
	      }                        
	      
	      
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      int cnt = -1;
	      
	      if(conn==null) {conn = super.getConnection();}
	      pstmt = conn.prepareStatement(sql);
	      rs = pstmt.executeQuery();
	      
	      
	      if(rs.next()) {
	         cnt = rs.getInt("cnt");
	      }
	      
	      if(rs != null) {rs.close();}
	      if(pstmt != null) {pstmt.close();}
	      if(conn != null) {conn.close();}
	      
	      return cnt;

	}
}

