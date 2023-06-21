package com.escape.model;

import com.escape.controller.SuperClass;

public class QBoard extends SuperClass{
	
	private int qno ;
	private String qwriter ;
	private String qcategory;
	private String qsubject ;
	private String qcontent ;
	private String qregdate ;
	private int qreadhit ;
	private String qremark;
	private int qgroupno ; // 게시글 그룹 번호 
	private int qorderno ; // 게시할 순번
	private int qdepth; //관리자가 답글 달아줄때
	
	public QBoard() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "QBoard [qno=" + qno + ", qwriter=" + qwriter + ", qcategory=" + qcategory + ", qsubject=" + qsubject
				+ ", qcontent=" + qcontent + ", qregdate=" + qregdate + ", qreadhit=" + qreadhit + ", qremark="
				+ qremark + ", qgroupno=" + qgroupno + ", qorderno=" + qorderno + ", qdepth=" + qdepth + "]";
	}

	public int getQno() {
		return qno;
	}

	public void setQno(int qno) {
		this.qno = qno;
	}

	public String getQwriter() {
		return qwriter;
	}

	public void setQwriter(String qwriter) {
		this.qwriter = qwriter;
	}

	public String getQcategory() {
		return qcategory;
	}

	public void setQcategory(String qcategory) {
		this.qcategory = qcategory;
	}

	public String getQsubject() {
		return qsubject;
	}

	public void setQsubject(String qsubject) {
		this.qsubject = qsubject;
	}

	public String getQcontent() {
		return qcontent;
	}

	public void setQcontent(String qcontent) {
		this.qcontent = qcontent;
	}

	public String getQregdate() {
		return qregdate;
	}

	public void setQregdate(String qregdate) {
		this.qregdate = qregdate;
	}

	public int getQreadhit() {
		return qreadhit;
	}

	public void setQreadhit(int qreadhit) {
		this.qreadhit = qreadhit;
	}

	public String getQremark() {
		return qremark;
	}

	public void setQremark(String qremark) {
		this.qremark = qremark;
	}

	public int getQgroupno() {
		return qgroupno;
	}

	public void setQgroupno(int qgroupno) {
		this.qgroupno = qgroupno;
	}

	public int getQorderno() {
		return qorderno;
	}

	public void setQorderno(int qorderno) {
		this.qorderno = qorderno;
	}

	public int getQdepth() {
		return qdepth;
	}

	public void setQdepth(int qdepth) {
		this.qdepth = qdepth;
	}
	
	
}
