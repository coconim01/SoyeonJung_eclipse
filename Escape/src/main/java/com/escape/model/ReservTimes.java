package com.escape.model;

public class ReservTimes {
    
    private int timenum; //예약 시퀀스 seqtime 시퀀스 사용
    private int themenum;//product에서 가져온 primary key
    private String reservtime;// 예약날짜, 시간, 테마넘버가 담긴 unique 데이터
    private int stock;//제고 default 1
    private int qty;//어차피 한개 있는거 없애기 위해 존재하는 수량
    private String mid;//members에 id와 
    private String purchasedate;//예약한 날짜, sysdate
    private int people;//예약시 참여 인원(가격에 필요함)
    
    public ReservTimes() {
		// TODO Auto-generated constructor stub
	}

	public int getTimenum() {
		return timenum;
	}

	public void setTimenum(int timenum) {
		this.timenum = timenum;
	}

	public int getThemenum() {
		return themenum;
	}

	public void setThemenum(int themenum) {
		this.themenum = themenum;
	}

	public String getReservtime() {
		return reservtime;
	}

	public void setReservtime(String reservtime) {
		this.reservtime = reservtime;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getPurchasedate() {
		return purchasedate;
	}

	public void setPurchasedate(String purchasedate) {
		this.purchasedate = purchasedate;
	}

	public int getPeople() {
		return people;
	}

	public void setPeople(int people) {
		this.people = people;
	}

	@Override
	public String toString() {
		return "ReservTimes [timenum=" + timenum + ", themenum=" + themenum + ", reservtime=" + reservtime + ", stock="
				+ stock + ", qty=" + qty + ", mid=" + mid + ", purchasedate=" + purchasedate + ", people=" + people
				+ "]";
	}

	public ReservTimes(int timenum, int themenum, String reservtime, int stock, int qty, String mid,
			String purchasedate, int people) {
		super();
		this.timenum = timenum;
		this.themenum = themenum;
		this.reservtime = reservtime;
		this.stock = stock;
		this.qty = qty;
		this.mid = mid;
		this.purchasedate = purchasedate;
		this.people = people;
	}
    



}
