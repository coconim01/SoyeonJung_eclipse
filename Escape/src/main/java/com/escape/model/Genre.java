package com.escape.model;

public class Genre {
	private String engname ; // 영문 이름
	private String korname ; // 한글 이름
	
	public Genre() {
	}

	@Override
	public String toString() {
		return "Genre [engname=" + engname + ", korname=" + korname + "]";
	}

	public String getEngname() {
		return engname;
	}

	public void setEngname(String engname) {
		this.engname = engname;
	}

	public String getKorname() {
		return korname;
	}

	public void setKorname(String korname) {
		this.korname = korname;
	}

	
	
}
