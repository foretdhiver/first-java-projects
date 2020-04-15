package com.spring.ge.controller;

import java.util.Calendar;
import java.util.Enumeration;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.spring.ge.common.Chaebun;
import com.spring.ge.common.FilePath;
import com.spring.ge.common.EmFileUploadUtil;
import com.spring.ge.service.BDService;
import com.spring.ge.service.BNService;
import com.spring.ge.service.EaService;
import com.spring.ge.service.EmService;
import com.spring.ge.vo.BDVO;
import com.spring.ge.vo.BNVO;
import com.spring.ge.vo.CommonVO;
import com.spring.ge.vo.EaVO;
import com.spring.ge.vo.EmHrVO;
import com.spring.ge.vo.EmInfoVO;
import com.spring.ge.vo.EmPrInfoVO;

@Controller
@RequestMapping(value="/em")
public class EmController {
	
	// 페이징
	private int pageSize = 0;
	private int groupSize = 0;
	private int curPage = 0;
	private int totalCount = 0;
	private int pageNo = 0;
		
	private static final String CONTEXT_PATH = "em";
	
	Logger logger = Logger.getLogger(EmController.class);
	
	@Autowired
	private EmService emService;
	@Autowired
	private EaService eaService;
	@Autowired
	private BDService bDService;
	@Autowired
	private BNService bNService;
	
	@Autowired
	private PlatformTransactionManager ptm;
	DefaultTransactionDefinition dtd = null;
	TransactionStatus ts = null;
	
	/**************************************************
	 * 로그인
	 **************************************************/
	@RequestMapping(value="/loginCheck")
	public ModelAndView loginCheck(@ModelAttribute EmInfoVO evo, HttpSession httpsession){
		logger.info("loginCheck 진입");
		
		ModelAndView mav = new ModelAndView();
		
		String emid = "";
        String empw = "";
        emid = evo.getEmid();
        empw = evo.getEmpw();
        logger.info("emid / empw >>> : " + emid + " / " + empw);
        
        EmInfoVO eVO = null;
        eVO = new EmInfoVO();
        
        eVO.setEmid(emid);
        eVO.setEmpw(empw);
        
        EmInfoVO infoList = new EmInfoVO();
        infoList = emService.emInfoList(eVO);
        logger.info("emname >>> " + infoList.getEmname());
        logger.info("emid >>> " + infoList.getEmid());
        logger.info("empw >>> " + infoList.getEmpw());
        logger.info("deptcd >>> " + infoList.getDeptcd());
        logger.info("deptname >>> " + infoList.getDeptname());
        
        if(eVO != null){
            System.out.println("아이디와 비밀번호가 일치하는 회원이 존재합니다.");
            httpsession.setAttribute("eminfo", infoList);
            
            List<EmInfoVO> list = emService.ctSelect(infoList);
			int listSize = list.size();
			logger.info("listSize >>> : " + listSize);
			
			if(infoList.getJobcd().equals("01")){
				logger.info("관리자 메인 페이지로 이동");
				mav.setViewName(CONTEXT_PATH + "/emAdminMain");
			}else{
				logger.info("사용자 메인 페이지로 이동");
				mav.addObject("list", list);
				mav.setViewName(CONTEXT_PATH + "/emMain");
			}
         }	// end of if(eVO != null)
		return mav;
	}	// end of EmController.loginCheck() 함수
	
	/**************************************************
	 * 사원등록 폼 출력하기
	 **************************************************/
	@RequestMapping(value="/insertForm", method=RequestMethod.GET)
	public ModelAndView insertForm(@ModelAttribute CommonVO ecvo, @ModelAttribute EmInfoVO evo){
		
		logger.info("insertForm 호출 성공");
//		EmCdVO.cdPrint(ecvo);
		
		List<CommonVO> cdList = emService.cdList(ecvo);
		System.out.println("(log) cdList.size() >>> : " + cdList.size());
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("cdList", cdList);
		mav.addObject("ecvo", ecvo);
		mav.setViewName(CONTEXT_PATH + "/emInsert");
		
		return mav;
	}	// end of EmController.insertForm() 함수
	
	/**************************************************
	 * 사원등록 구현
	 **************************************************/
	@RequestMapping(value="/emInsert", method=RequestMethod.POST)
	public ModelAndView emInsert(@ModelAttribute EmInfoVO evo, EmPrInfoVO epvo, HttpServletRequest request){
		
		logger.info("emInsert 호출 성공");
		
		ModelAndView mav = new ModelAndView();
		
		// 페이징 부분
		pageSize = 10;
		String cp = request.getParameter("curPage");
		if(cp!=null){
			curPage = Integer.parseInt(cp);
		}else{
			curPage = 1;
		}	// end of if(cp!=null)
		logger.info("curPage >>> : " + curPage);
		
		// 파일 경로
		String uploadPath = FilePath.UPLOAD_FILE_PATH;
		String downloadPath = FilePath.DOWNLOAD_FILE_PATH;
		
		// FileUploadUtil Class
		EmFileUploadUtil fu = new EmFileUploadUtil();
		boolean bFlag = false;
		bFlag = fu.fileUpload(request, uploadPath);
		logger.info("bFlag >>> : " + bFlag);
		
		if(bFlag){
			logger.info("bFlag가 true여서  if문 진입");
			
			Enumeration<String> en = fu.getFileNames();
			
			String emsign = en.nextElement();	// 서명
			String empic = en.nextElement();	// 사인
			logger.info("firstFile / secondFile >>> : " + emsign + " / " + empic);
			
			// EM_INFO 테이블에 들어갈 레코드
			String emno = fu.getParameter("emno");
			String emname = fu.getParameter("emname");
			String emid = fu.getParameter("emid");
			String empw = fu.getParameter("empw");
			String emhiredate = fu.getParameter("emhiredate");
			String deptcd = fu.getParameter("deptcd");
			String jobcd = fu.getParameter("jobcd");
			String ememailid = fu.getParameter("ememailid");
			String ememailadd = fu.getParameter("ememailadd");
			String ememail = ememailid + "@" + ememailadd;
			String emfhp = fu.getParameter("emfhp");
			String emshp = fu.getParameter("emshp");
			String emlhp = fu.getParameter("emlhp");
			String emhp = emfhp + emshp + emlhp;
			String emexno = fu.getParameter("emexno");
			String emfdino = fu.getParameter("emfdino");
			String emsdino = fu.getParameter("emsdino");
			String emldino = fu.getParameter("emldino");
			String emdino = emfdino + emsdino + emldino;
			
			// EM_PRINFO 테이블에 들어갈 레코드
			String emprno = fu.getParameter("emprno");
			// EM_PRINFO에도 emno가 들어가지만 위에서 받아와서 주석 처리
//			String emno = fu.getParameter("emno");
			String emprsex = fu.getParameter("emprsex");
			String emprbirth = fu.getParameter("emprbirth");
			String emprmarital = fu.getParameter("emprmarital");
			String emprpostno = fu.getParameter("emprpostno");
			String empradd = fu.getParameter("empradd");
			String emprdetailadd = fu.getParameter("emprdetailadd");
			String empreducd = fu.getParameter("empreducd");
			String empreduname = fu.getParameter("empreduname");
			
			// 채번
			List<EmInfoVO> ceList = emService.emChaebun(evo);
			List<EmPrInfoVO> cepList = emService.emPrChaebun(epvo);
			logger.info("ceList.size() / cepList.size() >>> : " + ceList.size() + " / " + cepList.size());
			
			// 채번 돌았는지 여부 확인
			if(ceList.size()==1 && cepList.size()==1){
				logger.info("채번 잘 돌아서 if문 진입");
				// emno 채번
				String _no = ceList.get(0).getEmno();
				emno = Chaebun.chaebunCreateE(_no);
				evo.setEmno(emno);
				logger.info("사번 >>> : " + evo.getEmno());
				
				// emprno 채번
				String no = cepList.get(0).getEmprno();
				emprno = Chaebun.chaebunCreateEP(no);
				epvo.setEmprno(emprno);
				logger.info("순번 >>> : " + epvo.getEmprno());
			}	// end of if(cList.size()==1)
			
			// EM_INFO 테이블의 VO에 데이터 넣기
			evo.setEmname(emname);
			evo.setEmid(emid);
			evo.setEmpw(empw);
			evo.setEmhiredate(emhiredate);
			evo.setDeptcd(deptcd);
			evo.setJobcd(jobcd);
			evo.setEmemail(ememail);
			evo.setEmhp(emhp);
			evo.setEmdino(emdino);
			evo.setEmexno(emexno);
			evo.setEmsign(downloadPath+emsign);
			evo.setEmpic(downloadPath+empic);
			
			// EM_PRINFO 테이블의 VO에 데이터 넣기
			epvo.setEmno(emno);
			epvo.setEmprsex(emprsex);
			epvo.setEmprbirth(emprbirth);
			epvo.setEmprmarital(emprmarital);
			epvo.setEmprpostno(emprpostno);
			epvo.setEmpradd(empradd);
			epvo.setEmprdetailadd(emprdetailadd);
//			epvo.setEmprpic(downloadPath+empic);
			epvo.setEmpreducd(empreducd);
			epvo.setEmpreduname(empreduname);
			
			// insert 함수 실행
			int emresult = 0;
			int emprresult = 0;
			
			// 트랜잭션 처리
			
			dtd = new DefaultTransactionDefinition();
			dtd.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			ts= ptm.getTransaction(dtd);
			try{
				emresult = emService.emInsert(evo);
				emprresult = emService.emPrInsert(epvo);
				logger.info("emresult / emprresult >>> : " + emresult + " / " + emprresult);
				
				ptm.commit(ts);
			}catch(Exception e){
				logger.info("등록과정에서 에러 발생하여 롤백");
				ptm.rollback(ts);
				
//				epvo.setEmno("");
//				List<EmPrInfoVO> list = emService.emAllSelect(epvo);
//				
//				mav.addObject("emList", list);
//				mav.setViewName(CONTEXT_PATH + "/emAllSelect");
			}
			
			if(emresult == 1 && emprresult == 1){
				System.out.println("(log) insert함수 잘 실행되면 if문 진입");
				
				epvo.setEmno("");
//				List<EmPrInfoVO> list = emService.emAllSelect(epvo);
//				mav.addObject("emList", list);
				
				epvo.setPageSize(pageSize);
				epvo.setCurPage(curPage);
				
				List<EmPrInfoVO> list = emService.emAllSelect(epvo);
				
				for(int i=0; i<list.size(); i++){
					EmPrInfoVO epVO = null;
					epVO = new EmPrInfoVO();
					epVO = list.get(i);
					pageNo = epVO.getPageNo();
					totalCount = epVO.getTotalCount();
					curPage = epVO.getCurPage();
					groupSize = epVO.getGroupSize();
					logger.info("pageNo/totalCount/curPage/groupSize >>> :"
							+ pageNo + "/" + totalCount + "/" + curPage + "/" + groupSize);
				}	// end of for
				
				mav.addObject("emList", list);
				mav.addObject("pageNo", pageNo);
				mav.addObject("totalCount", totalCount);
				mav.addObject("curPage", curPage);
				mav.addObject("groupSize", groupSize);
				mav.setViewName(CONTEXT_PATH + "/emAllSelect");
				
			}	// end of if(result == 1)
		}	// end of if(bFlag)
		
		
		System.out.println("(log) EmController.emInsert() 함수 끝");
	
		return mav;
	}	// end of EmController.emInsert() 함수

