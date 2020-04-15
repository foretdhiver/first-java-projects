package com.spring.ge.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ge.common.Chaebun;
import com.spring.ge.service.EaService_;
import com.spring.ge.vo.EaVO_;
import com.spring.ge.vo.EmInfoVO;

@RequestMapping(value="/ea_")
@Controller
public class EaController_ {
	Logger logger = Logger.getLogger(EaController_.class);
	
	// ������ �̵��� �� ���
	private int pageSize = 0;
	private int groupSize = 0;
	private int curPage = 0;
	private int totalCount = 0;
	private int pageNo = 0;
	
	@Autowired
	private EaService_ eaService;
	
	@RequestMapping(value="/loginCheck", method=RequestMethod.POST)
	public ModelAndView loginCheck(@ModelAttribute EmInfoVO evo, HttpSession session){
      logger.info("loginCheck ����");
      
      String emid = "";
      String empw = "";
      emid = evo.getEmid();
      empw = evo.getEmpw();
      logger.info("[log] emid : " + emid);
      logger.info("[log] empw : " + empw);
      
      EmInfoVO emvo = new EmInfoVO();
      emvo = new EmInfoVO();
      
      emvo.setEmid(emid);
      emvo.setEmpw(empw);
      
      EmInfoVO infoList = new EmInfoVO();
      infoList = eaService.emInfoList(emvo);
      logger.info("[log] emname : " + infoList.getEmname());
      logger.info("[log] emid : " + infoList.getEmid());
      logger.info("[log] empw : " + infoList.getEmpw());
      logger.info("[log] deptcd : " + infoList.getDeptcd());
      logger.info("[log] deptname : " + infoList.getDeptname());
      
      EaVO_ eavo = null;
      eavo = new EaVO_();
      
      String emno = infoList.getEmno();
      logger.info("emno Ȯ�� : " + emno);
      eavo.setEmno(emno);
      eavo.setEa_empno(emno);
      
      List<EaVO_> appList = eaService.eaMainAppList(eavo);
      List<EaVO_> eamyList = eaService.eaMaineaList(eavo);
            
      if(emvo != null){
          System.out.println("���̵�� ��й�ȣ�� ��ġ�ϴ� ȸ���� �����մϴ�.");
          session.setAttribute("eminfo", infoList);
       }
       
       ModelAndView mav = new ModelAndView();
       mav.setViewName("gemain");
       mav.addObject("eminfo", infoList);
       mav.addObject("eaListAP", appList);
       mav.addObject("eaListALL", eamyList);
             
      return mav;
   }
	
	@RequestMapping(value="gotoGEMain")
	public ModelAndView gotoGEMain(@ModelAttribute EmInfoVO evo, HttpSession session){
		logger.info("[log] EaContoroller.gotoGEMain ����");
		
		Object obj = session.getAttribute("eminfo");
		EmInfoVO em_info = new EmInfoVO();
		em_info = (EmInfoVO)obj;
		
		EaVO_ eavo = null;
		eavo = new EaVO_();
	      
		String emno = em_info.getEmno();
		logger.info("emno Ȯ�� : " + emno);
		eavo.setEmno(emno);
		eavo.setEa_empno(emno);
		 
		List<EaVO_> appList = eaService.eaMainAppList(eavo);
	    List<EaVO_> mainEaList = eaService.eaMaineaList(eavo);
		        
		if(em_info != null){
			System.out.println("�������� ����Ȯ�� �Ϸ�");
			session.setAttribute("eminfo", em_info);
		}
		   
		ModelAndView mav = new ModelAndView();
		mav.setViewName("gemain");
		mav.addObject("eminfo", em_info);
		mav.addObject("eaListAP", appList);
		mav.addObject("eaListALL", mainEaList);
		
		logger.info("[log] EaContoroller.gotoGEMain ��");
		return mav;
	}
	
	@RequestMapping(value="eamain")
	public ModelAndView gotoeaMain(@ModelAttribute EmInfoVO evo, HttpSession session){
		logger.info("[log] EaContoroller.gotoeaMain ����");
		
		Object obj = session.getAttribute("eminfo");
		EmInfoVO em_info = new EmInfoVO();
		em_info = (EmInfoVO)obj;
		
		EaVO_ eavo = null;
		eavo = new EaVO_();
	      
		String emno = em_info.getEmno();
		logger.info("emno Ȯ�� : " + emno);
		eavo.setEmno(emno);
		eavo.setEa_empno(emno);
		 
		List<EaVO_> appList = eaService.eaMainAppList(eavo);
		List<EaVO_> rjList = eaService.eaMainRJList(eavo);
		List<EaVO_> pgList = eaService.eaMainPGList(eavo);
		List<EaVO_> stList = eaService.eaMainSTList(eavo);
		        
		if(em_info != null){
			System.out.println("�������� ����Ȯ�� �Ϸ�");
			session.setAttribute("eminfo", em_info);
		}
		   
		ModelAndView mav = new ModelAndView();
		mav.setViewName("ea/eaMain_");
		mav.addObject("eminfo", em_info);
		mav.addObject("appList", appList);
		mav.addObject("rjList", rjList);
		mav.addObject("pgList", pgList);
		mav.addObject("stList", stList);
		
		logger.info("[log] EaContoroller.gotoeaMain ��");
		return mav;
	}
	
	/* session logout */
    @RequestMapping(value="/logOut")
    public ModelAndView logOut(@ModelAttribute EmInfoVO evo, HttpSession session){
       logger.info("logOut ����");
       session.invalidate();
       ModelAndView mav = new ModelAndView();
        mav.setViewName("/");
        logger.info("logOut ����");
       return mav;
    }
	
	@RequestMapping(value="gotoInsert")
	public ModelAndView gotoInsert(@ModelAttribute EaVO_ param, HttpSession session){
		logger.info("[log] EaContoroller.gotoInsert ����");
		
		Object obj = session.getAttribute("eminfo");
		EmInfoVO em_info = new EmInfoVO();
		em_info = (EmInfoVO)obj;
		String emno = em_info.getEmno();
		logger.info("[log] ��� Ȯ�� : " + emno);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("eminfo", em_info);
		mav.setViewName("ea/lineInsert");
		
		logger.info("[log] EaContoroller.gotoInsert ��");
		return mav;
	}
	
	@RequestMapping(value="eaInsertLine")
	public ModelAndView eaInsertLine(@ModelAttribute EaVO_ param, 
									 HttpServletRequest request, 
									 HttpSession session){
		logger.info("[log] EaContoroller.eaInsertLine ����");
		
		/*
		 * HttpServletRequest : getParameter() ����ϴ� �뵵
		 * HttpSession : session
		 */
		
		Object obj = session.getAttribute("eminfo");
		EmInfoVO em_info = new EmInfoVO();
		em_info = (EmInfoVO)obj;
		String emno = em_info.getEmno();
		logger.info("[log] ������ ���� �� ����Ϸ��� ��� Ȯ�� : " + emno);
		param.setEmno(emno);
		logger.info("[log] ������ ���� �� ����Ϸ��� ������ Ȯ�� : " + param.getEmno());

		String send = request.getParameter("send");
		System.out.println("send Ȯ�� : " + send);
		String linename = param.getEa_linename();
		System.out.println("[log] �̸� Ȯ�� : " + linename);

		EaVO_ chaebun = null;
		chaebun = eaService.chaebunLine(chaebun);
		String ea_lineno = Chaebun.chaebunEAL(chaebun.getEa_lineno());
		param.setEa_lineno(ea_lineno);
		logger.info("[log] ��ä�� Ȯ�� : " + ea_lineno);
		
		int result = 0;
		ModelAndView mav = new ModelAndView();
		
		result = eaService.eaInsertLine(param);
		
		logger.info("[log] ���缱��ȣ Ȯ�� : " + param.getEa_lineno());
		logger.info("[log] ���缱�̸� Ȯ�� : " + param.getEa_linename());

		if(result == 1){
			mav.addObject("ea_lineno", ea_lineno);
			mav.addObject("send", send);
			mav.setViewName("ea/stepInsert");
			logger.info("���Ծ��");
		}
		
		logger.info("[log] EaContoroller.insertLine ��");
		return mav;
	}
	
