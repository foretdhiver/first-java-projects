package com.spring.ge.dao;

import java.util.List;

import com.spring.ge.vo.EaVO;
import com.spring.ge.vo.EmInfoVO;

public interface EaDao {
	/* ============ SELECT ============ */
	public List<EaVO> eaSelectAll(EaVO param); //�ش����� �ۼ��� ���� ��ü ��ȸ
	public List<EaVO> eaSelectAllPG(EaVO param); //�ش����� �ۼ��� ǰ��, ���� ���� ��ȸ
	public List<EaVO> eaSelectAllSB(EaVO param); //�ش����� �ۼ��� ��� ���� ��ȸ
	public List<EaVO> eaSelectAllFN(EaVO param); //�ش����� �ۼ��� �������� ���� ��ȸ
	public List<EaVO> eaSelectAllRT(EaVO param); //�ش����� �ۼ��� �ݷ� ���� ��ȸ
	public List<EaVO> eaDetail(EaVO param); //���ڰ���� ��ȸ
	public List<EaVO> eaNextEmno(EaVO param);  // �������̺� ������Ʈ�� �����ڵ� ��ȸ
	public List<EaVO> eaMyAppList(EaVO param); // ���� �����ؾ��� ����Ʈ ��ȸ
	/* ============ ���ڰ��� ������������ SEECT ============ */
	public List<EaVO> eaMainSelectAll(EaVO param); // ��ü����Ʈ
	public List<EaVO> eaMainSelectAllFN(EaVO param); // �Ϸ���
	public List<EaVO> eaMainSelectAllPG(EaVO param); // ������
	public List<EaVO> eaMainSelectAllRJ(EaVO param); // �ݷ���
	public List<EaVO> eaMainApprove(EaVO param); // �����ؾ��� ��������Ʈ
	/* ============ eminfo ȸ�������˻� ============ */
	public List<EmInfoVO> emInfoSelectALL(EmInfoVO param);
	/* ============ INSERT ============ */
	public int eaLineInsert(EaVO param); //������� �Է�
	public int eaStepInsert(EaVO param); //���罺�� �Է�
	public int eaLogInsert(EaVO param); //����α� �Է�
	public int eaTableInsert(EaVO param); //�������̺� �Է�
	/* ============ UPDATE ============ */
	public int eaTableUpdate(EaVO param); //�������̺� ������Ʈ(���º���)
	/* ============ CHAEBUN ============ */
	public List<EaVO> chaebunEAL(EaVO param); //EAL ä��
	public List<EaVO> chaebunEASTNO(EaVO param); //EASTNO ä��
	public List<EaVO> chaebunEALOG(EaVO param); //EALOG ä��
	public List<EaVO> chaebunEA(EaVO param); //EA ä��
	/* ============ LOGIN SESSION ============ */
	public EmInfoVO em_InfoList(EmInfoVO param);
	/* ============ LOGIN SESSION ============ */
	public List<EmInfoVO> getDeptList(EmInfoVO param); // ajax�� ������ ����Ʈ
	public List<EmInfoVO> getJobList(EmInfoVO param); // ajax�� ������ ����Ʈ
	public List<EmInfoVO> getEmnameList(EmInfoVO param); // ajax�� ������ ����Ʈ
} // end of EaDao
