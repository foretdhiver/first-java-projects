package com.spring.ge.service;

import java.util.List;

import com.spring.ge.vo.EaVO_;
import com.spring.ge.vo.EmInfoVO;

public interface EaService_ {
	
	// ������ ����ϱ� ���� �α���
	public EmInfoVO emInfoList(EmInfoVO evo);
	
	// �� �ۼ��� ���
	public int eaInsertLine(EaVO_ evo);
	public int eaInsertStep(EaVO_ evo);
	public int eaInsertLog(EaVO_ evo);
	public List<EaVO_> nextEmno(EaVO_ evo);
	public int eaInsertH(EaVO_ evo);
	public int eaInsertP(EaVO_ evo);
	
	// ��ȸ	
	// ���ο��� ������ ����Ʈ
	public List<EaVO_> eaMainAppList(EaVO_ evo);
	public List<EaVO_> eaMainRJList(EaVO_ evo);
	public List<EaVO_> eaMainPGList(EaVO_ evo);
	public List<EaVO_> eaMainSTList(EaVO_ evo);
	public List<EaVO_> eaMaineaList(EaVO_ evo);
	
	// �� �ۼ� �� ajax���� ���� ��ȸ
	public List<EmInfoVO> searchDeptList(EmInfoVO evo);
	public List<EmInfoVO> searchJobList(EmInfoVO evo);
	public List<EmInfoVO> searchEmnameList(EmInfoVO evo);
	
	// ���� �ۼ��� ����Ʈ
	public List<EaVO_> eaWriteAll(EaVO_ evo);
	public List<EaVO_> eaWritePG(EaVO_ evo);
	public List<EaVO_> eaWriteEnd(EaVO_ evo);
	public List<EaVO_> eaWriteRJ(EaVO_ evo);
	public List<EaVO_> eaWriteST(EaVO_ evo);
	
	// ���� ������ ����Ʈ
	public List<EaVO_> eaListAll(EaVO_ evo);
	public List<EaVO_> eaListSelect(EaVO_ evo);
	public List<EaVO_> eaListStand(EaVO_ evo);
	
	public List<EaVO_> eaFormDetail(EaVO_ evo);
	
	// ä�� ģ����
	public EaVO_ chaebunLine(EaVO_ evo);
	public EaVO_ chaebunStep(EaVO_ evo);
	public EaVO_ chaebunLog(EaVO_ evo);
	public EaVO_ chaebunTable(EaVO_ evo);
	
	// ��ġ�� ���߿� �߰�
	
	// �߰������� ������Ʈ
	public int eaLog1st(EaVO_ evo);
	public int eaLog2nd(EaVO_ evo);
	public int updateTable(EaVO_ evo);

}