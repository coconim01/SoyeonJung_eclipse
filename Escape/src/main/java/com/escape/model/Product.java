package com.escape.model;

public class Product {
	
	private int productnum;
	private String title;
	private String genre;
	private int themalevel;
	private String recommendpeople;
	private int usetime;
	private String area;
	private int price;
	private String image01;
	private String image02;
	private String comments;
	private String address;
	private String uploaddate;
	private String time01;
	private String time02;
	private String time03;
	private String time04;
	private String time05;
	
	public Product() {
		// TODO Auto-generated constructor stub
	}

 
	
	public Product(int productnum, String title, String genre, int themalevel, String recommendpeople, int usetime,
			String area, int price, String image01, String image02, String comments, String address, String uploaddate,
			String time01, String time02, String time03, String time04, String time05) {
		super();
		this.productnum = productnum;
		this.title = title;
		this.genre = genre;
		this.themalevel = themalevel;
		this.recommendpeople = recommendpeople;
		this.usetime = usetime;
		this.area = area;
		this.price = price;
		this.image01 = image01;
		this.image02 = image02;
		this.comments = comments;
		this.address = address;
		this.uploaddate = uploaddate;
		this.time01 = time01;
		this.time02 = time02;
		this.time03 = time03;
		this.time04 = time04;
		this.time05 = time05;
	}



	public String getTime01() {
		return time01;
	}

	public void setTime01(String time01) {
		this.time01 = time01;
	}

	public String getTime02() {
		return time02;
	}

	public void setTime02(String time02) {
		this.time02 = time02;
	}

	public String getTime03() {
		return time03;
	}

	public void setTime03(String time03) {
		this.time03 = time03;
	}

	public String getTime04() {
		return time04;
	}

	public void setTime04(String time04) {
		this.time04 = time04;
	}

	public String getTime05() {
		return time05;
	}

	public void setTime05(String time05) {
		this.time05 = time05;
	}

	public int getProductnum() {
		return productnum;
	}

	public void setProductnum(int productnum) {
		this.productnum = productnum;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getThemalevel() {
		return themalevel;
	}

	public void setThemalevel(int themalevel) {
		this.themalevel = themalevel;
	}

	public String getRecommendpeople() {
		return recommendpeople;
	}

	public void setRecommendpeople(String recommendpeople) {
		this.recommendpeople = recommendpeople;
	}

	public int getUsetime() {
		return usetime;
	}

	public void setUsetime(int usetime) {
		this.usetime = usetime;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
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

	public String getImage02() {
		return image02;
	}

	public void setImage02(String image02) {
		this.image02 = image02;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUploaddate() {
		return uploaddate;
	}

	public void setUploaddate(String uploaddate) {
		this.uploaddate = uploaddate;
	}

	@Override
	public String toString() {
		return "Product [productnum=" + productnum + ", title=" + title + ", genre=" + genre + ", themalevel="
				+ themalevel + ", recommendpeople=" + recommendpeople + ", usetime=" + usetime + ", area=" + area
				+ ", price=" + price + ", image01=" + image01 + ", image02=" + image02 + ", comments=" + comments
				+ ", address=" + address + ", uploaddate=" + uploaddate + ", time01=" + time01 + ", time02=" + time02
				+ ", time03=" + time03 + ", time04=" + time04 + ", time05=" + time05 + "]";
	}

	

}
