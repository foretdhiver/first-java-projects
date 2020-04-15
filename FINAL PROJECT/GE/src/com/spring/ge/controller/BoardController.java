package com.spring.ge.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ge.common.Chaebun;
import com.spring.ge.service.BDService;
import com.spring.ge.service.BNService;
import com.spring.ge.service.BTService;
import com.spring.ge.service.EM_InfoService;
import com.spring.ge.vo.BDVO;
import com.spring.ge.vo.BNVO;
import com.spring.ge.vo.BTVO;
import com.spring.ge.vo.EmInfoVO;

@Controller
@RequestMapping(value="/board")
public class BoardController {
	
	Logger logger = Logger.getLogger(BoardController.class);
	
	// ������ �̵� ó��
	private int pageSize = 0;
	private int groupSize = 0;
	private int curPage = 0;
	private int totalCount = 0;
	private int pageNo = 0;
	
	@Autowired
	private EM_InfoService eM_InfoService;
	
	@Autowired
	private BDService bDService;
	
	@Autowired
	private BTService bTService;

	@Autowired
	private BNService bNService;
	
	/* �Խ��� ���� */
	@RequestMapping(value="/boardMain")
	public ModelAndView boardMain(@ModelAttribute EmInfoVO evo, HttpSession httpsession){
		logger.info("[BoardController] boardMain ����");
		
		Object infoObj = httpsession.getAttribute("eminfo");
		EmInfoVO infoList = new EmInfoVO();
	    infoList = (EmInfoVO)infoObj;
	    				
		BDVO check = null;
		check = new BDVO();
		
		check.setDeptcd("20");
		List<BDVO> executive = bDService.boardDeptMainList(check);
		check.setDeptcd("30");
		List<BDVO> salesSupport = bDService.boardDeptMainList(check);
		check.setDeptcd("40");
		List<BDVO> sales = bDService.boardDeptMainList(check);
		check.setDeptcd("50");
		List<BDVO> technicalSupport = bDService.boardDeptMainList(check);
				
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("executive", executive);
		mav.addObject("salesSupport", salesSupport);
		mav.addObject("sales", sales);
		mav.addObject("technicalSupport", technicalSupport);
		mav.setViewName("board/BoardMain");	
				
		return mav;
	}

	/* �μ��Խ��� ����Ʈ */
	@RequestMapping(value="/bDList")
	public ModelAndView bDList(@ModelAttribute BDVO bdvo, HttpSession httpsession, HttpServletRequest request){
		logger.info("[BoardController] bDList ����");
		Object infoObj = httpsession.getAttribute("eminfo");
		EmInfoVO infoList = new EmInfoVO();
		infoList = (EmInfoVO)infoObj;
		
		logger.info("[BoardController] Emname >>> " + infoList.getEmname());
		logger.info("[BoardController] Deptname >>> " + infoList.getDeptname());
		logger.info("[BoardController] search >>> " + bdvo.getSearch());
		logger.info("[BoardController] keyword >>> " + bdvo.getKeyword());
		
		BDVO check = null;
		check = new BDVO();
		check.setDeptcd(infoList.getDeptcd());
		
		pageSize =  10;
		String cp = "";
		cp = request.getParameter("curPage");
		System.out.println("����¡ Ȯ�� cp111 " + cp);
		if(cp!=null){
	    	curPage = Integer.parseInt(cp);
	    }else{
	    	curPage = 1;
	    }
		System.out.println("����¡ Ȯ�� cp333 " + curPage);
		logger.info("[BoardController] ps >>> " + pageSize);
		logger.info("[BoardController] cp >>> " + curPage);
		
		check.setSearch(bdvo.getSearch());
		check.setKeyword(bdvo.getKeyword());
		check.setPageSize(pageSize);
		check.setCurPage(curPage);
		
		List<BDVO> boardDepartmentList = bDService.boardDepartmentList(check);	
		int listCnt = boardDepartmentList.size();
		logger.info("[BoardController] listCnt(=Size) >>> " + listCnt);
		
		for(int i=0; i<listCnt; i++){
			BDVO bdVO = null;
			bdVO = new BDVO();
			bdVO = boardDepartmentList.get(i);
			int asdf = check.getCurPage();
			boardDepartmentList.get(i).setCurPage(asdf);
			System.out.println("ddddddd curPage>>" + bdVO.getCurPage());
			pageNo = bdVO.getPageNo();
			totalCount = bdVO.getTotalCount();
			curPage = bdVO.getCurPage();
			groupSize = bdVO.getGroupSize();
			logger.info("[BoardController] ����¡�� ������ : pageNo >>> " + pageNo);
			logger.info("[BoardController] ����¡�� ������ : totalCount >>> " + totalCount);
			logger.info("[BoardController] ����¡�� ������ : curPage >>> " + curPage);
			logger.info("[BoardController] ����¡�� ������ : groupSize >>> " + groupSize);
		}
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("pageNo", pageNo);
		mav.addObject("totalCount", totalCount);
		mav.addObject("curPage", curPage);
		mav.addObject("groupSize", groupSize);
		mav.addObject("boardDepartmentList", boardDepartmentList);
		mav.addObject("serchData", check);
		mav.setViewName("board/BDList");	
				
		return mav;
	}
	
