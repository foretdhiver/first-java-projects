package test;

import java.util.ArrayList;
import java.util.Scanner;

import kyj.board.dao.KyjHelloBoardDAO;
import kyj.board.dao.KyjHelloBoardDAOImpl;
import kyj.board.vo.KyjHelloBoardVO;


public class TestMain {
	
	
	public int deleteKyjHelloBoard(String k_no){
		System.out.println("[log] TestMain.deleteKyjHelloBoard() 시작 >>> ");
		System.out.println("[log] 삭제할 글 번호 >>> : " + k_no);
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
		System.out.println("[log] TestMain.updateKyjHelloBoard() 시작 >>> ");
		System.out.println("[log] 입력할 정보 출력 >>> : " + k_no+" "
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
		System.out.println("[log] TestMain.insertKyjHelloBoard() 시작 >>> ");
		System.out.println("[log] 입력할 정보 출력 >>> : " + k_subject+" "
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
		System.out.println("[log] kvo에 넣은 정보 출력 >>> : " + k_subject+" "
				  + k_name+" "
				  + k_pw+" "
				  + k_memo);
		int nCnt = kdao.insertKyjHelloBoard(kvo);
		return nCnt;
	}
		
	public ArrayList<KyjHelloBoardVO> selectKyjHelloBoard(){
		System.out.println("(log) KyjMemberScr.selectKyjHelloBoard 함수 시작 >>> ");
		KyjHelloBoardDAO kdao = new KyjHelloBoardDAOImpl();
		ArrayList<KyjHelloBoardVO> aList = kdao.selectKyjHelloBoard();
		System.out.println("(log) KyjMemberScr.selectKyjHelloBoard 함수 끝 <<< ");
		return aList;
	}
	
	public ArrayList<KyjHelloBoardVO> selectKyjBoard(String k_no){
		System.out.println("(log) KyjMemberScr.selectKyjBoard 함수 시작 >>> ");
		KyjHelloBoardDAO kdao = new KyjHelloBoardDAOImpl();
		KyjHelloBoardVO kvo = null;
		kvo = new KyjHelloBoardVO();
		kvo.setKno(k_no);
		ArrayList<KyjHelloBoardVO> aList = kdao.selectKyjBoard(kvo);
		System.out.println("(log) KyjMemberScr.selectKyjBoard 함수 끝 <<< ");
		return aList;
	}
	
	public static void displayFun(ArrayList<KyjHelloBoardVO> aList){
		for(int i=0; i<aList.size(); i++){
			KyjHelloBoardVO kvo = aList.get(i);
			System.out.println("제목 >>> : " + kvo.getKsubject());
			System.out.println("이름 >>> : " + kvo.getKname());
			System.out.println("비번 >>> : " + kvo.getKpw());
			System.out.println("메모 >>> : " + kvo.getKmemo());
			System.out.println("yn >>> : " + kvo.getKdeleteyn());
			System.out.println("in >>> : " + kvo.getKinsertdate());
			System.out.println("up >>> : " + kvo.getKupdatedate());
		}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String k_subject, k_name, k_pw, k_memo, k_no = "";
		System.out.println(" I or S or U or SS or D 입력 >>> ");
		Scanner sc = new Scanner(System.in);
		String st = sc.nextLine();
		
		// delete
		if ("D".equals(st)){
			System.out.println("[log] main().delete 시작 >>> ");
			System.out.println(" 삭제 할 글 번호 >>> ");
			k_no = sc.next();
			
			TestMain km = new TestMain();
			int nCnt = km.deleteKyjHelloBoard(k_no);
			
			if(nCnt==1){
				System.out.println("삭제 성공");
			}else{
				System.out.println("실패");
			}
			
		}
			
		// search
		if("SS".equals(st)){
			System.out.println("[log] main().search 시작 >>> ");
			System.out.println("검색할 번호 >>> ");
			k_no = sc.next();
			TestMain km = new TestMain();
			ArrayList<KyjHelloBoardVO> aList = km.selectKyjBoard(k_no);
			int aListsize = aList.size();
			System.out.println("[log] aList.size >>> : " + aList.size());
			if(aListsize>0){
				TestMain.displayFun(aList);
			}else{
				System.out.println("검색 실패");
			}
			System.out.println("[log] main().search 종료 <<< ");
		}
		
		// update
		
		if("U".equals(st)){
			System.out.println("[log] main().update 시작 >>> ");
			System.out.println(" 수정 할 글 번호 >>> ");
			k_no = sc.next();
			System.out.println(" 수정 할 글 제목 >>> ");
			k_subject = sc.next();
			System.out.println(" 수정 할 글 내용 >>> ");
			k_memo = sc.next();
			
			TestMain km = new TestMain();
			int nCnt = km.updateKyjHelloBoard(k_no, k_subject, k_memo);
			
			if(nCnt==1){
				System.out.println("수정 성공");
			}else{
				System.out.println("실패");
			}
			
		}
		
		
		// insert
		if("I".equals(st)){
			System.out.println("시작해볼까! ");
			System.out.println("제목 >>> ");
			k_subject = sc.nextLine();
			System.out.println("이름 >>> ");
			k_name = sc.nextLine();
			System.out.println("비번 >>> ");
			k_pw = sc.nextLine();
			System.out.println("메모 >>> ");
			k_memo = sc.nextLine();
			
			System.out.println("[log 입력된 정보] >>> : "  + k_subject+" "
													  + k_name+" "
													  + k_pw+" "
													  + k_memo);
			
			TestMain km = new TestMain();
			int nCnt = km.insertKyjHelloBoard(k_subject, k_name, k_pw, k_memo);
			if(nCnt==1){
				System.out.println("입력 성공");
			}else{
				System.out.println("실패");
			}
		}
		// select
		if("S".equals(st)){
			System.out.println("[log] main().select문 시작 >>> ");
			TestMain tm = new TestMain();
			ArrayList<KyjHelloBoardVO> aList = tm.selectKyjHelloBoard();
			System.out.println("[log] aList.size() >>> : " + aList.size());
			System.out.println("[log] main() aList >>> : " + aList);
			// 출력
			System.out.println("[log] 결과 출력 >>> : \n");
			int aListsize = aList.size();
			System.out.println("[log] aListsize 출력될 데이터 행의 갯수 >>> : " + aList.size());
//			for(int i=0; i<aList.size(); i++){
//				KyjHelloBoardVO kvo = aList.get(i);
//				System.out.println("제목 >>> : " + kvo.getKsubject());
//				System.out.println("이름 >>> : " + kvo.getKname());
//				System.out.println("비번 >>> : " + kvo.getKpw());
//				System.out.println("메모 >>> : " + kvo.getKmemo());
//			}
				if(aListsize>0){
					TestMain.displayFun(aList);
				}
			System.out.println("[log] main().select문 종료 <<< ");
		}
	}

}
