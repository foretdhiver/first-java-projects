package com.spring.ge.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ge.common.Chaebun;
import com.spring.ge.service.CalendarService;
import com.spring.ge.service.EM_InfoService;
import com.spring.ge.vo.CalendarVO;
import com.spring.ge.vo.EmInfoVO;

@Controller
@RequestMapping(value="/calendar")
public class CalendarController {
	
	Logger logger = Logger.getLogger(BoardController.class);
	
	@Autowired
	private EM_InfoService eM_InfoService;
	
	@Autowired
	private CalendarService calendarService;
	
	/* 일정 불러오기 */
	@ResponseBody
	@RequestMapping(value="/all/{emno}.ge", method=RequestMethod.GET)
	public List<CalendarVO> list(@PathVariable("emno") String emno){
		logger.info("list 호출 성공");
		logger.info("emno >>> "  + emno);
		
		CalendarVO clvo = null;
		clvo = new CalendarVO();
		clvo.setEmno(emno);
		
		List<CalendarVO> allList = null;
		allList = calendarService.calendarList(clvo);
		System.out.println("size >>> " + allList.size());
		for(int i = 0; i<allList.size(); i++){
			CalendarVO clVO = null;
			clVO = new CalendarVO();
			clVO = allList.get(i);
			System.out.println("제목 >> " + clVO.getCalsubject());
			System.out.println("시작시간 >> " + clVO.getCalstarttime());
		}

		return allList;
	}	
	/* 일정관리  입력 폼으로 이동 */
	@RequestMapping(value="/calendarPopup")
	public ModelAndView calendarPopup(HttpServletRequest request){
		logger.info("calendarPopup 진입");
		String dateTest = "";
		dateTest = request.getParameter("dateTest");
		System.out.println("dateTest >>> " + dateTest);
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("dateTest", dateTest);
		mav.setViewName("calendar/CalendarPopup");	
		
		return mav;		
	}
	/* 클릭한 일정 보기 */
	@RequestMapping(value="/calendarPopupCheck")
	public ModelAndView calendarPopupCheck(CalendarVO clvo){
		logger.info("calendarPopupCheck 진입");
		logger.info("clvo.getCalno() >> " + clvo.getCalno());
		
		CalendarVO clVO = null;
		clVO = new CalendarVO();
		clVO.setCalno(clvo.getCalno());
		
		List<CalendarVO> calendarListOne = null;
		calendarListOne = calendarService.calendarListOne(clVO);
		System.out.println("size >>> " + calendarListOne.size());	
				
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("calendarListOne", calendarListOne);
		mav.setViewName("calendar/CalendarPopupCheck");	
		
		return mav;		
	}
	/* 입력  */
	@RequestMapping(value="/calendarInsert")
	public ModelAndView calendarInsert(HttpSession httpsession, CalendarVO clvo){
		logger.info("calendarInsert 진입");
		String chaebun = "";
		int result = 0;
		String resultText = "";
		
		CalendarVO clVO = null;
		clVO = new CalendarVO();
		clVO = calendarService.calendarChaebun(clvo);
		System.out.println("no >> " + clVO.getCalno());
		chaebun = Chaebun.chaebunCAL(clVO.getCalno());
		System.out.println("chaebun >>> " + chaebun);
		
		Object infoObj = httpsession.getAttribute("eminfo");
		EmInfoVO infoList = new EmInfoVO();
		infoList = (EmInfoVO)infoObj;
		
		clvo.setCalno(chaebun);
		clvo.setDeptcd(infoList.getDeptcd());
		clvo.setJobcd(infoList.getJobcd());
		clvo.setEmno(infoList.getEmno());
		clvo.setCalid(infoList.getEmid());
		clvo.setCalpw(infoList.getEmpw());
		
		if(clvo.getCalendtime().equals("")&&clvo.getCalendtime().length()==0){
			clvo.setCalendtime(clvo.getCalstarttime());			
		}
		
		System.out.println(clvo.getCalsubject());
		System.out.println(clvo.getCalstarttime());
		System.out.println(clvo.getCalendtime());
		System.out.println(clvo.getCalcategory());
		System.out.println(clvo.getCalcontent());
		System.out.println(clvo.getDeptcd());
		System.out.println(clvo.getJobcd());
		System.out.println(clvo.getEmno());
		System.out.println(clvo.getCalid());
		System.out.println(clvo.getCalpw());
		
		result = calendarService.calendarInsert(clvo);
		System.out.println("insert 결과 >>> " + result);
		
		ModelAndView mav = new ModelAndView();
		
		if(result==1){
			resultText = "입력성공";
			System.out.println("resultText >>> " + resultText);
			mav.addObject("resultText", resultText);
			
		}else{
			resultText = "입력실패";
			System.out.println("resultText >>> " + resultText);
			mav.addObject("resultText", resultText);
		}
		
		mav.setViewName("calendar/CalendarResult_Insert");
		return mav;		
	}
	@RequestMapping(value="/calendarDelete")
	public ModelAndView calendarDelete(@ModelAttribute CalendarVO clvo, HttpServletRequest request,
												HttpSession httpsession) throws IllegalStateException, IOException{
		logger.info("calendarDelete 진입");
		int result = 0;
		String resultText = "";
		
		Object infoObj = httpsession.getAttribute("eminfo");
		EmInfoVO infoList = new EmInfoVO();
		infoList = (EmInfoVO)infoObj;
		
		clvo.setCalno(clvo.getCalno());
		clvo.setEmno(clvo.getEmno());
		clvo.setCalid(infoList.getEmid());
		clvo.setCalpw(infoList.getEmpw());
		
		logger.info("[controller] calno >> " + clvo.getCalno());
		logger.info("[controller] emno >> " + clvo.getEmno());
		logger.info("[controller] calid >> " + clvo.getCalid());
		logger.info("[controller] calpw >> " + clvo.getCalpw());	
		
		result = calendarService.calendarDelete(clvo);
		logger.info("[controller] result >> " + result);
		
		ModelAndView mav = new ModelAndView();

		if(result == 1){
			resultText = "삭제성공";
			logger.info("[controller] resultText >> " + resultText);
			mav.addObject("resultText", resultText);
		}else{
			resultText = "삭제실패";
			System.out.println("resultText >>> " + resultText);
			mav.addObject("resultText", resultText);
		}
		
		mav.setViewName("calendar/CalendarResult_Delete");
		
		return mav;
	}
	@RequestMapping(value="/calendarUpdateForm")
	public ModelAndView calendarUpdateForm(@ModelAttribute CalendarVO clvo) {
		logger.info("calendarUpdateForm 진입");
		logger.info("clvo.getCalno() >> " + clvo.getCalno());
		
		CalendarVO clVO = null;
		clVO = new CalendarVO();
		clVO.setCalno(clvo.getCalno());
		
		List<CalendarVO> calendarListOne = null;
		calendarListOne = calendarService.calendarListOne(clVO);
		System.out.println("size >>> " + calendarListOne.size());	
				
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("calendarListOne", calendarListOne);
		mav.setViewName("calendar/CalendarPopupUpdate");	
		
		return mav;	
	}
	/* 입력  */
	@RequestMapping(value="/calendarUpdate")
	public ModelAndView calendarUpdate(HttpSession httpsession, CalendarVO clvo){
		logger.info("calendarUpdate 진입");
		int result = 0;
		String resultText = "";
				
		Object infoObj = httpsession.getAttribute("eminfo");
		EmInfoVO infoList = new EmInfoVO();
		infoList = (EmInfoVO)infoObj;
				
		if(clvo.getCalendtime().equals("")&&clvo.getCalendtime().length()==0){
			clvo.setCalendtime(clvo.getCalstarttime());			
		}
		
		CalendarVO clVO = null;
		clVO = new CalendarVO();
		clVO.setCalsubject(clvo.getCalsubject());
		clVO.setCalstarttime(clvo.getCalstarttime());
		clVO.setCalendtime(clvo.getCalendtime());
		clVO.setCalcategory(clvo.getCalcategory());
		clVO.setCalcontent(clvo.getCalcontent());
		clVO.setCalno(clvo.getCalno());
		clVO.setEmno(infoList.getEmno());
		clVO.setCalid(infoList.getEmid());
		clVO.setCalpw(infoList.getEmpw());

		System.out.println(clVO.getCalsubject());
		System.out.println(clVO.getCalstarttime());
		System.out.println(clVO.getCalendtime());
		System.out.println(clVO.getCalcategory());
		System.out.println(clVO.getCalcontent());
		System.out.println(clVO.getCalno());
		System.out.println(clVO.getEmno());
		System.out.println(clVO.getCalid());
		System.out.println(clVO.getCalpw());
		
		result = calendarService.calendarUpdate(clVO);
		System.out.println("update 결과 >>> " + result);
		
		ModelAndView mav = new ModelAndView();
		
		if(result==1){
			resultText = "수정성공";
			System.out.println("resultText >>> " + resultText);
			mav.addObject("resultText", resultText);
			
		}else{
			resultText = "수정실패";
			System.out.println("resultText >>> " + resultText);
			mav.addObject("resultText", resultText);
		}
		
		mav.setViewName("calendar/CalendarResult_Insert");
		return mav;		
	}

}
