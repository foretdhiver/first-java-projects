package com.spring.ge.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.spring.ge.vo.ReplyVO;


public class ReplyDAOImpl implements ReplyDAO {

	@Autowired
	private SqlSession session;
	
	@Override
	public List<ReplyVO> replyList(String abno) {
		// TODO Auto-generated method stub
		return session.selectList("replyList", abno);
	}

	@Override
	public int replyInsert(ReplyVO rvo) {
		// TODO Auto-generated method stub
		return session.insert("replyInsert");
	}

	@Override
	public int replyUpedate(ReplyVO rvo) {
		// TODO Auto-generated method stub
		return session.update("replyUpedate");
	}

	@Override
	public int replyDelete(String abno) {
		// TODO Auto-generated method stub
		return session.delete("replyDelete");
	}

	@Override
	public ReplyVO chaebun(ReplyVO rvo) {
		// TODO Auto-generated method stub
		return session.selectOne("chaebun");
	}

}
