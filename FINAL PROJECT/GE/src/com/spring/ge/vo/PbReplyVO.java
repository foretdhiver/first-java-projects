package com.spring.ge.vo;

public class PbReplyVO {

//	CREATE TABLE PROJECT_BOARD_REPLY(
//		    PBR_NO VARCHAR2(100)  PRIMARY KEY
//		    ,PBNO  VARCHAR2(100)    NOT NULL
//		    ,PBR_CONTENT VARCHAR2(2000)
//		    ,EMNAME VARCHAR2(18)
//		    ,PBR_DATE DATE
//		    ,CONSTRAINT PROJECT_REPLY_FK FOREIGN KEY(PBNO) REFERENCES PROJECT_BOARD(PBNO)
//
//		);
	
	private String pbr_no;
	private String pbno;
	private String pbr_content;
	private String pbr_date;
	
	
	public String getPbr_no() {
		return pbr_no;
	}
	public void setPbr_no(String pbr_no) {
		this.pbr_no = pbr_no;
	}
	public String getPbno() {
		return pbno;
	}
	public void setPbno(String pbno) {
		this.pbno = pbno;
	}
	public String getPbr_content() {
		return pbr_content;
	}
	public void setPbr_content(String pbr_content) {
		this.pbr_content = pbr_content;
	}
	public String getPbr_date() {
		return pbr_date;
	}
	public void setPbr_date(String pbr_date) {
		this.pbr_date = pbr_date;
	}
	
	
	
	
	
	
}//class