	// text.jsp���� ù��° select ĭ ������ ��ȸ�ϴ� �Լ�
	@RequestMapping(value="getDeptList")
	@ResponseBody
	public List<EmInfoVO> getDeptList(@ModelAttribute EmInfoVO param,
									HttpServletRequest request,
									HttpSession session){
		logger.info("[log] EaContoroller.getDeptList ����");
		
		List<EmInfoVO> list = eaService.searchDeptList(param);
		logger.info("list : " + list);
		int listSize = list.size();
		logger.info("listSize : " + listSize);
		
		logger.info("[log] EaContoroller.getDeptList ��");
		
		return list;
	}
	
	// text.jsp���� �ι�° select ĭ ������ ��ȸ�ϴ� �Լ�
	@RequestMapping(value="getJobList")
	@ResponseBody
	public List<EmInfoVO> getJobList(@ModelAttribute EmInfoVO param,
									HttpServletRequest request,
									HttpSession session){
		logger.info("[log] EaContoroller.getJobList ����");
		
		String deptcd = param.getDeptcd();
		logger.info("deptcd : " + deptcd);
		
		EmInfoVO emvo = new EmInfoVO();
		emvo.setDeptcd(deptcd);
		
		List<EmInfoVO> list = eaService.searchJobList(emvo);
		logger.info("[log] EaContoroller.getJobList ��");
		
		int listSize = list.size();
		logger.info("listSize : " + listSize);
		
		return list;
	}
	
	// text.jsp���� ����° select ĭ ������ ��ȸ�ϴ� �Լ�
	@RequestMapping(value="getEmnameList")
	@ResponseBody
	public List<EmInfoVO> getEmnameList(@ModelAttribute EmInfoVO param,
									HttpServletRequest request,
									HttpSession session){
		logger.info("[log] EaContoroller.getEmnameList ����");
		
		String deptcd = param.getDeptcd();
		String jobcd = param.getJobcd();
		
		logger.info("deptcd : jobcd " + deptcd + " : " + jobcd);
		
		EmInfoVO emvo = new EmInfoVO();
		emvo.setDeptcd(deptcd);
		emvo.setJobcd(jobcd);
		
		List<EmInfoVO> list = eaService.searchEmnameList(emvo);
		logger.info("list : " + list);
		
		int listSize = list.size();
		logger.info("listSize : " + listSize);
		
		logger.info("[log] EaContoroller.getEmnameList ��");
		
		return list;
	}
	
	@RequestMapping(value="eaInsertStep")
	public ModelAndView eaInsertStep(@ModelAttribute EaVO_ param, 
									 HttpServletRequest request, 
									 HttpSession session){
		logger.info("[log] EaContoroller.eaInsertStep ����");
		
		Object obj = session.getAttribute("eminfo");
		EmInfoVO em_info = new EmInfoVO();
		em_info = (EmInfoVO)obj;
		
		String ea_lineno = "";
		String send = "";
		
		EaVO_ chaebun = null;
		chaebun = eaService.chaebunStep(chaebun);
		String ea_stepno = Chaebun.chaebunEASTNO(chaebun.getEa_stepno());
		param.setEa_stepno(ea_stepno);
		logger.info("[log] ä�� Ȯ�� : " + param.getEa_stepno());
		
		ea_lineno = param.getEa_lineno();
		send = request.getParameter("send");
		
		logger.info("[log] �� Ȯ�� : " + ea_lineno);
		logger.info("[log] send : " + send);
		
		String empno = param.getEa_empno();
		String empno1 = param.getEa_empno1();
		String empno2 = param.getEa_empno2();
		
		logger.info("[log] empno : " + empno + " : " + empno1 + " : " + empno2);
		
		int result = 0;
		ModelAndView mav = new ModelAndView();
		
		result = eaService.eaInsertStep(param);
		
		if(result == 1){
			mav.addObject("ea_lineno", ea_lineno);
			mav.addObject("ea_stepno", param.getEa_stepno());
			mav.addObject("ea_empno", empno);
			mav.addObject("ea_empno1", empno1);
			mav.addObject("ea_empno2", empno2);
			mav.addObject("send", send);
			mav.setViewName("ea/stepResult");
			logger.info("���Ծ��");
		}
		
		logger.info("[log] EaContoroller.eaInsertStep ��");
		return mav;
	}
	
	@RequestMapping(value="gotoEaForm")
	public ModelAndView gotoEaForm (@ModelAttribute EaVO_ param, HttpServletRequest request, HttpSession session){
		logger.info("[log] EaContoroller.gotoEaForm ����");
		
		Object obj = session.getAttribute("eminfo");
		EmInfoVO em_info = new EmInfoVO();
		em_info = (EmInfoVO)obj;
		
		String ea_lineno = null;
		String ea_stepno = null;
		String empno = null;
		String empno1 = null;
		String empno2 = null;
		String send = null;
		
		ea_lineno = param.getEa_lineno();
		ea_stepno = param.getEa_stepno();
		empno = param.getEa_empno();
		empno1 = param.getEa_empno1();
		empno2 = param.getEa_empno2();
		send = request.getParameter("send");
		
		System.out.println("[log] ea_lineno ������ Ȯ�� : " + ea_lineno);
		System.out.println("[log ]ea_stepno ������ Ȯ�� : " + ea_stepno);
		System.out.println("[log] empno ������ Ȯ�� : " + empno);
		System.out.println("[log] empno1 ������ Ȯ�� : " + empno1);
		System.out.println("[log] empno2 ������ Ȯ�� : " + empno2);
		System.out.println("[log] send ������ Ȯ�� : " + send);
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("ea_lineno", ea_lineno);
		mav.addObject("ea_stepno", ea_stepno);
		mav.addObject("ea_empno", empno);
		mav.addObject("ea_empno1", empno1);
		mav.addObject("ea_empno2", empno2);
		mav.addObject("send", send);
		mav.setViewName("ea/eaForm");
		
		logger.info("[log] EaContoroller.gotoEaForm ��");
		return mav;
	}
	
