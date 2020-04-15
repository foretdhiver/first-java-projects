package com.spring.ge.dao;

import java.util.List;

import com.spring.ge.vo.EaVO;
import com.spring.ge.vo.EmInfoVO;

public interface EaDao {
	/* ============ SELECT ============ */
	public List<EaVO> eaSelectAll(EaVO param); //해당사번이 작성한 문서 전체 조회
	public List<EaVO> eaSelectAllPG(EaVO param); //해당사번이 작성한 품의, 승인 상태 조회
	public List<EaVO> eaSelectAllSB(EaVO param); //해당사번이 작성한 대기 상태 조회
	public List<EaVO> eaSelectAllFN(EaVO param); //해당사번이 작성한 최종승인 상태 조회
	public List<EaVO> eaSelectAllRT(EaVO param); //해당사번이 작성한 반려 상태 조회
	public List<EaVO> eaDetail(EaVO param); //전자결재상세 조회
	public List<EaVO> eaNextEmno(EaVO param);  // 결재테이블 업데이트용 결재자들 조회
	public List<EaVO> eaMyAppList(EaVO param); // 내가 결재해야할 리스트 조회
	/* ============ 전자결재 메인페이지용 SEECT ============ */
	public List<EaVO> eaMainSelectAll(EaVO param); // 전체리스트
	public List<EaVO> eaMainSelectAllFN(EaVO param); // 완료함
	public List<EaVO> eaMainSelectAllPG(EaVO param); // 진행함
	public List<EaVO> eaMainSelectAllRJ(EaVO param); // 반려함
	public List<EaVO> eaMainApprove(EaVO param); // 결재해야할 문서리스트
	/* ============ eminfo 회원정보검색 ============ */
	public List<EmInfoVO> emInfoSelectALL(EmInfoVO param);
	/* ============ INSERT ============ */
	public int eaLineInsert(EaVO param); //결재라인 입력
	public int eaStepInsert(EaVO param); //결재스텝 입력
	public int eaLogInsert(EaVO param); //결재로그 입력
	public int eaTableInsert(EaVO param); //결재테이블 입력
	/* ============ UPDATE ============ */
	public int eaTableUpdate(EaVO param); //결재테이블 업데이트(상태변경)
	/* ============ CHAEBUN ============ */
	public List<EaVO> chaebunEAL(EaVO param); //EAL 채번
	public List<EaVO> chaebunEASTNO(EaVO param); //EASTNO 채번
	public List<EaVO> chaebunEALOG(EaVO param); //EALOG 채번
	public List<EaVO> chaebunEA(EaVO param); //EA 채번
	/* ============ LOGIN SESSION ============ */
	public EmInfoVO em_InfoList(EmInfoVO param);
	/* ============ LOGIN SESSION ============ */
	public List<EmInfoVO> getDeptList(EmInfoVO param); // ajax용 데이터 셀렉트
	public List<EmInfoVO> getJobList(EmInfoVO param); // ajax용 데이터 셀렉트
	public List<EmInfoVO> getEmnameList(EmInfoVO param); // ajax용 데이터 셀렉트
} // end of EaDao
