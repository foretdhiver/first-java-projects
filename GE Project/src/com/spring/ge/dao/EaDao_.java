package com.spring.ge.dao;

import java.util.List;
import com.spring.ge.vo.EaVO_;
import com.spring.ge.vo.EmInfoVO;

public interface EaDao_ {
	
	/* 세션을 사용하기 위한 로그인 */
	public EmInfoVO emInfoList(EmInfoVO evo);
	
	/* 글 작성 */
	public int eaInsertLine(EaVO_ evo);
	public int eaInsertStep(EaVO_ evo);
	public int eaInsertLog(EaVO_ evo);
	public List<EaVO_> nextEmno(EaVO_ evo);
	public int eaInsertH(EaVO_ evo);
	public int eaInsertP(EaVO_ evo);
	
	/* select */
	/* 메인 */
	public List<EaVO_> eaMainAppList(EaVO_ evo);
	public List<EaVO_> eaMainRJList(EaVO_ evo);
	public List<EaVO_> eaMainPGList(EaVO_ evo);
	public List<EaVO_> eaMainSTList(EaVO_ evo);
	public List<EaVO_> eaMaineaList(EaVO_ evo);
	
	/* 글 작성 시 사용하는 ajax용 select */
	public List<EmInfoVO> searchDeptList(EmInfoVO evo);
	public List<EmInfoVO> searchJobList(EmInfoVO evo);
	public List<EmInfoVO> searchEmnameList(EmInfoVO evo);
	
	/* 내가 작성한 리스트 */
	public List<EaVO_> eaWriteAll(EaVO_ evo);
	public List<EaVO_> eaWritePG(EaVO_ evo);
	public List<EaVO_> eaWriteEnd(EaVO_ evo);
	public List<EaVO_> eaWriteRJ(EaVO_ evo);
	public List<EaVO_> eaWriteST(EaVO_ evo);
	
	// 내가 결재할 리스트
	public List<EaVO_> eaListAll(EaVO_ evo);
	public List<EaVO_> eaListStand(EaVO_ evo);
	
	/* 문서 상세정보와 서치 */
	public List<EaVO_> eaFormDetail(EaVO_ evo);
	public List<EaVO_> eaListSelect(EaVO_ evo);
	
	/* 채번 친구들 */
	public EaVO_ chaebunLine(EaVO_ evo);
	public EaVO_ chaebunStep(EaVO_ evo);
	public EaVO_ chaebunLog(EaVO_ evo);
	public EaVO_ chaebunTable(EaVO_ evo);

	/* 업데이트 */
	public int eaLog1st(EaVO_ evo);
	public int eaLog2nd(EaVO_ evo);
	public int updateTable(EaVO_ evo);
}