	@RequestMapping(value="eaInsertEa")
	public ModelAndView eaInsertEa(@ModelAttribute EaVO_ param, 
									 HttpServletRequest request, 
									 HttpSession session){
		logger.info("[log] EaContoroller.eaInsertEa ����");
		
		Object obj = session.getAttribute("eminfo");
		EmInfoVO em_info = new EmInfoVO();
		em_info = (EmInfoVO)obj;
		
		String ea_lineno = "";
		String ea_stepno = "";
		String ea_empno = "";
		String ea_doccd = "";
		String ea_nextemno = "";
		
		EaVO_ chaebun = null;
		chaebun = eaService.chaebunLog(chaebun);
		
		String ea_logno = Chaebun.chaebunEALOG(chaebun.getEa_logno());
		param.setEa_logno(ea_logno);
		logger.info("[log] �α� ä�� Ȯ�� : " + param.getEa_logno());
		
		// ���繮�� ä���� ���⼭ �����
		chaebun = eaService.chaebunTable(chaebun); // �ִ� VO ��Ȱ��
		
		String ea_no = Chaebun.chaebunEA(chaebun.getEa_no());
		param.setEa_no(ea_no);
		logger.info("[log] ���̺� ä�� Ȯ�� : " + param.getEa_no());
		
		ea_lineno = param.getEa_lineno();
		ea_stepno = param.getEa_stepno();
		ea_empno = param.getEa_empno();
		ea_doccd = param.getEa_doccd();
		logger.info("[log] ea_lineno Ȯ�� : " + ea_lineno);
		logger.info("[log] ea_stepno Ȯ�� : " + ea_stepno);
		logger.info("[log] ea_empno Ȯ�� : " + ea_empno);
		logger.info("[log] ea_doccd Ȯ�� : " + ea_doccd);
		
		int result = 0;
		
		ModelAndView mav = new ModelAndView();
		result = eaService.eaInsertLog(param);
		if(result == 1){
			mav.addObject("ea_logno", param.getEa_logno());
			mav.addObject("ea_lineno", ea_lineno);
			mav.addObject("ea_stepno", ea_stepno);
			mav.addObject("ea_empno", ea_empno);
			logger.info("�α� �ױ� ����");
		}
		
		// ���� ������ ����
		EaVO_ evo1 = new EaVO_();
		evo1.setEa_stepno(ea_stepno);
		List<EaVO_> nextEmno = eaService.nextEmno(evo1);
		
		logger.info("nextEmno : " + nextEmno);
		logger.info("nextEmno.size : " + nextEmno.size());
		for(int i=0; i < nextEmno.size(); i++){
			EaVO_ eavo = new EaVO_();
			eavo = (EaVO_) nextEmno.get(i);
			ea_nextemno = eavo.getEa_empno1();
			param.setEa_nextemno(ea_nextemno);
		}
		logger.info("ea_nextemno : " + ea_nextemno);
		if("H".equals(ea_doccd)){ // ���̺� �μ�Ʈ
			logger.info("[log] ea_lineno : " + ea_lineno);
			logger.info("[log] ea_logno : " + ea_logno);
			logger.info("[log] ea_no : " + ea_no);
			logger.info("[log] ea_subject : " + param.getEa_subject());
			logger.info("[log] EA_MEMO : " + param.getEa_memo());
			logger.info("[log] EACNO : " + param.getEacno());
			logger.info("[log] EA_EMPNO : " + ea_empno);
			result = eaService.eaInsertH(param);
			if(result == 1){
				mav.setViewName("ea/gotoGEMain");
				mav.addObject("eminfo", em_info);
				logger.info("�ް��� �ۼ� ����");
			}
		}else if("P".equals(ea_doccd)){ // ���̺� �μ�Ʈ
			logger.info("[log] ea_lineno : " + ea_lineno);
			logger.info("[log] ea_logno : " + ea_logno);
			logger.info("[log] ea_no : " + ea_no);
			logger.info("[log] ea_subject : " + param.getEa_subject());
			logger.info("[log] EA_MEMO : " + param.getEa_memo());
			logger.info("[log] EACNO : " + param.getEacno());
			logger.info("[log] EA_EMPNO : " + ea_empno);
			result = eaService.eaInsertP(param);
			if(result == 1){
				EaVO_ eavo = null;
				eavo = new EaVO_();
			      
				String emno = em_info.getEmno();
				logger.info("emno Ȯ�� : " + emno);
				eavo.setEmno(emno);
				eavo.setEa_empno(emno);
				 
				List<EaVO_> appList = eaService.eaMainAppList(eavo);
				List<EaVO_> rjList = eaService.eaMainRJList(eavo);
				List<EaVO_> pgList = eaService.eaMainPGList(eavo);
				List<EaVO_> stList = eaService.eaMainSTList(eavo);
			    
				mav.setViewName("ea/eaMain_");
				mav.addObject("eminfo", em_info);
				mav.addObject("appList", appList);
				mav.addObject("rjList", rjList);
				mav.addObject("pgList", pgList);
				mav.addObject("stList", stList);
				logger.info("������Ʈ ��ȼ� �ۼ� ����");
			}
		}else{
			System.out.println("���̺� �μ�Ʈ ����");
		}
		logger.info("[log] EaContoroller.eaInsertEa ��");
		return mav;
	}
	
	@RequestMapping("/eaWriteAll")
	public ModelAndView eaWriteAll(@ModelAttribute EaVO_ param, HttpServletRequest request, HttpSession session){
		logger.info("[log] EaContoroller.eaWriteAll ����");

		Object obj = session.getAttribute("eminfo");
		EmInfoVO em_info = new EmInfoVO();
		em_info = (EmInfoVO) obj;
		String emname = em_info.getEmname();
		String emno = em_info.getEmno();
		
		param.setEmno(emno);
		logger.info("emname : " + emname);
		logger.info("emno : " + emno);
		
		pageSize =  10;
	    String cp = request.getParameter("curPage");
	    if(cp!=null){
	    	curPage = Integer.parseInt(cp);
	    }else{
	    	curPage = 1;
	    }
	    logger.info("gs + ps + cp : " + pageSize + ", " + curPage);
		param.setPageSize(pageSize);
		param.setCurPage(curPage);
		
		List<EaVO_> eaWriteAll = eaService.eaWriteAll(param);
		ModelAndView mav = new ModelAndView();
		
		logger.info("eaWriteAll : " + eaWriteAll);
		logger.info("eaWriteAll.size : " + eaWriteAll.size());
		
		for(int i=0; i < eaWriteAll.size(); i++){
			EaVO_ evo = null;
			evo = new EaVO_();
			evo = eaWriteAll.get(i);
			pageNo = evo.getPageNo();
			totalCount = evo.getTotalCount();
			curPage = evo.getCurPage();
			groupSize = evo.getGroupSize();
			logger.info("����¡ ������ Ȯ�� : " + pageNo + ":" + totalCount + ":" + curPage + ":" + groupSize);
		}
		
		mav.addObject("eaWriteAll", eaWriteAll);
		mav.addObject("pageNo", pageNo);
		mav.addObject("totalCount", totalCount);
		mav.addObject("curPage", curPage);
		mav.addObject("groupSize", groupSize);
		mav.setViewName("ea/eaWriteAll");
		logger.info("[log] EaContoroller.eaWriteAll ��");
		return mav;
	}
	
