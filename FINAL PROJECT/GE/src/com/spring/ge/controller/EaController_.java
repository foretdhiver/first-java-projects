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
	
	// 페이지 이동할 때 사용
	private int pageSize = 0;
	private int groupSize = 0;
	private int curPage = 0;
	private int totalCount = 0;
	private int pageNo = 0;
	
	@Autowired
	private EaService_ eaService;
	
	@RequestMapping(value="/loginCheck", method=RequestMethod.POST)
	public ModelAndView loginCheck(@ModelAttribute EmInfoVO evo, HttpSession session){
      logger.info("loginCheck 진입");
      
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
      logger.info("emno 확인 : " + emno);
      eavo.setEmno(emno);
      eavo.setEa_empno(emno);
      
      List<EaVO_> appList = eaService.eaMainAppList(eavo);
      List<EaVO_> eamyList = eaService.eaMaineaList(eavo);
            
      if(emvo != null){
          System.out.println("아이디와 비밀번호가 일치하는 회원이 존재합니다.");
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
		logger.info("[log] EaContoroller.gotoGEMain 시작");
		
		Object obj = session.getAttribute("eminfo");
		EmInfoVO em_info = new EmInfoVO();
		em_info = (EmInfoVO)obj;
		
		EaVO_ eavo = null;
		eavo = new EaVO_();
	      
		String emno = em_info.getEmno();
		logger.info("emno 확인 : " + emno);
		eavo.setEmno(emno);
		eavo.setEa_empno(emno);
		 
		List<EaVO_> appList = eaService.eaMainAppList(eavo);
	    List<EaVO_> mainEaList = eaService.eaMaineaList(eavo);
		        
		if(em_info != null){
			System.out.println("세션으로 본인확인 완료");
			session.setAttribute("eminfo", em_info);
		}
		   
		ModelAndView mav = new ModelAndView();
		mav.setViewName("gemain");
		mav.addObject("eminfo", em_info);
		mav.addObject("eaListAP", appList);
		mav.addObject("eaListALL", mainEaList);
		
		logger.info("[log] EaContoroller.gotoGEMain 끝");
		return mav;
	}
	
	@RequestMapping(value="eamain")
	public ModelAndView gotoeaMain(@ModelAttribute EmInfoVO evo, HttpSession session){
		logger.info("[log] EaContoroller.gotoeaMain 시작");
		
		Object obj = session.getAttribute("eminfo");
		EmInfoVO em_info = new EmInfoVO();
		em_info = (EmInfoVO)obj;
		
		EaVO_ eavo = null;
		eavo = new EaVO_();
	      
		String emno = em_info.getEmno();
		logger.info("emno 확인 : " + emno);
		eavo.setEmno(emno);
		eavo.setEa_empno(emno);
		 
		List<EaVO_> appList = eaService.eaMainAppList(eavo);
		List<EaVO_> rjList = eaService.eaMainRJList(eavo);
		List<EaVO_> pgList = eaService.eaMainPGList(eavo);
		List<EaVO_> stList = eaService.eaMainSTList(eavo);
		        
		if(em_info != null){
			System.out.println("세션으로 본인확인 완료");
			session.setAttribute("eminfo", em_info);
		}
		   
		ModelAndView mav = new ModelAndView();
		mav.setViewName("ea/eaMain_");
		mav.addObject("eminfo", em_info);
		mav.addObject("appList", appList);
		mav.addObject("rjList", rjList);
		mav.addObject("pgList", pgList);
		mav.addObject("stList", stList);
		
		logger.info("[log] EaContoroller.gotoeaMain 끝");
		return mav;
	}
	
	/* session logout */
    @RequestMapping(value="/logOut")
    public ModelAndView logOut(@ModelAttribute EmInfoVO evo, HttpSession session){
       logger.info("logOut 진입");
       session.invalidate();
       ModelAndView mav = new ModelAndView();
        mav.setViewName("/");
        logger.info("logOut 종료");
       return mav;
    }
	
	@RequestMapping(value="gotoInsert")
	public ModelAndView gotoInsert(@ModelAttribute EaVO_ param, HttpSession session){
		logger.info("[log] EaContoroller.gotoInsert 시작");
		
		Object obj = session.getAttribute("eminfo");
		EmInfoVO em_info = new EmInfoVO();
		em_info = (EmInfoVO)obj;
		String emno = em_info.getEmno();
		logger.info("[log] 사번 확인 : " + emno);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("eminfo", em_info);
		mav.setViewName("ea/lineInsert");
		
		logger.info("[log] EaContoroller.gotoInsert 끝");
		return mav;
	}
	
	@RequestMapping(value="eaInsertLine")
	public ModelAndView eaInsertLine(@ModelAttribute EaVO_ param, 
									 HttpServletRequest request, 
									 HttpSession session){
		logger.info("[log] EaContoroller.eaInsertLine 시작");
		
		/*
		 * HttpServletRequest : getParameter() 사용하는 용도
		 * HttpSession : session
		 */
		
		Object obj = session.getAttribute("eminfo");
		EmInfoVO em_info = new EmInfoVO();
		em_info = (EmInfoVO)obj;
		String emno = em_info.getEmno();
		logger.info("[log] 데이터 뽑을 때 사용하려는 사번 확인 : " + emno);
		param.setEmno(emno);
		logger.info("[log] 데이터 뽑을 때 사용하려는 찐찐사번 확인 : " + param.getEmno());

		String send = request.getParameter("send");
		System.out.println("send 확인 : " + send);
		String linename = param.getEa_linename();
		System.out.println("[log] 이름 확인 : " + linename);

		EaVO_ chaebun = null;
		chaebun = eaService.chaebunLine(chaebun);
		String ea_lineno = Chaebun.chaebunEAL(chaebun.getEa_lineno());
		param.setEa_lineno(ea_lineno);
		logger.info("[log] 찐채번 확인 : " + ea_lineno);
		
		int result = 0;
		ModelAndView mav = new ModelAndView();
		
		result = eaService.eaInsertLine(param);
		
		logger.info("[log] 결재선번호 확인 : " + param.getEa_lineno());
		logger.info("[log] 결재선이름 확인 : " + param.getEa_linename());

		if(result == 1){
			mav.addObject("ea_lineno", ea_lineno);
			mav.addObject("send", send);
			mav.setViewName("ea/stepInsert");
			logger.info("들어왔어용");
		}
		
		logger.info("[log] EaContoroller.insertLine 끝");
		return mav;
	}
	
	// text.jsp에서 첫번째 select 칸 데이터 조회하는 함수
	@RequestMapping(value="getDeptList")
	@ResponseBody
	public List<EmInfoVO> getDeptList(@ModelAttribute EmInfoVO param,
									HttpServletRequest request,
									HttpSession session){
		logger.info("[log] EaContoroller.getDeptList 시작");
		
		List<EmInfoVO> list = eaService.searchDeptList(param);
		logger.info("list : " + list);
		int listSize = list.size();
		logger.info("listSize : " + listSize);
		
		logger.info("[log] EaContoroller.getDeptList 끝");
		
		return list;
	}
	
	// text.jsp에서 두번째 select 칸 데이터 조회하는 함수
	@RequestMapping(value="getJobList")
	@ResponseBody
	public List<EmInfoVO> getJobList(@ModelAttribute EmInfoVO param,
									HttpServletRequest request,
									HttpSession session){
		logger.info("[log] EaContoroller.getJobList 시작");
		
		String deptcd = param.getDeptcd();
		logger.info("deptcd : " + deptcd);
		
		EmInfoVO emvo = new EmInfoVO();
		emvo.setDeptcd(deptcd);
		
		List<EmInfoVO> list = eaService.searchJobList(emvo);
		logger.info("[log] EaContoroller.getJobList 끝");
		
		int listSize = list.size();
		logger.info("listSize : " + listSize);
		
		return list;
	}
	
	// text.jsp에서 세번째 select 칸 데이터 조회하는 함수
	@RequestMapping(value="getEmnameList")
	@ResponseBody
	public List<EmInfoVO> getEmnameList(@ModelAttribute EmInfoVO param,
									HttpServletRequest request,
									HttpSession session){
		logger.info("[log] EaContoroller.getEmnameList 시작");
		
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
		
		logger.info("[log] EaContoroller.getEmnameList 끝");
		
		return list;
	}
	
	@RequestMapping(value="eaInsertStep")
	public ModelAndView eaInsertStep(@ModelAttribute EaVO_ param, 
									 HttpServletRequest request, 
									 HttpSession session){
		logger.info("[log] EaContoroller.eaInsertStep 시작");
		
		Object obj = session.getAttribute("eminfo");
		EmInfoVO em_info = new EmInfoVO();
		em_info = (EmInfoVO)obj;
		
		String ea_lineno = "";
		String send = "";
		
		EaVO_ chaebun = null;
		chaebun = eaService.chaebunStep(chaebun);
		String ea_stepno = Chaebun.chaebunEASTNO(chaebun.getEa_stepno());
		param.setEa_stepno(ea_stepno);
		logger.info("[log] 채번 확인 : " + param.getEa_stepno());
		
		ea_lineno = param.getEa_lineno();
		send = request.getParameter("send");
		
		logger.info("[log] 선 확인 : " + ea_lineno);
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
			logger.info("들어왔어용");
		}
		
		logger.info("[log] EaContoroller.eaInsertStep 끝");
		return mav;
	}
	
	@RequestMapping(value="gotoEaForm")
	public ModelAndView gotoEaForm (@ModelAttribute EaVO_ param, HttpServletRequest request, HttpSession session){
		logger.info("[log] EaContoroller.gotoEaForm 시작");
		
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
		
		System.out.println("[log] ea_lineno 데이터 확인 : " + ea_lineno);
		System.out.println("[log ]ea_stepno 데이터 확인 : " + ea_stepno);
		System.out.println("[log] empno 데이터 확인 : " + empno);
		System.out.println("[log] empno1 데이터 확인 : " + empno1);
		System.out.println("[log] empno2 데이터 확인 : " + empno2);
		System.out.println("[log] send 데이터 확인 : " + send);
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("ea_lineno", ea_lineno);
		mav.addObject("ea_stepno", ea_stepno);
		mav.addObject("ea_empno", empno);
		mav.addObject("ea_empno1", empno1);
		mav.addObject("ea_empno2", empno2);
		mav.addObject("send", send);
		mav.setViewName("ea/eaForm");
		
		logger.info("[log] EaContoroller.gotoEaForm 끝");
		return mav;
	}
	
	@RequestMapping(value="eaInsertEa")
	public ModelAndView eaInsertEa(@ModelAttribute EaVO_ param, 
									 HttpServletRequest request, 
									 HttpSession session){
		logger.info("[log] EaContoroller.eaInsertEa 시작");
		
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
		logger.info("[log] 로그 채번 확인 : " + param.getEa_logno());
		
		// 결재문서 채번을 여기서 만들기
		chaebun = eaService.chaebunTable(chaebun); // 있는 VO 재활용
		
		String ea_no = Chaebun.chaebunEA(chaebun.getEa_no());
		param.setEa_no(ea_no);
		logger.info("[log] 테이블 채번 확인 : " + param.getEa_no());
		
		ea_lineno = param.getEa_lineno();
		ea_stepno = param.getEa_stepno();
		ea_empno = param.getEa_empno();
		ea_doccd = param.getEa_doccd();
		logger.info("[log] ea_lineno 확인 : " + ea_lineno);
		logger.info("[log] ea_stepno 확인 : " + ea_stepno);
		logger.info("[log] ea_empno 확인 : " + ea_empno);
		logger.info("[log] ea_doccd 확인 : " + ea_doccd);
		
		int result = 0;
		
		ModelAndView mav = new ModelAndView();
		result = eaService.eaInsertLog(param);
		if(result == 1){
			mav.addObject("ea_logno", param.getEa_logno());
			mav.addObject("ea_lineno", ea_lineno);
			mav.addObject("ea_stepno", ea_stepno);
			mav.addObject("ea_empno", ea_empno);
			logger.info("로그 쌓기 성공");
		}
		
		// 다음 결재자 세팅
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
		if("H".equals(ea_doccd)){ // 테이블 인서트
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
				logger.info("휴가계 작성 성공");
			}
		}else if("P".equals(ea_doccd)){ // 테이블 인서트
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
				logger.info("emno 확인 : " + emno);
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
				logger.info("프로젝트 기안서 작성 성공");
			}
		}else{
			System.out.println("테이블 인서트 실패");
		}
		logger.info("[log] EaContoroller.eaInsertEa 끝");
		return mav;
	}
	
	@RequestMapping("/eaWriteAll")
	public ModelAndView eaWriteAll(@ModelAttribute EaVO_ param, HttpServletRequest request, HttpSession session){
		logger.info("[log] EaContoroller.eaWriteAll 시작");

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
			logger.info("페이징 데이터 확인 : " + pageNo + ":" + totalCount + ":" + curPage + ":" + groupSize);
		}
		
		mav.addObject("eaWriteAll", eaWriteAll);
		mav.addObject("pageNo", pageNo);
		mav.addObject("totalCount", totalCount);
		mav.addObject("curPage", curPage);
		mav.addObject("groupSize", groupSize);
		mav.setViewName("ea/eaWriteAll");
		logger.info("[log] EaContoroller.eaWriteAll 끝");
		return mav;
	}
	
	// 내가 썼는데 진행중
	@RequestMapping("/eaWritePG")
	public ModelAndView eaWritePG(@ModelAttribute EaVO_ param, HttpServletRequest request, HttpSession session){
		logger.info("[log] EaContoroller.eaWritePG 시작");

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
			logger.info("페이징 데이터 확인 : " + pageNo + ":" + totalCount + ":" + curPage + ":" + groupSize);
		}
		
		mav.addObject("eaWritePG", eaWritePG);
		mav.addObject("pageNo", pageNo);
		mav.addObject("totalCount", totalCount);
		mav.addObject("curPage", curPage);
		mav.addObject("groupSize", groupSize);
		mav.setViewName("ea/eaWritePG");
		logger.info("[log] EaContoroller.eaWritePG 끝");
		return mav;
	}
	
	// 내가 썼는데 최종완료
	@RequestMapping("/eaWriteEnd")
	public ModelAndView eaWriteEnd(@ModelAttribute EaVO_ param, HttpServletRequest request, HttpSession session){
		logger.info("[log] EaContoroller.eaWriteEnd 시작");

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
			logger.info("페이징 데이터 확인 : " + pageNo + ":" + totalCount + ":" + curPage + ":" + groupSize);
		}
		
		mav.addObject("eaWriteEnd", eaWriteEnd);
		mav.addObject("pageNo", pageNo);
		mav.addObject("totalCount", totalCount);
		mav.addObject("curPage", curPage);
		mav.addObject("groupSize", groupSize);
		mav.setViewName("ea/eaWriteEnd");
		logger.info("[log] EaContoroller.eaWriteEnd 끝");
		return mav;
	}
	
	// 내가 썼는데 반려
	@RequestMapping("/eaWriteRJ")
	public ModelAndView eaWriteRJ(@ModelAttribute EaVO_ param, HttpServletRequest request, HttpSession session){
		logger.info("[log] EaContoroller.eaWriteRJ 시작");

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
			logger.info("페이징 데이터 확인 : " + pageNo + ":" + totalCount + ":" + curPage + ":" + groupSize);
		}
		
		mav.addObject("eaWriteRJ", eaWriteRJ);
		mav.addObject("pageNo", pageNo);
		mav.addObject("totalCount", totalCount);
		mav.addObject("curPage", curPage);
		mav.addObject("groupSize", groupSize);
		mav.setViewName("ea/eaWriteRJ");
		logger.info("[log] EaContoroller.eaWriteRJ 끝");
		return mav;
	}
	
	// 내가 썼는데 대기
	@RequestMapping("/eaWriteST")
	public ModelAndView eaWriteST(@ModelAttribute EaVO_ param, HttpServletRequest request, HttpSession session){
		logger.info("[log] EaContoroller.eaWriteST 시작");

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
			logger.info("페이징 데이터 확인 : " + pageNo + ":" + totalCount + ":" + curPage + ":" + groupSize);
		}
		
		mav.addObject("eaWriteST", eaWriteST);
		mav.addObject("pageNo", pageNo);
		mav.addObject("totalCount", totalCount);
		mav.addObject("curPage", curPage);
		mav.addObject("groupSize", groupSize);
		mav.setViewName("ea/eaWriteST");
		logger.info("[log] EaContoroller.eaWriteST 끝");
		return mav;
	}
	
	@RequestMapping(value="/eaListAll")
	public ModelAndView eaListAll(@ModelAttribute EaVO_ param, HttpServletRequest request, HttpSession session){
		logger.info("[log] EaContoroller.eaListAll 시작");
		
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
		
		System.out.println("존재 확인 : " + eaListAll);
		System.out.println("사이즈 확인 : " + eaListAll.size());
		
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
			logger.info("페이징 데이터 확인 : " + pageNo + ":" + totalCount + ":" + curPage + ":" + groupSize);
		}
		
		ModelAndView mav = new ModelAndView();

		mav.addObject("eaList", eaListAll);
		mav.addObject("pageNo", pageNo);
		mav.addObject("totalCount", totalCount);
		mav.addObject("curPage", curPage);
		mav.addObject("groupSize", groupSize);
		mav.setViewName("ea/eaListAll");
		
		logger.info("[log] EaContoroller.eaListAll 끝");
		
		return mav;
	} // end of eaListAll
	
	@RequestMapping(value="/eaListSearch")
	public ModelAndView eaListSearch(@ModelAttribute EaVO_ param, HttpServletRequest request, HttpSession session){
		logger.info("[log] EaContoroller.eaListSearch 시작");
		Object obj = session.getAttribute("eminfo");
		EmInfoVO em_info = (EmInfoVO)obj;
		String emno = em_info.getEmno();
		logger.info("emno : " + emno);
		
		// 페이징용
		pageSize =  10;
	    String cp = request.getParameter("curPage");
	    if(cp!=null){
	    	curPage = Integer.parseInt(cp);
	    }else{
	    	curPage = 1;
	    }
	    logger.info("gs + ps + cp : " + pageSize + ", " + curPage);
		
		// 검색
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
		
		System.out.println("===[log] 받아온 거 확인 (검색용) 키워드 : " + keyword);
		System.out.println("===[log] 받아온 거 확인 (검색용) 서치 : " + search);
		System.out.println("===[log] 받아온 거 확인 (검색용) 시작 : " + st_date);
		System.out.println("===[log] 받아온 거 확인 (검색용) 끝 : " + ed_date);
		System.out.println("===[log] 받아온 거 확인 (검색용) 데이트서치 : " + datesearch);
		System.out.println("===[log] 받아온 거 확인 (검색용) 데이트올 : " + searchall);
		
		if(ed_date!=null && st_date!=null){
			datesearch = "ea_insertdate";
			System.out.println("날짜 검색");
		}
		
		System.out.println("[log] 받아온 거 확인 (검색용) 키워드 : " + keyword);
		System.out.println("[log] 받아온 거 확인 (검색용) 서치 : " + search);
		System.out.println("[log] 받아온 거 확인 (검색용) 시작 : " + st_date);
		System.out.println("[log] 받아온 거 확인 (검색용) 끝 : " + ed_date);
		System.out.println("[log] 받아온 거 확인 (검색용) 데이트서치 : " + datesearch);
		System.out.println("[log] 받아온 거 확인 (검색용) 데이트올 : " + searchall);
		
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
		System.out.println("[log] eaList 사이즈 : " + eaList.size());
		
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
		
		logger.info("[log] EaContoroller.eaListSearch 끝");
		return mav;
	}
	
	@RequestMapping(value="/eaListSelect")
	public ModelAndView eaListSelect(@ModelAttribute EaVO_ param, HttpServletRequest request, HttpSession session){
		logger.info("[log] EaContoroller.eaListSelect 시작");
		
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
		
		System.out.println("존재 확인 : " + eaList);
		System.out.println("사이즈 확인 : " + eaList.size());
		
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
			logger.info("페이징 데이터 확인 : " + pageNo + ":" + totalCount + ":" + curPage + ":" + groupSize);
		}
		
		ModelAndView mav = new ModelAndView();

		mav.addObject("eaList", eaList);
		mav.addObject("pageNo", pageNo);
		mav.addObject("totalCount", totalCount);
		mav.addObject("curPage", curPage);
		mav.addObject("groupSize", groupSize);
		mav.setViewName("ea/eaListSelect");
		
		logger.info("[log] EaContoroller.eaListSelect 끝");
		return mav;
	} // end of eaListSelect
	
	@RequestMapping(value="/eaListStand")
	public ModelAndView eaListStand(@ModelAttribute EaVO_ param, HttpServletRequest request, HttpSession session){
		logger.info("[log] EaContoroller.eaListStand 시작");
		
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
		
		System.out.println("존재 확인 : " + eaList);
		System.out.println("사이즈 확인 : " + eaList.size());
		
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
			logger.info("페이징 데이터 확인 : " + pageNo + ":" + totalCount + ":" + curPage + ":" + groupSize);
		}
		
		ModelAndView mav = new ModelAndView();

		mav.addObject("eaList", eaList);
		mav.addObject("pageNo", pageNo);
		mav.addObject("totalCount", totalCount);
		mav.addObject("curPage", curPage);
		mav.addObject("groupSize", groupSize);
		mav.setViewName("ea/eaListStand");
		
		logger.info("[log] EaContoroller.eaListSelect 끝");
		return mav;
	} // end of ealist1st2nd
	
	@RequestMapping(value="/eaWriteDetail")
	public ModelAndView eaWritedetail(@ModelAttribute EaVO_ param, HttpSession session){
		logger.info("[log] EaContoroller.eaWritedetail 시작");
		
		Object obj = session.getAttribute("eminfo");
		EmInfoVO em_info = new EmInfoVO();
		em_info = (EmInfoVO)obj;
		
		String emno = em_info.getEmno();
		String emname = em_info.getEmname();
		param.setEmno(emno);
		param.setEmname(emname);
		
		String deptname = em_info.getDeptname();
		String jobname = em_info.getJobcd(); // 코드를 이름에다가 넣음 (고쳐야하지 않을까?)
		param.setDeptname(deptname);
		param.setJobname(jobname);
		System.out.println("deptname : " + deptname);
		System.out.println("jobname : " + jobname);
		
		String stepno = param.getEa_stepno();
		String ea_no = param.getEa_no();
		
		System.out.println("[log] param : " + emno +":"+ emname +":"+ stepno +":"+ ea_no);
		System.out.println("스텝 번호 : " + stepno);
		System.out.println("문서번호 : " + ea_no);
		
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
		
		logger.info("[log] EaContoroller.eaWritedetail 끝");
		return mav;
	} // end of eaWritedetail
	
	@RequestMapping(value="/eaReadDetail")
	public ModelAndView eaDetail(@ModelAttribute EaVO_ param, HttpSession session){
		logger.info("[log] EaContoroller.eaDetail 시작");
		
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
		System.out.println("스텝 번호 : " + stepno);
		System.out.println("문서번호 : " + ea_no);
		
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
		
		logger.info("[log] EaContoroller.eaDetail 끝");
		return mav;
	}
	
	// 업데이트
	@RequestMapping(value="eaUpdate")
	public ModelAndView eaUpdate(@ModelAttribute EaVO_ param, HttpSession session){
		logger.info("[log] EaContoroller.eaUpdate 시작");
		
		Object obj = session.getAttribute("eminfo");
		EmInfoVO em_info = new EmInfoVO();
		em_info = (EmInfoVO)obj;
		
		String emno = em_info.getEmno();
		logger.info("emno : " + emno);
		
		// 로그 채번
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
		
		if("02".equals(status)){ // 승인
			logCnt = eaService.eaLog1st(param);
			
			if(logCnt == 1){
				logger.info("중간결재자 승인 로그 쌓기 성공");
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
					logger.info("중간결재자 승인 업데이트 성공");
					
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
					logger.info("중간 승인 업데이트 실패");
				}
			}else{
				logger.info("중간 승인 로그 쌓기 실패");
			}
			
		}else if("03".equals(status)){ // 최종승인
			logCnt = eaService.eaLog2nd(param);
			
			if(logCnt == 1){
				logger.info("최종결재자 승인 로그 쌓기");
				updateCnt = eaService.updateTable(param);
				
				if(updateCnt == 1){
					logger.info("최종결재자 승인 업데이트 성공");
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
					logger.info("최종 승인 업데이트 실패");
				}
				
			}else{
				logger.info("최종 승인 로그 쌓기 실패");
			}
		}else if("04".equals(status)){ // 반려
			if(empno2.equals(emno)){
				// 최종결재자
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
				// 중간결재자
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
				System.out.println("순번이 아닌 듯 함");
			}
		}else if("05".equals(status)){
			if(empno2.equals(emno)){
				// 최종결재자
				logCnt = eaService.eaLog2nd(param);
				// 내가 다시 결재해야 하므로 다음 결재자를 나로 세팅
				EaVO_ eavo = null;
				eavo = new EaVO_();
				eavo.setEa_status(status);
				eavo.setEa_nextemno(emno);
				eavo.setEa_no(eano);
				eavo.setEa_logno(logno);
				System.out.println("stuts : " + status);
				System.out.println("NEXTEMNO : " + eavo.getEa_nextemno());
				System.out.println("emno : " + emno);
				System.out.println("찐 넥스트 결재자 " + eavo.getEa_nextemno());
				System.out.println("ea_logno : " + logno);
				
				updateCnt = eaService.updateTable(eavo);
				
				if(updateCnt == 1){
					System.out.println("테이블 업데이트 성공");
					
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
					System.out.println("테이블 업데이트 실패");
				}
				
			}else if(!empno2.equals(emno)){
				// 중간결재자
				logCnt = eaService.eaLog1st(param);
				// 내가 다시 결재해야하므로 다음결재자를 나로 세팅
				EaVO_ eavo = null;
				eavo = new EaVO_();
				eavo.setEa_logno(logno);
				eavo.setEa_status(status);
				eavo.setEa_nextemno(emno);
				eavo.setEa_no(eano);
				System.out.println("stuts : " + status);
				System.out.println("NEXTEMNO : " + eavo.getEa_nextemno());
				System.out.println("emno : " + emno);
				System.out.println("찐 넥스트 결재자 " + eavo.getEa_nextemno());
				System.out.println("ea_logno : " + logno);
				
				updateCnt = eaService.updateTable(eavo);
				
				if(updateCnt == 1){
					System.out.println("테이블 업데이트 성공");
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
					System.out.println("테이블 업데이트 실패");
				}
				
			}else{
				System.out.println("순번이 아닌 듯 함");
			}
		}
		
		logger.info("[log] EaContoroller.eaUpdate 끝");
		
		return mav;
	}
	
} // end of EaController