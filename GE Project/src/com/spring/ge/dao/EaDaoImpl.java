package com.spring.ge.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.ge.controller.EaController;
import com.spring.ge.vo.EaVO;
import com.spring.ge.vo.EmInfoVO;

@Repository
public class EaDaoImpl implements EaDao {
	
	private static Logger logger = Logger.getLogger(EaController.class);
	
	@Autowired
	private SqlSession session;
	
	private final String EADAO = "com.spring.ge.dao.EaDao";
	
	@Override
	public List<EaVO> eaSelectAll(EaVO param) {
		// TODO Auto-generated method stub
		List<EaVO> list = null;
		/* 작성자가 나이고 가장 최신 로그상태의 selectall 쿼리를 이용하는 부분 */
		list = session.selectList(EADAO+"eaSelectAll");
		logger.info(param.getEa_no()); //나중에 확인하기
		return list;
	}

	@Override
	public List<EaVO> eaSelectAllPG(EaVO param) {
		// TODO Auto-generated method stub
		List<EaVO> list = null;
		list = session.selectList(EADAO+"eaSelectAllPG");
		return list;
	}

	@Override
	public List<EaVO> eaSelectAllSB(EaVO param) {
		// TODO Auto-generated method stub
		List<EaVO> list = null;
		list = session.selectList(EADAO+"eaSelectAllSB");
		return list;
	}

	@Override
	public List<EaVO> eaSelectAllFN(EaVO param) {
		// TODO Auto-generated method stub
		List<EaVO> list = null;
		list = session.selectList(EADAO+"eaSelectAllFN");
		return list;
	}

	@Override
	public List<EaVO> eaSelectAllRT(EaVO param) {
		// TODO Auto-generated method stub
		List<EaVO> list = null;
		list = session.selectList(EADAO+"eaSelectAllRT");
		return list;
	}
	
	@Override
	public List<EaVO> eaDetail(EaVO param) {
		// TODO Auto-generated method stub
		List<EaVO> list = null;
		list = session.selectList(EADAO+"eaDetail"); // selectList 할지 one 할지 생각해보자
		return list;
	}

	/* ========================== 이 아래는 인서트입니다 ========================== */
	
	@Override
	public int eaLineInsert(EaVO param) {
		// TODO Auto-generated method stub
		int result = 0;
		result = session.insert(EADAO+"eaLineInsert");
		return result;
	}

	@Override
	public int eaStepInsert(EaVO param) {
		// TODO Auto-generated method stub
		int result = 0;
		result = session.insert(EADAO+"eaStepInsert");
		return result;
	}

	@Override
	public int eaLogInsert(EaVO param) {
		// TODO Auto-generated method stub
		int result = 0;
		result = session.insert(EADAO+"eaLogInsert");
		return result;
	}

	@Override
	public int eaTableInsert(EaVO param) {
		// TODO Auto-generated method stub
		int result = 0;
		result = session.insert(EADAO+"eaTableInsert");
		return result;
	}
	
	/* ========================== 이 아래는 채번입니다 ========================== */
	@Override
	public List<EaVO> chaebunEAL(EaVO param) {
		// TODO Auto-generated method stub
		List<EaVO> list = session.selectList(EADAO+"chaebunEAL");
		return list;
	}
	
	@Override
	public List<EaVO> chaebunEASTNO(EaVO param) {
		// TODO Auto-generated method stub
		List<EaVO> list = session.selectList(EADAO+"chaebunEASTNO");
		return list;
	}

	@Override
	public List<EaVO> chaebunEALOG(EaVO param) {
		// TODO Auto-generated method stub
		List<EaVO> list = session.selectList(EADAO+"chaebunEALOG");
		return list;
	}

	@Override
	public List<EaVO> chaebunEA(EaVO param) {
		// TODO Auto-generated method stub
		List<EaVO> list = session.selectList(EADAO+"chaebunEA");
		return list;
	}
	
	/* =============이 아래는 세션로그인 입니다 ============= */

	@Override
	public EmInfoVO em_InfoList(EmInfoVO param) {
		// TODO Auto-generated method stub
		EmInfoVO evo = session.selectOne(EADAO+"em_InfoList");
		return evo;
	}

	/* =============이 아래는 테이블 업데이트 입니다 ============= */
	@Override
	public int eaTableUpdate(EaVO param) {
		// TODO Auto-generated method stub
		int result = 0;
		result = session.update(EADAO+"eaTableUpdate");
		return result;
	}

	/* =============이 아래는 ajax를 위한 데이터 셀렉트 입니다 ============= */
	@Override
	public List<EmInfoVO> getDeptList(EmInfoVO param) {
		// TODO Auto-generated method stub
		List<EmInfoVO> list = null;
		list = session.selectList(EADAO+"getDeptList"); // selectList 할지 one 할지 생각해보자
		return list;
	}
	
	@Override
	public List<EmInfoVO> getJobList(EmInfoVO param) {
		// TODO Auto-generated method stub
		List<EmInfoVO> list = null;
		list = session.selectList(EADAO+"getJobList"); // selectList 할지 one 할지 생각해보자
		return list;
	}
	
	@Override
	public List<EmInfoVO> getEmnameList(EmInfoVO param) {
		// TODO Auto-generated method stub
		List<EmInfoVO> list = null;
		list = session.selectList(EADAO+"getEmnameList"); // selectList 할지 one 할지 생각해보자
		return list;
	}

	@Override
	public List<EmInfoVO> emInfoSelectALL(EmInfoVO param) {
		// TODO Auto-generated method stub
		List<EmInfoVO> list = null;
		list = session.selectList(EADAO+"emInfoSelectALL"); // selectList 할지 one 할지 생각해보자
		return list;
	}

	@Override
	public List<EaVO> eaMainSelectAll(EaVO param) {
		// TODO Auto-generated method stub
		List<EaVO> list = null;
		list = session.selectList(EADAO+"eaMainSelectAll");
		return list;
	}

	@Override
	public List<EaVO> eaMainSelectAllFN(EaVO param) {
		// TODO Auto-generated method stub
		List<EaVO> list = null;
		list = session.selectList(EADAO+"eaMainSelectAllFN");
		return list;
	}

	@Override
	public List<EaVO> eaMainSelectAllPG(EaVO param) {
		// TODO Auto-generated method stub
		List<EaVO> list = null;
		list = session.selectList(EADAO+"eaMainSelectAllPG");
		return list;
	}

	@Override
	public List<EaVO> eaMainSelectAllRJ(EaVO param) {
		// TODO Auto-generated method stub
		List<EaVO> list = null;
		list = session.selectList(EADAO+"eaMainSelectAllRJ");
		return list;
	}

	@Override
	public List<EaVO> eaMainApprove(EaVO param) {
		// TODO Auto-generated method stub
		List<EaVO> list = null;
		list = session.selectList(EADAO+"eaMainApprove");
		return list;
	}

	@Override
	public List<EaVO> eaMyAppList(EaVO param) {
		// TODO Auto-generated method stub
		List<EaVO> list = null;
		list = session.selectList(EADAO+"eaMyAppList");
		return list;
	}

	@Override
	public List<EaVO> eaNextEmno(EaVO param) {
		// TODO Auto-generated method stub
		List<EaVO> list = null;
		list = session.selectList(EADAO+"eaNextEano");
		return list;
	}

} // end of EaDaoImpl
