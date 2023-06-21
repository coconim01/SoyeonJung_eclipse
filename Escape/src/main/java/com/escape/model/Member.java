package com.escape.model;

public class Member {

	private String id;
	private String password;
	private String nickname;
	private String phone;
	private String theme;
	private String hiredate;
	private String gender;
	private String reserv;



	public Member(String id, String password, String nickname, String phone, String theme, String hiredate,
			String gender, String reserv) {
		super();
		this.id = id;
		this.password = password;
		this.nickname = nickname;
		this.phone = phone;
		this.theme = theme;
		this.hiredate = hiredate;
		this.gender = gender;
		this.reserv = reserv;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getNickname() {
		return nickname;
	}



	public void setNickname(String nickname) {
		this.nickname = nickname;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public String getTheme() {
		return theme;
	}



	public void setTheme(String theme) {
		this.theme = theme;
	}



	public String getHiredate() {
		return hiredate;
	}



	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}



	public String getGender() {
		return gender;
	}



	public void setGender(String gender) {
		this.gender = gender;
	}



	public String getReserv() {
		return reserv;
	}



	public void setReserv(String reserv) {
		this.reserv = reserv;
	}



	public Member() {
	}

}
