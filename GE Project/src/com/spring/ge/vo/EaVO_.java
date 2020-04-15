package com.spring.ge.vo;

public class EaVO_ extends CommonVO{
	   /* 여기부터 결재라인 테이블 컬럼 */
	   private String ea_lineno;       	//EA_LINENO       	라인번호
	   private String ea_linename;    	//EA_LINENAME       라인명
	   /* 여기부터 결재스탭 테이블 컬럼 */
	   private String ea_stepno;        //EA_STEPNO      	스텝번호
	   private String ea_empno;      	//EA_EMPNO          기안자 사번
	   private String ea_empno1;      	//EA_EMPNO1        	첫번째결재자 사번
	   private String ea_empno2;      	//EA_EMPNO2                        두번째결재자 사번
	   /* 여기부터 결재로그 테이블 컬럼 */
	   private String ea_logno;      	//EA_LOGNO         	로그번호
	   private String ea_status;      	//EA_STATUS         결재상태
	   private String ea_finaldate;   	//EA_FINALDATE      전자결재최종수정일
	   private String ea_sign;         	//EA_SIGN         	기안자 싸인
	   private String ea_sign1;      	//EA_SIGN1         	첫번째결재자 싸인
	   private String ea_sign2;      	//EA_SIGN2         	두번째결재자 싸인
	   private String ea_nextemno;
	   /* 여기부터 결재문서양식 테이블 컬럼 */
	   private String ea_doccd;      	//EA_COCCD         	문서양식코드
	   /* 여기부터 전자결재테이블 컬럼 */
	   private String ea_no;         	//EA_NO            	전자결재번호
	   private String ea_subject;		//EASUBJECT			전자결재문서이름
	   private String ea_memo;			//EA_MEMO			전자결재문서설명
	   private String ea_insertdate;   	//EA_INSERTDATE     품위일
	   private String eacno;         	//EACNO            	근태코드
	   private String eadraftcontant;   //EADRAFTCONTANT   	기안내용
	   private String emno;
	   
	   private String rnum;
	   
	   // 여기서부터 연정 씨 컬럼
		private String ea_file;			//EA_FILE			기안자 파일
		private String ea_file1;		//EA_FILE1			첫번재결재자 파일
		private String ea_file2;		//EA_FILE2			두번째결재자 파일
		private String ea_fileform;		//EA_FILE			문서양식파일	   
		private String ea_fileupload;	//EA_FILEUPLOAD		전자결재문서파일업로드
	   
	   public String getEa_lineno() {
	      return ea_lineno;
	   }
	   public void setEa_lineno(String ea_lineno) {
	      this.ea_lineno = ea_lineno;
	   }
	   public String getEa_linename() {
	      return ea_linename;
	   }
	   public void setEa_linename(String ea_linename) {
	      this.ea_linename = ea_linename;
	   }
	   public String getEa_stepno() {
	      return ea_stepno;
	   }
	   public void setEa_stepno(String ea_stepno) {
	      this.ea_stepno = ea_stepno;
	   }
	   public String getEa_empno() {
	      return ea_empno;
	   }
	   public void setEa_empno(String ea_empno) {
	      this.ea_empno = ea_empno;
	   }
	   public String getEa_empno1() {
	      return ea_empno1;
	   }
	   public void setEa_empno1(String ea_empno1) {
	      this.ea_empno1 = ea_empno1;
	   }
	   public String getEa_empno2() {
	      return ea_empno2;
	   }
	   public void setEa_empno2(String ea_empno2) {
	      this.ea_empno2 = ea_empno2;
	   }
	   public String getEa_logno() {
	      return ea_logno;
	   }
	   public void setEa_logno(String ea_logno) {
	      this.ea_logno = ea_logno;
	   }
	   public String getEa_status() {
	      return ea_status;
	   }
	   public void setEa_status(String ea_status) {
	      this.ea_status = ea_status;
	   }
	   public String getEa_finaldate() {
	      return ea_finaldate;
	   }
	   public void setEa_finaldate(String ea_finaldate) {
	      this.ea_finaldate = ea_finaldate;
	   }
	   public String getEa_sign() {
	      return ea_sign;
	   }
	   public void setEa_sign(String ea_sign) {
	      this.ea_sign = ea_sign;
	   }
	   public String getEa_sign1() {
	      return ea_sign1;
	   }
	   public void setEa_sign1(String ea_sign1) {
	      this.ea_sign1 = ea_sign1;
	   }
	   public String getEa_sign2() {
	      return ea_sign2;
	   }
	   public void setEa_sign2(String ea_sign2) {
	      this.ea_sign2 = ea_sign2;
	   }
	   
	   public String getEa_nextemno() {
		return ea_nextemno;
	}
	public void setEa_nextemno(String ea_nextemno) {
		this.ea_nextemno = ea_nextemno;
	}
	public String getEa_doccd() {
	      return ea_doccd;
	   }
	   public void setEa_doccd(String ea_doccd) {
	      this.ea_doccd = ea_doccd;
	   }
	   public String getEa_no() {
	      return ea_no;
	   }
	   public void setEa_no(String ea_no) {
	      this.ea_no = ea_no;
	   }
	   public String getEa_insertdate() {
	      return ea_insertdate;
	   }
	   public void setEa_insertdate(String ea_insertdate) {
	      this.ea_insertdate = ea_insertdate;
	   }
	   public String getEacno() {
	      return eacno;
	   }
	   public void setEacno(String eacno) {
	      this.eacno = eacno;
	   }
	   public String getEa_subject() {
		return ea_subject;
	   }
	   public void setEa_subject(String ea_subject) {
		  this.ea_subject = ea_subject;
	   }
	   
	   public String getEa_memo() {
		return ea_memo;
	   }
	   public void setEa_memo(String ea_memo) {
	   	   this.ea_memo = ea_memo;
	   }
	   public String getEadraftcontant() {
		   return eadraftcontant;
	   }
	   public void setEadraftcontant(String eadraftcontant) {
	      this.eadraftcontant = eadraftcontant;
	   }
	public String getEmno() {
		return emno;
	}
	public void setEmno(String emno) {
		this.emno = emno;
	}
	public String getRnum() {
		return rnum;
	}
	public void setRnum(String rnum) {
		this.rnum = rnum;
	}
	public String getEa_file() {
		return ea_file;
	}
	public void setEa_file(String ea_file) {
		this.ea_file = ea_file;
	}
	public String getEa_file1() {
		return ea_file1;
	}
	public void setEa_file1(String ea_file1) {
		this.ea_file1 = ea_file1;
	}
	public String getEa_file2() {
		return ea_file2;
	}
	public void setEa_file2(String ea_file2) {
		this.ea_file2 = ea_file2;
	}
	public String getEa_fileform() {
		return ea_fileform;
	}
	public void setEa_fileform(String ea_fileform) {
		this.ea_fileform = ea_fileform;
	}
	public String getEa_fileupload() {
		return ea_fileupload;
	}
	public void setEa_fileupload(String ea_fileupload) {
		this.ea_fileupload = ea_fileupload;
	}
	
	   
} // end of EaVO
