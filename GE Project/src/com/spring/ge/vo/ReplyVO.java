package com.spring.ge.vo;

public class ReplyVO {

//	R_NO VARCHAR2(100)  PRIMARY KEY
//    ,ABNO  VARCHAR2(100)    NOT NULL
//    ,R_CONTENT VARCHAR2(2000)
//    ,R_PW VARCHAR2(18)
//    ,R_DATE DATE
	
	
	private String r_no;
	private String abno;
	private String r_content;
	private String r_pw;
	private String r_date;
	
	//getter&setter
	public String getR_no() {
		return r_no;
	}
	public void setR_no(String r_no) {
		this.r_no = r_no;
	}
	public String getAbno() {
		return abno;
	}
	public void setAbno(String abno) {
		this.abno = abno;
	}
	public String getR_content() {
		return r_content;
	}
	public void setR_content(String r_content) {
		this.r_content = r_content;
	}
	public String getR_pw() {
		return r_pw;
	}
	public void setR_pw(String r_pw) {
		this.r_pw = r_pw;
	}
	public String getR_date() {
		return r_date;
	}
	public void setR_date(String r_date) {
		this.r_date = r_date;
	}
	
	
	
	
}//class
