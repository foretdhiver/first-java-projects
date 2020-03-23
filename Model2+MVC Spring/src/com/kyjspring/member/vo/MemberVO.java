package com.kyjspring.member.vo;

public class MemberVO {
	private String kmem;
	private String kid;
	private String kpw;
	private String kname;
	private String khp;
	private String kbirth;
	private String kemail;
	private String kpostno;
	private String kjuso;
	private String kjuso1;
	private String kimage;
	private String kdeleteyn;
	private String kinsertdate;
	private String kupdatedate;
	private String ksession;
	
//	public MemberVO(){
//		
//	}
//	
//	public MemberVO(String Kmem, String kid, String kpw, String kname, String khp, String kbirth, String kemail,
//			String kpostno, String kjuso, String kjuso1, String kdeleteyn, String kinsertdate, String kupdatedate, String kimage) {
//		this.kmem = Kmem;
//		this.kid = kid;
//		this.kpw = kpw;
//		this.kname = kname;
//		this.khp = khp;
//		this.kbirth = kbirth;
//		this.kemail = kemail;
//		this.kpostno = kpostno;
//		this.kjuso = kjuso;
//		this.kjuso1 = kjuso1;
//		this.kdeleteyn = kdeleteyn;
//		this.kinsertdate = kinsertdate;
//		this.kupdatedate = kupdatedate;
//		this.kimage = kimage;
//	}
	
	public String getKmem() {
		return kmem;
	}
	public void setKmem(String kmem) {
		this.kmem = kmem;
	}
	public String getKid() {
		return kid;
	}
	public void setKid(String kid) {
		this.kid = kid;
	}
	public String getKpw() {
		return kpw;
	}
	public void setKpw(String kpw) {
		this.kpw = kpw;
	}
	public String getKname() {
		return kname;
	}
	public void setKname(String kname) {
		this.kname = kname;
	}
	public String getKhp() {
		return khp;
	}
	public void setKhp(String khp) {
		this.khp = khp;
	}
	public String getKbirth() {
		return kbirth;
	}
	public void setKbirth(String kbirth) {
		this.kbirth = kbirth;
	}
	public String getKemail() {
		return kemail;
	}
	public void setKemail(String kemail) {
		this.kemail = kemail;
	}
	public String getKpostno() {
		return kpostno;
	}
	public void setKpostno(String kpostno) {
		this.kpostno = kpostno;
	}
	public String getKjuso() {
		return kjuso;
	}
	public void setKjuso(String kjuso) {
		this.kjuso = kjuso;
	}
	public String getKjuso1() {
		return kjuso1;
	}
	public void setKjuso1(String kjuso1) {
		this.kjuso1 = kjuso1;
	}
	public String getKimage() {
		return kimage;
	}
	public void setKimage(String kimage) {
		this.kimage = kimage;
	}
	public String getKdeleteyn() {
		return kdeleteyn;
	}
	public void setKdeleteyn(String kdeleteyn) {
		this.kdeleteyn = kdeleteyn;
	}
	public String getKinsertdate() {
		return kinsertdate;
	}
	public void setKinsertdate(String kinsertdate) {
		this.kinsertdate = kinsertdate;
	}
	public String getKupdatedate() {
		return kupdatedate;
	}
	public void setKupdatedate(String kupdatedate) {
		this.kupdatedate = kupdatedate;
	}
	
	public String getKsession() {
		return ksession;
	}
	public void setKsession(String ksession) {
		this.ksession = ksession;
	}
	public static void memberPrint(MemberVO param){
		System.out.println("param.getKmem() : " + param.getKmem());
		System.out.println("param.getKid() : " + param.getKid());
		System.out.println("param.getKpw() : " + param.getKpw());
		System.out.println("param.getKhp() : " + param.getKhp());
		System.out.println("param.getKbirth() : " + param.getKbirth());
		System.out.println("param.getKemail() : " + param.getKemail());
		System.out.println("param.getKpostno() : " + param.getKpostno());
		System.out.println("param.getKjuso() : " + param.getKjuso());
		System.out.println("param.getKjuso1() : " + param.getKjuso1());
		System.out.println("param.getKimage() : " + param.getKimage());
		System.out.println("param.getKdeleteyn() : " + param.getKdeleteyn());
		System.out.println("param.getKinsertdate() : " + param.getKinsertdate());
		System.out.println("param.getKupdatedate() : " + param.getKupdatedate());
	}
} // end of MemberVO class
