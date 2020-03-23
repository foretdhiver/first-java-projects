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
		System.out.println("[log] BoardController.insertBoard ���� >>> ");
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
			System.out.println("[log] BoardController.insertBoard.try-catch ���� >>> : ");
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
			// �Էµ� ������ Ȯ��
			System.out.println("���������������������������������������Էµ� ������������������������������������������");
			System.out.printf("kno : " + kno +
							  "%nksubject : " + ksubject +
							  "%nkname : " + kname +
							  "%nkpw : " + kpw +
							  "%nkmemo : " + kmemo +
							  "%nkimage : " + kimage + "%n");
			System.out.println("��������������������������������������������������������������������������������������������������");
			bvo = new BoardVO();
			//ä��
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
				System.out.println("���ۼ� ����");
			}else{
				System.out.println("���ۼ� ����");
			}
		}catch(IOException e){ System.out.println("[log] ���� : " + e);}
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("/result_m");
		mav.addObject("kno", kno);
		System.out.println("[log] BoardController.insertBoard ���� <<< ");
		return mav;
	}
	
	/* ��ü ��ȸ */
	@RequestMapping("/listBoard")
	public ModelAndView listBoard(@ModelAttribute BoardVO param){
		System.out.println("[log] BoardController.listBoard ���� >>> ");
		System.out.println("param : " + param);
		System.out.println("�Է��� ���� : ");
		BoardVO.boardPrint(param);
		List<BoardVO> list = boardService.listBoard(param);
		int listCnt = list.size();
		System.out.println("listCnt : " + listCnt);
		System.out.println("����������������selectAll �� ��������������������");
		for(int i=0; i<listCnt; i++){
			BoardVO value = (BoardVO)list.get(i);
			BoardVO.boardPrint(value);
			System.out.println("������������������������������������������������������������");
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject("boardList", list);
		System.out.println("list : " + list);
		System.out.println("mav : " + mav);
		mav.setViewName(CONTEXT_PATH+"/board");
		System.out.println("[log]  BoardController.listBoard ���� <<< ");
		return mav;
	}
	
	/* �� ���� ���� */
	@RequestMapping("/selectBoard")
	public ModelAndView selectBoard(@ModelAttribute BoardVO param){
		System.out.println("[log] BoardController.selectBoard ���� >>> ");
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
				System.out.println("����������������select �� ��������������������");
				for(int i=0; i<listCnt; i++){
					BoardVO value = (BoardVO)list.get(i);
					BoardVO.boardPrint(value);
					System.out.println("������������������������������������������������������������");
				}
				
				System.out.println("mode = update ����");
				mav.addObject("boardList", list);
				mav.addObject("mode", "update");
				mav.setViewName(CONTEXT_PATH+"/boardUpdate");
				
			}else{
				System.out.println("[log] ��ȸ�� �����Ͱ� ���� ");
			}
//			
		}else{
			System.out.println("[log] kno���� ������� insert ���� : ");
			System.out.println("kno : " + kno);
			System.out.println("mode = insert ����");
			mav.addObject("mode", "insert");
			mav.setViewName(CONTEXT_PATH+"/boardInsert");
		}
		
//		// selectBoard
//		List<BoardVO> list = boardService.selectBoard(param);
//		int listCnt = list.size();
//		System.out.println("listCnt : " + listCnt);
//		System.out.println("����������������select �� ��������������������");
//		for(int i=0; i<listCnt; i++){
//			BoardVO value = (BoardVO)list.get(i);
//			BoardVO.boardPrint(value);
//			System.out.println("������������������������������������������������������������");
//		}

//		System.out.println("list : " + list);
	
