package com.spring.ge.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.ge.controller.EaController;
import com.spring.ge.dao.EaDao;
import com.spring.ge.vo.EaVO;
import com.spring.ge.vo.EmInfoVO;

@Service
@Transactional
public class EaServiceImpl implements EaService {
	
	private static Logger logger = Logger.getLogger(EaController.class);
	
	@Autowired
	private EaDao eaDao;
	
	@Override
	public List<EaVO> eaSelectAll(EaVO param) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.eaSelectAll 진입 >>> ");
		logger.info("[log] EaServiceImpl.eaSelectAll 진입 >>> " + param.getSearch());
		logger.info("[log] EaServiceImpl.eaSelectAll 진입 >>> " + param.getKeyword());
		List<EaVO> list = null;
		list = eaDao.eaSelectAll(param);
		logger.info("[log] EaServiceImpl.eaSelectAll 종료 <<< ");
		return list;
	}

	@Override
	public List<EaVO> eaSelectAllPG(EaVO param) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.eaSelectAllPG 진입 >>> ");
		List<EaVO> list = null;
		list = eaDao.eaSelectAllPG(param);
		logger.info("[log] EaServiceImpl.eaSelectAllPG 종료 <<< ");
		return list;
	}

	@Override
	public List<EaVO> eaSelectAllSB(EaVO param) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.eaSelectAllSB 진입 >>> ");
		List<EaVO> list = null;
		list = eaDao.eaSelectAllSB(param);
		logger.info("[log] EaServiceImpl.eaSelectAllSB 종료 <<< ");
		return list;
	}

	@Override
	public List<EaVO> eaSelectAllFN(EaVO param) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.eaSelectAllFN 진입 >>> ");
		List<EaVO> list = null;
		list = eaDao.eaSelectAllFN(param);
		logger.info("[log] EaServiceImpl.eaSelectAllFN 종료 <<< ");
		return list;
	}

	@Override
	public List<EaVO> eaSelectAllRT(EaVO param) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.eaSelectAllRT 진입 >>> ");
		List<EaVO> list = null;
		list = eaDao.eaSelectAllRT(param);
		logger.info("[log] EaServiceImpl.eaSelectAllRT 종료 <<< ");
		return list;
	}
	
	@Override
	public List<EaVO> eaDetail(EaVO param) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.eaDetail 진입 >>> ");
		List<EaVO> list = null;
		list = eaDao.eaDetail(param);
		logger.info("[log] EaServiceImpl.eaDetail 종료 <<< ");
		return list;
	}

	/* ========================== 이 아래는 인서트입니다 ========================== */
	
	@Override
	public int eaLineInsert(EaVO param) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.eaLineInsert 진입 >>> ");		
		int result = eaDao.eaLineInsert(param);
		logger.info("[log] EaServiceImpl.eaLineInsert 종료 <<< ");
		return result;
	}

	@Override
	public int eaStepInsert(EaVO param) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.eaStepInsert 진입 >>> ");		
		int result = eaDao.eaStepInsert(param);
		logger.info("[log] EaServiceImpl.eaStepInsert 종료 <<< ");
		return result;
	}

	@Override
	public int eaLogInsert(EaVO param) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.eaLogInsert 진입 >>> ");		
		int result = eaDao.eaLogInsert(param);
		logger.info("[log] EaServiceImpl.eaLogInsert 종료 <<< ");
		return result;
	}

	@Override
	public int eaTableInsert(EaVO param) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.eaTableInsert 진입 >>> ");		
		int result = eaDao.eaTableInsert(param);
		logger.info("[log] EaServiceImpl.eaTableInsert 종료 <<< ");
		return result;
	}
	
	/* =============이 아래는 채번입니다============= */
	@Override
	public List<EaVO> chaebunEAL(EaVO param) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.chaebunEAL 진입 >>> ");
		List<EaVO> list = new ArrayList<EaVO>();
		list = eaDao.chaebunEAL(param);
		logger.info("[log] EaServiceImpl.chaebunEAL 종료 <<< ");
		return list;
	}

	@Override
	public List<EaVO> chaebunEASTNO(EaVO param) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.chaebunEASTNO 진입 >>> ");
		List<EaVO> list = new ArrayList<EaVO>();
		list = eaDao.chaebunEASTNO(param);
		logger.info("[log] EaServiceImpl.chaebunEASTNO 종료 <<< ");
		return list;
	}

	@Override
	public List<EaVO> chaebunEALOG(EaVO param) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.chaebunEALOG 진입 >>> ");
		List<EaVO> list = new ArrayList<EaVO>();
		list = eaDao.chaebunEALOG(param);
		logger.info("[log] EaServiceImpl.chaebunEALOG 종료 <<< ");
		return list;
	}

	@Override
	public List<EaVO> chaebunEA(EaVO param) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.chaebunEA 진입 >>> ");
		List<EaVO> list = new ArrayList<EaVO>();
		list = eaDao.chaebunEA(param);
		logger.info("[log] EaServiceImpl.chaebunEA 종료 <<< ");
		return list;
	}
	
	/* =============이 아래는 세션로그인 입니다 ============= */
	@Override
	public EmInfoVO em_InfoList(EmInfoVO param) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.em_InfoList 진입 >>> ");
		EmInfoVO evo = null;
		evo = eaDao.em_InfoList(param);
		logger.info("[log] EaServiceImpl.em_InfoList 종료 <<< ");
		return evo;
	}

	/* =============이 아래는 테이블 업데이트 입니다 ============= */
	@Override
	public int eaTableUpdate(EaVO param) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.eaTableUpdate 진입 >>> ");		
		int result = eaDao.eaTableUpdate(param);
		logger.info("[log] EaServiceImpl.eaTableUpdate 종료 <<< ");
		return result;
	}

	/* =============이 아래는 ajax를 위한 데이터 셀렉트 입니다 ============= */
	@Override
	public List<EmInfoVO> getDeptList(EmInfoVO param) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.getDeptList 진입 >>> ");
		List<EmInfoVO> list = null;
		list = eaDao.getDeptList(param);
		logger.info("[log] EaServiceImpl.getDeptList 종료 <<< ");
		return list;
	}

	@Override
	public List<EmInfoVO> getJobList(EmInfoVO param) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.getJobList 진입 >>> ");
		List<EmInfoVO> list = null;
		list = eaDao.getJobList(param);
		logger.info("[log] EaServiceImpl.getJobList 종료 <<< ");
		return list;
	}
	
	public List<EmInfoVO> getEmnameList(EmInfoVO param) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.getEmnameList 진입 >>> ");
		List<EmInfoVO> list = null;
		list = eaDao.getEmnameList(param);
		logger.info("[log] EaServiceImpl.getEmnameList 종료 <<< ");
		return list;
	}

	@Override
	public List<EmInfoVO> emInfoSelectALL(EmInfoVO param) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.emInfoSelectALL 진입 >>> ");
		List<EmInfoVO> list = null;
		list = eaDao.emInfoSelectALL(param);
		logger.info("[log] EaServiceImpl.emInfoSelectALL 종료 <<< ");
		return list;
	}

	@Override
	public List<EaVO> eaMainSelectAll(EaVO param) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.eaMainSelectAll 진입 >>> ");
		List<EaVO> list = null;
		list = eaDao.eaMainSelectAll(param);
		logger.info("[log] EaServiceImpl.eaMainSelectAll 종료 <<< ");
		return list;
	}

	@Override
	public List<EaVO> eaMainSelectAllFN(EaVO param) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.eaMainSelectAllFN 진입 >>> ");
		List<EaVO> list = null;
		list = eaDao.eaMainSelectAllFN(param);
		logger.info("[log] EaServiceImpl.eaMainSelectAllFN 종료 <<< ");
		return list;
	}

	@Override
	public List<EaVO> eaMainSelectAllPG(EaVO param) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.eaMainSelectAllPG 진입 >>> ");
		List<EaVO> list = null;
		list = eaDao.eaMainSelectAllPG(param);
		logger.info("[log] EaServiceImpl.eaMainSelectAllPG 종료 <<< ");
		return list;
	}

	@Override
	public List<EaVO> eaMainSelectAllRJ(EaVO param) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.eaMainSelectAllRJ 진입 >>> ");
		List<EaVO> list = null;
		list = eaDao.eaMainSelectAllRJ(param);
		logger.info("[log] EaServiceImpl.eaMainSelectAllRJ 종료 <<< ");
		return list;
	}

	@Override
	public List<EaVO> eaMainApprove(EaVO param) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.eaMainApprove 진입 >>> ");
		List<EaVO> list = null;
		list = eaDao.eaMainApprove(param);
		logger.info("[log] EaServiceImpl.eaMainApprove 종료 <<< ");
		return list;
	}

	@Override
	public List<EaVO> eaMyAppList(EaVO param) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.eaMyAppList 진입 >>> ");
		List<EaVO> list = null;
		list = eaDao.eaMyAppList(param);
		logger.info("[log] EaServiceImpl.eaMyAppList 종료 <<< ");
		return list;
	}

	@Override
	public List<EaVO> eaNextEmno(EaVO param) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.eaNextEmno 진입 >>> ");
		List<EaVO> list = null;
		list = eaDao.eaNextEmno(param);
		logger.info("[log] EaServiceImpl.eaNextEmno 종료 <<< ");
		return list;
	}

} // end of EaServiceImpl
