package com.spring.ge.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.ge.vo.EaVO_;
import com.spring.ge.vo.EmInfoVO;

@Repository("eaaDao")
public class EaDaoImpl_ implements EaDao_ {

	Logger logger = Logger.getLogger(EaDao_.class);
	
	@Autowired
	private SqlSession session;
	
	@Override
	public EmInfoVO emInfoList(EmInfoVO evo){
		logger.info("[log] EaDaoImpl.emInfoList 시작");
		System.out.println("id " + evo.getEmid());
		System.out.println("pw " + evo.getEmpw());
		EmInfoVO emInfoList = (EmInfoVO) session.selectOne("emInfoList", evo);
		logger.info("[log] EaDaoImpl.emInfoList 끝");
		return emInfoList;
	}
	
	@Override
	public int eaInsertLine(EaVO_ evo){
		logger.info("[log] EaDaoImpl.eaInsertLine 시작");
		int eaInsertLine = 0;
		eaInsertLine = session.insert("eaInsertLine", evo);
		logger.info("[log] EaDaoImpl.eaInsertLine 끝");
		return eaInsertLine;
	}
	
	@Override
	public int eaInsertStep(EaVO_ evo){
		logger.info("[log] EaDaoImpl.eaInsertStep 시작");
		int eaInsertStep = 0;
		eaInsertStep = session.insert("eaInsertStep", evo);
		logger.info("[log] EaDaoImpl.eaInsertStep 끝");
		return eaInsertStep;
	}
	
	@Override
	public List<EaVO_> nextEmno(EaVO_ evo){
		logger.info("[log] EaDaoImpl.nextEmno 시작");
		List<EaVO_> eaNextEmno = null;
		eaNextEmno = session.selectList("nextEmno", evo);
		logger.info("[log] EaDaoImpl.nextEmno 끝");
		return eaNextEmno;
	}
	
	@Override
	public int eaInsertLog(EaVO_ evo){
		logger.info("[log] EaDaoImpl.eaInsertLog 시작");
		int eaInsertLog = 0;
		eaInsertLog = session.insert("eaInsertLog", evo);
		logger.info("[log] EaDaoImpl.eaInsertLog 끝");
		return eaInsertLog;
	}
	
	@Override
	public int eaInsertH(EaVO_ evo){
		logger.info("[log] EaDaoImpl.eaInsertH 시작");
		int eaInsertH = 0;
		eaInsertH = session.insert("eaInsertH", evo);
		logger.info("[log] EaDaoImpl.eaInsertH 끝");
		return eaInsertH;
	}
	
	@Override
	public int eaInsertP(EaVO_ evo){
		logger.info("[log] EaDaoImpl.eaInsertP 시작");
		int eaInsertP = 0;
		eaInsertP = session.insert("eaInsertP", evo);
		logger.info("[log] EaDaoImpl.eaInsertP 끝");
		return eaInsertP;
	}
	
	@Override
	public List<EaVO_> eaMainAppList(EaVO_ evo){
		logger.info("[log] EaDaoImpl.eaMainAppList 시작");
		List<EaVO_> eaMainAppList = null;
		eaMainAppList = session.selectList("eaMainAppList", evo);
		logger.info("[log] EaDaoImpl.eaMainAppList 끝");
		return eaMainAppList;
	}
	
	@Override
	public List<EaVO_> eaMaineaList(EaVO_ evo){
		logger.info("[log] EaDaoImpl.eaMaineaList 시작");
		List<EaVO_> eaMaineaList = null;
		eaMaineaList = session.selectList("eaMaineaList", evo);
		logger.info("[log] EaDaoImpl.eaMaineaList 끝");
		return eaMaineaList;
	}
	
	@Override
	public List<EaVO_> eaMainRJList(EaVO_ evo){
		logger.info("[log] EaDaoImpl.eaMainRJList 시작");
		List<EaVO_> eaMainRJList = null;
		eaMainRJList = session.selectList("eaMainRJList", evo);
		logger.info("[log] EaDaoImpl.eaMainRJList 끝");
		return eaMainRJList;
	}
	