	// ���� ��µ� ������
	@RequestMapping("/eaWritePG")
	public ModelAndView eaWritePG(@ModelAttribute EaVO_ param, HttpServletRequest request, HttpSession session){
		logger.info("[log] EaContoroller.eaWritePG ����");

		Object obj = session.getAttribute("eminfo");
		EmInfoVO em_info = new EmInfoVO();
		em_info = (EmInfoVO) obj;
		String emname = em_info.getEmname();
		String emno = em_info.getEmno();
		
		logger.info("emname : " + emname);
		logger.info("emno : " + emno);
		
		pageSize =  10;
	    String cp = request.getParameter("curPage");
	    if(cp!=null){
	    	curPage = Integer.parseInt(cp);
	    }else{
	    	curPage = 1;
	    }
	    
	    param.setEmno(emno);
		logger.info("gs + ps + cp : " + pageSize + ", " + curPage);
		param.setPageSize(pageSize);
		param.setCurPage(curPage);
		
		List<EaVO_> eaWritePG = eaService.eaWritePG(param);
		ModelAndView mav = new ModelAndView();
		
		logger.info("eaWritePG : " + eaWritePG);
		logger.info("eaWritePG.size : " + eaWritePG.size());
		
		for(int i=0; i < eaWritePG.size(); i++){
			EaVO_ evo = null;
			evo = new EaVO_();
			evo = eaWritePG.get(i);
			pageNo = evo.getPageNo();
			totalCount = evo.getTotalCount();
			curPage = evo.getCurPage();
			groupSize = evo.getGroupSize();
			logger.info("����¡ ������ Ȯ�� : " + pageNo + ":" + totalCount + ":" + curPage + ":" + groupSize);
		}
		
		mav.addObject("eaWritePG", eaWritePG);
		mav.addObject("pageNo", pageNo);
		mav.addObject("totalCount", totalCount);
		mav.addObject("curPage", curPage);
		mav.addObject("groupSize", groupSize);
		mav.setViewName("ea/eaWritePG");
		logger.info("[log] EaContoroller.eaWritePG ��");
		return mav;
	}
	
	// ���� ��µ� �����Ϸ�
	@RequestMapping("/eaWriteEnd")
	public ModelAndView eaWriteEnd(@ModelAttribute EaVO_ param, HttpServletRequest request, HttpSession session){
		logger.info("[log] EaContoroller.eaWriteEnd ����");

		Object obj = session.getAttribute("eminfo");
		EmInfoVO em_info = new EmInfoVO();
		em_info = (EmInfoVO) obj;
		String emname = em_info.getEmname();
		String emno = em_info.getEmno();
		
		pageSize =  10;
	    String cp = request.getParameter("curPage");
	    if(cp!=null){
	    	curPage = Integer.parseInt(cp);
	    }else{
	    	curPage = 1;
	    }
	    logger.info("gs + ps + cp : " + pageSize + ", " + curPage);
		param.setPageSize(pageSize);
		param.setCurPage(curPage);
		
		param.setEmno(emno);
		logger.info("emname : " + emname);
		logger.info("emno : " + emno);
		
		List<EaVO_> eaWriteEnd = eaService.eaWriteEnd(param);
		ModelAndView mav = new ModelAndView();
		
		logger.info("eaWriteEnd : " + eaWriteEnd);
		logger.info("eaWriteEnd.size : " + eaWriteEnd.size());
		
		for(int i=0; i < eaWriteEnd.size(); i++){
			EaVO_ evo = null;
			evo = new EaVO_();
			evo = eaWriteEnd.get(i);
			pageNo = evo.getPageNo();
			totalCount = evo.getTotalCount();
			curPage = evo.getCurPage();
			groupSize = evo.getGroupSize();
			logger.info("����¡ ������ Ȯ�� : " + pageNo + ":" + totalCount + ":" + curPage + ":" + groupSize);
		}
		
		mav.addObject("eaWriteEnd", eaWriteEnd);
		mav.addObject("pageNo", pageNo);
		mav.addObject("totalCount", totalCount);
		mav.addObject("curPage", curPage);
		mav.addObject("groupSize", groupSize);
		mav.setViewName("ea/eaWriteEnd");
		logger.info("[log] EaContoroller.eaWriteEnd ��");
		return mav;
	}
	
	// ���� ��µ� �ݷ�
	@RequestMapping("/eaWriteRJ")
	public ModelAndView eaWriteRJ(@ModelAttribute EaVO_ param, HttpServletRequest request, HttpSession session){
		logger.info("[log] EaContoroller.eaWriteRJ ����");

		Object obj = session.getAttribute("eminfo");
		EmInfoVO em_info = new EmInfoVO();
		em_info = (EmInfoVO) obj;
		String emname = em_info.getEmname();
		String emno = em_info.getEmno();
		
		param.setEmno(emno);
		logger.info("emname : " + emname);
		logger.info("emno : " + emno);
		
		pageSize =  10;
	    String cp = request.getParameter("curPage");
	    if(cp!=null){
	    	curPage = Integer.parseInt(cp);
	    }else{
	    	curPage = 1;
	    }
	    logger.info("gs + ps + cp : " + pageSize + ", " + curPage);
		param.setPageSize(pageSize);
		param.setCurPage(curPage);
		
		List<EaVO_> eaWriteRJ = eaService.eaWriteRJ(param);
		ModelAndView mav = new ModelAndView();
		
		logger.info("eaWriteRJ : " + eaWriteRJ);
		logger.info("eaWriteRJ.size : " + eaWriteRJ.size());
		
		for(int i=0; i < eaWriteRJ.size(); i++){
			EaVO_ evo = null;
			evo = new EaVO_();
			evo = eaWriteRJ.get(i);
			pageNo = evo.getPageNo();
			totalCount = evo.getTotalCount();
			curPage = evo.getCurPage();
			groupSize = evo.getGroupSize();
			logger.info("����¡ ������ Ȯ�� : " + pageNo + ":" + totalCount + ":" + curPage + ":" + groupSize);
		}
		
		mav.addObject("eaWriteRJ", eaWriteRJ);
		mav.addObject("pageNo", pageNo);
		mav.addObject("totalCount", totalCount);
		mav.addObject("curPage", curPage);
		mav.addObject("groupSize", groupSize);
		mav.setViewName("ea/eaWriteRJ");
		logger.info("[log] EaContoroller.eaWriteRJ ��");
		return mav;
	}
	
	// ���� ��µ� ���
	@RequestMapping("/eaWriteST")
	public ModelAndView eaWriteST(@ModelAttribute EaVO_ param, HttpServletRequest request, HttpSession session){
		logger.info("[log] EaContoroller.eaWriteST ����");

		Object obj = session.getAttribute("eminfo");
		EmInfoVO em_info = new EmInfoVO();
		em_info = (EmInfoVO) obj;
		String emname = em_info.getEmname();
		String emno = em_info.getEmno();
		
		param.setEmno(emno);
		logger.info("emname : " + emname);
		logger.info("emno : " + emno);
		
		pageSize =  10;
	    String cp = request.getParameter("curPage");
	    if(cp!=null){
	    	curPage = Integer.parseInt(cp);
	    }else{
	    	curPage = 1;
	    }
	    logger.info("gs + ps + cp : " + pageSize + ", " + curPage);
		param.setPageSize(pageSize);
		param.setCurPage(curPage);
		
		List<EaVO_> eaWriteST = eaService.eaWriteST(param);
		ModelAndView mav = new ModelAndView();
		
		logger.info("eaWriteST : " + eaWriteST);
		logger.info("eaWriteST.size : " + eaWriteST.size());
		
		for(int i=0; i < eaWriteST.size(); i++){
			EaVO_ evo = null;
			evo = new EaVO_();
			evo = eaWriteST.get(i);
			pageNo = evo.getPageNo();
			totalCount = evo.getTotalCount();
			curPage = evo.getCurPage();
			groupSize = evo.getGroupSize();
			logger.info("����¡ ������ Ȯ�� : " + pageNo + ":" + totalCount + ":" + curPage + ":" + groupSize);
		}
		
		mav.addObject("eaWriteST", eaWriteST);
		mav.addObject("pageNo", pageNo);
		mav.addObject("totalCount", totalCount);
		mav.addObject("curPage", curPage);
		mav.addObject("groupSize", groupSize);
		mav.setViewName("ea/eaWriteST");
		logger.info("[log] EaContoroller.eaWriteST ��");
		return mav;
	}
	
