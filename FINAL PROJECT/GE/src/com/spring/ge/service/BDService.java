package com.spring.ge.service;

import java.util.ArrayList;
import java.util.List;

import com.spring.ge.vo.BDVO;

public interface BDService {
	//조회
	public List<BDVO> boardDepartmentList(BDVO bdvo);
	//detail조회
	public BDVO boardDepartmentDetail(BDVO bdvo);
	//채번
	public BDVO boardDepartmentChebun(BDVO bdvo);
	//입력
	public int boardDepartmentInsert(BDVO bdvo);
	//삭제
	public int boardDepartmentDelete(BDVO bdvo);
	//수정
	public int boardDepartmentUpdate(BDVO bdvo);
	//조회수 증가
	public int boardDepartmentViewCntUp(BDVO bdvo);
	//메인에서 보여지는 최신 8건
	public List<BDVO> boardDeptMainList(BDVO bdvo);

}