	/**************************************************
	 * 사원목록 출력
	 **************************************************/
	@RequestMapping(value="/emAllSelect")
	public ModelAndView emAllSelect(@ModelAttribute EmPrInfoVO epvo, HttpServletRequest request){
		logger.info("Controller - emAllSelect 호출 성공");
		
		ModelAndView mav = new ModelAndView();
		
		// 페이징 부분
		pageSize = 10;
		String cp = request.getParameter("curPage");
		if(cp!=null){
			curPage = Integer.parseInt(cp);
		}else{
			curPage = 1;
		}	// end of if(cp!=null)
		logger.info("curPage >>> : " + curPage);
		
		epvo.setPageSize(pageSize);
		epvo.setCurPage(curPage);
		
		List<EmPrInfoVO> list = emService.emAllSelect(epvo);
		
		for(int i=0; i<list.size(); i++){
			EmPrInfoVO epVO = null;
			epVO = new EmPrInfoVO();
			epVO = list.get(i);
			pageNo = epVO.getPageNo();
			totalCount = epVO.getTotalCount();
			curPage = epVO.getCurPage();
			groupSize = epVO.getGroupSize();
			logger.info("pageNo/totalCount/curPage/groupSize >>> :"
					+ pageNo + "/" + totalCount + "/" + curPage + "/" + groupSize);
		}	// end of for
		
		mav.addObject("emList", list);
		mav.addObject("pageNo", pageNo);
		mav.addObject("totalCount", totalCount);
		mav.addObject("curPage", curPage);
		mav.addObject("groupSize", groupSize);
		mav.setViewName(CONTEXT_PATH + "/emAllSelect");
		
		System.out.println("(log) EmController.emAllSelect() 함수 끝");
		return mav;
	}	// end of EmController.emAllSelect() 함수

	/**************************************************
	 * 사원 상세 정보
	 **************************************************/
	@RequestMapping(value="/emDetail")
	public ModelAndView emDetail(@ModelAttribute EmPrInfoVO epvo, CommonVO ecvo){

		logger.info("Controller - emDetail 성공");
		
		// 매개변수 출력
		String emno = epvo.getEmno();
		System.out.println("(log) emno >>> : " + emno);
		
		List<EmPrInfoVO> sList = emService.emDetail(epvo);
		int sListSize = sList.size();
		System.out.println("(log) sListSize >>> : " + sListSize);

		List<CommonVO> cdList = emService.cdList(ecvo);
		int cdListSize = cdList.size();
		System.out.println("(log) cdListSize >>> : " + cdListSize);
		
		ModelAndView mav = new ModelAndView();
		if(emno != null){
			System.out.println("(log) emno 있어서 if문 진입");
			
			mav.addObject("cdList", cdList);
			mav.addObject("sList", sList);
		}	// end of if(emno != null)
		mav.setViewName(CONTEXT_PATH + "/emDetail");
		
		return mav;
	}	// end of EmController.emDetail() 함수

