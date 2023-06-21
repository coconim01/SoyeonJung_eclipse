package com.escape.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.escape.model.Comment;

public class CommentDao extends SuperDao{

	public List<Comment> GetDataByPk(int no) throws Exception{
		// 해당 게시물 번호에 달려있는 댓글 목록을 가장 오래된 글부터 정렬하여 반환해줍니다.
		String sql = " select * from comments ";
		sql += " where no = ? order by cnum asc";//이전에 작성했던 댓글부터
		
		List<Comment> lists = new ArrayList<Comment>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		if(conn==null) {conn=super.getConnection();}
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, no);
		
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			lists.add(this.makeBean(rs));
		}
		
		
		if(rs != null) {rs.close();}
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		
		return lists;
	}

	private Comment makeBean(ResultSet rs) throws Exception{
		Comment bean = new Comment();
		
		bean.setCnum(rs.getInt("cnum"));
		bean.setContent(rs.getString("content"));
		bean.setNo(rs.getInt("no"));
		bean.setRegdate(rs.getString("regdate"));
		bean.setWriter(rs.getString("writer"));
		
		
		return bean;
	}

	public int DeleteData(int cnum) throws Exception{
		System.out.println("삭제될 코멘트 번호 : " + cnum);
		
		int cnt = -1; 
		
		String sql = " delete from comments where cnum = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		if(conn==null) {conn=super.getConnection();}
		
		conn.setAutoCommit(false);
		
		pstmt=conn.prepareStatement(sql);
		
		pstmt.setInt(1, cnum);
		
		cnt = pstmt.executeUpdate();
		
		conn.commit();
		
		if(pstmt!=null) {pstmt.close();}
		if(conn!=null) {conn.close();}

		return cnt;
	}
	
	public int ModifyData(String writer) throws Exception{
		System.out.println("수정할 댓글 작성자 : " + writer);
		
		int cnt = -1; 
		
		String sql = " update comments set content where writer=?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		if(conn==null) {conn=super.getConnection();}
		
		conn.setAutoCommit(false);
		
		pstmt=conn.prepareStatement(sql);
		
		
		pstmt.setString(1, writer);
		
		cnt = pstmt.executeUpdate();
		
		conn.commit();
		
		if(pstmt!=null) {pstmt.close();}
		if(conn!=null) {conn.close();}

		return cnt;
	}	
	
	public int InsertData(Comment bean) throws Exception {
		System.out.println(bean);
		
		int cnt = -1; 
		
		String sql = " insert into comments(cnum, no, writer, content, regdate)";
		sql += " values(mycomment.nextval, ?, ?, ?, sysdate) ";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		if(conn==null) {conn=super.getConnection();}
		
		conn.setAutoCommit(false);
		
		pstmt=conn.prepareStatement(sql);
		
		pstmt.setInt(1, bean.getNo());
		pstmt.setString(2, bean.getWriter());
		pstmt.setString(3, bean.getContent());
		
		cnt = pstmt.executeUpdate();
		
		conn.commit();
		
		if(pstmt!=null) {pstmt.close();}
		if(conn!=null) {conn.close();}

		return cnt;
	}

	public int ModifyData(int cnum, String content) throws Exception{
		System.out.println("수정할 댓글 번호 : " + cnum);
		
		int cnt = -1; 
		
		String sql = " update comments set content =? where cnum = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		if(conn==null) {conn=super.getConnection();}
		
		conn.setAutoCommit(false);
		
		pstmt=conn.prepareStatement(sql);
		
		pstmt.setString(1, content);
		pstmt.setInt(2, cnum);
		
		cnt = pstmt.executeUpdate();
		
		conn.commit();
		
		if(pstmt!=null) {pstmt.close();}
		if(conn!=null) {conn.close();}

		return cnt;
	}

}
