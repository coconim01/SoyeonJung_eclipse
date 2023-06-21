package com.escape.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.escape.utility.Paging;
import com.escape.model.QBoard;

public class QBoardDao extends SuperDao{

	public List<QBoard> selectAll() throws Exception{
		String sql = " select * from qboards order by qno desc";
		
		List<QBoard> lists = new ArrayList<QBoard>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		if(conn==null){conn=super.getConnection();}
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			lists.add(this.makeBean(rs));
		}
		
		return lists;
	}

	private QBoard makeBean(ResultSet rs) throws Exception{
		QBoard bean = new QBoard();
		bean.setQno(rs.getInt("qno"));
		bean.setQwriter(rs.getString("qwriter"));
		bean.setQcategory(rs.getString("qcategory"));
		bean.setQsubject(rs.getString("qsubject"));
		bean.setQcontent(rs.getString("qcontent"));
		bean.setQregdate(String.valueOf(rs.getDate("qregdate")));
		bean.setQreadhit(rs.getInt("qreadhit"));
		bean.setQremark(rs.getString("qremark"));
		bean.setQgroupno(rs.getInt("qgroupno"));
		bean.setQorderno(rs.getInt("qorderno"));
		bean.setQdepth(rs.getInt("qdepth"));
		return bean;
	}

	public int InsertData(QBoard bean) throws Exception{
		   int cnt = -1;
		      
		      String sql = " insert into qboards(qno, qwriter, qsubject, qcontent, qregdate, qreadhit, qremark, qgroupno, qorderno, qdepth, qcategory)";
		      sql += " values(qnboard.nextval, ?, ?, ?, sysdate, default, ?, qnboard.currval, default, default, ?)";
		      
		      Connection conn = null;
		      PreparedStatement pstmt = null;
		      
		      if(conn==null) {conn = super.getConnection();}
		      
		      conn.setAutoCommit(false);
		      
		      pstmt = conn.prepareStatement(sql);
		      
		      pstmt.setString(1, bean.getQwriter());
		      pstmt.setString(2, bean.getQsubject());
		      pstmt.setString(3, bean.getQcontent());
		      pstmt.setString(4, bean.getQremark());
		      pstmt.setString(5, bean.getQcategory());
		      
		      cnt = pstmt.executeUpdate();
		      
		      conn.commit();
		      
		      if(pstmt != null) {pstmt.close();}
		      if(conn != null) {conn.close();}
		      
		      return cnt;
	}

	public QBoard getDataByPk(int qno) throws Exception{
		System.out.println("찾고자 하는 문의글 번호 : "+qno);
	      
	      String sql = " select * from qboards";
	      sql += " where qno = ?";
	      
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;

	      
	      QBoard bean = null;
	      
	      try {
		        conn = super.getConnection();
		        pstmt = conn.prepareStatement(sql);
		        pstmt.setInt(1, qno);
		        rs = pstmt.executeQuery();

		        if (rs.next()) {
		            bean = this.makeBean(rs);

		            // 조회수 증가
		            String updateSql = "update qboards set qreadhit = qreadhit + 1 where qno = ?";
		            PreparedStatement updatePstmt = conn.prepareStatement(updateSql);
		            updatePstmt.setInt(1, qno);
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

	public int GetTotalRecordCount(String mode, String keyword) throws Exception{
		// 검색할 때 사용할 메소드
		System.out.print("검색할 필드명 : "+mode);
		System.out.println(", 검색할 키워드 : "+keyword);
		
		String sql = " select count(*) as cnt from qboards ";
		
		
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

	public List<QBoard> SelectAll(Paging pageInfo) throws Exception{
		//TopN 구문을 이용하여 페이징 처리된 목록을 반환해 줍니다.
		String sql = " select qno, qwriter, qcategory, qsubject, qcontent, qregdate, qreadhit, qremark, qgroupno, qorderno, qdepth"; 
		sql += " from (select qno, qwriter, qcategory, qsubject, qcontent, qregdate, qreadhit, qremark, qgroupno, qorderno, qdepth, "; 
		//sql += " rank() over(order by qno desc) as ranking"; // 답글 구현하기 이전 코딩
		sql += " rank() over(order by qgroupno desc, qorderno asc) as ranking"; // 답글 구현 이후 정렬 순서 변경, for 답글 기능
		sql += " from qboards";
		
		String mode = pageInfo.getMode();
		String keyword = pageInfo.getKeyword();
						
		if(mode.equals("all")==false) {// 전체 보기가 모드가 아닐 경우
			sql += " where " + mode + " like '%" + keyword + "%'"; 
		}					
		sql +=" )";
		sql += " where ranking between ? and ?";
		
		List<QBoard> lists = new ArrayList<QBoard>();
				
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

	public QBoard GetDataByPk(int qno) throws Exception{
		System.out.println("찾고자 하는 글번호 : "+qno);
		
		String sql = " select * from qboards";
		sql += " where qno = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		if(conn==null) {conn = super.getConnection();}
		
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, qno);
		
		rs = pstmt.executeQuery();
		
		QBoard bean = null;
		
		if(rs.next()) {
			bean = this.makeBean(rs);
		}
		
		if(rs != null) {rs.close();}
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		
		return bean;
	}

	public int UpdataDate(QBoard bean) throws Exception{
		System.out.println(bean);
		int cnt = -1; 
		
		//remark칼럼은 관리자가 상품 삭제시 자동으로 업데이트 됩니다.
		String sql = " update qboards set ";
		sql += " qwriter = ?, qsubject = ?, qcategory = ?, qcontent = ?, qregdate = sysdate, qreadhit = ? ";
		sql	+= ", qgroupno = ?, qorderno = ?, qdepth = ? ";
		sql += " where qno = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		if(conn==null) {conn=super.getConnection();}
		
		conn.setAutoCommit(false);
		
		pstmt=conn.prepareStatement(sql);
		
		pstmt.setString(1, bean.getQwriter());
		pstmt.setString(2, bean.getQsubject());
		pstmt.setString(3, bean.getQcategory());
		pstmt.setString(4, bean.getQcontent());
		pstmt.setInt(5, bean.getQreadhit());
		pstmt.setInt(6, bean.getQgroupno());
		pstmt.setInt(7, bean.getQorderno());
		pstmt.setInt(8, bean.getQdepth());
		pstmt.setInt(9, bean.getQno());
		
		cnt = pstmt.executeUpdate();
		
		conn.commit();
		
		if(pstmt!=null) {pstmt.close();}
		if(conn!=null) {conn.close();}

		return cnt;
	}

	public int ReplyData(QBoard bean, int qorderno) throws Exception{
		System.out.println(bean);
		
		int cnt = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		if(conn==null) {conn = super.getConnection();}
		
		conn.setAutoCommit(false);
		
		
		//update 구문) 동일한 그룹 번호에 대하여 orderno컬럼의 숫자를 1씩 증가시켜야 합니다.
		String sql1 = " update qboards set qorderno = qorderno + 1";
		sql1 += " where qgroupno = ? and qorderno > ?";
		
		pstmt = conn.prepareStatement(sql1);
		pstmt.setInt(1, bean.getQgroupno());
		pstmt.setInt(2, qorderno);
		
		cnt = pstmt.executeUpdate();
		
		//insert 구문) 해당 bean객체를 이용하여 답글을 작성합니다.
		
		pstmt = null; // update가 끝나고 참조관계를 한번 끊어준다
		
		String sql2 = " insert into qboards(qno, qwriter, qsubject, qcontent, qregdate, qgroupno, qorderno, qdepth)";
		sql2 += " values(qnboard.nextval, ?, ?, ?, sysdate, ?, ?, ?)";
		
		pstmt = conn.prepareStatement(sql2);
		
		pstmt.setString(1, bean.getQwriter());
		pstmt.setString(2, bean.getQsubject());
		pstmt.setString(3, bean.getQcontent());
		
		pstmt.setInt(4, bean.getQgroupno());
		pstmt.setInt(5, bean.getQorderno());
		pstmt.setInt(6, bean.getQdepth());
		
		cnt = pstmt.executeUpdate();
		
		conn.commit();
		
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		
		return cnt;
	}

	public int DeleteData(int qno) throws Exception{
		// 게시물에서 해당 글번호를 삭제합니다.
		System.out.println("삭제될 글 번호 : " + qno);
		int cnt = -1; 
		
		//remark칼럼은 관리자가 상품 삭제시 자동으로 업데이트 됩니다.
		String sql = " delete from qboards where qno = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		if(conn==null) {conn=super.getConnection();}
		
		conn.setAutoCommit(false);
		
		pstmt=conn.prepareStatement(sql);
		
		pstmt.setInt(1, qno);
		
		cnt = pstmt.executeUpdate();
		
		conn.commit();
		
		if(pstmt!=null) {pstmt.close();}
		if(conn!=null) {conn.close();}

		return cnt;
				
	}


}
