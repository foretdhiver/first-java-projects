package com.spring.ge.vo;

public class PjCalendarVO {
	/*CREATE TABLE PROJECT_CALENDAR(
    PCNO            VARCHAR2(13)    PRIMARY KEY
    ,PCSUB          VARCHAR2(100)
    ,DEPTCD         VARCHAR2(2)
    ,EMNAME         VARCHAR2(20)
    ,PCMEMO         VARCHAR2(200)
    ,PCSDATE        DATE
    ,PCEDATE        DATE
    ,PCSTIME        DATE
    ,PCETIME        DATE
    ,PCINSERTDATE   DATE
    ,PCUPDATEDATE   DATE
    ,PCDELETEYN     VARCHAR2(1)
    ,CONSTRAINT PROJECT_CALENDAR_FK FOREIGN KEY(DEPTCD) REFERENCES EM_DEPARTMENT(DEPTCD)


	);*/
	
	private String pcno;
	private String pcsub;
	private String deptcd;
	private String deptname;
	private String jobcd;
	private String jobname;
	private String emname;
	private String pcmemo;
	private String pcsdate;
	private String pcedate;
	private String pcinsertdate;
	private String pcupdatedate;
	private String deleteyn;
	
	
	public String getPcno() {
		return pcno;
	}
	public void setPcno(String pcno) {
		this.pcno = pcno;
	}
	public String getPcsub() {
		return pcsub;
	}
	public void setPcsub(String pcsub) {
		this.pcsub = pcsub;
	}
	public String getDeptcd() {
		return deptcd;
	}
	public void setDeptcd(String deptcd) {
		this.deptcd = deptcd;
	}
	public String getJobcd() {
		return jobcd;
	}
	public void setJobcd(String jobcd) {
		this.jobcd = jobcd;
	}
	public String getEmname() {
		return emname;
	}
	public void setEmname(String emname) {
		this.emname = emname;
	}
	public String getPcmemo() {
		return pcmemo;
	}
	public void setPcmemo(String pcmemo) {
		this.pcmemo = pcmemo;
	}
	public String getPcsdate() {
		return pcsdate;
	}
	public void setPcsdate(String pcsdate) {
		this.pcsdate = pcsdate;
	}
	public String getPcedate() {
		return pcedate;
	}
	public void setPcedate(String pcedate) {
		this.pcedate = pcedate;
	}
	public String getPcinsertdate() {
		return pcinsertdate;
	}
	public void setPcinsertdate(String pcinsertdate) {
		this.pcinsertdate = pcinsertdate;
	}
	public String getPcupdatedate() {
		return pcupdatedate;
	}
	public void setPcupdatedate(String pcupdatedate) {
		this.pcupdatedate = pcupdatedate;
	}
	public String getDeleteyn() {
		return deleteyn;
	}
	public void setDeleteyn(String deleteyn) {
		this.deleteyn = deleteyn;
	}
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	public String getJobname() {
		return jobname;
	}
	public void setJobname(String jobname) {
		this.jobname = jobname;
	}
	
	
	
	
	
	
	
}//class