	@RequestMapping(value="/eaListAll")
	public ModelAndView eaListAll(@ModelAttribute EaVO_ param, HttpServletRequest request, HttpSession session){
		logger.info("[log] EaContoroller.eaListAll ����");
		
		Object obj = session.getAttribute("eminfo");
		EmInfoVO em_info = (EmInfoVO)obj;
		String emno = em_info.getEmno();
		logger.info("emno : " + emno);
		
		pageSize =  10;
	    String cp = request.getParameter("curPage");
	    if(cp!=null){
	    	curPage = Integer.parseInt(cp);
	    }else{
	    	curPage = 1;
	    }
	    logger.info("gs + ps + cp : " + pageSize + ", " + curPage);
	    param.setPageSize(pageSize);
		param.setCurPage(curPage);
		param.setEmno(emno);

		List<EaVO_> eaListAll = null;

		eaListAll = eaService.eaListAll(param);
		String eano = "";
		String subject = "";
		String ea_status = "";
		String ea_empno = "";
		String ea_insertdate = "";
		
		System.out.println("���� Ȯ�� : " + eaListAll);
		System.out.println("������ Ȯ�� : " + eaListAll.size());
		
		for(int i=0; i < eaListAll.size(); i++){
			EaVO_ evo = (EaVO_)eaListAll.get(i);
			eano = evo.getEa_no();
			subject = evo.getEa_subject();
			ea_status = evo.getEa_status();
			ea_empno = evo.getEa_empno();
			ea_insertdate = evo.getEa_insertdate();
			
			System.out.println("ea_status : " + ea_status);
			logger.info("eano : " + eano);
			logger.info("subject : " + subject);
			logger.info("ea_status : " + ea_status);
			logger.info("ea_empno : " + ea_empno);
			logger.info("ea_insertdate : " + ea_insertdate);
			logger.info("LINENO : " + evo.getEa_lineno());
			pageNo = evo.getPageNo();
			totalCount = evo.getTotalCount();
			curPage = evo.getCurPage();
			groupSize = evo.getGroupSize();
			logger.info("����¡ ������ Ȯ�� : " + pageNo + ":" + totalCount + ":" + curPage + ":" + groupSize);
		}
		
		ModelAndView mav = new ModelAndView();

		mav.addObject("eaList", eaListAll);
		mav.addObject("pageNo", pageNo);
		mav.addObject("totalCount", totalCount);
		mav.addObject("curPage", curPage);
		mav.addObject("groupSize", groupSize);
		mav.setViewName("ea/eaListAll");
		
		logger.info("[log] EaContoroller.eaListAll ��");
		
		return mav;
	} // end of eaListAll
	
	@RequestMapping(value="/eaListSearch")
	public ModelAndView eaListSearch(@ModelAttribute EaVO_ param, HttpServletRequest request, HttpSession session){
		logger.info("[log] EaContoroller.eaListSearch ����");
		Object obj = session.getAttribute("eminfo");
		EmInfoVO em_info = (EmInfoVO)obj;
		String emno = em_info.getEmno();
		logger.info("emno : " + emno);
		
		// ����¡��
		pageSize =  10;
	    String cp = request.getParameter("curPage");
	    if(cp!=null){
	    	curPage = Integer.parseInt(cp);
	    }else{
	    	curPage = 1;
	    }
	    logger.info("gs + ps + cp : " + pageSize + ", " + curPage);
		
		// �˻�
		String keyword = null;
		String search = null;
		String st_date = null;
		String ed_date = null;
		String datesearch = null;
		String searchall = null;
		
		System.out.println();
		keyword = request.getParameter("keyword");
		st_date = request.getParameter("st_date");
		ed_date = request.getParameter("ed_date");
		
		System.out.println("===[log] �޾ƿ� �� Ȯ�� (�˻���) Ű���� : " + keyword);
		System.out.println("===[log] �޾ƿ� �� Ȯ�� (�˻���) ��ġ : " + search);
		System.out.println("===[log] �޾ƿ� �� Ȯ�� (�˻���) ���� : " + st_date);
		System.out.println("===[log] �޾ƿ� �� Ȯ�� (�˻���) �� : " + ed_date);
		System.out.println("===[log] �޾ƿ� �� Ȯ�� (�˻���) ����Ʈ��ġ : " + datesearch);
		System.out.println("===[log] �޾ƿ� �� Ȯ�� (�˻���) ����Ʈ�� : " + searchall);
		
		if(ed_date!=null && st_date!=null){
			datesearch = "ea_insertdate";
			System.out.println("��¥ �˻�");
		}
		
		System.out.println("[log] �޾ƿ� �� Ȯ�� (�˻���) Ű���� : " + keyword);
		System.out.println("[log] �޾ƿ� �� Ȯ�� (�˻���) ��ġ : " + search);
		System.out.println("[log] �޾ƿ� �� Ȯ�� (�˻���) ���� : " + st_date);
		System.out.println("[log] �޾ƿ� �� Ȯ�� (�˻���) �� : " + ed_date);
		System.out.println("[log] �޾ƿ� �� Ȯ�� (�˻���) ����Ʈ��ġ : " + datesearch);
		System.out.println("[log] �޾ƿ� �� Ȯ�� (�˻���) ����Ʈ�� : " + searchall);
		
		EaVO_ evo = new EaVO_();
		evo.setKeyword(keyword);
		evo.setSearch(search);
		evo.setDatesearch(datesearch);
		evo.setEmno(emno);
		evo.setSt_date(st_date);
		evo.setEd_date(ed_date);
		evo.setPageSize(pageSize);
		evo.setCurPage(curPage);
		
		List<EaVO_> eaList = null;
		eaList = eaService.eaListAll(evo);
		
		System.out.println("[log] eaList : " + eaList);
		System.out.println("[log] eaList ������ : " + eaList.size());
		
		for(int i=0; i < eaList.size(); i++){
			EaVO_ eavo = new EaVO_();
			eavo = eaList.get(i);
			pageNo = eavo.getPageNo();
			totalCount = eavo.getTotalCount();
			curPage = eavo.getCurPage();
			groupSize = eavo.getGroupSize();
		}
		
		param.setKeyword(param.getKeyword());
		param.setEd_date(param.getEd_date());
		param.setSt_date(param.getSt_date());
		
		ModelAndView mav = new ModelAndView();

		mav.addObject("param", param);
		mav.addObject("eaList", eaList);
		mav.addObject("st_date", st_date);
		mav.addObject("ed_date", ed_date);
		mav.addObject("keyword", keyword);
//		mav.addObject("datesearch", datesearch);
		mav.addObject("pageNo", pageNo);
		mav.addObject("totalCount", totalCount);
		mav.addObject("curPage", curPage);
		mav.addObject("groupSize", groupSize);
		mav.setViewName("ea/eaListAll");
		
		logger.info("[log] EaContoroller.eaListSearch ��");
		return mav;
	}
	
