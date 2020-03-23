package bitcamp.java142.ch5.kyjjf.test;
import java.util.ArrayList;
import java.util.Scanner;

import bitcamp.java142.ch5.kyjjf.dao.KyjMemberDAO;
import bitcamp.java142.ch5.kyjjf.dao.KyjMemberDAOImpl;
import bitcamp.java142.ch5.kyjjf.vo.KyjMemberVO;

public class TestMemberMain {
	public ArrayList<KyjMemberVO> searchKyjMember(String kname){
		System.out.println("(log) KyjMemberScr.searchKyjMember �Լ� ���� >>> ");
		KyjMemberDAO kdao = new KyjMemberDAOImpl();
		KyjMemberVO kvo = null;
		kvo = new KyjMemberVO();
		kvo.setKname(kname);
		ArrayList<KyjMemberVO> aList = kdao.searchKyjMember(kvo);
		System.out.println("(log) KyjMemberScr.searchKyjMember �Լ� �� >>> ");
		return aList;
	}
	
	public boolean insertKyjMember(String k_id,
				   String k_pw,
				   String k_name,
				   String k_hp,
				   String k_birth,
				   String k_mail,
				   String k_postno,
				   String k_juso){
	System.out.println("(log) KyjMemberScr.insertKyjMember �Լ� ���� >>> ");
	System.out.println("(log) �Է��� ���� ��� >>> : " +k_id+" "+k_pw+" "
									  +k_name+" "+k_hp+" "
									  +k_birth+" "+k_mail+" "
									  +k_postno+" "+k_juso);
	KyjMemberDAO kdao = new KyjMemberDAOImpl();
	KyjMemberVO kvo = null;
	kvo = new KyjMemberVO();
	kvo.setKid(k_id);
	kvo.setKpw(k_pw);
	kvo.setKname(k_name);
	kvo.setKhp(k_hp);
	kvo.setKbirth(k_birth);
	kvo.setKemail(k_mail);
	kvo.setKpostno(k_postno);
	kvo.setKjuso(k_juso);
	System.out.println("(log) �Է��� ���� ��� >>> : " +k_id+" "+k_pw+" "
									  +k_name+" "+k_hp+" "
									  +k_birth+" "+k_mail+" "
									  +k_postno+" "+k_juso);
	boolean bFlag = kdao.insertKyjMember(kvo);
	System.out.println("(log) KyjMemberScr.insertKyjMember �Լ� �� >>> ");
	return bFlag;
	}
	public static void displayFun(ArrayList<KyjMemberVO> aList){
		for(int i=0; i<aList.size(); i++){
			KyjMemberVO kvo = aList.get(i);
			System.out.println("ȸ����ȣ : " + kvo.getKnumm());
			System.out.println("���̵� : " + kvo.getKid());
			System.out.println("��й�ȣ : " + kvo.getKpw());
			System.out.println("�̸� : " + kvo.getKname());
			System.out.println("�޴���ȭ��ȣ : " + kvo.getKhp());
			System.out.println("������� : " + kvo.getKbirth());
			System.out.println("�̸��� : " + kvo.getKemail());
			System.out.println("�����ȣ : " + kvo.getKpostno());
			System.out.println("���θ��ּ� : " + kvo.getKjuso());
			System.out.println("�������� : " + kvo.getKdeleteyn());
			System.out.println("�Է��� : " + kvo.getKinsertdate());
			System.out.println("������ : " + kvo.getKupdatedate());
			System.out.println("����������������������������������������������������������");
		}
	}
	public ArrayList<KyjMemberVO> selectKyjMember(){
		System.out.println("(log) KyjMemberScr.selectKyjMember �Լ� ���� >>> ");
		KyjMemberDAO kdao = new KyjMemberDAOImpl();
		ArrayList<KyjMemberVO> aList = kdao.selectKyjMember();
		System.out.println("(log) KyjMemberScr.selectKyjMember �Լ� �� >>> ");
		return aList;
	}
	public static void main(String[] args) {
		System.out.println("(log) KytMemberScr.main() Start >>> ");
		boolean aFlag = true;
		while(aFlag){
			
		String k_id,k_pw,k_name,k_hp,k_birth,k_mail,k_postno,k_juso,kemail,kname = "";
		System.out.println("��������������������MAIN��������������������");
		System.out.println(" (I)ȸ������ \n (U)�̸��ϼ��� \n (D)ȸ������ \n (SS)��üȸ������ \n (SR)ȸ���˻� \n (LS)Ű����'����'�˻�\n (E)���α׷�����");
		System.out.println("������������������������������������������������");
		System.out.println(" ��ȣ �� ���ڸ� �Է��� ���α׷� ���� >>> : ");
		Scanner sc = new Scanner(System.in);
		String st = sc.nextLine();

		// insert
		if("I".equals(st)){
			
			System.out.println("(log) main().insert�� ���� >>>");
			System.out.println("��ȸ�����ԡ� \n(�� ǥ�õ� �׸��� �ݵ�� �Է��ؾ� �մϴ�.) \n�ڻ���� ���̵� �Է�(����+����) \n>>>");
			k_id = sc.nextLine();
			System.out.println("���е���� �Է�(����+���� or ����) \n>>>");
			k_pw = sc.nextLine();
			System.out.println("���̸� �Է�(�ѱ�) \n>>>");
			k_name = sc.nextLine();
			System.out.println("�޴���ȭ��ȣ �Է�(-����) \n>>>");
			k_hp = sc.nextLine();
			System.out.println("������� �Է�(ex.19991212) \n>>>");
			k_birth = sc.nextLine();
			System.out.println("�̸����ּ� �Է� \n>>>");
			k_mail = sc.nextLine();
			System.out.println("�����ȣ �Է� \n>>>");
			k_postno = sc.nextLine();
			System.out.println("�ּ� �Է� \n>>>");
			k_juso = sc.nextLine();
	

//			String k_id = "yeonjungk";
//			String k_pw = "123456";
//			String k_name = "�迬��";
//			String k_hp = "01033333333";
//			String k_birth = "19921228";
//			String k_mail = "yeon_j_k@naver.com";
//			String k_postno = "44444";
//			String k_juso = "��⵵ ������";
			System.out.println("�Էµ� ����  >>> \n" + "���̵� : " + k_id+"\n��й�ȣ : "+k_pw+"\n�̸� : "+k_name+"\n�޴���ȭ��ȣ : "+k_hp+"\n������� : "+k_birth+"\n�̸��� : "+k_mail+"\n�����ȣ : "+k_postno+"\n���θ��ּ� : "+k_juso+"\n");
			
			TestMemberMain km = new TestMemberMain();
			boolean bFlag = km.insertKyjMember(k_id,k_pw,k_name,k_hp,k_birth,k_mail,k_postno,k_juso);
			if(bFlag){
				System.out.println("(log) bFlag ������ ����? >>> : " + bFlag);
				TestMemberMain km0 = new TestMemberMain();
				ArrayList<KyjMemberVO> aList = km0.selectKyjMember();
				int aListSize = aList.size();
				if(aListSize>0){
					TestMemberMain.displayFun(aList);
				}
			}else{
				System.out.println("(log) ������ �Է� ����");
			}
		System.out.println("(log) main().insert�� �� >>>");
		}
		
		
		else if("SS".equals(st)){
			System.out.println("(log) main().select�� ���� >>> ");
			TestMemberMain km = new TestMemberMain(); 
			ArrayList<KyjMemberVO> aList = km.selectKyjMember();
			System.out.println("(log) aList.size() >>> : " + aList.size());
			System.out.println("(log) main() aList >>> : " + aList);
			//���
			System.out.println("(log) ��� ��� >>> \n");
			int aListsize = aList.size();
			System.out.println("(log) aListsize ��µ� ������ ���� ���� >>> : " + aList.size());
			System.out.println("\n ��ü ȸ��("+aList.size()+"��)�� ���� �˻� ��� ���  >>> : ");
			System.out.println("����������������������������������������������������������");
			if(aListsize>0){
				TestMemberMain.displayFun(aList);
			}
			System.out.println("(log) main().select�� ��  >>> ");
		}
		// search
				else if("SR".equals(st)){
					System.out.println("(log) main().search�� ���� >>> ");
					System.out.println("�˻� �� ����� �̸� >>>");
					kname = sc.next();
					TestMemberMain km = new TestMemberMain();
					ArrayList<KyjMemberVO> aList = km.searchKyjMember(kname);
					int aListsize = aList.size();
					System.out.println("(log) aList.size() >>> : " + aList.size());
					System.out.println("\n�˻� �Ǽ�("+aList.size()+"��) ����ڸ� '" + kname + "'�� ���� �˻� ��� ���  >>> : ");
					System.out.println("����������������������������������������������������������");
					if(aListsize>0){
						TestMemberMain.displayFun(aList);
					}else{
						System.out.println("�˻� ����");
					}
					System.out.println("(log) main().search�� �� >>> ");
				}
	} // main
}}
