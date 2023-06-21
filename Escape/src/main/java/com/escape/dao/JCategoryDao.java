package com.escape.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.escape.model.JCategory;

public class JCategoryDao extends SuperDao{

	public List<JCategory> GetCategoryList() throws Exception{
		//조인 게시판 카테고리 읽어옴
		String sql = " select * from jcategories";
		
		List<JCategory> lists = new ArrayList<JCategory>();
		
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

	private JCategory makeBean(ResultSet rs) throws Exception{
		JCategory bean = new JCategory();
		
		bean.setJcname(rs.getString("jcname"));
		return bean;
	}

}