//		ModelAndView mav = new ModelAndView();
		if(kno == null){
			System.out.println("mode = insert ����");
			mav.addObject("mode", "insert");
			mav.setViewName(CONTEXT_PATH+"/boardInsert");
		}else{
			System.out.println("mode = update ����");
			mav.addObject("boardList", list);
			mav.addObject("mode", "update");
			mav.setViewName(CONTEXT_PATH+"/boardUpdate");
		}
		System.out.println("[log] BoardController.selectBoard ���� <<< ");
		return mav;
	}
	
	/* ��й�ȣ üũ */
	@RequestMapping("/pwValueCheck")
	public ModelAndView pwValueCheck(@ModelAttribute BoardVO param){
		System.out.println("[log] BoardController.pwValueCheck ���� >>> ");
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
			System.out.println("����� ���� �ʾƼ� ���� �Ұ�");
			System.out.println("bFlag : " + bFlag);
			mav.addObject("bFlag", bFlag);
			mav.setViewName(CONTEXT_PATH+"/pwValueCheck");
		}else{
			bFlag = true;
			System.out.println("���� ����");
			System.out.println("bFlag : " + bFlag);
			mav.addObject("bFlag", bFlag);
			mav.setViewName(CONTEXT_PATH+"/pwValueCheck");
		}
		System.out.println("[log] BoardController.pwValueCheck ���� <<< ");
		return mav;
	}
	
	/* ������Ʈ */
	@RequestMapping("/updateBoard")
	public ModelAndView updateBoard(HttpServletRequest request){
		System.out.println("[log] BoardController.updateBoard ���� >>> ");
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
			System.out.println("[log] BoardController.updateBoard.try-catch ���� >>> : ");
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
			// �Էµ� ������ Ȯ��
			System.out.println("���������������������������������������Էµ� ������������������������������������������");
			System.out.printf("kno : " + kno +
							  "%nksubject : " + ksubject +
							  "%nkname : " + kname +
							  "%nkpw : " + kpw +
							  "%nkmemo : " + kmemo +
							  "%nkimage : " + kimage + "%n");
			System.out.println("��������������������������������������������������������������������������������������������������");
			bvo = new BoardVO();
			System.out.println("filename : " + filename);
			if(filename!=null && filename.length()>0){
				System.out.println("�̹����� �ִ� ������Ʈ");
				bvo.setKno(kno);
				bvo.setKsubject(ksubject);
				bvo.setKname(kname);
				bvo.setKmemo(kmemo);
				bvo.setKimage(kimage);
				
				int bFlag = boardService.updateBoard(bvo);
				System.out.println("bFlag : " + bFlag);
				if(bFlag>0){
					System.out.println("(�̹�������) ������Ʈ ����");
					
				}else{
					System.out.println("(�̹�������) ������Ʈ ����");
				}
			}else{
				System.out.println("�̹����� ���� ������Ʈ");
				bvo.setKno(kno);
				bvo.setKsubject(ksubject);
				bvo.setKname(kname);
				bvo.setKmemo(kmemo);
				
				int bFlag = boardService.updateBoard_w(bvo);
				System.out.println("bFlag : " + bFlag);
				if(bFlag>0){
					System.out.println("(�̹�������) ������Ʈ ����");
					
				}else{
					System.out.println("(�̹�������) ������Ʈ ����");
				}
			}
		}catch(IOException e){ System.out.println("[log] ���� : " + e);}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/result_m");
		mav.addObject("kno", kno);
		System.out.println("[log] BoardController.updateBoard ���� <<< ");
		return mav;
	}
	
	/* ���� */
	@RequestMapping("/deleteBoard")
	public ModelAndView deleteBoard(HttpServletRequest request){
		System.out.println("[log] BoardController.deleteBoard ���� >>> ");
		String uploadPath = Filepath.UPLOAD_FILEPATH;
		System.out.println("uploadPath : " + uploadPath);
		
		int size = 10*1024*1024;
		String kno = "";
		BoardVO bvo = null;
		
		try{
			MultipartRequest multi = new MultipartRequest(request, uploadPath, size, "EUC-KR", new DefaultFileRenamePolicy());
			System.out.println("[log] BoardController.deleteBoard.try-catch ���� >>> : ");
			kno = multi.getParameter("kno");
			bvo = new BoardVO();
			bvo.setKno(kno);
			// �Էµ� ������ Ȯ��
			System.out.println("�������������������������������������������� �۹�ȣ��������������������������������������");
			System.out.printf("kno : " + kno);
			System.out.println("��������������������������������������������������������������������������������������������������");
			int bFlag = boardService.deleteBoard(bvo);
			System.out.println("bFlag : " + bFlag);
			if(bFlag>0){
				System.out.println("ȸ��Ż�� ����");
			}else{
				System.out.println("ȸ��Ż�� ����");
			}	
		}catch(IOException e){ System.out.println("[log] ���� : " + e);}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/result_m");
		mav.addObject("kno", kno);
		System.out.println("[log] BoardController.deleteBoard ���� <<< ");
		return mav;
	}
}
