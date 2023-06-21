

package com.escape.board.reviewBoard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.escape.board.reviewBoard.model.ReviewBoard;
import com.escape.dao.SuperDao;

public class ReviewBoardDao extends SuperDao{
	public List<ReviewBoard> selectAll() throws Exception{
		System.out.println("11");
		// ------------------------------
		// 1. return할 변수 선언
		// ------------------------------
		List<ReviewBoard> lists = new ArrayList<ReviewBoard>();
		
		// ------------------------------
		// 2. 실행할 sql 쿼리문 작성
		// ------------------------------
		String sql = " ";
		sql += " SELECT ROWNUM AS \"ROWNUM\"  /*글순번*/ , A.* FROM (SELECT B.REVIEW_THEME_CONTENTS /*리뷰내용*/,CASE WHEN A.THEME_GENRE = 1 THEN '공포'      WHEN A.THEME_GENRE = 2 THEN '스릴러' WHEN A.THEME_GENRE = 3 THEN '코믹'";      
		sql += " WHEN A.THEME_GENRE = 4 THEN '역사'                                            "  ;
		sql += "     WHEN A.THEME_GENRE = 5 THEN '판타지'                                      "  ;
		sql += "     WHEN A.THEME_GENRE = 6 THEN '감동'                                        "  ;
		sql += "     WHEN A.THEME_GENRE = 7 THEN 'SF'                                          "  ;
		sql += "     WHEN A.THEME_GENRE = 8 THEN '동화'                                        "  ;
		sql += "     WHEN A.THEME_GENRE = 9 THEN '야외'                                        "  ;
		sql += "     ELSE '' END AS themeGenreNm           /*장르명*/                          "  ;
		sql += "     , A.THEME_NAME                    /*테마명*/                              "  ;
		sql += "     , C.NICKNAME                             /*회원닉네임*/                   "  ;
		sql += "     , TO_CHAR(B.REVIEW_THEME_SCORE, 99.9) AS reviewThemeScore    /*리뷰점수*/ "  ;
		sql += "     , B.REG_DT                                      /*등록일자*/              "  ;
		sql += "     ,A.THEME_GENRE          /*장르코드*/                                      "  ;
		sql += "     ,B.MEMBER_ID/*회원아이디*/                                                "  ;
		sql += "     ,A.THEME_SN/*테마순번*/                                                   "  ;
		sql += "     ,B.REVIEW_SN/*리뷰순번*/                                                  "  ;
		sql += " FROM  ESCAPE_THEME A   /*방탈출테마 테이블*/                                  "  ;
		sql += "     , REVIEW_THEME B    /*방탈출리뷰테마 테이블*/                             "  ;
		sql += "     , MEMBERS C          /*회원 테이블*/                                       "  ;
		sql += " WHERE A.THEME_SN = B.THEME_SN                                                 "  ;
		sql += "   AND B.MEMBER_ID = C.ID                                               "  ;
		sql += " ORDER BY B.REG_DT DESC                                                        "  ;
		sql += " ) A                                                                           "  ;

		
		
//		sql += " SELECT";
//		sql += " ROWNUM AS \"ROWNUM\"                     /*글순번*/";
//		sql += " ,B.REVIEW_THEME_CONTENTS                     		/*리뷰내용*/";
//		sql += " ,CASE WHEN A.THEME_GENRE = 1 THEN '공포'";    
//		sql += "       WHEN A.THEME_GENRE = 2 THEN '스릴러'";
//		sql += "       WHEN A.THEME_GENRE = 3 THEN '코믹'";
//		sql += "       WHEN A.THEME_GENRE = 4 THEN '역사'";
//		sql += "       WHEN A.THEME_GENRE = 5 THEN '판타지'";
//		sql += "       WHEN A.THEME_GENRE = 6 THEN '감동'";
//		sql += "       WHEN A.THEME_GENRE = 7 THEN 'SF'";
//		sql += "       WHEN A.THEME_GENRE = 8 THEN '동화'";
//		sql += "       WHEN A.THEME_GENRE = 9 THEN '야외'";
//		sql += "       ELSE '' END AS themeGenreNm               /*장르명*/";
//		sql += " , A.THEME_NAME                                  /*테마명*/";
//		sql += " , C.NICKNAME                                    /*회원닉네임*/";
//		sql += " , TO_CHAR(B.REVIEW_THEME_SCORE, 99.9) AS reviewThemeScore           /*리뷰점수*/";
//		sql += " , B.REG_DT                                      /*등록일자*/";
//		sql += " ,A.THEME_GENRE          /*장르코드*/";
//		sql += " ,B.MEMBER_ID/*회원아이디*/";
//		sql += " ,A.THEME_SN/*테마순번*/";
//		sql += " ,B.REVIEW_SN/*리뷰순번*/";
//		sql += " FROM  ESCAPE_THEME A    /*방탈출테마 테이블*/";
//		sql += "     , REVIEW_THEME B    /*방탈출리뷰테마 테이블*/";
//		sql += "     , MEMBER C          /*회원 테이블*/";
//		sql += " WHERE A.THEME_SN = B.THEME_SN";
//		sql += " AND B.MEMBER_ID = C.MEMBER_ID";
//		sql += " ORDER BY REG_DT DESC";
//		System.out.println("sql"+ sql);
		
		
		try {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			if(conn == null) {
				conn = super.getConnection();
			}
			System.out.println("11");
			// ------------------------------
			// 3. 나의 sql문 집어넣기
			// ------------------------------
			pstmt = conn.prepareStatement(sql);
			System.out.println("12");
			// ------------------------------
			// 4. ? 조건 넣기 (순서에 맞게)
			// ------------------------------
			// pstmt.setString(1, sn);
			
			rs = pstmt.executeQuery();
			System.out.println("13");
			// ------------------------------
			// 5. 쿼리에서 조회한 한row의 데이터를 리턴할 데이터 형식에 담기
			// ------------------------------
			while(rs.next()) {
				lists.add(	this.makeVo(rs) // 쿼리에서 조회한 한row의 데이터
						);
			}
			System.out.println("14");
			if(rs!=null) {rs.close();}
			if(pstmt!=null) {rs.close();}
			if(conn!=null) {rs.close();}
			
		} catch (SQLException e) {
			System.out.println(e.getStackTrace());
		}
		
		System.out.println("15");
		return lists;
	}
	