	/* �μ��Խ��� �۾��� �� ����ϱ� */
	@RequestMapping(value="/bDWrite")
	public ModelAndView bDWriteForm(@ModelAttribute BDVO bdvo){
		logger.info("[BoardController] BDWrite ����");
		logger.info("[BoardController] Bdref >>> " + bdvo.getBdref());
		
		if(bdvo.getBdref() == null){
			logger.info("[BoardController] if�� ���� ");
			bdvo.setBdref("0");
			logger.info("[BoardController] Bdref >>> " + bdvo.getBdref());
		}
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("bdvo", bdvo);
		mav.setViewName("board/BDWrite");
		return mav;		
	}
	
	/* �μ��Խ��� �۾��� */
	@RequestMapping(value="/bDWriteInsert")
	public ModelAndView bDWriteInsert(@ModelAttribute BDVO bdvo, HttpServletRequest request, HttpSession httpsession) throws IllegalStateException, IOException{
		logger.info("[BoardController] bDWriteInsert ����");
		
		int result = 0;
		String chaebun = "";
		String bdfilepath = "../../board_image/department.PNG"; 
		
		Object infoObj = httpsession.getAttribute("eminfo");
		EmInfoVO infoList = new EmInfoVO();
		infoList = (EmInfoVO)infoObj;
		
		BDVO chaeVO = null;
		chaeVO = new BDVO();
		chaeVO = bDService.boardDepartmentChebun(chaeVO);
		chaebun = Chaebun.chaebunBD(chaeVO.getBdno());
		logger.info("[BoardController] Chae >>>> " + chaebun);
		logger.info("[BoardController] Bdstep >>> " + bdvo.getBdstep());
		logger.info("[BoardController] Bdref >>> " + bdvo.getBdref());
		
		if(bdvo.getBdref().equals("0")){
			bdvo.setBdref(chaebun);
			logger.info("[BoardController] Bdref�� ���� ������ �۹�ȣ�� Bdref�� ����");
		}
		
		bdvo.setBdno(chaebun);
		bdvo.setDeptcd(infoList.getDeptcd());
		/*���� ���̰� 0�̶��, ���� �������� ���� �Է�*/
		if(bdvo.getBdsubject().length()==0){
			bdvo.setBdsubject("���� ����");
		}
		bdvo.setEmname(infoList.getEmname());
		bdvo.setBdpw(infoList.getEmpw());
		/*���� ���̰� 0�̶��, ���� �������� ���� �Է�*/
		if(bdvo.getBdcontent().length()==0){
			bdvo.setBdcontent("���� ����");
		}
		if(bdvo.getBdtaskyn().length()==0){
			bdvo.setBdtaskyn("N");
		}
		if(bdvo.getBdnoticeyn().length()==0){
			bdvo.setBdnoticeyn("N");
		}
		
		bdvo.setEmno(infoList.getEmno());
		bdvo.setJobcd(infoList.getJobcd());
		bdvo.setBdref(bdvo.getBdref());
		bdvo.setBdfilepath(bdfilepath);
		
		System.out.print("[BoardController] " + bdvo.getBdno() + "  ");
		System.out.print("[BoardController] " + bdvo.getDeptcd() + "  ");
		System.out.print("[BoardController] " + bdvo.getBdsubject() + "  ");
		System.out.print("[BoardController] " + bdvo.getEmname() + "  "); 
		System.out.print("[BoardController] " + bdvo.getBdpw() + "  ");
		System.out.print("[BoardController] " + bdvo.getBdcontent() + "  ");
		System.out.print("[BoardController] " + bdvo.getBdtaskyn() + "  ");
		System.out.print("[BoardController] " + bdvo.getBdnoticeyn() + "  ");
		System.out.print("[BoardController] " + bdvo.getBdfilepath() + "  ");
		System.out.print("[BoardController] " + bdvo.getBdvcnt() + "  ");
		System.out.print("[BoardController] " + bdvo.getEmno() + "  ");
		System.out.print("[BoardController] " + bdvo.getJobcd() + "  ");
		System.out.print("[BoardController] " + bdvo.getBdref() + "  ");
		System.out.print("[BoardController] " + bdvo.getBdstep() + "  ");
		
		ModelAndView mav = new ModelAndView();
		
		result = bDService.boardDepartmentInsert(bdvo);			
		if(result>0){
			logger.info("[BoardController] �Խñ��� �ԷµǾ����ϴ�.");
			mav.setViewName("redirect:" + "/board/bDList.ge?deptcd="+infoList.getDeptcd());
		}else{
			logger.info("[BoardController] �Խñ��� �Է��� �����Ͽ����ϴ�.");
		}
		
		return mav;
	}
	
