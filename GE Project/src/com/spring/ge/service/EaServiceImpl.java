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
		logger.info("[log] EaServiceImpl.eaSelectAll ���� >>> ");
		logger.info("[log] EaServiceImpl.eaSelectAll ���� >>> " + param.getSearch());
		logger.info("[log] EaServiceImpl.eaSelectAll ���� >>> " + param.getKeyword());
		List<EaVO> list = null;
		list = eaDao.eaSelectAll(param);
		logger.info("[log] EaServiceImpl.eaSelectAll ���� <<< ");
		return list;
	}

	@Override
	public List<EaVO> eaSelectAllPG(EaVO param) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.eaSelectAllPG ���� >>> ");
		List<EaVO> list = null;
		list = eaDao.eaSelectAllPG(param);
		logger.info("[log] EaServiceImpl.eaSelectAllPG ���� <<< ");
		return list;
	}

	@Override
	public List<EaVO> eaSelectAllSB(EaVO param) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.eaSelectAllSB ���� >>> ");
		List<EaVO> list = null;
		list = eaDao.eaSelectAllSB(param);
		logger.info("[log] EaServiceImpl.eaSelectAllSB ���� <<< ");
		return list;
	}

	@Override
	public List<EaVO> eaSelectAllFN(EaVO param) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.eaSelectAllFN ���� >>> ");
		List<EaVO> list = null;
		list = eaDao.eaSelectAllFN(param);
		logger.info("[log] EaServiceImpl.eaSelectAllFN ���� <<< ");
		return list;
	}

	@Override
	public List<EaVO> eaSelectAllRT(EaVO param) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.eaSelectAllRT ���� >>> ");
		List<EaVO> list = null;
		list = eaDao.eaSelectAllRT(param);
		logger.info("[log] EaServiceImpl.eaSelectAllRT ���� <<< ");
		return list;
	}
	
	@Override
	public List<EaVO> eaDetail(EaVO param) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.eaDetail ���� >>> ");
		List<EaVO> list = null;
		list = eaDao.eaDetail(param);
		logger.info("[log] EaServiceImpl.eaDetail ���� <<< ");
		return list;
	}

	/* ========================== �� �Ʒ��� �μ�Ʈ�Դϴ� ========================== */
	
	@Override
	public int eaLineInsert(EaVO param) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.eaLineInsert ���� >>> ");		
		int result = eaDao.eaLineInsert(param);
		logger.info("[log] EaServiceImpl.eaLineInsert ���� <<< ");
		return result;
	}

	@Override
	public int eaStepInsert(EaVO param) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.eaStepInsert ���� >>> ");		
		int result = eaDao.eaStepInsert(param);
		logger.info("[log] EaServiceImpl.eaStepInsert ���� <<< ");
		return result;
	}

	@Override
	public int eaLogInsert(EaVO param) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.eaLogInsert ���� >>> ");		
		int result = eaDao.eaLogInsert(param);
		logger.info("[log] EaServiceImpl.eaLogInsert ���� <<< ");
		return result;
	}

	@Override
	public int eaTableInsert(EaVO param) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.eaTableInsert ���� >>> ");		
		int result = eaDao.eaTableInsert(param);
		logger.info("[log] EaServiceImpl.eaTableInsert ���� <<< ");
		return result;
	}
	
	/* =============�� �Ʒ��� ä���Դϴ�============= */
	@Override
	public List<EaVO> chaebunEAL(EaVO param) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.chaebunEAL ���� >>> ");
		List<EaVO> list = new ArrayList<EaVO>();
		list = eaDao.chaebunEAL(param);
		logger.info("[log] EaServiceImpl.chaebunEAL ���� <<< ");
		return list;
	}

	@Override
	public List<EaVO> chaebunEASTNO(EaVO param) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.chaebunEASTNO ���� >>> ");
		List<EaVO> list = new ArrayList<EaVO>();
		list = eaDao.chaebunEASTNO(param);
		logger.info("[log] EaServiceImpl.chaebunEASTNO ���� <<< ");
		return list;
	}

	@Override
	public List<EaVO> chaebunEALOG(EaVO param) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.chaebunEALOG ���� >>> ");
		List<EaVO> list = new ArrayList<EaVO>();
		list = eaDao.chaebunEALOG(param);
		logger.info("[log] EaServiceImpl.chaebunEALOG ���� <<< ");
		return list;
	}

	@Override
	public List<EaVO> chaebunEA(EaVO param) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.chaebunEA ���� >>> ");
		List<EaVO> list = new ArrayList<EaVO>();
		list = eaDao.chaebunEA(param);
		logger.info("[log] EaServiceImpl.chaebunEA ���� <<< ");
		return list;
	}
	
	/* =============�� �Ʒ��� ���Ƿα��� �Դϴ� ============= */
	@Override
	public EmInfoVO em_InfoList(EmInfoVO param) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.em_InfoList ���� >>> ");
		EmInfoVO evo = null;
		evo = eaDao.em_InfoList(param);
		logger.info("[log] EaServiceImpl.em_InfoList ���� <<< ");
		return evo;
	}

	/* =============�� �Ʒ��� ���̺� ������Ʈ �Դϴ� ============= */
	@Override
	public int eaTableUpdate(EaVO param) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.eaTableUpdate ���� >>> ");		
		int result = eaDao.eaTableUpdate(param);
		logger.info("[log] EaServiceImpl.eaTableUpdate ���� <<< ");
		return result;
	}

	/* =============�� �Ʒ��� ajax�� ���� ������ ����Ʈ �Դϴ� ============= */
	@Override
	public List<EmInfoVO> getDeptList(EmInfoVO param) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.getDeptList ���� >>> ");
		List<EmInfoVO> list = null;
		list = eaDao.getDeptList(param);
		logger.info("[log] EaServiceImpl.getDeptList ���� <<< ");
		return list;
	}

	@Override
	public List<EmInfoVO> getJobList(EmInfoVO param) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.getJobList ���� >>> ");
		List<EmInfoVO> list = null;
		list = eaDao.getJobList(param);
		logger.info("[log] EaServiceImpl.getJobList ���� <<< ");
		return list;
	}
	
	public List<EmInfoVO> getEmnameList(EmInfoVO param) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.getEmnameList ���� >>> ");
		List<EmInfoVO> list = null;
		list = eaDao.getEmnameList(param);
		logger.info("[log] EaServiceImpl.getEmnameList ���� <<< ");
		return list;
	}

	@Override
	public List<EmInfoVO> emInfoSelectALL(EmInfoVO param) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.emInfoSelectALL ���� >>> ");
		List<EmInfoVO> list = null;
		list = eaDao.emInfoSelectALL(param);
		logger.info("[log] EaServiceImpl.emInfoSelectALL ���� <<< ");
		return list;
	}

	@Override
	public List<EaVO> eaMainSelectAll(EaVO param) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.eaMainSelectAll ���� >>> ");
		List<EaVO> list = null;
		list = eaDao.eaMainSelectAll(param);
		logger.info("[log] EaServiceImpl.eaMainSelectAll ���� <<< ");
		return list;
	}

	@Override
	public List<EaVO> eaMainSelectAllFN(EaVO param) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.eaMainSelectAllFN ���� >>> ");
		List<EaVO> list = null;
		list = eaDao.eaMainSelectAllFN(param);
		logger.info("[log] EaServiceImpl.eaMainSelectAllFN ���� <<< ");
		return list;
	}

	@Override
	public List<EaVO> eaMainSelectAllPG(EaVO param) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.eaMainSelectAllPG ���� >>> ");
		List<EaVO> list = null;
		list = eaDao.eaMainSelectAllPG(param);
		logger.info("[log] EaServiceImpl.eaMainSelectAllPG ���� <<< ");
		return list;
	}

	@Override
	public List<EaVO> eaMainSelectAllRJ(EaVO param) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.eaMainSelectAllRJ ���� >>> ");
		List<EaVO> list = null;
		list = eaDao.eaMainSelectAllRJ(param);
		logger.info("[log] EaServiceImpl.eaMainSelectAllRJ ���� <<< ");
		return list;
	}

	@Override
	public List<EaVO> eaMainApprove(EaVO param) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.eaMainApprove ���� >>> ");
		List<EaVO> list = null;
		list = eaDao.eaMainApprove(param);
		logger.info("[log] EaServiceImpl.eaMainApprove ���� <<< ");
		return list;
	}

	@Override
	public List<EaVO> eaMyAppList(EaVO param) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.eaMyAppList ���� >>> ");
		List<EaVO> list = null;
		list = eaDao.eaMyAppList(param);
		logger.info("[log] EaServiceImpl.eaMyAppList ���� <<< ");
		return list;
	}

	@Override
	public List<EaVO> eaNextEmno(EaVO param) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.eaNextEmno ���� >>> ");
		List<EaVO> list = null;
		list = eaDao.eaNextEmno(param);
		logger.info("[log] EaServiceImpl.eaNextEmno ���� <<< ");
		return list;
	}

} // end of EaServiceImpl
