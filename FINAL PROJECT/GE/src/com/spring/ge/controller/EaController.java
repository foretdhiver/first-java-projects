package com.spring.ge.controller;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ge.common.Chaebun;
import com.spring.ge.common.EaFileUploadUtil;
import com.spring.ge.service.BDService;
import com.spring.ge.service.BNService;
import com.spring.ge.service.CalendarService;
import com.spring.ge.service.EaService;
import com.spring.ge.service.EmService;
import com.spring.ge.vo.BDVO;
import com.spring.ge.vo.BNVO;
import com.spring.ge.vo.CalendarVO;
import com.spring.ge.vo.EaVO;
import com.spring.ge.vo.EmInfoVO;


@Controller
@RequestMapping(value="/ea")
public class EaController {
	
	// ����¡
	private int pageSize = 0;
	private int groupSize = 0;
	private int curPage = 0;
	private int totalCount = 0;
	private int pageNo = 0;
		
	/* ���ڰ��繮��(ea_file)���ϰ�� */
	final static String EAFILEPATH="C://Users//bitcamp//Desktop//Java142_Project_GE//WebContent//ea_Upload";
//	final static String EAFILEPATH="D://00.KYJ//java142_luna//GE//WebContent//ea_Upload";
	
	final static String DOWNLOADEAPATH="ea_Upload/";
	
	/* �������(ea_form)���ϰ�� */
	final static String EAFORMATH="C://Users//bitcamp//Desktop//Java142_Project_GE//WebContent//ea_Form";
//	final static String EAFORMATH="D://00.KYJ//java142_luna//GE//WebContent//ea_Form";
	final static String DOWNLOADFORMPATH="ea_Form/";
	
	private static Logger logger=Logger.getLogger(EaController.class);
	
	@Autowired
	private EaService eaService;
	
	@Autowired
	private BDService bDService;
	
	@Autowired
	private BNService bNService;
	
	@Autowired
	private EmService emService;
	
	@Autowired
	private CalendarService calendarService;
	
	/* SEARCH */
	/* �� ���ڰ��� ��ü����Ʈ SEARCH */
	@RequestMapping("/eamylistsearch")
	public ModelAndView eaMyListSearch(@ModelAttribute EaVO param, HttpServletRequest request, HttpSession httpsession){
		logger.info("[log] EaController.eaMyListSearch ���� >>> ");
		String keyword = "";
		String search = "";
		String datesearch = "";
		String st_date = "";
		String ed_date = "";
		String searchall = "";
		
		keyword = request.getParameter("keyword");
		ed_date = request.getParameter("ed_date");
		st_date = request.getParameter("st_date");
		
		String kwd = keyword.toString();
		
		logger.info(" keyword : " + keyword);
		logger.info("search : " + search);
		logger.info("search : " + search.length());
		logger.info("datesearch : " + datesearch);
		logger.info("datesearch : " + datesearch.length());
		logger.info("ed_date : " + ed_date);
		logger.info("st_date : " + st_date);
		logger.info("searchall : " + searchall);
		logger.info("kwd : " + kwd);
		
		if(kwd!=null && ed_date==""){
			search = "ea_doccd";
		}else if(ed_date!=null && keyword==""){
			datesearch = "ea_insertdate";
		}else if(keyword!=null && ed_date!=null){
			datesearch = "ea_insertdate";
			search = "ea_doccd";
		}else if(kwd=="ALL"){
			searchall = "ALL";
		}
	    
	    Object infoObj = httpsession.getAttribute("eminfo");
		EmInfoVO infoList = new EmInfoVO();
	    infoList = (EmInfoVO)infoObj;
	    String emno = infoList.getEmno();
	    
	    pageSize =  10;
	    String cp = request.getParameter("curPage");
	    if(cp!=null){
	    	curPage = Integer.parseInt(cp);
	    }else{
	    	curPage = 1;
	    }
	    logger.info("gs + ps + cp :  "+ pageSize + ", " + curPage);
	    
	    EaVO evo = new EaVO();
	    evo.setEd_date(ed_date);
	    evo.setSt_date(st_date);
	    evo.setDatesearch(datesearch);
	    evo.setSearch(search);
	    evo.setKeyword(keyword);
	    evo.setEmno(emno);
	    evo.setPageSize(pageSize);
	    evo.setCurPage(curPage);
	    
	    List<EaVO> list = eaService.eaSelectAll(evo);
	    
	    int listCnt = list.size();
		logger.info("listCnt : " + listCnt);
		
		for(int i=0; i<listCnt; i++){
			EaVO eavo = null;
			eavo = new EaVO();
			eavo = list.get(i);
			pageNo = eavo.getPageNo();
			totalCount = eavo.getTotalCount();
			curPage = eavo.getCurPage();
			groupSize = eavo.getGroupSize();
			logger.info("����¡�� ������ : " + pageNo + ", " + totalCount + ", " + curPage + ", " + groupSize);
		}
	
		param.setKeyword(param.getKeyword());
		param.setEd_date(param.getEd_date());
		param.setSt_date(param.getSt_date());
	
	
		ModelAndView mav = new ModelAndView();
		mav.addObject("param",param);
		mav.addObject("ed_date", ed_date);
		mav.addObject("st_date", st_date);
		mav.addObject("keyword", keyword);
		mav.addObject("pageNo", pageNo);
		mav.addObject("totalCount", totalCount);
		mav.addObject("curPage", curPage);
		mav.addObject("groupSize", groupSize);
		mav.addObject("eaList", list);
		mav.setViewName("ea/eamylistall");
		logger.info("[log] EaController.eaMyListSearch ���� <<< ");
		return mav;
	}
	
	/* ���� �����ؾ��� ���� SEARCH */
	@RequestMapping("/eaapprovesearch")
	public ModelAndView eaApproveSearch(@ModelAttribute EaVO param, HttpServletRequest request, HttpSession httpsession){
		logger.info("[log] EaController.eaApproveSearch ���� >>> ");
		String keyword = "";
		String search = "";
		String datesearch = "";
		String st_date = "";
		String ed_date = "";
		String searchall = "";
		
		keyword = request.getParameter("keyword");
		ed_date = request.getParameter("ed_date");
		st_date = request.getParameter("st_date");
		
		String kwd = keyword.toString();
		
		logger.info(" keyword : " + keyword);
		logger.info("search : " + search);
		logger.info("search : " + search.length());
		logger.info("datesearch : " + datesearch);
		logger.info("datesearch : " + datesearch.length());
		logger.info("ed_date : " + ed_date);
		logger.info("st_date : " + st_date);
		logger.info("searchall : " + searchall);
		logger.info("kwd : " + kwd);
		
		if(kwd!=null && ed_date==""){
			search = "ea_doccd";
		}else if(ed_date!=null && keyword==""){
			datesearch = "ea_insertdate";
		}else if(keyword!=null && ed_date!=null){
			datesearch = "ea_insertdate";
			search = "ea_doccd";
		}else if(kwd=="ALL"){
			searchall = "ALL";
		}
	    
	    Object infoObj = httpsession.getAttribute("eminfo");
		EmInfoVO infoList = new EmInfoVO();
	    infoList = (EmInfoVO)infoObj;
	    String emno = infoList.getEmno();
	    
	    pageSize =  10;
	    String cp = request.getParameter("curPage");
	    if(cp!=null){
	    	curPage = Integer.parseInt(cp);
	    }else{
	    	curPage = 1;
	    }
	    logger.info("gs + ps + cp :  "+ pageSize + ", " + curPage);
	    
	    EaVO evo = new EaVO();
	    evo.setEd_date(ed_date);
	    evo.setSt_date(st_date);
	    evo.setDatesearch(datesearch);
	    evo.setSearch(search);
	    evo.setKeyword(keyword);
	    evo.setEmno(emno);
	    evo.setPageSize(pageSize);
	    evo.setCurPage(curPage);
	    
	    List<EaVO> list = eaService.eaMyAppList(evo);
	    
	    int listCnt = list.size();
		logger.info("listCnt : " + listCnt);
		
		for(int i=0; i<listCnt; i++){
			EaVO eavo = null;
			eavo = new EaVO();
			eavo = list.get(i);
			pageNo = eavo.getPageNo();
			totalCount = eavo.getTotalCount();
			curPage = eavo.getCurPage();
			groupSize = eavo.getGroupSize();
			logger.info("����¡�� ������ : " + pageNo + ", " + totalCount + ", " + curPage + ", " + groupSize);
		}
	
		param.setKeyword(param.getKeyword());
		param.setEd_date(param.getEd_date());
		param.setSt_date(param.getSt_date());
	
	
		ModelAndView mav = new ModelAndView();
		mav.addObject("param",param);
		mav.addObject("ed_date", ed_date);
		mav.addObject("st_date", st_date);
		mav.addObject("keyword", keyword);
		mav.addObject("pageNo", pageNo);
		mav.addObject("totalCount", totalCount);
		mav.addObject("curPage", curPage);
		mav.addObject("groupSize", groupSize);
		mav.addObject("eaList", list);
		mav.setViewName("ea/eamyapplist");
		logger.info("[log] EaController.eaApproveSearch ���� <<< ");
		return mav;
	}
	
