package com.spring.ge.dao;

import java.util.List;

import com.spring.ge.vo.BDComVO;

public interface BDCDao {
	//��ȸ
	public List<BDComVO> commentList(BDComVO bdc);
	//ä��
	public BDComVO commentChaebun(BDComVO bdc);
	//�Է�
	public int commentInsert(BDComVO bdc);	
	//����
	public int commentDelete(BDComVO bdc);
	//����
	public int commentUpdate(BDComVO bdc);

}
