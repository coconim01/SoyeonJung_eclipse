package com.escape.model;

public class ReserveItem {
	private String mid ; // 고객 아이디 reservetimes

	private int timenum ; // 예약번호 reservetimes

	private String title ; // 테마명 product
	
	private String reservdate; // 테마 일자 reservtimes 에서 reservetime substring하기...

	private int price ; // 단가 product

	private String image01 ; // 이미지 이름 product
	
	private String purchasedate; // 결제일자 reservetimes
	
	private String reservtime; // 테마 시간 reservetimes
	
	private int people; // 참여인원 reservetimes

	public ReserveItem() {
		// TODO Auto-generated constructor stub
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public int getTimenum() {
		return timenum;
	}

	public void setTimenum(int timenum) {
		this.timenum = timenum;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getReservdate() {
		return reservdate;
	}

	public void setReservdate(String reservdate) {
		this.reservdate = reservdate;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getImage01() {
		return image01;
	}

	public void setImage01(String image01) {
		this.image01 = image01;
	}

	public String getPurchasedate() {
		return purchasedate;
	}

	public void setPurchasedate(String purchasedate) {
		this.purchasedate = purchasedate;
	}

	public String getReservtime() {
		return reservtime;
	}

	public void setReservtime(String reservtime) {
		this.reservtime = reservtime;
	}

	public int getPeople() {
		return people;
	}

	public void setPeople(int people) {
		this.people = people;
	}

	@Override
	public String toString() {
		return "ReserveItem [mid=" + mid + ", timenum=" + timenum + ", title=" + title + ", reservdate=" + reservdate
				+ ", price=" + price + ", image01=" + image01 + ", purchasedate=" + purchasedate + ", reservtime="
				+ reservtime + ", people=" + people + "]";
	}

	public ReserveItem(String mid, int timenum, String title, String reservdate, int price, String image01,
			String purchasedate, String reservtime, int people) {
		super();
		this.mid = mid;
		this.timenum = timenum;
		this.title = title;
		this.reservdate = reservdate;
		this.price = price;
		this.image01 = image01;
		this.purchasedate = purchasedate;
		this.reservtime = reservtime;
		this.people = people;
	}

	


}
