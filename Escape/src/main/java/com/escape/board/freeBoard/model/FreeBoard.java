package com.escape.board.freeBoard.model;

public class FreeBoard {
	
	private int 	no; 			// 글목록순번
	private int 	freeBoardSn;	// 자유게시판순번
	private String  memberId;		// 멤버ID
	private String  subject; 		// 글제목
	private String  contents;		// 글내용
	private int 	readCnt;		// 조회수
	private String  regDt;			// 등록일자
	private String  nickname; 		// 닉네임
	private String  replyCnt; 		// 댓글수
	

	public String getReplyCnt() {
		return replyCnt;
	}
	public void setReplyCnt(String replyCnt) {
		this.replyCnt = replyCnt;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getFreeBoardSn() {
		return freeBoardSn;
	}
	public void setFreeBoardSn(int freeBoardSn) {
		this.freeBoardSn = freeBoardSn;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public int getReadCnt() {
		return readCnt;
	}
	public void setReadCnt(int readCnt) {
		this.readCnt = readCnt;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
}
