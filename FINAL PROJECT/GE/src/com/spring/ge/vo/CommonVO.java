package com.spring.ge.vo;

public class CommonVO {
	private String cdno;      	// �ڵ����
	private String cdkname;     // �ѱ۸�
	private String cdename;     // ������
	private String cd;         	// �ڵ�
	private String cdnote;      // ���
	
	private String jobcd;
	private String jobcd1;
	private String jobcd2;
	private String jobname;
	private String jobname1;
	private String jobname2;
	private String deptcd;
	private String deptcd1;
	private String deptcd2;
	private String deptname;
	private String deptname1;
	private String deptname2;
	private String emname;			//EMNAME
	private String emname1;
	private String emname2;
		
	private String comcd;		// ��� ����
	private String comname;		// ��� �����̸�
	private String calcategorydept;		// ���κμ�
	private String calcategorycompany;	// ȸ��
	private String calcategorypersonal;	// ����
	
	
	//����¡
	private int pageSize;
	private int curPage;
	private int pageNo;
	private int totalCount;
	private int groupSize;
	
	private String page_;
	private String pagesize_;
	private String start_row_;
	private String end_row_;
	private String listsize_;
	private String pageno_;
	private String totalcount_;
	
	//���� �˻��� ����� �Ӽ�
	private String search;
	private String keyword;
	private String ctstdate;	// ���� ���� ��ȸ ��
	private String cteddate;	// ���� ���� ��ȸ ��
	private String datesearch;
	private String st_date;
	private String ed_date;  // ���ó�¥
	private String searchall;
	
	// �μ� ���̺� �÷� (������ �� ���µ� ���ڰ��� �־ �ּ�ó����~~)
//	private String deptcd;		// �μ��ڵ�
//	private String deptname;	// �μ��̸�
	
	// ���� ���̺� �÷� (������ �� ���µ� ���ڰ��� �־ �ּ�ó����~~)
//	private String jobcd;		// �����ڵ�
//	private String jobname;		// �����̸�
	