	private ReviewBoard makeVo(ResultSet rs) throws Exception{
		ReviewBoard vo = new ReviewBoard();
		
		vo.setRowNum(rs.getString("ROWNUM"));
		vo.setReviewThemeContents(rs.getString("REVIEW_THEME_CONTENTS"));
		vo.setThemeGenreNm(rs.getString("THEMEGENRENM"));	
		vo.setThemeName(rs.getString("THEME_NAME")); 	
		vo.setNickname(rs.getString("NICKNAME"));	
		vo.setReviewThemeScore(rs.getString("REVIEWTHEMESCORE"));	
		vo.setRegDt(rs.getString("REG_DT"));	
		
		vo.setMemberId(rs.getString("MEMBER_ID"));		
		vo.setThemeSn(rs.getInt("THEME_SN"));		
		vo.setReviewSn(rs.getInt("REVIEW_SN"));		
		
		return vo;
	}
	
	/*************************
	 * 장르조건으로 검색
	 **************************/
	public List<ReviewBoard> getReviewListWhere(String srchData, String selectGenreData, String selectThemeData, String selectScoreData, String srchTextGubun)throws Exception{
		
		
		System.out.println ("서치데이타 ::::::" +  srchData);
		System.out.println("장르셀렉트데이타 :::::: " + selectGenreData);
		System.out.println ("테마셀렉트데이타 ::::::" +  selectThemeData);
		System.out.println ("테마평점순데이타 ::::::" +  selectScoreData);
		System.out.println ("검색텍스트구분데이타 ::::::" +  srchTextGubun);
		
			String sql = " ";
			
			sql += " SELECT";
			sql += " ROWNUM AS \"ROWNUM\"                     		/*글순번*/"; 
			sql += " ,B.REVIEW_THEME_CONTENTS                     		/*리뷰내용*/"; 
			sql += " ,CASE WHEN A.THEME_GENRE = 1 THEN '공포'";    
			sql += "       WHEN A.THEME_GENRE = 2 THEN '스릴러'";
			sql += "       WHEN A.THEME_GENRE = 3 THEN '코믹'";
			sql += "       WHEN A.THEME_GENRE = 4 THEN '역사'";
			sql += "       WHEN A.THEME_GENRE = 5 THEN '판타지'";
			sql += "       WHEN A.THEME_GENRE = 6 THEN '감동'";
			sql += "       WHEN A.THEME_GENRE = 7 THEN 'SF'";
			sql += "       WHEN A.THEME_GENRE = 8 THEN '동화'";
			sql += "       WHEN A.THEME_GENRE = 9 THEN '야외'";
			sql += "       ELSE '' END AS themeGenreNm               /*장르명*/";
			sql += " , A.THEME_NAME                                  /*테마명*/";
			sql += " , C.NICKNAME                                    /*회원닉네임*/";
			sql += " , TO_CHAR(B.REVIEW_THEME_SCORE, 99.9) AS reviewThemeScore           /*리뷰점수*/";
			sql += " , B.REG_DT                                      /*등록일자*/";
			sql += " ,A.THEME_GENRE          						 /*장르코드*/";
			sql += " ,B.MEMBER_ID								     /*회원아이디*/";
			sql += " ,A.THEME_SN									 /*테마순번*/";
			sql += " ,B.REVIEW_SN									 /*리뷰순번*/";
			sql += " FROM  ESCAPE_THEME A   						 /*방탈출테마 테이블*/";
			sql += "     , REVIEW_THEME B  						     /*방탈출리뷰테마 테이블*/";
			sql += "     , MEMBERS C       							 /*회원 테이블*/";
			sql += " WHERE A.THEME_SN = B.THEME_SN";
			sql += " AND B.MEMBER_ID = C.ID";
					 
			// 0이 아니면 where 장르코드 추가
			
			// 전체 조회
//			if("0".equals(selectGenreData)||"0".equals(selectThemeData)) {
//				
//			}
			
			
			// 1.테마장르가 0이면 
			if("0".equals(selectGenreData)) {
			}else { 
				sql += " AND A.THEME_GENRE = ?";
			}
			
			// 2.테마명이 0이면
			if("0".equals(selectThemeData)) {
			}else {
				sql += " AND A.THEME_NAME = ?";
			}
			
			// srchData
			// 3. 검색텍스트조건이 0이면  전체(작성자 + 리뷰내용 + 테마명)
			if("0".equals(srchTextGubun)) { //AND A.SUBJECT || B.NICKNAME || A.CONTENTS LIKE ?
				sql += " AND A.THEME_NAME || C.NICKNAME || B.REVIEW_THEME_CONTENTS LIKE ?";
			// 4. 검색텍스트조건이 1이면  작성자
			}else if("1".equals(srchTextGubun)){ 
				sql += " AND C.NICKNAME LIKE ?";
			// 5. 검색텍스트조건이 2이면  리뷰내용
			}else {
				sql += " AND B.REVIEW_THEME_CONTENTS LIKE ?";
			}
			
			// 6.평점순이 0이면
			if("0".equals(selectScoreData)) {
			// 1이면 높은순
			}else if("1".equals(selectScoreData)){
				sql += " ORDER BY REVIEW_THEME_SCORE DESC";
			}else {
				sql += " ORDER BY REVIEW_THEME_SCORE";
			}
		List<ReviewBoard> srchReviewList = new ArrayList<ReviewBoard>();
		
		try {
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null ;
			if(conn == null) {
				conn = super.getConnection();
			}
			//----------------------
			// 내가 만든 sql 작성 끝 (?) 
			//----------------------
			System.out.println("selectGenreData:"+selectGenreData);
			System.out.println("selectThemeData:"+selectThemeData);
			System.out.println("sql:"+sql);
			pstmt = conn.prepareStatement(sql);
			
			//----------------------
			// 조건 파라미터값 넣기 시작
			//----------------------
			int paramInt = 0; //파라미터의 순서 숫자 처음엔 0
			// 1.테마장르가 0이면 
			if("0".equals(selectGenreData)) {
				
			}else { //테마장르가 0이 아니면
				// 2.?에 파라미터 넣는다. 순번을 1로 해서
				paramInt= paramInt + 1; 
				pstmt.setInt(paramInt, Integer.parseInt(selectGenreData));
				System.out.println("paramInt1::"+paramInt);
			}
			
			// 2.테마명이 0이면
			if("0".equals(selectThemeData)) {
				
			}else {
				// 2.?에 파라미터 넣는다. 순번을 1로 해서
				paramInt= paramInt + 1; 
				pstmt.setString(paramInt, selectThemeData);
				System.out.println("paramInt2::"+paramInt);
			}
			// 3.검색조건에 대한 텍스트
			pstmt.setString(paramInt+1, "%" + srchData + "%");
			
			
			// pstmt.setString(1, "%"+srchData+"%");
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				srchReviewList.add(this.makeVo(rs));
			}
			
			if(rs!=null) {rs.close();}
			if(pstmt!=null) {pstmt.close();}
			if(conn!=null) {conn.close();}
				
		} catch (Exception  e) {
			e.printStackTrace();
		}
		
		System.out.println("리스트의 길이 ::::::::::" + srchReviewList.size());
		
		
		return srchReviewList;
	}
}





