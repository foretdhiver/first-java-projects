package test;

import java.util.ArrayList;
import java.util.Scanner;

import kyj.board.dao.KyjHelloBoardDAO;
import kyj.board.dao.KyjHelloBoardDAOImpl;
import kyj.board.vo.KyjHelloBoardVO;


public class TestMain {
	
	
	public int deleteKyjHelloBoard(String k_no){
		System.out.println("[log] TestMain.deleteKyjHelloBoard() ���� >>> ");
		System.out.println("[log] ������ �� ��ȣ >>> : " + k_no);
		KyjHelloBoardDAO kdao = new KyjHelloBoardDAOImpl();
		KyjHelloBoardVO kvo = null;
		kvo = new KyjHelloBoardVO();
		kvo.setKno(k_no);
		
		int nCnt = kdao.deleteKyjHelloBoard(kvo);
		
		return nCnt;
	}
	
	public int updateKyjHelloBoard(String k_no,
								   String k_subject,
								   String k_memo){
		System.out.println("[log] TestMain.updateKyjHelloBoard() ���� >>> ");
		System.out.println("[log] �Է��� ���� ��� >>> : " + k_no+" "
													  + k_subject+" "
													  + k_memo);
		KyjHelloBoardDAO kdao = new KyjHelloBoardDAOImpl();
		KyjHelloBoardVO kvo = null;
		kvo = new KyjHelloBoardVO();
		kvo.setKno(k_no);
		kvo.setKsubject(k_subject);
		kvo.setKmemo(k_memo);
		
		int nCnt = kdao.updateKyjHelloBoard(kvo);
		
		return nCnt;
	}
	
	
	public int insertKyjHelloBoard(String k_subject,
								   String k_name,
								   String k_pw,
								   String k_memo){
		System.out.println("[log] TestMain.insertKyjHelloBoard() ���� >>> ");
		System.out.println("[log] �Է��� ���� ��� >>> : " + k_subject+" "
													  + k_name+" "
													  + k_pw+" "
													  + k_memo);
		KyjHelloBoardDAO kdao = new KyjHelloBoardDAOImpl();
		KyjHelloBoardVO kvo = null;
		kvo = new KyjHelloBoardVO();
		kvo.setKsubject(k_subject);
		kvo.setKname(k_name);
		kvo.setKpw(k_pw);
		kvo.setKmemo(k_memo);
		System.out.println("[log] kvo�� ���� ���� ��� >>> : " + k_subject+" "
				  + k_name+" "
				  + k_pw+" "
				  + k_memo);
		int nCnt = kdao.insertKyjHelloBoard(kvo);
		return nCnt;
	}
		
	public ArrayList<KyjHelloBoardVO> selectKyjHelloBoard(){
		System.out.println("(log) KyjMemberScr.selectKyjHelloBoard �Լ� ���� >>> ");
		KyjHelloBoardDAO kdao = new KyjHelloBoardDAOImpl();
		ArrayList<KyjHelloBoardVO> aList = kdao.selectKyjHelloBoard();
		System.out.println("(log) KyjMemberScr.selectKyjHelloBoard �Լ� �� <<< ");
		return aList;
	}
	
	public ArrayList<KyjHelloBoardVO> selectKyjBoard(String k_no){
		System.out.println("(log) KyjMemberScr.selectKyjBoard �Լ� ���� >>> ");
		KyjHelloBoardDAO kdao = new KyjHelloBoardDAOImpl();
		KyjHelloBoardVO kvo = null;
		kvo = new KyjHelloBoardVO();
		kvo.setKno(k_no);
		ArrayList<KyjHelloBoardVO> aList = kdao.selectKyjBoard(kvo);
		System.out.println("(log) KyjMemberScr.selectKyjBoard �Լ� �� <<< ");
		return aList;
	}
	