	/* �μ��Խ��� �ۻ����ϱ� */
	@RequestMapping(value="/bDDelete")
	public ModelAndView bDDelete(@ModelAttribute BDVO bdvo, HttpServletRequest request, HttpSession httpsession) throws IllegalStateException, IOException{
		logger.info("[BoardController] bDDelete ����");
		int result = 0;
		
		Object infoObj = httpsession.getAttribute("eminfo");
		EmInfoVO infoList = new EmInfoVO();
		infoList = (EmInfoVO)infoObj;
		
		bdvo.setBdno(bdvo.getBdno());
		bdvo.setEmname(infoList.getEmname());
		bdvo.setBdpw(infoList.getEmpw());
		bdvo.setEmno(infoList.getEmno());
		
		logger.info("[BoardController] bdno >>> " + bdvo.getBdno());
		logger.info("[BoardController] bdpw >>> " + bdvo.getBdpw());
		logger.info("[BoardController] emname >>> " + bdvo.getEmname());
		logger.info("[BoardController] emno >>> " + bdvo.getEmno());
		
		ModelAndView mav = new ModelAndView();
		
		result = bDService.boardDepartmentDelete(bdvo);
		logger.info("[BoardController] result >>> " + result);
		
		if(result == 1){
			mav.setViewName("redirect:" + "/board/bDList.ge?deptcd="+infoList.getDeptcd());
		}
		
		return mav;
	}
	
	/* �μ��Խ��� �� �󼼺��� */
	@RequestMapping(value="/bDDetail", method=RequestMethod.POST)
	public ModelAndView bDDetail(@ModelAttribute BDVO bdvo){
		logger.info("[BoardController] bDDetail ȣ�� ����");
		logger.info("[BoardController] deptcd >>> " + bdvo.getDeptcd());
		logger.info("[BoardController] bdno >>> " + bdvo.getBdno());
		
		int viewCntUp = 0;
		
		BDVO detail = null;
		detail = new BDVO();
		detail.setBdno(bdvo.getBdno());
		detail = bDService.boardDepartmentDetail(detail);
		
		ModelAndView mav = new ModelAndView();
		
		if(detail.getBdno() != "" && detail.getBdno() != null){
			
			logger.info("[BoardController] detail - bdno >>> " + detail.getBdno());
			logger.info("[BoardController] detail - emno >>> " + detail.getEmno());
			logger.info("[BoardController] detail - emname >>> " + detail.getEmname());
			
			viewCntUp = bDService.boardDepartmentViewCntUp(bdvo);
			if(viewCntUp==0){
				logger.info("[BoardController] ��ȸ�� ������ ���� �ʾҽ��ϴ�.");
			}else{
				logger.info("[BoardController] ��ȸ�� ���� �Ǿ����ϴ�.");				
			}
			
			mav.setViewName("board/BDDetail");	
			mav.addObject("boardDepartmentDetail", detail);	
		}
	
		return mav;		
	}
	
	/* �μ��Խ��� ���� �� ��� */
	@RequestMapping(value="/bDUForm")
	public ModelAndView bDUForm(@ModelAttribute BDVO bdvo) {
		logger.info("[BoardController] bDUForm ����");
		logger.info("[BoardController] bdno >>> " + bdvo.getBdno());
		
		BDVO boardDepartmentDetail = null;
		boardDepartmentDetail = new BDVO();
		boardDepartmentDetail = bDService.boardDepartmentDetail(bdvo);
		
		ModelAndView mav = new ModelAndView();
		
		if(boardDepartmentDetail != null){
			mav.addObject("boardDepartmentDetail", boardDepartmentDetail);
		}
		mav.setViewName("board/BDUForm");	
		
		return mav;
	}
	
