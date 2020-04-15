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

	// �α���
	@Override
	public EmInfoVO emInfoList(EmInfoVO _eVO) {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl - emInfoList(�α���) ����");
		
		EmInfoVO eVO = null;
		eVO = new EmInfoVO();
		
		eVO = emDao.emInfoList(_eVO);
		
		logger.info("EmServiceImpl - emInfoList(�α���) ����");
		return eVO;
	}	// end of EmServiceImpl.emInsert() �Լ�

	// �ڵ帮��Ʈ
	@Override
	public List<CommonVO> cdList(CommonVO ecvo) {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl - cdList(�ڵ帮��Ʈ) ����");
		
		List<CommonVO> list = null;
		list = emDao.cdList(ecvo);
		
		logger.info("EmServiceImpl - cdList(�ڵ帮��Ʈ) ����");
		return list;
	}	// end of EmServiceImpl.cdList() �Լ�

	// ä�� (���)
	@Override
	public List<EmInfoVO> emChaebun(EmInfoVO evo) {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl.emChaebun() �Լ� ����");
		
		List<EmInfoVO> list = null;
		list = emDao.emChaebun(evo);
		
		logger.info("EmServiceImpl.emChaebun() �Լ� ��");
		return list;
	}	// end of EmServiceImpl.emChaebun() �Լ�
	
	// ä�� (����)
	@Override
	public List<EmPrInfoVO> emPrChaebun(EmPrInfoVO epvo) {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl.emPrChaebun() �Լ� ����");
		
		List<EmPrInfoVO> list = null;
		list = emDao.emPrChaebun(epvo);
		
		logger.info("EmServiceImpl.emPrChaebun() �Լ� ��");
		return list;
	}	// end of EmServiceImpl.emPrChaebun() �Լ�

	// ��� ��� insert �Լ�(EM_INFO ���̺�)
	@Override
	public int emInsert(EmInfoVO evo) throws Exception {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl - emInsert(EM_INFO ���̺�) ����");
		
		int result = 0;
		result = emDao.emInsert(evo);
		
		return result;
	}	// end of EmServiceImpl.emInsert() �Լ�

	// ������  insert �Լ�(EM_PRINFO ���̺�)
	@Override
	public int emPrInsert(EmPrInfoVO epvo) throws Exception {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl - emPrInsert(EM_PRINFO ���̺�) ����");
		
		int result = 0;
		result = emDao.emPrInsert(epvo);
		
		return result;
	}	// end of EmServiceImpl.emPrInsert() �Լ�

	// ������
	@Override
	public List<EmPrInfoVO> emAllSelect(EmPrInfoVO epvo) {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl - emAllSelect ����");
		
		List<EmPrInfoVO> asList = null;
		asList = emDao.emAllSelect(epvo);
		
		return asList;
	}	// end of EmServiceImpl.emAllSelect() �Լ�

	// ��� ���� ��ȸ
	@Override
	public List<EmPrInfoVO> emDetail(EmPrInfoVO epvo) {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl.emDetail() �Լ� ����");
		
		List<EmPrInfoVO> sList = null;
		sList = emDao.emDetail(epvo);
		System.out.println("(log) sList.size >>> : " + sList.size());
		
		logger.info("EmServiceImpl.emDetail() �Լ� ��");
		return sList;
	}	// end of EmServiceImpl.emDetail() �Լ�

	// ��� ���� ����
	@Override
	public int emUpdate(EmInfoVO evo) throws Exception  {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl.emUpdate() �Լ� ����");
		
		int result = 0;
		result = emDao.emUpdate(evo);
		
		logger.info("EmServiceImpl.emUpdate() �Լ� ��");
		return result;
	}	// end of EmServiceImpl.emUpdate() �Լ�

	// ��� ���� ���� ����
	@Override
	public int emprUpdate(EmPrInfoVO epvo) throws Exception  {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl.emprUpdate() �Լ� ����");
		
		int result = 0;
		result = emDao.emprUpdate(epvo);
		
		logger.info("EmServiceImpl.emprUpdate() �Լ� ��");
		return result;
	}	// end of EmServiceImpl.emprUpdate() �Լ�

	// ��� ����
	@Override
	public int emDelete(EmPrInfoVO epvo) {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl.emDelete() �Լ� ����");
		
		int result = 0;
		result = emDao.emDelete(epvo);
		
		logger.info("EmServiceImpl.emDelete() �Լ� ��");
		return result;
	}	// end of EmServiceImpl.emDelete() �Լ�

	// ��� �˻�
	@Override
	public List<EmPrInfoVO> emSearch(EmPrInfoVO epvo) {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl.emSearch() �Լ� ����");
		
		List<EmPrInfoVO> scList = null;
		scList = emDao.emSearch(epvo);
		
		logger.info("EmServiceImpl.emSearch() �Լ� ����");
		return scList;
	}	// end of EmServiceImpl.emSearch() �Լ�

	// ä�� (����)
	@Override
	public List<EmHrVO> trChaebun(EmHrVO ehvo) {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl.trChaebun() �Լ� ����");
		
		List<EmHrVO> cList = null;
		cList = emDao.trChaebun(ehvo);
		
		logger.info("EmServiceImpl.trChaebun() �Լ� ��");
		return cList;
	}	// end of EmServiceImpl.trChaebun() �Լ�

	// ���� ���� ���
	@Override
	public int trInsert(EmHrVO ehVO) {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl.trInsert() �Լ� ����");
		
		int result = 0;
		result = emDao.trInsert(ehVO);
		
		logger.info("EmServiceImpl.trInsert() �Լ� ��");
		return result;
	}	// end of EmServiceImpl.trInsert() �Լ�

	// ���� ���� ���
	@Override
	public List<EmHrVO> trAllSelect(EmHrVO ehvo) {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl.trAllSelect() �Լ� ����");
		
		List<EmHrVO> trList = null;
		trList = emDao.trAllSelect(ehvo);
		
		logger.info("EmServiceImpl.trAllSelect() �Լ� ��");
		return trList;
	}	// end of EmServiceImpl.trAllSelect() �Լ�

	// ���� ���� �� ��ȸ
	@Override
	public List<EmHrVO> trDetail(EmHrVO ehvo) {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl.trDetail() �Լ� ����");
		
		List<EmHrVO> trDList = null;
		trDList = emDao.trDetail(ehvo);
		
		logger.info("EmServiceImpl.trDetail() �Լ� ��");
		return trDList;
	}	// end of EmServiceImpl.trDetail() �Լ�

	// ���� ���� ����
	@Override
	public int trUpdate(EmHrVO ehVO) {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl.trUpdate() �Լ� ����");
		
		int result = 0;
		result = emDao.trUpdate(ehVO);
		
		logger.info("EmServiceImpl.trUpdate() �Լ� ��");
		return result;
	}	// end of EmServiceImpl.trUpdate() �Լ�

	// ä�� (����)
	@Override
	public List<EmInfoVO> ctChaebun(EmInfoVO evo) {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl.ctChaebun() �Լ� ����");
		
		List<EmInfoVO> list = null;
		list = emDao.ctChaebun(evo);
		
		logger.info("EmServiceImpl.ctChaebun() �Լ� ��");
		return list;
	}	// end of EmServiceImpl.ctChaebun() �Լ�
	
	// ���� ���
	@Override
	public int ctInsert(EmPrInfoVO epVO) {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl.ctInsert() �Լ� ����");
		
		int result = 0;
		result = emDao.ctInsert(epVO);
		
		logger.info("EmServiceImpl.ctInsert() �Լ� ��");
		return result;
	}	// end of EmServiceImpl.ctInsert() �Լ�

	// ��� �ð� ������Ʈ
	@Override
	public int ctIntimeUpdate(EmInfoVO evo) {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl.ctIntimeUpdate() �Լ� ����");
		
		int result = 0;
		result = emDao.ctIntimeUpdate(evo);
		
		logger.info("EmServiceImpl.ctIntimeUpdate() �Լ� ��");
		return result;
	}	// end of EmServiceImpl.ctInUpdate() �Լ�
	
	// ��� �ð� ������Ʈ
	@Override
	public int ctOuttimeUpdate(EmInfoVO evo) {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl.ctOuttimeUpdate() �Լ� ����");
		
		int result = 0;
		result = emDao.ctOuttimeUpdate(evo);
		
		logger.info("EmServiceImpl.ctOuttimeUpdate() �Լ� ��");
		return result;
	}	// end of EmServiceImpl.ctOuttimeUpdate() �Լ�

	// ��/��� �ð� Ȯ��
	@Override
	public List<EmInfoVO> ctSelect(EmInfoVO evo) {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl.ctSelect() �Լ� ����");
		
		List<EmInfoVO> ctSelect = null;
		ctSelect = emDao.ctSelect(evo);
		
		logger.info("EmServiceImpl.ctSelect() �Լ� ��");
		return ctSelect;
	}	// end of EmServiceImpl.ctSelect() �Լ�

	// ��� �ڵ� ������Ʈ
	@Override
	public int ctUpdate(EmInfoVO evo) {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl.ctInUpdate() �Լ� ����");
		
		int result = 0;
		result = emDao.ctUpdate(evo);
		
		logger.info("EmServiceImpl.ctUpdate() �Լ� ��");
		return result;
	}

	// ��� �ڵ� ������Ʈ
	@Override
	public int ctOutUpdate(EmInfoVO evo) {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl.ctOutUpdate() �Լ� ����");
		
		int result = 0;
		result = emDao.ctOutUpdate(evo);
		
		logger.info("EmServiceImpl.ctOutUpdate() �Լ� ��");
		return result;
	}

	// ���� ���
	@Override
	public List<EmInfoVO> ctAllSelect(EmInfoVO evo) {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl.ctAllSelect() �Լ� ����");
		
		List<EmInfoVO> ctList = null;
		ctList = emDao.ctAllSelect(evo);
		
		logger.info("EmServiceImpl.ctAllSelect() �Լ� ��");
		return ctList;
	}	// end of EmServiceImpl.ctOuttimeUpdate() �Լ�

	// ���� �ڷ� �α� ���̺�� �̵�
	@Override
	public int ctToLog() {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl.ctToLog() �Լ� ����");
		
		int result = 0;
		result = emDao.ctToLog();
		
		logger.info("EmServiceImpl.ctToLog() �Լ� ��");
		return result;
	}	// end of EmServiceImpl.ctOutUpdate() �Լ�

	// �� ��� ������� ������Ʈ
	@Override
	public int ctNextDay() {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl.ctNextDay() �Լ� ����");
		
		int result = 0;
		result = emDao.ctNextDay();
		
		logger.info("EmServiceImpl.ctNextDay() �Լ� ��");
		return result;
	}	// end of EmServiceImpl.ctNextDay() �Լ�

	// ä�� (�α����̺�)
	@Override
	public List<EmInfoVO> logChaebun(EmInfoVO evo) {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl.logChaebun() �Լ� ����");
		
		List<EmInfoVO> logList = null;
		logList = emDao.ctAllSelect(evo);
		
		logger.info("EmServiceImpl.logChaebun() �Լ� ��");
		return logList;
	}	// end of EmServiceImpl.logChaebun() �Լ�

	// ������ ���� ����
	@Override
	public int ctAdminUpdate(EmInfoVO eVO) {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl.ctAdminUpdate() �Լ� ����");
		
		int result = 0;
		result = emDao.ctAdminUpdate(eVO);
		
		logger.info("EmServiceImpl.ctAdminUpdate() �Լ� ��");
		return result;
	}	// end of EmServiceImpl.logChaebun() �Լ�

	// �� ���� ��ȸ
	@Override
	public List<EmPrInfoVO> myPageInfo(EmPrInfoVO epvo) {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl.myPageInfo() �Լ� ����");
		
		List<EmPrInfoVO> myList = null;
		myList = emDao.myPageInfo(epvo);
		
		logger.info("EmServiceImpl.myPageInfo() �Լ� ��");
		return myList;
	}	// end of EmServiceImpl.myPageInfo() �Լ�

	// �׽�Ʈ~!~!~!~!~!~!~!~!~!~~!~!~!~!~~!~!~~
	@Override
	public String test() {
		// TODO Auto-generated method stub
		String aa = "EmService�� �����";
		return aa;
	}

	// ��й�ȣ ����
	@Override
	public int pwUpdate(EmInfoVO eVo) {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl.pwUpdate() �Լ� ����");
		
		int result = 0;
		result = emDao.pwUpdate(eVo);
		
		logger.info("EmServiceImpl.pwUpdate() �Լ� ��");
		return result;
	}	// end of EmServiceImpl.pwUpdate() �Լ�
	
	// ���������� ����
	@Override
	public List<EmHrVO> myPageTr(EmHrVO ehvo) {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl.myPageTr() �Լ� ����");
		
		List<EmHrVO> mtList = null;
		mtList = emDao.myPageTr(ehvo);
		
		logger.info("EmServiceImpl.myPageTr() �Լ� ��");
		return mtList;
	}	// end of EmServiceImpl.myPageTr() �Լ�

	// ���������� ���� ��
	@Override
	public List<EmHrVO> myPageTrDetail(EmHrVO ehVO) {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl.myPageTrDetail() �Լ� ����");
		
		List<EmHrVO> mtdList = null;
		mtdList = emDao.myPageTrDetail(ehVO);
		
		logger.info("EmServiceImpl.myPageTrDetail() �Լ� ��");
		return mtdList;
	}	// end of EmServiceImpl.myPageTrDetail() �Լ�

	// �� ä��
	@Override
	public List<EmHrVO> evChaebun(EmHrVO ehvo) {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl.evChaebun() �Լ� ����");
		
		List<EmHrVO> cList = null;
		cList = emDao.evChaebun(ehvo);
		
		logger.info("EmServiceImpl.evChaebun() �Լ� ��");
		return cList;
	}	// end of EmServiceImpl.evChaebun() �Լ�

	// �� ���
	@Override
	public int evInsert(EmHrVO ehVO) {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl.evInsert() �Լ� ����");
		
		int result = 0;
		result = emDao.evInsert(ehVO);
		
		logger.info("EmServiceImpl.evInsert() �Լ� ��");
		return result;
	}	// end of EmServiceImpl.evInsert() �Լ�

	// �� ���
	@Override
	public List<EmHrVO> evAllSelect(EmHrVO ehvo) {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl.evAllSelect() �Լ� ����");
		
		List<EmHrVO> evList = null;
		evList = emDao.evAllSelect(ehvo);
		
		logger.info("EmServiceImpl.evAllSelect() �Լ� ��");
		return evList;
	}

	// ��� ������ �α� ���̺�� �̵�
	@Override
	public int goToLog(EmInfoVO evo) {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl.goToLog() �Լ� ����");
		
		int result = 0;
		result = emDao.goToLog(evo);
		
		logger.info("EmServiceImpl.goToLog() �Լ� ��");
		return result;
	}

	// �� �� ��ȸ
	@Override
	public List<EmHrVO> evDetail(EmHrVO ehvo) {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl.evDetail() �Լ� ����");
		
		List<EmHrVO> evdList = null;
		evdList = emDao.evAllSelect(ehvo);
		
		logger.info("EmServiceImpl.evDetail() �Լ� ��");
		return evdList;
	}

	// �� ����
	@Override
	public int evUpdate(EmHrVO ehvo) {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl.evUpdate() �Լ� ����");
		
		int result = 0;
		result = emDao.evUpdate(ehvo);
		
		logger.info("EmServiceImpl.evUpdate() �Լ� ��");
		return result;
	}

	
	// ���� �Ⱓ ��ȸ
	@Override
	public List<EmInfoVO> ctSearch(EmInfoVO evo) {
		// TODO Auto-generated method stub
		logger.info("EmServiceImpl.ctSearch() �Լ� ����");
		
		List<EmInfoVO> list = null;
		list = emDao.ctSearch(evo);
		
		logger.info("EmServiceImpl.ctSearch() �Լ� ��");
		return list;
	}	// end of EmServiceImpl.ctSearch() �Լ�


}