	/* �� ���ڰ��� �Ϸ� SEARCH */
	@RequestMapping("/eamylistfnsearch")
	public ModelAndView eaMylistFnSearch(@ModelAttribute EaVO param, HttpServletRequest request, HttpSession httpsession){
		logger.info("[log] EaController.eaMylistFnSearch ���� >>> ");
		String keyword = "";
		String search = "";
		String datesearch = "";
		String st_date = "";
		String ed_date = "";
		String searchall = "";
		
		keyword = request.getParameter("keyword");
		ed_date = request.getParameter("ed_date");
		st_date = request.getParameter("st_date");
		
		String kwd = keyword.toString();
		
		logger.info(" keyword : " + keyword);
		logger.info("search : " + search);
		logger.info("search : " + search.length());
		logger.info("datesearch : " + datesearch);
		logger.info("datesearch : " + datesearch.length());
		logger.info("ed_date : " + ed_date);
		logger.info("st_date : " + st_date);
		logger.info("searchall : " + searchall);
		logger.info("kwd : " + kwd);
		
		if(kwd!=null && ed_date==""){
			search = "ea_doccd";
		}else if(ed_date!=null && keyword==""){
			datesearch = "ea_insertdate";
		}else if(keyword!=null && ed_date!=null){
			datesearch = "ea_insertdate";
			search = "ea_doccd";
		}else if(kwd=="ALL"){
			searchall = "ALL";
		}
	    
	    Object infoObj = httpsession.getAttribute("eminfo");
		EmInfoVO infoList = new EmInfoVO();
	    infoList = (EmInfoVO)infoObj;
	    String emno = infoList.getEmno();
	    
	    pageSize =  10;
	    String cp = request.getParameter("curPage");
	    if(cp!=null){
	    	curPage = Integer.parseInt(cp);
	    }else{
	    	curPage = 1;
	    }
	    logger.info("gs + ps + cp :  "+ pageSize + ", " + curPage);
	    
	    EaVO evo = new EaVO();
	    evo.setEd_date(ed_date);
	    evo.setSt_date(st_date);
	    evo.setDatesearch(datesearch);
	    evo.setSearch(search);
	    evo.setKeyword(keyword);
	    evo.setEmno(emno);
	    evo.setPageSize(pageSize);
	    evo.setCurPage(curPage);
	    
	    List<EaVO> list = eaService.eaSelectAllFN(evo);
	    
	    int listCnt = list.size();
		logger.info("listCnt : " + listCnt);
		
		for(int i=0; i<listCnt; i++){
			EaVO eavo = null;
			eavo = new EaVO();
			eavo = list.get(i);
			pageNo = eavo.getPageNo();
			totalCount = eavo.getTotalCount();
			curPage = eavo.getCurPage();
			groupSize = eavo.getGroupSize();
			logger.info("����¡�� ������ : " + pageNo + ", " + totalCount + ", " + curPage + ", " + groupSize);
		}
	
		param.setKeyword(param.getKeyword());
		param.setEd_date(param.getEd_date());
		param.setSt_date(param.getSt_date());
	
	
		ModelAndView mav = new ModelAndView();
		mav.addObject("param",param);
		mav.addObject("ed_date", ed_date);
		mav.addObject("st_date", st_date);
		mav.addObject("keyword", keyword);
		mav.addObject("pageNo", pageNo);
		mav.addObject("totalCount", totalCount);
		mav.addObject("curPage", curPage);
		mav.addObject("groupSize", groupSize);
		mav.addObject("eaList", list);
		mav.setViewName("ea/eamylistfn");
		logger.info("[log] EaController.eaMylistFnSearch ���� <<< ");
		return mav;
	}
	
	/* �� ���ڰ��� ���� SEARCH */
	@RequestMapping("/eamylistpgsearch")
	public ModelAndView eaMylistPgSearch(@ModelAttribute EaVO param, HttpServletRequest request, HttpSession httpsession){
		logger.info("[log] EaController.eaMylistPgSearch ���� >>> ");
		String keyword = "";
		String search = "";
		String datesearch = "";
		String st_date = "";
		String ed_date = "";
		String searchall = "";
		
		keyword = request.getParameter("keyword");
		ed_date = request.getParameter("ed_date");
		st_date = request.getParameter("st_date");
		
		String kwd = keyword.toString();
		
		logger.info(" keyword : " + keyword);
		logger.info("search : " + search);
		logger.info("search : " + search.length());
		logger.info("datesearch : " + datesearch);
		logger.info("datesearch : " + datesearch.length());
		logger.info("ed_date : " + ed_date);
		logger.info("st_date : " + st_date);
		logger.info("searchall : " + searchall);
		logger.info("kwd : " + kwd);
		
		if(kwd!=null && ed_date==""){
			search = "ea_doccd";
		}else if(ed_date!=null && keyword==""){
			datesearch = "ea_insertdate";
		}else if(keyword!=null && ed_date!=null){
			datesearch = "ea_insertdate";
			search = "ea_doccd";
		}else if(kwd=="ALL"){
			searchall = "ALL";
		}
	    
	    Object infoObj = httpsession.getAttribute("eminfo");
		EmInfoVO infoList = new EmInfoVO();
	    infoList = (EmInfoVO)infoObj;
	    String emno = infoList.getEmno();
	    
	    pageSize =  10;
	    String cp = request.getParameter("curPage");
	    if(cp!=null){
	    	curPage = Integer.parseInt(cp);
	    }else{
	    	curPage = 1;
	    }
	    logger.info("gs + ps + cp :  "+ pageSize + ", " + curPage);
	    
	    EaVO evo = new EaVO();
	    evo.setEd_date(ed_date);
	    evo.setSt_date(st_date);
	    evo.setDatesearch(datesearch);
	    evo.setSearch(search);
	    evo.setKeyword(keyword);
	    evo.setEmno(emno);
	    evo.setPageSize(pageSize);
	    evo.setCurPage(curPage);
	    
	    List<EaVO> list = eaService.eaSelectAllPG(evo);
	    
	    int listCnt = list.size();
		logger.info("listCnt : " + listCnt);
		
		for(int i=0; i<listCnt; i++){
			EaVO eavo = null;
			eavo = new EaVO();
			eavo = list.get(i);
			pageNo = eavo.getPageNo();
			totalCount = eavo.getTotalCount();
			curPage = eavo.getCurPage();
			groupSize = eavo.getGroupSize();
			logger.info("����¡�� ������ : " + pageNo + ", " + totalCount + ", " + curPage + ", " + groupSize);
		}
	
		param.setKeyword(param.getKeyword());
		param.setEd_date(param.getEd_date());
		param.setSt_date(param.getSt_date());
	
	
		ModelAndView mav = new ModelAndView();
		mav.addObject("param",param);
		mav.addObject("ed_date", ed_date);
		mav.addObject("st_date", st_date);
		mav.addObject("keyword", keyword);
		mav.addObject("pageNo", pageNo);
		mav.addObject("totalCount", totalCount);
		mav.addObject("curPage", curPage);
		mav.addObject("groupSize", groupSize);
		mav.addObject("eaList", list);
		mav.setViewName("ea/eamylistpg");
		logger.info("[log] EaController.eaMylistPgSearch ���� <<< ");
		return mav;
	}
	
	/* �� ���ڰ��� ���� SEARCH */
	@RequestMapping("/eamylistrtsearch")
	public ModelAndView eaMylistRtSearch(@ModelAttribute EaVO param, HttpServletRequest request, HttpSession httpsession){
		logger.info("[log] EaController.eaMylistFnSearch ���� >>> ");
		String keyword = "";
		String search = "";
		String datesearch = "";
		String st_date = "";
		String ed_date = "";
		String searchall = "";
		
		keyword = request.getParameter("keyword");
		ed_date = request.getParameter("ed_date");
		st_date = request.getParameter("st_date");
		
		String kwd = keyword.toString();
		
		logger.info(" keyword : " + keyword);
		logger.info("search : " + search);
		logger.info("search : " + search.length());
		logger.info("datesearch : " + datesearch);
		logger.info("datesearch : " + datesearch.length());
		logger.info("ed_date : " + ed_date);
		logger.info("st_date : " + st_date);
		logger.info("searchall : " + searchall);
		logger.info("kwd : " + kwd);
		
		if(kwd!=null && ed_date==""){
			search = "ea_doccd";
		}else if(ed_date!=null && keyword==""){
			datesearch = "ea_insertdate";
		}else if(keyword!=null && ed_date!=null){
			datesearch = "ea_insertdate";
			search = "ea_doccd";
		}else if(kwd=="ALL"){
			searchall = "ALL";
		}
	    
	    Object infoObj = httpsession.getAttribute("eminfo");
		EmInfoVO infoList = new EmInfoVO();
	    infoList = (EmInfoVO)infoObj;
	    String emno = infoList.getEmno();
	    
	    pageSize =  10;
	    String cp = request.getParameter("curPage");
	    if(cp!=null){
	    	curPage = Integer.parseInt(cp);
	    }else{
	    	curPage = 1;
	    }
	    logger.info("gs + ps + cp :  "+ pageSize + ", " + curPage);
	    
	    EaVO evo = new EaVO();
	    evo.setEd_date(ed_date);
	    evo.setSt_date(st_date);
	    evo.setDatesearch(datesearch);
	    evo.setSearch(search);
	    evo.setKeyword(keyword);
	    evo.setEmno(emno);
	    evo.setPageSize(pageSize);
	    evo.setCurPage(curPage);
	    
	    List<EaVO> list = eaService.eaSelectAllRT(evo);
	    
	    int listCnt = list.size();
		logger.info("listCnt : " + listCnt);
		
		for(int i=0; i<listCnt; i++){
			EaVO eavo = null;
			eavo = new EaVO();
			eavo = list.get(i);
			pageNo = eavo.getPageNo();
			totalCount = eavo.getTotalCount();
			curPage = eavo.getCurPage();
			groupSize = eavo.getGroupSize();
			logger.info("����¡�� ������ : " + pageNo + ", " + totalCount + ", " + curPage + ", " + groupSize);
		}
	
		param.setKeyword(param.getKeyword());
		param.setEd_date(param.getEd_date());
		param.setSt_date(param.getSt_date());
	
	
		ModelAndView mav = new ModelAndView();
		mav.addObject("param",param);
		mav.addObject("ed_date", ed_date);
		mav.addObject("st_date", st_date);
		mav.addObject("keyword", keyword);
		mav.addObject("pageNo", pageNo);
		mav.addObject("totalCount", totalCount);
		mav.addObject("curPage", curPage);
		mav.addObject("groupSize", groupSize);
		mav.addObject("eaList", list);
		mav.setViewName("ea/eamylistrt");
		logger.info("[log] EaController.eaMylistRtSearch ���� <<< ");
		return mav;
	}
	
