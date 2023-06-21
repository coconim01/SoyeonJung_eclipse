/**
 * 페이지명 : 자유게시판 상세페이지 Dao
 * 작성일자 : 2023-03-05
 * 작성자 : 정영우
 */
package com.escape.board.freeBoard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.escape.board.freeBoard.model.FreeBoard;
import com.escape.dao.SuperDao;

public class FreeBoardDetailDao extends SuperDao{

	/********************************************
	 * 자유게시판 디테일화면 오픈했을때 조회()하는 메소드
	 ********************************************/
	public FreeBoard getFreeBoardDetail(String sn) {
		System.out.println("디테일메소드 순번1 " + sn);
		
		// ------------------------------
		// 1. return할 변수 선언
		// ------------------------------
		FreeBoard dto = new FreeBoard();
		// ------------------------------
		// 2. 실행할 sql 쿼리문 작성
		// ------------------------------
		String sql = " SELECT A.*, B.NICKNAME , (SELECT COUNT(*) FROM FREE_BOARD_REPLY WHERE FREE_BOARD_SN = A.FREE_BOARD_SN) AS replyCnt FROM FREE_BOARD A, MEMBERS B WHERE A.MEMBER_ID = B.ID AND A.FREE_BOARD_SN = ?";
		
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
	 * 리턴 타입 : FreeBoard 객체 (dto, Vo)
	 ********************************************/
	private FreeBoard makeVo(ResultSet rs) throws Exception{
		
		FreeBoard vo = new FreeBoard();
		
		vo.setFreeBoardSn(rs.getInt("FREE_BOARD_SN"));
		vo.setMemberId(rs.getString("MEMBER_ID"));	
		vo.setSubject(rs.getString("SUBJECT")); 	
		vo.setContents(rs.getString("CONTENTS"));	
		vo.setReadCnt(rs.getInt("READ_CNT"));	
		vo.setRegDt(rs.getString("REG_DT"));		
		vo.setNickname(rs.getString("NICKNAME"));		
		vo.setReplyCnt(rs.getString("replyCnt"));		
		
		return vo;
	}
	

	/*****************************
	 * 상세페이지의 게시글의 댓글을 조회하는 메소드(SELECT)
	 *****************************/
	public List<FreeBoard> getFreeBoardDetailReplyList(String sn) {
		System.out.println("디테일리플레이메소드 순번 " + sn);
		// ------------------------------
		// 1. return할 타입 변수 선언
		// ------------------------------
		List<FreeBoard> list = new ArrayList<FreeBoard>();
		// ------------------------------
		// 2. 실행할 sql 쿼리문 작성
		// ------------------------------
		String sql = " SELECT A.NICKNAME, B.CONTENTS, TO_CHAR(B.REG_DT, 'YYYY-MM-DD HH24:MI:SS') AS REG_DT FROM MEMBERS A, FREE_BOARD_REPLY B WHERE A.ID = B.MEMBER_ID AND B.FREE_BOARD_SN = ? ORDER BY FREE_BOARD_REPLY_SN ASC";
		
		
		try {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null ;
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
			pstmt.setString(1, sn);
		
			rs = pstmt.executeQuery(); 
			
			// ------------------------------
			// 5. 쿼리에서 조회한 한row의 데이터를 리턴할 데이터 형식에 담기
			// ------------------------------
			while(rs.next()) {
				list.add(this.makeVo1(rs)); // 쿼리에서 조회한 한row의 데이터
			}
			
			if(rs!=null) {rs.close();}
			if(pstmt!=null) {pstmt.close();}
			if(conn!=null) {conn.close();}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		// dto 리턴
		return list; 
	}
	private FreeBoard makeVo1(ResultSet rs) throws Exception{
		FreeBoard vo = new FreeBoard();
		
		vo.setContents(rs.getString("CONTENTS"));	
		vo.setRegDt(rs.getString("REG_DT"));		
		vo.setNickname(rs.getString("NICKNAME"));		
		
		return vo;
	}

	/*****************************
	 * 상세페이지의 게시글을 수정하는 메소드(UPDATE)
	 *****************************/
	public int updateFreeBoardDetailDto(String subject, String content, String freeBoardSn) throws Exception{
		
		// 업데이트한 결과 갯수
		int resultUpdateCnt = -1; 

		//1. sql만든다.
		String sql = " UPDATE FREE_BOARD SET SUBJECT = ?, CONTENTS = ? WHERE FREE_BOARD_SN = ?";
		try {
			//2. jdbc 연결한다.
			Connection conn = null;
			PreparedStatement pstmt = null;
			if(conn==null) {conn=super.getConnection();}
			conn.setAutoCommit(false);
			
			//3. oracle에 내가만든 sql 넣는다.
			pstmt = conn.prepareStatement(sql);
			
			//4. 조건이 있으면 조건을 넣어준다.
			pstmt.setString(1,subject);
			pstmt.setString(2,content);
			pstmt.setString(3,freeBoardSn);
	
			//5. 결과값을 받는다.
			resultUpdateCnt = pstmt.executeUpdate();
			System.out.println("업데이트수 :::::" + resultUpdateCnt);
			
			//6. 커밋
			conn.commit();
			
			//6. db접속을 끊는다.
			if(pstmt!=null) {pstmt.close();}
			if(conn!=null) {conn.close();}	
		} catch (Exception  e) {
			e.printStackTrace();
		}
		//6. 결과값을 줌야됨 . 화면에다가보내줌.
		return resultUpdateCnt;
	}
	/*****************************
	 * 게시글 삭제하는 메소드 (DELETE)
	 *****************************/
	public int deleteFreeBoardDetailDto(String freeBoardSn) {
		// 삭제한 결과 갯수
		int resultdeleteCnt = -1; 

		//1. sql만든다.
		String sql = " DELETE FROM FREE_BOARD WHERE FREE_BOARD_SN = ?";
		try {
			//2. jdbc 연결한다.
			Connection conn = null;
			PreparedStatement pstmt = null;
			if(conn==null) {conn=super.getConnection();}
			conn.setAutoCommit(false);
			
			//3. oracle에 내가만든 sql 넣는다.
			pstmt = conn.prepareStatement(sql);
			
			//4. 조건이 있으면 조건을 넣어준다.
			pstmt.setString(1,freeBoardSn);
	
			//5. 결과값을 받는다.
			resultdeleteCnt = pstmt.executeUpdate();
			System.out.println("delete수 :::::" + resultdeleteCnt);
			
			//6. 커밋
			conn.commit();
			
			//6. db접속을 끊는다.
			if(pstmt!=null) {pstmt.close();}
			if(conn!=null) {conn.close();}	
		} catch (Exception  e) {
			e.printStackTrace();
		}
		//6. 결과값을 줌야됨 . 화면에다가보내줌.
		return resultdeleteCnt;		
	
	}
	
	/*****************************
	 * 게시글 등록하는 메소드 (INSERT)
	 *****************************/
	public int FreeBoardDetailRegForm(String memberId, String subject, String content) throws Exception{
		System.out.println("멤버아이디::::"+memberId);
		System.out.println("제목::::"+subject);
		System.out.println("내용::::"+content);
		
		// 등록한 결과 갯수
		int resultRegCnt = -1; 
		
		//1. sql만든다. 
		String sql = " INSERT INTO FREE_BOARD ( FREE_BOARD_SN ,MEMBER_ID ,SUBJECT ,CONTENTS ,READ_CNT ,REG_DT ) VALUES ( ( SELECT NVL( MAX(FREE_BOARD_SN ) , 0 ) +1 FROM FREE_BOARD ), ? , ? , ?, 0 , SYSDATE )";
		try {
			//2. jdbc 연결한다.
			Connection conn = null;
			PreparedStatement pstmt = null;
			if(conn==null) {conn=super.getConnection();}
			conn.setAutoCommit(false);
			
			System.out.println("글쓰기등록수2222222 :::::");
			
			//3. oracle에 내가만든 sql 넣는다.
			pstmt = conn.prepareStatement(sql);
			System.out.println("글쓰기등록수333333333 :::::" );
			//4. 조건이 있으면 조건을 넣어준다.
			pstmt.setString(1,memberId);
			pstmt.setString(2,subject);
			pstmt.setString(3,content);
			System.out.println("글쓰기등록수4444444 :::::" );
	
			//5. 결과값을 받는다.
			resultRegCnt = pstmt.executeUpdate();
			conn.commit();
			if (pstmt!=null) {pstmt.close();}
			if (conn!=null) {conn.close();}
			//return resultRegCnt;
		
			System.out.println("글쓰기등록수555555555 :::::" + resultRegCnt);
		} catch (Exception  e) {
			e.printStackTrace();
		}
		//6. 결과값을 줌야됨 . 화면에다가보내줌
		return resultRegCnt;
	}
	
	/***************************
	* 게시물 조회수 증가를 위한 메소드 (UPDATE)
	****************************/
	public int readCntFreeBoardDetailDto(String freeBoardSn) throws Exception{
		System.out.println("조회수 메소드에 왔나");
		// 업데이트한 결과 갯수
		int resultReadCnt = -1; 

		//1. sql만든다.
		String sql = " update free_board set read_cnt = read_cnt+1 where free_board_sn = ?";
		try {
			//2. jdbc 연결한다.
			Connection conn = null;
			PreparedStatement pstmt = null;
			if(conn==null) {conn=super.getConnection();}
			conn.setAutoCommit(false);
			
			//3. oracle에 내가만든 sql 넣는다.
			pstmt = conn.prepareStatement(sql);
			
			//4. 조건이 있으면 조건을 넣어준다.
			pstmt.setString(1,freeBoardSn);
		
	
			//5. 결과값을 받는다.
			resultReadCnt = pstmt.executeUpdate();
			System.out.println("조회수 :::::" + resultReadCnt);
			
			//6. 커밋
			conn.commit();
			System.out.println(" 리턴전에 값1" + resultReadCnt);
			//6. db접속을 끊는다.
			if(pstmt!=null) {pstmt.close();}
			if(conn!=null) {conn.close();}	
		} catch (Exception  e) {
			e.printStackTrace();
		}
		//6. 결과값을 줌야됨 . 화면에다가보내줌.
		System.out.println(" 리턴전에 값2" + resultReadCnt);
		return resultReadCnt;
	}

	/*****************************
	 * 게시글의 댓글을 등록하는 메소드 (INSERT)
	 *****************************/
	public int FreeBoardDetailReplyRegForm(String freeBoardSn, String loginId, String replyInput) {
		System.out.println("게시글 순번::::"+freeBoardSn);
		System.out.println("로그인한 회원 아이디::::"+loginId);
		System.out.println("댓글내용::::"+replyInput);
		
		// 댓글 등록한 결과 갯수
		int resultReplyRegCnt = -1; 
		
		//1. sql만든다. 
		String sql = " INSERT INTO FREE_BOARD_REPLY(FREE_BOARD_REPLY_SN, FREE_BOARD_SN, MEMBER_ID, CONTENTS, REG_DT)VALUES((SELECT NVL(MAX(FREE_BOARD_REPLY_SN),0)+1 FROM FREE_BOARD_REPLY), ?, ?, ?, SYSDATE)";
		try {
			//2. jdbc 연결한다.
			Connection conn = null;
			PreparedStatement pstmt = null;
			if(conn==null) {conn=super.getConnection();}
			conn.setAutoCommit(false);
			
			System.out.println("글쓰기등록수2222222 :::::");
			
			//3. oracle에 내가만든 sql 넣는다.
			pstmt = conn.prepareStatement(sql);
			System.out.println("글쓰기등록수333333333 :::::" );
			//4. 조건이 있으면 조건을 넣어준다.
			pstmt.setString(1,freeBoardSn);
			pstmt.setString(2,loginId);
			pstmt.setString(3,replyInput);
			System.out.println("글쓰기등록수4444444 :::::" );
	
			//5. 결과값을 받는다.
			resultReplyRegCnt = pstmt.executeUpdate();
			conn.commit();
			if (pstmt!=null) {pstmt.close();}
			if (conn!=null) {conn.close();}
			//return resultRegCnt;
		
			System.out.println("글쓰기등록수555555555 :::::" + resultReplyRegCnt);
		} catch (Exception  e) {
			e.printStackTrace();
		}
		System.out.println("글쓰기등록수555555555 :::::" + resultReplyRegCnt);
		//6. 결과값을 줌야됨 . 화면에다가보내줌.
		return resultReplyRegCnt;
	}
	
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
		//6. 결과값을 줌야됨 . 화면에다가보내줌.
		
		return nickName;
		
	}
	
	
	
	
}
