package com.spring.ge.vo;

import org.springframework.web.multipart.MultipartFile;



public class AnBoardVO extends CommonVO{
/*CREATE TABLE ANONYMOUS_BOARD(
    ABNO            VARCHAR2(13)    PRIMARY KEY
    ,ABSUBJECT      VARCHAR2(100) 
    ,ABPW           VARCHAR2(20)    NOT NULL
    ,ABCONTENT      VARCHAR2(3000)
    ,ABINSERTDATE   DATE
    ,ABUPDATEDATE   DATE
    ,ABDELETEYN     VARCHAR2(1)
    ,ABCVCNT        VARCHAR2(1000)
	,ABFILE         VARCHAR2(800)
);*/
	//vo
	private String abno;
	private String absubject;
	private String abpw;
	private String abcontent;
	private String abinsertdate;
	private String abupdatedate;
	private String deleteyn;
	private String abcvcnt;
	private String abfile;
	private MultipartFile abfilefile;
	
	
	public String getAbno() {
		return abno;
	}
	public void setAbno(String abno) {
		this.abno = abno;
	}
	public String getAbsubject() {
		return absubject;
	}
	public void setAbsubject(String absubject) {
		this.absubject = absubject;
	}
	public String getAbpw() {
		return abpw;
	}
	public void setAbpw(String abpw) {
		this.abpw = abpw;
	}
	public String getAbcontent() {
		return abcontent;
	}
	public void setAbcontent(String abcontent) {
		this.abcontent = abcontent;
	}
	public String getAbinsertdate() {
		return abinsertdate;
	}
	public void setAbinsertdate(String abinsertdate) {
		this.abinsertdate = abinsertdate;
	}
	public String getAbupdatedate() {
		return abupdatedate;
	}
	public void setAbupdatedate(String abupdatedate) {
		this.abupdatedate = abupdatedate;
	}
	public String getDeleteyn() {
		return deleteyn;
	}
	public void setDeleteyn(String deleteyn) {
		this.deleteyn = deleteyn;
	}
	public String getAbcvcnt() {
		return abcvcnt;
	}
	public void setAbcvcnt(String abcvcnt) {
		this.abcvcnt = abcvcnt;
	}
	
	public MultipartFile getAbfilefile() {
		return abfilefile;
	}
	public void setAbfilefile(MultipartFile abfilefile) {
		this.abfilefile = abfilefile;
	}
	public String getAbfile() {
		return abfile;
	}
	public void setAbfile(String abfile) {
		this.abfile = abfile;
	}
	
	
	
	
	
}//class