	/* �� ���ڰ��� 05 SEARCH */
	@RequestMapping("/eamylistsbsearch")
	public ModelAndView eaMylistSbSearch(@ModelAttribute EaVO param, HttpServletRequest request, HttpSession httpsession){
		logger.info("[log] EaController.eaMylistSbSearch ���� >>> ");
		String keyword = "";
		String search = "";
		String datesearch = "";
		String st_date = "";
		String ed_date = "";
		String searchall = "";
		
		keyword = request.getParameter("keyword");
		ed_date = request.getParameter("ed_date");
		st_date = request.getParameter("st_date");
		
		String kwd = keyword.toString();
		
		logger.info(" keyword : " + keyword);
		logger.info("search : " + search);
		logger.info("search : " + search.length());
		logger.info("datesearch : " + datesearch);
		logger.info("datesearch : " + datesearch.length());
		logger.info("ed_date : " + ed_date);
		logger.info("st_date : " + st_date);
		logger.info("searchall : " + searchall);
		logger.info("kwd : " + kwd);
		
		if(kwd!=null && ed_date==""){
			search = "ea_doccd";
		}else if(ed_date!=null && keyword==""){
			datesearch = "ea_insertdate";
		}else if(keyword!=null && ed_date!=null){
			datesearch = "ea_insertdate";
			search = "ea_doccd";
		}else if(kwd=="ALL"){
			searchall = "ALL";
		}
	    
	    Object infoObj = httpsession.getAttribute("eminfo");
		EmInfoVO infoList = new EmInfoVO();
	    infoList = (EmInfoVO)infoObj;
	    String emno = infoList.getEmno();
	    
	    pageSize =  10;
	    String cp = request.getParameter("curPage");
	    if(cp!=null){
	    	curPage = Integer.parseInt(cp);
	    }else{
	    	curPage = 1;
	    }
	    logger.info("gs + ps + cp :  "+ pageSize + ", " + curPage);
	    
	    EaVO evo = new EaVO();
	    evo.setEd_date(ed_date);
	    evo.setSt_date(st_date);
	    evo.setDatesearch(datesearch);
	    evo.setSearch(search);
	    evo.setKeyword(keyword);
	    evo.setEmno(emno);
	    evo.setPageSize(pageSize);
	    evo.setCurPage(curPage);
	    
	    List<EaVO> list = eaService.eaSelectAllSB(evo);
	    
	    int listCnt = list.size();
		logger.info("listCnt : " + listCnt);
		
		for(int i=0; i<listCnt; i++){
			EaVO eavo = null;
			eavo = new EaVO();
			eavo = list.get(i);
			pageNo = eavo.getPageNo();
			totalCount = eavo.getTotalCount();
			curPage = eavo.getCurPage();
			groupSize = eavo.getGroupSize();
			logger.info("����¡�� ������ : " + pageNo + ", " + totalCount + ", " + curPage + ", " + groupSize);
		}
	
		param.setKeyword(param.getKeyword());
		param.setEd_date(param.getEd_date());
		param.setSt_date(param.getSt_date());
	
	
		ModelAndView mav = new ModelAndView();
		mav.addObject("param",param);
		mav.addObject("ed_date", ed_date);
		mav.addObject("st_date", st_date);
		mav.addObject("keyword", keyword);
		mav.addObject("pageNo", pageNo);
		mav.addObject("totalCount", totalCount);
		mav.addObject("curPage", curPage);
		mav.addObject("groupSize", groupSize);
		mav.addObject("eaList", list);
		mav.setViewName("ea/eamylistsb");
		logger.info("[log] EaController.eaMylistSbSearch ���� <<< ");
		return mav;
	}
	
	/* ���ڰ��� �������� Ŭ�� �������� �̵� */
	@RequestMapping("/eadoccdform")
	public ModelAndView eaDoccdForm(@ModelAttribute EaVO param, HttpSession httpsession){
		logger.info("[log] EaController.eaDocLib ���� >>> ");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("ea/eadoccdform");
		logger.info("[log] EaController.eaDocLib ���� <<< ");
		return mav;
	}
	
	/* ���ڰ��繮����İԽ��� �̵� */
	@RequestMapping("/eadoclib")
	public ModelAndView eaDocLib(@ModelAttribute EaVO param, HttpSession httpsession){
		logger.info("[log] EaController.eaDocLib ���� >>> ");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("ea/eadoclib");
		logger.info("[log] EaController.eaDocLib ���� <<< ");
		return mav;
	}
	
	/* ���ڰ��� ���� �ٿ�ε� */
	@RequestMapping("/eafiledownload")
	public ModelAndView eaFileDownload(@ModelAttribute EaVO param, HttpSession httpsession){
		logger.info("[log] EaController.eaFileDownload ���� >>> ");

		String ea_file = "";
		String ea_file1 = "";
		String ea_file2 = "";
		ea_file = param.getEa_file();
		
		logger.info("ea_file : " + ea_file);
		logger.info("ea_file1 : " + ea_file1);
		logger.info("ea_file2 : " + ea_file2);
		
		ModelAndView mav = new ModelAndView();
		
		if(ea_file!=null){
			mav.addObject("ea_file", ea_file);
			mav.addObject("filePath", EAFILEPATH);
			mav.setViewName("ea/eadownload");
		}
		logger.info("[log] EaController.eaFileDownload ���� <<< ");
		return mav;
	}
	
	/* ���ڰ��� ��� �ٿ�ε� */
	@RequestMapping("/eadocdownload")
	public ModelAndView eaDocDownload(@ModelAttribute EaVO param, HttpServletRequest request, HttpSession httpsession){
		logger.info("[log] EaController.eaDocDownload ���� >>> ");
		
		String ea_doccd = request.getParameter("ea_doccd");
		String ea_file = request.getParameter("ea_file");
		logger.info("ea_doccd : " + ea_doccd);
		logger.info("ea_file : " + ea_file);

		ModelAndView mav = new ModelAndView();
		
		if(ea_file!=null){
			mav.addObject("ea_file", ea_file);
			mav.addObject("filePath", EAFORMATH);
			mav.setViewName("ea/eadownload");
		}
		logger.info("[log] EaController.eaDocDownload ���� <<< ");
		return mav;
	}
	
