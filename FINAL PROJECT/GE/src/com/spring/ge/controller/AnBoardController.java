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

		//검색 데이터 확인
		System.out.println("가져온 Search>>>:" + bvo.getSearch());
		System.out.println("가져온 Keyword>>>:" + bvo.getKeyword());
	
		//페이지확인
		System.out.println("가져온 pageno>>>:" + bvo.getPageno_());
		System.out.println("가져온 listsize>>>:" + bvo.getListsize_());
		
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
	
	//상세보기 
	@RequestMapping(value="/anBoardDetail", method=RequestMethod.POST)
	public ModelAndView anBoardDetail(@ModelAttribute AnBoardVO bvo){
		
		ModelAndView mav = new ModelAndView();
		
		AnBoardVO detail = new AnBoardVO();
		detail = anBoardService.anBoardDetail(bvo);
		
		System.out.println("asdf>> + " + bvo.getAbno());
		
		//첨부파일 처리
		if(detail!=null && (!detail.equals(""))){
			detail.setAbcontent(detail.getAbcontent().toString().replaceAll("\n", "<br>"));
		}//if
		
//		//조회수
		anBoardService.anBoardListCnt(bvo);
		
		mav.addObject("detail",detail);
		mav.setViewName("board/anBoardDetail");
	
		return mav;
	}
	
	//글쓰기 폼
	@RequestMapping(value = "/writeForm",  method = RequestMethod.GET)
	public String writeForm() {

		logger.info("(log)writeForm >>> ");
		return "board/anBoardWrite";
	}
	
	// 글쓰기
	@RequestMapping(value="/anBoardInsert",  method = RequestMethod.POST )
	public ModelAndView anBoardInsert(HttpServletRequest request, @ModelAttribute AnBoardVO bvo) 
			throws IllegalStateException, IOException{
			
		ModelAndView mav = new ModelAndView();
		
		//변수 초기화
		logger.info("(log)anBoardInsert 변수초기화 >>>");
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
			
			//변수 가져오기
			logger.info(" 변수 가져오기 >>>");
			absubject = mr.getParameter("absubject");
			abpw = mr.getParameter("abpw");
			abcontent = mr.getParameter("abcontent");
			
			logger.info(" absubject >>>" + absubject);
			
			//사진 가져오기
			logger.info("사진 가져오기  >>>");
			Enumeration abfileName = mr.getFileNames();
			abfile=(String)abfileName.nextElement();
			String abfile_ = mr.getFilesystemName(abfile);
				
			//채번
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
			
			//변수확인
			logger.info("변수확인 >>> ");
			logger.info("	absubject  >>>" + bvo.getAbno());
			logger.info("	abpw >>> " + bvo.getAbpw());
			logger.info("	abcontent >>> " + bvo.getAbcontent());
			logger.info("	abfile_ >>> " + bvo.getAbfile());
			
			result = anBoardService.anBoardInsert(bvo); 
			String str = "";
			if(result>0){
				logger.info("if >>> ");
				str="게시글이 입력되었습니다.";
			}else{
				str="입력하지 못했습니다.";
			}
			
		}catch(Exception e){
			System.out.println("ERROR >> " + e);
		}
		
		mav.addObject("result", result);
		mav.setViewName("board/anResult");
		//mav.~~ 꼭 함수 밖으로 빼기!!!
		
		return mav;		
	}
	
	//글 수정폼으로 가기
	@RequestMapping(value="/updateForm", method = RequestMethod.POST)
	public String updateForm(@ModelAttribute AnBoardVO bvo, Model model){
		//HttpServletRequest랑 @ModelAttribute 같은 것이므로 하나만 써줘도 된다.
		
		AnBoardVO updateVO = new AnBoardVO();
		updateVO = anBoardService.anBoardDetail(bvo);
		
		model.addAttribute("updateForm", updateVO);
		return "board/anBoardUpdate";
	}
	
	//글 수정 구현하기
	@RequestMapping(value="/anBoardUpdate", method = RequestMethod.POST)
	public ModelAndView anBoardUpdate(HttpServletRequest request, @ModelAttribute AnBoardVO abvo) 
			throws IllegalStateException, IOException{
		
		ModelAndView mav = new ModelAndView();
		
		//변수 초기화
		logger.info("update 변수 초기화 >>> ");
		String abno = "";
		String absubject= "";
		String abcontent= "";
		String abfile= "";
		int size = 10*1024*1024;
		String upload =  PjFilePath.Ab_FILEPATH;
		int result=0;
		
		
		try{
			MultipartRequest mr = new MultipartRequest(request, upload, size, "EUC-KR", new DefaultFileRenamePolicy());
			//변수 가져오기
			logger.info("update 변수 가져오기 >>> ");
			abno = mr.getParameter("abno");
			absubject = mr.getParameter("u_subject"); //jsp에서 설정한 id로 받아와야한다
			abcontent = mr.getParameter("u_content");
			logger.info(abno + "/" + absubject + "/" + abcontent);

			//사진 가져오기
			logger.info("update 사진 가져오기 >>> ");
			Enumeration abfileName = mr.getFileNames();
			logger.info("abfileName >>> " + abfileName);
			//oo
			
			abfile=(String)abfileName.nextElement();
			logger.info("abfile >>> " + abfile);
			String abfile_ = mr.getFilesystemName(abfile);
			logger.info("abfile_ >>> " + abfile_);
			
			//데이터확인
			logger.info("update 데이터확인 >>> ");
			logger.info("abno >>> " + abno);
			logger.info("absubject >>> " + absubject);
			logger.info("abcontent >>> " + abcontent);
			logger.info("abfile_ >>> " + abfile_);
			
			//데이터 세팅하기
			logger.info("update 데이터 세팅하기 >>> ");
			abvo.setAbno(abno);
			abvo.setAbsubject(absubject);
			abvo.setAbcontent(abcontent);
			abvo.setAbfile(abfile_);
			
			result =anBoardService.anBoardUpdate(abvo);
			String str = "";
			
			if(result>0){
				logger.info("update if >>> ");
				str="수정되었습니다.";
				logger.info("수정되었습니다. >>> ");
			}else{
				str="수정에 실패하였습니다.";
				logger.info("수정에 실패하였습니다. >>> ");
			}
			
		}catch(Exception e){
			System.out.println("errer >>> " + e.getMessage());
		}
		
		mav.addObject("result", result);
		mav.setViewName("board/anResult");
		
		return mav;
	}
	//글삭제
	@RequestMapping(value="/anBoardDelete")
	public String boardDelete(@ModelAttribute AnBoardVO bvo,HttpServletRequest request) 
			throws IOException {
		
		int result=0;
		String url="";
		String str = "";

		result = anBoardService.anBoardDelete(bvo);
		
		if(result==1){
			logger.info("delete.if >>> ");
			str="삭제되었습니다.";
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
