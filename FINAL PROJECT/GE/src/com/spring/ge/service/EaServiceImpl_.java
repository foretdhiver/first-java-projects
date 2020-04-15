package com.spring.ge.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.ge.dao.EaDao_;
import com.spring.ge.vo.EaVO_;
import com.spring.ge.vo.EmInfoVO;

@Service("eaService")
@Transactional
public class EaServiceImpl_ implements EaService_ {
	
	Logger logger = Logger.getLogger(EaService_.class);
	
	@Autowired
	private EaDao_ eaaDao;
	
	@Override
	public EmInfoVO emInfoList(EmInfoVO evo){
		logger.info("[log] EaServiceImpl.emInfoList ����");
		System.out.println("id " + evo.getEmid());
		System.out.println("pw " + evo.getEmpw());
		EmInfoVO emInfoList = new EmInfoVO();
		emInfoList = eaaDao.emInfoList(evo);
		logger.info("[log] EaServiceImpl.emInfoList ��");
		return emInfoList;
	}
	
	@Override
	public int eaInsertLine(EaVO_ evo){
		logger.info("[log] EaServiceImpl.eaInsertLine ����");
		int eaInsertLine = 0;
		eaInsertLine = eaaDao.eaInsertLine(evo);
		logger.info("[log] EaServiceImpl.eaInsertLine ��");
		return eaInsertLine;
	}
	
	@Override
	public int eaInsertStep(EaVO_ evo){
		logger.info("[log] EaServiceImpl.eaInsertStep ����");
		int eaInsertStep = 0;
		eaInsertStep = eaaDao.eaInsertStep(evo);
		logger.info("[log] EaServiceImpl.eaInsertStep ��");
		return eaInsertStep;
	}
	
	@Override
	public int eaInsertLog(EaVO_ evo){
		logger.info("[log] EaServiceImpl.eaInsertLog ����");
		int eaInsertLog = 0;
		eaInsertLog = eaaDao.eaInsertLog(evo);
		logger.info("[log] EaServiceImpl.eaInsertLog ��");
		return eaInsertLog;
	}
	