	public static void displayFun(ArrayList<KyjHelloBoardVO> aList){
		for(int i=0; i<aList.size(); i++){
			KyjHelloBoardVO kvo = aList.get(i);
			System.out.println("���� >>> : " + kvo.getKsubject());
			System.out.println("�̸� >>> : " + kvo.getKname());
			System.out.println("��� >>> : " + kvo.getKpw());
			System.out.println("�޸� >>> : " + kvo.getKmemo());
			System.out.println("yn >>> : " + kvo.getKdeleteyn());
			System.out.println("in >>> : " + kvo.getKinsertdate());
			System.out.println("up >>> : " + kvo.getKupdatedate());
		}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String k_subject, k_name, k_pw, k_memo, k_no = "";
		System.out.println(" I or S or U or SS or D �Է� >>> ");
		Scanner sc = new Scanner(System.in);
		String st = sc.nextLine();
		
		// delete
		if ("D".equals(st)){
			System.out.println("[log] main().delete ���� >>> ");
			System.out.println(" ���� �� �� ��ȣ >>> ");
			k_no = sc.next();
			
			TestMain km = new TestMain();
			int nCnt = km.deleteKyjHelloBoard(k_no);
			
			if(nCnt==1){
				System.out.println("���� ����");
			}else{
				System.out.println("����");
			}
			
		}
			
		// search
		if("SS".equals(st)){
			System.out.println("[log] main().search ���� >>> ");
			System.out.println("�˻��� ��ȣ >>> ");
			k_no = sc.next();
			TestMain km = new TestMain();
			ArrayList<KyjHelloBoardVO> aList = km.selectKyjBoard(k_no);
			int aListsize = aList.size();
			System.out.println("[log] aList.size >>> : " + aList.size());
			if(aListsize>0){
				TestMain.displayFun(aList);
			}else{
				System.out.println("�˻� ����");
			}
			System.out.println("[log] main().search ���� <<< ");
		}
		
		// update
		
		if("U".equals(st)){
			System.out.println("[log] main().update ���� >>> ");
			System.out.println(" ���� �� �� ��ȣ >>> ");
			k_no = sc.next();
			System.out.println(" ���� �� �� ���� >>> ");
			k_subject = sc.next();
			System.out.println(" ���� �� �� ���� >>> ");
			k_memo = sc.next();
			
			TestMain km = new TestMain();
			int nCnt = km.updateKyjHelloBoard(k_no, k_subject, k_memo);
			
			if(nCnt==1){
				System.out.println("���� ����");
			}else{
				System.out.println("����");
			}
			
		}
		
		
		// insert
		if("I".equals(st)){
			System.out.println("�����غ���! ");
			System.out.println("���� >>> ");
			k_subject = sc.nextLine();
			System.out.println("�̸� >>> ");
			k_name = sc.nextLine();
			System.out.println("��� >>> ");
			k_pw = sc.nextLine();
			System.out.println("�޸� >>> ");
			k_memo = sc.nextLine();
			
			System.out.println("[log �Էµ� ����] >>> : "  + k_subject+" "
													  + k_name+" "
													  + k_pw+" "
													  + k_memo);
			
			TestMain km = new TestMain();
			int nCnt = km.insertKyjHelloBoard(k_subject, k_name, k_pw, k_memo);
			if(nCnt==1){
				System.out.println("�Է� ����");
			}else{
				System.out.println("����");
			}
		}
		// select
		if("S".equals(st)){
			System.out.println("[log] main().select�� ���� >>> ");
			TestMain tm = new TestMain();
			ArrayList<KyjHelloBoardVO> aList = tm.selectKyjHelloBoard();
			System.out.println("[log] aList.size() >>> : " + aList.size());
			System.out.println("[log] main() aList >>> : " + aList);
			// ���
			System.out.println("[log] ��� ��� >>> : \n");
			int aListsize = aList.size();
			System.out.println("[log] aListsize ��µ� ������ ���� ���� >>> : " + aList.size());
//			for(int i=0; i<aList.size(); i++){
//				KyjHelloBoardVO kvo = aList.get(i);
//				System.out.println("���� >>> : " + kvo.getKsubject());
//				System.out.println("�̸� >>> : " + kvo.getKname());
//				System.out.println("��� >>> : " + kvo.getKpw());
//				System.out.println("�޸� >>> : " + kvo.getKmemo());
//			}
				if(aListsize>0){
					TestMain.displayFun(aList);
				}
			System.out.println("[log] main().select�� ���� <<< ");
		}
	}

}