	/**************************************************
	 * 사원 상세 정보 수정
	 **************************************************/
	@RequestMapping(value="/emUpdate", method=RequestMethod.POST)
	public ModelAndView emUpdatest(@ModelAttribute EmPrInfoVO epvo, EmInfoVO evo, HttpServletRequest request){
		
		logger.info("emUpdate 호출 성공");
		
		ModelAndView mav = new ModelAndView();
		
		// 파일 경로
		String uploadPath = FilePath.UPLOAD_FILE_PATH;
		String downloadPath = FilePath.DOWNLOAD_FILE_PATH;
		
		// FileUploadUtil class
		EmFileUploadUtil fu = new EmFileUploadUtil();
		boolean bFlag = false;
		bFlag = fu.fileUpload(request, uploadPath);
		logger.info("bFlag >>> : " + bFlag);
		
		if(bFlag){
			logger.info("bFlag true여서 진입");
			
			Enumeration<String> en = fu.getFileNames();
			
			String empic = en.nextElement();	// 사진
			String emsign = en.nextElement();	// 사인
			logger.info("firstFile / secondFile >>> : " + empic + " / " + emsign);
			
			if(empic != null && emsign == null){
				System.out.println("(log) 사진이 바뀌고 사인은 유지");
				evo.setEmpic(downloadPath + empic);
				evo.setEmsign("");
				System.out.println("(log) epvo.getEmsign() >>> : " + evo.getEmsign());
				System.out.println("(log) epvo.getEmpic() >>> : " + evo.getEmpic());
		
			}else if(empic == null && emsign != null){
				System.out.println("(log) 사진 유지하고 사인 바뀜");
				evo.setEmpic("");
				evo.setEmsign(downloadPath + emsign);
				System.out.println("(log) evo.getEmsign() >>> : " + evo.getEmsign());
				System.out.println("(log) evo.getEmpic() >>> : " + evo.getEmpic());
		
			}else if(empic == null && emsign == null){
				System.out.println("(log) 사진 유지 사인 유지");
				evo.setEmsign("");
				evo.setEmpic("");
				System.out.println("(log) evo.getEmsign() >>> : " + evo.getEmsign());
				System.out.println("(log) evo.getEmpic() >>> : " + evo.getEmpic());
			}
			if(empic != null && emsign != null){
				System.out.println("(log) 사진 변경 & 서명 변경");
				epvo.setEmpic(downloadPath + empic);
				evo.setEmsign(downloadPath + emsign);
				System.out.println("(log) evo.getEmsign() >>> : " + evo.getEmsign());
				System.out.println("(log) evo.getErpic() >>> : " + evo.getEmpic());
			}// end of if
			
			// EM_INFO 테이블에 들어갈 레코드
			String emno = fu.getParameter("emno");
			String emname = fu.getParameter("emname");
			String emid = fu.getParameter("emid");
			String empw = fu.getParameter("empw");
			String emhiredate = fu.getParameter("emhiredate");
			String deptcd = fu.getParameter("deptcd");
			String jobcd = fu.getParameter("jobcd");
			String ememailid = fu.getParameter("ememailid");
			String ememailadd = fu.getParameter("ememailadd");
			String ememail = ememailid + "@" + ememailadd;
			String emfhp = fu.getParameter("emfhp");
			String emshp = fu.getParameter("emshp");
			String emlhp = fu.getParameter("emlhp");
			String emhp = emfhp + emshp + emlhp;
			String emexno = fu.getParameter("emexno");
			String emfdino = fu.getParameter("emfdino");
			String emsdino = fu.getParameter("emsdino");
			String emldino = fu.getParameter("emldino");
			String emdino = emfdino + emsdino + emldino;
						
			// EM_PRINFO 테이블에 들어갈 레코드
			String emprno = fu.getParameter("emprno");
			// EM_PRINFO에도 emno가 들어가지만 위에서 받아와서 주석 처리
	//		String emno = mr.getParameter("emno");
			String emprsex = fu.getParameter("emprsex");
			String emprbirth = fu.getParameter("emprbirth");
			String emprmarital = fu.getParameter("emprmarital");
			String emprpostno = fu.getParameter("emprpostno");
			String empradd = fu.getParameter("empradd");
			String emprdetailadd = fu.getParameter("emprdetailadd");
			String empreducd = fu.getParameter("empreducd");
			String empreduname = fu.getParameter("empreduname");
			
			// EM_INFO 테이블 set
			evo.setEmno(emno);
			evo.setEmname(emname);
			evo.setEmid(emid);
			evo.setEmpw(empw);
			evo.setEmhiredate(emhiredate);
			evo.setEmemail(ememail);
			evo.setEmhp(emhp);
			evo.setEmdino(emdino);
			evo.setEmexno(emexno);
			evo.setDeptcd(deptcd);
			evo.setJobcd(jobcd);
						
			// EM_PRINFO 테이블 set
			epvo.setEmno(emno);
			epvo.setEmprsex(emprsex);
			epvo.setEmprbirth(emprbirth);
			epvo.setEmprmarital(emprmarital);
			epvo.setEmprpostno(emprpostno);
			epvo.setEmpradd(empradd);
			epvo.setEmprdetailadd(emprdetailadd);
			epvo.setEmpreducd(empreducd);
			epvo.setEmpreduname(empreduname);
			
			// update 함수 실행
			int emresult = 0;
			int emprresult = 0;
			
			dtd = new DefaultTransactionDefinition();
			dtd.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			ts= ptm.getTransaction(dtd);
			
			try{
				emresult = emService.emUpdate(evo);
				emprresult = emService.emprUpdate(epvo);
			
				logger.info("emresult / emprresult >>> : " + emresult + " / " + emprresult);
			
				ptm.commit(ts);
				
				logger.info("commit 완료");
			}catch(Exception e){
				ptm.rollback(ts);
				
				logger.info("rollback 완료");
				
				epvo.setEmno("");
				List<EmPrInfoVO> list = emService.emAllSelect(epvo);
				
				mav.addObject("emList", list);
				mav.setViewName(CONTEXT_PATH + "/emAllSelect");
			}
			
			if(emresult == 1 && emprresult == 1){
				logger.info("update 함수 잘 실행 되었음");
				
				epvo.setEmno("");
				List<EmPrInfoVO> list = emService.emAllSelect(epvo);
				
				mav.addObject("emList", list);
				mav.setViewName(CONTEXT_PATH + "/emAllSelect");
			}	// end of if(result == 1)
		}else{
			System.out.println("수정 실패했음");
		}	// end of if(bFlag)
		
		System.out.println("(log) EmController.emUpdate() 함수 끝");
		return mav;
		
	}

	/**************************************************
	 * 사원 삭제
	 **************************************************/
	@RequestMapping(value="/emDelete")
	public ModelAndView emDelete(@ModelAttribute EmPrInfoVO epvo, EmInfoVO evo, HttpServletRequest request){
		logger.info("Controller - emDelete 호출 성공");
		
		ModelAndView mav = new ModelAndView();
		
		String uploadPath = FilePath.UPLOAD_FILE_PATH;
		
		// FileUploadUtil class
		EmFileUploadUtil fu = new EmFileUploadUtil();
		boolean bFlag = fu.fileUpload(request, uploadPath);
		logger.info("bFlag >>> : " + bFlag);
		
		String emno = fu.getParameter("emno");
		
		epvo.setEmno(emno);
			
		int result = 0;
		result = emService.emDelete(epvo);
		logger.info("result >>> : " + result);
			
		if(result == 1){
			logger.info("delete 함수 작동했음");
			
			epvo.setEmno("");
			List<EmPrInfoVO> list = emService.emAllSelect(epvo);
			
			mav.addObject("emList", list);
			mav.setViewName(CONTEXT_PATH + "/emAllSelect");
		}
		return mav;
	}	// end of EmController.emDelete() 함수

	/**************************************************
	 * 사원 검색
	 **************************************************/
	@RequestMapping(value="/emSearch")
	public ModelAndView emSearch(@ModelAttribute EmPrInfoVO epvo, HttpServletRequest request){
		
		logger.info("Controller - emSearch 성공");
		
		ModelAndView mav = new ModelAndView();
		
		String search = "";
		String keyword = "";
		
		search = request.getParameter("search");
		keyword = request.getParameter("keyword");
		
		// 검색에 대한 데이터 확인
		logger.info("search >>> : " + search);
		logger.info("keyword >>> : " + keyword);
		
		// 페이징 부분
		pageSize = 10;
		String cp = request.getParameter("curPage");
		if(cp!=null){
			curPage = Integer.parseInt(cp);
		}else{
			curPage = 1;
		}	// end of if(cp!=null)
		logger.info("curPage >>> : " + curPage);
		
		EmPrInfoVO epVO = new EmPrInfoVO();
		epVO.setSearch(search);
		epVO.setKeyword(keyword);
		epVO.setPageSize(pageSize);
		epVO.setCurPage(curPage);
		
		List<EmPrInfoVO> list = emService.emSearch(epVO);
		int listSize = list.size();
		logger.info("listSize >>> : " + listSize);
		
		for(int i=0; i<list.size(); i++){
			EmPrInfoVO ePVO = null;
			ePVO = new EmPrInfoVO();
			ePVO = list.get(i);
			pageNo = ePVO.getPageNo();
			totalCount = ePVO.getTotalCount();
			curPage = ePVO.getCurPage();
			groupSize = ePVO.getGroupSize();
			logger.info("pageNo/totalCount/curPage/groupSize >>> :"
					+ pageNo + "/" + totalCount + "/" + curPage + "/" + groupSize);
		}	// end of for
		
		if(listSize > 0){
			mav.addObject("emList", list);
			mav.addObject("pageNo", pageNo);
			mav.addObject("totalCount", totalCount);
			mav.addObject("curPage", curPage);
			mav.addObject("groupSize", groupSize);
			mav.setViewName(CONTEXT_PATH + "/emAllSelect");
		}	// end of if(scListSize > 0)
		
		return mav;
	}	// end of EmController.emSearch() 함수

