package com.spring.ge.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.ge.dao.EmDao;
import com.spring.ge.vo.CommonVO;
import com.spring.ge.vo.EmHrVO;
import com.spring.ge.vo.EmInfoVO;
import com.spring.ge.vo.EmPrInfoVO;

@Service
@Transactional
public class EmServiceImpl implements EmService {
	
	Logger logger = Logger.getLogger(EmServiceImpl.class);
	
	@Autowired
	private EmDao emDao;

	// 로그인
	@Override
	public EmInfoVO emInfoList(EmInfoVO _eVO) {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl - emInfoList(로그인) 진입");
		
		EmInfoVO eVO = null;
		eVO = new EmInfoVO();
		
		eVO = emDao.emInfoList(_eVO);
		
		logger.info("EmServiceImpl - emInfoList(로그인) 종료");
		return eVO;
	}	// end of EmServiceImpl.emInsert() 함수

	// 코드리스트
	@Override
	public List<CommonVO> cdList(CommonVO ecvo) {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl - cdList(코드리스트) 진입");
		
		List<CommonVO> list = null;
		list = emDao.cdList(ecvo);
		
		logger.info("EmServiceImpl - cdList(코드리스트) 종료");
		return list;
	}	// end of EmServiceImpl.cdList() 함수

	// 채번 (사번)
	@Override
	public List<EmInfoVO> emChaebun(EmInfoVO evo) {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl.emChaebun() 함수 시작");
		
		List<EmInfoVO> list = null;
		list = emDao.emChaebun(evo);
		
		logger.info("EmServiceImpl.emChaebun() 함수 끝");
		return list;
	}	// end of EmServiceImpl.emChaebun() 함수
	
	// 채번 (순번)
	@Override
	public List<EmPrInfoVO> emPrChaebun(EmPrInfoVO epvo) {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl.emPrChaebun() 함수 시작");
		
		List<EmPrInfoVO> list = null;
		list = emDao.emPrChaebun(epvo);
		
		logger.info("EmServiceImpl.emPrChaebun() 함수 끝");
		return list;
	}	// end of EmServiceImpl.emPrChaebun() 함수

	// 사원 등록 insert 함수(EM_INFO 테이블)
	@Override
	public int emInsert(EmInfoVO evo) throws Exception {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl - emInsert(EM_INFO 테이블) 진입");
		
		int result = 0;
		result = emDao.emInsert(evo);
		
		return result;
	}	// end of EmServiceImpl.emInsert() 함수

	// 사원등록  insert 함수(EM_PRINFO 테이블)
	@Override
	public int emPrInsert(EmPrInfoVO epvo) throws Exception {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl - emPrInsert(EM_PRINFO 테이블) 진입");
		
		int result = 0;
		result = emDao.emPrInsert(epvo);
		
		return result;
	}	// end of EmServiceImpl.emPrInsert() 함수

	// 사원목록
	@Override
	public List<EmPrInfoVO> emAllSelect(EmPrInfoVO epvo) {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl - emAllSelect 진입");
		
		List<EmPrInfoVO> asList = null;
		asList = emDao.emAllSelect(epvo);
		
		return asList;
	}	// end of EmServiceImpl.emAllSelect() 함수

	// 사원 정보 조회
	@Override
	public List<EmPrInfoVO> emDetail(EmPrInfoVO epvo) {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl.emDetail() 함수 시작");
		
		List<EmPrInfoVO> sList = null;
		sList = emDao.emDetail(epvo);
		System.out.println("(log) sList.size >>> : " + sList.size());
		
		logger.info("EmServiceImpl.emDetail() 함수 끝");
		return sList;
	}	// end of EmServiceImpl.emDetail() 함수

	// 사원 정보 수정
	@Override
	public int emUpdate(EmInfoVO evo) throws Exception  {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl.emUpdate() 함수 시작");
		
		int result = 0;
		result = emDao.emUpdate(evo);
		
		logger.info("EmServiceImpl.emUpdate() 함수 끝");
		return result;
	}	// end of EmServiceImpl.emUpdate() 함수

