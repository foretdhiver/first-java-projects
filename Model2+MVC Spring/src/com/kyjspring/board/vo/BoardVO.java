package com.kyjspring.board.vo;

public class BoardVO {
	private String kno;
	private String ksubject;
	private String kname;
	private String kpw;
	private String kmemo;
	private String kdeleteyn;
	private String kinsertdate;
	private String kupdatedate;
	private String kimage;
	
	public String getKno() {
		return kno;
	}
	public void setKno(String kno) {
		this.kno = kno;
	}
	public String getKsubject() {
		return ksubject;
	}
	public void setKsubject(String ksubject) {
		this.ksubject = ksubject;
	}
	public String getKname() {
		return kname;
	}
	public void setKname(String kname) {
		this.kname = kname;
	}
	public String getKpw() {
		return kpw;
	}
	public void setKpw(String kpw) {
		this.kpw = kpw;
	}
	public String getKmemo() {
		return kmemo;
	}
	public void setKmemo(String kmemo) {
		this.kmemo = kmemo;
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
	public String getKimage() {
		return kimage;
	}
	public void setKimage(String kimage) {
		this.kimage = kimage;
	}
	
	public static void boardPrint(BoardVO param){
		System.out.println("param.getKno() : " + param.getKno());
		System.out.println("param.getKsubject() : " + param.getKsubject());
		System.out.println("param.getKid() : " + param.getKname());
		System.out.println("param.getKpw() : " + param.getKpw());
		System.out.println("param.getKimage() : " + param.getKimage());
		System.out.println("param.getKdeleteyn() : " + param.getKdeleteyn());
		System.out.println("param.getKinsertdate() : " + param.getKinsertdate());
		System.out.println("param.getKupdatedate() : " + param.getKupdatedate());
	}
}
