package com.escape.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.escape.utility.Paging;
import com.escape.model.JBoard;
import com.escape.model.QBoard;

public class JBoardDao extends SuperDao{//조인게시판 다오

	public List<JBoard> selectAll() throws Exception{//0314 알림메세지 띄우기위한 수정
		String sql = " select * from jboards order by jno desc";
		
		List<JBoard> lists = new ArrayList<JBoard>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		if(conn==null){conn=super.getConnection();}
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			lists.add(this.makeBean(rs));
		}
		
		if (lists.size() == 0) {
		    return null;
		} else {
		    return lists;
		}
		
	}

	
	public int GetTotalRecordCount(String mode, String keyword) throws Exception{
				// 검색할 때 사용할 메소드
				System.out.print("검색할 필드명 : "+mode);
				System.out.println(", 검색할 키워드 : "+keyword);
				
				String sql = " select count(*) as cnt from jboards ";
				
				
				if(mode.equals("all")==false) {// 전체 보기가 모드가 아닐 경우
					sql += " where " + mode + " like '%" + keyword + "%'"; 
					
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

	public List<JBoard> SelectAll(Paging pageInfo) throws Exception{
		// TODO Auto-generated method stub
		//TopN 구문을 이용하여 페이징 처리된 목록을 반환해 줍니다.
		String sql = " select jno, jwriter, jcategory, jsubject, jcontent, jregdate, jreadhit, jremark, jgroupno, jorderno "; 
		sql += " from (select jno, jwriter, jcategory, jsubject, jcontent, jregdate, jreadhit, jremark, jgroupno, jorderno, "; 
		sql += " rank() over(order by jno desc) as ranking"; // 답글 구현하기 이전 코딩
		//sql += " rank() over(order by qgroupno desc, qorderno asc) as ranking"; // 답글 구현 이후 정렬 순서 변경, for 답글 기능
		sql += " from jboards";
		
		String mode = pageInfo.getMode();
		String keyword = pageInfo.getKeyword();
						
		if(mode.equals("all")==false) {// 전체 보기가 모드가 아닐 경우
			sql += " where " + mode + " like '%" + keyword + "%'"; 
		}					
		sql +=" )";
		sql += " where ranking between ? and ?";
		
		List<JBoard> lists = new ArrayList<JBoard>();
				
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				
				if(conn==null) {conn = super.getConnection();}
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, pageInfo.getBeginRow());
				pstmt.setInt(2, pageInfo.getEndRow());
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					lists.add(this.makeBean(rs));
				}
				
				if(rs != null) {rs.close();}
				if(pstmt != null) {rs.close();}
				if(conn != null) {rs.close();}
				
				return lists;
	}
	
	private JBoard makeBean(ResultSet rs) throws Exception{
		// 조인 게시판
		JBoard bean = new JBoard();
		
		bean.setJno(rs.getInt("jno"));
		bean.setJwriter(rs.getString("jwriter"));
		bean.setJcategory(rs.getString("jcategory"));
		bean.setJsubject(rs.getString("jsubject"));
		bean.setJcontent(rs.getString("jcontent"));
		bean.setJregdate(String.valueOf(rs.getDate("jregdate")));
		bean.setJreadhit(rs.getInt("jreadhit"));
		bean.setJremark(rs.getString("jremark"));
		bean.setJgroupno(rs.getInt("jgroupno"));
		bean.setJorderno(rs.getInt("jorderno"));
		
		return bean;
	}


	public int InsertData(JBoard bean) throws Exception{
		 int cnt = -1;
	      
	      String sql = " insert into jboards(jno, jwriter, jsubject, jcontent, jregdate, jreadhit, jremark, jgroupno, jorderno, jcategory)";
	      sql += " values(joboard.nextval, ?, ?, ?, sysdate, default, ?, joboard.currval, default, ?)";
	      
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      
	      if(conn==null) {conn = super.getConnection();}
	      
	      conn.setAutoCommit(false);
	      
	      pstmt = conn.prepareStatement(sql);
	      
	      pstmt.setString(1, bean.getJwriter());
	      pstmt.setString(2, bean.getJsubject());
	      pstmt.setString(3, bean.getJcontent());
	      pstmt.setString(4, bean.getJremark());
	      pstmt.setString(5, bean.getJcategory());
	      
	      cnt = pstmt.executeUpdate();
	      
	      conn.commit();
	      
	      if(pstmt != null) {pstmt.close();}
	      if(conn != null) {conn.close();}
	      
	      return cnt;
	}


	public List<JBoard> SelectCategory(String jcategory) throws Exception{
		
		String sql = " select * from jboards where jcategory = ? ";
		
		
		List<JBoard> lists01 = new ArrayList<JBoard>();
		
		Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;  
	      
	    if(conn==null) {conn = super.getConnection();}
	      
	    pstmt = conn.prepareStatement(sql);

	    pstmt.setString(1, jcategory);

	    rs = pstmt.executeQuery();
	    
	    
	    if(rs.next()) {
	    	lists01.add(this.makeBean(rs));
		}
		
		if(rs != null) {rs.close();}
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		
		return lists01;
	}


	public JBoard getDataByPk(int jno) throws Exception {
	    System.out.println("찾고자 하는 게시글 번호: " + jno);

	    String sql = "select * from jboards where jno = ?";
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    JBoard bean = null;

	    try {
	        conn = super.getConnection();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, jno);
	        rs = pstmt.executeQuery();

	        if (rs.next()) {
	            bean = this.makeBean(rs);

	            // 조회수 증가
	            String updateSql = "update jboards set jreadhit = jreadhit + 1 where jno = ?";
	            PreparedStatement updatePstmt = conn.prepareStatement(updateSql);
	            updatePstmt.setInt(1, jno);
	            updatePstmt.executeUpdate();
	            updatePstmt.close();
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        if (rs != null) { rs.close(); }
	        if (pstmt != null) { pstmt.close(); }
	        if (conn != null) { conn.close(); }
	    }

	    return bean;
	}



	public JBoard GetDataByPk(int jno) throws Exception{
		System.out.println("찾고자 하는 문의글 번호 : "+jno);
	      
	      String sql = " select * from jboards";
	      sql += " where jno = ?";
	      
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      
	      if(conn==null) {conn = super.getConnection();}
	      
	      pstmt = conn.prepareStatement(sql);
	      
	      pstmt.setInt(1, jno);
	      
	      rs = pstmt.executeQuery();
	      
	      JBoard bean = null;
	      
	      if(rs.next()) {
	         bean = this.makeBean(rs);
	      }
	      
	      if(rs != null) {rs.close();}
	      if(pstmt != null) {pstmt.close();}
	      if(conn != null) {conn.close();}
	      
	      return bean;

	}


	public int UpdataDate(JBoard bean) throws Exception{
		System.out.println(bean);
		int cnt = -1; 
		
		//remark칼럼은 관리자가 상품 삭제시 자동으로 업데이트 됩니다.
		String sql = " update jboards set ";
		sql += " jwriter = ?, jsubject = ?, jcategory = ?, jcontent = ?, jregdate = sysdate, jreadhit = ? ";
		sql	+= ", jgroupno = ?, jorderno = ? ";
		sql += " where jno = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		if(conn==null) {conn=super.getConnection();}
		
		conn.setAutoCommit(false);
		
		pstmt=conn.prepareStatement(sql);
		
		pstmt.setString(1, bean.getJwriter());
		pstmt.setString(2, bean.getJsubject());
		pstmt.setString(3, bean.getJcategory());
		pstmt.setString(4, bean.getJcontent());
		pstmt.setInt(5, bean.getJreadhit());
		pstmt.setInt(6, bean.getJgroupno());
		pstmt.setInt(7, bean.getJorderno());
		pstmt.setInt(8, bean.getJno());
		
		cnt = pstmt.executeUpdate();
		
		conn.commit();
		
		if(pstmt!=null) {pstmt.close();}
		if(conn!=null) {conn.close();}

		return cnt;
	}


	public int DeleteData(int jno) throws Exception{
		// 게시물에서 해당 글번호를 삭제합니다.
		System.out.println("삭제될 글 번호 : " + jno);
		int cnt = -1; 
		
		//remark칼럼은 관리자가 상품 삭제시 자동으로 업데이트 됩니다.
		String sql = " delete from jboards where jno = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		if(conn==null) {conn=super.getConnection();}
		
		conn.setAutoCommit(false);
		
		pstmt=conn.prepareStatement(sql);
		
		pstmt.setInt(1, jno);
		
		cnt = pstmt.executeUpdate();
		
		conn.commit();
		
		if(pstmt!=null) {pstmt.close();}
		if(conn!=null) {conn.close();}

		return cnt;
						
	}

	

}
