package com.spring.ge.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.ge.dao.AnBoardDao;
import com.spring.ge.vo.AnBoardVO;

@Service("anBoardService")
@Transactional
public class AnBoardServiceImpl implements AnBoardService {

	Logger logger = Logger.getLogger(AnBoardServiceImpl.class);

	@Autowired
	private AnBoardDao aa;

	//����Ʈ
	@Override
	public List<AnBoardVO> anBoardList(AnBoardVO bvo) {
		// TODO Auto-generated method stub
	
		List<AnBoardVO> aBoard = null;
		aBoard = aa.anBoardList(bvo);
		return aBoard;
	}

	//�󼼺���
	@Override
	public AnBoardVO anBoardDetail(AnBoardVO bvo) {
		// TODO Auto-generated method stub
		AnBoardVO detail = aa.anBoardDetail(bvo);
		
		return detail;
	}

	//�μ�Ʈ
	@Override
	public int anBoardInsert(AnBoardVO bvo) {
		// TODO Auto-generated method stub
		
		int result= 0;
		result = aa.anBoardInsert(bvo);
		
		return result;
	}
	//ä��
	@Override
	public AnBoardVO chaebun(AnBoardVO bvo) {
		// TODO Auto-generated method stub
		AnBoardVO cbA = null;
		cbA = aa.chaebun(bvo);
		return cbA;
	}

	//��й�ȣ Ȯ��
	@Override
	public int pwConfirm(AnBoardVO bvo) {
		// TODO Auto-generated method stub
		
		int result= 0;
		result = aa.pwConfirm(bvo);
		
		return result;
	}

	@Override
	public int anBoardUpdate(AnBoardVO bvo) {
		// TODO Auto-generated method stub
		
		int result = 0;
		result = aa.anBoardUpdate(bvo);
		return result;
	}
	//����
	@Override
	public int anBoardDelete(AnBoardVO bvo) {
		// TODO Auto-generated method stub
		return aa.anBoardDelete(bvo);
	}

	//��ȸ��
	@Override
	public int anBoardListCnt(AnBoardVO bvo) {
		// TODO Auto-generated method stub
				
		int cnt=0;
		cnt=aa.anBoardListCnt(bvo);		
		return cnt;
	}

	
	

}//class
