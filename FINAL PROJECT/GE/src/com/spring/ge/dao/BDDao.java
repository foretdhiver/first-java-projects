package com.spring.ge.dao;

import java.util.List;

import com.spring.ge.vo.BDVO;

public interface BDDao {
	//��ȸ
	public List<BDVO> boardDepartmentList(BDVO bdvo);
	//detail��ȸ
	public BDVO boardDepartmentDetail(BDVO bdvo);
	//ä��
	public BDVO boardDepartmentChebun(BDVO bdvo);
	//�Է�
	public int boardDepartmentInsert(BDVO bdvo);
	//����
	public int boardDepartmentDelete(BDVO bdvo);
	//����
	public int boardDepartmentUpdate(BDVO bdvo);
	//��ȸ�� ����
	public int boardDepartmentViewCntUp(BDVO bdvo);
	//���ο��� �������� �ֽ� 5��
	public List<BDVO> boardDeptMainList(BDVO bdvo);
}