	/* �μ��Խ��� �� �����ϱ� */
	@RequestMapping(value="/bDUdate")
	public ModelAndView bDUdate(@ModelAttribute BDVO bdvo, HttpServletRequest request, HttpSession httpsession) throws IllegalStateException, IOException{
		logger.info("[BoardController] bDUdate ����");
		logger.info("[BoardController] bdno >>> " + bdvo.getBdno());
		
		int result = 0;
		
		Object infoObj = httpsession.getAttribute("eminfo");
		EmInfoVO infoList = new EmInfoVO();
		infoList = (EmInfoVO)infoObj;
		
		bdvo.setBdno(bdvo.getBdno());
		bdvo.setEmname(infoList.getEmname());
		bdvo.setBdpw(infoList.getEmpw());
		bdvo.setEmno(infoList.getEmno());		
		
		result = bDService.boardDepartmentUpdate(bdvo);	
		
		ModelAndView mav = new ModelAndView();
		
		if(result>0){
			logger.info("[BoardController] �Խñ��� �����Ǿ����ϴ�.");
			mav.setViewName("redirect:" + "/board/bDList.ge?deptcd="+infoList.getDeptcd());
		}else{
			logger.info("[BoardController] �Խñ��� ������ �����Ͽ����ϴ�.");		
		}
		
		return mav;
	}
	
	/* �����Խ��� �۾��� */
	@RequestMapping(value="/bTWriteInsert")
	public ModelAndView bTWriteInsert(@ModelAttribute BTVO btvo, HttpServletRequest request, HttpSession httpsession, @ModelAttribute BDVO bdvo) throws IllegalStateException, IOException{
		logger.info("[BoardController] bTWriteInsert ����");
		
		int result = 0;
		String chaebun = "";
		String btfilepath = "../../board_image/task.PNG";
		
		Object infoObj = httpsession.getAttribute("eminfo");
		EmInfoVO infoList = new EmInfoVO();
		infoList = (EmInfoVO)infoObj;
		
		BTVO chae = null;
		chae = new BTVO();
		chae = bTService.boardTaskChebun(chae);
		chaebun = Chaebun.chaebunBT(chae.getBtno());
		logger.info("[BoardController] chae >>> " + chaebun);
		
		btvo.setBtno(chaebun);
		btvo.setDeptcd(infoList.getDeptcd());
		System.out.println("1111btvo.getBtsubject()"+btvo.getBtsubject());
		System.out.println("1111btvo.getBtsubject()"+btvo.getBtsubject());
		if(bdvo.getBdsubject()!=null){
			btvo.setBtsubject(bdvo.getBdsubject());
		}
		btvo.setBtsubject(btvo.getBtsubject());
		System.out.println("2222btvo.getBtsubject()"+btvo.getBtsubject());
		
	
		if(btvo.getBtsubject().length()==0){
			btvo.setBtsubject("���� ����");
		}
		btvo.setEmname(infoList.getEmname());
		btvo.setBtpw(infoList.getEmpw());
		if(bdvo.getBdcontent()!=null){
			btvo.setBtcontent(bdvo.getBdcontent());
		}
		btvo.setBtcontent(btvo.getBtcontent());
		System.out.println("btvo.getBtcontent()" + btvo.getBtcontent().length());
		if(btvo.getBtcontent().length()==0){
			btvo.setBtcontent("���� ����");
		}
		if(bdvo.getBdtaskyn()!=null){
			btvo.setBttaskyn(bdvo.getBdtaskyn());
		}
		btvo.setBttaskyn("Y");
		btvo.setEmno(infoList.getEmno());
		btvo.setJobcd(infoList.getJobcd());
		btvo.setBtfilepath(btfilepath);

		System.out.print("[BoardController] " + btvo.getBtno() + "  ");
		System.out.print("[BoardController] " + btvo.getDeptcd() + "  ");
		System.out.print("[BoardController] " + btvo.getBtsubject() + "  ");
		System.out.print("[BoardController] " + btvo.getEmname() + "  "); 
		System.out.print("[BoardController] " + btvo.getBtpw() + "  ");
		System.out.print("[BoardController] " + btvo.getBtcontent() + "  ");
		System.out.print("[BoardController] " + btvo.getBttaskyn() + "  ");
		System.out.print("[BoardController] " + btvo.getBtfilepath() + "  ");
		System.out.print("[BoardController] " + btvo.getBtvcnt() + "  ");
		System.out.print("[BoardController] " + btvo.getEmno() + "  ");
		System.out.print("[BoardController] " + btvo.getJobcd() + "  ");
		
		ModelAndView mav = new ModelAndView();
		
		result = bTService.boardTaskInsert(btvo);	
		if(result>0){
			logger.info("[BoardController] �Խñ��� �ԷµǾ����ϴ�.");
			mav.setViewName("redirect:" + "/board/bTList.ge?deptcd="+infoList.getDeptcd());
		}else{
			logger.info("[BoardController] �Խñ��� �Է��� �����Ͽ����ϴ�.");		
		}
		
		return mav;
	}
	
