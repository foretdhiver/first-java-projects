package com.spring.ge.vo;

public class ProjectBoardVO extends EmInfoVO {

/*CREATE TABLE PROJECT_BOARD(
    PBNO            VARCHAR2(13)    PRIMARY KEY     -- 글번호
    ,DEPTCD         VARCHAR2(2)                     -- 부서코드 FK
    ,PBSUBJECT      VARCHAR2(100)                   -- 게시글 제목
    ,EMNAME         VARCHAR2(20)                    -- 작성자
    ,JOBCD          VARCHAR2(2)                     -- 직위코드 FK
    ,PBPW           VARCHAR2(20)                    -- 게시글 비밀번호
    ,PBCONTENT      VARCHAR2(3000)                  -- 게시글내용
    ,PBINSERTDATE   DATE                            -- 작성일
    ,PBUPDATEDATE   DATE                            -- 수정일
    ,PBDELETEYN     VARCHAR2(1)                     -- 삭제여부
    ,PBFILEPATH     VARCHAR2(200)                   -- 첨부파일
    ,PBCVCNT        VARCHAR2(1000)                  -- 조회수
    ,CONSTRAINT PROJECT_BOARD_DEPTCD FOREIGN KEY(DEPTCD) REFERENCES EM_DEPARTMENT(DEPTCD)
    ,CONSTRAINT PROJECT_BOARD_JOBCD FOREIGN KEY(JOBCD) REFERENCES EM_JOB(JOBCD)

);*/	
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
	private String pbno;
	private String deptcd;
	private String pbsubject;
	private String emname;
	private String jobcd;
	private String pbpw;
	private String pbcontent;
	private String pbinsertdate;
	private String pbupdatedate;
	private String pbdeleteyn;
	private String pbfilepath;
	private String pbcvcnt;
	private String pjname;
	
	private String view_cnt; //조회수
	
	//페이징
	private String page;
	private String pagesize;
	private String start_row;
	private String end_row;
	private String listsize;
	private String pageno;
	private String totalcount;
	
	//검색
	private String search ="";
	private String keyword="";
	public String getPbno() {
		return pbno;
	}
	public void setPbno(String pbno) {
		this.pbno = pbno;
	}
	public String getDeptcd() {
		return deptcd;
	}
	public void setDeptcd(String deptcd) {
		this.deptcd = deptcd;
	}
	public String getPbsubject() {
		return pbsubject;
	}
	public void setPbsubject(String pbsubject) {
		this.pbsubject = pbsubject;
	}
	public String getEmname() {
		return emname;
	}
	public void setEmname(String emname) {
		this.emname = emname;
	}
	public String getJobcd() {
		return jobcd;
	}
	public void setJobcd(String jobcd) {
		this.jobcd = jobcd;
	}
	public String getPbpw() {
		return pbpw;
	}
	public void setPbpw(String pbpw) {
		this.pbpw = pbpw;
	}
	public String getPbcontent() {
		return pbcontent;
	}
	public void setPbcontent(String pbcontent) {
		this.pbcontent = pbcontent;
	}
	public String getPbinsertdate() {
		return pbinsertdate;
	}
	public void setPbinsertdate(String pbinsertdate) {
		this.pbinsertdate = pbinsertdate;
	}
	public String getPbupdatedate() {
		return pbupdatedate;
	}
	public void setPbupdatedate(String pbupdatedate) {
		this.pbupdatedate = pbupdatedate;
	}
	public String getPbdeleteyn() {
		return pbdeleteyn;
	}
	public void setPbdeleteyn(String pbdeleteyn) {
		this.pbdeleteyn = pbdeleteyn;
	}
	public String getPbfilepath() {
		return pbfilepath;
	}
	public void setPbfilepath(String pbfilepath) {
		this.pbfilepath = pbfilepath;
	}
	public String getPbcvcnt() {
		return pbcvcnt;
	}
	public void setPbcvcnt(String pbcvcnt) {
		this.pbcvcnt = pbcvcnt;
	}
	public String getPjname() {
		return pjname;
	}
	public void setPjname(String pjname) {
		this.pjname = pjname;
	}
	public String getView_cnt() {
		return view_cnt;
	}
	public void setView_cnt(String view_cnt) {
		this.view_cnt = view_cnt;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getPagesize() {
		return pagesize;
	}
	public void setPagesize(String pagesize) {
		this.pagesize = pagesize;
	}
	public String getStart_row() {
		return start_row;
	}
	public void setStart_row(String start_row) {
		this.start_row = start_row;
	}
	public String getEnd_row() {
		return end_row;
	}
	public void setEnd_row(String end_row) {
		this.end_row = end_row;
	}
	public String getListsize() {
		return listsize;
	}
	public void setListsize(String listsize) {
		this.listsize = listsize;
	}
	public String getPageno() {
		return pageno;
	}
	public void setPageno(String pageno) {
		this.pageno = pageno;
	}
	public String getTotalcount() {
		return totalcount;
	}
	public void setTotalcount(String totalcount) {
		this.totalcount = totalcount;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	
	
	
	
	
}