	/* ���ڰ��� 05�� �̺�Ʈ */
	@RequestMapping("/eastand")
	public ModelAndView eaStand(@ModelAttribute EaVO param, HttpServletRequest request, HttpSession httpsession){
		logger.info("[log] EaController.eareject ���� >>> ");
		String ea_no = "";
		String ea_logno = "";
		String ea_lineno = "";
		String ea_stepno = "";
		String ea_status = "";		//EA_STATUS			�������
		String ea_sign = "";		//EA_SIGN			����� ����
		String ea_sign1 = "";		//EA_SIGN1			ù��°������ ����
		String ea_sign2 = "";		//EA_SIGN2			�ι�°������ ����
		String ea_file = "";		//EA_FILE			����� ����
		String ea_file1 = "";		//EA_FILE1			ù��������� ����
		String ea_file2 = "";
		String ea_subject = "";		//EA_SUBJECT		���ڰ�������
		String ea_memo = ""; 		//EA_MEMO			���ڰ��系��
		
		List <EaVO> list = null;
		EaVO evo = null;
		evo = new EaVO();
		
		Object infoObj = httpsession.getAttribute("eminfo");
		EmInfoVO infoList = new EmInfoVO();
	    infoList = (EmInfoVO)infoObj;
	    String emno = infoList.getEmno();
	    String emname = infoList.getEmname();
	    String ea_sign0 = infoList.getEmsign();
	    
	    logger.info("����� ��� : " + emno);
	    logger.info("����� �̸� : " + emname);
	    logger.info("����� ���� : " + ea_sign0);
	    
		/* �α�ä�� ���� */
		list = eaService.chaebunEALOG(param);
		evo = list.get(0);
		ea_logno = evo.getEa_logno();
		ea_logno = Chaebun.chaebunEALOG(ea_logno);
		logger.info("ea_logno : " + ea_logno);
		
		/* �α� �μ�Ʈ */
		EaFileUploadUtil multi = new EaFileUploadUtil();
		boolean bFlag = false;
		bFlag = multi.fileUpload(request, EAFILEPATH);
		logger.info("bFlag >>> : " + bFlag );
		
		/* jsp���� ��������� */
		Object obj = httpsession.getAttribute("eaList");
		List<EaVO> ealist = (List<EaVO>)obj;
		for(int i=0; i<ealist.size(); i++){
			EaVO eavo = null;
			eavo = new EaVO();
			eavo = ealist.get(i);
			ea_stepno = eavo.getEa_stepno();
			logger.info("ea_stepno : " + ea_stepno);
			ea_lineno = eavo.getEa_lineno();
			logger.info("ea_lineno : " + ea_lineno);
			ea_subject = eavo.getEa_subject();
			logger.info("ea_subject : " + ea_subject);
			ea_memo = eavo.getEa_memo();
			logger.info("ea_memo : " + ea_memo);
			ea_status = eavo.getEa_status();
			logger.info("ea_status : " + ea_status);
			ea_file = eavo.getEa_file();
			logger.info("ea_file : " + ea_file);
			ea_no = eavo.getEa_no();
			logger.info("ea_no : " + ea_no);
			ea_sign = eavo.getEa_sign();
			logger.info("ea_sign : " + ea_sign);
			ea_sign1 = eavo.getEa_sign1();
			logger.info("ea_sign1 : " + ea_sign1);
			ea_sign2 = eavo.getEa_sign2();
			logger.info("ea_sign2 : " + ea_sign2);
			ea_file1 = eavo.getEa_file1();
			logger.info("ea_file1 : " + ea_file1);
			ea_file2 = eavo.getEa_file2();
			logger.info("ea_file2 : " + ea_file2);
		}
		
		if(bFlag){
			Enumeration<String> files = multi.getFileNames();
			String filename = files.nextElement();
			
			evo = new EaVO();
			
			evo.setEa_logno(ea_logno);
			evo.setEa_lineno(ea_lineno);
			evo.setEa_stepno(ea_stepno);
			evo.setEa_no(ea_no);
			
			
			if(ea_status.equals("01")){
			evo.setEa_status("05");	  
			evo.setEa_sign1("");
			evo.setEa_sign2(ea_sign2);
			evo.setEa_file1(filename); // ���Ͼ��ε�
			evo.setEa_file2(ea_file2);
			}
			else if(ea_status.equals("02")){
			evo.setEa_status("05");
			evo.setEa_sign1(ea_sign1);
			evo.setEa_file1(ea_file1); 
			evo.setEa_sign2("");
			evo.setEa_file2(filename); // ���Ͼ��ε�
			}
			evo.setEa_sign(ea_sign);
			evo.setEa_file(ea_file);
			
			
			int nCnt = eaService.eaLogInsert(evo);
			
			if(nCnt>0){
				logger.info("[log] ealog_insert success");
				/* ���ڰ������̺� ������Ʈ */
				evo = new EaVO();
				evo.setEa_no(ea_no);
				evo.setEa_logno(ea_logno);
				evo.setEa_status("05");
				evo.setEa_fileupload(filename);
				evo.setEa_nextemno(emno); 
				
				int sCnt = eaService.eaTableUpdate(evo);
				
				if(sCnt>0){
					logger.info("[log] eatable_update success");
				}else{
					logger.info("[log] eatable_update fail");
				}
			}else{
				logger.info("[log] ealog_insert fail");
			}
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("ea/earesult");
		mav.addObject("ea_no", ea_no);
		mav.addObject("ea_lineno", ea_lineno);
		mav.addObject("ea_stepno", ea_stepno);
		mav.addObject("ea_logno", ea_logno);
		mav.addObject("ea_empno", emno);
		logger.info("[log] EaController.eastand ���� <<< ");
		return mav;
	}
	
	/* ���ڰ��� 04�� �̺�Ʈ */
	@RequestMapping("/eareject")
	public ModelAndView eaReject(@ModelAttribute EaVO param, HttpServletRequest request, HttpSession httpsession){
		logger.info("[log] EaController.eareject ���� >>> ");
		String ea_no = "";
		String ea_logno = "";
		String ea_lineno = "";
		String ea_stepno = "";
		String ea_status = "";		//EA_STATUS			�������
		String ea_sign = "";		//EA_SIGN			����� ����
		String ea_sign1 = "";		//EA_SIGN1			ù��°������ ����
		String ea_sign2 = "";		//EA_SIGN2			�ι�°������ ����
		String ea_file = "";		//EA_FILE			����� ����
		String ea_file1 = "";		//EA_FILE1			ù��������� ����
		String ea_file2 = "";
		String ea_subject = "";		//EA_SUBJECT		���ڰ�������
		String ea_memo = ""; 		//EA_MEMO			���ڰ��系��
		
		List <EaVO> list = null;
		EaVO evo = null;
		evo = new EaVO();
		
		Object infoObj = httpsession.getAttribute("eminfo");
		EmInfoVO infoList = new EmInfoVO();
	    infoList = (EmInfoVO)infoObj;
	    String emno = infoList.getEmno();
	    String emname = infoList.getEmname();
	    String ea_sign0 = infoList.getEmsign();
	    
	    logger.info("����� ��� : " + emno);
	    logger.info("����� �̸� : " + emname);
	    logger.info("����� ���� : " + ea_sign0);
	    
		/* �α�ä�� ���� */
		list = eaService.chaebunEALOG(param);
		evo = list.get(0);
		ea_logno = evo.getEa_logno();
		ea_logno = Chaebun.chaebunEALOG(ea_logno);
		logger.info("ea_logno : " + ea_logno);
		
		/* �α� �μ�Ʈ */
		EaFileUploadUtil multi = new EaFileUploadUtil();
		boolean bFlag = false;
		bFlag = multi.fileUpload(request, EAFILEPATH);
		logger.info("bFlag >>> : " + bFlag );
		
		/* jsp���� ��������� */
		Object obj = httpsession.getAttribute("eaList");
		List<EaVO> ealist = (List<EaVO>)obj;
		for(int i=0; i<ealist.size(); i++){
			EaVO eavo = null;
			eavo = new EaVO();
			eavo = ealist.get(i);
			ea_stepno = eavo.getEa_stepno();
			logger.info("ea_stepno : " + ea_stepno);
			ea_lineno = eavo.getEa_lineno();
			logger.info("ea_lineno : " + ea_lineno);
			ea_subject = eavo.getEa_subject();
			logger.info("ea_subject : " + ea_subject);
			ea_memo = eavo.getEa_memo();
			logger.info("ea_memo : " + ea_memo);
			ea_status = eavo.getEa_status();
			logger.info("ea_status : " + ea_status);
			ea_file = eavo.getEa_file();
			logger.info("ea_file : " + ea_file);
			ea_no = eavo.getEa_no();
			logger.info("ea_no : " + ea_no);
			ea_sign = eavo.getEa_sign();
			logger.info("ea_sign : " + ea_sign);
			ea_sign1 = eavo.getEa_sign1();
			logger.info("ea_sign1 : " + ea_sign1);
			ea_sign2 = eavo.getEa_sign2();
			logger.info("ea_sign2 : " + ea_sign2);
			ea_file1 = eavo.getEa_file1();
			logger.info("ea_file1 : " + ea_file1);
			ea_file2 = eavo.getEa_file2();
			logger.info("ea_file2 : " + ea_file2);
		}
		
		if(bFlag){
			Enumeration<String> files = multi.getFileNames();
			String filename = files.nextElement();
			
			evo = new EaVO();
			
			evo.setEa_logno(ea_logno);
			evo.setEa_lineno(ea_lineno);
			evo.setEa_stepno(ea_stepno);
			evo.setEa_no(ea_no);
			
			
			if(ea_status.equals("01")){
			evo.setEa_status("04");	
			evo.setEa_sign1("");
			evo.setEa_sign2(ea_sign2);
			evo.setEa_file1(filename); // ���Ͼ��ε�
			evo.setEa_file2(ea_file2);
			}
			else if(ea_status.equals("02")){
			evo.setEa_status("04");
			evo.setEa_sign1(ea_sign1);
			evo.setEa_file1(ea_file1); 
			evo.setEa_sign2("");
			evo.setEa_file2(filename); // ���Ͼ��ε�
			}
			evo.setEa_sign(ea_sign);
			evo.setEa_file(ea_file);
			
			
			int nCnt = eaService.eaLogInsert(evo);
			
			if(nCnt>0){
				logger.info("[log] ealog_insert success");
				/* ���ڰ������̺� ������Ʈ */
				evo = new EaVO();
				evo.setEa_no(ea_no);
				evo.setEa_logno(ea_logno);
				evo.setEa_status("04");
				evo.setEa_fileupload(filename);
				evo.setEa_nextemno(emno); 
				
				int sCnt = eaService.eaTableUpdate(evo);
				
				if(sCnt>0){
					logger.info("[log] eatable_update success");
				}else{
					logger.info("[log] eatable_update fail");
				}
			}else{
				logger.info("[log] ealog_insert fail");
			}
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("ea/earesult");
		mav.addObject("ea_no", ea_no);
		mav.addObject("ea_lineno", ea_lineno);
		mav.addObject("ea_stepno", ea_stepno);
		mav.addObject("ea_logno", ea_logno);
		mav.addObject("ea_empno", emno);
		logger.info("[log] EaController.eareject ���� <<< ");
		return mav;
	}
	
	/* ���ڰ��� 02�� �̺�Ʈ */
	@RequestMapping("/eaapproval")
	public ModelAndView eaApproval(@ModelAttribute EaVO param, HttpServletRequest request, HttpSession httpsession){
		logger.info("[log] EaController.eaApproval ���� >>> ");
		String ea_no = "";
		String ea_logno = "";
		String ea_lineno = "";
		String ea_stepno = "";
		String ea_status = "";		//EA_STATUS			�������
		String ea_sign = "";		//EA_SIGN			����� ����
		String ea_sign1 = "";		//EA_SIGN1			ù��°������ ����
		String ea_sign2 = "";		//EA_SIGN2			�ι�°������ ����
		String ea_file = "";		//EA_FILE			����� ����
		String ea_file1 = "";		//EA_FILE1			ù��������� ����
		String ea_file2 = "";
		String ea_subject = "";		//EA_SUBJECT		���ڰ�������
		String ea_memo = ""; 		//EA_MEMO			���ڰ��系��
		String ea_nextemno = "";
		
		List <EaVO> list = null;
		EaVO evo = null;
		evo = new EaVO();
		
		Object infoObj = httpsession.getAttribute("eminfo");
		EmInfoVO infoList = new EmInfoVO();
	    infoList = (EmInfoVO)infoObj;
	    String emno = infoList.getEmno();
	    String emname = infoList.getEmname();
	    String ea_sign0 = infoList.getEmsign();
	    
	    logger.info("����� ��� : " + emno);
	    logger.info("����� �̸� : " + emname);
	    logger.info("����� ���� : " + ea_sign0);
	    
		/* �α�ä�� ���� */
		list = eaService.chaebunEALOG(param);
		evo = list.get(0);
		ea_logno = evo.getEa_logno();
		ea_logno = Chaebun.chaebunEALOG(ea_logno);
		logger.info("ea_logno : " + ea_logno);
		
		/* �α� �μ�Ʈ */
		EaFileUploadUtil multi = new EaFileUploadUtil();
		boolean bFlag = false;
		bFlag = multi.fileUpload(request, EAFILEPATH);
		logger.info("bFlag >>> : " + bFlag );
		
		/* jsp���� ��������� */
		Object obj = httpsession.getAttribute("eaList");
		List<EaVO> ealist = (List<EaVO>)obj;
		for(int i=0; i<ealist.size(); i++){
			EaVO eavo = null;
			eavo = new EaVO();
			eavo = ealist.get(i);
			ea_stepno = eavo.getEa_stepno();
			logger.info("ea_stepno : " + ea_stepno);
			ea_lineno = eavo.getEa_lineno();
			logger.info("ea_lineno : " + ea_lineno);
			ea_subject = eavo.getEa_subject();
			logger.info("ea_subject : " + ea_subject);
			ea_memo = eavo.getEa_memo();
			logger.info("ea_memo : " + ea_memo);
			ea_status = eavo.getEa_status();
			logger.info("ea_status : " + ea_status);
			ea_file = eavo.getEa_file();
			logger.info("ea_file : " + ea_file);
			ea_no = eavo.getEa_no();
			logger.info("ea_no : " + ea_no);
			ea_sign = eavo.getEa_sign();
			logger.info("ea_sign : " + ea_sign);
			ea_sign1 = eavo.getEa_sign1();
			logger.info("ea_sign1 : " + ea_sign1);
			ea_sign2 = eavo.getEa_sign2();
			logger.info("ea_sign2 : " + ea_sign2);
			ea_file1 = eavo.getEa_file1();
			logger.info("ea_file1 : " + ea_file1);
			ea_file2 = eavo.getEa_file2();
			logger.info("ea_file2 : " + ea_file2);
		}
		
		/* �������̺� ������Ʈ�� ������ select */
		EaVO evo1 = new EaVO();
		evo1.setEa_stepno(ea_stepno);
		
		List<EaVO> eaList = eaService.eaNextEmno(evo1);
		for(int i=0; i<eaList.size(); i++){
			EaVO eavo = new EaVO();
			eavo = (EaVO) eaList.get(i);
			ea_nextemno = eavo.getEa_empno2();
			logger.info("ea_nextemno : " + ea_nextemno);
		}
		
		if(bFlag){
			Enumeration<String> files = multi.getFileNames();
			String filename = files.nextElement();
			
			evo = new EaVO();
			
			evo.setEa_logno(ea_logno);
			evo.setEa_lineno(ea_lineno);
			evo.setEa_stepno(ea_stepno);
			evo.setEa_no(ea_no);
			
			
			if(ea_status.equals("01")){
			evo.setEa_status("02");	
			evo.setEa_sign1(ea_sign0);
			evo.setEa_sign2(ea_sign2);
			evo.setEa_file1(filename); // ���Ͼ��ε�
			evo.setEa_file2(ea_file2);
			}
			else if(ea_status.equals("02")){
			evo.setEa_status("03");
			evo.setEa_sign1(ea_sign1);
			evo.setEa_file1(ea_file1); 
			evo.setEa_sign2(ea_sign0);
			evo.setEa_file2(filename); // ���Ͼ��ε�
			}
			evo.setEa_sign(ea_sign);
			evo.setEa_file(ea_file);
			
			
			int nCnt = eaService.eaLogInsert(evo);
			
			if(nCnt>0){
				logger.info("[log] ealog_insert success");
				/* ���ڰ������̺� ������Ʈ */
				evo = new EaVO();
				evo.setEa_no(ea_no);
				evo.setEa_logno(ea_logno);
				if(ea_status.equals("01")){
					logger.info("���̺��μ�Ʈ  �����Է� if������");
					evo.setEa_nextemno(ea_nextemno);   
					evo.setEa_status("02");	
				}
				else if(ea_status.equals("02")){
					evo.setEa_nextemno(emno); 
					evo.setEa_status("03");	
				}
				evo.setEa_fileupload(filename);
				
				int sCnt = eaService.eaTableUpdate(evo);
				
				if(sCnt>0){
					logger.info("[log] eatable_update success");
				}else{
					logger.info("[log] eatable_update fail");
				}
			}else{
				logger.info("[log] ealog_insert fail");
			}
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("ea/earesult");
		mav.addObject("ea_no", ea_no);
		mav.addObject("ea_lineno", ea_lineno);
		mav.addObject("ea_stepno", ea_stepno);
		mav.addObject("ea_logno", ea_logno);
		mav.addObject("ea_empno", emno);
		logger.info("[log] EaController.eaApproval ���� <<< ");
		return mav;
	}
	
	
//	/* selectAll �ۼ��ڰ� ���� ���繮���� ��ü ��ȸ */
//	@RequestMapping("/eamylist")
//	public ModelAndView eaMyList(@ModelAttribute EaVO param, HttpServletRequest request, HttpSession httpsession){
//		logger.info("[log] EaController.eaMyList ���� >>> ");
//
//		Object infoObj = httpsession.getAttribute("eminfo");
//		EmInfoVO infoList = new EmInfoVO();
//	    infoList = (EmInfoVO)infoObj;
//	    String emno = infoList.getEmno();
//	    String emname = infoList.getEmname();
//	    logger.info("����� ��� : " + emno);
//	    logger.info("����� �̸� : " + emname);
// 
//	    param.setEmno(infoList.getEmno());
//
////	    param.setEmno("E202001280008");
//		List<EaVO> list = eaService.eaSelectAll(param);
//		
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("eaList", list);
//		mav.setViewName("ea/eamylist");
//		logger.info("[log] EaController.eaMyList ���� <<< ");
//		return mav;
//	} // end of eaMyList
	
	
	/* selectAll �ۼ��ڰ� ���� ���� ���� ����Ʈ  ��ȸ */
	@RequestMapping("/eamylistpg")
	public ModelAndView eaMyListPG(@ModelAttribute EaVO param, HttpServletRequest request, HttpSession httpsession){
		logger.info("[log] EaController.eaMyListPG ���� >>> ");

		Object infoObj = httpsession.getAttribute("eminfo");
		EmInfoVO infoList = new EmInfoVO();
	    infoList = (EmInfoVO)infoObj;
	    String emno = infoList.getEmno();
	    String emname = infoList.getEmname();
	    logger.info("����� ��� : " + emno);
	    logger.info("����� �̸� : " + emname);
 
	    pageSize =  10;
	    String cp = request.getParameter("curPage");
	    if(cp!=null){
	    	curPage = Integer.parseInt(cp);
	    }else{
	    	curPage = 1;
	    }
	    param.setEmno(emno);
	    logger.info("gs + ps + cp :  "+ pageSize + ", " + curPage);
	    param.setPageSize(pageSize);
	    param.setCurPage(curPage);
	    
		List<EaVO> list = eaService.eaSelectAllPG(param);
		
		logger.info("list : " + list);
		int listCnt = list.size();
		logger.info("listCnt : " + listCnt);
		for(int i=0; i<listCnt; i++){
			EaVO eavo = null;
			eavo = new EaVO();
			eavo = list.get(i);
			pageNo = eavo.getPageNo();
			totalCount = eavo.getTotalCount();
			curPage = eavo.getCurPage();
			groupSize = eavo.getGroupSize();
			logger.info("����¡�� ������ : " + pageNo + ", " + totalCount + ", " + curPage + ", " + groupSize);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageNo", pageNo);
		mav.addObject("totalCount", totalCount);
		mav.addObject("curPage", curPage);
		mav.addObject("groupSize", groupSize);
		mav.addObject("eaList", list);
		mav.setViewName("ea/eamylistpg");
		logger.info("[log] EaController.eaMyListPG ���� <<< ");
		return mav;
	} // end of eaMyListPG
	
	/* selectAll �ۼ��ڰ� ���� 05 ���� ����Ʈ  ��ȸ */
	@RequestMapping("/eamylistsb")
	public ModelAndView eaMyListSB(@ModelAttribute EaVO param, HttpServletRequest request, HttpSession httpsession){
		logger.info("[log] EaController.eaMyListSB ���� >>> ");

		Object infoObj = httpsession.getAttribute("eminfo");
		EmInfoVO infoList = new EmInfoVO();
	    infoList = (EmInfoVO)infoObj;
	    String emno = infoList.getEmno();
	    String emname = infoList.getEmname();
	    logger.info("����� ��� : " + emno);
	    logger.info("����� �̸� : " + emname);
 
	    pageSize =  10;
	    String cp = request.getParameter("curPage");
	    if(cp!=null){
	    	curPage = Integer.parseInt(cp);
	    }else{
	    	curPage = 1;
	    }
	    param.setEmno(emno);
	    logger.info("gs + ps + cp :  "+ pageSize + ", " + curPage);
	    param.setPageSize(pageSize);
	    param.setCurPage(curPage);
	
		List<EaVO> list = eaService.eaSelectAllSB(param);
		
		logger.info("list : " + list);
		int listCnt = list.size();
		logger.info("listCnt : " + listCnt);
		for(int i=0; i<listCnt; i++){
			EaVO eavo = null;
			eavo = new EaVO();
			eavo = list.get(i);
			pageNo = eavo.getPageNo();
			totalCount = eavo.getTotalCount();
			curPage = eavo.getCurPage();
			groupSize = eavo.getGroupSize();
			logger.info("����¡�� ������ : " + pageNo + ", " + totalCount + ", " + curPage + ", " + groupSize);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageNo", pageNo);
		mav.addObject("totalCount", totalCount);
		mav.addObject("curPage", curPage);
		mav.addObject("groupSize", groupSize);
		mav.addObject("eaList", list);
		mav.setViewName("ea/eamylistsb");
		logger.info("[log] EaController.eaMyListSB ���� <<< ");
		return mav;
	} // end of eaSelectAllSB
	
	/* selectAll �ۼ��ڰ� ���� �Ϸ� ���� ����Ʈ  ��ȸ */
	@RequestMapping("/eamylistfn")
	public ModelAndView eaMyListFN(@ModelAttribute EaVO param, HttpServletRequest request, HttpSession httpsession){
		logger.info("[log] EaController.eaMyListFN ���� >>> ");

		Object infoObj = httpsession.getAttribute("eminfo");
		EmInfoVO infoList = new EmInfoVO();
	    infoList = (EmInfoVO)infoObj;
	    String emno = infoList.getEmno();
	    String emname = infoList.getEmname();
	    logger.info("����� ��� : " + emno);
	    logger.info("����� �̸� : " + emname);
 
	    pageSize =  10;
	    String cp = request.getParameter("curPage");
	    if(cp!=null){
	    	curPage = Integer.parseInt(cp);
	    }else{
	    	curPage = 1;
	    }
	    param.setEmno(emno);
	    logger.info("gs + ps + cp :  "+ pageSize + ", " + curPage);
	    param.setPageSize(pageSize);
	    param.setCurPage(curPage);
	
		List<EaVO> list = eaService.eaSelectAllFN(param);
		
		logger.info("list : " + list);
		int listCnt = list.size();
		logger.info("listCnt : " + listCnt);
		for(int i=0; i<listCnt; i++){
			EaVO eavo = null;
			eavo = new EaVO();
			eavo = list.get(i);
			pageNo = eavo.getPageNo();
			totalCount = eavo.getTotalCount();
			curPage = eavo.getCurPage();
			groupSize = eavo.getGroupSize();
			logger.info("����¡�� ������ : " + pageNo + ", " + totalCount + ", " + curPage + ", " + groupSize);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageNo", pageNo);
		mav.addObject("totalCount", totalCount);
		mav.addObject("curPage", curPage);
		mav.addObject("groupSize", groupSize);
		mav.addObject("eaList", list);
		mav.setViewName("ea/eamylistfn");
		logger.info("[log] EaController.eaMyListFN ���� <<< ");
		return mav;
	} // end of eaMyListFN
	
	/* selectAll �ۼ��ڰ� ���� 04 ���� ����Ʈ  ��ȸ */
	@RequestMapping("/eamylistrt")
	public ModelAndView eaMyListRT(@ModelAttribute EaVO param, HttpServletRequest request, HttpSession httpsession){
		logger.info("[log] EaController.eaMyListRT ���� >>> ");

		Object infoObj = httpsession.getAttribute("eminfo");
		EmInfoVO infoList = new EmInfoVO();
	    infoList = (EmInfoVO)infoObj;
	    String emno = infoList.getEmno();
	    String emname = infoList.getEmname();
	    logger.info("����� ��� : " + emno);
	    logger.info("����� �̸� : " + emname);
 
	    pageSize =  10;
	    String cp = request.getParameter("curPage");
	    if(cp!=null){
	    	curPage = Integer.parseInt(cp);
	    }else{
	    	curPage = 1;
	    }
	    param.setEmno(emno);
	    logger.info("gs + ps + cp :  "+ pageSize + ", " + curPage);
	    param.setPageSize(pageSize);
	    param.setCurPage(curPage);
	    
		List<EaVO> list = eaService.eaSelectAllRT(param);
		
		logger.info("list : " + list);
		int listCnt = list.size();
		logger.info("listCnt : " + listCnt);
		for(int i=0; i<listCnt; i++){
			EaVO eavo = null;
			eavo = new EaVO();
			eavo = list.get(i);
			pageNo = eavo.getPageNo();
			totalCount = eavo.getTotalCount();
			curPage = eavo.getCurPage();
			groupSize = eavo.getGroupSize();
			logger.info("����¡�� ������ : " + pageNo + ", " + totalCount + ", " + curPage + ", " + groupSize);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageNo", pageNo);
		mav.addObject("totalCount", totalCount);
		mav.addObject("curPage", curPage);
		mav.addObject("groupSize", groupSize);
		mav.addObject("eaList", list);
		mav.setViewName("ea/eamylistrt");
		logger.info("[log] EaController.eaMyListRT ���� <<< ");
		return mav;
	} // end of eaMyListRT
	
	/* ������ ��üSELECT */
	@RequestMapping("/eamylistall")
	public ModelAndView eaList(@ModelAttribute EaVO param, HttpServletRequest request, HttpSession httpsession){
		logger.info("[log] EaController.eaList ���� >>> ");
		
		Object infoObj = httpsession.getAttribute("eminfo");
		EmInfoVO infoList = new EmInfoVO();
	    infoList = (EmInfoVO)infoObj;
	    String emno = infoList.getEmno();
	    String emname = infoList.getEmname();
	    logger.info("����� ��� : " + emno);
	    logger.info("����� �̸� : " + emname);
	    
	    String search = request.getParameter("search");
	    logger.info("search : " + search);
	    param.setSearch(param.getSearch());
	    param.setKeyword(param.getKeyword());
	    
	    pageSize =  10;
	    String cp = request.getParameter("curPage");   //�갡 �η� ����; ��
	    logger.info("��𰫳� cp : " + cp);
	    if(cp!=null){
	    	curPage = Integer.parseInt(cp);
	    }else{
	    	curPage = 1;
	    }
	    param.setEmno(emno);
	    logger.info("ps + cp :  "+ pageSize + ", " + curPage);
	    param.setPageSize(pageSize);
	    param.setCurPage(curPage);
	    
		List<EaVO> list = eaService.eaSelectAll(param);
		
		
		logger.info("list : " + list);
		int listCnt = list.size();
		logger.info("listCnt : " + listCnt);
		for(int i=0; i<listCnt; i++){
			EaVO eavo = null;
			eavo = new EaVO();
			eavo = list.get(i);
			pageNo = eavo.getPageNo();
			totalCount = eavo.getTotalCount();
			curPage = eavo.getCurPage();
			groupSize = eavo.getGroupSize();
			logger.info("����¡�� ������ : " + pageNo + ", " + totalCount + ", " + curPage + ", " + groupSize);
		}
		
				
		ModelAndView mav = new ModelAndView();
		mav.addObject("aa", param);
		mav.addObject("pageNo", pageNo);
		mav.addObject("totalCount", totalCount);
		mav.addObject("curPage", curPage);
		mav.addObject("groupSize", groupSize);
		mav.addObject("eaList", list);
		mav.setViewName("ea/eamylistall");
		logger.info("[log] EaController.eaList ���� <<< ");
		return mav;
	} // end of eaList
	
	/* ���ڰ��繮�� detail ��ȸ */
	@RequestMapping("/eadetail")
	public ModelAndView eaDetail(@ModelAttribute EaVO param, HttpSession httpsession){
		logger.info("[log] EaController.eadetail ���� >>> ");
		/* session�� ��� em_info������ ����ϱ� ���� �κ� */
		Object infoObj = httpsession.getAttribute("eminfo");
		EmInfoVO infoList = new EmInfoVO();
	    infoList = (EmInfoVO)infoObj;
	    String emno = infoList.getEmno();
	    String emname = infoList.getEmname();
	    logger.info("����� ��� : " + emno);
	    logger.info("����� �̸� : " + emname);
	    param.setEmno(infoList.getEmno());
	    
	    /* ealist.jsp ���������� �޾ƿ� ��  */
	    System.out.println(param.getEa_no());
	    System.out.println(param.getEa_stepno());
		String ea_no = param.getEa_no();
		logger.info("ea_no : " + ea_no);
		String ea_stepno = param.getEa_stepno();
		logger.info("ea_stepno : " + ea_stepno);
		param.setEa_stepno(ea_stepno);
		
		ModelAndView mav = new ModelAndView();
		List<EaVO> list = null;
		list = eaService.eaDetail(param);
		logger.info("list : " + list);
		int listCnt = list.size();
		logger.info("listCnt : " + listCnt);
		mav.addObject("eaList", list);
		mav.setViewName("ea/eadetail");
		logger.info("[log] EaController.eadetail ���� <<< ");
		return mav;
	} // end of eaDetail
	
	/* 02/04/05 ��� ���� �� detail ��ȸ */
	@RequestMapping("/eaapprove")
	public ModelAndView eaApprove(@ModelAttribute EaVO param, HttpServletRequest request,HttpSession httpsession){
		logger.info("[log] EaController.eadetail ���� >>> ");
		/* session�� ��� em_info������ ����ϱ� ���� �κ� */
		Object infoObj = httpsession.getAttribute("eminfo");
		EmInfoVO infoList = new EmInfoVO();
	    infoList = (EmInfoVO)infoObj;
	    String emno = infoList.getEmno();
	    String emname = infoList.getEmname();
	    logger.info("����� ��� : " + emno);
	    logger.info("����� �̸� : " + emname);
	    param.setEmno(infoList.getEmno());
	    
	    /* ealist.jsp ���������� �޾ƿ� ��  */
	    System.out.println(param.getEa_no());
	    System.out.println(param.getEa_stepno());
		String ea_no = param.getEa_no();
		logger.info("ea_no : " + ea_no);
		String ea_stepno = param.getEa_stepno();
		logger.info("ea_stepno : " + ea_stepno);
		param.setEa_stepno(ea_stepno);
		
		ModelAndView mav = new ModelAndView();
		List<EaVO> list = null;
		list = eaService.eaDetail(param);
		logger.info("list : " + list);
		int listCnt = list.size();
		logger.info("listCnt : " + listCnt);
		mav.addObject("eaList", list);
		mav.setViewName("ea/eaapprove");
		logger.info("[log] EaController.eadetail ���� <<< ");
		return mav;
	} // end of eaDetail
	
	/* ���� �����ؾ��� ����  */ 
	@RequestMapping("/eamyapplist")
	public ModelAndView eaMyAppList(@ModelAttribute EaVO param,  HttpServletRequest request, HttpSession httpsession){
		logger.info("[log] EaController.eaMyAppList ���� >>> ");
		
		/* ���ǿ��� �� ���� �������� */
		Object infoObj = httpsession.getAttribute("eminfo");
		EmInfoVO infoList = new EmInfoVO();
	    infoList = (EmInfoVO)infoObj;
	    String emno = infoList.getEmno();
	    String emname = infoList.getEmname();
	    logger.info("����� ��� : " + emno);
	    logger.info("����� �̸� : " + emname);
	    
	    /* ����¡�� ���� �������� */
	    EaVO evo = new EaVO();
	    pageSize =  10;
	    String cp = request.getParameter("curPage");
	    if(cp!=null){
	    	curPage = Integer.parseInt(cp);
	    }else{
	    	curPage = 1;
	    }

	    logger.info("gs + ps + cp :  "+ pageSize + ", " + curPage);
	    evo.setPageSize(pageSize);
	    evo.setCurPage(curPage);
	    evo.setEmno(emno);
	    
	    /* eaMyAppList ���� Ÿ�� */
	    evo.setEmno(emno);
	    List<EaVO> list = eaService.eaMyAppList(evo);
	    
	    logger.info("list : " + list);
		int listCnt = list.size();
		logger.info("listCnt : " + listCnt);
		for(int j=0; j<listCnt; j++){
			EaVO eavo = null;
			eavo = new EaVO();
			eavo = list.get(j);
			pageNo = eavo.getPageNo();
			totalCount = eavo.getTotalCount();
			curPage = eavo.getCurPage();
			groupSize = eavo.getGroupSize();
			logger.info("����¡�� ������ : " + pageNo + ", " + totalCount + ", " + curPage + ", " + groupSize);
		}
		/* ModelAndView �� ��� */
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("pageNo", pageNo);
		mav.addObject("totalCount", totalCount);
		mav.addObject("curPage", curPage);
		mav.addObject("groupSize", groupSize);
		mav.addObject("eaList", list);
		mav.setViewName("ea/eamyapplist");
	    		
		logger.info("[log] EaController.eaMyAppList ���� <<< ");
		return mav;
	}
	
	/* ea_lineform���� �̵�*/
	@RequestMapping("/ealineform")
	public ModelAndView ealineform(@ModelAttribute EaVO param, HttpSession httpsession, HttpServletRequest request){
		logger.info("[log] EaController.ealineform ���� >>> ");
		String eadoccd = param.getEa_doccd();
		logger.info("eadoccd : " + eadoccd);  
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("eadoccd", eadoccd);
		mav.setViewName("ea/ealineform");
		logger.info("[log] EaController.ealineform ���� <<< ");
		return mav;
	}
	
	/* ea_writeform���� �̵�*/
	@RequestMapping("/eawriteform")
	public ModelAndView eawriteform(@ModelAttribute EaVO param, HttpSession httpsession, HttpServletRequest request){
		logger.info("[log] EaController.eawriteform ���� >>> ");

		String ea_stepno = "";
		String ea_empno1 = "";
		String ea_empno2 = "";
		String ea_lineno = "";
		String eadoccd = request.getParameter("ea_doccd");
		logger.info("eadoccd : " + eadoccd);   
		
		Object infoObj = httpsession.getAttribute("eminfo");
		EmInfoVO infoList = new EmInfoVO();
	    infoList = (EmInfoVO)infoObj;
	    String emno = infoList.getEmno();
	    String emname = infoList.getEmname();
	    logger.info("����� ��� : " + emno);
	    logger.info("����� �̸� : " + emname);
		    
		ea_stepno = request.getParameter("ea_stepno");
		logger.info("ea_stepno : " + ea_stepno);
		ea_lineno = request.getParameter("ea_lineno");
		logger.info("ea_lineno : " + ea_lineno);
		ea_empno1 = request.getParameter("ea_empno1");
		logger.info("ea_empno1 : " + ea_empno1);
		ea_empno2 = request.getParameter("ea_empno2");
		logger.info("ea_empno2 : " + ea_empno2);
		
		List<EmInfoVO> list = null;
		EmInfoVO emvo = new EmInfoVO();
		emvo.setEmno(ea_empno1);
		list = eaService.emInfoSelectALL(emvo);
		
		emvo.setEmno(ea_empno2);
		List<EmInfoVO> list1 = eaService.emInfoSelectALL(emvo);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("eadoccd", eadoccd);
		mav.addObject("ea_lineno", ea_lineno);
		mav.addObject("ea_stepno", ea_stepno);
		mav.addObject("ea_empno", emno);
		mav.addObject("ea_empno1list", list);
		mav.addObject("ea_empno2list", list1);
		mav.setViewName("ea/eawriteform");
		logger.info("[log] EaController.eawriteform ���� <<< ");
		return mav;
	}
	
	/* ea_line���̺� insert */
	@RequestMapping(value="/ealineinsert")
	public ModelAndView ealine_insert(@ModelAttribute EaVO param, HttpServletRequest request, HttpSession httpsession){
		logger.info("[log] EaController.ealine_insert ���� >>> ");
		List<EaVO> list = null;
		EaVO evo = null;
		String ea_lineno = "";
		String ea_linename = "";
		list = eaService.chaebunEAL(param);
		evo=list.get(0);
		ea_lineno = evo.getEa_lineno();
		ea_lineno = Chaebun.chaebunEAL(ea_lineno);
		logger.info("ea_lineno : " + ea_lineno);    
		ea_linename = param.getEa_linename();
		logger.info("ea_linename : " + ea_linename);
		
		evo = new EaVO();
		evo.setEa_lineno(ea_lineno);
		evo.setEa_linename(ea_linename);
		
		int nCnt = eaService.eaLineInsert(evo);
		if(nCnt>0){
			logger.info("[log] ea_line insert success");
		}else{
			logger.info("[log] ea_line insert fail");
		}
		
		String eadoccd = param.getEa_doccd();
		logger.info("eadoccd : " + eadoccd);   
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("ea/eastepform");
		mav.addObject("eadoccd", eadoccd);
		mav.addObject("ea_lineno", ea_lineno);
		logger.info("[log] EaController.ealine_insert ���� <<< ");
		return mav;
	}
	
	/* ea_step���̺� insert */
	@RequestMapping("/eastepinsert")
	public ModelAndView eastep_insert(@ModelAttribute EaVO param, HttpSession httpsession){
		logger.info("[log] EaController.eastep_insert ���� >>> ");
		List<EaVO> list = null;
		EaVO evo = null;
		String ea_stepno = "";
		String ea_empno1 = "";
		String ea_empno2 = "";
		String ea_lineno = "";
		
		Object infoObj = httpsession.getAttribute("eminfo");
		EmInfoVO infoList = new EmInfoVO();
	    infoList = (EmInfoVO)infoObj;
	    String emno = infoList.getEmno();
	    String emname = infoList.getEmname();
	    logger.info("����� ��� : " + emno);
	    logger.info("����� �̸� : " + emname);
		
		list = eaService.chaebunEASTNO(param);
		evo = list.get(0);
		ea_stepno = evo.getEa_stepno();
		ea_stepno = Chaebun.chaebunEASTNO(ea_stepno);
		logger.info("ea_stepno : " + ea_stepno);
				
		ea_lineno = param.getEa_lineno();
		logger.info("ea_lineno : " + ea_lineno);

		ea_empno1 = param.getEa_empno1();
		logger.info("ea_empno1 : " + ea_empno1);
		ea_empno2 = param.getEa_empno2();
		logger.info("ea_empno2 : " + ea_empno2);
		String eadoccd = param.getEa_doccd();
		logger.info("eadoccd : " + eadoccd);
		
		evo = new EaVO();
		evo.setEa_stepno(ea_stepno);
		evo.setEa_empno(emno);
		evo.setEa_empno1(ea_empno1);
		evo.setEa_empno2(ea_empno2);
		evo.setEa_lineno(ea_lineno);
		
		int nCnt = eaService.eaStepInsert(evo);
		if(nCnt>0){
			logger.info("[log] eastep_insert success");
		}else{
			logger.info("[log] eastep_insert fail");
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("ea/result");
		mav.addObject("eadoccd", eadoccd);
		mav.addObject("ea_lineno", ea_lineno);
		mav.addObject("ea_stepno", ea_stepno);
		mav.addObject("ea_empno", emno);
		mav.addObject("ea_empno1", ea_empno1);
		mav.addObject("ea_empno2", ea_empno2);
		logger.info("[log] EaController.eastep_insert ���� <<< ");
		return mav;
	}
	
	/* ea_log, ea_table���̺� insert */
	@RequestMapping("/eainsert")
	public ModelAndView eaInsert(@ModelAttribute EaVO param, HttpServletRequest request, HttpSession httpsession){
		// �α׶� ���̺� �Ѵ� �μ�Ʈ ���Ѿ���.
		logger.info("[log] EaController.eainsert ���� >>> ");
		String ea_no = "";
		String ea_logno = "";
		String ea_lineno = "";
		String ea_stepno = "";
		String ea_status = "";		//EA_STATUS			�������
		String ea_sign1 = "";		//EA_SIGN1			ù��°������ ����
		String ea_sign2 = "";		//EA_SIGN2			�ι�°������ ����
		String ea_file1 = "";		//EA_FILE1			ù��������� ����
		String ea_file2 = "";
		String ea_doccd = "";		//EA_COCCD			��������ڵ�
		String ea_subject = "";		//EA_SUBJECT		���ڰ�������
		String ea_memo = ""; 		//EA_MEMO			���ڰ��系��
		String ea_nextemno = "";    //EA_NEXTEMNO       ���������ڻ��
		
		List <EaVO> list = null;
		EaVO evo = null;
		evo = new EaVO();
		
		Object infoObj = httpsession.getAttribute("eminfo");
		EmInfoVO infoList = new EmInfoVO();
	    infoList = (EmInfoVO)infoObj;
	    String emno = infoList.getEmno();
	    String emname = infoList.getEmname();
	    String emsign = infoList.getEmsign();
	    
	    logger.info("����� ��� : " + emno);
	    logger.info("����� �̸� : " + emname);
	    logger.info("����� ���� : " + emsign);
	    
		/* �α�ä�� ���� */
		list = eaService.chaebunEALOG(param);
		evo = list.get(0);
		ea_logno = evo.getEa_logno();
		ea_logno = Chaebun.chaebunEALOG(ea_logno);
		logger.info("ea_logno : " + ea_logno);
		/* ���̺�ä�� ���� */
		list = eaService.chaebunEA(param);
		evo = list.get(0);
		ea_no = evo.getEa_no();
		ea_no = Chaebun.chaebunEA(ea_no);
		logger.info("ea_no : " + ea_no);
		
		/* �α� �μ�Ʈ */
		EaFileUploadUtil multi = new EaFileUploadUtil();
		boolean bFlag = false;
		bFlag = multi.fileUpload(request, EAFILEPATH);
		logger.info("bFlag >>> : " + bFlag );
		
		/* ���ܹ�ȣ ��������� */
		ea_stepno = multi.getParameter("ea_stepno");
		logger.info("ea_stepno : " + ea_stepno);
		
		/* ���ι�ȣ ��������� */
		ea_lineno = multi.getParameter("ea_lineno");
		logger.info("ea_lineno : " + ea_lineno);
		
		ea_subject = multi.getParameter("ea_subject");
		logger.info("ea_subject : " + ea_subject);
		ea_memo = multi.getParameter("ea_memo");
		logger.info("ea_memo : " + ea_memo);
		ea_status = multi.getParameter("ea_status");
		logger.info("ea_status : " + ea_status);
		ea_doccd = multi.getParameter("ea_doccd");
		logger.info("ea_doccd : " + ea_doccd);
		
		EaVO evo1 = new EaVO();
		evo1.setEa_stepno(ea_stepno);
		
		List<EaVO> eaList = eaService.eaNextEmno(evo1);
		for(int i=0; i<eaList.size(); i++){
			EaVO eavo = new EaVO();
			eavo = (EaVO) eaList.get(i);
			ea_nextemno = eavo.getEa_empno1();
			logger.info("ea_nextemno : " + ea_nextemno);
		}
		
		if(bFlag){
			Enumeration<String> files = multi.getFileNames();
			String filename = files.nextElement();
			
			evo = new EaVO();
			evo.setEa_logno(ea_logno);
			evo.setEa_lineno(ea_lineno);
			evo.setEa_stepno(ea_stepno);
			evo.setEa_no(ea_no);
			evo.setEa_status(ea_status);
			evo.setEa_sign(emsign);
			evo.setEa_sign1(ea_sign1);
			evo.setEa_sign2(ea_sign2);
			evo.setEa_file(filename);  // ���Ͼ��ε�
			evo.setEa_file1(ea_file1);
			evo.setEa_file2(ea_file2);
			
			int nCnt = eaService.eaLogInsert(evo);
			
			if(nCnt>0){
				logger.info("[log] ealog_insert success");
				/* ���ڰ������̺� �μ�Ʈ */
				evo = new EaVO();
				evo.setEa_doccd(ea_doccd);
				evo.setEa_lineno(ea_lineno);
				evo.setEa_logno(ea_logno);
				evo.setEa_no(ea_no);
				evo.setEa_memo(ea_memo);
				evo.setEa_fileupload(filename);
				evo.setEa_empno(emno);
				evo.setEa_subject(ea_subject);
				evo.setEa_status(ea_status);
				evo.setEa_nextemno(ea_nextemno);
				
				int sCnt = eaService.eaTableInsert(evo);
				
				if(sCnt>0){
					logger.info("[log] eatable_insert success");
				}else{
					logger.info("[log] eatable_insert fail");
				}
			}else{
				logger.info("[log] ealog_insert fail");
			}
			
		}else{
			logger.info("[log] log insert fail");
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("ea/earesult");
		mav.addObject("ea_no", ea_no);
		mav.addObject("ea_lineno", ea_lineno);
		mav.addObject("ea_stepno", ea_stepno);
		mav.addObject("ea_logno", ea_logno);
		mav.addObject("ea_empno", emno);
		logger.info("[log] EaController.eainsert ���� <<< ");
		return mav;
	}
	
	/* session login */
	 @RequestMapping(value="/loginCheck")
	 public ModelAndView loginCheck(@ModelAttribute EmInfoVO param, HttpSession httpsession){
		 logger.info("loginCheck ����");
		 
		 ModelAndView mav = new ModelAndView();
	      
	     String emid = "";
	     String empw = "";
	     emid = param.getEmid();
	     empw = param.getEmpw();
	     logger.info("emid >>> " + emid);
	     logger.info("empw >>> " + empw);
	     
	     EmInfoVO eVO = null;
	     eVO = new EmInfoVO();
	      
	     eVO.setEmid(emid);
	     eVO.setEmpw(empw);
	      
	     EmInfoVO infoList = new EmInfoVO();
	     infoList = eaService.em_InfoList(eVO);
	     String emname = infoList.getEmname();
	     logger.info("�α����� ����� �� : " + emname);
	      
	     if(eVO != null){
	        logger.info("���̵�� ��й�ȣ�� ��ġ�ϴ� ȸ���� �����մϴ�.");
	        httpsession.setAttribute("eminfo", infoList);
	     }
	     
	     String ea_stepno = "";
	     
		 String emno = infoList.getEmno();
		 logger.info("emno : " + emno);
	     EaVO evo = new EaVO();
	     evo.setEa_empno(emno);
	     evo.setEmno(emno);
	     
	     List<EaVO> list = eaService.eaMainSelectAll(evo);   // ��ü����Ʈ
	     List<EaVO> list0 = eaService.eaMainSelectAllRJ(evo);   // 04��
	     List<EaVO> list1 = eaService.eaMainSelectAllFN(evo);  // �Ϸ���
	     List<EaVO> list2 = eaService.eaMainSelectAllPG(evo);   // ������
	     List<EaVO> list3 = eaService.eaMainApprove(evo); // �����ؾ��� ���� ���
	     
	     logger.info("list size : " + list.size());
	     logger.info("list0 size : " + list0.size());
	     logger.info("list1 size : " + list1.size());
	     logger.info("list2 size : " + list2.size());
	     logger.info("list3 size : " + list3.size());
	     
	     /* ���� ��������, �μ��� �Խ��� ���� ���� �ڵ� �߰�  */
	     /* ===========================================================================================  */
	    
	     //�μ��� �Խ���
	    BDVO check = null;
		check = new BDVO();
		
		check.setDeptcd(infoList.getDeptcd());
		List<BDVO> deptList = bDService.boardDeptMainList(check);
		
		//�������� �Խ���
		BNVO check1 = null;
		check1 = new BNVO();
		
		List<BNVO> noticeList = bNService.boardNoticeMainList(check1);     
	     
	     /* ===========================================================================================  */
			// �� ����� �߰�
	      	
	      List<EmInfoVO> ctList = emService.ctSelect(infoList);
	      int ctListSize = ctList.size();
	      logger.info("ctListSize >>> : " + ctListSize);
	      
	      /* ===========================================================================================  */
			// �� ������ ȸ�� ���� �߰�
	      
	      CalendarVO cvo = null;
	      cvo = new CalendarVO();
	      	
	      List<CalendarVO> adminCalendarList = calendarService.adminCalendarList(cvo);
	      
		 /* ===========================================================================================  */
	     
	     mav.addObject("ea_stepno", ea_stepno);
	     mav.addObject("eaListRJ", list0);
	     mav.addObject("eaListALL", list);
	     mav.addObject("eaListFN", list1);
	     mav.addObject("eaListPG", list2);
	     mav.addObject("eaListAP", list3);
	     
	     mav.addObject("deptList", deptList);
	     mav.addObject("noticeList", noticeList);
	     
	     mav.addObject("adminCalendarList", adminCalendarList);
	     
	     mav.addObject("ctList", ctList);
	     /* ===========================================================================================  */
	    
	     if(infoList.getJobcd().equals("01")){
	    	  logger.info("������ ���� �������� �̵�");
	    	  mav.setViewName("em/emAdminMain");
	      }else{
	    	  logger.info("����� ���� �������� �̵�");
	    	  mav.setViewName("gemain");   
	      }
	     logger.info("loginCheck ����");    
	     return mav;
	 }
	 
	 /* session logout */
	 @RequestMapping(value="/geLogOut")
	 public ModelAndView geLogOut(@ModelAttribute EmInfoVO evo, HttpSession httpsession){
		 logger.info("geLogOut ����");
		 httpsession.invalidate();
		 ModelAndView mav = new ModelAndView();
	     mav.setViewName("../../index");   
	     logger.info("geLogOut ����");
		 return mav;
	 }
	 
	 /* eastepform.jsp���� ù��° selectĭ �����͸� �̾ƿ��� �Լ� */
	 @RequestMapping(value="/getDeptList")
	 @ResponseBody
	 public List<EmInfoVO> getDeptList(@ModelAttribute EmInfoVO param, HttpSession httpsession){
		 logger.info("[log] EaController.getDeptList ���� >>> ");
		 List<EmInfoVO> list = eaService.getDeptList(param);
		 logger.info("list : " + list);
	  	 int listCnt = list.size();
		 logger.info("listCnt : " + listCnt);		 
		 logger.info("[log] EaController.getDeptList ���� <<< ");
		 return list;
	 }
	 
	 /* eastepform.jsp���� �ι�° selectĭ �����͸� �̾ƿ��� �Լ� */
	 @RequestMapping(value="/getJobList")
	 @ResponseBody
	 public List<EmInfoVO> getJobList(@ModelAttribute EmInfoVO param, HttpSession httpsession){
		 logger.info("[log] EaController.getJobList ���� >>> ");
		 String deptcd = param.getDeptcd();
		 logger.info("deptcd : " + deptcd);
		 EmInfoVO emvo = new EmInfoVO();
		 emvo.setDeptcd(deptcd);
		 
		 List<EmInfoVO> list = eaService.getJobList(emvo);
		 logger.info("list : " + list);
	  	 int listCnt = list.size();
		 logger.info("listCnt : " + listCnt);		 
		 logger.info("[log] EaController.getJobList ���� <<< ");
		 return list;
	 }
	 
	 /* eastepform.jsp���� ����° selectĭ �����͸� �̾ƿ��� �Լ� */
	 @RequestMapping(value="/getEmnameList")
	 @ResponseBody
	 public List<EmInfoVO> getEmnameList(@ModelAttribute EmInfoVO param, HttpSession httpsession){
		 logger.info("[log] EaController.getEmnameList ���� >>> ");
		 String deptcd = param.getDeptcd();
		 String jobcd = param.getJobcd();
		 logger.info("deptcd : " + deptcd);
		 logger.info("jobcd  : " + jobcd);
		 EmInfoVO emvo = new EmInfoVO();
		 emvo.setDeptcd(deptcd);
		 emvo.setJobcd(jobcd);
		 
		 List<EmInfoVO> list = eaService.getEmnameList(emvo);
		 logger.info("list : " + list);
	  	 int listCnt = list.size();
		 logger.info("listCnt : " + listCnt);		 
		 logger.info("[log] EaController.getEmnameList ���� <<< ");
		 return list;
	 }
	 
	 /* Eamain �������̵� */
	 @RequestMapping(value="/eamain")
	 public ModelAndView toEamain(@ModelAttribute EaVO param, HttpSession httpsession){
		 logger.info("[log] EaController.toEamain ���� >>> ");
		 /* session�� ��� em_info������ ����ϱ� ���� �κ� */
		 Object infoObj = httpsession.getAttribute("eminfo");
		 EmInfoVO infoList = new EmInfoVO();
		 infoList = (EmInfoVO)infoObj;
		 String emno = infoList.getEmno();
		 String emname = infoList.getEmname();
		 logger.info("����� ��� : " + emno);
		 logger.info("����� �̸� : " + emname);		
		 
		 String ea_stepno = "";
	     
	     EaVO evo = new EaVO();
	     evo.setEa_empno(emno);
		 evo.setEmno(emno);
		 
	     List<EaVO> list = eaService.eaMainSelectAll(evo);   // ��ü����Ʈ
	     List<EaVO> list0 = eaService.eaMainSelectAllRJ(evo);   // 04��
	     List<EaVO> list1 = eaService.eaMainSelectAllFN(evo);  // �Ϸ���
	     List<EaVO> list2 = eaService.eaMainSelectAllPG(evo);   // ������
	     List<EaVO> list3 = eaService.eaMainApprove(evo); // �����ؾ��� ���� ���
	     
	     logger.info("list size : " + list.size());
	     logger.info("list0 size : " + list0.size());
	     logger.info("list1 size : " + list1.size());
	     logger.info("list2 size : " + list2.size());
	     logger.info("list3 size : " + list3.size());
	     
	     /* ���� ��������, �μ��� �Խ��� ���� ���� �ڵ� �߰�  */
	     /* ===========================================================================================  */
	    
	     //�μ��� �Խ���
	    BDVO check = null;
		check = new BDVO();
		
		check.setDeptcd(infoList.getDeptcd());
		List<BDVO> deptList = bDService.boardDeptMainList(check);
		
		//�������� �Խ���
		BNVO check1 = null;
		check1 = new BNVO();
		
		List<BNVO> noticeList = bNService.boardNoticeMainList(check1);     
	     
	     /* ===========================================================================================  */
	     
	     ModelAndView mav = new ModelAndView();
	     mav.addObject("ea_stepno", ea_stepno);
	     mav.addObject("eaListRJ", list0);
	     mav.addObject("eaListALL", list);
	     mav.addObject("eaListFN", list1);
	     mav.addObject("eaListPG", list2);
	     mav.addObject("eaListAP", list3);
	     
	     mav.addObject("deptList", deptList);
	     mav.addObject("noticeList", noticeList);	     
	     
	     mav.setViewName("ea/eamain");   
	     logger.info("[log] EaController.toEamain ���� <<< ");    
	     return mav;
	 }
 
	 /* gemain �������̵� */
	 @RequestMapping(value="/gemain")
	 public ModelAndView toGemain(@ModelAttribute EaVO param, HttpSession httpsession){
		 logger.info("[log] EaController.toGemain ���� >>> ");
		 
		 ModelAndView mav = new ModelAndView();
		 
		 /* session�� ��� em_info������ ����ϱ� ���� �κ� */
		 Object infoObj = httpsession.getAttribute("eminfo");
		 EmInfoVO infoList = new EmInfoVO();
		 infoList = (EmInfoVO)infoObj;
		 String emno = infoList.getEmno();
		 String emname = infoList.getEmname();
		 logger.info("����� ��� : " + emno);
		 logger.info("����� �̸� : " + emname);		
		 
		 String ea_stepno = "";
	     
	     EaVO evo = new EaVO();
	     evo.setEa_empno(emno);
		 evo.setEmno(emno);
		 
	     List<EaVO> list = eaService.eaMainSelectAll(evo);   // ��ü����Ʈ
	     List<EaVO> list0 = eaService.eaMainSelectAllRJ(evo);   // 04��
	     List<EaVO> list1 = eaService.eaMainSelectAllFN(evo);  // �Ϸ���
	     List<EaVO> list2 = eaService.eaMainSelectAllPG(evo);   // ������
	     List<EaVO> list3 = eaService.eaMainApprove(evo); // �����ؾ��� ���� ���
	     
	     logger.info("list size : " + list.size());
	     logger.info("list0 size : " + list0.size());
	     logger.info("list1 size : " + list1.size());
	     logger.info("list2 size : " + list2.size());
	     logger.info("list3 size : " + list3.size());
	     
	     /* ���� ��������, �μ��� �Խ��� ���� ���� �ڵ� �߰�  */
	     /* ===========================================================================================  */
	    
	     //�μ��� �Խ���
	    BDVO check = null;
		check = new BDVO();
		
		check.setDeptcd(infoList.getDeptcd());
		List<BDVO> deptList = bDService.boardDeptMainList(check);
		
		//�������� �Խ���
		BNVO check1 = null;
		check1 = new BNVO();
		
		List<BNVO> noticeList = bNService.boardNoticeMainList(check1);     
	     
		 /* ===========================================================================================  */
		// �� ����� �߰�
      	
	      List<EmInfoVO> ctList = emService.ctSelect(infoList);
	      int ctListSize = ctList.size();
	      logger.info("ctListSize >>> : " + ctListSize);
	      
	      if(infoList.getJobcd().equals("01")){
	    	  //logger.info("������ ���� �������� �̵�");
	    	  mav.setViewName("em/emAdminMain");
	      }else{
	    	  //logger.info("����� ���� �������� �̵�");
	      }
	
	 /* ===========================================================================================  */
	    
	     mav.addObject("ea_stepno", ea_stepno);
	     mav.addObject("eaListRJ", list0);
	     mav.addObject("eaListALL", list);
	     mav.addObject("eaListFN", list1);
	     mav.addObject("eaListPG", list2);
	     mav.addObject("eaListAP", list3);
	     
	     mav.addObject("deptList", deptList);
	     mav.addObject("noticeList", noticeList);
	     
	     mav.addObject("ctList", ctList);
	     
	     mav.setViewName("gemain");   
	     logger.info("[log] EaController.toGemain ���� <<< ");    
	     return mav;
	 }
} // end of EaController