	@Override
	public List<EaVO_> eaMainPGList(EaVO_ evo){
		logger.info("[log] EaDaoImpl.eaMainPGList 시작");
		List<EaVO_> eaMainPGList = null;
		eaMainPGList = session.selectList("eaMainPGList", evo);
		logger.info("[log] EaDaoImpl.eaMainPGList 끝");
		return eaMainPGList;
	}
	
	@Override
	public List<EaVO_> eaMainSTList(EaVO_ evo){
		logger.info("[log] EaDaoImpl.eaMainSTList 시작");
		List<EaVO_> eaMainSTList = null;
		eaMainSTList = session.selectList("eaMainSTList", evo);
		logger.info("[log] EaDaoImpl.eaMainSTList 끝");
		return eaMainSTList;
	}
	
	@Override
	public List<EmInfoVO> searchDeptList(EmInfoVO evo){
		logger.info("[log] EaDaoImpl.SearchDeptList 시작");
		List<EmInfoVO> searchDeptList = null;
		searchDeptList = session.selectList("searchDeptList", evo);
		logger.info("[log] EaDaoImpl.searchDeptList 끝");
		return searchDeptList;
	}
	
	@Override
	public List<EmInfoVO> searchJobList(EmInfoVO evo){
		logger.info("[log] EaDaoImpl.searchJobList 시작");
		List<EmInfoVO> searchJobList = null;
		searchJobList = session.selectList("searchJobList", evo);
		logger.info("[log] EaDaoImpl.searchJobList 끝");
		return searchJobList;
	}
	
	@Override
	public List<EmInfoVO> searchEmnameList(EmInfoVO evo){
		logger.info("[log] EaDaoImpl.searchEmnameList 시작");
		List<EmInfoVO> searchEmnameList = null;
		searchEmnameList = session.selectList("searchEmnameList", evo);
		logger.info("[log] EaDaoImpl.searchEmnameList 끝");
		return searchEmnameList;
	}

	@Override
	public List<EaVO_> eaWriteAll(EaVO_ evo) {
		// TODO Auto-generated method stub
		logger.info("[log] EaDaoImpl.eaWriteAll 시작");
		List<EaVO_> eaWriteAll = null;
		eaWriteAll = session.selectList("eaWriteAll", evo);
		logger.info("[log] EaDaoImpl.eaWriteAll 끝");
		return eaWriteAll;
	}
	
	@Override
	public List<EaVO_> eaWritePG(EaVO_ evo) {
		// TODO Auto-generated method stub
		logger.info("[log] EaDaoImpl.eaWritePG 시작");
		List<EaVO_> eaWritePG = null;
		eaWritePG = session.selectList("eaWritePG", evo);
		logger.info("[log] EaDaoImpl.eaWritePG 끝");
		return eaWritePG;
	}
	
	@Override
	public List<EaVO_> eaWriteEnd(EaVO_ evo) {
		// TODO Auto-generated method stub
		logger.info("[log] EaDaoImpl.eaWriteEnd 시작");
		List<EaVO_> eaWriteEnd = null;
		eaWriteEnd = session.selectList("eaWriteEnd", evo);
		logger.info("[log] EaDaoImpl.eaWriteEnd 끝");
		return eaWriteEnd;
	}
	
	@Override
	public List<EaVO_> eaWriteRJ(EaVO_ evo) {
		// TODO Auto-generated method stub
		logger.info("[log] EaDaoImpl.eaWriteRJ 시작");
		List<EaVO_> eaWriteRJ = null;
		eaWriteRJ = session.selectList("eaWriteRJ", evo);
		logger.info("[log] EaDaoImpl.eaWriteRJ 끝");
		return eaWriteRJ;
	}
	
	@Override
	public List<EaVO_> eaWriteST(EaVO_ evo) {
		// TODO Auto-generated method stub
		logger.info("[log] EaDaoImpl.eaWriteST 시작");
		List<EaVO_> eaWriteST = null;
		eaWriteST = session.selectList("eaWriteST", evo);
		logger.info("[log] EaDaoImpl.eaWriteEnd 끝");
		return eaWriteST;
	}
	
