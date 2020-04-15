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
		logger.info("[log] EaServiceImpl.emInfoList 시작");
		System.out.println("id " + evo.getEmid());
		System.out.println("pw " + evo.getEmpw());
		EmInfoVO emInfoList = new EmInfoVO();
		emInfoList = eaaDao.emInfoList(evo);
		logger.info("[log] EaServiceImpl.emInfoList 끝");
		return emInfoList;
	}
	
	@Override
	public int eaInsertLine(EaVO_ evo){
		logger.info("[log] EaServiceImpl.eaInsertLine 시작");
		int eaInsertLine = 0;
		eaInsertLine = eaaDao.eaInsertLine(evo);
		logger.info("[log] EaServiceImpl.eaInsertLine 끝");
		return eaInsertLine;
	}
	
	@Override
	public int eaInsertStep(EaVO_ evo){
		logger.info("[log] EaServiceImpl.eaInsertStep 시작");
		int eaInsertStep = 0;
		eaInsertStep = eaaDao.eaInsertStep(evo);
		logger.info("[log] EaServiceImpl.eaInsertStep 끝");
		return eaInsertStep;
	}
	
	@Override
	public int eaInsertLog(EaVO_ evo){
		logger.info("[log] EaServiceImpl.eaInsertLog 시작");
		int eaInsertLog = 0;
		eaInsertLog = eaaDao.eaInsertLog(evo);
		logger.info("[log] EaServiceImpl.eaInsertLog 끝");
		return eaInsertLog;
	}
	
	@Override
	public List<EaVO_> nextEmno(EaVO_ evo) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.nextEmno 시작");
		List<EaVO_> eaNextEmno = null;
		eaNextEmno = eaaDao.nextEmno(evo);
		logger.info("[log] EaServiceImpl.nextEmno 끝");
		return eaNextEmno;
	}
	
	@Override
	public int eaInsertH(EaVO_ evo){
		logger.info("[log] EaServiceImpl.eaInsertH 시작");
		int eaInsertH = 0;
		eaInsertH = eaaDao.eaInsertH(evo);
		logger.info("[log] EaServiceImpl.eaInsertH 끝");
		return eaInsertH;
	}
	
	@Override
	public int eaInsertP(EaVO_ evo){
		logger.info("[log] EaServiceImpl.eaInsertP 시작");
		int eaInsertP = 0;
		eaInsertP = eaaDao.eaInsertP(evo);
		logger.info("[log] EaServiceImpl.eaInsertP 끝");
		return eaInsertP;
	}
	
	@Override
	public List<EaVO_> eaMainAppList(EaVO_ evo) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.eaMainAppList 시작");
		List<EaVO_> eaMainAppList = null;
		eaMainAppList = eaaDao.eaMainAppList(evo);
		logger.info("[log] EaServiceImpl.eaMainAppList 끝");
		return eaMainAppList;
	}
	
	@Override
	public List<EaVO_> eaMainRJList(EaVO_ evo) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.eaMainRJList 시작");
		List<EaVO_> eaMainRJList = null;
		eaMainRJList = eaaDao.eaMainRJList(evo);
		logger.info("[log] EaServiceImpl.eaMainRJList 끝");
		return eaMainRJList;
	}
	
	@Override
	public List<EaVO_> eaMainPGList(EaVO_ evo) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.eaMainPGList 시작");
		List<EaVO_> eaMainPGList = null;
		eaMainPGList = eaaDao.eaMainPGList(evo);
		logger.info("[log] EaServiceImpl.eaMainPGList 끝");
		return eaMainPGList;
	}
	
	@Override
	public List<EaVO_> eaMainSTList(EaVO_ evo) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.eaMainSTList 시작");
		List<EaVO_> eaMainSTList = null;
		eaMainSTList = eaaDao.eaMainSTList(evo);
		logger.info("[log] EaServiceImpl.eaMainSTList 끝");
		return eaMainSTList;
	}
	
	@Override
	public List<EaVO_> eaMaineaList(EaVO_ evo){
		logger.info("[log] EaServiceImpl.eaMaineaList 시작");
		List<EaVO_> eaMaineaList = null;
		eaMaineaList = eaaDao.eaMaineaList(evo);
		logger.info("[log] EaServiceImpl.eaMaineaList 끝");
		return eaMaineaList;
	}
	
	@Override
	public List<EmInfoVO> searchDeptList(EmInfoVO evo){
		logger.info("[log] EaServiceImpl.searchDeptList 시작");
		List<EmInfoVO> searchDeptList = null;
		searchDeptList = eaaDao.searchDeptList(evo);
		logger.info("[log] EaServiceImpl.searchDeptList 시작");
		return searchDeptList;
	}
	
	@Override
	public List<EmInfoVO> searchJobList(EmInfoVO evo){
		logger.info("[log] EaServiceImpl.searchJobList 시작");
		List<EmInfoVO> searchJobList = null;
		searchJobList = eaaDao.searchJobList(evo);
		logger.info("[log] EaServiceImpl.searchJobList 시작");
		return searchJobList;
	}
	
	@Override
	public List<EmInfoVO> searchEmnameList(EmInfoVO evo){
		logger.info("[log] EaServiceImpl.searchEmnameList 시작");
		List<EmInfoVO> searchEmnameList = null;
		searchEmnameList = eaaDao.searchEmnameList(evo);
		logger.info("[log] EaServiceImpl.searchEmnameList 시작");
		return searchEmnameList;
	}

	@Override
	public List<EaVO_> eaWriteAll(EaVO_ evo) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.eaWriteAll 시작");
		List<EaVO_> eaWriteAll = null;
		eaWriteAll = eaaDao.eaWriteAll(evo);
		System.out.println("[]어딨니 " + evo.getEmno());
		logger.info("[log] EaServiceImpl.eaWriteAll 끝");
		return eaWriteAll;
	}
	
	@Override
	public List<EaVO_> eaWritePG(EaVO_ evo) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.eaWritePG 시작");
		List<EaVO_> eaWritePG = null;
		eaWritePG = eaaDao.eaWritePG(evo);
		System.out.println("[]어딨니 " + evo.getEmno());
		logger.info("[log] EaServiceImpl.eaWritePG 끝");
		return eaWritePG;
	}
	
	@Override
	public List<EaVO_> eaWriteEnd(EaVO_ evo) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.eaWriteEnd 시작");
		List<EaVO_> eaWriteEnd = null;
		eaWriteEnd = eaaDao.eaWriteEnd(evo);
		System.out.println("[]어딨니 " + evo.getEmno());
		logger.info("[log] EaServiceImpl.eaWriteEnd 끝");
		return eaWriteEnd;
	}
	
	@Override
	public List<EaVO_> eaWriteRJ(EaVO_ evo) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.eaWriteRJ 시작");
		List<EaVO_> eaWriteRJ = null;
		eaWriteRJ = eaaDao.eaWriteRJ(evo);
		System.out.println("[]어딨니 " + evo.getEmno());
		logger.info("[log] EaServiceImpl.eaWriteRJ 끝");
		return eaWriteRJ;
	}
	
	@Override
	public List<EaVO_> eaWriteST(EaVO_ evo) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.eaWriteST 시작");
		List<EaVO_> eaWriteST = null;
		eaWriteST = eaaDao.eaWriteST(evo);
		System.out.println("[]어딨니 " + evo.getEmno());
		logger.info("[log] EaServiceImpl.eaWriteST 끝");
		return eaWriteST;
	}

	@Override
	public List<EaVO_> eaListAll(EaVO_ evo){
		logger.info("[log] EaServiceImpl.eaListAll 시작");
		List<EaVO_> eaListAll = null;
		eaListAll = eaaDao.eaListAll(evo);
		System.out.println("[log]내 사번 : " + evo.getEa_nextemno());
		logger.info("[log] EaServiceImpl.eaListAll 끝");
		return eaListAll;
	}
	
	@Override
	public List<EaVO_> eaListSelect(EaVO_ evo) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.eaListSelect 시작");
		List<EaVO_> eaListSelect = null;
		eaListSelect = eaaDao.eaListSelect(evo);
		System.out.println("[log]내 사번 : " + evo.getEa_nextemno());
		logger.info("[log] EaServiceImpl.eaListSelect 끝");
		return eaListSelect;
	}
	
	@Override
	public List<EaVO_> eaListStand(EaVO_ evo){
		logger.info("[log] EaServiceImpl.eaListStand 시작");
		List<EaVO_> eaListStand = null;
		eaListStand = eaaDao.eaListStand(evo);
		System.out.println("[log]내 사번 : " + evo.getEa_nextemno());
		logger.info("[log] EaServiceImpl.eaListStand 끝");
		return eaListStand;
	}
	
	@Override
	public List<EaVO_> eaFormDetail(EaVO_ evo) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.eaFormDetail 시작");
		List<EaVO_> eaDetail = null;
		eaDetail = eaaDao.eaFormDetail(evo);
		logger.info("[log] EaServiceImpl.eaFormDetail 끝");
		return eaDetail;
	}
	
	@Override
	public EaVO_ chaebunLine(EaVO_ evo){
		logger.info("[log] EaServiceImpl.chaebunLine 시작");
		EaVO_ chaebunLine = null;
		chaebunLine = eaaDao.chaebunLine(evo);
		logger.info("[log] EaServiceImpl.chaebunLine 끝");
		return chaebunLine;
	}
	
	@Override
	public EaVO_ chaebunStep(EaVO_ evo){
		logger.info("[log] EaDaoImpl.chaebunStep 시작");
		EaVO_ chaebunStep = null;
		chaebunStep = eaaDao.chaebunStep(evo);
		logger.info("[log] EaDaoImpl.chaebunStep 끝");
		return chaebunStep;
	}
	
	@Override
	public EaVO_ chaebunLog(EaVO_ evo) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.chaebunLog 시작");
		EaVO_ chaebunLog = null;
		chaebunLog = eaaDao.chaebunLog(evo);
		logger.info("[log] EaServiceImpl.chaebunLog 끝");
		return chaebunLog;
	}	
	
	@Override
	public EaVO_ chaebunTable(EaVO_ evo){
		logger.info("[log] EaDaoImpl.chaebunTable 시작");
		EaVO_ chaebunTable = null;
		chaebunTable = eaaDao.chaebunTable(evo);
		logger.info("[log] EaDaoImpl.chaebunTable 끝");
		return chaebunTable;
	}
	
	@Override
	public int eaLog1st(EaVO_ evo) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.eaLog1st 시작");
		int eaLog1st = 0;
		eaLog1st = eaaDao.eaLog1st(evo);
		logger.info("[log] EaServiceImpl.eaLog1st 끝");
		return eaLog1st;
	}

	@Override
	public int updateTable(EaVO_ evo) {
		// TODO Auto-generated method stub
		logger.info("[log] EaServiceImpl.updateTable 시작");
		int updateTable = 0;
		updateTable = eaaDao.updateTable(evo);
		logger.info("[log] EaServiceImpl.updateTable 끝");
		return updateTable;
	}

	@Override
	public int eaLog2nd(EaVO_ evo){
		logger.info("[log] EaServiceImpl.eaLog2nd 시작");
		int eaLog2nd = 0;
		eaLog2nd = eaaDao.eaLog2nd(evo);
		logger.info("[log] EaServiceImpl.eaLog2nd 끝");
		return eaLog2nd;
	}
}