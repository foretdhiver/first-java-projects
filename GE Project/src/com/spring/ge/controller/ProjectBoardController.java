package com.spring.ge.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.spring.ge.common.Chaebun;
import com.spring.ge.common.FilePath;
import com.spring.ge.common.PjFilePath;
import com.spring.ge.service.ProjectBoardService;
import com.spring.ge.vo.ProjectBoardVO;

@Controller
@RequestMapping(value="/pjboard")
public class ProjectBoardController {

	@Autowired
	private ProjectBoardService pjboardService;
	
	//����Ʈ
	@ResponseBody
	@RequestMapping(value="/pjBoardList")
	public ModelAndView pjBoardList(@ModelAttribute ProjectBoardVO pjvo, Model model){
		
		ModelAndView mav = new ModelAndView();
		
		//�˻� ������ Ȯ��
		System.out.println("������ Search>>>:" + pjvo.getSearch());
		System.out.println("������ Keyword>>>:" + pjvo.getKeyword());
	
		//������Ȯ��
		System.out.println("������ pageno>>>:" + pjvo.getPageno_());
		System.out.println("������ listsize>>>:" + pjvo.getListsize_());
		
		int listsize=10;
		
		if(pjvo.getListsize_()==null){
			pjvo.setListsize_(listsize+"");
		}
		if(pjvo.getPageno_()==null){
			pjvo.setPageno_("1");
		}
			
		List<ProjectBoardVO> pbList = pjboardService.pjBoardList(pjvo);
		mav.addObject("pjBoardList",pbList);
		mav.addObject("data", pjvo);
		mav.addObject("listsize_", listsize);
		mav.setViewName("/ProjectBoard/pjBoardList");

		return mav;
	}
	
	//�۾�����
	@RequestMapping(value = "/pjBoardWrite",  method = RequestMethod.GET)
	public String pjBoardWrite() {
		
		return "ProjectBoard/pjBoardWrite";
		
	}
	//�۾���
	@RequestMapping(value="/pjBoardInsert",  method = RequestMethod.POST )
	public ModelAndView pjBoardInsert(HttpServletRequest request, @ModelAttribute ProjectBoardVO pjvo) 
			throws IllegalStateException, IOException{

		ModelAndView mav = new ModelAndView();
		
		String pbno ="";
		String pjname ="";
		String deptcd="";
		String pbsubject="";
		String emname="";
		String jobcd="";
		String pbpw="";
		String pbcontent="";
		String pbinsertdate="";
		String pbupdatedate="";
		String pbdeleteyn="";
		String pbfilepath="";
		String pbcvcnt="";
		int size = 10*1024*1024;
		String upload = PjFilePath.Pj_FILEPATH;
		int result = 0;
		
		try{
			System.out.println("(log) try >>> ");
		
			MultipartRequest mr = new MultipartRequest(request, upload, size, "EUC-KR", new DefaultFileRenamePolicy());
			
			pjname = mr.getParameter("pjname");
			pbsubject = mr.getParameter("pbsubject");
			deptcd = mr.getParameter("deptcd");
			jobcd = mr.getParameter("jobcd");
			emname=mr.getParameter("emname");
			pbcontent = mr.getParameter("pbcontent");
			pbpw = mr.getParameter("pbpw");
			pbinsertdate = mr.getParameter("pbinsertdate");
			pbupdatedate = mr.getParameter("pbupdatedate");

			Enumeration pbfileName = mr.getFileNames();
			pbfilepath = (String)pbfileName.nextElement();
			String pbfilepath_ = mr.getFilesystemName(pbfilepath);
			
			//ä��
			ProjectBoardVO pjcb = null;
			pjcb = pjboardService.pjChaebun(pjvo);
			pjcb.getPbno();
			
			String pjpjcb = "";
			pjpjcb = pjcb.getPbno();
			pjvo.setPbno(Chaebun.chaebunPB(pjpjcb));
			
			pjvo.setPjname(pjname);
			pjvo.setPbsubject(pbsubject);
			pjvo.setPbinsertdate(pbinsertdate);
			pjvo.setPbupdatedate(pbupdatedate);
			pjvo.setDeptcd(deptcd);
			pjvo.setJobcd(jobcd);
			pjvo.setEmname(emname);
			pjvo.setPbcontent(pbcontent);
			pjvo.setPbpw(pbpw);
			pjvo.setPbfilepath(pbfilepath_);
						
			//����Ȯ��
			System.out.println("	pbno >>> " + pjvo.getPbno());
			System.out.println("	pjname >>> " + pjvo.getPjname());
			System.out.println("	pbsubject >>> " + pjvo.getPbsubject());
			System.out.println("	Pbinsertdate >>> " + pjvo.getPbinsertdate());
			System.out.println("	Pbupdatedate >>> " + pjvo.getPbupdatedate());
			System.out.println("	Deptcd >>> " + pjvo.getDeptcd());
			System.out.println("	Jobcd >>> " + pjvo.getJobcd());
			System.out.println("	Emname >>> " + pjvo.getEmname());
			System.out.println("	pbcontent >>> " + pjvo.getPbcontent());
			System.out.println("	pbpw >>> " + pjvo.getPbpw());
			System.out.println("	pbfilepath_ >>> " + pjvo.getPbfilepath());
			
			result = pjboardService.pjBoardInsert(pjvo);
			
			String str="";
			
			if(result>0){
				System.out.println("insert if >>> ");
				str="�Խñ��� �ԷµǾ����ϴ�.";
			}else{
				str="�Է����� ���߽��ϴ�.";
			}
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}

		mav.addObject("result", result);
		mav.setViewName("ProjectBoard/pjResult");
		return mav;
	}
	
	
	//�󼼺��� 
	@RequestMapping(value="/pjBoardDetail", method=RequestMethod.POST)
	public ModelAndView pjBoardDetail(@ModelAttribute ProjectBoardVO pjvo){
		
		String no = pjvo.getPbno();
		
		ModelAndView mav = new ModelAndView();
		
		ProjectBoardVO pjDetail = pjboardService.pjBoardDetail(pjvo);
		
		//÷������ ó��
		if(pjDetail!=null && (!pjDetail.equals(""))){
			pjDetail.setPbcontent(pjDetail.getPbcontent().toString().replaceAll("\n", "<br>"));
		}//if
		
		//��ȸ��
		pjboardService.pjBoardListCnt(pjvo);
		
		mav.addObject("pjDetail", pjDetail);
		mav.setViewName("ProjectBoard/pjBoardDetail");
		
		return mav;
	}
		
