package com.escape.model;

import com.escape.controller.SuperClass;

public class JBoard extends SuperClass{
	
	private int jno ;
	private String jwriter ;
	private String jsubject ;
	private String jcategory;
	private String jcontent ;
	private String jregdate ;
	private int jreadhit ;
	private String jremark;
	private int jgroupno ; // 게시글 그룹 번호 
	private int jorderno ; // 게시할 순번
	
	public JBoard() {
	}

	public int getJno() {
		return jno;
	}

	public void setJno(int jno) {
		this.jno = jno;
	}

	public String getJwriter() {
		return jwriter;
	}

	public void setJwriter(String jwriter) {
		this.jwriter = jwriter;
	}

	public String getJsubject() {
		return jsubject;
	}

	public void setJsubject(String jsubject) {
		this.jsubject = jsubject;
	}

	public String getJcategory() {
		return jcategory;
	}

	public void setJcategory(String jcategory) {
		this.jcategory = jcategory;
	}

	public String getJcontent() {
		return jcontent;
	}

	public void setJcontent(String jcontent) {
		this.jcontent = jcontent;
	}

	public String getJregdate() {
		return jregdate;
	}

	public void setJregdate(String jregdate) {
		this.jregdate = jregdate;
	}

	public int getJreadhit() {
		return jreadhit;
	}

	public void setJreadhit(int jreadhit) {
		this.jreadhit = jreadhit;
	}

	public String getJremark() {
		return jremark;
	}

	public void setJremark(String jremark) {
		this.jremark = jremark;
	}

	public int getJgroupno() {
		return jgroupno;
	}

	public void setJgroupno(int jgroupno) {
		this.jgroupno = jgroupno;
	}

	public int getJorderno() {
		return jorderno;
	}

	public void setJorderno(int jorderno) {
		this.jorderno = jorderno;
	}

	@Override
	public String toString() {
		return "JBoard [jno=" + jno + ", jwriter=" + jwriter + ", jsubject=" + jsubject + ", jcategory=" + jcategory
				+ ", jcontent=" + jcontent + ", jregdate=" + jregdate + ", jreadhit=" + jreadhit + ", jremark="
				+ jremark + ", jgroupno=" + jgroupno + ", jorderno=" + jorderno + "]";
	}	
	
	
	
}
