package com.escape.board.reviewBoard.model;

public class ReviewBoard {
	
	private String rowNum;    				// 글순번
	private String themeGenreNm;		// 장르명
	private String themeName ;			// 테마명
	private String nickname;			// 닉네임
	private String reviewThemeScore;	// 리뷰테마스코어	
	private String regDt;				// 등록일자
	private String reviewThemeContents;				// 리뷰내용

	private String themeGenre ;			// 테마장르코드
	private String memberId;			// 회원아이디
	private int themeSn;				// 테마순번
	private int reviewSn;				// 리뷰순번

	
	public String getReviewThemeContents() {
		return reviewThemeContents;
	}
	public void setReviewThemeContents(String reviewThemeContents) {
		this.reviewThemeContents = reviewThemeContents;
	}
	public String getRowNum() {
		return rowNum;
	}
	public void setRowNum(String rowNum) {
		this.rowNum = rowNum;
	}
	public String getThemeGenreNm() {
		return themeGenreNm;
	}
	public void setThemeGenreNm(String themeGenreNm) {
		this.themeGenreNm = themeGenreNm;
	}
	public String getThemeName() {
		return themeName;
	}
	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getReviewThemeScore() {
		return reviewThemeScore;
	}
	public void setReviewThemeScore(String reviewThemeScore) {
		this.reviewThemeScore = reviewThemeScore;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	public String getThemeGenre() {
		return themeGenre;
	}
	public void setThemeGenre(String themeGenre) {
		this.themeGenre = themeGenre;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public int getThemeSn() {
		return themeSn;
	}
	public void setThemeSn(int themeSn) {
		this.themeSn = themeSn;
	}
	public int getReviewSn() {
		return reviewSn;
	}
	public void setReviewSn(int reviewSn) {
		this.reviewSn = reviewSn;
	}
	
}