	// 사원 개인 정보 수정
	@Override
	public int emprUpdate(EmPrInfoVO epvo) throws Exception  {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl.emprUpdate() 함수 시작");
		
		int result = 0;
		result = emDao.emprUpdate(epvo);
		
		logger.info("EmServiceImpl.emprUpdate() 함수 끝");
		return result;
	}	// end of EmServiceImpl.emprUpdate() 함수

	// 사원 삭제
	@Override
	public int emDelete(EmPrInfoVO epvo) {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl.emDelete() 함수 시작");
		
		int result = 0;
		result = emDao.emDelete(epvo);
		
		logger.info("EmServiceImpl.emDelete() 함수 끝");
		return result;
	}	// end of EmServiceImpl.emDelete() 함수

	// 사원 검색
	@Override
	public List<EmPrInfoVO> emSearch(EmPrInfoVO epvo) {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl.emSearch() 함수 시작");
		
		List<EmPrInfoVO> scList = null;
		scList = emDao.emSearch(epvo);
		
		logger.info("EmServiceImpl.emSearch() 함수 시작");
		return scList;
	}	// end of EmServiceImpl.emSearch() 함수

	// 채번 (교육)
	@Override
	public List<EmHrVO> trChaebun(EmHrVO ehvo) {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl.trChaebun() 함수 시작");
		
		List<EmHrVO> cList = null;
		cList = emDao.trChaebun(ehvo);
		
		logger.info("EmServiceImpl.trChaebun() 함수 끝");
		return cList;
	}	// end of EmServiceImpl.trChaebun() 함수

	// 교육 사항 등록
	@Override
	public int trInsert(EmHrVO ehVO) {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl.trInsert() 함수 시작");
		
		int result = 0;
		result = emDao.trInsert(ehVO);
		
		logger.info("EmServiceImpl.trInsert() 함수 끝");
		return result;
	}	// end of EmServiceImpl.trInsert() 함수

	// 교육 사항 목록
	@Override
	public List<EmHrVO> trAllSelect(EmHrVO ehvo) {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl.trAllSelect() 함수 시작");
		
		List<EmHrVO> trList = null;
		trList = emDao.trAllSelect(ehvo);
		
		logger.info("EmServiceImpl.trAllSelect() 함수 끝");
		return trList;
	}	// end of EmServiceImpl.trAllSelect() 함수

	// 교육 사항 상세 조회
	@Override
	public List<EmHrVO> trDetail(EmHrVO ehvo) {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl.trDetail() 함수 시작");
		
		List<EmHrVO> trDList = null;
		trDList = emDao.trDetail(ehvo);
		
		logger.info("EmServiceImpl.trDetail() 함수 끝");
		return trDList;
	}	// end of EmServiceImpl.trDetail() 함수

	// 교육 사항 수정
	@Override
	public int trUpdate(EmHrVO ehVO) {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl.trUpdate() 함수 시작");
		
		int result = 0;
		result = emDao.trUpdate(ehVO);
		
		logger.info("EmServiceImpl.trUpdate() 함수 끝");
		return result;
	}	// end of EmServiceImpl.trUpdate() 함수

	// 채번 (근태)
	@Override
	public List<EmInfoVO> ctChaebun(EmInfoVO evo) {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl.ctChaebun() 함수 시작");
		
		List<EmInfoVO> list = null;
		list = emDao.ctChaebun(evo);
		
		logger.info("EmServiceImpl.ctChaebun() 함수 끝");
		return list;
	}	// end of EmServiceImpl.ctChaebun() 함수
	
	// 근태 등록
	@Override
	public int ctInsert(EmPrInfoVO epVO) {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl.ctInsert() 함수 시작");
		
		int result = 0;
		result = emDao.ctInsert(epVO);
		
		logger.info("EmServiceImpl.ctInsert() 함수 끝");
		return result;
	}	// end of EmServiceImpl.ctInsert() 함수

	// 출근 시간 업데이트
	@Override
	public int ctIntimeUpdate(EmInfoVO evo) {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl.ctIntimeUpdate() 함수 시작");
		
		int result = 0;
		result = emDao.ctIntimeUpdate(evo);
		
		logger.info("EmServiceImpl.ctIntimeUpdate() 함수 끝");
		return result;
	}	// end of EmServiceImpl.ctInUpdate() 함수
	