	/* �����Խ��� ����Ʈ */
	@RequestMapping(value="/bTList")
	public ModelAndView bTList(@ModelAttribute BTVO btvo, HttpSession httpsession, HttpServletRequest request){
		logger.info("[BoardController] bTList ����");
		Object infoObj = httpsession.getAttribute("eminfo");
		EmInfoVO infoList = new EmInfoVO();
		infoList = (EmInfoVO)infoObj;
		
		logger.info("[BoardController] Emname >>> " + infoList.getEmname());
		logger.info("[BoardController] Deptname >>> " + infoList.getDeptname());
		logger.info("[BoardController] search >>> " + btvo.getSearch());
		logger.info("[BoardController] keyword >>> " + btvo.getKeyword());
		
		BTVO check = null;
		check = new BTVO();
		check.setDeptcd(infoList.getDeptcd());
		
		pageSize =  10;
		String cp = request.getParameter("curPage");
		if(cp!=null){
	    	curPage = Integer.parseInt(cp);
	    }else{
	    	curPage = 1;
	    }
		logger.info("ps + cp :  "+ pageSize + ", " + curPage);
		check.setSearch(btvo.getSearch());
		check.setKeyword(btvo.getKeyword());
		check.setPageSize(pageSize);
		check.setCurPage(curPage);

		List<BTVO> boardTaskList = bTService.boardTaskList(check);	
		int listCnt = boardTaskList.size();
		logger.info("[BoardController] listCnt(=Size) >>> " + listCnt);
		
		for(int i=0; i<listCnt; i++){
			BTVO btVO = null;
			btVO = new BTVO();
			btVO = boardTaskList.get(i);
			pageNo = btVO.getPageNo();
			totalCount = btVO.getTotalCount();
			curPage = btVO.getCurPage();
			groupSize = btVO.getGroupSize();
			logger.info("[BoardController] ����¡�� ������ : pageNo >>> " + pageNo);
			logger.info("[BoardController] ����¡�� ������ : totalCount >>> " + totalCount);
			logger.info("[BoardController] ����¡�� ������ : curPage >>> " + curPage);
			logger.info("[BoardController] ����¡�� ������ : groupSize >>> " + groupSize);
		}
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("pageNo", pageNo);
		mav.addObject("totalCount", totalCount);
		mav.addObject("curPage", curPage);
		mav.addObject("groupSize", groupSize);
		mav.addObject("boardTaskList", boardTaskList);
		mav.addObject("serchData", check);
		mav.setViewName("board/BTList");	
				
		return mav;
	}
	
	/* �����Խ��� �۾��� �� ����ϱ� */
	@RequestMapping(value="/bTWrite")
	public ModelAndView bTWrite(@ModelAttribute BTVO btvo){
		logger.info("[BoardController] bTWrite ����");

		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("board/BTWrite");
		
		return mav;		
	}
	
	/* �����Խ��� �� �󼼺��� */
	@RequestMapping(value="/bTDetail", method=RequestMethod.POST)
	public ModelAndView bTDetail(@ModelAttribute BTVO btvo){
		logger.info("[BoardController] bTDetail ȣ�� ����");
		logger.info("[BoardController] deptcd >>> " + btvo.getDeptcd());
		logger.info("[BoardController] Bdno >>> " + btvo.getBtno());
		int viewCntUp = 0;
		
		BTVO detail = null;
		detail = new BTVO();
		detail.setBtno(btvo.getBtno());
		detail = bTService.boardTaskDetail(detail);
		
		ModelAndView mav = new ModelAndView();
		
		if(detail.getBtno() != "" && detail.getBtno() != null){
			
			logger.info("[BoardController] detail - btno >>> " + detail.getBtno());
			logger.info("[BoardController] detail - emno >>>" + detail.getEmno());
			logger.info("[BoardController] detail - emname >>> " + detail.getEmname());
			
			viewCntUp = bTService.boardTaskViewCntUp(btvo);
			if(viewCntUp==0){
				logger.info("[BoardController] ��ȸ�� ������ ���� �ʾҽ��ϴ�.");
			}else{
				logger.info("[BoardController] ��ȸ�� ���� �Ǿ����ϴ�.");				
			}
			
			mav.setViewName("board/BTDetail");	
			mav.addObject("boardTaskDetail", detail);	

		}
	
		return mav;		
	}
	
