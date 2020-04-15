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

	// �α���
	@Override
	public EmInfoVO emInfoList(EmInfoVO eVO) {
		// TODO Auto-generated method stub
		return session.selectOne("emInfoList", eVO);
	}	// end of EmDaoImpl.emInfoList() �Լ�

	// �ڵ帮��Ʈ
	@Override
	public List<CommonVO> cdList(CommonVO ecvo) {
		// TODO Auto-generated method stub
		return session.selectList("cdList", ecvo);
	}	// end of EmDaoImpl.cdList() �Լ�

	// ä�� (���)
	@Override
	public List<EmInfoVO> emChaebun(EmInfoVO evo) {
		// TODO Auto-generated method stub
		return session.selectList("emChaebun", evo);
	}	// end of EmDaoImpl.emChaebun() �Լ�

	// ä�� (����)
	@Override
	public List<EmPrInfoVO> emPrChaebun(EmPrInfoVO epvo) {
		// TODO Auto-generated method stub
		return session.selectList("emPrChaebun", epvo);
	}	// end of EmDaoImpl.emPrChaebun() �Լ�

	// ��� ��� (EM_INFO)
	@Override
	public int emInsert(EmInfoVO evo) throws Exception {
		// TODO Auto-generated method stub
		return session.insert("emInsert");
	}

	// ������ (EM_PRINFO)
	@Override
	public int emPrInsert(EmPrInfoVO epvo) throws Exception {
		// TODO Auto-generated method stub
		return session.insert("emPrInsert");
	}

	// ������
	@Override
	public List<EmPrInfoVO> emAllSelect(EmPrInfoVO epvo) {
		// TODO Auto-generated method stub
		System.out.println("dao emAllSelect ����");
		return session.selectList("emAllSelect", epvo);
	}

	// ��� ���� ��ȸ
	@Override
	public List<EmPrInfoVO> emDetail(EmPrInfoVO epvo) {
		// TODO Auto-generated method stub
		logger.info("aaa");
		return session.selectList("emDetail", epvo);
	}

	// ��� ���� ����
	@Override
	public int emUpdate(EmInfoVO evo) throws Exception  {
		// TODO Auto-generated method stub
		logger.info(evo.getEmdino());
		System.out.println(evo.getEmdino());
		return session.update("emUpdate", evo);
	}

	// ��� �������� ����
	@Override
	public int emprUpdate(EmPrInfoVO epvo) throws Exception  {
		// TODO Auto-generated method stub
		return session.update("emprUpdate", epvo);
	}

	// ��� ����
	@Override
	public int emDelete(EmPrInfoVO epvo) {
		// TODO Auto-generated method stub
		return session.update("emDelete", epvo);
	}

	// ��� �˻�
	@Override
	public List<EmPrInfoVO> emSearch(EmPrInfoVO epvo) {
		// TODO Auto-generated method stub
		return session.selectList("emSearch", epvo);
	}

	// ä�� (����)
	@Override
	public List<EmHrVO> trChaebun(EmHrVO ehvo) {
		// TODO Auto-generated method stub
		return session.selectList("trChaebun", ehvo);
	}

	// ���� ���� ���
	@Override
	public int trInsert(EmHrVO ehVO) {
		// TODO Auto-generated method stub
		return session.insert("trInsert", ehVO);
	}

	// ���� ���� ���
	@Override
	public List<EmHrVO> trAllSelect(EmHrVO ehvo) {
		// TODO Auto-generated method stub
		return session.selectList("trAllSelect", ehvo);
	}
	
	// ���� ���� �� ��ȸ
	@Override
	public List<EmHrVO> trDetail(EmHrVO ehvo) {
		// TODO Auto-generated method stub
		return session.selectList("trDetail", ehvo);
	}

	// ���� ���� ����
	@Override
	public int trUpdate(EmHrVO ehVO) {
		// TODO Auto-generated method stub
		return session.update("trUpdate", ehVO);
	}

	// ä�� (����)
	@Override
	public List<EmInfoVO> ctChaebun(EmInfoVO evo) {
		// TODO Auto-generated method stub
		return session.selectList("ctChaebun", evo);
	}
	
	// ���� ���
	@Override
	public int ctInsert(EmPrInfoVO epVO) {
		// TODO Auto-generated method stub
		return session.insert("ctInsert", epVO);
	}

	// ��� �ð� ������Ʈ
	@Override
	public int ctIntimeUpdate(EmInfoVO evo) {
		// TODO Auto-generated method stub
		return session.update("ctIntimeUpdate", evo);
	}

	// ��� �ð� ������Ʈ
	@Override
	public int ctOuttimeUpdate(EmInfoVO evo) {
		// TODO Auto-generated method stub
		return session.update("ctOuttimeUpdate", evo);
	}
	
	// ��/��� �ð� Ȯ��
	@Override
	public List<EmInfoVO> ctSelect(EmInfoVO evo) {
		// TODO Auto-generated method stub
		return session.selectList("ctSelect", evo);
	}

	// ��� �ڵ� ������Ʈ
	@Override
	public int ctUpdate(EmInfoVO evo) {
		// TODO Auto-generated method stub
		return session.update("ctUpdate", evo);
	}

	// ��� �ڵ� ������Ʈ
	@Override
	public int ctOutUpdate(EmInfoVO evo) {
		// TODO Auto-generated method stub
		return session.update("ctOutUpdate", evo);
	}

	
	// ���� ���
	@Override
	public List<EmInfoVO> ctAllSelect(EmInfoVO evo) {
		// TODO Auto-generated method stub
		return session.selectList("ctAllSelect", evo);
	}
	
	// ���� �ڷ� �α� ���̺�� �̵�
	@Override
	public int ctToLog() {
		// TODO Auto-generated method stub
		return session.insert("ctToLog");
	}

	// �� ��� ������� ������Ʈ
	@Override
	public int ctNextDay() {
		// TODO Auto-generated method stub
		return session.update("ctNextDay");
	}

	// ä�� (�α����̺�)
	@Override
	public List<EmInfoVO> logChaebun(EmInfoVO evo) {
		// TODO Auto-generated method stub
		return session.selectList("logChaebun", evo);
	}

	// ������ ���� ����
	@Override
	public int ctAdminUpdate(EmInfoVO eVO) {
		// TODO Auto-generated method stub
		return session.update("ctAdminUpdate", eVO);
	}

	// �� ���� ��ȸ
	@Override
	public List<EmPrInfoVO> myPageInfo(EmPrInfoVO epvo) {
		// TODO Auto-generated method stub
		return session.selectList("myPageInfo", epvo);
	}

	// ��й�ȣ ����
	@Override
	public int pwUpdate(EmInfoVO eVo) {
		// TODO Auto-generated method stub
		return session.update("pwUpdate", eVo);
	}

	// ���������� �������
	@Override
	public List<EmHrVO> myPageTr(EmHrVO ehvo) {
		// TODO Auto-generated method stub
		return session.selectList("myPageTr", ehvo);
	}

	// ���������� ���� ��
	@Override
	public List<EmHrVO> myPageTrDetail(EmHrVO ehVO) {
		// TODO Auto-generated method stub
		return session.selectList("myPageTrDetail", ehVO);
	}

	// ä�� (��)
	@Override
	public List<EmHrVO> evChaebun(EmHrVO ehvo) {
		// TODO Auto-generated method stub
		return session.selectList("evChaebun", ehvo);
	}

	// �� ���
	@Override
	public int evInsert(EmHrVO ehVO) {
		// TODO Auto-generated method stub
		return session.insert("evInsert", ehVO);
	}

	// �� ���
	@Override
	public List<EmHrVO> evAllSelect(EmHrVO ehvo) {
		// TODO Auto-generated method stub
		return session.selectList("evAllSelect", ehvo);
	}

	// ��� ������ �α� ���̺�� �̵�
	@Override
	public int goToLog(EmInfoVO evo) {
		// TODO Auto-generated method stub
		return session.insert("goToLog", evo);
	}

	// �� �� ��ȸ
	@Override
	public List<EmHrVO> evDetail(EmHrVO ehvo) {
		// TODO Auto-generated method stub
		return session.selectList("evDetail", ehvo);
		
	}

	// �� ����
	@Override
	public int evUpdate(EmHrVO ehvo) {
		// TODO Auto-generated method stub
		return session.update("evUpdate", ehvo);
	}

	// ���� �Ⱓ ��ȸ
	@Override
	public List<EmInfoVO> ctSearch(EmInfoVO evo) {
		// TODO Auto-generated method stub
		return session.selectList("ctSearch", evo);
	}



}
