package com.escape.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.escape.model.Genre;

public class GenreDao extends SuperDao{
	
	public List<Genre> GetGenreList() throws Exception{
		// 장르의 목록을 읽어서 반환해 줍니다.
		String sql = " select * from Genres " ; 
		
		List<Genre> lists = new ArrayList<Genre>() ;
		
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
	
		System.out.println("Genre item size : " + lists.size()); 
		return lists;
	}

	private Genre makeBean(ResultSet rs) throws Exception{
		Genre bean = new Genre() ;
		
		bean.setEngname(rs.getString("engname"));
		bean.setKorname(rs.getString("korname"));
		
		return bean;
	}

}