	/* �����Խ��� �ۻ����ϱ� */
	@RequestMapping(value="/bTDelete")
	public ModelAndView bTDelete(@ModelAttribute BTVO btvo, HttpServletRequest request, HttpSession httpsession) throws IllegalStateException, IOException{
		logger.info("[BoardController] bTDelete ����");
		int result = 0;
		
		Object infoObj = httpsession.getAttribute("eminfo");
		EmInfoVO infoList = new EmInfoVO();
		infoList = (EmInfoVO)infoObj;
		
		btvo.setBtno(btvo.getBtno());
		btvo.setEmname(infoList.getEmname());
		btvo.setBtpw(infoList.getEmpw());
		btvo.setEmno(infoList.getEmno());
		logger.info("[BoardController] bdno >>> " + btvo.getBtno());
		logger.info("[BoardController] bdpw >>> " + btvo.getBtpw());
		logger.info("[BoardController] emname >>> " + btvo.getEmname());
		logger.info("[BoardController] emno >>> " + btvo.getEmno());
		
		
		result = bTService.boardTaskDelete(btvo);
		logger.info("[BoardController] result >>> " + result);
		
		ModelAndView mav = new ModelAndView();

		if(result == 1){
			mav.setViewName("redirect:" + "/board/bTList.ge?deptcd="+infoList.getDeptcd());
		}
		
		return mav;
	}
	
	/* �����Խ��� ���� �� ��� */
	@RequestMapping(value="/bTUForm")
	public ModelAndView bTUForm(@ModelAttribute BTVO btvo) {
		logger.info("[BoardController] bTUForm ����");
		logger.info("[BoardController] btno >>> " + btvo.getBtno());
		
		BTVO boardTaskDetail = null;
		boardTaskDetail = new BTVO();
		boardTaskDetail = bTService.boardTaskDetail(btvo);
		
		ModelAndView mav = new ModelAndView();
		
		if(boardTaskDetail != null){
			mav.addObject("boardTaskDetail", boardTaskDetail);
		}
		mav.setViewName("board/BTUForm");	
		
		return mav;
	}
	
	/* �����Խ��� �� �����ϱ� */
	@RequestMapping(value="/bTUdate")
	public ModelAndView bTUdate(@ModelAttribute BTVO btvo, HttpServletRequest request, HttpSession httpsession) throws IllegalStateException, IOException{
		logger.info("[BoardController] bTUdate ����");
		logger.info("[BoardController] btvo >>> " + btvo.getBtno());
		
		int result = 0;
		
		Object infoObj = httpsession.getAttribute("eminfo");
		EmInfoVO infoList = new EmInfoVO();
		infoList = (EmInfoVO)infoObj;
		
		btvo.setBtno(btvo.getBtno());
		btvo.setEmname(infoList.getEmname());
		btvo.setBtpw(infoList.getEmpw());
		btvo.setEmno(infoList.getEmno());		
		
		result = bTService.boardTaskUpdate(btvo);
		
		ModelAndView mav = new ModelAndView();
		
		if(result>0){
			logger.info("[BoardController] �Խñ��� �����Ǿ����ϴ�.");
			mav.setViewName("redirect:" + "/board/bTList.ge?deptcd="+infoList.getDeptcd());
		}else{
			logger.info("[BoardController] �Խñ��� ������ �����Ͽ����ϴ�.");	
		}
		
		return mav;
	}
	
	/* �������װԽ��� ����Ʈ */
	@RequestMapping(value="/bNList")
	public ModelAndView bNList(@ModelAttribute BNVO bnvo, HttpSession httpsession, HttpServletRequest request){
		logger.info("[BoardController] bNList ����");
		
		Object infoObj = httpsession.getAttribute("eminfo");
		EmInfoVO infoList = new EmInfoVO();
		infoList = (EmInfoVO)infoObj;		
		logger.info("[BoardController] Emname >>> " + infoList.getEmname());
		logger.info("[BoardController] Deptname >>> " + infoList.getDeptname());
		logger.info("[BoardController] search >>> " + bnvo.getSearch());
		logger.info("[BoardController] keyword >>> " + bnvo.getKeyword());
		
		pageSize =  10;
		String cp = request.getParameter("curPage");
		if(cp!=null){
	    	curPage = Integer.parseInt(cp);
	    }else{
	    	curPage = 1;
	    }
		logger.info("[BoardController] ps >>> " + pageSize);
		logger.info("[BoardController] cp >>> " + curPage);

		bnvo.setSearch(bnvo.getSearch());
		bnvo.setKeyword(bnvo.getKeyword());
		bnvo.setPageSize(pageSize);
		bnvo.setCurPage(curPage);
		
		List<BNVO> boardNoticeList = bNService.boardNoticeList(bnvo);		
		int listCnt = boardNoticeList.size();
		logger.info("[BoardController] listCnt(=Size) >>> " + listCnt);
		
		for(int i=0; i<listCnt; i++){
			BNVO bnVO = null;
			bnVO = new BNVO();
			bnVO = boardNoticeList.get(i);
			pageNo = bnVO.getPageNo();
			totalCount = bnVO.getTotalCount();
			curPage = bnVO.getCurPage();
			groupSize = bnVO.getGroupSize();
			logger.info("[BoardController] ����¡�� ������ : pageNo >>> " + pageNo);
			logger.info("[BoardController] ����¡�� ������ : totalCount >>> " + totalCount);
			logger.info("[BoardController] ����¡�� ������ : curPage >>> " + curPage);
			logger.info("[BoardController] ����¡�� ������ : groupSize >>> " + groupSize);
		}
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("pageNo", pageNo);
		mav.addObject("totalCount", totalCount);
		mav.addObject("curPage", curPage);
		mav.addObject("groupSize", groupSize);
		mav.addObject("boardNoticeList", boardNoticeList);
		mav.addObject("serchData", bnvo);
		mav.setViewName("board/BNList");	
				
		return mav;
	}
	
