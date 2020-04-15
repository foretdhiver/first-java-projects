package com.spring.ge.vo;

/**
 *  작성일자 : 2020-02-13 (목)
 *  작성자 : 한은민
 *  수정일자 : 2020-02-14 (금)
 *  수정내용 : 2020-02-14 (금) - 파일 업로드 위해 sign 변수 추가
 *  클래스 역활 : EM_INFO 테이블의 VO 클래스
 * 
 * @author bitcamp
 */
public class EmInfoVO extends CommonVO  {

	private String emno;			// 사번
	private String emname;			// 이름
	private String emid;			// 아이디
	private String empw;			// 비밀번호
	private String emhiredate;		// 입사일
	private String deptcd;			// 부서코드
	private String jobcd;			// 직위코드
	private String ememail;			// 이메일
	private String emhp;			// 휴대폰번호
	private String emdino;			// 직통번호
	private String emexno;			// 내선번호
	private String eminsertdate;	// 등록일
	private String emupdatedate;	// 수정일
	private String empic;			// 사진
	private String emsign;			// 서명
	
	public String getEmno() {
		return emno;
	}
	public void setEmno(String emno) {
		this.emno = emno;
	}
	public String getEmname() {
		return emname;
	}
	public void setEmname(String emname) {
		this.emname = emname;
	}
	public String getEmid() {
		return emid;
	}
	public void setEmid(String emid) {
		this.emid = emid;
	}
	public String getEmpw() {
		return empw;
	}
	public void setEmpw(String empw) {
		this.empw = empw;
	}
	public String getEmhiredate() {
		return emhiredate;
	}
	public void setEmhiredate(String emhiredate) {
		this.emhiredate = emhiredate;
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
	public String getEmemail() {
		return ememail;
	}
	public void setEmemail(String ememail) {
		this.ememail = ememail;
	}
	public String getEmhp() {
		return emhp;
	}
	public void setEmhp(String emhp) {
		this.emhp = emhp;
	}
	public String getEmdino() {
		return emdino;
	}
	public void setEmdino(String emdino) {
		this.emdino = emdino;
	}
	public String getEmexno() {
		return emexno;
	}
	public void setEmexno(String emexno) {
		this.emexno = emexno;
	}
	public String getEminsertdate() {
		return eminsertdate;
	}
	public void setEminsertdate(String eminsertdate) {
		this.eminsertdate = eminsertdate;
	}
	public String getEmupdatedate() {
		return emupdatedate;
	}
	public void setEmupdatedate(String emupdatedate) {
		this.emupdatedate = emupdatedate;
	}
	public String getEmpic() {
		return empic;
	}
	public void setEmpic(String empic) {
		this.empic = empic;
	}
	public String getEmsign() {
		return emsign;
	}
	public void setEmsign(String emsign) {
		this.emsign = emsign;
	}
	
}	