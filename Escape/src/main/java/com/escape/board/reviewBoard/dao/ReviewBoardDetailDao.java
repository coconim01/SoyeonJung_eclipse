/**
 * 페이지명 : 자유게시판 상세페이지 Dao
 * 작성일자 : 2023-03-05
 * 작성자 : 정영우
 */
package com.escape.board.reviewBoard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.escape.board.reviewBoard.model.ReviewBoard;
import com.escape.dao.SuperDao;

public class ReviewBoardDetailDao extends SuperDao{

	/********************************************
	 * 리뷰게시판 디테일화면 오픈했을때 조회()하는 메소드
	 ********************************************/
	public ReviewBoard getReviewBoardDetail(String sn) {
		System.out.println("디테일메소드 순번1 " + sn);
		
		// ------------------------------
		// 1. return할 변수 선언
		// ------------------------------
		ReviewBoard dto = new ReviewBoard();
		// ------------------------------
		// 2. 실행할 sql 쿼리문 작성
		// ------------------------------
		String sql = " ";
		
	    sql += " SELECT                                                                       ";
		sql += " B.REVIEW_THEME_CONTENTS                     		/*리뷰내용*/             ";
		sql += " ,CASE WHEN A.THEME_GENRE = 1 THEN '공포'                                    ";
		sql += " WHEN A.THEME_GENRE = 2 THEN '스릴러'                                        ";
		sql += " WHEN A.THEME_GENRE = 3 THEN '코믹'                                          ";
		sql += " WHEN A.THEME_GENRE = 4 THEN '역사'                                          ";
		sql += " WHEN A.THEME_GENRE = 5 THEN '판타지'                                        ";
		sql += " WHEN A.THEME_GENRE = 6 THEN '감동'                                          ";
		sql += " WHEN A.THEME_GENRE = 7 THEN 'SF'                                            ";
		sql += " WHEN A.THEME_GENRE = 8 THEN '동화'                                          ";
		sql += " WHEN A.THEME_GENRE = 9 THEN '야외'                                          ";
		sql += " ELSE '' END AS themeGenreNm           /*장르명*/                            ";
		sql += " , A.THEME_NAME                    /*테마명*/                                ";
		sql += " , C.NICKNAME                             /*회원닉네임*/                     ";
		sql += " ,B.REVIEW_THEME_SCORE AS reviewThemeScore    /*리뷰점수*/   ";
		sql += " , B.REG_DT                                      /*등록일자*/                ";
		sql += " ,A.THEME_GENRE          /*장르코드*/                                        ";
		sql += " ,B.MEMBER_ID/*회원아이디*/                                                  ";
		sql += " ,A.THEME_SN/*테마순번*/                                                     ";
		sql += " ,B.REVIEW_SN/*리뷰순번*/                                                    ";
		sql += " FROM  ESCAPE_THEME A   /*방탈출테마 테이블*/                                ";
		sql += " , REVIEW_THEME B    /*방탈출리뷰테마 테이블*/                               ";
		sql += " , MEMBERS C          /*회원 테이블*/                                         ";
		sql += " WHERE A.THEME_SN = B.THEME_SN                                               ";
		sql += " AND B.MEMBER_ID = C.ID                                               ";
		sql += " AND B.REVIEW_SN = ?                                                         ";
		sql += " ORDER BY B.REG_DT DESC";
				
		
		System.out.println("디테일메소드 순번2 " + sn);
		
		try {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null ;
			if(conn == null) {
				conn = super.getConnection();
			}
			System.out.println("디테일메소드 순번3 " + sn);
			// ------------------------------
			// 3. 나의 sql문 집어넣기
			// ------------------------------
			pstmt = conn.prepareStatement(sql);
			System.out.println("디테일메소드 순번4 " + sn);
			// ------------------------------
			// 4. ? 조건 넣기 (순서에 맞게)
			// ------------------------------
			pstmt.setString(1, sn);
			System.out.println("디테일메소드 순번5 " + sn);
			rs = pstmt.executeQuery(); 
			System.out.println("디테일메소드 순번6 " + sn);
			// ------------------------------
			// 5. 쿼리에서 조회한 한row의 데이터를 리턴할 데이터 형식에 담기
			// ------------------------------
			while(rs.next()) {
				// 쿼리에서 조회한 row데이터를 내 dto로 담기
				dto = 	this.makeVo(rs); // rs=쿼리에서 조회한 한row의 데이터
			}
			System.out.println("디테일메소드 순번7 " + sn);
			if(rs!=null) {rs.close();}
			if(pstmt!=null) {pstmt.close();}
			if(conn!=null) {conn.close();}
			System.out.println("디테일메소드 순번8 " + sn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("디테일메소드 순번9 " + sn);
		// dto 리턴
		return dto; 
	}
	
	
	/********************************************
	 * 오라클에서 조회한 데이터를 -> 내 dto로 담는 메서드
	 * 파라미터 타입 : ResultSet
	 * 리턴 타입 : ReviewBoard 객체 (dto, Vo)
	 ********************************************/
	private ReviewBoard makeVo(ResultSet rs) throws Exception{
		
		ReviewBoard vo = new ReviewBoard();
		
		vo.setRowNum(rs.getString("REVIEW_SN"));
		vo.setThemeName(rs.getString("THEME_NAME"));
		vo.setNickname(rs.getString("NICKNAME"));
		vo.setReviewThemeScore(rs.getString("reviewThemeScore"));
		vo.setRegDt(rs.getString("REG_DT"));
		vo.setReviewThemeContents(rs.getString("REVIEW_THEME_CONTENTS"));
		vo.setThemeGenre(rs.getString("THEME_GENRE"));
		vo.setMemberId(rs.getString("MEMBER_ID"));
		vo.setThemeSn(rs.getInt("THEME_SN"));
			
		
		return vo;
	}
	

	
	private ReviewBoard makeVo1(ResultSet rs) throws Exception{
		ReviewBoard vo = new ReviewBoard();
		
		vo.setThemeName(rs.getString("THEME_NAME"));	
		vo.setNickname(rs.getString("NICKNAME"));		
		vo.setReviewThemeScore(rs.getString("REVIEW_THEME_SCORE"));		
		vo.setReviewThemeContents(rs.getString("REVIEW_THEME_CONTENTS"));		
		
		return vo;
	}

	/*****************************
	 * 상세페이지의 게시글을 수정하는 메소드(UPDATE)
	 *****************************/
	public int updateReviewBoardDetailDto(String reviewContent, String reviewBoardSn) throws Exception{
		
		// 업데이트한 결과 갯수
		int resultUpdateCnt = -1; 

		//1. sql만든다.
		String sql = " UPDATE REVIEW_THEME SET REVIEW_THEME_CONTENTS = ? WHERE REVIEW_SN = ? ";
		try {
			//2. jdbc 연결한다.
			Connection conn = null;
			PreparedStatement pstmt = null;
			if(conn==null) {conn=super.getConnection();}
			conn.setAutoCommit(false);
			
			System.out.println("sql::::::"+ sql);
			//3. oracle에 내가만든 sql 넣는다.
			pstmt = conn.prepareStatement(sql);
			
			//4. 조건이 있으면 조건을 넣어준다.
			pstmt.setString(1, reviewContent);
			System.out.println("reviewBoardSn1::"+reviewBoardSn);
			int sn = Integer.valueOf(reviewBoardSn);
			System.out.println("reviewBoardSn2::"+sn);
			pstmt.setInt(2, sn);

			//5. 결과값을 받는다.
			resultUpdateCnt = pstmt.executeUpdate();
			System.out.println("업데이트수 :::::" + resultUpdateCnt);
			System.out.println("resultUpdateCnt:"+resultUpdateCnt);
			//6. 커밋
			conn.commit();
			
			//6. db접속을 끊는다.
			if(pstmt!=null) {pstmt.close();}
			if(conn!=null) {conn.close();}	
		} catch (Exception  e) {
			e.printStackTrace();
		}
		//6. 결과값을 줘야돼 . 화면에다가보내줘.
		return resultUpdateCnt;
	}
	
	/*****************************
	 * 게시글 삭제하는 메소드 (DELETE)
	 *****************************/
//	public int deleteReviewBoardDetailDto(String freeBoardSn) {
//		// 삭제한 결과 갯수
//		int resultdeleteCnt = -1; 
//
//		//1. sql만든다.
//		String sql = " DELETE FROM FREE_BOARD WHERE FREE_BOARD_SN = ?";
//		try {
//			//2. jdbc 연결한다.
//			Connection conn = null;
//			PreparedStatement pstmt = null;
//			if(conn==null) {conn=super.getConnection();}
//			conn.setAutoCommit(false);
//			
//			//3. oracle에 내가만든 sql 넣는다.
//			pstmt = conn.prepareStatement(sql);
//			
//			//4. 조건이 있으면 조건을 넣어준다.
//			pstmt.setString(1,freeBoardSn);
//	
//			//5. 결과값을 받는다.
//			resultdeleteCnt = pstmt.executeUpdate();
//			System.out.println("delete수 :::::" + resultdeleteCnt);
//			
//			//6. 커밋
//			conn.commit();
//			
//			//6. db접속을 끊는다.
//			if(pstmt!=null) {pstmt.close();}
//			if(conn!=null) {conn.close();}	
//		} catch (Exception  e) {
//			e.printStackTrace();
//		}
//		//6. 결과값을 줘야돼 . 화면에다가보내줘.
//		return resultdeleteCnt;		
//	
//	}
	
	
	/*********************
	 * 글작성페이지 오픈시 닉네임 조회 메소드
	 *********************/
	public String getNickname(String memberId) {
		
		String nickName = "";
		String sql = " SELECT NICKNAME FROM MEMBERS WHERE ID = ? /*파라미터1_회원아이디*/"; 
		
		try {
			//2. jdbc 연결한다.
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null ;
			if(conn == null) {
				conn = super.getConnection();
			}
			
			//3. oracle에 내가만든 sql 넣는다.
			pstmt = conn.prepareStatement(sql);
			
			//4. 조건이 있으면 조건을 넣어준다.
			pstmt.setString(1,memberId);
			
			// 실행
			rs = pstmt.executeQuery(); 
			
			// ------------------------------
			// 5. 쿼리에서 조회한 한row의 데이터를 리턴할 데이터 형식에 담기
			// ------------------------------
			//5. 결과값을 받는다.
			while(rs.next()) {
				// 쿼리에서 조회한 row데이터를 내 String로 담기
				nickName = rs.getString("NICKNAME"); // rs=쿼리에서 조회한 한row의 데이터
			}
			
			if (pstmt!=null) {pstmt.close();}
			if (conn!=null) {conn.close();}
		
		} catch (Exception  e) {
			e.printStackTrace();
		}
		//6. 결과값을 줘야돼 . 화면에다가보내줘.
		
		return nickName;
		
	}

	/*********************
	 * 리뷰게시글 글작성 메소드(INSERT)
	 *********************/
	public int reviewBoardDetailRegForm(String themeNm, String loginId, String scoreStar, String reviewContent) throws Exception {
		
		
		System.out.println("테마순번::::"+themeNm);
		System.out.println("로그인아이디::::"+loginId);
		System.out.println("리뷰별점::::"+scoreStar);
		System.out.println("리뷰글내용::::"+reviewContent);
		
		
		
		
		// 등록한 결과 갯수
		int resultReviewRegCnt = -1; 
		
		
		String sql = " INSERT INTO REVIEW_THEME(REVIEW_SN, THEME_SN, MEMBER_ID, REVIEW_THEME_SCORE, REVIEW_THEME_CONTENTS, REG_DT)VALUES((SELECT NVL(MAX(REVIEW_SN),0)+1 FROM REVIEW_THEME),(SELECT THEME_SN FROM ESCAPE_THEME WHERE THEME_NAME = '대영어시대'),?,?,?,SYSDATE)";
		
		try {
			//2. jdbc 연결
			Connection conn = null;
			PreparedStatement pstmt = null;
			if(conn==null) {conn=super.getConnection();}
			conn.setAutoCommit(false);
			
			System.out.println("글쓰기등록수2222222 :::::");
			
			//3. oracle에 내가만든 sql 넣는다.
			pstmt = conn.prepareStatement(sql);
			System.out.println("글쓰기등록수333333333 :::::" );
			
			//4. 조건을 넣어준다.
			pstmt.setString(1,themeNm);
			pstmt.setString(2,loginId);
			pstmt.setString(3,scoreStar);
			pstmt.setString(4,reviewContent);

			System.out.println("글쓰기등록수4444444 :::::" );
	
			
			resultReviewRegCnt = pstmt.executeUpdate();
			conn.commit();
			if (pstmt!=null) {pstmt.close();}
			if (conn!=null) {conn.close();}
		
			System.out.println("글쓰기등록수555555555 :::::" + resultReviewRegCnt);
		} catch (Exception  e) {
			e.printStackTrace();
		}
		//6. 결과값 화면에 보여줌
		return resultReviewRegCnt;
	}


	public int deleteReviewBoardDetailDto(String reviewBoardSn) {
		// 삭제한 결과 갯수
		System.out.println("reviewBoardSn :::::" + reviewBoardSn);
				int resultdeleteCnt = -1; 

				//1. sql만든다.
				String sql = " DELETE FROM REVIEW_THEME WHERE REVIEW_SN = ? ";
				try {
					//2. jdbc 연결한다.
					Connection conn = null;
					PreparedStatement pstmt = null;
					if(conn==null) {conn=super.getConnection();}
					conn.setAutoCommit(false);
					
					//3. oracle에 내가만든 sql 넣는다.
					pstmt = conn.prepareStatement(sql);
					
					//4. 조건이 있으면 조건을 넣어준다.
					pstmt.setString(1,reviewBoardSn);
			
					//5. 결과값을 받는다.
					resultdeleteCnt = pstmt.executeUpdate();
					System.out.println("reviewdelete수 :::::" + resultdeleteCnt);
					
					//6. 커밋
					conn.commit();
					
					//6. db접속을 끊는다.
					if(pstmt!=null) {pstmt.close();}
					if(conn!=null) {conn.close();}	
				} catch (Exception  e) {
					e.printStackTrace();
				}
				//6. 결과값
				return resultdeleteCnt;		
	}
	
}