	/* �������װԽ��� �۾��� �� ����ϱ� */
	@RequestMapping(value="/bNWrite")
	public ModelAndView bNWriteForm(@ModelAttribute BNVO bnvo){
		logger.info("[BoardController] bNWrite ����");
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("board/BNWrite");
	
		return mav;		
	}
	
	/* �������װԽ��� �۾��� */
	@RequestMapping(value="/bNWriteInsert")
	public ModelAndView bNWriteInsert(@ModelAttribute BNVO bnvo, HttpServletRequest request, HttpSession httpsession) throws IllegalStateException, IOException{
		logger.info("[BoardController] bNWriteInsert ����");
		
		int result = 0;
		String chaebun = "";
		String bnfilepath = "../../board_image/notice.PNG"; 
		
		Object infoObj = httpsession.getAttribute("eminfo");
		EmInfoVO infoList = new EmInfoVO();
		infoList = (EmInfoVO)infoObj;
		
		BNVO chae = null;
		chae = new BNVO();
		chae = bNService.boardNoticeChebun(chae);
		chaebun = Chaebun.chaebunBN(chae.getBnno());
		logger.info("[BoardController] chae >>> " + chaebun);
				
		bnvo.setBnno(chaebun);
		bnvo.setDeptcd(infoList.getDeptcd());
		if(bnvo.getBnsubject().length()==0){
			bnvo.setBnsubject("���� ����");
		}
		bnvo.setEmname(infoList.getEmname());
		bnvo.setBnpw(infoList.getEmpw());
		if(bnvo.getBncontent().length()==0){
			bnvo.setBncontent("���� ����");
		}
		bnvo.setEmno(infoList.getEmno());
		bnvo.setJobcd(infoList.getJobcd());
		bnvo.setBnfilepath(bnfilepath);

		System.out.print("[BoardController] " + bnvo.getBnno() + "  ");
		System.out.print("[BoardController] " + bnvo.getDeptcd() + "  ");
		System.out.print("[BoardController] " + bnvo.getBnsubject() + "  ");
		System.out.print("[BoardController] " + bnvo.getEmname() + "  "); 
		System.out.print("[BoardController] " + bnvo.getBnpw() + "  ");
		System.out.print("[BoardController] " + bnvo.getBncontent() + "  ");
		System.out.print("[BoardController] " + bnvo.getBnfilepath() + "  ");
		System.out.print("[BoardController] " + bnvo.getBnvcnt() + "  ");
		System.out.print("[BoardController] " + bnvo.getEmno() + "  ");
		System.out.print("[BoardController] " + bnvo.getJobcd() + "  ");
		
		ModelAndView mav = new ModelAndView();
		
		result = bNService.boardNoticeInsert(bnvo);	
		if(result>0){
			logger.info("[BoardController] �Խñ��� �ԷµǾ����ϴ�.");
			mav.setViewName("redirect:" + "/board/bNList.ge");
		}else{
			logger.info("[BoardController] �Խñ��� �Է��� �����Ͽ����ϴ�.");
		}
		
		return mav;
	}
	