	/**************************************************
	 * 교육사항등록 폼 출력하기
	 **************************************************/
	@RequestMapping(value="/trInsertForm")
	public ModelAndView trInsertForm(@ModelAttribute EmInfoVO evo){
		
		logger.info("trInsertForm 호출 성공");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName(CONTEXT_PATH + "/trInsert");
		
		return mav;
	}	// end of EmController.trInsertForm() 함수
	
	/**************************************************
	 * 교육사항등록
	 **************************************************/
	@RequestMapping(value="/trInsert", method=RequestMethod.GET)
	public ModelAndView trInsert(@ModelAttribute EmHrVO ehvo){
		
		logger.info("trInsert 호출 성공");
		
		ModelAndView mav = new ModelAndView();
		
		// 변수 초기화
		String trno = "";
		String emno = "";
		String emname = "";
		String deptcd = "";
		String jobcd = "";
		String trfday = "";
		String treday = "";
		String trname = "";
		String trcontent = "";
		String trnote = "";
		
		// ehvo에서 getter 함수로 값 꺼내기
		emno = ehvo.getEmno();
		emname = ehvo.getEmname();
		deptcd = ehvo.getDeptcd();
		jobcd = ehvo.getJobcd();
		trfday = ehvo.getTrfday();
		treday = ehvo.getTreday();
		trname = ehvo.getTrname();
		trcontent = ehvo.getTrcontent();
		trnote = ehvo.getTrnote();
		logger.info("emno >>> : " + emno);
		logger.info("emname >>> : " + emname);
		
		// VO 새로 선언
		EmHrVO ehVO = null;
		ehVO = new EmHrVO();
		
		// 채번
		List<EmHrVO> cList = emService.trChaebun(ehvo);
		logger.info("cList.size() >>> : " + cList.size());
		
		if(cList.size()==1){
			logger.info("채번 잘 돌아서 if문 진입");
			
			String _no = cList.get(0).getTrno();
			trno = Chaebun.chaebunCreateTR(_no);
			logger.info("trno >>> : " + trno);
			ehVO.setTrno(trno);
		}	// end of if(cList.size()==1)
		
		// setter 함수로 값 넣기
		ehVO.setTrno(trno);
		ehVO.setEmno(emno);
		ehVO.setEmname(emname);
		ehVO.setDeptcd(deptcd);
		ehVO.setJobcd(jobcd);
		ehVO.setTrfday(trfday);
		ehVO.setTreday(treday);
		ehVO.setTrname(trname);
		ehVO.setTrcontent(trcontent);
		ehVO.setTrnote(trnote);
		
		// insert 함수 실행
		int result = 0;
		result = emService.trInsert(ehVO);
		logger.info("result >>> : " + result);
		
		if(result == 1){
			logger.info("insert 함수 돌았음");
			mav.setViewName(CONTEXT_PATH + "/emAdminMain");
		}	// end of if(result == 1)
		
		return mav;
	}	// end of EmController.trInsert() 함수

	/**************************************************
	 * 교육사항등록 폼 - 사원조회팝업
	 **************************************************/
	@RequestMapping(value="/trPopup")
	public ModelAndView trSelect(@ModelAttribute EmPrInfoVO epvo){
		
		logger.info("Controller - trPopup 호출 성공");
		
		List<EmPrInfoVO> list = emService.emAllSelect(epvo);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("emList", list);
		mav.setViewName(CONTEXT_PATH + "/tr_pop");
		
		return mav;
	}	// end of EmController.trSelect() 함수
	
	/**************************************************
	 * 교육사항목록
	 **************************************************/
	@RequestMapping(value="/trAllSelect")
	public ModelAndView trAllSelect(@ModelAttribute EmHrVO ehvo){
		
		logger.info("Controller - trAllSelect 호출 성공");
		
		List<EmHrVO> trList = emService.trAllSelect(ehvo);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("trList", trList);
		mav.setViewName(CONTEXT_PATH + "/trAllSelect");
		
		return mav;
	}	// end of EmController.trAllSelect() 함수
	
	/**************************************************
	 * 교육사항 상세 조회
	 **************************************************/
	@RequestMapping(value="/trDetail")
	public ModelAndView trDetail(@ModelAttribute EmHrVO ehvo){
		
		logger.info("Controller - trDetail 호출 성공");
		
		// 매개변수 출력
		String trno = ehvo.getTrno();
		logger.info("trno >>> : " + trno);
		
		List<EmHrVO> trDList = emService.trDetail(ehvo);
		int trDListSize = trDList.size();
		logger.info("trDListSize >>> : " + trDListSize);
		
		ModelAndView mav = new ModelAndView();
		if(trno != null){
			logger.info("trno 있어서 if문 진입");
			mav.addObject("trDList", trDList);
			mav.setViewName(CONTEXT_PATH + "/trDetail");
		}	// end of if(trno != null)
		
		return mav;
	}	// end of EmController.trAllSelect() 함수

	/**************************************************
	 * 교육 사항 수정
	 **************************************************/
	@RequestMapping(value="/trUpdate", method=RequestMethod.GET)
	public ModelAndView trUpdate(@ModelAttribute EmHrVO ehvo){
		
		logger.info("Controller - trUpdate 호출 성공");
		
		ModelAndView mav = new ModelAndView();
		
		// 변수 초기화
		String trno = "";
//		String emno = "";
//		String emname = "";
//		String deptcd = "";
//		String jobcd = "";
		String trfday = "";
		String treday = "";
		String trname = "";
		String trcontent = "";
		String trnote = "";
		
		// ehvo에서 getter 함수로 값 꺼내기
		trno = ehvo.getTrno();
//		emno = ehvo.getEmno();
//		emname = ehvo.getEmname();
//		deptcd = ehvo.getDeptcd();
//		jobcd = ehvo.getJobcd();
		trfday = ehvo.getTrfday();
		treday = ehvo.getTreday();
		trname = ehvo.getTrname();
		trcontent = ehvo.getTrcontent();
		trnote = ehvo.getTrnote();
		logger.info("trname >>> : " + trname);
		logger.info("trcontent >>> : " + trcontent);
		
		// VO 새로 선언
		EmHrVO ehVO = null;
		ehVO = new EmHrVO();
		
		// setter 함수로 값 넣기
		ehVO.setTrno(trno);
//		ehVO.setEmno(emno);
//		ehVO.setEmname(emname);
//		ehVO.setDeptcd(deptcd);
//		ehVO.setJobcd(jobcd);
		ehVO.setTrfday(trfday);
		ehVO.setTreday(treday);
		ehVO.setTrname(trname);
		ehVO.setTrcontent(trcontent);
		ehVO.setTrnote(trnote);
		
		// update 함수 실행
		int result = 0;
		result = emService.trUpdate(ehVO);
		logger.info("result >>> : " + result);
		
		if(result == 1){
			logger.info("update 함수 돌았음");
			mav.setViewName(CONTEXT_PATH + "/emAdminMain");
		}	// end of if(result == 1)
		
		return mav;
	}	// end of EmController.trUpdate() 함수