	@Override
	public List<EaVO_> eaListAll(EaVO_ evo){
		logger.info("[log] EaDaoImpl.eaListAll 시작");
		List<EaVO_> eaListAll = null;
		eaListAll = session.selectList("eaListAll", evo);
		logger.info("[log] EaDaoImpl.eaListAll 끝");
		return eaListAll;
	}

	@Override
	public List<EaVO_> eaListSelect(EaVO_ evo) {
		// TODO Auto-generated method stub
		logger.info("[log] EaDaoImpl.eaListSelect 시작");
		List<EaVO_> eaListSelect = null;
		eaListSelect = session.selectList("eaListSelect", evo);
		logger.info("[log] EaDaoImpl.eaListSelect 끝");
		return eaListSelect;
	}
	
	@Override
	public List<EaVO_> eaListStand(EaVO_ evo){
		logger.info("[log] EaDaoImpl.eaListStand 시작");
		List<EaVO_> eaListStand = null;
		eaListStand = session.selectList("eaListStand", evo);
		logger.info("[log] EaDaoImpl.eaListStand 끝");
		return eaListStand;
	}
	
	@Override
	public List<EaVO_> eaFormDetail(EaVO_ evo) {
		// TODO Auto-generated method stub
		logger.info("[log] EaDaoImpl.eaFormDetail 시작");
		List<EaVO_> eaDetail = null;
		eaDetail = session.selectList("eaFormDetail", evo);
		logger.info("[log] EaDaoImpl.eaFormDetail 끝");
		return eaDetail;
	}
	
	@Override
	public EaVO_ chaebunLine(EaVO_ evo){
		logger.info("[log] EaDaoImpl.chaebunLine 시작");
		EaVO_ chaebunLine = null;
		chaebunLine = (EaVO_)session.selectOne("chaebunLine", evo);
		logger.info("[log] EaDaoImpl.chaebunLine 끝");
		return chaebunLine;
	}
	
	@Override
	public EaVO_ chaebunStep(EaVO_ evo){
		logger.info("[log] EaDaoImpl.chaebunStep 시작");
		EaVO_ chaebunStep = null;
		chaebunStep = (EaVO_)session.selectOne("chaebunStep", evo);
		logger.info("[log] EaDaoImpl.chaebunStep 끝");
		return chaebunStep;
	}
	
	@Override
	public EaVO_ chaebunLog(EaVO_ evo) {
		// TODO Auto-generated method stub
		logger.info("[log] EaDaoImpl.chaebunLog 시작");
		EaVO_ chaebunLog = null;
		chaebunLog = (EaVO_)session.selectOne("chaebunLog", evo);
		logger.info("[log] EaDaoImpl.chaebunLog 끝");
		return chaebunLog;
	}
	
	@Override
	public EaVO_ chaebunTable(EaVO_ evo){
		logger.info("[log] EaDaoImpl.chaebunTable 시작");
		EaVO_ chaebunTable = null;
		chaebunTable = (EaVO_)session.selectOne("chaebunTable", evo);
		logger.info("[log] EaDaoImpl.chaebunTable 끝");
		return chaebunTable;
	}
	
	@Override
	public int eaLog1st(EaVO_ evo) {
		// TODO Auto-generated method stub
		logger.info("[log] EaDaoImpl.eaLog1st 시작");
		int eaLog1st = 0;
		eaLog1st = session.insert("eaLog1st", evo);
		logger.info("[log] EaDaoImpl.eaLog1st 끝");
		return eaLog1st;
	}

	@Override
	public int updateTable(EaVO_ evo) {
		// TODO Auto-generated method stub
		logger.info("[log] EaDaoImpl.updateTable 시작");
		int updateTable = 0;
		updateTable = session.update("updateTable", evo);
		logger.info("[log] EaDaoImpl.updateTable 끝");
		return updateTable;
	}

	@Override
	public int eaLog2nd(EaVO_ evo){
		logger.info("[log] EaDaoImpl.eaLog2nd 시작");
		int eaLog2nd = 0;
		eaLog2nd = session.insert("eaLog2nd", evo);
		logger.info("[log] EaDaoImpl.eaLog2nd 끝");
		return eaLog2nd;
	}
}