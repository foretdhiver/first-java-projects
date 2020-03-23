package com.kyjspring.board.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kyjspring.board.common.Filepath;
import com.kyjspring.board.service.BoardService;
import com.kyjspring.board.vo.BoardVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


@Controller
@RequestMapping(value="/board")
public class BoardController {
	private static final String CONTEXT_PATH = "board";
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/insertBoard")
	public ModelAndView insertBoard(HttpServletRequest request){
		System.out.println("[log] BoardController.insertBoard 시작 >>> ");
		String uploadPath = Filepath.UPLOAD_FILEPATH;
		System.out.println("uploadPath : " + uploadPath);
		
		int size = 10*1024*1024;
		String kno = "";
		String kmem = "";
		String ksubject = "";
		String kname = "";
		String kpw = "";
		String kmemo = ""; 
		String kimage = "";
		String filename = "";
		BoardVO bvo = null;
		
		try{
			MultipartRequest multi = new MultipartRequest(request, uploadPath, size, "EUC-KR", new DefaultFileRenamePolicy());
			System.out.println("[log] BoardController.insertBoard.try-catch 시작 >>> : ");
			kno = multi.getParameter("kno");
			ksubject = multi.getParameter("ksubject");
			kname = multi.getParameter("kname");
			kpw = multi.getParameter("kpw");
			kmemo = multi.getParameter("kmemo");
			kimage = multi.getParameter("kimage");
			Enumeration files = multi.getFileNames();
			String file = (String)files.nextElement();
			filename = multi.getFilesystemName(file);
			kimage = "../"+Filepath.DOWNLOAD_FILEPATH+filename;
			System.out.println("filename : " + kimage);
			// 입력된 데이터 확인
			System.out.println("―――――――――――――――――――입력된 정보―――――――――――――――――――");
			System.out.printf("kno : " + kno +
							  "%nksubject : " + ksubject +
							  "%nkname : " + kname +
							  "%nkpw : " + kpw +
							  "%nkmemo : " + kmemo +
							  "%nkimage : " + kimage + "%n");
			System.out.println("―――――――――――――――――――――――――――――――――――――――――――――――――");
			bvo = new BoardVO();
			//채번
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String date = sdf.format(d);
			System.out.println("date : " + date);
			
			List<BoardVO> list = boardService.chaebunBoard(bvo);
			String kno_test = "B" + list.get(0).getKno();
			
			System.out.println("kno_test : " + kno_test);
			if (list.size() == 1){
				bvo.setKno("B" + list.get(0).getKno());
			}
			bvo.setKsubject(ksubject);	
			bvo.setKname(kname);
			bvo.setKpw(kpw);
			bvo.setKmemo(kmemo);
			bvo.setKimage(kimage);
			
			int bFlag = boardService.insertBoard(bvo);
			if(bFlag>0){
				System.out.println("글작성 성공");
			}else{
				System.out.println("글작성 실패");
			}
		}catch(IOException e){ System.out.println("[log] 에러 : " + e);}
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("/result_m");
		mav.addObject("kno", kno);
		System.out.println("[log] BoardController.insertBoard 종료 <<< ");
		return mav;
	}
	
