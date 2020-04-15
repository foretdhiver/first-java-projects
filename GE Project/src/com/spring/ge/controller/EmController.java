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
	
	// ����¡
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
	 * �α���
	 **************************************************/
	@RequestMapping(value="/loginCheck")
	public ModelAndView loginCheck(@ModelAttribute EmInfoVO evo, HttpSession httpsession){
		logger.info("loginCheck ����");
		
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
            System.out.println("���̵�� ��й�ȣ�� ��ġ�ϴ� ȸ���� �����մϴ�.");
            httpsession.setAttribute("eminfo", infoList);
            
            List<EmInfoVO> list = emService.ctSelect(infoList);
			int listSize = list.size();
			logger.info("listSize >>> : " + listSize);
			
			if(infoList.getJobcd().equals("01")){
				logger.info("������ ���� �������� �̵�");
				mav.setViewName(CONTEXT_PATH + "/emAdminMain");
			}else{
				logger.info("����� ���� �������� �̵�");
				mav.addObject("list", list);
				mav.setViewName(CONTEXT_PATH + "/emMain");
			}
         }	// end of if(eVO != null)
		return mav;
	}	// end of EmController.loginCheck() �Լ�
	
	/**************************************************
	 * ������ �� ����ϱ�
	 **************************************************/
	@RequestMapping(value="/insertForm", method=RequestMethod.GET)
	public ModelAndView insertForm(@ModelAttribute CommonVO ecvo, @ModelAttribute EmInfoVO evo){
		
		logger.info("insertForm ȣ�� ����");
//		EmCdVO.cdPrint(ecvo);
		
		List<CommonVO> cdList = emService.cdList(ecvo);
		System.out.println("(log) cdList.size() >>> : " + cdList.size());
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("cdList", cdList);
		mav.addObject("ecvo", ecvo);
		mav.setViewName(CONTEXT_PATH + "/emInsert");
		
		return mav;
	}	// end of EmController.insertForm() �Լ�
	
	/**************************************************
	 * ������ ����
	 **************************************************/
	@RequestMapping(value="/emInsert", method=RequestMethod.POST)
	public ModelAndView emInsert(@ModelAttribute EmInfoVO evo, EmPrInfoVO epvo, HttpServletRequest request){
		
		logger.info("emInsert ȣ�� ����");
		
		ModelAndView mav = new ModelAndView();
		
		// ����¡ �κ�
		pageSize = 10;
		String cp = request.getParameter("curPage");
		if(cp!=null){
			curPage = Integer.parseInt(cp);
		}else{
			curPage = 1;
		}	// end of if(cp!=null)
		logger.info("curPage >>> : " + curPage);
		
		// ���� ���
		String uploadPath = FilePath.UPLOAD_FILE_PATH;
		String downloadPath = FilePath.DOWNLOAD_FILE_PATH;
		
		// FileUploadUtil Class
		EmFileUploadUtil fu = new EmFileUploadUtil();
		boolean bFlag = false;
		bFlag = fu.fileUpload(request, uploadPath);
		logger.info("bFlag >>> : " + bFlag);
		
		if(bFlag){
			logger.info("bFlag�� true����  if�� ����");
			
			Enumeration<String> en = fu.getFileNames();
			
			String emsign = en.nextElement();	// ����
			String empic = en.nextElement();	// ����
			logger.info("firstFile / secondFile >>> : " + emsign + " / " + empic);
			
			// EM_INFO ���̺� �� ���ڵ�
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
			
			// EM_PRINFO ���̺� �� ���ڵ�
			String emprno = fu.getParameter("emprno");
			// EM_PRINFO���� emno�� ������ ������ �޾ƿͼ� �ּ� ó��
//			String emno = fu.getParameter("emno");
			String emprsex = fu.getParameter("emprsex");
			String emprbirth = fu.getParameter("emprbirth");
			String emprmarital = fu.getParameter("emprmarital");
			String emprpostno = fu.getParameter("emprpostno");
			String empradd = fu.getParameter("empradd");
			String emprdetailadd = fu.getParameter("emprdetailadd");
			String empreducd = fu.getParameter("empreducd");
			String empreduname = fu.getParameter("empreduname");
			
			// ä��
			List<EmInfoVO> ceList = emService.emChaebun(evo);
			List<EmPrInfoVO> cepList = emService.emPrChaebun(epvo);
			logger.info("ceList.size() / cepList.size() >>> : " + ceList.size() + " / " + cepList.size());
			
			// ä�� ���Ҵ��� ���� Ȯ��
			if(ceList.size()==1 && cepList.size()==1){
				logger.info("ä�� �� ���Ƽ� if�� ����");
				// emno ä��
				String _no = ceList.get(0).getEmno();
				emno = Chaebun.chaebunCreateE(_no);
				evo.setEmno(emno);
				logger.info("��� >>> : " + evo.getEmno());
				
				// emprno ä��
				String no = cepList.get(0).getEmprno();
				emprno = Chaebun.chaebunCreateEP(no);
				epvo.setEmprno(emprno);
				logger.info("���� >>> : " + epvo.getEmprno());
			}	// end of if(cList.size()==1)
			
			// EM_INFO ���̺��� VO�� ������ �ֱ�
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
			
			// EM_PRINFO ���̺��� VO�� ������ �ֱ�
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
			
			// insert �Լ� ����
			int emresult = 0;
			int emprresult = 0;
			
			// Ʈ����� ó��
			
			dtd = new DefaultTransactionDefinition();
			dtd.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			ts= ptm.getTransaction(dtd);
			try{
				emresult = emService.emInsert(evo);
				emprresult = emService.emPrInsert(epvo);
				logger.info("emresult / emprresult >>> : " + emresult + " / " + emprresult);
				
				ptm.commit(ts);
			}catch(Exception e){
				logger.info("��ϰ������� ���� �߻��Ͽ� �ѹ�");
				ptm.rollback(ts);
				
//				epvo.setEmno("");
//				List<EmPrInfoVO> list = emService.emAllSelect(epvo);
//				
//				mav.addObject("emList", list);
//				mav.setViewName(CONTEXT_PATH + "/emAllSelect");
			}
			
			if(emresult == 1 && emprresult == 1){
				System.out.println("(log) insert�Լ� �� ����Ǹ� if�� ����");
				
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
		
		
		System.out.println("(log) EmController.emInsert() �Լ� ��");
	
		return mav;
	}	// end of EmController.emInsert() �Լ�

	/**************************************************
	 * ������ ���
	 **************************************************/
	@RequestMapping(value="/emAllSelect")
	public ModelAndView emAllSelect(@ModelAttribute EmPrInfoVO epvo, HttpServletRequest request){
		logger.info("Controller - emAllSelect ȣ�� ����");
		
		ModelAndView mav = new ModelAndView();
		
		// ����¡ �κ�
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
		
		System.out.println("(log) EmController.emAllSelect() �Լ� ��");
		return mav;
	}	// end of EmController.emAllSelect() �Լ�

	/**************************************************
	 * ��� �� ����
	 **************************************************/
	@RequestMapping(value="/emDetail")
	public ModelAndView emDetail(@ModelAttribute EmPrInfoVO epvo, CommonVO ecvo){

		logger.info("Controller - emDetail ����");
		
		// �Ű����� ���
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
			System.out.println("(log) emno �־ if�� ����");
			
			mav.addObject("cdList", cdList);
			mav.addObject("sList", sList);
		}	// end of if(emno != null)
		mav.setViewName(CONTEXT_PATH + "/emDetail");
		
		return mav;
	}	// end of EmController.emDetail() �Լ�

	/**************************************************
	 * ��� �� ���� ����
	 **************************************************/
	@RequestMapping(value="/emUpdate", method=RequestMethod.POST)
	public ModelAndView emUpdatest(@ModelAttribute EmPrInfoVO epvo, EmInfoVO evo, HttpServletRequest request){
		
		logger.info("emUpdate ȣ�� ����");
		
		ModelAndView mav = new ModelAndView();
		
		// ���� ���
		String uploadPath = FilePath.UPLOAD_FILE_PATH;
		String downloadPath = FilePath.DOWNLOAD_FILE_PATH;
		
		// FileUploadUtil class
		EmFileUploadUtil fu = new EmFileUploadUtil();
		boolean bFlag = false;
		bFlag = fu.fileUpload(request, uploadPath);
		logger.info("bFlag >>> : " + bFlag);
		
		if(bFlag){
			logger.info("bFlag true���� ����");
			
			Enumeration<String> en = fu.getFileNames();
			
			String empic = en.nextElement();	// ����
			String emsign = en.nextElement();	// ����
			logger.info("firstFile / secondFile >>> : " + empic + " / " + emsign);
			
			if(empic != null && emsign == null){
				System.out.println("(log) ������ �ٲ�� ������ ����");
				evo.setEmpic(downloadPath + empic);
				evo.setEmsign("");
				System.out.println("(log) epvo.getEmsign() >>> : " + evo.getEmsign());
				System.out.println("(log) epvo.getEmpic() >>> : " + evo.getEmpic());
		
			}else if(empic == null && emsign != null){
				System.out.println("(log) ���� �����ϰ� ���� �ٲ�");
				evo.setEmpic("");
				evo.setEmsign(downloadPath + emsign);
				System.out.println("(log) evo.getEmsign() >>> : " + evo.getEmsign());
				System.out.println("(log) evo.getEmpic() >>> : " + evo.getEmpic());
		
			}else if(empic == null && emsign == null){
				System.out.println("(log) ���� ���� ���� ����");
				evo.setEmsign("");
				evo.setEmpic("");
				System.out.println("(log) evo.getEmsign() >>> : " + evo.getEmsign());
				System.out.println("(log) evo.getEmpic() >>> : " + evo.getEmpic());
			}
			if(empic != null && emsign != null){
				System.out.println("(log) ���� ���� & ���� ����");
				epvo.setEmpic(downloadPath + empic);
				evo.setEmsign(downloadPath + emsign);
				System.out.println("(log) evo.getEmsign() >>> : " + evo.getEmsign());
				System.out.println("(log) evo.getErpic() >>> : " + evo.getEmpic());
			}// end of if
			
			// EM_INFO ���̺� �� ���ڵ�
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
						
			// EM_PRINFO ���̺� �� ���ڵ�
			String emprno = fu.getParameter("emprno");
			// EM_PRINFO���� emno�� ������ ������ �޾ƿͼ� �ּ� ó��
	//		String emno = mr.getParameter("emno");
			String emprsex = fu.getParameter("emprsex");
			String emprbirth = fu.getParameter("emprbirth");
			String emprmarital = fu.getParameter("emprmarital");
			String emprpostno = fu.getParameter("emprpostno");
			String empradd = fu.getParameter("empradd");
			String emprdetailadd = fu.getParameter("emprdetailadd");
			String empreducd = fu.getParameter("empreducd");
			String empreduname = fu.getParameter("empreduname");
			
			// EM_INFO ���̺� set
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
						
			// EM_PRINFO ���̺� set
			epvo.setEmno(emno);
			epvo.setEmprsex(emprsex);
			epvo.setEmprbirth(emprbirth);
			epvo.setEmprmarital(emprmarital);
			epvo.setEmprpostno(emprpostno);
			epvo.setEmpradd(empradd);
			epvo.setEmprdetailadd(emprdetailadd);
			epvo.setEmpreducd(empreducd);
			epvo.setEmpreduname(empreduname);
			
			// update �Լ� ����
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
				
				logger.info("commit �Ϸ�");
			}catch(Exception e){
				ptm.rollback(ts);
				
				logger.info("rollback �Ϸ�");
				
				epvo.setEmno("");
				List<EmPrInfoVO> list = emService.emAllSelect(epvo);
				
				mav.addObject("emList", list);
				mav.setViewName(CONTEXT_PATH + "/emAllSelect");
			}
			
			if(emresult == 1 && emprresult == 1){
				logger.info("update �Լ� �� ���� �Ǿ���");
				
				epvo.setEmno("");
				List<EmPrInfoVO> list = emService.emAllSelect(epvo);
				
				mav.addObject("emList", list);
				mav.setViewName(CONTEXT_PATH + "/emAllSelect");
			}	// end of if(result == 1)
		}else{
			System.out.println("���� ��������");
		}	// end of if(bFlag)
		
		System.out.println("(log) EmController.emUpdate() �Լ� ��");
		return mav;
		
	}

	/**************************************************
	 * ��� ����
	 **************************************************/
	@RequestMapping(value="/emDelete")
	public ModelAndView emDelete(@ModelAttribute EmPrInfoVO epvo, EmInfoVO evo, HttpServletRequest request){
		logger.info("Controller - emDelete ȣ�� ����");
		
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
			logger.info("delete �Լ� �۵�����");
			
			epvo.setEmno("");
			List<EmPrInfoVO> list = emService.emAllSelect(epvo);
			
			mav.addObject("emList", list);
			mav.setViewName(CONTEXT_PATH + "/emAllSelect");
		}
		return mav;
	}	// end of EmController.emDelete() �Լ�

	/**************************************************
	 * ��� �˻�
	 **************************************************/
	@RequestMapping(value="/emSearch")
	public ModelAndView emSearch(@ModelAttribute EmPrInfoVO epvo, HttpServletRequest request){
		
		logger.info("Controller - emSearch ����");
		
		ModelAndView mav = new ModelAndView();
		
		String search = "";
		String keyword = "";
		
		search = request.getParameter("search");
		keyword = request.getParameter("keyword");
		
		// �˻��� ���� ������ Ȯ��
		logger.info("search >>> : " + search);
		logger.info("keyword >>> : " + keyword);
		
		// ����¡ �κ�
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
	}	// end of EmController.emSearch() �Լ�

	/**************************************************
	 * �������׵�� �� ����ϱ�
	 **************************************************/
	@RequestMapping(value="/trInsertForm")
	public ModelAndView trInsertForm(@ModelAttribute EmInfoVO evo){
		
		logger.info("trInsertForm ȣ�� ����");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName(CONTEXT_PATH + "/trInsert");
		
		return mav;
	}	// end of EmController.trInsertForm() �Լ�
	
	/**************************************************
	 * �������׵��
	 **************************************************/
	@RequestMapping(value="/trInsert", method=RequestMethod.GET)
	public ModelAndView trInsert(@ModelAttribute EmHrVO ehvo){
		
		logger.info("trInsert ȣ�� ����");
		
		ModelAndView mav = new ModelAndView();
		
		// ���� �ʱ�ȭ
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
		
		// ehvo���� getter �Լ��� �� ������
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
		
		// VO ���� ����
		EmHrVO ehVO = null;
		ehVO = new EmHrVO();
		
		// ä��
		List<EmHrVO> cList = emService.trChaebun(ehvo);
		logger.info("cList.size() >>> : " + cList.size());
		
		if(cList.size()==1){
			logger.info("ä�� �� ���Ƽ� if�� ����");
			
			String _no = cList.get(0).getTrno();
			trno = Chaebun.chaebunCreateTR(_no);
			logger.info("trno >>> : " + trno);
			ehVO.setTrno(trno);
		}	// end of if(cList.size()==1)
		
		// setter �Լ��� �� �ֱ�
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
		
		// insert �Լ� ����
		int result = 0;
		result = emService.trInsert(ehVO);
		logger.info("result >>> : " + result);
		
		if(result == 1){
			logger.info("insert �Լ� ������");
			mav.setViewName(CONTEXT_PATH + "/emAdminMain");
		}	// end of if(result == 1)
		
		return mav;
	}	// end of EmController.trInsert() �Լ�

	/**************************************************
	 * �������׵�� �� - �����ȸ�˾�
	 **************************************************/
	@RequestMapping(value="/trPopup")
	public ModelAndView trSelect(@ModelAttribute EmPrInfoVO epvo){
		
		logger.info("Controller - trPopup ȣ�� ����");
		
		List<EmPrInfoVO> list = emService.emAllSelect(epvo);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("emList", list);
		mav.setViewName(CONTEXT_PATH + "/tr_pop");
		
		return mav;
	}	// end of EmController.trSelect() �Լ�
	
	/**************************************************
	 * �������׸��
	 **************************************************/
	@RequestMapping(value="/trAllSelect")
	public ModelAndView trAllSelect(@ModelAttribute EmHrVO ehvo){
		
		logger.info("Controller - trAllSelect ȣ�� ����");
		
		List<EmHrVO> trList = emService.trAllSelect(ehvo);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("trList", trList);
		mav.setViewName(CONTEXT_PATH + "/trAllSelect");
		
		return mav;
	}	// end of EmController.trAllSelect() �Լ�
	
	/**************************************************
	 * �������� �� ��ȸ
	 **************************************************/
	@RequestMapping(value="/trDetail")
	public ModelAndView trDetail(@ModelAttribute EmHrVO ehvo){
		
		logger.info("Controller - trDetail ȣ�� ����");
		
		// �Ű����� ���
		String trno = ehvo.getTrno();
		logger.info("trno >>> : " + trno);
		
		List<EmHrVO> trDList = emService.trDetail(ehvo);
		int trDListSize = trDList.size();
		logger.info("trDListSize >>> : " + trDListSize);
		
		ModelAndView mav = new ModelAndView();
		if(trno != null){
			logger.info("trno �־ if�� ����");
			mav.addObject("trDList", trDList);
			mav.setViewName(CONTEXT_PATH + "/trDetail");
		}	// end of if(trno != null)
		
		return mav;
	}	// end of EmController.trAllSelect() �Լ�

	/**************************************************
	 * ���� ���� ����
	 **************************************************/
	@RequestMapping(value="/trUpdate", method=RequestMethod.GET)
	public ModelAndView trUpdate(@ModelAttribute EmHrVO ehvo){
		
		logger.info("Controller - trUpdate ȣ�� ����");
		
		ModelAndView mav = new ModelAndView();
		
		// ���� �ʱ�ȭ
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
		
		// ehvo���� getter �Լ��� �� ������
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
		
		// VO ���� ����
		EmHrVO ehVO = null;
		ehVO = new EmHrVO();
		
		// setter �Լ��� �� �ֱ�
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
		
		// update �Լ� ����
		int result = 0;
		result = emService.trUpdate(ehVO);
		logger.info("result >>> : " + result);
		
		if(result == 1){
			logger.info("update �Լ� ������");
			mav.setViewName(CONTEXT_PATH + "/emAdminMain");
		}	// end of if(result == 1)
		
		return mav;
	}	// end of EmController.trUpdate() �Լ�

	/**************************************************
	 * ���� - �ο� �߰� �μ�Ʈ
	 **************************************************/
	@RequestMapping(value="/ctInsert", method=RequestMethod.POST)
	public ModelAndView ctInsert(@ModelAttribute EmPrInfoVO epvo, HttpServletRequest request){
		
		logger.info("Controller - ctInsert ȣ�� ����");
		
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
		
		// ���� ���
		String uploadPath = FilePath.UPLOAD_FILE_PATH;
		
		// FileUploadUtil class
		EmFileUploadUtil fu = new EmFileUploadUtil();
		boolean bFlag = false;
		bFlag = fu.fileUpload(request, uploadPath);
		logger.info("bFlag >>> : " + bFlag);
		
		if(bFlag){
			logger.info("bFlag true���� ����");
			
			Enumeration<String> en = fu.getFileNames();
			String emname = fu.getParameter("emname");		// �̸�
			String deptcd = fu.getParameter("deptcd");		// 
			String jobcd = fu.getParameter("jobcd");		// 
			String emno = fu.getParameter("emno");		// 
			logger.info(emname + "/" + deptcd + "/" + jobcd + "/" + emno);
			// ����
			String ctno = "";		// ���� ���̺� PK ä��
			String ctincd = "58";	// ��� �ڵ� : 58(���)
			String ctoutcd = "58";	// ��� �ڵ� : 58 (���)
			String ctnote = "";		// ���� NULL ����
			
			// VO ���� ����
			EmPrInfoVO epVO = null;
			epVO = new EmPrInfoVO();
			
			// ctno ä��
			List<EmInfoVO> cList = emService.ctChaebun(epvo);
			logger.info("cList.size() >>> : " + cList.size());
			
			// ä�� ���Ҵ��� Ȯ��
			if(cList.size()>0){
				logger.info("ä�� �� ���Ƽ� if�� ����");
				String _no = cList.get(0).getCtno();
				ctno = Chaebun.chaebunCreateCT(_no);
				logger.info("ctno >>> : " + ctno);
				epVO.setCtno(ctno);
			}	// end of if(cList.size()>1)
			
			// ���� ����
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
//		System.out.println("������.. >>> : " + emname + " / " + deptcd + " / " + jobcd + " / " + emno);
		
		return mav;
	}	// end of EmController.ctInsert() �Լ�

	/**************************************************
	 * ���� - ���
	 **************************************************/
	@RequestMapping(value="/ctInUpdate")
	public String ctInUpdate(@ModelAttribute EmInfoVO param, HttpSession httpsession){
		logger.info("Controller - ctInUpdate ȣ�� ����");
		
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
			// ��/��� �ð� ��ȸ
			List<EmInfoVO> ctList = emService.ctSelect(param);
			int ctListSize = ctList.size();
			logger.info("ctListSize >>> : " + ctListSize);
			
			// �ð� / �� �ڸ���
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
						logger.info("�������");
						ctincd="51";
						param.setCtincd(ctincd);
						cdResult = emService.ctUpdate(param);
					}	// end of if(hh <= 9 && mm <= 0)
						
					if((hh == 9 && mm > 0) || hh > 9){
						logger.info("����");
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
	}	// end of EmController.ctInUpdate() �Լ�
	
	/**************************************************
	 * ���� - ���
	 **************************************************/
	@RequestMapping(value="/ctOutUpdate")
	public String ctOutUpdate(@ModelAttribute EmInfoVO evo, HttpSession httpsession){
		
		logger.info("Controller - ctInUpdate ȣ�� ����");
		
		String url="";
		
		ModelAndView mav = new ModelAndView();
		
		String emno = evo.getEmno();
		logger.info("emno >>> : " + emno);
		
		int result = 0;
		result = emService.ctOuttimeUpdate(evo);
		logger.info("result >>> : " + result);
		
		if(result == 1){
			// ��/��� �ð� ��ȸ
			List<EmInfoVO> list = emService.ctSelect(evo);
			int listSize = list.size();
			logger.info("listSize >>> : " + listSize);
			
			// �ð� / �� �ڸ���
			if(listSize > 0){
				for(int i=0; i<listSize; i++){
					EmInfoVO evo1 = (EmInfoVO)list.get(i);
					
					// �ð�, �� �ڸ���
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
						logger.info("����");
						ctoutcd="59";
						evo.setCtoutcd(ctoutcd);
						cdResult = emService.ctOutUpdate(evo);
					}	// end of if(hh < 18)
					if(hh==18 && mm < 30){
						logger.info("�������");
						ctoutcd="56";
						evo.setCtoutcd(ctoutcd);
						cdResult = emService.ctOutUpdate(evo);
					}	// end of if(hh==18 && mm < 30)
					if((hh==18 && mm>=30) || (hh>18)){
						logger.info("����ٹ�");
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
	}	// end of EmController.ctOutUpdate() �Լ�
	
	/**************************************************
	 * ���� �̵� �Լ�
	 **************************************************/
	@RequestMapping(value="/goToMain")
	public ModelAndView goToMain(HttpSession httpsession, HttpServletRequest request){
		logger.info("���� ������ �̵�");
		
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
	    
	    // ���ڰ���
	    String ea_stepno = "";
	     
	    logger.info("emno : " + emno);
	    EaVO evo = new EaVO();
	    evo.setEa_empno(emno);
	    evo.setEmno(emno);
	    
	    /******************************************************************/
		// ���ڰ��� ���ο��� ������ �� �͵�
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
	   
	    /******************************************************************/
	    // �Խ���
	    //�μ��� �Խ���
	    BDVO check = null;
		check = new BDVO();
		
		check.setDeptcd(eVO.getDeptcd());
		List<BDVO> deptList = bDService.boardDeptMainList(check);
		
		//�������� �Խ���
		BNVO check1 = null;
		check1 = new BNVO();
		
		List<BNVO> noticeList = bNService.boardNoticeMainList(check1);
		/******************************************************************/
		// ����� ��ȸ
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
	 * ���� ���
	 **************************************************/
	@RequestMapping(value="/ctAllSelect")
	public ModelAndView ctAllSelect(@ModelAttribute EmInfoVO evo, HttpServletRequest request){
		
		logger.info("Controller - ctAllSelect ȣ�� ����");
		
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
	}	// end of EmController.ctAllSelect() �Լ�
	
	/**************************************************
	 * ���� �Ⱓ ��ȸ
	 **************************************************/
	@RequestMapping(value="/ctSearch")
	public ModelAndView ctSearch(@ModelAttribute EmInfoVO evo, HttpServletRequest request){
		
		logger.info("Controller - ctSearch ����");
		
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
		
		// ��¥���� - ���ֹ�����
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
	}	// end of EmController.ctSearch() �Լ�

	/**************************************************
	 * ���� �ڷ� �α� ���̺�� �̵� && �� ��� ������� �ڵ� ������Ʈ
	 **************************************************/
	@RequestMapping(value="/ctChange")
	public ModelAndView ctChange(@ModelAttribute EmInfoVO evo){
		
		logger.info("Controller - ctChange ȣ�� ����");
		
		ModelAndView mav = new ModelAndView();
		
		// ���� ����
		int logResult = 0;
		int nextResult = 0;
		
		// Ʈ����� ó��
		dtd = new DefaultTransactionDefinition();
		dtd.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		ts= ptm.getTransaction(dtd);
		
		try{
			// 1) �α����̺� insert
			// ä��
//			List<EmInfoVO> cList = emService.logChaebun(evo);
//			logger.info("cList.size() >>> : " + cList.size());
//			
//			if(cList.size()>0){
//				logger.info("ä�� �� ���Ƽ� if�� ����");
//				
//				String _no = cList.get(0).getLogno();
//				String logno = Chaebun.logChaebunFormat(_no);
//				logger.info("logno >>> : " + logno);
//				evo.setLogno(logno);
//			}	// end of if(cList.size()==1)
//			
			logResult = emService.ctToLog();
//			
			// 2) �� ��� ������� �ڵ� ������Ʈ
			String ctincd="58";
			String ctoutcd="58";
			evo.setCtincd(ctincd);
			evo.setCtoutcd(ctoutcd);
			
			nextResult = emService.ctNextDay();
			
			logger.info("logResult / nextResult >>> : " + logResult + " / " + nextResult);
			
			ptm.commit(ts);
			
			logger.info("commit �Ϸ�");
		}catch(Exception e){
			ptm.rollback(ts);
			
			logger.info("rollback �Ϸ�");
			
			mav.setViewName(CONTEXT_PATH + "/ctAllSelect");
		}	// end of try-catch
		
		// logResult > 0 && 
		if(nextResult > 1){
			logger.info("�α� ���̺�� insert �Ϸ� & ������� update �Ϸ�");
			
			mav.setViewName(CONTEXT_PATH + "/emAdminMain");
		}	// end of if(logResult == 1 && nextResult == 1)
		
		return mav;
	}	// end of EmController.ctChange() �Լ�

	/**************************************************
	 * ���� - ������ ����
	 **************************************************/
	@RequestMapping(value="/ctAdminUpdate")
	public ModelAndView ctAdminUpdate(@ModelAttribute EmInfoVO evo, HttpSession httpsession, HttpServletRequest request){
		
		logger.info("Controller - ctInUpdate ȣ�� ����");
		
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
			logger.info("������Ʈ �����ؼ� if�� ����");
			
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
	}	// end of EmController.ctAdminUpdate() �Լ�

	/**************************************************
	 * ���� ��ȸ �� - ���� �� ��ȸ �˾�
	 **************************************************/
	@RequestMapping(value="/ctSelect")
	public ModelAndView ctSelect(@ModelAttribute EmInfoVO evo){
		
		logger.info("Controller - ctSelect ȣ�� ����");
		
		logger.info("emno >>> : " + evo.getEmno());
		List<EmInfoVO> list = emService.ctSelect(evo);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("ctList", list);
		mav.setViewName(CONTEXT_PATH + "/ctSelect");
		
		return mav;
	}	// end of EmController.ctSelect() �Լ�

	/**************************************************
	 * ����������
	 **************************************************/
	@RequestMapping(value="/myPageInfo")
	public ModelAndView myPageInfo(HttpSession httpsession){
		
		logger.info("Controller - myPageInfo ����");
		
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
	}	// end of EmController.myPageInfo() �Լ�

	/**************************************************
	 * ���� �ð��Ǹ� ������Ʈ �ǵ���
	 **************************************************/
	@RequestMapping(value="/autoTask")
	public ModelAndView autoTask(){
		logger.info("Controller - autoTask ȣ�� ����");
		
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
		
	}	// end of EmController.autoTask() �Լ�
	
	class TimerTaskTest extends TimerTask{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
			// ����
			int nextResult = 0;
			
			// 1) �� ��� ������� �ڵ� ������Ʈ
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
	 * ��� ������ �α� ���̺�� �̵�999999999999999999999999999999999999999999999999999999999999
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
	}	// end of EmController.goToLog() �Լ�
	
	/**************************************************
	 * ��й�ȣ ����
	 **************************************************/
	@RequestMapping(value="/pwUpdate")
	public ModelAndView pwUpdate(@ModelAttribute EmInfoVO evo, HttpSession httpsession){
		logger.info("Controller - pwUpdate ȣ�� ����");
		
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
	}	// end of EmController.pwUpdate() �Լ�
	
	/**************************************************
	 * ���������� - ���� ���
	 **************************************************/
	@RequestMapping(value="/myPageTr")
	public ModelAndView myPageTr(HttpSession httpsession){
		
		logger.info("Controller - myPageTr ȣ�� ����");
		
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
	}	// end of EmController.myPageTr() �Լ�
	
	/**************************************************
	 * ���������� - ���� ���
	 **************************************************/
	@RequestMapping(value="/myPageTrDetail")
	public ModelAndView myPageTr(@ModelAttribute EmHrVO ehvo, HttpSession httpsession){
		
		logger.info("Controller - myPageTrDetail ȣ�� ����");
		
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
	}	// end of EmController.myPageTrDetail() �Լ�

	/**************************************************
	 * �� ��� �� ����ϱ�
	 **************************************************/
	@RequestMapping(value="/evInsertForm")
	public ModelAndView evInsertForm(@ModelAttribute EmHrVO ehvo){
		
		logger.info("evInsertForm ȣ�� ����");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName(CONTEXT_PATH + "/evInsert");
		
		return mav;
	}	// end of EmController.evInsertForm() �Լ�
	
	/**************************************************
	 * �� ���
	 **************************************************/
	@RequestMapping(value="/evInsert", method=RequestMethod.GET)
	public ModelAndView evInsert(@ModelAttribute EmHrVO ehvo){
		
		logger.info("evInsert ȣ�� ����");
		
		ModelAndView mav = new ModelAndView();
		
		// ���� �ʱ�ȭ
		String evno = "";
		String emno = "";
		String emname = "";
		String deptcd = "";
		String jobcd = "";
		String evalgrade = "";
		String evalyear = "";
		String evalnote = "";
		
		// ehvo���� getter �Լ��� �� ������
		evno = ehvo.getEvno();
		emno = ehvo.getEmno();
		emname = ehvo.getEmname();
		deptcd = ehvo.getDeptcd();
		jobcd = ehvo.getJobcd();
		evalgrade = ehvo.getEvalgrade();
		evalyear = ehvo.getEvalyear();
		evalnote = ehvo.getEvalnote();
		
		// VO ���� ����
		EmHrVO ehVO = null;
		ehVO = new EmHrVO();
		
		// ä��
		List<EmHrVO> cList = emService.evChaebun(ehVO);
		logger.info("cList.size() >>> : " + cList.size());
		
		if(cList.size()==1){
			logger.info("ä�� �� ���Ƽ� if�� ����");
			
			String _no = cList.get(0).getEvno();
			evno = Chaebun.chaebunCreateEV(_no);
			logger.info("evno >>> : " + evno);
			// setter �Լ��� �� �ֱ�  - evno
			ehVO.setEvno(evno);
		}	// end of if(cList.size()==1)
		
		// setter �Լ��� �� �ֱ�
		ehVO.setEmno(emno);
		ehVO.setEvalyear(evalyear);
		ehVO.setEmname(emname);
		ehVO.setDeptcd(deptcd);
		ehVO.setJobcd(jobcd);
		ehVO.setEvalgrade(evalgrade);
		ehVO.setEvalnote(evalnote);
		
		// insert �Լ� ����
		int result = 0;
		result = emService.evInsert(ehVO);
		logger.info("result >>> : " + result);
		
		if(result == 1){
			logger.info("insert �Լ� ������");
			mav.setViewName(CONTEXT_PATH + "/emAdminMain");
		}	// end of if(result == 1)
		
		return mav;
	}	// end of EmController.evInsert() �Լ�

	/**************************************************
	 * �򰡵�� �� - �����ȸ�˾�
	 **************************************************/
	@RequestMapping(value="/evPopup")
	public ModelAndView evSelect(@ModelAttribute EmPrInfoVO epvo){
		
		logger.info("Controller - evSelect ȣ�� ����");
		
		List<EmPrInfoVO> emList = emService.emAllSelect(epvo);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("emList", emList);
		mav.setViewName(CONTEXT_PATH + "/ev_pop");
		
		return mav;
	}	// end of EmController.evSelect() �Լ�
	
	/**************************************************
	 * �򰡸��
	 **************************************************/
	@RequestMapping(value="/evAllSelect")
	public ModelAndView evAllSelect(@ModelAttribute EmHrVO ehvo){
		
		logger.info("Controller - evAllSelect ȣ�� ����");
		
		List<EmHrVO> evList = emService.evAllSelect(ehvo);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("evList", evList);
		mav.setViewName(CONTEXT_PATH + "/evAllSelect");
		
		return mav;
	}	// end of EmController.trAllSelect() �Լ�
	
	/**************************************************
	 * �� �� ����
	 **************************************************/
	@RequestMapping(value="/evDetail")
	public ModelAndView evDetail(@ModelAttribute EmHrVO ehvo){
		
		logger.info("Controller - evDetail ����");
		
		// �Ű�����
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
	}	// end of EmController.evDetail() �Լ�
}	