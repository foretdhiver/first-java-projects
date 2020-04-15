package com.spring.ge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.ge.dao.PbReplyDao;
import com.spring.ge.vo.PbReplyVO;

@Service
@Transactional
public class PbReplyServiceImpl implements PbReplyService {
	
	@Autowired
	private PbReplyDao pbReplyDao;

	@Override
	public List<PbReplyVO> PbReplyList(String pbno) {
		// TODO Auto-generated method stub
		
		System.out.println("(log)��� ����Ʈ ���� ���Դ� >>> ");
		
		List<PbReplyVO> pbrList = null;
		pbrList = pbReplyDao.PbReplyList(pbno);
		
		return pbrList;
		
	}
	
	//�μ�Ʈ
	@Override
	public int PbReplyInsert(PbReplyVO prvo) {
		// TODO Auto-generated method stub

		System.out.println("(log)��� �μ�Ʈ ���� ���Դ� >>> ");
		
		int result =0;
		result = pbReplyDao.PbReplyInsert(prvo);
		return result;
	}

	@Override
	public int PbReplyUpedate(PbReplyVO prvo) {
		// TODO Auto-generated method stub
		
		System.out.println("(log)��� ���� ���� ���Դ� >>> ");
		
		int result =0;
		System.out.println("	1");
		result = pbReplyDao.PbReplyUpedate(prvo);
			
		System.out.println("	2 ");
		return result;
	}

	@Override
	public int PbReplyDelete(String pbno) {
		// TODO Auto-generated method stub
		
		System.out.println("(log)��� ���� ���� ���Դ� >>> ");
		
		int result =0;
		result = pbReplyDao.PbReplyDelete(pbno);
		return result;
	}

	@Override
	public PbReplyVO PbChaebun(PbReplyVO prvo) {
		// TODO Auto-generated method stub
		
		System.out.println("(log)��� ä������ �Դ� >>> ");
		PbReplyVO pbChaebun = null;
		pbChaebun = pbReplyDao.PbChaebun(prvo);
		
		return pbChaebun;
	}
	

}
