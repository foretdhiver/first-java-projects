package com.kyjspring.member.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kyjspring.member.common.Filepath;
import com.kyjspring.member.service.MemberService;
import com.kyjspring.member.vo.MemberVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@Controller
@RequestMapping(value="/member")
public class MemberController {
	private static final String CONTEXT_PATH = "member";
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/insertMember")
	public ModelAndView insertMember(HttpServletRequest request){
		System.out.println("[log] MemberController.insertMember 시작 >>> ");
		String uploadPath = Filepath.UPLOAD_FILEPATH;
		System.out.println("uploadPath : " + uploadPath);
		
		int size = 10*1024*1024;
		String kmem = "";
		String kid = "";
		String kpw = "";
		String kname = "";
		String khp = "";
		String kbirth = ""; 
		String kemail = ""; 
		String kpostno = "";
		String kjuso = "";
		String kjuso1 = "";
		String kimage = "";
		String filename = "";
		MemberVO kvo = null;
		
		try{
			MultipartRequest multi = new MultipartRequest(request, uploadPath, size, "EUC-KR", new DefaultFileRenamePolicy());
			System.out.println("[log] MemberController.insertMember.try-catch 시작 >>> : ");
			kmem = multi.getParameter("kmem");
			kid = multi.getParameter("kid");
			kpw = multi.getParameter("kpw");
			kname = multi.getParameter("kname");
			khp = multi.getParameter("khp");
			kbirth = multi.getParameter("kbirth");
			kemail = multi.getParameter("kemail");
			kpostno = multi.getParameter("kpostno");
			kjuso = multi.getParameter("kjuso");
			kjuso1 = multi.getParameter("kjuso1");
			Enumeration files = multi.getFileNames();
			String file = (String)files.nextElement();
			filename = multi.getFilesystemName(file);
			kimage = "../"+Filepath.DOWNLOAD_FILEPATH+filename;
			System.out.println("filename : " + kimage);
			// 입력된 데이터 확인
			System.out.println("―――――――――――――――――――입력된 정보―――――――――――――――――――");
			System.out.printf("kmem : " + kmem +
							  "%nkid : " + kid +
							  "%nkpw : " + kpw +
							  "%nkname : " + kname +
							  "%nkhp : " + khp +
							  "%nkbirth : " + kbirth +
							  "%nkemail : " + kemail +
							  "%nkpostno : " + kpostno +
							  "%nkjuso : " + kjuso +
							  "%nkjuso1 : " + kjuso1 +
							  "%nkimage : " + kimage + "%n");
			System.out.println("―――――――――――――――――――――――――――――――――――――――――――――――――");
			kvo = new MemberVO();
			//채번
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String date = sdf.format(d);
			System.out.println("date : " + date);
			
			List<MemberVO> list = memberService.chaebunMember(kvo);
			String kmem_test = "M" + date + list.get(0).getKmem();
			
			System.out.println("kmem_test : " + kmem_test);
			if (list.size() == 1){
				kvo.setKmem("M" + date + list.get(0).getKmem());
			}
			
			kvo.setKid(kid);
			kvo.setKpw(kpw);
			kvo.setKname(kname);
			kvo.setKhp(khp);
			kvo.setKbirth(kbirth);
			kvo.setKemail(kemail);
			kvo.setKpostno(kpostno);
			kvo.setKjuso(kjuso);
			kvo.setKjuso1(kjuso1);
			kvo.setKimage(kimage);
			
			int bFlag = memberService.insertMember(kvo);
			if(bFlag>0){
				System.out.println("회원가입 성공");
			}else{
				System.out.println("회원가입 실패");
			}
		}catch(IOException e){ System.out.println("[log] 에러 : " + e);}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/result_m");
		mav.addObject("kid", kid);
		System.out.println("[log] MemberController.insertMember 종료 <<< ");
		return mav;
	}
	
