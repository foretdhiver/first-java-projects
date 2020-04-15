package com.spring.ge.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.ge.service.EmServiceImpl;
import com.spring.ge.vo.CommonVO;
import com.spring.ge.vo.EmHrVO;
import com.spring.ge.vo.EmInfoVO;
import com.spring.ge.vo.EmPrInfoVO;

@Repository
public class EmDaoImpl implements EmDao {
	
	Logger logger = Logger.getLogger(EmDaoImpl.class);
	@Autowired
	private SqlSession session;

	// 로그인
	@Override
	public EmInfoVO emInfoList(EmInfoVO eVO) {
		// TODO Auto-generated method stub
		return session.selectOne("emInfoList", eVO);
	}	// end of EmDaoImpl.emInfoList() 함수

	// 코드리스트
	@Override
	public List<CommonVO> cdList(CommonVO ecvo) {
		// TODO Auto-generated method stub
		return session.selectList("cdList", ecvo);
	}	// end of EmDaoImpl.cdList() 함수

	// 채번 (사번)
	@Override
	public List<EmInfoVO> emChaebun(EmInfoVO evo) {
		// TODO Auto-generated method stub
		return session.selectList("emChaebun", evo);
	}	// end of EmDaoImpl.emChaebun() 함수

	// 채번 (순번)
	@Override
	public List<EmPrInfoVO> emPrChaebun(EmPrInfoVO epvo) {
		// TODO Auto-generated method stub
		return session.selectList("emPrChaebun", epvo);
	}	// end of EmDaoImpl.emPrChaebun() 함수

	// 사원 등록 (EM_INFO)
	@Override
	public int emInsert(EmInfoVO evo) throws Exception {
		// TODO Auto-generated method stub
		return session.insert("emInsert");
	}

	// 사원등록 (EM_PRINFO)
	@Override
	public int emPrInsert(EmPrInfoVO epvo) throws Exception {
		// TODO Auto-generated method stub
		return session.insert("emPrInsert");
	}

	// 사원목록
	@Override
	public List<EmPrInfoVO> emAllSelect(EmPrInfoVO epvo) {
		// TODO Auto-generated method stub
		System.out.println("dao emAllSelect 진입");
		return session.selectList("emAllSelect", epvo);
	}

	// 사원 정보 조회
	@Override
	public List<EmPrInfoVO> emDetail(EmPrInfoVO epvo) {
		// TODO Auto-generated method stub
		logger.info("aaa");
		return session.selectList("emDetail", epvo);
	}

	// 사원 정보 수정
	@Override
	public int emUpdate(EmInfoVO evo) throws Exception  {
		// TODO Auto-generated method stub
		logger.info(evo.getEmdino());
		System.out.println(evo.getEmdino());
		return session.update("emUpdate", evo);
	}

	// 사원 개인정보 수정
	@Override
	public int emprUpdate(EmPrInfoVO epvo) throws Exception  {
		// TODO Auto-generated method stub
		return session.update("emprUpdate", epvo);
	}

	// 사원 삭제
	@Override
	public int emDelete(EmPrInfoVO epvo) {
		// TODO Auto-generated method stub
		return session.update("emDelete", epvo);
	}

	// 사원 검색
	@Override
	public List<EmPrInfoVO> emSearch(EmPrInfoVO epvo) {
		// TODO Auto-generated method stub
		return session.selectList("emSearch", epvo);
	}

	// 채번 (교육)
	@Override
	public List<EmHrVO> trChaebun(EmHrVO ehvo) {
		// TODO Auto-generated method stub
		return session.selectList("trChaebun", ehvo);
	}

	// 교육 사항 등록
	@Override
	public int trInsert(EmHrVO ehVO) {
		// TODO Auto-generated method stub
		return session.insert("trInsert", ehVO);
	}

	// 교육 사항 목록
	@Override
	public List<EmHrVO> trAllSelect(EmHrVO ehvo) {
		// TODO Auto-generated method stub
		return session.selectList("trAllSelect", ehvo);
	}
	
	// 교육 사항 상세 조회
	@Override
	public List<EmHrVO> trDetail(EmHrVO ehvo) {
		// TODO Auto-generated method stub
		return session.selectList("trDetail", ehvo);
	}

	// 교육 사항 수정
	@Override
	public int trUpdate(EmHrVO ehVO) {
		// TODO Auto-generated method stub
		return session.update("trUpdate", ehVO);
	}

	// 채번 (근태)
	@Override
	public List<EmInfoVO> ctChaebun(EmInfoVO evo) {
		// TODO Auto-generated method stub
		return session.selectList("ctChaebun", evo);
	}
	
