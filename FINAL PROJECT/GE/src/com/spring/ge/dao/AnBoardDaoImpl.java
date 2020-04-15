package com.spring.ge.dao;

import java.util.List;



import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.ge.vo.AnBoardVO;
@Repository("aa")
public class AnBoardDaoImpl implements AnBoardDao {

	@Autowired
	private SqlSession SqlSession;

	//����Ʈ
	@Override
	public List<AnBoardVO> anBoardList(AnBoardVO bvo) {
		// TODO Auto-generated method stub
		
		return SqlSession.selectList("anBoardList", bvo);
	}

	//�󼼺���
	@Override
	public AnBoardVO anBoardDetail(AnBoardVO bvo) {
		// TODO Auto-generated method stub
		return SqlSession.selectOne("anBoardDetail",bvo);
	}

	//�μ�Ʈ
	@Override
	public int anBoardInsert(AnBoardVO bvo) {
		// TODO Auto-generated method stub
		return SqlSession.insert("anBoardInsert",bvo);
	}

	//��й�ȣ Ȯ��
	@Override
	public int pwConfirm(AnBoardVO bvo) {
		// TODO Auto-generated method stub
		return SqlSession.selectOne("pwConfirm");
	}
	//������Ʈ
	@Override
	public int anBoardUpdate(AnBoardVO bvo) {
		// TODO Auto-generated method stub
		return SqlSession.update("anBoardUpdate",bvo);
	}
	//����
	@Override
	public int anBoardDelete(AnBoardVO bvo) {
		// TODO Auto-generated method stub
		return SqlSession.delete("anBoardDelete",bvo);
	}

	@Override
	public AnBoardVO chaebun(AnBoardVO bvo) {
		// TODO Auto-generated method stub
		
		return SqlSession.selectOne("abChaebun");
	}

	@Override
	public int anBoardListCnt(AnBoardVO bvo) {
		// TODO Auto-generated method stub
		return SqlSession.update("anBoardListCnt",bvo);
	}

	

	
}//class