	@Override
	public List<EaVO_> nextEmno(EaVO_ evo) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.nextEmno ����");
		List<EaVO_> eaNextEmno = null;
		eaNextEmno = eaaDao.nextEmno(evo);
		logger.info("[log] EaServiceImpl.nextEmno ��");
		return eaNextEmno;
	}
	
	@Override
	public int eaInsertH(EaVO_ evo){
		logger.info("[log] EaServiceImpl.eaInsertH ����");
		int eaInsertH = 0;
		eaInsertH = eaaDao.eaInsertH(evo);
		logger.info("[log] EaServiceImpl.eaInsertH ��");
		return eaInsertH;
	}
	
	@Override
	public int eaInsertP(EaVO_ evo){
		logger.info("[log] EaServiceImpl.eaInsertP ����");
		int eaInsertP = 0;
		eaInsertP = eaaDao.eaInsertP(evo);
		logger.info("[log] EaServiceImpl.eaInsertP ��");
		return eaInsertP;
	}
	
	@Override
	public List<EaVO_> eaMainAppList(EaVO_ evo) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.eaMainAppList ����");
		List<EaVO_> eaMainAppList = null;
		eaMainAppList = eaaDao.eaMainAppList(evo);
		logger.info("[log] EaServiceImpl.eaMainAppList ��");
		return eaMainAppList;
	}
	
	@Override
	public List<EaVO_> eaMainRJList(EaVO_ evo) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.eaMainRJList ����");
		List<EaVO_> eaMainRJList = null;
		eaMainRJList = eaaDao.eaMainRJList(evo);
		logger.info("[log] EaServiceImpl.eaMainRJList ��");
		return eaMainRJList;
	}
	
	@Override
	public List<EaVO_> eaMainPGList(EaVO_ evo) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.eaMainPGList ����");
		List<EaVO_> eaMainPGList = null;
		eaMainPGList = eaaDao.eaMainPGList(evo);
		logger.info("[log] EaServiceImpl.eaMainPGList ��");
		return eaMainPGList;
	}
	
	@Override
	public List<EaVO_> eaMainSTList(EaVO_ evo) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.eaMainSTList ����");
		List<EaVO_> eaMainSTList = null;
		eaMainSTList = eaaDao.eaMainSTList(evo);
		logger.info("[log] EaServiceImpl.eaMainSTList ��");
		return eaMainSTList;
	}
	
	@Override
	public List<EaVO_> eaMaineaList(EaVO_ evo){
		logger.info("[log] EaServiceImpl.eaMaineaList ����");
		List<EaVO_> eaMaineaList = null;
		eaMaineaList = eaaDao.eaMaineaList(evo);
		logger.info("[log] EaServiceImpl.eaMaineaList ��");
		return eaMaineaList;
	}
	
	@Override
	public List<EmInfoVO> searchDeptList(EmInfoVO evo){
		logger.info("[log] EaServiceImpl.searchDeptList ����");
		List<EmInfoVO> searchDeptList = null;
		searchDeptList = eaaDao.searchDeptList(evo);
		logger.info("[log] EaServiceImpl.searchDeptList ����");
		return searchDeptList;
	}
	
	@Override
	public List<EmInfoVO> searchJobList(EmInfoVO evo){
		logger.info("[log] EaServiceImpl.searchJobList ����");
		List<EmInfoVO> searchJobList = null;
		searchJobList = eaaDao.searchJobList(evo);
		logger.info("[log] EaServiceImpl.searchJobList ����");
		return searchJobList;
	}
	
	@Override
	public List<EmInfoVO> searchEmnameList(EmInfoVO evo){
		logger.info("[log] EaServiceImpl.searchEmnameList ����");
		List<EmInfoVO> searchEmnameList = null;
		searchEmnameList = eaaDao.searchEmnameList(evo);
		logger.info("[log] EaServiceImpl.searchEmnameList ����");
		return searchEmnameList;
	}

	@Override
	public List<EaVO_> eaWriteAll(EaVO_ evo) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.eaWriteAll ����");
		List<EaVO_> eaWriteAll = null;
		eaWriteAll = eaaDao.eaWriteAll(evo);
		System.out.println("[]����� " + evo.getEmno());
		logger.info("[log] EaServiceImpl.eaWriteAll ��");
		return eaWriteAll;
	}
	
	@Override
	public List<EaVO_> eaWritePG(EaVO_ evo) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.eaWritePG ����");
		List<EaVO_> eaWritePG = null;
		eaWritePG = eaaDao.eaWritePG(evo);
		System.out.println("[]����� " + evo.getEmno());
		logger.info("[log] EaServiceImpl.eaWritePG ��");
		return eaWritePG;
	}
	
	@Override
	public List<EaVO_> eaWriteEnd(EaVO_ evo) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.eaWriteEnd ����");
		List<EaVO_> eaWriteEnd = null;
		eaWriteEnd = eaaDao.eaWriteEnd(evo);
		System.out.println("[]����� " + evo.getEmno());
		logger.info("[log] EaServiceImpl.eaWriteEnd ��");
		return eaWriteEnd;
	}
	
	@Override
	public List<EaVO_> eaWriteRJ(EaVO_ evo) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.eaWriteRJ ����");
		List<EaVO_> eaWriteRJ = null;
		eaWriteRJ = eaaDao.eaWriteRJ(evo);
		System.out.println("[]����� " + evo.getEmno());
		logger.info("[log] EaServiceImpl.eaWriteRJ ��");
		return eaWriteRJ;
	}
	
	@Override
	public List<EaVO_> eaWriteST(EaVO_ evo) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.eaWriteST ����");
		List<EaVO_> eaWriteST = null;
		eaWriteST = eaaDao.eaWriteST(evo);
		System.out.println("[]����� " + evo.getEmno());
		logger.info("[log] EaServiceImpl.eaWriteST ��");
		return eaWriteST;
	}

	@Override
	public List<EaVO_> eaListAll(EaVO_ evo){
		logger.info("[log] EaServiceImpl.eaListAll ����");
		List<EaVO_> eaListAll = null;
		eaListAll = eaaDao.eaListAll(evo);
		System.out.println("[log]�� ��� : " + evo.getEa_nextemno());
		logger.info("[log] EaServiceImpl.eaListAll ��");
		return eaListAll;
	}
	
	@Override
	public List<EaVO_> eaListSelect(EaVO_ evo) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.eaListSelect ����");
		List<EaVO_> eaListSelect = null;
		eaListSelect = eaaDao.eaListSelect(evo);
		System.out.println("[log]�� ��� : " + evo.getEa_nextemno());
		logger.info("[log] EaServiceImpl.eaListSelect ��");
		return eaListSelect;
	}
	
	@Override
	public List<EaVO_> eaListStand(EaVO_ evo){
		logger.info("[log] EaServiceImpl.eaListStand ����");
		List<EaVO_> eaListStand = null;
		eaListStand = eaaDao.eaListStand(evo);
		System.out.println("[log]�� ��� : " + evo.getEa_nextemno());
		logger.info("[log] EaServiceImpl.eaListStand ��");
		return eaListStand;
	}
	
	@Override
	public List<EaVO_> eaFormDetail(EaVO_ evo) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.eaFormDetail ����");
		List<EaVO_> eaDetail = null;
		eaDetail = eaaDao.eaFormDetail(evo);
		logger.info("[log] EaServiceImpl.eaFormDetail ��");
		return eaDetail;
	}
	
	@Override
	public EaVO_ chaebunLine(EaVO_ evo){
		logger.info("[log] EaServiceImpl.chaebunLine ����");
		EaVO_ chaebunLine = null;
		chaebunLine = eaaDao.chaebunLine(evo);
		logger.info("[log] EaServiceImpl.chaebunLine ��");
		return chaebunLine;
	}
	
	@Override
	public EaVO_ chaebunStep(EaVO_ evo){
		logger.info("[log] EaDaoImpl.chaebunStep ����");
		EaVO_ chaebunStep = null;
		chaebunStep = eaaDao.chaebunStep(evo);
		logger.info("[log] EaDaoImpl.chaebunStep ��");
		return chaebunStep;
	}
	
	@Override
	public EaVO_ chaebunLog(EaVO_ evo) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.chaebunLog ����");
		EaVO_ chaebunLog = null;
		chaebunLog = eaaDao.chaebunLog(evo);
		logger.info("[log] EaServiceImpl.chaebunLog ��");
		return chaebunLog;
	}	
	
	@Override
	public EaVO_ chaebunTable(EaVO_ evo){
		logger.info("[log] EaDaoImpl.chaebunTable ����");
		EaVO_ chaebunTable = null;
		chaebunTable = eaaDao.chaebunTable(evo);
		logger.info("[log] EaDaoImpl.chaebunTable ��");
		return chaebunTable;
	}
	
	@Override
	public int eaLog1st(EaVO_ evo) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.eaLog1st ����");
		int eaLog1st = 0;
		eaLog1st = eaaDao.eaLog1st(evo);
		logger.info("[log] EaServiceImpl.eaLog1st ��");
		return eaLog1st;
	}

	@Override
	public int updateTable(EaVO_ evo) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.updateTable ����");
		int updateTable = 0;
		updateTable = eaaDao.updateTable(evo);
		logger.info("[log] EaServiceImpl.updateTable ��");
		return updateTable;
	}

	@Override
	public int eaLog2nd(EaVO_ evo){
		logger.info("[log] EaServiceImpl.eaLog2nd ����");
		int eaLog2nd = 0;
		eaLog2nd = eaaDao.eaLog2nd(evo);
		logger.info("[log] EaServiceImpl.eaLog2nd ��");
		return eaLog2nd;
	}
}