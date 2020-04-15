package com.spring.ge.service;

import java.util.List;

import com.spring.ge.vo.BDVO;
import com.spring.ge.vo.BNVO;

public interface BNService {
	//��ȸ
	public List<BNVO> boardNoticeList(BNVO bnvo);
	//detail��ȸ
	public BNVO boardNoticeDetail(BNVO bnvo);
	//ä��
	public BNVO boardNoticeChebun(BNVO bnvo);
	//�Է�
	public int boardNoticeInsert(BNVO bnvo);
	//����
	public int boardNoticeDelete(BNVO bnvo);
	//����
	public int boardNoticeUpdate(BNVO bnvo);
	//��ȸ�� ����
	public int boardNoticeViewCntUp(BNVO bnvo);

	public List<BNVO> boardNoticeMainList(BNVO bnvo);

}