	/* 전체 조회 */
	@RequestMapping("/listMember")
	public ModelAndView listMember(@ModelAttribute MemberVO param){
		System.out.println("[log] MemberController.listMember 시작 >>> ");
		System.out.println("param : " + param);
		System.out.println("입력한 정보 : ");
		MemberVO.memberPrint(param);
		List<MemberVO> list = memberService.listMember(param);
		int listCnt = list.size();
		System.out.println("listCnt : " + listCnt);
		System.out.println("――――――――selectAll 된 정보――――――――");
		for(int i=0; i<listCnt; i++){
			MemberVO value = (MemberVO)list.get(i);
			MemberVO.memberPrint(value);
			System.out.println("――――――――――――――――――――――――――――――");
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject("memberList", list);
		System.out.println("list : " + list);
		System.out.println("mav : " + mav);
		mav.setViewName(CONTEXT_PATH+"/member");
		System.out.println("[log] MemberController.listMember 종료 <<< ");
		return mav;
	} // end of listMember()
	
	/* 상세 정보 보기 */
	@RequestMapping("/selectMember")
	public ModelAndView selectMember(@ModelAttribute MemberVO param){
		System.out.println("[log] MemberController.selectMember 시작 >>> ");
		List<MemberVO> list = memberService.listMember(param);
		String kidval = param.getKid();
		MemberVO.memberPrint(param);
		System.out.println("kidval : " + kidval);
		int listCnt = list.size();
		System.out.println("listCnt : " + listCnt);
		System.out.println("――――――――select 된 정보――――――――");
		for(int i=0; i<listCnt; i++){
			MemberVO value = (MemberVO)list.get(i);
			MemberVO.memberPrint(value);
			System.out.println("――――――――――――――――――――――――――――――");
		}
		ModelAndView mav = new ModelAndView();
		if(kidval == null){
			System.out.println("mode = insert 진입");
			mav.addObject("mode", "insert");
		}else{
			System.out.println("mode = update 진입");
			mav.addObject("memberVO", memberService.selectMember(param));
			mav.addObject("mode", "update");
		}
		mav.setViewName(CONTEXT_PATH+"/member_pop");
		System.out.println("[log] MemberController.selectMember 종료 <<< ");
		return mav;
	}
	
	/* 업데이트 */
	@RequestMapping("/updateMember")
	public ModelAndView updateMember(HttpServletRequest request){
		System.out.println("[log] MemberController.updateMember 시작 >>> ");
		String uploadPath = Filepath.UPLOAD_FILEPATH;
		System.out.println("uploadPath : " + uploadPath);
		
		int size = 10*1024*1024;
		String kmem = "";
		String kid = "";
		String kpw = "";
		String kname = "";
		String khp = "";
		String kbirth = ""; 
		String kemail = ""; 
		String kpostno = "";
		String kjuso = "";
		String kjuso1 = "";
		String kimage = "";
		String filename = "";
		MemberVO kvo = null;
		
		try{
			MultipartRequest multi = new MultipartRequest(request, uploadPath, size, "EUC-KR", new DefaultFileRenamePolicy());
			System.out.println("[log] MemberController.updateMember.try-catch 시작 >>> : ");
			kmem = multi.getParameter("kmem");
			kid = multi.getParameter("kid");
			kpw = multi.getParameter("kpw");
			kname = multi.getParameter("kname");
			khp = multi.getParameter("khp");
			kbirth = multi.getParameter("kbirth");
			kemail = multi.getParameter("kemail");
			kpostno = multi.getParameter("kpostno");
			kjuso = multi.getParameter("kjuso");
			kjuso1 = multi.getParameter("kjuso1");
			Enumeration files = multi.getFileNames();
			String file = (String)files.nextElement();
			System.out.println("file >>> : " + file);
			filename = multi.getFilesystemName(file);
			kimage = "../"+Filepath.DOWNLOAD_FILEPATH+filename;
			System.out.println("filename : " + kimage);
			// 입력된 데이터 확인
			System.out.println("―――――――――――――――――――입력된 정보―――――――――――――――――――");
			System.out.printf("kmem : " + kmem +
							  "%nkid : " + kid +
							  "%nkpw : " + kpw +
							  "%nkname : " + kname +
							  "%nkhp : " + khp +
							  "%nkbirth : " + kbirth +
							  "%nkemail : " + kemail +
							  "%nkpostno : " + kpostno +
							  "%nkjuso : " + kjuso +
							  "%nkjuso1 : " + kjuso1 +
							  "%nkimage : " + kimage + "%n");
			System.out.println("―――――――――――――――――――――――――――――――――――――――――――――――――");
			
			if(filename!=null){
				kvo = new MemberVO();
				kvo.setKid(kid);
				kvo.setKpw(kpw);
				kvo.setKname(kname);
				kvo.setKhp(khp);
				kvo.setKbirth(kbirth);
				kvo.setKemail(kemail);
				kvo.setKpostno(kpostno);
				kvo.setKjuso(kjuso);
				kvo.setKjuso1(kjuso1);
				kvo.setKimage(kimage);
					
				int bFlag = memberService.updateMember(kvo);
				System.out.println("bFlag : " + bFlag);
				if(bFlag>0){
					System.out.println("(이미지 포함) 업데이트 성공");
				}else{
					System.out.println("(이미지 포함) 업데이트 실패");
				}
			}else{
				kvo = new MemberVO();
				kvo.setKid(kid);
				kvo.setKpw(kpw);
				kvo.setKname(kname);
				kvo.setKhp(khp);
				kvo.setKbirth(kbirth);
				kvo.setKemail(kemail);
				kvo.setKpostno(kpostno);
				kvo.setKjuso(kjuso);
				kvo.setKjuso1(kjuso1);
				
				int bFlag = memberService.updateMember_w(kvo);
				System.out.println("bFlag : " + bFlag);
				if(bFlag>0){
					System.out.println("(이미지 제외) 업데이트 성공");
				}else{
					System.out.println("(이미지 제외) 업데이트 실패");
				}
			}
		}catch(IOException e){ System.out.println("[log] 에러 : " + e);}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/result");
		mav.addObject("kid", kid);
		System.out.println("[log] MemberController.updateMember 종료 <<< ");
		return mav;
	}
	
	/* 삭제 */
	@RequestMapping("/deleteMember")
	public ModelAndView deleteMember(HttpServletRequest request){
		System.out.println("[log] MemberController.deleteMember 시작 >>> ");
		String uploadPath = Filepath.UPLOAD_FILEPATH;
		int size = 10*1024*1024;
		String kid = "";
		MemberVO kvo = null;
		
		try{
			MultipartRequest multi = new MultipartRequest(request, uploadPath, size, "EUC-KR", new DefaultFileRenamePolicy());
			System.out.println("[log] MemberController.deleteMember.try-catch 시작 >>> : ");
			kid = multi.getParameter("kid");
			kvo = new MemberVO();
			kvo.setKid(kid);	
			int bFlag = memberService.deleteMember(kvo);
			System.out.println("bFlag : " + bFlag);
			if(bFlag>0){
				System.out.println("회원탈퇴 성공");
			}else{
				System.out.println("회원탈퇴 실패");
			}
		}catch(IOException e){ System.out.println("[log] 에러 : " + e);}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/result");
		mav.addObject("kid", kid);
		System.out.println("[log] MemberController.deleteMember 종료 <<< ");
		return mav;
	}
	
	@RequestMapping("/logIn")
	public ModelAndView logIn(@ModelAttribute MemberVO param, HttpSession session, HttpServletRequest request){
		System.out.println("[log] MemberController.logIn 시작 >>> ");
		ModelAndView mav = new ModelAndView();
		MemberVO.memberPrint(param);
		if(param.getKid()==null){
			System.out.println("[log] logIn 함수 안 if문 진입 >>>");
			mav.setViewName("/member/login");
		}else{
			System.out.println("[log] logIn 함수 안 else문 진입 >>>");
//			List<MemberVO> list = memberService.listMember(param);
			List<MemberVO> list = memberService.logIn(param);
			String kid = param.getKid();
			String kpw = param.getKpw();
			System.out.println("kid : " + kid);
			System.out.println("kpw : " + kpw);
			int listCnt = list.size();
			System.out.println("listCnt : " + listCnt);
			if(listCnt==1){
				System.out.println("login 함수 안 if문 안 if문");
				HttpSession hs = request.getSession();
				hs.setAttribute("id", kid);
				String id = (String)hs.getAttribute("id");
				System.out.println("세션부여 id >>> : " + hs.getId());
				System.out.println("세션부여  value >>> : " + id);
				mav.addObject("memberVO", memberService.selectMember(param));
				mav.addObject("sessionID", hs.getId());
				mav.addObject("sessionVal", id);
				mav.addObject("mode", "success");
				mav.setViewName("/main");
			}else{
				System.out.println("login 함수 안 if문 안 else문");
				System.out.println("로그인 실패");
				mav.addObject("mode", "fail");
				mav.setViewName("/main");
			}
		}
		System.out.println("[log] MemberController.logIn 종료 >>> ");
		return mav;
	}
	/*로그아웃*/
	@RequestMapping("/logOut")
	public String logOut(@ModelAttribute MemberVO param, HttpSession session, HttpServletRequest request){
		System.out.println("[log] MemberController.logOut 시작 >>> ");
		session.invalidate();
		ModelAndView mav = new ModelAndView();
		request.setAttribute("mode", "logout");
		System.out.println("[log] MemberController.logOut 종료 >>> ");
		return "../../index";
	}
	
	@RequestMapping("/idValueCkeck")
	public ModelAndView idValueCkeck(@ModelAttribute MemberVO param){
		System.out.println("[log] MemberController.idValueCkeck 시작 >>> ");
		MemberVO mvo = null;
		mvo = new MemberVO();
		mvo.setKid(param.getKid());
		System.out.println("id : " + param.getKid());
		List<MemberVO> list = memberService.idValueCkeck(mvo);
		int listCnt = list.size();
		boolean bFlag = false;
		System.out.println("listCnt : " + listCnt);
		ModelAndView mav = new ModelAndView();
		if(listCnt==0){
			System.out.println("[log] MemberController.idValueCheck 아이디 생성 ㅇㅋ");
			bFlag = true;
			System.out.println("[log] bFlag : "+  bFlag);
			mav.addObject("bFlag", bFlag);
			mav.setViewName(CONTEXT_PATH+"/idValueCheck" );
		}else{
			System.out.println("[log] MemberController.idValueCheck  아이디 생성 ㄴㄴ");
			bFlag = false;
			System.out.println("[log] bFlag : "+  bFlag);
			mav.addObject("bFlag", bFlag);
			mav.setViewName(CONTEXT_PATH+"/idValueCheck" );
		}
		System.out.println("[log] MemberController.idValueCkeck 종료 >>> ");
		return mav;
	}
} // end of MemberController class
