

package com.escape.board.freeBoard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.escape.board.freeBoard.model.FreeBoard;
import com.escape.dao.SuperDao;

public class FreeBoardDao extends SuperDao{
	
	/*************************
	 * 검색조건으로 검색
	 **************************/
	public List<FreeBoard> getListWhere(String srchData, String selectData) throws Exception{
		System.out.println ("서치데이타" +  srchData);
	
		 // String sql = " SELECT * FROM FREE_BOARD WHERE SUBJECT LIKE ?";
		// String sql = " SELECT A.*, B.NICKNAME FROM FREE_BOARD A ,MEMBER B WHERE A.MEMBER_ID = B.MEMBER_ID AND A.SUBJECT LIKE ?";
		String sql = " SELECT A.*, B.NICKNAME, (SELECT COUNT(*) FROM FREE_BOARD_REPLY WHERE A.FREE_BOARD_SN=FREE_BOARD_SN) AS replyCnt FROM FREE_BOARD A ,MEMBERS B WHERE A.MEMBER_ID = B.ID";
		 
		 System.out.println("셀렉트데이타 :::::: " + selectData);
		 System.out.println("서치데이타 ::::::::: "+ srchData);
		 
		 // selectData = 1 일때는 제목 + 내용
		 if("1".equals(selectData)) {
			 
			 sql += " AND A.SUBJECT || A.CONTENTS LIKE ?";
		 }// selectData =  2일때는 작성자
		 else if("2".equals(selectData)) {
			 
			 sql += " AND B.NICKNAME LIKE ?";
		 } // selectData = 0 일때는 전체 
		 else {
			 sql += " AND A.SUBJECT || B.NICKNAME || A.CONTENTS LIKE ?";
		 }
		 
		 
		 //  ORDER BY A.FREE_BOARD_SN DESC
		 sql += " ORDER BY A.FREE_BOARD_SN DESC";
		 
		 
		List<FreeBoard> srchList = new ArrayList<FreeBoard>();
		
		try {
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null ;
			
			if(conn == null) {
				conn = super.getConnection();
			}
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+srchData+"%");
		
			
			rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				srchList.add(this.MakeVo(rs));
			}
			
			if(rs!=null) {rs.close();}
			if(pstmt!=null) {pstmt.close();}
			if(conn!=null) {conn.close();}
				
		} catch (Exception  e) {
			e.printStackTrace();
		}
		
		System.out.println("리스트의 길이 ::::::::::" + srchList.size());
		
		
		return srchList;
	}


	public List<FreeBoard> selectAll() throws Exception{
		// ------------------------------
		// 1. return할 변수 선언
		// ------------------------------
		List<FreeBoard> lists = new ArrayList<FreeBoard>();
		
		// ------------------------------
		// 2. 실행할 sql 쿼리문 작성
		// ------------------------------
		String sql = " SELECT A.*,B.NICKNAME, (SELECT COUNT(*) FROM    FREE_BOARD_REPLY WHERE   A.FREE_BOARD_SN=FREE_BOARD_SN) AS replyCnt FROM     FREE_BOARD A  , MEMBERS B WHERE    A.MEMBER_ID = B.ID ORDER BY A.FREE_BOARD_SN DESC" ;
		
		try {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			if(conn == null) {
				conn = super.getConnection();
			}
			
			// ------------------------------
			// 3. 나의 sql문 집어넣기
			// ------------------------------
			pstmt = conn.prepareStatement(sql);
			
			// ------------------------------
			// 4. ? 조건 넣기 (순서에 맞게)
			// ------------------------------
			// pstmt.setString(1, sn);
			
			rs = pstmt.executeQuery();
			
			// ------------------------------
			// 5. 쿼리에서 조회한 한row의 데이터를 리턴할 데이터 형식에 담기
			// ------------------------------
			while(rs.next()) {
				lists.add(	this.MakeVo(rs) // 쿼리에서 조회한 한row의 데이터
						);
			}
			
			if(rs!=null) {rs.close();}
			if(pstmt!=null) {rs.close();}
			if(conn!=null) {rs.close();}
			
		} catch (SQLException e) {
			System.out.println(e.getStackTrace());
		}
		
		// 글목록순번
		if(lists.size() > 0) {
			for(int i =0; i< lists.size();i++) {
				lists.get(i).setNo(i+1);
			}
		}
		
		return lists;
	}
	private FreeBoard MakeVo(ResultSet rs) throws Exception{
		FreeBoard vo = new FreeBoard();
		
		vo.setFreeBoardSn(rs.getInt("FREE_BOARD_SN"));
		vo.setMemberId(rs.getString("MEMBER_ID"));	
		vo.setSubject(rs.getString("SUBJECT")); 	
		vo.setContents(rs.getString("CONTENTS"));	
		vo.setReadCnt(rs.getInt("READ_CNT"));	
		vo.setRegDt(rs.getString("REG_DT"));		
		vo.setNickname(rs.getString("NICKNAME"));		
		vo.setReplyCnt(rs.getString("REPLYCNT"));		
		
		return vo;
	}
	
	
}
