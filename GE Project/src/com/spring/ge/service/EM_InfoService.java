package com.spring.ge.service;

import java.util.List;

import com.spring.ge.vo.EmInfoVO;

public interface EM_InfoService {
	//��ȸ
	public List<EmInfoVO> em_Info(EmInfoVO param);
	
	//���̵�� ��й�ȣ Ȯ�� �� �´� ��� ���� �̱�
	public EmInfoVO em_InfoList(EmInfoVO param);

}
