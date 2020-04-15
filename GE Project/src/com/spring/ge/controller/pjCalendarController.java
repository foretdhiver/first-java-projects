package com.spring.ge.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ge.common.Chaebun;
import com.spring.ge.service.PjCalendarService;
import com.spring.ge.vo.PjCalendarVO;

@Controller
@RequestMapping(value="/pjCalendar")
public class pjCalendarController {

	@Autowired
	private PjCalendarService pjCalendarService;
	
	//리스트 가기
	@ResponseBody
	@RequestMapping(value="/calList")
	public ModelAndView calList(@ModelAttribute PjCalendarVO cvo) {
	
		//리스트로 넘겨주기
		List<PjCalendarVO> clist = pjCalendarService.calList(cvo);
				
		JSONArray jArr = new JSONArray();
		for(int i=0; i< clist.size(); i++){
			JSONObject jObj = new JSONObject();
			
			jObj.put("title", clist.get(i).getPcsub());
			jObj.put("start", clist.get(i).getPcsdate());
			jObj.put("end", clist.get(i).getPcedate());
			jArr.add(jObj);
			
		}//for
		
		//jsonArray 로그 확인
		for(int i=0; i< clist.size(); i++){
			System.out.println("(log)jaaray >>> " + jArr.get(i));
			
		}
		System.out.println("	clist.size >>> " + clist.size());
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("clist", clist);
		mav.setViewName("ProjectBoard/pjCalMain");
			
		return mav;
	}//List
	
	//팝업
	@RequestMapping(value="/calPopup")
	public String calPopup(@ModelAttribute PjCalendarVO cvo, Model model,HttpServletRequest req) {
		
		return "ProjectBoard/pjCalendarPopup";
	}//List
	