	/**************************************************
	 * 근태 - 인원 추가 인서트
	 **************************************************/
	@RequestMapping(value="/ctInsert", method=RequestMethod.POST)
	public ModelAndView ctInsert(@ModelAttribute EmPrInfoVO epvo, HttpServletRequest request){
		
		logger.info("Controller - ctInsert 호출 성공");
		
		ModelAndView mav = new ModelAndView();
		
		pageSize = 10;
		String cp = request.getParameter("curPage");
		if(cp!=null){
			curPage = Integer.parseInt(cp);
		}else{
			curPage = 1;
		}	// end of if(cp!=null)
		logger.info("curPage >>> : " + curPage);
		
		epvo.setPageSize(pageSize);
		epvo.setCurPage(curPage);
		
		List<EmPrInfoVO> list = emService.emAllSelect(epvo);
		
		// 파일 경로
		String uploadPath = FilePath.UPLOAD_FILE_PATH;
		
		// FileUploadUtil class
		EmFileUploadUtil fu = new EmFileUploadUtil();
		boolean bFlag = false;
		bFlag = fu.fileUpload(request, uploadPath);
		logger.info("bFlag >>> : " + bFlag);
		
		if(bFlag){
			logger.info("bFlag true여서 진입");
			
			Enumeration<String> en = fu.getFileNames();
			String emname = fu.getParameter("emname");		// 이름
			String deptcd = fu.getParameter("deptcd");		// 
			String jobcd = fu.getParameter("jobcd");		// 
			String emno = fu.getParameter("emno");		// 
			logger.info(emname + "/" + deptcd + "/" + jobcd + "/" + emno);
			// 변수
			String ctno = "";		// 근태 테이블 PK 채번
			String ctincd = "58";	// 출근 코드 : 58(결근)
			String ctoutcd = "58";	// 퇴근 코드 : 58 (결근)
			String ctnote = "";		// 비고라서 NULL 들어가게
			
			// VO 새로 선언
			EmPrInfoVO epVO = null;
			epVO = new EmPrInfoVO();
			
			// ctno 채번
			List<EmInfoVO> cList = emService.ctChaebun(epvo);
			logger.info("cList.size() >>> : " + cList.size());
			
			// 채번 돌았는지 확인
			if(cList.size()>0){
				logger.info("채번 잘 돌아서 if문 진입");
				String _no = cList.get(0).getCtno();
				ctno = Chaebun.chaebunCreateCT(_no);
				logger.info("ctno >>> : " + ctno);
				epVO.setCtno(ctno);
			}	// end of if(cList.size()>1)
			
			// 변수 세팅
			epVO.setEmname(emname);
			epVO.setDeptcd(deptcd);
			epVO.setJobcd(jobcd);
			epVO.setEmno(emno);
			epVO.setCtincd(ctincd);
			epVO.setCtoutcd(ctoutcd);
			epVO.setCtnote(ctnote);
			
			int result = 0;
			result = emService.ctInsert(epVO);
			logger.info("result >>> : " + result);
			
			if(result == 1){
				mav.addObject("emList", list);
				mav.addObject("pageNo", pageNo);
				mav.addObject("totalCount", totalCount);
				mav.addObject("curPage", curPage);
				mav.addObject("groupSize", groupSize);
				mav.setViewName(CONTEXT_PATH + "/emAllSelect");
			}	// end of if(result == 1)
		}
//		
//		emname = epvo.getEmname();
//		deptcd = epvo.getDeptcd();
//		jobcd = epvo.getJobcd();
//		emno = epvo.getEmno();
//		
//		System.out.println("변수들.. >>> : " + emname + " / " + deptcd + " / " + jobcd + " / " + emno);
		
		return mav;
	}	// end of EmController.ctInsert() 함수

	/**************************************************
	 * 근태 - 출근
	 **************************************************/
	@RequestMapping(value="/ctInUpdate")
	public String ctInUpdate(@ModelAttribute EmInfoVO param, HttpSession httpsession){
		logger.info("Controller - ctInUpdate 호출 성공");
		
		ModelAndView mav = new ModelAndView();
		
		Object obj = httpsession.getAttribute("eminfo");
		EmInfoVO eVO = null;
		eVO = new EmInfoVO();
		eVO = (EmInfoVO)obj;
		
		String url="";
	    
		String emno = eVO.getEmno();
		logger.info("emno >>> : " + emno);
		
		int result = 0;
		result = emService.ctIntimeUpdate(param);
		logger.info("result >>> : " + result);
		
		if(result == 1){
			// 출/퇴근 시간 조회
			List<EmInfoVO> ctList = emService.ctSelect(param);
			int ctListSize = ctList.size();
			logger.info("ctListSize >>> : " + ctListSize);
			
			// 시간 / 분 자르기
			if(ctListSize > 0){
				for(int i=0; i<ctListSize; i++){
					EmInfoVO evo1 = (EmInfoVO)ctList.get(i);
					
					String ctintime = evo1.getCtintime();
					int idx = ctintime.indexOf(":");
					String ctintimehh = ctintime.substring(0, idx);
					int hh = Integer.valueOf(ctintimehh);
					String ctintimemm = ctintime.substring(idx+1);
					int mm = Integer.valueOf(ctintimemm);
					
					int cdResult = 0;
					String ctincd = "";
					if(hh < 9 || (hh==9 && mm==0)){
						logger.info("정상출근");
						ctincd="51";
						param.setCtincd(ctincd);
						cdResult = emService.ctUpdate(param);
					}	// end of if(hh <= 9 && mm <= 0)
						
					if((hh == 9 && mm > 0) || hh > 9){
						logger.info("지각");
						ctincd="52";
						param.setCtincd(ctincd);
						logger.info("ctincd >>> : " + ctincd);
						cdResult = emService.ctUpdate(param);
					}	// end of if(hh >= 9 && mm >= 1)
				}	// end of for
			}	// end of if(listSize > 0)
			url = "/em/goToMain.ge";
		}	// end of if(result == 1)
		return "redirect:"+url;
	}	// end of EmController.ctInUpdate() 함수
	
	/**************************************************
	 * 근태 - 퇴근
	 **************************************************/
	@RequestMapping(value="/ctOutUpdate")
	public String ctOutUpdate(@ModelAttribute EmInfoVO evo, HttpSession httpsession){
		
		logger.info("Controller - ctInUpdate 호출 성공");
		
		String url="";
		
		ModelAndView mav = new ModelAndView();
		
		String emno = evo.getEmno();
		logger.info("emno >>> : " + emno);
		
		int result = 0;
		result = emService.ctOuttimeUpdate(evo);
		logger.info("result >>> : " + result);
		
		if(result == 1){
			// 출/퇴근 시간 조회
			List<EmInfoVO> list = emService.ctSelect(evo);
			int listSize = list.size();
			logger.info("listSize >>> : " + listSize);
			
			// 시간 / 분 자르기
			if(listSize > 0){
				for(int i=0; i<listSize; i++){
					EmInfoVO evo1 = (EmInfoVO)list.get(i);
					
					// 시간, 분 자르기
					String ctouttime = evo1.getCtouttime();
					int idx = ctouttime.indexOf(":");
					String ctouttimehh = ctouttime.substring(0, idx);
					int hh = Integer.valueOf(ctouttimehh);
					String ctouttimemm = ctouttime.substring(idx+1);
					int mm = Integer.valueOf(ctouttimemm);
					logger.info("hh / mm >>> : " + hh + " / " + mm);
					
					int cdResult = 0;
					String ctoutcd = "";
					if(hh < 18){
						logger.info("조퇴");
						ctoutcd="59";
						evo.setCtoutcd(ctoutcd);
						cdResult = emService.ctOutUpdate(evo);
					}	// end of if(hh < 18)
					if(hh==18 && mm < 30){
						logger.info("정상퇴근");
						ctoutcd="56";
						evo.setCtoutcd(ctoutcd);
						cdResult = emService.ctOutUpdate(evo);
					}	// end of if(hh==18 && mm < 30)
					if((hh==18 && mm>=30) || (hh>18)){
						logger.info("연장근무");
						ctoutcd="57";
						evo.setCtoutcd(ctoutcd);
						cdResult = emService.ctOutUpdate(evo);
					}	// end of if((hh==18 && mm>=30) && (hh>18))
				}	// end of for
			}	// end of if(listSize > 0)
			
			int logResult = 0;
			logResult = emService.goToLog(evo);
			logger.info("result >>> : " + result);
			
			url = "/em/goToMain.ge";
			
		}	// end of if(result == 1)
		
		return "redirect:"+url;
	}	// end of EmController.ctOutUpdate() 함수
	