	// 퇴근 시간 업데이트
	@Override
	public int ctOuttimeUpdate(EmInfoVO evo) {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl.ctOuttimeUpdate() 함수 시작");
		
		int result = 0;
		result = emDao.ctOuttimeUpdate(evo);
		
		logger.info("EmServiceImpl.ctOuttimeUpdate() 함수 끝");
		return result;
	}	// end of EmServiceImpl.ctOuttimeUpdate() 함수

	// 출/퇴근 시간 확인
	@Override
	public List<EmInfoVO> ctSelect(EmInfoVO evo) {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl.ctSelect() 함수 시작");
		
		List<EmInfoVO> ctSelect = null;
		ctSelect = emDao.ctSelect(evo);
		
		logger.info("EmServiceImpl.ctSelect() 함수 끝");
		return ctSelect;
	}	// end of EmServiceImpl.ctSelect() 함수

	// 출근 코드 업데이트
	@Override
	public int ctUpdate(EmInfoVO evo) {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl.ctInUpdate() 함수 시작");
		
		int result = 0;
		result = emDao.ctUpdate(evo);
		
		logger.info("EmServiceImpl.ctUpdate() 함수 끝");
		return result;
	}

	// 퇴근 코드 업데이트
	@Override
	public int ctOutUpdate(EmInfoVO evo) {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl.ctOutUpdate() 함수 시작");
		
		int result = 0;
		result = emDao.ctOutUpdate(evo);
		
		logger.info("EmServiceImpl.ctOutUpdate() 함수 끝");
		return result;
	}

	// 근태 목록
	@Override
	public List<EmInfoVO> ctAllSelect(EmInfoVO evo) {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl.ctAllSelect() 함수 시작");
		
		List<EmInfoVO> ctList = null;
		ctList = emDao.ctAllSelect(evo);
		
		logger.info("EmServiceImpl.ctAllSelect() 함수 끝");
		return ctList;
	}	// end of EmServiceImpl.ctOuttimeUpdate() 함수

	// 근태 자료 로그 테이블로 이동
	@Override
	public int ctToLog() {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl.ctToLog() 함수 시작");
		
		int result = 0;
		result = emDao.ctToLog();
		
		logger.info("EmServiceImpl.ctToLog() 함수 끝");
		return result;
	}	// end of EmServiceImpl.ctOutUpdate() 함수

	// 전 사원 결근으로 업데이트
	@Override
	public int ctNextDay() {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl.ctNextDay() 함수 시작");
		
		int result = 0;
		result = emDao.ctNextDay();
		
		logger.info("EmServiceImpl.ctNextDay() 함수 끝");
		return result;
	}	// end of EmServiceImpl.ctNextDay() 함수

	// 채번 (로그테이블)
	@Override
	public List<EmInfoVO> logChaebun(EmInfoVO evo) {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl.logChaebun() 함수 시작");
		
		List<EmInfoVO> logList = null;
		logList = emDao.ctAllSelect(evo);
		
		logger.info("EmServiceImpl.logChaebun() 함수 끝");
		return logList;
	}	// end of EmServiceImpl.logChaebun() 함수

	// 관리자 근태 조정
	@Override
	public int ctAdminUpdate(EmInfoVO eVO) {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl.ctAdminUpdate() 함수 시작");
		
		int result = 0;
		result = emDao.ctAdminUpdate(eVO);
		
		logger.info("EmServiceImpl.ctAdminUpdate() 함수 끝");
		return result;
	}	// end of EmServiceImpl.logChaebun() 함수

	// 내 정보 조회
	@Override
	public List<EmPrInfoVO> myPageInfo(EmPrInfoVO epvo) {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl.myPageInfo() 함수 시작");
		
		List<EmPrInfoVO> myList = null;
		myList = emDao.myPageInfo(epvo);
		
		logger.info("EmServiceImpl.myPageInfo() 함수 끝");
		return myList;
	}	// end of EmServiceImpl.myPageInfo() 함수