	//일정입력
	@RequestMapping(value="/calInsert")
	public ModelAndView calInsert(HttpServletRequest request, @ModelAttribute PjCalendarVO cvo) {
		
		System.out.println("cvo.getPcsub >>> : "+cvo.getPcsub());
		
		//변수 초기화
		String pcno = "";
		String pcsub = "";
		String deptcd = "";
		String jobcd = "";
		String emname = "";
		String pcmemo = "";
		String pcsdate = "";
		String pcedate = "";
		String pcinsertdate = "";
		String pcupdatedate = "";
		String deleteyn = "";
		String deptname = "";
		String jobname = "";

		pcsub = request.getParameter("pcsub");
		deptcd = request.getParameter("deptcd");
		jobcd = request.getParameter("jobcd");
		emname = request.getParameter("emname");
		pcmemo = request.getParameter("pcmemo");
		pcsdate = request.getParameter("pcsdate");
		pcedate = request.getParameter("pcedate");
		pcinsertdate = request.getParameter("pcinsertdate");
		pcupdatedate = request.getParameter("pcupdatedate");
		deleteyn = request.getParameter("deleteyn");
		deptname = request.getParameter("deptname");
		jobname = request.getParameter("jobname");
		
		//채번
		PjCalendarVO ccb = null;
		ccb = pjCalendarService.calChaebun(cvo);
		ccb.getPcno();
		
		String cbcb = "";
		cbcb = ccb.getPcno();
		cvo.setPcno(Chaebun.chaebunPC(cbcb));

		cvo.setPcsub(pcsub);
		cvo.setDeptname(deptname);
		cvo.setJobcd(jobcd);
		cvo.setJobname(jobname);
		cvo.setEmname(emname);
		cvo.setPcmemo(pcmemo);
		cvo.setPcsdate(pcsdate);
		cvo.setPcedate(pcedate);
		cvo.setPcinsertdate(pcinsertdate);
		cvo.setPcupdatedate(pcupdatedate);
		cvo.setDeleteyn(deleteyn);
		
		System.out.println("	pcno >>> " + cvo.getPcno());
		System.out.println("	pcsub >>> " + cvo.getPcsub());
		System.out.println("	deptcd >>> " + cvo.getDeptcd());
		System.out.println("	jobcd >>> " + cvo.getJobcd());
		System.out.println("	emname >>> " + cvo.getEmname());
		System.out.println("	pcmemo >>> " + cvo.getPcmemo());
		System.out.println("	pcsdate >>> " + cvo.getPcsdate());
		System.out.println("	pcedate >>> " + cvo.getPcedate());
		System.out.println("	pcinsertdate >>> " + cvo.getPcinsertdate() );
		System.out.println("	pcupdatedate >>> " + cvo.getPcupdatedate());
		System.out.println("	deleteyn >>> " + cvo.getDeleteyn());
		System.out.println("	deptname >>> " + cvo.getDeptname());
		System.out.println("	jobname >>> " + cvo.getJobname());
			
		int result = 0;
		result = pjCalendarService.calInsert(cvo);
		
		if(result >0){
			System.out.println("************* 등록이 완료되었습니다.");
		}else{
			System.out.println("************* 등록에 실패하였습니다.");
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("calResult", result);
		mav.setViewName("ProjectBoard/calResult");
				
		return mav;
	}//insert

	//detail
	@RequestMapping(value="/calDetail")
	public ModelAndView calDetail(@ModelAttribute PjCalendarVO cvo){
		
		System.out.println("*** cvogetPcno >>> " + cvo.getPcno());
		
		PjCalendarVO calvo = new PjCalendarVO();
		cvo.getPcno();
		cvo.getEmname();
		cvo.getDeptname();
		cvo.getJobname();
		calvo.setPcno(cvo.getPcno());
		calvo.setEmname(cvo.getEmname());
		calvo.setDeptcd(cvo.getDeptname());
		calvo.setJobcd(cvo.getJobname());
		System.out.println("****디테일가져온 calvo.setPcno >>> " + calvo.getPcno());
		System.out.println("****디테일가져온 calvo.setEmname >>> " + calvo.getEmname());
		System.out.println("****디테일가져온 calvo.setJobname >>> " + calvo.getJobname());
		System.out.println("****디테일가져온 calvo.setDeptname >>> " + calvo.getDeptname());

		
		ModelAndView mav = new ModelAndView();
		PjCalendarVO calDetail = pjCalendarService.calDetail(cvo);
		
		mav.addObject("calDetail", calDetail);
		mav.setViewName("ProjectBoard/pjCalDetail");
		
		return mav;
	}
	//수정폼 가기	
	@RequestMapping(value="/calUpdateForm")
	public String calUpdateForm(@ModelAttribute PjCalendarVO cvo, Model model){
		
		PjCalendarVO updateVO = new PjCalendarVO();
		updateVO = pjCalendarService.calDetail(cvo);
		
		model.addAttribute("calUpdateForm", updateVO);
		return "ProjectBoard/pjCalendarUpdate";
	}
	
	//수정하기
	@RequestMapping(value="/calUpdate")
	public ModelAndView calUpdate(@ModelAttribute PjCalendarVO cvo, Model model,HttpServletRequest req) {
		
		System.out.println("	pcno >>> " + cvo.getPcno());
		System.out.println("	pcsub >>> " + cvo.getPcsub());
		System.out.println("	emname >>> " + cvo.getEmname());
		System.out.println("	pcmemo >>> " + cvo.getPcmemo());
		System.out.println("	pcsdate >>> " + cvo.getPcsdate());
		System.out.println("	pcedate >>> " + cvo.getPcedate());
		System.out.println("	jobname >>> " + cvo.getJobname());
		System.out.println("	deptname >>> " + cvo.getDeptname());

		
			
		int result = 0;
		result = pjCalendarService.calUpdate(cvo);
		
		if(result >0){
			System.out.println("************* 수정이 완료되었습니다.");
		}else{
			System.out.println("************* 수정에 실패하였습니다.");
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("calResult", result);
		mav.setViewName("ProjectBoard/pjCalDetail");
		
		return mav;
	}//List
	
	//삭제
	@RequestMapping(value="/calDelete")
	public String calDelete(@ModelAttribute PjCalendarVO cvo, HttpServletRequest request) 
			throws IOException {

		int result=0;
		String url="";
		String str = "";
		
		result = pjCalendarService.calDelete(cvo);
		
		if(result==1){
			System.out.println("delete.if >>> ");
			str="삭제되었습니다.";
		}
		
		return ("ProjectBoard/calResult");
	}
	
}//class