	/* �������װԽ��� �� �󼼺��� */
	@RequestMapping(value="/bNDetail", method=RequestMethod.POST)
	public ModelAndView bNDetail(@ModelAttribute BNVO bnvo){
		logger.info("[BoardController] bNDetail ȣ�� ����");
		logger.info("[BoardController] deptcd >> " + bnvo.getDeptcd());
		logger.info("[BoardController] bnno >> " + bnvo.getBnno());
		
		int viewCntUp = 0;
		
		BNVO detail = null;
		detail = new BNVO();
		detail.setBnno(bnvo.getBnno());
		detail = bNService.boardNoticeDetail(detail);
		
		ModelAndView mav = new ModelAndView();
		
		if(detail.getBnno() != "" && detail.getBnno() != null){
			
			logger.info("[BoardController] detail - bnno >>> " + detail.getBnno());
			logger.info("[BoardController] detail - emno >>> " + detail.getEmno());
			logger.info("[BoardController] detail - emname >>> " + detail.getEmname());
			
			viewCntUp = bNService.boardNoticeViewCntUp(bnvo);
			if(viewCntUp==0){
				logger.info("[BoardController] ��ȸ�� ������ ���� �ʾҽ��ϴ�.");
			}else{
				logger.info("[BoardController] ��ȸ�� ���� �Ǿ����ϴ�.");				
			}
			
			mav.setViewName("board/BNDetail");	
			mav.addObject("boardNoticeDetail", detail);	
		}
	
		return mav;		
	}
	
	/* �������װԽ��� �ۻ����ϱ� */
	@RequestMapping(value="/bNDelete")
	public ModelAndView bNDelete(@ModelAttribute BNVO bnvo, HttpSession httpsession) throws IllegalStateException, IOException{
		logger.info("[BoardController] bNDelete ����");
		int result = 0;
		
		Object infoObj = httpsession.getAttribute("eminfo");
		EmInfoVO infoList = new EmInfoVO();
		infoList = (EmInfoVO)infoObj;
		
		bnvo.setBnno(bnvo.getBnno());
		bnvo.setEmname(infoList.getEmname());
		bnvo.setBnpw(infoList.getEmpw());
		bnvo.setEmno(infoList.getEmno());
		
		
		logger.info("[BoardController] bnno >>> " + bnvo.getBnno());
		logger.info("[BoardController] bnpw >>> " + bnvo.getBnpw());
		logger.info("[BoardController] emname >>> " + bnvo.getEmname());
		logger.info("[BoardController] emno >>> " + bnvo.getEmno());

		ModelAndView mav = new ModelAndView();
		
		result = bNService.boardNoticeDelete(bnvo);
		logger.info("[BoardController] result >>> " + result);

		if(result == 1){
			mav.setViewName("redirect:" + "/board/bNList.ge");
		}
		
		return mav;
	}
	
	/* �������װԽ��� ���� �� ��� */
	@RequestMapping(value="/bNUForm")
	public ModelAndView bNUForm(@ModelAttribute BNVO bnvo) {
		logger.info("[BoardController] bNUForm ����");
		logger.info("[BoardController] bdno >>>> " + bnvo.getBnno());

		ModelAndView mav = new ModelAndView();
		
		BNVO boardNoticeDetail = bNService.boardNoticeDetail(bnvo);
		
		if(boardNoticeDetail != null){
			mav.addObject("boardNoticeDetail", boardNoticeDetail);
		}
		mav.setViewName("board/BNUForm");	
		
		return mav;
	}
	
	/* �������װԽ��� �� �����ϱ� */
	@RequestMapping(value="/bNUdate")
	public ModelAndView bNUdate(@ModelAttribute BNVO bnvo, HttpServletRequest request, HttpSession httpsession) throws IllegalStateException, IOException{
		logger.info("[BoardController] bNUdate ����");
		logger.info("[BoardController] bnvo >>> " + bnvo.getBnno());
		
		int result = 0;
		
		Object infoObj = httpsession.getAttribute("eminfo");
		EmInfoVO infoList = new EmInfoVO();
		infoList = (EmInfoVO)infoObj;
		
		bnvo.setBnno(bnvo.getBnno());
		bnvo.setEmname(infoList.getEmname());
		bnvo.setBnpw(infoList.getEmpw());
		bnvo.setEmno(infoList.getEmno());		
		
		result = bNService.boardNoticeUpdate(bnvo);	
		
		ModelAndView mav = new ModelAndView();
		
		if(result>0){
			logger.info("[BoardController] �Խñ��� �����Ǿ����ϴ�.");
			mav.setViewName("redirect:" + "/board/bNList.ge");
		}else{
			logger.info("[BoardController] �Խñ��� ������ �����Ͽ����ϴ�.");	
		}
		
		return mav;
	}
	
	/* �������� �̵� */
	@RequestMapping(value="/calendar")
	public ModelAndView calendar(){
		logger.info("[BoardController] calendar ����");
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("calendar/CalendarMain");	
		
		return mav;		
	}
	
	/* �α׾ƿ� �� ó�� ȭ������ ���ư���*/
	@RequestMapping(value="/geLogOut")
	public ModelAndView geLogOut(HttpSession httpsession){
		logger.info("[BoardController] geLogOut ����");
		
		httpsession.invalidate();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("../../index");	
				
		return mav;
	}
}
