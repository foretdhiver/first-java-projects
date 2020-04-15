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
		logger.info("[log] EaDaoImpl.emInfoList ����");
		System.out.println("id " + evo.getEmid());
		System.out.println("pw " + evo.getEmpw());
		EmInfoVO emInfoList = (EmInfoVO) session.selectOne("emInfoList", evo);
		logger.info("[log] EaDaoImpl.emInfoList ��");
		return emInfoList;
	}
	
	@Override
	public int eaInsertLine(EaVO_ evo){
		logger.info("[log] EaDaoImpl.eaInsertLine ����");
		int eaInsertLine = 0;
		eaInsertLine = session.insert("eaInsertLine", evo);
		logger.info("[log] EaDaoImpl.eaInsertLine ��");
		return eaInsertLine;
	}
	
	@Override
	public int eaInsertStep(EaVO_ evo){
		logger.info("[log] EaDaoImpl.eaInsertStep ����");
		int eaInsertStep = 0;
		eaInsertStep = session.insert("eaInsertStep", evo);
		logger.info("[log] EaDaoImpl.eaInsertStep ��");
		return eaInsertStep;
	}
	
	@Override
	public List<EaVO_> nextEmno(EaVO_ evo){
		logger.info("[log] EaDaoImpl.nextEmno ����");
		List<EaVO_> eaNextEmno = null;
		eaNextEmno = session.selectList("nextEmno", evo);
		logger.info("[log] EaDaoImpl.nextEmno ��");
		return eaNextEmno;
	}
	
	@Override
	public int eaInsertLog(EaVO_ evo){
		logger.info("[log] EaDaoImpl.eaInsertLog ����");
		int eaInsertLog = 0;
		eaInsertLog = session.insert("eaInsertLog", evo);
		logger.info("[log] EaDaoImpl.eaInsertLog ��");
		return eaInsertLog;
	}
	
	@Override
	public int eaInsertH(EaVO_ evo){
		logger.info("[log] EaDaoImpl.eaInsertH ����");
		int eaInsertH = 0;
		eaInsertH = session.insert("eaInsertH", evo);
		logger.info("[log] EaDaoImpl.eaInsertH ��");
		return eaInsertH;
	}
	
	@Override
	public int eaInsertP(EaVO_ evo){
		logger.info("[log] EaDaoImpl.eaInsertP ����");
		int eaInsertP = 0;
		eaInsertP = session.insert("eaInsertP", evo);
		logger.info("[log] EaDaoImpl.eaInsertP ��");
		return eaInsertP;
	}
	
	@Override
	public List<EaVO_> eaMainAppList(EaVO_ evo){
		logger.info("[log] EaDaoImpl.eaMainAppList ����");
		List<EaVO_> eaMainAppList = null;
		eaMainAppList = session.selectList("eaMainAppList", evo);
		logger.info("[log] EaDaoImpl.eaMainAppList ��");
		return eaMainAppList;
	}
	
	@Override
	public List<EaVO_> eaMaineaList(EaVO_ evo){
		logger.info("[log] EaDaoImpl.eaMaineaList ����");
		List<EaVO_> eaMaineaList = null;
		eaMaineaList = session.selectList("eaMaineaList", evo);
		logger.info("[log] EaDaoImpl.eaMaineaList ��");
		return eaMaineaList;
	}
	
	@Override
	public List<EaVO_> eaMainRJList(EaVO_ evo){
		logger.info("[log] EaDaoImpl.eaMainRJList ����");
		List<EaVO_> eaMainRJList = null;
		eaMainRJList = session.selectList("eaMainRJList", evo);
		logger.info("[log] EaDaoImpl.eaMainRJList ��");
		return eaMainRJList;
	}
	
	@Override
	public List<EaVO_> eaMainPGList(EaVO_ evo){
		logger.info("[log] EaDaoImpl.eaMainPGList ����");
		List<EaVO_> eaMainPGList = null;
		eaMainPGList = session.selectList("eaMainPGList", evo);
		logger.info("[log] EaDaoImpl.eaMainPGList ��");
		return eaMainPGList;
	}
	
	@Override
	public List<EaVO_> eaMainSTList(EaVO_ evo){
		logger.info("[log] EaDaoImpl.eaMainSTList ����");
		List<EaVO_> eaMainSTList = null;
		eaMainSTList = session.selectList("eaMainSTList", evo);
		logger.info("[log] EaDaoImpl.eaMainSTList ��");
		return eaMainSTList;
	}
	
	@Override
	public List<EmInfoVO> searchDeptList(EmInfoVO evo){
		logger.info("[log] EaDaoImpl.SearchDeptList ����");
		List<EmInfoVO> searchDeptList = null;
		searchDeptList = session.selectList("searchDeptList", evo);
		logger.info("[log] EaDaoImpl.searchDeptList ��");
		return searchDeptList;
	}
	
	@Override
	public List<EmInfoVO> searchJobList(EmInfoVO evo){
		logger.info("[log] EaDaoImpl.searchJobList ����");
		List<EmInfoVO> searchJobList = null;
		searchJobList = session.selectList("searchJobList", evo);
		logger.info("[log] EaDaoImpl.searchJobList ��");
		return searchJobList;
	}
	
	@Override
	public List<EmInfoVO> searchEmnameList(EmInfoVO evo){
		logger.info("[log] EaDaoImpl.searchEmnameList ����");
		List<EmInfoVO> searchEmnameList = null;
		searchEmnameList = session.selectList("searchEmnameList", evo);
		logger.info("[log] EaDaoImpl.searchEmnameList ��");
		return searchEmnameList;
	}

	@Override
	public List<EaVO_> eaWriteAll(EaVO_ evo) {
		// TODO Auto-generated method stub
		logger.info("[log] EaDaoImpl.eaWriteAll ����");
		List<EaVO_> eaWriteAll = null;
		eaWriteAll = session.selectList("eaWriteAll", evo);
		logger.info("[log] EaDaoImpl.eaWriteAll ��");
		return eaWriteAll;
	}
	
	@Override
	public List<EaVO_> eaWritePG(EaVO_ evo) {
		// TODO Auto-generated method stub
		logger.info("[log] EaDaoImpl.eaWritePG ����");
		List<EaVO_> eaWritePG = null;
		eaWritePG = session.selectList("eaWritePG", evo);
		logger.info("[log] EaDaoImpl.eaWritePG ��");
		return eaWritePG;
	}
	
	@Override
	public List<EaVO_> eaWriteEnd(EaVO_ evo) {
		// TODO Auto-generated method stub
		logger.info("[log] EaDaoImpl.eaWriteEnd ����");
		List<EaVO_> eaWriteEnd = null;
		eaWriteEnd = session.selectList("eaWriteEnd", evo);
		logger.info("[log] EaDaoImpl.eaWriteEnd ��");
		return eaWriteEnd;
	}
	
	@Override
	public List<EaVO_> eaWriteRJ(EaVO_ evo) {
		// TODO Auto-generated method stub
		logger.info("[log] EaDaoImpl.eaWriteRJ ����");
		List<EaVO_> eaWriteRJ = null;
		eaWriteRJ = session.selectList("eaWriteRJ", evo);
		logger.info("[log] EaDaoImpl.eaWriteRJ ��");
		return eaWriteRJ;
	}
	
	@Override
	public List<EaVO_> eaWriteST(EaVO_ evo) {
		// TODO Auto-generated method stub
		logger.info("[log] EaDaoImpl.eaWriteST ����");
		List<EaVO_> eaWriteST = null;
		eaWriteST = session.selectList("eaWriteST", evo);
		logger.info("[log] EaDaoImpl.eaWriteEnd ��");
		return eaWriteST;
	}
	
	@Override
	public List<EaVO_> eaListAll(EaVO_ evo){
		logger.info("[log] EaDaoImpl.eaListAll ����");
		List<EaVO_> eaListAll = null;
		eaListAll = session.selectList("eaListAll", evo);
		logger.info("[log] EaDaoImpl.eaListAll ��");
		return eaListAll;
	}

	@Override
	public List<EaVO_> eaListSelect(EaVO_ evo) {
		// TODO Auto-generated method stub
		logger.info("[log] EaDaoImpl.eaListSelect ����");
		List<EaVO_> eaListSelect = null;
		eaListSelect = session.selectList("eaListSelect", evo);
		logger.info("[log] EaDaoImpl.eaListSelect ��");
		return eaListSelect;
	}
	
	@Override
	public List<EaVO_> eaListStand(EaVO_ evo){
		logger.info("[log] EaDaoImpl.eaListStand ����");
		List<EaVO_> eaListStand = null;
		eaListStand = session.selectList("eaListStand", evo);
		logger.info("[log] EaDaoImpl.eaListStand ��");
		return eaListStand;
	}
	
	@Override
	public List<EaVO_> eaFormDetail(EaVO_ evo) {
		// TODO Auto-generated method stub
		logger.info("[log] EaDaoImpl.eaFormDetail ����");
		List<EaVO_> eaDetail = null;
		eaDetail = session.selectList("eaFormDetail", evo);
		logger.info("[log] EaDaoImpl.eaFormDetail ��");
		return eaDetail;
	}
	
	@Override
	public EaVO_ chaebunLine(EaVO_ evo){
		logger.info("[log] EaDaoImpl.chaebunLine ����");
		EaVO_ chaebunLine = null;
		chaebunLine = (EaVO_)session.selectOne("chaebunLine", evo);
		logger.info("[log] EaDaoImpl.chaebunLine ��");
		return chaebunLine;
	}
	
	@Override
	public EaVO_ chaebunStep(EaVO_ evo){
		logger.info("[log] EaDaoImpl.chaebunStep ����");
		EaVO_ chaebunStep = null;
		chaebunStep = (EaVO_)session.selectOne("chaebunStep", evo);
		logger.info("[log] EaDaoImpl.chaebunStep ��");
		return chaebunStep;
	}
	
	@Override
	public EaVO_ chaebunLog(EaVO_ evo) {
		// TODO Auto-generated method stub
		logger.info("[log] EaDaoImpl.chaebunLog ����");
		EaVO_ chaebunLog = null;
		chaebunLog = (EaVO_)session.selectOne("chaebunLog", evo);
		logger.info("[log] EaDaoImpl.chaebunLog ��");
		return chaebunLog;
	}
	
	@Override
	public EaVO_ chaebunTable(EaVO_ evo){
		logger.info("[log] EaDaoImpl.chaebunTable ����");
		EaVO_ chaebunTable = null;
		chaebunTable = (EaVO_)session.selectOne("chaebunTable", evo);
		logger.info("[log] EaDaoImpl.chaebunTable ��");
		return chaebunTable;
	}
	
	@Override
	public int eaLog1st(EaVO_ evo) {
		// TODO Auto-generated method stub
		logger.info("[log] EaDaoImpl.eaLog1st ����");
		int eaLog1st = 0;
		eaLog1st = session.insert("eaLog1st", evo);
		logger.info("[log] EaDaoImpl.eaLog1st ��");
		return eaLog1st;
	}

	@Override
	public int updateTable(EaVO_ evo) {
		// TODO Auto-generated method stub
		logger.info("[log] EaDaoImpl.updateTable ����");
		int updateTable = 0;
		updateTable = session.update("updateTable", evo);
		logger.info("[log] EaDaoImpl.updateTable ��");
		return updateTable;
	}

	@Override
	public int eaLog2nd(EaVO_ evo){
		logger.info("[log] EaDaoImpl.eaLog2nd ����");
		int eaLog2nd = 0;
		eaLog2nd = session.insert("eaLog2nd", evo);
		logger.info("[log] EaDaoImpl.eaLog2nd ��");
		return eaLog2nd;
	}
}