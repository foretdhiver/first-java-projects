package com.spring.ge.service;

import java.util.List;

import com.spring.ge.vo.CommonVO;
import com.spring.ge.vo.EmHrVO;
import com.spring.ge.vo.EmInfoVO;
import com.spring.ge.vo.EmPrInfoVO;

public interface EmService {
	
	// �α���
	public EmInfoVO emInfoList(EmInfoVO eVO);
	
	// �ڵ帮��Ʈ
	public List<CommonVO> cdList(CommonVO ecvo);
	
	// ------------------------ ��� ------------------------ 
	
	// ä�� (���)
	public List<EmInfoVO> emChaebun(EmInfoVO evo);
	
	// ä�� (����)
	public List<EmPrInfoVO> emPrChaebun(EmPrInfoVO epvo);
	
	// ��� ���(EM_INFO ���̺�)
	public int emInsert(EmInfoVO evo) throws Exception;
	
	// ������(EM_PRINFO ���̺�)
	public int emPrInsert(EmPrInfoVO epvo) throws Exception;
	
	// ��� ���
	public List<EmPrInfoVO> emAllSelect(EmPrInfoVO epvo);
	
	// ��� ���� ��ȸ
	public List<EmPrInfoVO> emDetail(EmPrInfoVO epvo);
	
	// ��� ���� ����
	public int emUpdate(EmInfoVO evo) throws Exception;
	
	// ��� ���� ���� ����
	public int emprUpdate(EmPrInfoVO epvo) throws Exception;
	
	// ��� ����
	public int emDelete(EmPrInfoVO epvo);
	
	// ��� �˻�
	public List<EmPrInfoVO> emSearch(EmPrInfoVO epvo);
	
	// ------------------------ ���� ------------------------ 
	
	// ä�� (��������)
	public List<EmHrVO> trChaebun(EmHrVO ehvo);
	
	// ���� ���� ���
	public int trInsert(EmHrVO ehVO);

	// ���� ���� ���
	public List<EmHrVO> trAllSelect(EmHrVO ehvo);
	
	// ���� ���� �� ��ȸ
	public List<EmHrVO> trDetail(EmHrVO ehvo);
		
	// ���� ���� ����
	public int trUpdate(EmHrVO ehVO);
	
	// ------------------------ ���� ------------------------
	
	// ä�� (����)
	public List<EmInfoVO> ctChaebun(EmInfoVO evo);
	
	// ���� ���
	public int ctInsert(EmPrInfoVO epVO);
	
	// ��ٽð� ������Ʈ
	public int ctIntimeUpdate(EmInfoVO evo);
	
	// ��ٽð� ������Ʈ
	public int ctOuttimeUpdate(EmInfoVO evo);
	
	// ��/��� �ð� Ȯ��
	public List<EmInfoVO> ctSelect(EmInfoVO evo);
	
	// ��� �ڵ� ������Ʈ
	public int ctUpdate(EmInfoVO evo);
	
	// ��� �ڵ� ������Ʈ
	public int ctOutUpdate(EmInfoVO evo);
	
	// ���� ���
	public List<EmInfoVO> ctAllSelect(EmInfoVO evo);
	
	// ���� �Ⱓ ��ȸ
	public List<EmInfoVO> ctSearch(EmInfoVO evo);
	
	// ���� �ڷ� �α� ���̺�� �̵�
	public int ctToLog();
	
	// �� ��� ������� ������Ʈ
	public int ctNextDay();
	
	// ä�� (�α����̺�)
	public List<EmInfoVO> logChaebun(EmInfoVO evo);
	
	// ������ ���� ����
	public int ctAdminUpdate(EmInfoVO eVO);
	
	// ��� ������ �α� ���̺�� �̵�
	public int goToLog(EmInfoVO evo);
	
	// ------------------------ ���������� ------------------------
	
	// �� ���� ��ȸ
	public List<EmPrInfoVO> myPageInfo(EmPrInfoVO epvo);
	
	// ��й�ȣ ����
	public int pwUpdate(EmInfoVO eVo);
	
	// ���������� �������
	public List<EmHrVO> myPageTr(EmHrVO ehvo);
	
	// ���������� ������
	public List<EmHrVO> myPageTrDetail(EmHrVO ehVO);
	
	// ------------------------ �� ------------------------
	
	// �� ä��
	public List<EmHrVO> evChaebun(EmHrVO ehvo);
	
	// �� ���
	public int evInsert(EmHrVO ehVO);
	
	// �� ���
	public List<EmHrVO> evAllSelect(EmHrVO ehvo);
	
	// �� ��ȸ
	public List<EmHrVO> evDetail(EmHrVO ehvo);
	
	// �� ����
	public int evUpdate(EmHrVO ehvo);
	
	public String test();
	
}	// end of EmSerVice interface
