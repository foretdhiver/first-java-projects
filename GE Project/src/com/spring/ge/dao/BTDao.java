package com.spring.ge.dao;

import java.util.List;

import com.spring.ge.vo.BTVO;

public interface BTDao {
	//��ȸ
	public List<BTVO> boardTaskList(BTVO btvo);
	//detail��ȸ
	public BTVO boardTaskDetail(BTVO btvo);
	//ä��
	public BTVO boardTaskChebun(BTVO btvo);
	//�Է�
	public int boardTaskInsert(BTVO btvo);
	//����
	public int boardTaskDelete(BTVO btvo);
	//����
	public int boardTaskUpdate(BTVO btvo);
	//��ȸ�� ����
	public int boardTaskViewCntUp(BTVO btvo);

}
