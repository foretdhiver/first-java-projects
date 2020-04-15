package com.spring.ge.service;

import java.util.List;

import com.spring.ge.vo.CommonVO;
import com.spring.ge.vo.EmHrVO;
import com.spring.ge.vo.EmInfoVO;
import com.spring.ge.vo.EmPrInfoVO;

public interface EmService {
	
	// 로그인
	public EmInfoVO emInfoList(EmInfoVO eVO);
	
	// 코드리스트
	public List<CommonVO> cdList(CommonVO ecvo);
	
	// ------------------------ 사원 ------------------------ 
	
	// 채번 (사번)
	public List<EmInfoVO> emChaebun(EmInfoVO evo);
	
	// 채번 (순번)
	public List<EmPrInfoVO> emPrChaebun(EmPrInfoVO epvo);
	
	// 사원 등록(EM_INFO 테이블)
	public int emInsert(EmInfoVO evo) throws Exception;
	
	// 사원등록(EM_PRINFO 테이블)
	public int emPrInsert(EmPrInfoVO epvo) throws Exception;
	
	// 사원 목록
	public List<EmPrInfoVO> emAllSelect(EmPrInfoVO epvo);
	
	// 사원 정보 조회
	public List<EmPrInfoVO> emDetail(EmPrInfoVO epvo);
	
	// 사원 정보 수정
	public int emUpdate(EmInfoVO evo) throws Exception;
	
	// 사원 개인 정보 수정
	public int emprUpdate(EmPrInfoVO epvo) throws Exception;
	
	// 사원 삭제
	public int emDelete(EmPrInfoVO epvo);
	
	// 사원 검색
	public List<EmPrInfoVO> emSearch(EmPrInfoVO epvo);
	
	// ------------------------ 교육 ------------------------ 
	
	// 채번 (교육사항)
	public List<EmHrVO> trChaebun(EmHrVO ehvo);
	
	// 교육 사항 등록
	public int trInsert(EmHrVO ehVO);

	// 교육 사항 목록
	public List<EmHrVO> trAllSelect(EmHrVO ehvo);
	
	// 교육 사항 상세 조회
	public List<EmHrVO> trDetail(EmHrVO ehvo);
		
	// 교육 사항 수정
	public int trUpdate(EmHrVO ehVO);
	
	// ------------------------ 근태 ------------------------
	
	// 채번 (근태)
	public List<EmInfoVO> ctChaebun(EmInfoVO evo);
	
	// 근태 등록
	public int ctInsert(EmPrInfoVO epVO);
	
	// 출근시간 업데이트
	public int ctIntimeUpdate(EmInfoVO evo);
	
	// 퇴근시간 업데이트
	public int ctOuttimeUpdate(EmInfoVO evo);
	
	// 출/퇴근 시간 확인
	public List<EmInfoVO> ctSelect(EmInfoVO evo);
	
	// 출근 코드 업데이트
	public int ctUpdate(EmInfoVO evo);
	
	// 퇴근 코드 업데이트
	public int ctOutUpdate(EmInfoVO evo);
	
	// 근태 목록
	public List<EmInfoVO> ctAllSelect(EmInfoVO evo);
	
	// 근태 기간 조회
	public List<EmInfoVO> ctSearch(EmInfoVO evo);
	
	// 근태 자료 로그 테이블로 이동
	public int ctToLog();
	
	// 전 사원 결근으로 업데이트
	public int ctNextDay();
	
	// 채번 (로그테이블)
	public List<EmInfoVO> logChaebun(EmInfoVO evo);
	
	// 관리자 근태 조정
	public int ctAdminUpdate(EmInfoVO eVO);
	
	// 퇴근 누르면 로그 테이블로 이동
	public int goToLog(EmInfoVO evo);
	
	// ------------------------ 마이페이지 ------------------------
	
	// 내 정보 조회
	public List<EmPrInfoVO> myPageInfo(EmPrInfoVO epvo);
	
	// 비밀번호 변경
	public int pwUpdate(EmInfoVO eVo);
	
	// 마이페이지 교육목록
	public List<EmHrVO> myPageTr(EmHrVO ehvo);
	
	// 마이페이지 교육상세
	public List<EmHrVO> myPageTrDetail(EmHrVO ehVO);
	
	// ------------------------ 평가 ------------------------
	
	// 평가 채번
	public List<EmHrVO> evChaebun(EmHrVO ehvo);
	
	// 평가 등록
	public int evInsert(EmHrVO ehVO);
	
	// 평가 목록
	public List<EmHrVO> evAllSelect(EmHrVO ehvo);
	
	// 평가 조회
	public List<EmHrVO> evDetail(EmHrVO ehvo);
	
	// 평가 수정
	public int evUpdate(EmHrVO ehvo);
	
	public String test();
	
}	// end of EmSerVice interface
