package com.spring.ge.service;

import java.util.List;

import com.spring.ge.vo.BTComVO;

public interface BTCService {
	//��ȸ
	public List<BTComVO> commentList(BTComVO btc);
	//ä��
	public BTComVO commentChaebun(BTComVO btc);
	//�Է�
	public int commentInsert(BTComVO btc);
	//����
	public int commentDelete(BTComVO btc);
	//����
	public int commentUpdate(BTComVO btc);
	
}