	@RequestMapping(value="/eaListSelect")
	public ModelAndView eaListSelect(@ModelAttribute EaVO_ param, HttpServletRequest request, HttpSession session){
		logger.info("[log] EaContoroller.eaListSelect ����");
		
		Object obj = session.getAttribute("eminfo");
		EmInfoVO em_info = (EmInfoVO)obj;
		String emno = em_info.getEmno();
		logger.info("emno : " + emno);
		
		pageSize =  10;
	    String cp = request.getParameter("curPage");
	    if(cp!=null){
	    	curPage = Integer.parseInt(cp);
	    }else{
	    	curPage = 1;
	    }
	    logger.info("gs + ps + cp : " + pageSize + ", " + curPage);
	    param.setPageSize(pageSize);
		param.setCurPage(curPage);
		param.setEmno(emno);

		List<EaVO_> eaList = null;

		eaList = eaService.eaListSelect(param);
		String eano = "";
		String subject = "";
		String ea_status = "";
		String ea_empno = "";
		String ea_insertdate = "";
		
		System.out.println("���� Ȯ�� : " + eaList);
		System.out.println("������ Ȯ�� : " + eaList.size());
		
		for(int i=0; i < eaList.size(); i++){
			EaVO_ evo = (EaVO_)eaList.get(i);
			eano = evo.getEa_no();
			subject = evo.getEa_subject();
			ea_status = evo.getEa_status();
			ea_empno = evo.getEa_empno();
			ea_insertdate = evo.getEa_insertdate();
			
			System.out.println("ea_status : " + ea_status);
			logger.info("eano : " + eano);
			logger.info("subject : " + subject);
			logger.info("ea_status : " + ea_status);
			logger.info("ea_empno : " + ea_empno);
			logger.info("ea_insertdate : " + ea_insertdate);
			logger.info("LINENO : " + evo.getEa_lineno());
			pageNo = evo.getPageNo();
			totalCount = evo.getTotalCount();
			curPage = evo.getCurPage();
			groupSize = evo.getGroupSize();
			logger.info("����¡ ������ Ȯ�� : " + pageNo + ":" + totalCount + ":" + curPage + ":" + groupSize);
		}
		
		ModelAndView mav = new ModelAndView();

		mav.addObject("eaList", eaList);
		mav.addObject("pageNo", pageNo);
		mav.addObject("totalCount", totalCount);
		mav.addObject("curPage", curPage);
		mav.addObject("groupSize", groupSize);
		mav.setViewName("ea/eaListSelect");
		
		logger.info("[log] EaContoroller.eaListSelect ��");
		return mav;
	} // end of eaListSelect
	
	@RequestMapping(value="/eaListStand")
	public ModelAndView eaListStand(@ModelAttribute EaVO_ param, HttpServletRequest request, HttpSession session){
		logger.info("[log] EaContoroller.eaListStand ����");
		
		Object obj = session.getAttribute("eminfo");
		EmInfoVO em_info = (EmInfoVO)obj;
		String emno = em_info.getEmno();
		logger.info("emno : " + emno);
		
		pageSize =  10;
	    String cp = request.getParameter("curPage");
	    if(cp!=null){
	    	curPage = Integer.parseInt(cp);
	    }else{
	    	curPage = 1;
	    }
	    logger.info("gs + ps + cp : " + pageSize + ", " + curPage);
	    param.setPageSize(pageSize);
		param.setCurPage(curPage);
		param.setEmno(emno);

		List<EaVO_> eaList = null;

		eaList = eaService.eaListStand(param);
		String eano = "";
		String subject = "";
		String ea_status = "";
		String ea_empno = "";
		String ea_insertdate = "";
		String next = "";
		
		System.out.println("���� Ȯ�� : " + eaList);
		System.out.println("������ Ȯ�� : " + eaList.size());
		
		for(int i=0; i < eaList.size(); i++){
			EaVO_ evo = (EaVO_)eaList.get(i);
			eano = evo.getEa_no();
			subject = evo.getEa_subject();
			ea_status = evo.getEa_status();
			ea_empno = evo.getEa_empno();
			ea_insertdate = evo.getEa_insertdate();
			next = evo.getEa_nextemno();
			
			System.out.println("ea_status : " + ea_status);
			logger.info("eano : " + eano);
			logger.info("subject : " + subject);
			logger.info("ea_status : " + ea_status);
			logger.info("ea_empno : " + ea_empno);
			logger.info("ea_insertdate : " + ea_insertdate);
			logger.info("LINENO : " + evo.getEa_lineno());
			System.out.println("----------------------------------" + next);
			pageNo = evo.getPageNo();
			totalCount = evo.getTotalCount();
			curPage = evo.getCurPage();
			groupSize = evo.getGroupSize();
			logger.info("����¡ ������ Ȯ�� : " + pageNo + ":" + totalCount + ":" + curPage + ":" + groupSize);
		}
		
		ModelAndView mav = new ModelAndView();

		mav.addObject("eaList", eaList);
		mav.addObject("pageNo", pageNo);
		mav.addObject("totalCount", totalCount);
		mav.addObject("curPage", curPage);
		mav.addObject("groupSize", groupSize);
		mav.setViewName("ea/eaListStand");
		
		logger.info("[log] EaContoroller.eaListSelect ��");
		return mav;
	} // end of ealist1st2nd
	
	@RequestMapping(value="/eaWriteDetail")
	public ModelAndView eaWritedetail(@ModelAttribute EaVO_ param, HttpSession session){
		logger.info("[log] EaContoroller.eaWritedetail ����");
		
		Object obj = session.getAttribute("eminfo");
		EmInfoVO em_info = new EmInfoVO();
		em_info = (EmInfoVO)obj;
		
		String emno = em_info.getEmno();
		String emname = em_info.getEmname();
		param.setEmno(emno);
		param.setEmname(emname);
		
		String deptname = em_info.getDeptname();
		String jobname = em_info.getJobcd(); // �ڵ带 �̸����ٰ� ���� (���ľ����� ������?)
		param.setDeptname(deptname);
		param.setJobname(jobname);
		System.out.println("deptname : " + deptname);
		System.out.println("jobname : " + jobname);
		
		String stepno = param.getEa_stepno();
		String ea_no = param.getEa_no();
		
		System.out.println("[log] param : " + emno +":"+ emname +":"+ stepno +":"+ ea_no);
		System.out.println("���� ��ȣ : " + stepno);
		System.out.println("������ȣ : " + ea_no);
		
		List<EaVO_> eaDetail = null;
		eaDetail = eaService.eaFormDetail(param);
		String doccd = "";
		
		System.out.println("[log] eaDetail : " + eaDetail);
		
		if(eaDetail != null){
			
			for(int i=0; i < eaDetail.size(); i++){
				EaVO_ eavo = eaDetail.get(i);
				doccd = eavo.getEa_doccd();
				if("H".equals(doccd)){
					System.out.println("[log] " + eavo.getEa_no());
					System.out.println("[log] " + eavo.getEa_status());
					System.out.println("[log] " + eavo.getEa_subject());
					System.out.println("[log] " + eavo.getEa_doccd());
					System.out.println("[log] " + eavo.getEmname());
					System.out.println("[log] " + eavo.getEmname1());
					System.out.println("[log] " + eavo.getEmname2());
					System.out.println("[log] " + eavo.getEa_sign());
					System.out.println("[log] " + eavo.getEa_sign1());
					System.out.println("[log] " + eavo.getEa_sign2());
					System.out.println("[log] " + eavo.getEa_memo());
					System.out.println("[log] " + eavo.getEacno());
					System.out.println("[log] " + eavo.getEa_insertdate());
					System.out.println("[log] " + eavo.getEa_finaldate());
				}
				
				if("P".equals(doccd)){
					System.out.println("[log] " + eavo.getEa_no());
					System.out.println("[log] " + eavo.getEa_status());
					System.out.println("[log] " + eavo.getEa_subject());
					System.out.println("[log] " + eavo.getEa_doccd());
					System.out.println("[log] " + eavo.getEmname());
					System.out.println("[log] " + eavo.getEmname1());
					System.out.println("[log] " + eavo.getEmname2());
					System.out.println("[log] " + eavo.getEa_sign());
					System.out.println("[log] " + eavo.getEa_sign1());
					System.out.println("[log] " + eavo.getEa_sign2());
					System.out.println("[log] " + eavo.getEa_memo());
					System.out.println("[log] " + eavo.getEadraftcontant());
					System.out.println("[log] " + eavo.getEa_insertdate());
					System.out.println("[log] " + eavo.getEa_finaldate());
				}
			}
		}
		
		ModelAndView mav = new ModelAndView();
		
		if(eaDetail != null){
			mav.addObject("eaDetail", eaDetail);
			mav.addObject("doccd", doccd);
			mav.setViewName("ea/eaWriteDetail");
		}
		
		logger.info("[log] EaContoroller.eaWritedetail ��");
		return mav;
	} // end of eaWritedetail
	