	/* 전체 조회 */
	@RequestMapping("/listBoard")
	public ModelAndView listBoard(@ModelAttribute BoardVO param){
		System.out.println("[log] BoardController.listBoard 시작 >>> ");
		System.out.println("param : " + param);
		System.out.println("입력한 정보 : ");
		BoardVO.boardPrint(param);
		List<BoardVO> list = boardService.listBoard(param);
		int listCnt = list.size();
		System.out.println("listCnt : " + listCnt);
		System.out.println("――――――――selectAll 된 정보――――――――");
		for(int i=0; i<listCnt; i++){
			BoardVO value = (BoardVO)list.get(i);
			BoardVO.boardPrint(value);
			System.out.println("――――――――――――――――――――――――――――――");
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject("boardList", list);
		System.out.println("list : " + list);
		System.out.println("mav : " + mav);
		mav.setViewName(CONTEXT_PATH+"/board");
		System.out.println("[log]  BoardController.listBoard 종료 <<< ");
		return mav;
	}
	
	/* 상세 정보 보기 */
	@RequestMapping("/selectBoard")
	public ModelAndView selectBoard(@ModelAttribute BoardVO param){
		System.out.println("[log] BoardController.selectBoard 시작 >>> ");
		String kno= param.getKno();
		System.out.println("kno : " + kno);
		
		
		ModelAndView mav = new ModelAndView();
		List<BoardVO> list = null;
		
		if (kno !=null && kno.length() > 0){
			// selectBoard
			list = boardService.selectBoard(param);
			int listCnt = list.size();
//			if (listCnt > 0){
			if (listCnt == 1){
				System.out.println("――――――――select 된 정보――――――――");
				for(int i=0; i<listCnt; i++){
					BoardVO value = (BoardVO)list.get(i);
					BoardVO.boardPrint(value);
					System.out.println("――――――――――――――――――――――――――――――");
				}
				
				System.out.println("mode = update 진입");
				mav.addObject("boardList", list);
				mav.addObject("mode", "update");
				mav.setViewName(CONTEXT_PATH+"/boardUpdate");
				
			}else{
				System.out.println("[log] 조회된 데이터가 없음 ");
			}
//			
		}else{
			System.out.println("[log] kno값이 없을경우 insert 진입 : ");
			System.out.println("kno : " + kno);
			System.out.println("mode = insert 진입");
			mav.addObject("mode", "insert");
			mav.setViewName(CONTEXT_PATH+"/boardInsert");
		}
		
//		// selectBoard
//		List<BoardVO> list = boardService.selectBoard(param);
//		int listCnt = list.size();
//		System.out.println("listCnt : " + listCnt);
//		System.out.println("――――――――select 된 정보――――――――");
//		for(int i=0; i<listCnt; i++){
//			BoardVO value = (BoardVO)list.get(i);
//			BoardVO.boardPrint(value);
//			System.out.println("――――――――――――――――――――――――――――――");
//		}

//		System.out.println("list : " + list);
	
//		ModelAndView mav = new ModelAndView();
		if(kno == null){
			System.out.println("mode = insert 진입");
			mav.addObject("mode", "insert");
			mav.setViewName(CONTEXT_PATH+"/boardInsert");
		}else{
			System.out.println("mode = update 진입");
			mav.addObject("boardList", list);
			mav.addObject("mode", "update");
			mav.setViewName(CONTEXT_PATH+"/boardUpdate");
		}
		System.out.println("[log] BoardController.selectBoard 종료 <<< ");
		return mav;
	}
	
	/* 비밀번호 체크 */
	@RequestMapping("/pwValueCheck")
	public ModelAndView pwValueCheck(@ModelAttribute BoardVO param){
		System.out.println("[log] BoardController.pwValueCheck 시작 >>> ");
		BoardVO bvo = null;
		bvo = new BoardVO();
		bvo.setKno(param.getKno());
		bvo.setKpw(param.getKpw());
		System.out.println("kno : " + param.getKno());
		System.out.println("kno : " + param.getKpw());
		List<BoardVO> list = boardService.pwValueCheck(bvo);
		int listCnt = list.size();
		boolean bFlag = false;
		System.out.println("listCnt : " + listCnt);
		ModelAndView mav = new ModelAndView();
		if(listCnt==0){
			bFlag = false;
			System.out.println("비번이 같지 않아서 수정 불가");
			System.out.println("bFlag : " + bFlag);
			mav.addObject("bFlag", bFlag);
			mav.setViewName(CONTEXT_PATH+"/pwValueCheck");
		}else{
			bFlag = true;
			System.out.println("수정 가능");
			System.out.println("bFlag : " + bFlag);
			mav.addObject("bFlag", bFlag);
			mav.setViewName(CONTEXT_PATH+"/pwValueCheck");
		}
		System.out.println("[log] BoardController.pwValueCheck 종료 <<< ");
		return mav;
	}
	
	/* 업데이트 */
	@RequestMapping("/updateBoard")
	public ModelAndView updateBoard(HttpServletRequest request){
		System.out.println("[log] BoardController.updateBoard 시작 >>> ");
		String uploadPath = Filepath.UPLOAD_FILEPATH;
		System.out.println("uploadPath : " + uploadPath);
		
		int size = 10*1024*1024;
		String kno = "";
		String ksubject = "";
		String kname = "";
		String kpw = "";
		String kmemo = ""; 
		String kimage = "";
		String filename = "";
		BoardVO bvo = null;
		
		try{
			MultipartRequest multi = new MultipartRequest(request, uploadPath, size, "EUC-KR", new DefaultFileRenamePolicy());
			System.out.println("[log] BoardController.updateBoard.try-catch 시작 >>> : ");
			kno = multi.getParameter("kno");
			ksubject = multi.getParameter("ksubject");
			kname = multi.getParameter("kname");
			kpw = multi.getParameter("kpw");
			kmemo = multi.getParameter("kmemo");
			kimage = multi.getParameter("kimage");
			Enumeration files = multi.getFileNames();
			String file = (String)files.nextElement();
			filename = multi.getFilesystemName(file);
			kimage = "../"+Filepath.DOWNLOAD_FILEPATH+filename;
			System.out.println("filename : " + kimage);
			// 입력된 데이터 확인
			System.out.println("―――――――――――――――――――입력된 정보―――――――――――――――――――");
			System.out.printf("kno : " + kno +
							  "%nksubject : " + ksubject +
							  "%nkname : " + kname +
							  "%nkpw : " + kpw +
							  "%nkmemo : " + kmemo +
							  "%nkimage : " + kimage + "%n");
			System.out.println("―――――――――――――――――――――――――――――――――――――――――――――――――");
			bvo = new BoardVO();
			System.out.println("filename : " + filename);
			if(filename!=null && filename.length()>0){
				System.out.println("이미지가 있는 업데이트");
				bvo.setKno(kno);
				bvo.setKsubject(ksubject);
				bvo.setKname(kname);
				bvo.setKmemo(kmemo);
				bvo.setKimage(kimage);
				
				int bFlag = boardService.updateBoard(bvo);
				System.out.println("bFlag : " + bFlag);
				if(bFlag>0){
					System.out.println("(이미지포함) 업데이트 성공");
					
				}else{
					System.out.println("(이미지포함) 업데이트 실패");
				}
			}else{
				System.out.println("이미지가 없는 업데이트");
				bvo.setKno(kno);
				bvo.setKsubject(ksubject);
				bvo.setKname(kname);
				bvo.setKmemo(kmemo);
				
				int bFlag = boardService.updateBoard_w(bvo);
				System.out.println("bFlag : " + bFlag);
				if(bFlag>0){
					System.out.println("(이미지제외) 업데이트 성공");
					
				}else{
					System.out.println("(이미지제외) 업데이트 실패");
				}
			}
		}catch(IOException e){ System.out.println("[log] 에러 : " + e);}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/result_m");
		mav.addObject("kno", kno);
		System.out.println("[log] BoardController.updateBoard 종료 <<< ");
		return mav;
	}
	
	/* 삭제 */
	@RequestMapping("/deleteBoard")
	public ModelAndView deleteBoard(HttpServletRequest request){
		System.out.println("[log] BoardController.deleteBoard 시작 >>> ");
		String uploadPath = Filepath.UPLOAD_FILEPATH;
		System.out.println("uploadPath : " + uploadPath);
		
		int size = 10*1024*1024;
		String kno = "";
		BoardVO bvo = null;
		
		try{
			MultipartRequest multi = new MultipartRequest(request, uploadPath, size, "EUC-KR", new DefaultFileRenamePolicy());
			System.out.println("[log] BoardController.deleteBoard.try-catch 시작 >>> : ");
			kno = multi.getParameter("kno");
			bvo = new BoardVO();
			bvo.setKno(kno);
			// 입력된 데이터 확인
			System.out.println("―――――――――――――――――――삭제할 글번호―――――――――――――――――――");
			System.out.printf("kno : " + kno);
			System.out.println("―――――――――――――――――――――――――――――――――――――――――――――――――");
			int bFlag = boardService.deleteBoard(bvo);
			System.out.println("bFlag : " + bFlag);
			if(bFlag>0){
				System.out.println("회원탈퇴 성공");
			}else{
				System.out.println("회원탈퇴 실패");
			}	
		}catch(IOException e){ System.out.println("[log] 에러 : " + e);}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/result_m");
		mav.addObject("kno", kno);
		System.out.println("[log] BoardController.deleteBoard 종료 <<< ");
		return mav;
	}
}