	// 테스트~!~!~!~!~!~!~!~!~!~~!~!~!~!~~!~!~~
	@Override
	public String test() {
		// TODO Auto-generated method stub
		String aa = "EmService를 통과함";
		return aa;
	}

	// 비밀번호 변경
	@Override
	public int pwUpdate(EmInfoVO eVo) {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl.pwUpdate() 함수 시작");
		
		int result = 0;
		result = emDao.pwUpdate(eVo);
		
		logger.info("EmServiceImpl.pwUpdate() 함수 끝");
		return result;
	}	// end of EmServiceImpl.pwUpdate() 함수
	
	// 마이페이지 교육
	@Override
	public List<EmHrVO> myPageTr(EmHrVO ehvo) {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl.myPageTr() 함수 시작");
		
		List<EmHrVO> mtList = null;
		mtList = emDao.myPageTr(ehvo);
		
		logger.info("EmServiceImpl.myPageTr() 함수 끝");
		return mtList;
	}	// end of EmServiceImpl.myPageTr() 함수

	// 마이페이지 교육 상세
	@Override
	public List<EmHrVO> myPageTrDetail(EmHrVO ehVO) {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl.myPageTrDetail() 함수 시작");
		
		List<EmHrVO> mtdList = null;
		mtdList = emDao.myPageTrDetail(ehVO);
		
		logger.info("EmServiceImpl.myPageTrDetail() 함수 끝");
		return mtdList;
	}	// end of EmServiceImpl.myPageTrDetail() 함수

	// 평가 채번
	@Override
	public List<EmHrVO> evChaebun(EmHrVO ehvo) {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl.evChaebun() 함수 시작");
		
		List<EmHrVO> cList = null;
		cList = emDao.evChaebun(ehvo);
		
		logger.info("EmServiceImpl.evChaebun() 함수 끝");
		return cList;
	}	// end of EmServiceImpl.evChaebun() 함수

	// 평가 등록
	@Override
	public int evInsert(EmHrVO ehVO) {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl.evInsert() 함수 시작");
		
		int result = 0;
		result = emDao.evInsert(ehVO);
		
		logger.info("EmServiceImpl.evInsert() 함수 끝");
		return result;
	}	// end of EmServiceImpl.evInsert() 함수

	// 평가 목록
	@Override
	public List<EmHrVO> evAllSelect(EmHrVO ehvo) {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl.evAllSelect() 함수 시작");
		
		List<EmHrVO> evList = null;
		evList = emDao.evAllSelect(ehvo);
		
		logger.info("EmServiceImpl.evAllSelect() 함수 끝");
		return evList;
	}

	// 퇴근 누르면 로그 테이블로 이동
	@Override
	public int goToLog(EmInfoVO evo) {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl.goToLog() 함수 시작");
		
		int result = 0;
		result = emDao.goToLog(evo);
		
		logger.info("EmServiceImpl.goToLog() 함수 끝");
		return result;
	}

	// 평가 상세 조회
	@Override
	public List<EmHrVO> evDetail(EmHrVO ehvo) {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl.evDetail() 함수 시작");
		
		List<EmHrVO> evdList = null;
		evdList = emDao.evAllSelect(ehvo);
		
		logger.info("EmServiceImpl.evDetail() 함수 끝");
		return evdList;
	}

	// 평가 수정
	@Override
	public int evUpdate(EmHrVO ehvo) {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl.evUpdate() 함수 시작");
		
		int result = 0;
		result = emDao.evUpdate(ehvo);
		
		logger.info("EmServiceImpl.evUpdate() 함수 끝");
		return result;
	}

	
	// 근태 기간 조회
	@Override
	public List<EmInfoVO> ctSearch(EmInfoVO evo) {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl.ctSearch() 함수 시작");
		
		List<EmInfoVO> list = null;
		list = emDao.ctSearch(evo);
		
		logger.info("EmServiceImpl.ctSearch() 함수 끝");
		return list;
	}	// end of EmServiceImpl.ctSearch() 함수


}