	@RequestMapping(value="/eaReadDetail")
	public ModelAndView eaDetail(@ModelAttribute EaVO_ param, HttpSession session){
		logger.info("[log] EaContoroller.eaDetail ����");
		
		Object obj = session.getAttribute("eminfo");
		EmInfoVO em_info = new EmInfoVO();
		em_info = (EmInfoVO)obj;
		
		String emno = em_info.getEmno();
		String emname = em_info.getEmname();
		param.setEmno(emno);
		param.setEmname(emname);
		
		String deptname = em_info.getDeptname();
		String jobname = em_info.getJobcd();
		param.setDeptname(deptname);
		param.setJobname(jobname);
		System.out.println("deptname : " + deptname);
		System.out.println("jobname : " + jobname);
		
		String stepno = param.getEa_stepno();
		String ea_no = param.getEa_no();
		
		System.out.println("[log] param : " + emno +":"+ emname +":"+ stepno +":"+ ea_no);
		System.out.println("���� ��ȣ : " + stepno);
		System.out.println("������ȣ : " + ea_no);
		
		List<EaVO_> eaDetail = null;
		eaDetail = eaService.eaFormDetail(param);
		String doccd = "";
		
		System.out.println("[log] eaDetail : " + eaDetail);
		
		if(eaDetail != null){
			for(int i=0; i < eaDetail.size(); i++){
				EaVO_ eavo = eaDetail.get(i);
				doccd = eavo.getEa_doccd();
				if("H".equals(doccd)){
					System.out.println("[log] " + eavo.getEa_no());
					System.out.println("[log] " + eavo.getEa_status());
					System.out.println("[log] " + eavo.getEa_subject());
					System.out.println("[log] " + eavo.getEa_doccd());
					System.out.println("[log] " + eavo.getEmname());
					System.out.println("[log] " + eavo.getEmname1());
					System.out.println("[log] " + eavo.getEmname2());
					System.out.println("[log] " + eavo.getEa_sign());
					System.out.println("[log] " + eavo.getEa_sign1());
					System.out.println("[log] " + eavo.getEa_sign2());
					System.out.println("[log] " + eavo.getEa_memo());
					System.out.println("[log] " + eavo.getEacno());
					System.out.println("[log] " + eavo.getEa_insertdate());
					System.out.println("[log] " + eavo.getEa_finaldate());
					System.out.println("-----------------------------------" + eavo.getEa_logno());
				}
				
				if("P".equals(doccd)){
					System.out.println("[log] " + eavo.getEa_no());
					System.out.println("[log] " + eavo.getEa_status());
					System.out.println("[log] " + eavo.getEa_subject());
					System.out.println("[log] " + eavo.getEa_doccd());
					System.out.println("[log] " + eavo.getEmname());
					System.out.println("[log] " + eavo.getEmname1());
					System.out.println("[log] " + eavo.getEmname2());
					System.out.println("[log] " + eavo.getEa_sign());
					System.out.println("[log] " + eavo.getEa_sign1());
					System.out.println("[log] " + eavo.getEa_sign2());
					System.out.println("[log] " + eavo.getEa_memo());
					System.out.println("[log] " + eavo.getEadraftcontant());
					System.out.println("[log] " + eavo.getEa_insertdate());
					System.out.println("[log] " + eavo.getEa_finaldate());
				}
			}
		}
		
		ModelAndView mav = new ModelAndView();
		
		if(eaDetail != null){
			mav.addObject("eaDetail", eaDetail);
			mav.addObject("emno", emno);
			mav.addObject("doccd", doccd);
			mav.setViewName("ea/eaReadDetail");
		}
		
		logger.info("[log] EaContoroller.eaDetail ��");
		return mav;
	}
	
