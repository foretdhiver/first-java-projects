package com.spring.ge.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.spring.ge.common.Chaebun;
import com.spring.ge.common.PjFilePath;
import com.spring.ge.service.AnBoardService;
import com.spring.ge.vo.AnBoardVO;

@Controller
@RequestMapping(value="anmsBoard")
public class AnBoardController {

	Logger logger = Logger.getLogger(AnBoardController.class);
	
	@Autowired
	private AnBoardService anBoardService;

	@RequestMapping(value = "/anBoardList")
	public String anBoardList(@ModelAttribute AnBoardVO bvo, Model model) {

		//�˻� ������ Ȯ��
		System.out.println("������ Search>>>:" + bvo.getSearch());
		System.out.println("������ Keyword>>>:" + bvo.getKeyword());
	
		//������Ȯ��
		System.out.println("������ pageno>>>:" + bvo.getPageno_());
		System.out.println("������ listsize>>>:" + bvo.getListsize_());
		
		int listsize=10;
		
		if(bvo.getListsize_()==null){
			bvo.setListsize_(listsize+"");
		}
		if(bvo.getPageno_()==null){
			bvo.setPageno_("1");
		}
		
		List<AnBoardVO> boardlist = anBoardService.anBoardList(bvo);

		model.addAttribute("anBoardList",boardlist);
		model.addAttribute("data", bvo);
		model.addAttribute("listsize_", listsize);

		return "board/anBoardList";
	}
	
	//�󼼺��� 
	@RequestMapping(value="/anBoardDetail", method=RequestMethod.POST)
	public ModelAndView anBoardDetail(@ModelAttribute AnBoardVO bvo){
		
		ModelAndView mav = new ModelAndView();
		
		AnBoardVO detail = new AnBoardVO();
		detail = anBoardService.anBoardDetail(bvo);
		
		System.out.println("asdf>> + " + bvo.getAbno());
		
		//÷������ ó��
		if(detail!=null && (!detail.equals(""))){
			detail.setAbcontent(detail.getAbcontent().toString().replaceAll("\n", "<br>"));
		}//if
		
//		//��ȸ��
		anBoardService.anBoardListCnt(bvo);
		
		mav.addObject("detail",detail);
		mav.setViewName("board/anBoardDetail");
	
		return mav;
	}
	
	//�۾��� ��
	@RequestMapping(value = "/writeForm",  method = RequestMethod.GET)
	public String writeForm() {

		logger.info("(log)writeForm >>> ");
		return "board/anBoardWrite";
	}
	
	// �۾���
	@RequestMapping(value="/anBoardInsert",  method = RequestMethod.POST )
	public ModelAndView anBoardInsert(HttpServletRequest request, @ModelAttribute AnBoardVO bvo) 
			throws IllegalStateException, IOException{
			
		ModelAndView mav = new ModelAndView();
		
		//���� �ʱ�ȭ
		logger.info("(log)anBoardInsert �����ʱ�ȭ >>>");
		String abno = "";
		String absubject= "";
		String abpw= "";
		String abcontent= "";
		String abinsertdate= "";
		String abupdatedate= "";
		String deleteyn= "";
		String abcvcnt= "";
		String abfile= "";
		int size = 10*1024*1024;
		String upload =  PjFilePath.Ab_FILEPATH;
//		String abfilefile = FileUploadUtil.fileUpload(bvo.getAbfilefile(), request);
		int result = 0;
		
		try{
			logger.info(" try >>>");
			MultipartRequest mr = new MultipartRequest(request, upload, size, "EUC-KR", new DefaultFileRenamePolicy());
			
			//���� ��������
			logger.info(" ���� �������� >>>");
			absubject = mr.getParameter("absubject");
			abpw = mr.getParameter("abpw");
			abcontent = mr.getParameter("abcontent");
			
			logger.info(" absubject >>>" + absubject);
			
			//���� ��������
			logger.info("���� ��������  >>>");
			Enumeration abfileName = mr.getFileNames();
			abfile=(String)abfileName.nextElement();
			String abfile_ = mr.getFilesystemName(abfile);
				
			//ä��
			AnBoardVO abcb = null;
			abcb = anBoardService.chaebun(bvo);
			abcb.getAbno();
			logger.info("abcb  >>>" + abcb);
			
			String cb = "";
			cb=abcb.getAbno();
			bvo.setAbno(Chaebun.chaebunBA(cb));
			
			bvo.setAbsubject(absubject);
			bvo.setAbpw(abpw);
			bvo.setAbcontent(abcontent);
			bvo.setAbfile(abfile_);
			
			//����Ȯ��
			logger.info("����Ȯ�� >>> ");
			logger.info("	absubject  >>>" + bvo.getAbno());
			logger.info("	abpw >>> " + bvo.getAbpw());
			logger.info("	abcontent >>> " + bvo.getAbcontent());
			logger.info("	abfile_ >>> " + bvo.getAbfile());
			
			result = anBoardService.anBoardInsert(bvo); 
			String str = "";
			if(result>0){
				logger.info("if >>> ");
				str="�Խñ��� �ԷµǾ����ϴ�.";
			}else{
				str="�Է����� ���߽��ϴ�.";
			}
			
		}catch(Exception e){
			System.out.println("ERROR >> " + e);
		}
		
		mav.addObject("result", result);
		mav.setViewName("board/anResult");
		//mav.~~ �� �Լ� ������ ����!!!
		
		return mav;		
	}
	
