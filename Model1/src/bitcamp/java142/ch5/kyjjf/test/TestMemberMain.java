package bitcamp.java142.ch5.kyjjf.test;
import java.util.ArrayList;
import java.util.Scanner;

import bitcamp.java142.ch5.kyjjf.dao.KyjMemberDAO;
import bitcamp.java142.ch5.kyjjf.dao.KyjMemberDAOImpl;
import bitcamp.java142.ch5.kyjjf.vo.KyjMemberVO;

public class TestMemberMain {
	public ArrayList<KyjMemberVO> searchKyjMember(String kname){
		System.out.println("(log) KyjMemberScr.searchKyjMember 함수 시작 >>> ");
		KyjMemberDAO kdao = new KyjMemberDAOImpl();
		KyjMemberVO kvo = null;
		kvo = new KyjMemberVO();
		kvo.setKname(kname);
		ArrayList<KyjMemberVO> aList = kdao.searchKyjMember(kvo);
		System.out.println("(log) KyjMemberScr.searchKyjMember 함수 끝 >>> ");
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
	System.out.println("(log) KyjMemberScr.insertKyjMember 함수 시작 >>> ");
	System.out.println("(log) 입력할 정보 출력 >>> : " +k_id+" "+k_pw+" "
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
	System.out.println("(log) 입력할 정보 출력 >>> : " +k_id+" "+k_pw+" "
									  +k_name+" "+k_hp+" "
									  +k_birth+" "+k_mail+" "
									  +k_postno+" "+k_juso);
	boolean bFlag = kdao.insertKyjMember(kvo);
	System.out.println("(log) KyjMemberScr.insertKyjMember 함수 끝 >>> ");
	return bFlag;
	}
	public static void displayFun(ArrayList<KyjMemberVO> aList){
		for(int i=0; i<aList.size(); i++){
			KyjMemberVO kvo = aList.get(i);
			System.out.println("회원번호 : " + kvo.getKnumm());
			System.out.println("아이디 : " + kvo.getKid());
			System.out.println("비밀번호 : " + kvo.getKpw());
			System.out.println("이름 : " + kvo.getKname());
			System.out.println("휴대전화번호 : " + kvo.getKhp());
			System.out.println("생년월일 : " + kvo.getKbirth());
			System.out.println("이메일 : " + kvo.getKemail());
			System.out.println("우편번호 : " + kvo.getKpostno());
			System.out.println("도로명주소 : " + kvo.getKjuso());
			System.out.println("삭제여부 : " + kvo.getKdeleteyn());
			System.out.println("입력일 : " + kvo.getKinsertdate());
			System.out.println("수정일 : " + kvo.getKupdatedate());
			System.out.println("―――――――――――――――――――――――――――――");
		}
	}
	public ArrayList<KyjMemberVO> selectKyjMember(){
		System.out.println("(log) KyjMemberScr.selectKyjMember 함수 시작 >>> ");
		KyjMemberDAO kdao = new KyjMemberDAOImpl();
		ArrayList<KyjMemberVO> aList = kdao.selectKyjMember();
		System.out.println("(log) KyjMemberScr.selectKyjMember 함수 끝 >>> ");
		return aList;
	}
	public static void main(String[] args) {
		System.out.println("(log) KytMemberScr.main() Start >>> ");
		boolean aFlag = true;
		while(aFlag){
			
		String k_id,k_pw,k_name,k_hp,k_birth,k_mail,k_postno,k_juso,kemail,kname = "";
		System.out.println("――――――――――MAIN――――――――――");
		System.out.println(" (I)회원가입 \n (U)이메일수정 \n (D)회원삭제 \n (SS)전체회원보기 \n (SR)회원검색 \n (LS)키워드'성씨'검색\n (E)프로그램종료");
		System.out.println("――――――――――――――――――――――――");
		System.out.println(" 괄호 안 문자를 입력해 프로그램 실행 >>> : ");
		Scanner sc = new Scanner(System.in);
		String st = sc.nextLine();

		// insert
		if("I".equals(st)){
			
			System.out.println("(log) main().insert문 시작 >>>");
			System.out.println("※회원가입※ \n(★ 표시된 항목은 반드시 입력해야 합니다.) \n★사용할 아이디 입력(영문+숫자) \n>>>");
			k_id = sc.nextLine();
			System.out.println("★패드워드 입력(영문+숫자 or 숫자) \n>>>");
			k_pw = sc.nextLine();
			System.out.println("★이름 입력(한글) \n>>>");
			k_name = sc.nextLine();
			System.out.println("휴대전화번호 입력(-제외) \n>>>");
			k_hp = sc.nextLine();
			System.out.println("생년월일 입력(ex.19991212) \n>>>");
			k_birth = sc.nextLine();
			System.out.println("이메일주소 입력 \n>>>");
			k_mail = sc.nextLine();
			System.out.println("우편번호 입력 \n>>>");
			k_postno = sc.nextLine();
			System.out.println("주소 입력 \n>>>");
			k_juso = sc.nextLine();
	

//			String k_id = "yeonjungk";
//			String k_pw = "123456";
//			String k_name = "김연정";
//			String k_hp = "01033333333";
//			String k_birth = "19921228";
//			String k_mail = "yeon_j_k@naver.com";
//			String k_postno = "44444";
//			String k_juso = "경기도 수원시";
			System.out.println("입력된 정보  >>> \n" + "아이디 : " + k_id+"\n비밀번호 : "+k_pw+"\n이름 : "+k_name+"\n휴대전화번호 : "+k_hp+"\n생년월일 : "+k_birth+"\n이메일 : "+k_mail+"\n우편번호 : "+k_postno+"\n도로명주소 : "+k_juso+"\n");
			
			TestMemberMain km = new TestMemberMain();
			boolean bFlag = km.insertKyjMember(k_id,k_pw,k_name,k_hp,k_birth,k_mail,k_postno,k_juso);
			if(bFlag){
				System.out.println("(log) bFlag 데이터 있음? >>> : " + bFlag);
				TestMemberMain km0 = new TestMemberMain();
				ArrayList<KyjMemberVO> aList = km0.selectKyjMember();
				int aListSize = aList.size();
				if(aListSize>0){
					TestMemberMain.displayFun(aList);
				}
			}else{
				System.out.println("(log) 데이터 입력 실패");
			}
		System.out.println("(log) main().insert문 끝 >>>");
		}
		
		
		else if("SS".equals(st)){
			System.out.println("(log) main().select문 시작 >>> ");
			TestMemberMain km = new TestMemberMain(); 
			ArrayList<KyjMemberVO> aList = km.selectKyjMember();
			System.out.println("(log) aList.size() >>> : " + aList.size());
			System.out.println("(log) main() aList >>> : " + aList);
			//출력
			System.out.println("(log) 결과 출력 >>> \n");
			int aListsize = aList.size();
			System.out.println("(log) aListsize 출력될 데이터 행의 갯수 >>> : " + aList.size());
			System.out.println("\n 전체 회원("+aList.size()+"명)에 대한 검색 결과 출력  >>> : ");
			System.out.println("―――――――――――――――――――――――――――――");
			if(aListsize>0){
				TestMemberMain.displayFun(aList);
			}
			System.out.println("(log) main().select문 끝  >>> ");
		}
		// search
				else if("SR".equals(st)){
					System.out.println("(log) main().search문 시작 >>> ");
					System.out.println("검색 할 사람의 이름 >>>");
					kname = sc.next();
					TestMemberMain km = new TestMemberMain();
					ArrayList<KyjMemberVO> aList = km.searchKyjMember(kname);
					int aListsize = aList.size();
					System.out.println("(log) aList.size() >>> : " + aList.size());
					System.out.println("\n검색 건수("+aList.size()+"건) 사용자명 '" + kname + "'의 정보 검색 결과 출력  >>> : ");
					System.out.println("―――――――――――――――――――――――――――――");
					if(aListsize>0){
						TestMemberMain.displayFun(aList);
					}else{
						System.out.println("검색 실패");
					}
					System.out.println("(log) main().search문 끝 >>> ");
				}
	} // main
}}