	// ���� ���̺� �÷�
		private String logno;		// �α׹�ȣ(�����丮 ���̺�)
		private String ctno;		// ����
		private String ctinsertdate;	// ���� �����
		private String ctupdatedate;	// ���� ������
//		private String emname;		// �̸�
//		private String deptcd;		// �μ��ڵ� (�̹� ����)
//		private String jobcd;		// �����ڵ� (�̹� ����)
//		private String emno;		// ���
		private String ctincd;		// ��ٱ����ڵ�
		private String ctoutcd;		// ��ٱ����ڵ�
		private String ctintime;	// ��ٽð�
		private String ctouttime;	// ��ٽð�
		private String ctnote;		// ���
		public String getCdno() {
			return cdno;
		}
		public void setCdno(String cdno) {
			this.cdno = cdno;
		}
		public String getCdkname() {
			return cdkname;
		}
		public void setCdkname(String cdkname) {
			this.cdkname = cdkname;
		}
		public String getCdename() {
			return cdename;
		}
		public void setCdename(String cdename) {
			this.cdename = cdename;
		}
		public String getCd() {
			return cd;
		}
		public void setCd(String cd) {
			this.cd = cd;
		}
		public String getCdnote() {
			return cdnote;
		}
		public void setCdnote(String cdnote) {
			this.cdnote = cdnote;
		}
		public String getJobcd() {
			return jobcd;
		}
		public void setJobcd(String jobcd) {
			this.jobcd = jobcd;
		}
		public String getJobcd1() {
			return jobcd1;
		}
		public void setJobcd1(String jobcd1) {
			this.jobcd1 = jobcd1;
		}
		public String getJobcd2() {
			return jobcd2;
		}
		public void setJobcd2(String jobcd2) {
			this.jobcd2 = jobcd2;
		}
		public String getJobname() {
			return jobname;
		}
		public void setJobname(String jobname) {
			this.jobname = jobname;
		}
		public String getJobname1() {
			return jobname1;
		}
		public void setJobname1(String jobname1) {
			this.jobname1 = jobname1;
		}
		public String getJobname2() {
			return jobname2;
		}
		public void setJobname2(String jobname2) {
			this.jobname2 = jobname2;
		}
		public String getDeptcd() {
			return deptcd;
		}
		public void setDeptcd(String deptcd) {
			this.deptcd = deptcd;
		}
		public String getDeptcd1() {
			return deptcd1;
		}
		public void setDeptcd1(String deptcd1) {
			this.deptcd1 = deptcd1;
		}
		public String getDeptcd2() {
			return deptcd2;
		}
		public void setDeptcd2(String deptcd2) {
			this.deptcd2 = deptcd2;
		}
		public String getDeptname() {
			return deptname;
		}
		public void setDeptname(String deptname) {
			this.deptname = deptname;
		}
		public String getDeptname1() {
			return deptname1;
		}
		public void setDeptname1(String deptname1) {
			this.deptname1 = deptname1;
		}
		public String getDeptname2() {
			return deptname2;
		}
		public void setDeptname2(String deptname2) {
			this.deptname2 = deptname2;
		}
		public String getEmname() {
			return emname;
		}
		public void setEmname(String emname) {
			this.emname = emname;
		}
		public String getEmname1() {
			return emname1;
		}
		public void setEmname1(String emname1) {
			this.emname1 = emname1;
		}
		public String getEmname2() {
			return emname2;
		}
		public void setEmname2(String emname2) {
			this.emname2 = emname2;
		}
		public String getComcd() {
			return comcd;
		}
		public void setComcd(String comcd) {
			this.comcd = comcd;
		}
		public String getComname() {
			return comname;
		}
		public void setComname(String comname) {
			this.comname = comname;
		}
		public String getCalcategorydept() {
			return calcategorydept;
		}
		public void setCalcategorydept(String calcategorydept) {
			this.calcategorydept = calcategorydept;
		}
		public String getCalcategorycompany() {
			return calcategorycompany;
		}
		public void setCalcategorycompany(String calcategorycompany) {
			this.calcategorycompany = calcategorycompany;
		}
		public String getCalcategorypersonal() {
			return calcategorypersonal;
		}
		public void setCalcategorypersonal(String calcategorypersonal) {
			this.calcategorypersonal = calcategorypersonal;
		}
		public int getPageSize() {
			return pageSize;
		}
		public void setPageSize(int pageSize) {
			this.pageSize = pageSize;
		}
		public int getCurPage() {
			return curPage;
		}
		public void setCurPage(int curPage) {
			this.curPage = curPage;
		}
		public int getPageNo() {
			return pageNo;
		}
		public void setPageNo(int pageNo) {
			this.pageNo = pageNo;
		}
		public int getTotalCount() {
			return totalCount;
		}
		public void setTotalCount(int totalCount) {
			this.totalCount = totalCount;
		}
		public int getGroupSize() {
			return groupSize;
		}
		public void setGroupSize(int groupSize) {
			this.groupSize = groupSize;
		}
		public String getPage_() {
			return page_;
		}
		public void setPage_(String page_) {
			this.page_ = page_;
		}
		public String getPagesize_() {
			return pagesize_;
		}
		public void setPagesize_(String pagesize_) {
			this.pagesize_ = pagesize_;
		}
		public String getStart_row_() {
			return start_row_;
		}
		public void setStart_row_(String start_row_) {
			this.start_row_ = start_row_;
		}
		public String getEnd_row_() {
			return end_row_;
		}
		public void setEnd_row_(String end_row_) {
			this.end_row_ = end_row_;
		}
		public String getListsize_() {
			return listsize_;
		}
		public void setListsize_(String listsize_) {
			this.listsize_ = listsize_;
		}
		public String getPageno_() {
			return pageno_;
		}
		public void setPageno_(String pageno_) {
			this.pageno_ = pageno_;
		}
		public String getTotalcount_() {
			return totalcount_;
		}
		public void setTotalcount_(String totalcount_) {
			this.totalcount_ = totalcount_;
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
		public String getCtstdate() {
			return ctstdate;
		}
		public void setCtstdate(String ctstdate) {
			this.ctstdate = ctstdate;
		}
		public String getCteddate() {
			return cteddate;
		}
		public void setCteddate(String cteddate) {
			this.cteddate = cteddate;
		}
		public String getDatesearch() {
			return datesearch;
		}
		public void setDatesearch(String datesearch) {
			this.datesearch = datesearch;
		}
		public String getSt_date() {
			return st_date;
		}
		public void setSt_date(String st_date) {
			this.st_date = st_date;
		}
		public String getEd_date() {
			return ed_date;
		}
		public void setEd_date(String ed_date) {
			this.ed_date = ed_date;
		}
		public String getSearchall() {
			return searchall;
		}
		public void setSearchall(String searchall) {
			this.searchall = searchall;
		}
		public String getLogno() {
			return logno;
		}
		public void setLogno(String logno) {
			this.logno = logno;
		}
		public String getCtno() {
			return ctno;
		}
		public void setCtno(String ctno) {
			this.ctno = ctno;
		}
		public String getCtinsertdate() {
			return ctinsertdate;
		}
		public void setCtinsertdate(String ctinsertdate) {
			this.ctinsertdate = ctinsertdate;
		}
		public String getCtupdatedate() {
			return ctupdatedate;
		}
		public void setCtupdatedate(String ctupdatedate) {
			this.ctupdatedate = ctupdatedate;
		}
		public String getCtincd() {
			return ctincd;
		}
		public void setCtincd(String ctincd) {
			this.ctincd = ctincd;
		}
		public String getCtoutcd() {
			return ctoutcd;
		}
		public void setCtoutcd(String ctoutcd) {
			this.ctoutcd = ctoutcd;
		}
		public String getCtintime() {
			return ctintime;
		}
		public void setCtintime(String ctintime) {
			this.ctintime = ctintime;
		}
		public String getCtouttime() {
			return ctouttime;
		}
		public void setCtouttime(String ctouttime) {
			this.ctouttime = ctouttime;
		}
		public String getCtnote() {
			return ctnote;
		}
		public void setCtnote(String ctnote) {
			this.ctnote = ctnote;
		}
		
		
		
		
		
}