	//�� ���������� ����
	@RequestMapping(value="/updateForm", method = RequestMethod.POST)
	public String updateForm(@ModelAttribute AnBoardVO bvo, Model model){
		//HttpServletRequest�� @ModelAttribute ���� ���̹Ƿ� �ϳ��� ���൵ �ȴ�.
		
		AnBoardVO updateVO = new AnBoardVO();
		updateVO = anBoardService.anBoardDetail(bvo);
		
		model.addAttribute("updateForm", updateVO);
		return "board/anBoardUpdate";
	}
	
	//�� ���� �����ϱ�
	@RequestMapping(value="/anBoardUpdate", method = RequestMethod.POST)
	public ModelAndView anBoardUpdate(HttpServletRequest request, @ModelAttribute AnBoardVO abvo) 
			throws IllegalStateException, IOException{
		
		ModelAndView mav = new ModelAndView();
		
		//���� �ʱ�ȭ
		logger.info("update ���� �ʱ�ȭ >>> ");
		String abno = "";
		String absubject= "";
		String abcontent= "";
		String abfile= "";
		int size = 10*1024*1024;
		String upload =  PjFilePath.Ab_FILEPATH;
		int result=0;
		
		
		try{
			MultipartRequest mr = new MultipartRequest(request, upload, size, "EUC-KR", new DefaultFileRenamePolicy());
			//���� ��������
			logger.info("update ���� �������� >>> ");
			abno = mr.getParameter("abno");
			absubject = mr.getParameter("u_subject"); //jsp���� ������ id�� �޾ƿ;��Ѵ�
			abcontent = mr.getParameter("u_content");
			logger.info(abno + "/" + absubject + "/" + abcontent);

			//���� ��������
			logger.info("update ���� �������� >>> ");
			Enumeration abfileName = mr.getFileNames();
			logger.info("abfileName >>> " + abfileName);
			//oo
			
			abfile=(String)abfileName.nextElement();
			logger.info("abfile >>> " + abfile);
			String abfile_ = mr.getFilesystemName(abfile);
			logger.info("abfile_ >>> " + abfile_);
			
			//������Ȯ��
			logger.info("update ������Ȯ�� >>> ");
			logger.info("abno >>> " + abno);
			logger.info("absubject >>> " + absubject);
			logger.info("abcontent >>> " + abcontent);
			logger.info("abfile_ >>> " + abfile_);
			
			//������ �����ϱ�
			logger.info("update ������ �����ϱ� >>> ");
			abvo.setAbno(abno);
			abvo.setAbsubject(absubject);
			abvo.setAbcontent(abcontent);
			abvo.setAbfile(abfile_);
			
			result =anBoardService.anBoardUpdate(abvo);
			String str = "";
			
			if(result>0){
				logger.info("update if >>> ");
				str="�����Ǿ����ϴ�.";
				logger.info("�����Ǿ����ϴ�. >>> ");
			}else{
				str="������ �����Ͽ����ϴ�.";
				logger.info("������ �����Ͽ����ϴ�. >>> ");
			}
			
		}catch(Exception e){
			System.out.println("errer >>> " + e.getMessage());
		}
		
		mav.addObject("result", result);
		mav.setViewName("board/anResult");
		
		return mav;
	}
	//�ۻ���
	@RequestMapping(value="/anBoardDelete")
	public String boardDelete(@ModelAttribute AnBoardVO bvo,HttpServletRequest request) 
			throws IOException {
		
		int result=0;
		String url="";
		String str = "";

		result = anBoardService.anBoardDelete(bvo);
		
		if(result==1){
			logger.info("delete.if >>> ");
			str="�����Ǿ����ϴ�.";
		}
		
		return ("board/anResult");
	}
	
	@RequestMapping(value="/anbDownload")
	public String anbDownload(@ModelAttribute AnBoardVO bvo,HttpServletRequest request,Model model){
		String filename =(String) request.getParameter("filename");
		System.out.println("filename>>> "+ filename);
		
		model.addAttribute("filename",filename );
		return "common/download";
		
	}
	
	
}//class