	/**************************************************
	 * 메인 이동 함수
	 **************************************************/
	@RequestMapping(value="/goToMain")
	public ModelAndView goToMain(HttpSession httpsession, HttpServletRequest request){
		logger.info("메인 페이지 이동");
		
		ModelAndView mav = new ModelAndView();
		
		Object obj = httpsession.getAttribute("eminfo");
		EmInfoVO eVO = null;
		eVO = new EmInfoVO();
		eVO = (EmInfoVO)obj;
		
		String emid = "";
	    String empw = "";
	    String emno = "";
	    emid = eVO.getEmid();
	    empw= eVO.getEmpw();
	    emno = eVO.getEmno();
	    logger.info("emid >>> " + emid);
	    logger.info("empw >>> " + empw);
	    logger.info("emno >>> " + emno);
	    
	    eVO.setEmid(emid);
	    eVO.setEmpw(empw);
	    
	    // 전자결재
	    String ea_stepno = "";
	     
	    logger.info("emno : " + emno);
	    EaVO evo = new EaVO();
	    evo.setEa_empno(emno);
	    evo.setEmno(emno);
	    
	    /******************************************************************/
		// 전자결재 메인에서 보여야 할 것들
		List<EaVO> list = eaService.eaMainSelectAll(evo);   // 전체리스트
	    List<EaVO> list0 = eaService.eaMainSelectAllRJ(evo);   // 04함
	    List<EaVO> list1 = eaService.eaMainSelectAllFN(evo);  // 완료함
	    List<EaVO> list2 = eaService.eaMainSelectAllPG(evo);   // 진행중
	    List<EaVO> list3 = eaService.eaMainApprove(evo); // 결재해야할 문서 출력
	     
	    logger.info("list size : " + list.size());
	    logger.info("list0 size : " + list0.size());
	    logger.info("list1 size : " + list1.size());
	    logger.info("list2 size : " + list2.size());
	    logger.info("list3 size : " + list3.size());
	   
	    /******************************************************************/
	    // 게시판
	    //부서별 게시판
	    BDVO check = null;
		check = new BDVO();
		
		check.setDeptcd(eVO.getDeptcd());
		List<BDVO> deptList = bDService.boardDeptMainList(check);
		
		//공지사항 게시판
		BNVO check1 = null;
		check1 = new BNVO();
		
		List<BNVO> noticeList = bNService.boardNoticeMainList(check1);
		/******************************************************************/
		// 출퇴근 조회
		List<EmInfoVO> ctList = emService.ctSelect(eVO);
		/******************************************************************/
		// mav
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
		
		return mav;
	}	// end of goToMain

	/**************************************************
	 * 근태 목록
	 **************************************************/
	@RequestMapping(value="/ctAllSelect")
	public ModelAndView ctAllSelect(@ModelAttribute EmInfoVO evo, HttpServletRequest request){
		
		logger.info("Controller - ctAllSelect 호출 성공");
		
		ModelAndView mav = new ModelAndView();
		
		pageSize = 10;
		String cp = request.getParameter("curPage");
		if(cp!=null){
			curPage = Integer.parseInt(cp);
		}else{
			curPage = 1;
		}	// end of if(cp!=null)
		logger.info("curPage >>> : " + curPage);
		
//		EmInfoVO eVO = new EmInfoVO();
		evo.setPageSize(pageSize);
		evo.setCurPage(curPage);
		
		List<EmInfoVO> ctList = emService.ctAllSelect(evo);
		
		if(ctList.size() > 0){
			for(int i=0; i<ctList.size(); i++){
				EmInfoVO eVO = null;
				eVO = new EmInfoVO();
				eVO = ctList.get(i);
				pageNo = eVO.getPageNo();
				totalCount = eVO.getTotalCount();
				curPage = eVO.getCurPage();
				groupSize = eVO.getGroupSize();
				logger.info("pageNo/totalCount/curPage/groupSize >>> :"
						   + pageNo + "/" + totalCount + "/" + curPage + "/" + groupSize);
		}
			mav.addObject("ctList", ctList);
			mav.addObject("pageNo", pageNo);
			mav.addObject("totalCount", totalCount);
			mav.addObject("curPage", curPage);
			mav.addObject("groupSize", groupSize);
			mav.setViewName(CONTEXT_PATH + "/ctAllSelect");
		}	// end of if(ctList.size() > 0)
		
		return mav;
	}	// end of EmController.ctAllSelect() 함수
	
	/**************************************************
	 * 근태 기간 조회
	 **************************************************/
	@RequestMapping(value="/ctSearch")
	public ModelAndView ctSearch(@ModelAttribute EmInfoVO evo, HttpServletRequest request){
		
		logger.info("Controller - ctSearch 성공");
		
		ModelAndView mav = new ModelAndView();
		
		String search = "";
		String keyword = "";
		String ctstdate = "";
		String cteddate = "";
		
		search = request.getParameter("search");
		keyword = request.getParameter("keyword");
		ctstdate = request.getParameter("ctstdate");
		cteddate = request.getParameter("cteddate");

		logger.info("search >>> : " + search);
		logger.info("ctstdate >>> : " + ctstdate);
		logger.info("cteddate >>> : " + cteddate);
		
		// 날짜에서 - 없애버리기
		ctstdate = ctstdate.replace("-", "");
		cteddate = cteddate.replace("-", "");
		
		logger.info("ctstdate >>> : " + ctstdate);
		logger.info("cteddate >>> : " + cteddate);
		
		pageSize = 10;
		String cp = request.getParameter("curPage");
		if(cp!=null){
			curPage = Integer.parseInt(cp);
		}else{
			curPage = 1;
		}	// end of if(cp!=null)
		logger.info("curPage >>> : " + curPage);
		
		EmInfoVO eVO = new EmInfoVO();
		eVO.setSearch(search);
		eVO.setKeyword(keyword);
		eVO.setCtstdate(ctstdate);
		eVO.setCteddate(cteddate);
		eVO.setPageSize(pageSize);
		eVO.setCurPage(curPage);
		
		List<EmInfoVO> sList = emService.ctSearch(eVO);
		logger.info("sList.size() >>> : " + sList.size());
	//	List<EmInfoVO> ctList = emService.ctAllSelect(eVO);
		
		for(int i=0; i<sList.size(); i++){
			EmInfoVO eiVO = null;
			eiVO = new EmInfoVO();
			eiVO = sList.get(i);
			pageNo = eiVO.getPageNo();
			totalCount = eiVO.getTotalCount();
			curPage = eiVO.getCurPage();
			groupSize = eiVO.getGroupSize();
		}	// end of for
			logger.info("pageNo/totalCount/curPage/groupSize >>> :"
				+ pageNo + "/" + totalCount + "/" + curPage + "/" + groupSize);
		
		mav.addObject("sList", sList);
		mav.addObject("eVO", eVO);
		mav.addObject("pageNo", pageNo);
		mav.addObject("totalCount", totalCount);
		mav.addObject("curPage", curPage);
		mav.addObject("groupSize", groupSize);
		mav.setViewName(CONTEXT_PATH + "/ctSearch");
		
		return mav;
	}	// end of EmController.ctSearch() 함수

	/**************************************************
	 * 근태 자료 로그 테이블로 이동 && 전 사원 결근으로 코드 업데이트
	 **************************************************/
	@RequestMapping(value="/ctChange")
	public ModelAndView ctChange(@ModelAttribute EmInfoVO evo){
		
		logger.info("Controller - ctChange 호출 성공");
		
		ModelAndView mav = new ModelAndView();
		
		// 변수 선언
		int logResult = 0;
		int nextResult = 0;
		
		// 트랜잭션 처리
		dtd = new DefaultTransactionDefinition();
		dtd.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		ts= ptm.getTransaction(dtd);
		
		try{
			// 1) 로그테이블 insert
			// 채번
//			List<EmInfoVO> cList = emService.logChaebun(evo);
//			logger.info("cList.size() >>> : " + cList.size());
//			
//			if(cList.size()>0){
//				logger.info("채번 잘 돌아서 if문 진입");
//				
//				String _no = cList.get(0).getLogno();
//				String logno = Chaebun.logChaebunFormat(_no);
//				logger.info("logno >>> : " + logno);
//				evo.setLogno(logno);
//			}	// end of if(cList.size()==1)
//			
			logResult = emService.ctToLog();
//			
			// 2) 전 사원 결근으로 코드 업데이트
			String ctincd="58";
			String ctoutcd="58";
			evo.setCtincd(ctincd);
			evo.setCtoutcd(ctoutcd);
			
			nextResult = emService.ctNextDay();
			
			logger.info("logResult / nextResult >>> : " + logResult + " / " + nextResult);
			
			ptm.commit(ts);
			
			logger.info("commit 완료");
		}catch(Exception e){
			ptm.rollback(ts);
			
			logger.info("rollback 완료");
			
			mav.setViewName(CONTEXT_PATH + "/ctAllSelect");
		}	// end of try-catch
		
		// logResult > 0 && 
		if(nextResult > 1){
			logger.info("로그 테이블로 insert 완료 & 결근으로 update 완료");
			
			mav.setViewName(CONTEXT_PATH + "/emAdminMain");
		}	// end of if(logResult == 1 && nextResult == 1)
		
		return mav;
	}	// end of EmController.ctChange() 함수