	// 근태 등록
	@Override
	public int ctInsert(EmPrInfoVO epVO) {
		// TODO Auto-generated method stub
		return session.insert("ctInsert", epVO);
	}

	// 출근 시간 업데이트
	@Override
	public int ctIntimeUpdate(EmInfoVO evo) {
		// TODO Auto-generated method stub
		return session.update("ctIntimeUpdate", evo);
	}

	// 퇴근 시간 업데이트
	@Override
	public int ctOuttimeUpdate(EmInfoVO evo) {
		// TODO Auto-generated method stub
		return session.update("ctOuttimeUpdate", evo);
	}
	
	// 출/퇴근 시간 확인
	@Override
	public List<EmInfoVO> ctSelect(EmInfoVO evo) {
		// TODO Auto-generated method stub
		return session.selectList("ctSelect", evo);
	}

	// 출근 코드 업데이트
	@Override
	public int ctUpdate(EmInfoVO evo) {
		// TODO Auto-generated method stub
		return session.update("ctUpdate", evo);
	}

	// 퇴근 코드 업데이트
	@Override
	public int ctOutUpdate(EmInfoVO evo) {
		// TODO Auto-generated method stub
		return session.update("ctOutUpdate", evo);
	}

	
	// 근태 목록
	@Override
	public List<EmInfoVO> ctAllSelect(EmInfoVO evo) {
		// TODO Auto-generated method stub
		return session.selectList("ctAllSelect", evo);
	}
	
	// 근태 자료 로그 테이블로 이동
	@Override
	public int ctToLog() {
		// TODO Auto-generated method stub
		return session.insert("ctToLog");
	}

	// 전 사원 결근으로 업데이트
	@Override
	public int ctNextDay() {
		// TODO Auto-generated method stub
		return session.update("ctNextDay");
	}

	// 채번 (로그테이블)
	@Override
	public List<EmInfoVO> logChaebun(EmInfoVO evo) {
		// TODO Auto-generated method stub
		return session.selectList("logChaebun", evo);
	}

	// 관리자 근태 조정
	@Override
	public int ctAdminUpdate(EmInfoVO eVO) {
		// TODO Auto-generated method stub
		return session.update("ctAdminUpdate", eVO);
	}

	// 내 정보 조회
	@Override
	public List<EmPrInfoVO> myPageInfo(EmPrInfoVO epvo) {
		// TODO Auto-generated method stub
		return session.selectList("myPageInfo", epvo);
	}

	// 비밀번호 변경
	@Override
	public int pwUpdate(EmInfoVO eVo) {
		// TODO Auto-generated method stub
		return session.update("pwUpdate", eVo);
	}

	// 마이페이지 교육목록
	@Override
	public List<EmHrVO> myPageTr(EmHrVO ehvo) {
		// TODO Auto-generated method stub
		return session.selectList("myPageTr", ehvo);
	}

	// 마이페이지 교육 상세
	@Override
	public List<EmHrVO> myPageTrDetail(EmHrVO ehVO) {
		// TODO Auto-generated method stub
		return session.selectList("myPageTrDetail", ehVO);
	}

	// 채번 (평가)
	@Override
	public List<EmHrVO> evChaebun(EmHrVO ehvo) {
		// TODO Auto-generated method stub
		return session.selectList("evChaebun", ehvo);
	}

	// 평가 등록
	@Override
	public int evInsert(EmHrVO ehVO) {
		// TODO Auto-generated method stub
		return session.insert("evInsert", ehVO);
	}

	// 평가 목록
	@Override
	public List<EmHrVO> evAllSelect(EmHrVO ehvo) {
		// TODO Auto-generated method stub
		return session.selectList("evAllSelect", ehvo);
	}

	// 퇴근 누르면 로그 테이블로 이동
	@Override
	public int goToLog(EmInfoVO evo) {
		// TODO Auto-generated method stub
		return session.insert("goToLog", evo);
	}

	// 평가 상세 조회
	@Override
	public List<EmHrVO> evDetail(EmHrVO ehvo) {
		// TODO Auto-generated method stub
		return session.selectList("evDetail", ehvo);
		
	}

	// 평가 수정
	@Override
	public int evUpdate(EmHrVO ehvo) {
		// TODO Auto-generated method stub
		return session.update("evUpdate", ehvo);
	}

	// 근태 기간 조회
	@Override
	public List<EmInfoVO> ctSearch(EmInfoVO evo) {
		// TODO Auto-generated method stub
		return session.selectList("ctSearch", evo);
	}



}
