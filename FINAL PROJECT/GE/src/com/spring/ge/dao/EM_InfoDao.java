package com.spring.ge.dao;

import java.util.List;

import com.spring.ge.vo.EmInfoVO;

public interface EM_InfoDao {
	//조회
	public List<EmInfoVO> em_Info(EmInfoVO param);
	//아이디와 비밀번호 확인 후 맞는 사람 정보 뽑기
	public EmInfoVO em_InfoList(EmInfoVO param);

}