	//�� ���������� ����
	@RequestMapping(value="/pjUpdateForm")
	public String pjUpdateForm(@ModelAttribute ProjectBoardVO pjvo, Model model){
		
		ProjectBoardVO updateVO = new ProjectBoardVO();
		updateVO = pjboardService.pjBoardDetail(pjvo);
		
		model.addAttribute("pjUpdateForm", updateVO);
		return "ProjectBoard/pjBoardUpdate";
	}
	
	
	//update >> ��Ƽ��Ʈ�� 
	@RequestMapping(value="/pjBoardUpdate", method = RequestMethod.POST)
	public ModelAndView pjBoardUpdate(HttpServletRequest request, @ModelAttribute ProjectBoardVO pjvo) 
			throws IllegalStateException, IOException{
		
		ModelAndView mav = new ModelAndView();
		
		//���� �ʱ�ȭ
		String pbno ="";
		String pjname ="";
		String pbsubject="";
		String pbcontent="";
		String pbupdatedate="";
		String pbfilepath="";
		String deptcd="";
		String jobcd="";
		String upload = PjFilePath.Pj_FILEPATH;
		int size = 10*1024*1024;
		int result = 0;
		
		try{
				MultipartRequest mr = new MultipartRequest(request, upload, size, "EUC-KR");
				
				pbno = mr.getParameter("pbno");
				pjname = mr.getParameter("pjname");
				pbsubject = mr.getParameter("pbsubject");
				pbcontent = mr.getParameter("pbcontent");
				deptcd = mr.getParameter("deptcd");
				jobcd = mr.getParameter("jobcd");
				System.out.println("pbno >>> " + pbno);
				System.out.println("pbsubject >>> " + pbsubject);
				System.out.println("pbcontent >>> " + pbcontent);
				System.out.println("deptcd >>> " + deptcd);
				System.out.println("jobcd >>> " + jobcd);
				

				Enumeration pbfileName = mr.getFileNames();
				System.out.println("pbfileName >>> " + pbfileName);
				pbfilepath=(String)pbfileName.nextElement();
				String pbfilepath_ = mr.getFilesystemName(pbfilepath);
				
				pjvo.setPbno(pbno);
				pjvo.setPjname(pjname);
				pjvo.setPbsubject(pbsubject);
				pjvo.setPbcontent(pbcontent);
				pjvo.setPbfilepath(pbfilepath_);
				
				result = pjboardService.pjBoardUpate(pjvo);
			
				if(result >0){
					System.out.println("���� ���� >>>>>>>> ");
				}else{
					System.out.println(" >>>> error >>>> ");	
				}
				
		}catch(Exception e){
			System.out.println("errer >>> " + e.getMessage());
		}
		
		
		mav.addObject("result", result);
		mav.setViewName("ProjectBoard/pjResult");
		
		return mav;
	}
	//����
	@RequestMapping(value="/pjBoardDelete")
	public String pjBoardDelete(@ModelAttribute ProjectBoardVO pjvo, HttpServletRequest request) 
			throws IOException {
		
		int result=0;
		String url="";
		String str = "";
		
		result = pjboardService.pjBoardDelete(pjvo);
		
		if(result==1){
			System.out.println("delete.if >>> ");
			str="�����Ǿ����ϴ�.";
		}
		
		return ("ProjectBoard/pjResult");
	}
	//�ٿ�ε�
	@RequestMapping(value="/pjDownload")
	public String pjDownload(@ModelAttribute ProjectBoardVO pjvo, HttpServletRequest request,Model model){
		String filename =(String) request.getParameter("filename");
		
		model.addAttribute("filename",filename);
		return "common/download";
		
	}
	
	@ResponseBody
	@RequestMapping(value="/pjConfirm", method=RequestMethod.POST)
	public String pwConfirm(@ModelAttribute ProjectBoardVO pjvo){
		
		//�Ʒ� �������� �Է� ������ ���� ���°� ����(1 or 0)
		int result=0;
		result=pjboardService.pjConfirm(pjvo);
				
		return result+"";
	}
}//class