	// ������Ʈ
	@RequestMapping(value="eaUpdate")
	public ModelAndView eaUpdate(@ModelAttribute EaVO_ param, HttpSession session){
		logger.info("[log] EaContoroller.eaUpdate ����");
		
		Object obj = session.getAttribute("eminfo");
		EmInfoVO em_info = new EmInfoVO();
		em_info = (EmInfoVO)obj;
		
		String emno = em_info.getEmno();
		logger.info("emno : " + emno);
		
		// �α� ä��
		EaVO_ chaebun = null;
		chaebun = eaService.chaebunLog(param);
		String ea_logno = chaebun.getEa_logno();
		param.setEa_logno(Chaebun.chaebunEALOG(ea_logno));
		
		int logCnt = 0;
		int updateCnt = 0;
		String status = param.getEa_status();
		System.out.println("[log] status : " + status);
		
		EaVO_ evo = new EaVO_();
		evo.setEa_logno(param.getEa_logno());
		evo.setEa_status(status);
		System.out.println("[log] evo logno : " + evo.getEa_logno());
		System.out.println("[log] evo status : " + status);
		
		String empno2 = param.getEa_empno2();
		String eano = param.getEa_no();
		String logno = param.getEa_logno();
		logger.info("empno2 : " + empno2);
		logger.info("eano : " + eano);
		logger.info("logno : " + logno);
		
		ModelAndView mav = new ModelAndView();
		
		if("02".equals(status)){ // ����
			logCnt = eaService.eaLog1st(param);
			
			if(logCnt == 1){
				logger.info("�߰������� ���� �α� �ױ� ����");
				EaVO_ eavo = null;
				eavo = new EaVO_();
				eavo.setEa_status(status);
				eavo.setEa_nextemno(empno2);
				eavo.setEa_no(eano);
				eavo.setEa_logno(logno);
				System.out.println("empno2 : " + empno2);
				System.out.println("eano : " + eano);
				System.out.println("ea_logno : " + logno);
				
				updateCnt = eaService.updateTable(eavo);
				
				if(updateCnt == 1){
					logger.info("�߰������� ���� ������Ʈ ����");
					
					EaVO_ eavo1 = null;
					eavo1 = new EaVO_();
					
					eavo1.setEmno(emno);
					eavo1.setEa_empno(emno);
					 
					List<EaVO_> appList = eaService.eaMainAppList(eavo1);
					List<EaVO_> rjList = eaService.eaMainRJList(eavo1);
					List<EaVO_> pgList = eaService.eaMainPGList(eavo1);
					List<EaVO_> stList = eaService.eaMainSTList(eavo1);
					
					mav.setViewName("ea/eaMain_");
					mav.addObject("eminfo", em_info);
					mav.addObject("appList", appList);
					mav.addObject("rjList", rjList);
					mav.addObject("pgList", pgList);
					mav.addObject("stList", stList);
				}else{
					logger.info("�߰� ���� ������Ʈ ����");
				}
			}else{
				logger.info("�߰� ���� �α� �ױ� ����");
			}
			
		}else if("03".equals(status)){ // ��������
			logCnt = eaService.eaLog2nd(param);
			
			if(logCnt == 1){
				logger.info("���������� ���� �α� �ױ�");
				updateCnt = eaService.updateTable(param);
				
				if(updateCnt == 1){
					logger.info("���������� ���� ������Ʈ ����");
					EaVO_ eavo1 = null;
					eavo1 = new EaVO_();
					
					eavo1.setEmno(emno);
					eavo1.setEa_empno(emno);
					 
					List<EaVO_> appList = eaService.eaMainAppList(eavo1);
					List<EaVO_> rjList = eaService.eaMainRJList(eavo1);
					List<EaVO_> pgList = eaService.eaMainPGList(eavo1);
					List<EaVO_> stList = eaService.eaMainSTList(eavo1);
					
					mav.setViewName("ea/eaMain_");
					mav.addObject("eminfo", em_info);
					mav.addObject("appList", appList);
					mav.addObject("rjList", rjList);
					mav.addObject("pgList", pgList);
					mav.addObject("stList", stList);
				}else{
					logger.info("���� ���� ������Ʈ ����");
				}
				
			}else{
				logger.info("���� ���� �α� �ױ� ����");
			}
		}else if("04".equals(status)){ // �ݷ�
			if(empno2.equals(emno)){
				// ����������
				logCnt = eaService.eaLog2nd(param);
				updateCnt = eaService.updateTable(param);
				
				EaVO_ eavo1 = null;
				eavo1 = new EaVO_();
				
				eavo1.setEmno(emno);
				eavo1.setEa_empno(emno);
				 
				List<EaVO_> appList = eaService.eaMainAppList(eavo1);
				List<EaVO_> rjList = eaService.eaMainRJList(eavo1);
				List<EaVO_> pgList = eaService.eaMainPGList(eavo1);
				List<EaVO_> stList = eaService.eaMainSTList(eavo1);
				
				mav.setViewName("ea/eaMain_");
				mav.addObject("eminfo", em_info);
				mav.addObject("appList", appList);
				mav.addObject("rjList", rjList);
				mav.addObject("pgList", pgList);
				mav.addObject("stList", stList);
			}else if(!empno2.equals(emno)){
				// �߰�������
				logCnt = eaService.eaLog1st(param);
				updateCnt = eaService.updateTable(param);

				EaVO_ eavo1 = null;
				eavo1 = new EaVO_();
				
				eavo1.setEmno(emno);
				eavo1.setEa_empno(emno);
				 
				List<EaVO_> appList = eaService.eaMainAppList(eavo1);
				List<EaVO_> rjList = eaService.eaMainRJList(eavo1);
				List<EaVO_> pgList = eaService.eaMainPGList(eavo1);
				List<EaVO_> stList = eaService.eaMainSTList(eavo1);
				
				mav.setViewName("ea/eaMain_");
				mav.addObject("eminfo", em_info);
				mav.addObject("appList", appList);
				mav.addObject("rjList", rjList);
				mav.addObject("pgList", pgList);
				mav.addObject("stList", stList);
			}else{
				System.out.println("������ �ƴ� �� ��");
			}
		}else if("05".equals(status)){
			if(empno2.equals(emno)){
				// ����������
				logCnt = eaService.eaLog2nd(param);
				// ���� �ٽ� �����ؾ� �ϹǷ� ���� �����ڸ� ���� ����
				EaVO_ eavo = null;
				eavo = new EaVO_();
				eavo.setEa_status(status);
				eavo.setEa_nextemno(emno);
				eavo.setEa_no(eano);
				eavo.setEa_logno(logno);
				System.out.println("stuts : " + status);
				System.out.println("NEXTEMNO : " + eavo.getEa_nextemno());
				System.out.println("emno : " + emno);
				System.out.println("�� �ؽ�Ʈ ������ " + eavo.getEa_nextemno());
				System.out.println("ea_logno : " + logno);
				
				updateCnt = eaService.updateTable(eavo);
				
				if(updateCnt == 1){
					System.out.println("���̺� ������Ʈ ����");
					
					EaVO_ eavo1 = null;
					eavo1 = new EaVO_();
					
					eavo1.setEmno(emno);
					eavo1.setEa_empno(emno);
					 
					List<EaVO_> appList = eaService.eaMainAppList(eavo1);
					List<EaVO_> rjList = eaService.eaMainRJList(eavo1);
					List<EaVO_> pgList = eaService.eaMainPGList(eavo1);
					List<EaVO_> stList = eaService.eaMainSTList(eavo1);
					
					mav.setViewName("ea/eaMain_");
					mav.addObject("eminfo", em_info);
					mav.addObject("appList", appList);
					mav.addObject("rjList", rjList);
					mav.addObject("pgList", pgList);
					mav.addObject("stList", stList);
				}else{
					System.out.println("���̺� ������Ʈ ����");
				}
				
			}else if(!empno2.equals(emno)){
				// �߰�������
				logCnt = eaService.eaLog1st(param);
				// ���� �ٽ� �����ؾ��ϹǷ� ���������ڸ� ���� ����
				EaVO_ eavo = null;
				eavo = new EaVO_();
				eavo.setEa_logno(logno);
				eavo.setEa_status(status);
				eavo.setEa_nextemno(emno);
				eavo.setEa_no(eano);
				System.out.println("stuts : " + status);
				System.out.println("NEXTEMNO : " + eavo.getEa_nextemno());
				System.out.println("emno : " + emno);
				System.out.println("�� �ؽ�Ʈ ������ " + eavo.getEa_nextemno());
				System.out.println("ea_logno : " + logno);
				
				updateCnt = eaService.updateTable(eavo);
				
				if(updateCnt == 1){
					System.out.println("���̺� ������Ʈ ����");
					EaVO_ eavo1 = null;
					eavo1 = new EaVO_();
					
					eavo1.setEmno(emno);
					eavo1.setEa_empno(emno);
					 
					List<EaVO_> appList = eaService.eaMainAppList(eavo1);
					List<EaVO_> rjList = eaService.eaMainRJList(eavo1);
					List<EaVO_> pgList = eaService.eaMainPGList(eavo1);
					List<EaVO_> stList = eaService.eaMainSTList(eavo1);
					
					mav.setViewName("ea/eaMain_");
					mav.addObject("eminfo", em_info);
					mav.addObject("appList", appList);
					mav.addObject("rjList", rjList);
					mav.addObject("pgList", pgList);
					mav.addObject("stList", stList);
				}else{
					System.out.println("���̺� ������Ʈ ����");
				}
				
			}else{
				System.out.println("������ �ƴ� �� ��");
			}
		}
		
		logger.info("[log] EaContoroller.eaUpdate ��");
		
		return mav;
	}
	
} // end of EaController