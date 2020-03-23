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
		System.out.println("[log] MemberController.insertMember ���� >>> ");
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
			System.out.println("[log] MemberController.insertMember.try-catch ���� >>> : ");
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
			// �Էµ� ������ Ȯ��
			System.out.println("���������������������������������������Էµ� ������������������������������������������");
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
			System.out.println("��������������������������������������������������������������������������������������������������");
			kvo = new MemberVO();
			//ä��
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
				System.out.println("ȸ������ ����");
			}else{
				System.out.println("ȸ������ ����");
			}
		}catch(IOException e){ System.out.println("[log] ���� : " + e);}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/result_m");
		mav.addObject("kid", kid);
		System.out.println("[log] MemberController.insertMember ���� <<< ");
		return mav;
	}
	
	/* ��ü ��ȸ */
	@RequestMapping("/listMember")
	public ModelAndView listMember(@ModelAttribute MemberVO param){
		System.out.println("[log] MemberController.listMember ���� >>> ");
		System.out.println("param : " + param);
		System.out.println("�Է��� ���� : ");
		MemberVO.memberPrint(param);
		List<MemberVO> list = memberService.listMember(param);
		int listCnt = list.size();
		System.out.println("listCnt : " + listCnt);
		System.out.println("����������������selectAll �� ��������������������");
		for(int i=0; i<listCnt; i++){
			MemberVO value = (MemberVO)list.get(i);
			MemberVO.memberPrint(value);
			System.out.println("������������������������������������������������������������");
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject("memberList", list);
		System.out.println("list : " + list);
		System.out.println("mav : " + mav);
		mav.setViewName(CONTEXT_PATH+"/member");
		System.out.println("[log] MemberController.listMember ���� <<< ");
		return mav;
	} // end of listMember()
	
	/* �� ���� ���� */
	@RequestMapping("/selectMember")
	public ModelAndView selectMember(@ModelAttribute MemberVO param){
		System.out.println("[log] MemberController.selectMember ���� >>> ");
		List<MemberVO> list = memberService.listMember(param);
		String kidval = param.getKid();
		MemberVO.memberPrint(param);
		System.out.println("kidval : " + kidval);
		int listCnt = list.size();
		System.out.println("listCnt : " + listCnt);
		System.out.println("����������������select �� ��������������������");
		for(int i=0; i<listCnt; i++){
			MemberVO value = (MemberVO)list.get(i);
			MemberVO.memberPrint(value);
			System.out.println("������������������������������������������������������������");
		}
		ModelAndView mav = new ModelAndView();
		if(kidval == null){
			System.out.println("mode = insert ����");
			mav.addObject("mode", "insert");
		}else{
			System.out.println("mode = update ����");
			mav.addObject("memberVO", memberService.selectMember(param));
			mav.addObject("mode", "update");
		}
		mav.setViewName(CONTEXT_PATH+"/member_pop");
		System.out.println("[log] MemberController.selectMember ���� <<< ");
		return mav;
	}
	
	/* ������Ʈ */
	@RequestMapping("/updateMember")
	public ModelAndView updateMember(HttpServletRequest request){
		System.out.println("[log] MemberController.updateMember ���� >>> ");
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
			System.out.println("[log] MemberController.updateMember.try-catch ���� >>> : ");
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
			// �Էµ� ������ Ȯ��
			System.out.println("���������������������������������������Էµ� ������������������������������������������");
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
			System.out.println("��������������������������������������������������������������������������������������������������");
			
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
					System.out.println("(�̹��� ����) ������Ʈ ����");
				}else{
					System.out.println("(�̹��� ����) ������Ʈ ����");
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
					System.out.println("(�̹��� ����) ������Ʈ ����");
				}else{
					System.out.println("(�̹��� ����) ������Ʈ ����");
				}
			}
		}catch(IOException e){ System.out.println("[log] ���� : " + e);}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/result");
		mav.addObject("kid", kid);
		System.out.println("[log] MemberController.updateMember ���� <<< ");
		return mav;
	}
	
	/* ���� */
	@RequestMapping("/deleteMember")
	public ModelAndView deleteMember(HttpServletRequest request){
		System.out.println("[log] MemberController.deleteMember ���� >>> ");
		String uploadPath = Filepath.UPLOAD_FILEPATH;
		int size = 10*1024*1024;
		String kid = "";
		MemberVO kvo = null;
		
		try{
			MultipartRequest multi = new MultipartRequest(request, uploadPath, size, "EUC-KR", new DefaultFileRenamePolicy());
			System.out.println("[log] MemberController.deleteMember.try-catch ���� >>> : ");
			kid = multi.getParameter("kid");
			kvo = new MemberVO();
			kvo.setKid(kid);	
			int bFlag = memberService.deleteMember(kvo);
			System.out.println("bFlag : " + bFlag);
			if(bFlag>0){
				System.out.println("ȸ��Ż�� ����");
			}else{
				System.out.println("ȸ��Ż�� ����");
			}
		}catch(IOException e){ System.out.println("[log] ���� : " + e);}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/result");
		mav.addObject("kid", kid);
		System.out.println("[log] MemberController.deleteMember ���� <<< ");
		return mav;
	}
	
	@RequestMapping("/logIn")
	public ModelAndView logIn(@ModelAttribute MemberVO param, HttpSession session, HttpServletRequest request){
		System.out.println("[log] MemberController.logIn ���� >>> ");
		ModelAndView mav = new ModelAndView();
		MemberVO.memberPrint(param);
		if(param.getKid()==null){
			System.out.println("[log] logIn �Լ� �� if�� ���� >>>");
			mav.setViewName("/member/login");
		}else{
			System.out.println("[log] logIn �Լ� �� else�� ���� >>>");
//			List<MemberVO> list = memberService.listMember(param);
			List<MemberVO> list = memberService.logIn(param);
			String kid = param.getKid();
			String kpw = param.getKpw();
			System.out.println("kid : " + kid);
			System.out.println("kpw : " + kpw);
			int listCnt = list.size();
			System.out.println("listCnt : " + listCnt);
			if(listCnt==1){
				System.out.println("login �Լ� �� if�� �� if��");
				HttpSession hs = request.getSession();
				hs.setAttribute("id", kid);
				String id = (String)hs.getAttribute("id");
				System.out.println("���Ǻο� id >>> : " + hs.getId());
				System.out.println("���Ǻο�  value >>> : " + id);
				mav.addObject("memberVO", memberService.selectMember(param));
				mav.addObject("sessionID", hs.getId());
				mav.addObject("sessionVal", id);
				mav.addObject("mode", "success");
				mav.setViewName("/main");
			}else{
				System.out.println("login �Լ� �� if�� �� else��");
				System.out.println("�α��� ����");
				mav.addObject("mode", "fail");
				mav.setViewName("/main");
			}
		}
		System.out.println("[log] MemberController.logIn ���� >>> ");
		return mav;
	}
	/*�α׾ƿ�*/
	@RequestMapping("/logOut")
	public String logOut(@ModelAttribute MemberVO param, HttpSession session, HttpServletRequest request){
		System.out.println("[log] MemberController.logOut ���� >>> ");
		session.invalidate();
		ModelAndView mav = new ModelAndView();
		request.setAttribute("mode", "logout");
		System.out.println("[log] MemberController.logOut ���� >>> ");
		return "../../index";
	}
	
	@RequestMapping("/idValueCkeck")
	public ModelAndView idValueCkeck(@ModelAttribute MemberVO param){
		System.out.println("[log] MemberController.idValueCkeck ���� >>> ");
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
			System.out.println("[log] MemberController.idValueCheck ���̵� ���� ����");
			bFlag = true;
			System.out.println("[log] bFlag : "+  bFlag);
			mav.addObject("bFlag", bFlag);
			mav.setViewName(CONTEXT_PATH+"/idValueCheck" );
		}else{
			System.out.println("[log] MemberController.idValueCheck  ���̵� ���� ����");
			bFlag = false;
			System.out.println("[log] bFlag : "+  bFlag);
			mav.addObject("bFlag", bFlag);
			mav.setViewName(CONTEXT_PATH+"/idValueCheck" );
		}
		System.out.println("[log] MemberController.idValueCkeck ���� >>> ");
		return mav;
	}
} // end of MemberController class