	/**************************************************
	 * 근태 - 관리자 조정
	 **************************************************/
	@RequestMapping(value="/ctAdminUpdate")
	public ModelAndView ctAdminUpdate(@ModelAttribute EmInfoVO evo, HttpSession httpsession, HttpServletRequest request){
		
		logger.info("Controller - ctInUpdate 호출 성공");
		
		ModelAndView mav = new ModelAndView();
		
		pageSize = 10;
		String cp = request.getParameter("curPage");
		if(cp!=null){
			curPage = Integer.parseInt(cp);
		}else{
			curPage = 1;
		}	// end of if(cp!=null)
		logger.info("curPage >>> : " + curPage);
		
		evo.setPageSize(pageSize);
		evo.setCurPage(curPage);
		
		
		
		String emno = evo.getEmno();
		logger.info("emno >>> : " + emno);
		String emname = evo.getEmname();
		logger.info("emname >>> : " + emname);
		
		String ctincd="";
		String ctoutcd="";
		String ctnote="";
		
		ctincd = evo.getCtincd();
		ctoutcd = evo.getCtoutcd();
		ctnote = evo.getCtnote();
		logger.info("ctincd/ctoutcd/ctnote >>> : " +
		ctincd + "/" + ctoutcd + "/" + ctnote);
		
		EmInfoVO eVO = null;
		eVO = new EmInfoVO();
		
		eVO.setEmno(emno);
		eVO.setCtincd(ctincd);
		eVO.setCtoutcd(ctoutcd);
		eVO.setCtnote(ctnote);
		
		int result = 0;
	    result = emService.ctAdminUpdate(eVO);
	    logger.info("result >>> : " + result);
	
    	if(result > 0){
			logger.info("업데이트 성공해서 if문 진입");
			
			List<EmInfoVO> ctList = emService.ctAllSelect(evo);
			
			if(ctList.size() > 0){
				for(int i=0; i<ctList.size(); i++){
					EmInfoVO eivo = null;
					eivo = new EmInfoVO();
					eivo = ctList.get(i);
					pageNo = eivo.getPageNo();
					totalCount = eivo.getTotalCount();
					curPage = eivo.getCurPage();
					groupSize = eivo.getGroupSize();
					logger.info("pageNo/totalCount/curPage/groupSize >>> :"
							   + pageNo + "/" + totalCount + "/" + curPage + "/" + groupSize);
				}
			}
			if(ctList.size() > 0){
				mav.addObject("ctList", ctList);
				mav.addObject("pageNo", pageNo);
				mav.addObject("totalCount", totalCount);
				mav.addObject("curPage", curPage);
				mav.addObject("groupSize", groupSize);
				mav.setViewName(CONTEXT_PATH + "/ctAllSelect");
			}	// end of if(ctList.size() > 0)
			
		}    // end of if(result > 0)
		
		return mav;
	}	// end of EmController.ctAdminUpdate() 함수

	/**************************************************
	 * 근태 조회 폼 - 근태 상세 조회 팝업
	 **************************************************/
	@RequestMapping(value="/ctSelect")
	public ModelAndView ctSelect(@ModelAttribute EmInfoVO evo){
		
		logger.info("Controller - ctSelect 호출 성공");
		
		logger.info("emno >>> : " + evo.getEmno());
		List<EmInfoVO> list = emService.ctSelect(evo);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("ctList", list);
		mav.setViewName(CONTEXT_PATH + "/ctSelect");
		
		return mav;
	}	// end of EmController.ctSelect() 함수

	/**************************************************
	 * 마이페이지
	 **************************************************/
	@RequestMapping(value="/myPageInfo")
	public ModelAndView myPageInfo(HttpSession httpsession){
		
		logger.info("Controller - myPageInfo 성공");
		
		Object obj = httpsession.getAttribute("eminfo");
		EmInfoVO eVO = null;
		eVO = new EmInfoVO();
		eVO = (EmInfoVO)obj;
		
		String emno = eVO.getEmno();
		logger.info("emno >>> : " + emno);
		
		EmPrInfoVO epvo = null;
		epvo = new EmPrInfoVO();
		epvo.setEmno(emno);
		logger.info("epvo.getEmno() >>> : " + epvo.getEmno());
		
		List<EmPrInfoVO> myList = emService.myPageInfo(epvo);
		int myListSize = myList.size();
		logger.info("myListSize >>> : " + myListSize);
		
		ModelAndView mav = new ModelAndView();
		if(emno != null){
			mav.addObject("myList", myList);
			mav.setViewName(CONTEXT_PATH + "/myPageInfo");
		}	// end of if(emno != null)
		
		return mav;
	}	// end of EmController.myPageInfo() 함수

	/**************************************************
	 * 근태 시간되면 업데이트 되도록
	 **************************************************/
	@RequestMapping(value="/autoTask")
	public ModelAndView autoTask(){
		logger.info("Controller - autoTask 호출 성공");
		
		Timer timer = new Timer(false);
		Calendar date = Calendar.getInstance();
		date.set(Calendar.AM_PM, Calendar.PM);
		date.set(Calendar.HOUR, 7);
		date.set(Calendar.MINUTE, 14);
		date.set(Calendar.SECOND, 0);
		date.set(Calendar.MILLISECOND, 0);
		timer.schedule(new TimerTaskTest(), date.getTime(), 24*60*60*1000);
		
		ModelAndView mav = new ModelAndView();
		EmInfoVO evo = null;
		evo = new EmInfoVO();
		List<EmInfoVO> ctList = emService.ctAllSelect(evo);
		mav.addObject("ctList", ctList);
		mav.setViewName(CONTEXT_PATH + "/ctAllSelect");
		return mav;
		
	}	// end of EmController.autoTask() 함수
	
	class TimerTaskTest extends TimerTask{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
			// 변수
			int nextResult = 0;
			
			// 1) 전 사원 결근으로 코드 업데이트
			nextResult = emService.ctNextDay();
			
