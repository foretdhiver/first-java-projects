package com.spring.ge.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.ge.vo.PbReplyVO;

@Repository
public class PbReplyDaoImpl implements PbReplyDao {

	@Autowired
	private SqlSession sqlsession;

	@Override
	public List<PbReplyVO> PbReplyList(String pbno) {
		// TODO Auto-generated method stub
		
		System.out.println("(log)댓글 리스트 dao 들어왔다 >>> ");
		
		return sqlsession.selectList("replyList", pbno);
	}

	@Override
	public int PbReplyInsert(PbReplyVO prvo) {
		// TODO Auto-generated method stub
		System.out.println("(log)댓글 인서트 Dao 왔다 >>> ");
		
		return sqlsession.insert("PbReplyInsert");
	}

	@Override
	public int PbReplyUpedate(PbReplyVO prvo) {
		// TODO Auto-generated method stub
		System.out.println("(log)댓글 수정 Dao 왔다 >>> ");

		return sqlsession.update("PbReplyUpedate");
	}

	@Override
	public int PbReplyDelete(String pbno) {
		// TODO Auto-generated method stub
		System.out.println("(log)댓글 삭제 Dao 왔다 >>> ");

		return sqlsession.delete("PbReplyDelete");
	}

	@Override
	public PbReplyVO PbChaebun(PbReplyVO prvo) {
		// TODO Auto-generated method stub
		System.out.println("(log)댓글 채번 dao왔다 >>> ");
		
		return sqlsession.selectOne("PbChaebun");
		
	}
	
	

}
