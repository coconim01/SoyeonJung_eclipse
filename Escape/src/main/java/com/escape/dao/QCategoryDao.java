package com.escape.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.escape.model.QCategory;

public class QCategoryDao extends SuperDao{

	public List<QCategory> GetCategoryList() throws Exception{
		//문의게시판 카테고리 읽어옴
		String sql = " select * from qcategories";
		
		List<QCategory> lists = new ArrayList<QCategory>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		if(conn==null) {conn=super.getConnection();}
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			lists.add(this.makeBean(rs));
		}
		
		
		if(rs != null) {rs.close();}
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		
		return lists;
		
	}

	private QCategory makeBean(ResultSet rs) throws Exception{
		QCategory bean = new QCategory();
		
		bean.setQcname(rs.getString("qcname"));
		
		return bean;
	}
}
