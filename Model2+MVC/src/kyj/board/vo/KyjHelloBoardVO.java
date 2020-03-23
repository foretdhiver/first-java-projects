package kyj.board.vo;

public class KyjHelloBoardVO {
	private String kno;
	private String ksubject;
	private String kname;
	private String kpw;
	private String kmemo;
	private String kdeleteyn;
	private String kinsertdate;
	private String kupdatedate;
	private String kimage;
	
	public KyjHelloBoardVO(){}
	
	public KyjHelloBoardVO(String kno, String ksubject, String kname, String kpw, String kmemo, String kdeleteyn,
			String kinsertdate, String kupdatedate, String kimage) {
		super();
		this.kno = kno;
		this.ksubject = ksubject;
		this.kname = kname;
		this.kpw = kpw;
		this.kmemo = kmemo;
		this.kdeleteyn = kdeleteyn;
		this.kinsertdate = kinsertdate;
		this.kupdatedate = kupdatedate;
		this.kimage = kimage;
	}

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
} // end of KyjHelloBoardVO