			logger.info("nextResult >>> : " + nextResult);
				
//			ModelAndView mav = new ModelAndView();
//			
//			EmInfoVO evo = null;
//			evo = new EmInfoVO();
//			List<EmInfoVO> ctList = emService.ctAllSelect(evo);
//			mav.addObject("ctList", ctList);
//			mav.setViewName(CONTEXT_PATH + "/ctAllSelect");
			
		}	// end of TimerTaskTest.run()
	}	// end of TimerTaskTest (Inner Class)
	
	/**************************************************
	 * 퇴근 누르면 로그 테이블로 이동999999999999999999999999999999999999999999999999999999999999
	 **************************************************/
	public ModelAndView goToLog(HttpSession httpsession){
		
		ModelAndView mav = new ModelAndView();
		
		Object obj = httpsession.getAttribute("eminfo");
		EmInfoVO eVO = null;
		eVO = new EmInfoVO();
		eVO = (EmInfoVO)obj;
		
		String emno = eVO.getEmno();
		logger.info("emno >>> : " + emno);
		
		EmInfoVO evo = null;
		evo = new EmInfoVO();
		
		evo.setEmno(emno);
		logger.info("emno >>> : " + emno);
		
		int result = 0;
		result = emService.goToLog(evo);
		logger.info("result >>> : " + result);
		
		if(result > 0){
			mav.setViewName(CONTEXT_PATH + "/emMain");
		}	// end of if(result > 0)
	
		return mav;
	}	// end of EmController.goToLog() 함수
	
	/**************************************************
	 * 비밀번호 변경
	 **************************************************/
	@RequestMapping(value="/pwUpdate")
	public ModelAndView pwUpdate(@ModelAttribute EmInfoVO evo, HttpSession httpsession){
		logger.info("Controller - pwUpdate 호출 성공");
		
		ModelAndView mav = new ModelAndView();
		
		Object obj = httpsession.getAttribute("eminfo");
		EmInfoVO eVO = null;
		eVO = new EmInfoVO();
		eVO = (EmInfoVO)obj;
		
		String emno = eVO.getEmno();
		logger.info("emno >>> : " + emno);
		
		String empw = evo.getEmpw();
		logger.info("empw >>> : " + empw);

		EmInfoVO eVo = null;
		eVo = new EmInfoVO();
		eVo.setEmno(emno);
		eVo.setEmpw(empw);
		
		int result = 0;
		result = emService.pwUpdate(eVo);
		if(result == 1){
			mav.setViewName(CONTEXT_PATH + "/emMain");
		}	// end of if(result == 1)
		return mav;
	}	// end of EmController.pwUpdate() 함수
	
	/**************************************************
	 * 마이페이지 - 교육 목록
	 **************************************************/
	@RequestMapping(value="/myPageTr")
	public ModelAndView myPageTr(HttpSession httpsession){
		
		logger.info("Controller - myPageTr 호출 성공");
		
		ModelAndView mav = new ModelAndView();
		
		Object obj = httpsession.getAttribute("eminfo");
		EmInfoVO evo = null;
		evo = new EmInfoVO();
		evo = (EmInfoVO)obj;
		
		String emno = evo.getEmno();
		logger.info("emno >>> : " + emno);
		
		EmHrVO ehvo = null;
		ehvo = new EmHrVO();
		ehvo.setEmno(emno);
		
		if(emno.length()!=0){
			List<EmHrVO> mtList = emService.myPageTr(ehvo);
			int mtListSize = 0;
			mtListSize = mtList.size();
			logger.info("mtListSize >>> : " + mtListSize);
			if(mtListSize > 0){
				mav.addObject("mtList", mtList);
				mav.setViewName(CONTEXT_PATH + "/myPageTr");
			}	// end of if(mtListSize > 0)
		}	// end of if(emno.length()!=0)
				
		return mav;
	}	// end of EmController.myPageTr() 함수
	
	/**************************************************
	 * 마이페이지 - 교육 목록
	 **************************************************/
	@RequestMapping(value="/myPageTrDetail")
	public ModelAndView myPageTr(@ModelAttribute EmHrVO ehvo, HttpSession httpsession){
		
		logger.info("Controller - myPageTrDetail 호출 성공");
		
		ModelAndView mav = new ModelAndView();
		
		String trno = ehvo.getTrno();
		logger.info("trno >>> : " + trno);
		
		EmHrVO ehVO = null;
		ehVO = new EmHrVO();
		
		ehVO.setTrno(trno);
		
		if(trno.length()!=0){
			List<EmHrVO> myTrList = emService.myPageTrDetail(ehVO);
			int listSize = 0;
			listSize = myTrList.size();
			logger.info("listSize >>> : " + listSize);
			
			if(listSize > 0){
				mav.addObject("myTrList", myTrList);
				mav.setViewName(CONTEXT_PATH + "/myPageTrDetail");
			}	// end of if(listSize > 0)
		}	// end of if(trno.length()!=0)
		
		return mav;
	}	// end of EmController.myPageTrDetail() 함수

	/**************************************************
	 * 평가 등록 폼 출력하기
	 **************************************************/
	@RequestMapping(value="/evInsertForm")
	public ModelAndView evInsertForm(@ModelAttribute EmHrVO ehvo){
		
		logger.info("evInsertForm 호출 성공");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName(CONTEXT_PATH + "/evInsert");
		
		return mav;
	}	// end of EmController.evInsertForm() 함수
	
	/**************************************************
	 * 평가 등록
	 **************************************************/
	@RequestMapping(value="/evInsert", method=RequestMethod.GET)
	public ModelAndView evInsert(@ModelAttribute EmHrVO ehvo){
		
		logger.info("evInsert 호출 성공");
		
		ModelAndView mav = new ModelAndView();
		
		// 변수 초기화
		String evno = "";
		String emno = "";
		String emname = "";
		String deptcd = "";
		String jobcd = "";
		String evalgrade = "";
		String evalyear = "";
		String evalnote = "";
		
		// ehvo에서 getter 함수로 값 꺼내기
		evno = ehvo.getEvno();
		emno = ehvo.getEmno();
		emname = ehvo.getEmname();
		deptcd = ehvo.getDeptcd();
		jobcd = ehvo.getJobcd();
		evalgrade = ehvo.getEvalgrade();
		evalyear = ehvo.getEvalyear();
		evalnote = ehvo.getEvalnote();
		
		// VO 새로 선언
		EmHrVO ehVO = null;
		ehVO = new EmHrVO();
		
		// 채번
		List<EmHrVO> cList = emService.evChaebun(ehVO);
		logger.info("cList.size() >>> : " + cList.size());
		
		if(cList.size()==1){
			logger.info("채번 잘 돌아서 if문 진입");
			
			String _no = cList.get(0).getEvno();
			evno = Chaebun.chaebunCreateEV(_no);
			logger.info("evno >>> : " + evno);
			// setter 함수로 값 넣기  - evno
			ehVO.setEvno(evno);
		}	// end of if(cList.size()==1)
		
		// setter 함수로 값 넣기
		ehVO.setEmno(emno);
		ehVO.setEvalyear(evalyear);
		ehVO.setEmname(emname);
		ehVO.setDeptcd(deptcd);
		ehVO.setJobcd(jobcd);
		ehVO.setEvalgrade(evalgrade);
		ehVO.setEvalnote(evalnote);
		
		// insert 함수 실행
		int result = 0;
		result = emService.evInsert(ehVO);
		logger.info("result >>> : " + result);
		
		if(result == 1){
			logger.info("insert 함수 돌았음");
			mav.setViewName(CONTEXT_PATH + "/emAdminMain");
		}	// end of if(result == 1)
		
		return mav;
	}	// end of EmController.evInsert() 함수

	/**************************************************
	 * 평가등록 폼 - 사원조회팝업
	 **************************************************/
	@RequestMapping(value="/evPopup")
	public ModelAndView evSelect(@ModelAttribute EmPrInfoVO epvo){
		
		logger.info("Controller - evSelect 호출 성공");
		
		List<EmPrInfoVO> emList = emService.emAllSelect(epvo);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("emList", emList);
		mav.setViewName(CONTEXT_PATH + "/ev_pop");
		
		return mav;
	}	// end of EmController.evSelect() 함수
	
	/**************************************************
	 * 평가목록
	 **************************************************/
	@RequestMapping(value="/evAllSelect")
	public ModelAndView evAllSelect(@ModelAttribute EmHrVO ehvo){
		
		logger.info("Controller - evAllSelect 호출 성공");
		
		List<EmHrVO> evList = emService.evAllSelect(ehvo);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("evList", evList);
		mav.setViewName(CONTEXT_PATH + "/evAllSelect");
		
		return mav;
	}	// end of EmController.trAllSelect() 함수
	
	/**************************************************
	 * 평가 상세 정보
	 **************************************************/
	@RequestMapping(value="/evDetail")
	public ModelAndView evDetail(@ModelAttribute EmHrVO ehvo){
		
		logger.info("Controller - evDetail 성공");
		
		// 매개변수
		String evno = ehvo.getEvno();
		logger.info("evno >>> : " + evno);
		
		List<EmHrVO> evList = emService.evDetail(ehvo);
		int evListSize = evList.size();
		logger.info("evListSize >>> : " + evListSize);
		
		ModelAndView mav = new ModelAndView();
		if(evno.length()!=0){
			mav.addObject("evList", evList);
		}	// end of if(evno.length()!=0)
		mav.setViewName(CONTEXT_PATH + "/evDetail"); 
		return mav;
	}	// end of EmController.evDetail() 함수
}	