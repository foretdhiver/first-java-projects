package mvc;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kyj.member.common.FilePath;
import kyj.board.dao.KyjHelloBoardDAO;
import kyj.board.dao.KyjHelloBoardDAOImpl;
import kyj.board.vo.KyjHelloBoardVO;

/**
 * Servlet implementation class HelloBoardControllerServlet
 */
@WebServlet("/HelloBoardControllerServlet")
public class HelloBoardControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("EUC-KR");
		response.setContentType("text/html;charset=EUC-KR");
		PrintWriter out = response.getWriter();
		String upLoadPath = FilePath.UPLOAD_FILEPATH;
		String downLoadPath = FilePath.DOWNLOAD_FILEPATH;
		int size = 10*1024*1024;
		
		String isudType = "";
		String chkInKno	= "";
		String kno = "";
		String ksubject = "";
		String kname = "";
		String kpw = "";
		String kmemo = "";
		String filename1 = "";
		String kimage = "";
		
		if(request.getContentType().toLowerCase().startsWith("multipart/form-data")){
			
			MultipartRequest mr = new MultipartRequest(request,
														upLoadPath,
														size,
														"EUC-KR",
														new DefaultFileRenamePolicy());
			isudType = mr.getParameter("ISUD_TYPE");
			
			System.out.println("isudType >>> : " + isudType);
			if(isudType!=null){
				if("I".equals(isudType.toUpperCase())){
					Enumeration files = mr.getFileNames();
					String file1 = (String)files.nextElement();
					filename1 = mr.getFilesystemName(file1);
					ksubject = mr.getParameter("ksubject");
					kname = mr.getParameter("kname");
					kpw = mr.getParameter("kpw");
					kmemo = mr.getParameter("kmemo");
					kimage = "../kyj/"+FilePath.DOWNLOAD_FILEPATH+filename1;
					
					System.out.println("filename1 >>> : " + filename1);
					System.out.println("ksubject >> " + ksubject+" "+
									   "kname >> " + kname+" "+
									   "kpw >> " + kpw+" "+
									   "kmemo >> " + kmemo+" "+
									   "kimage >> " + kimage);
					KyjHelloBoardDAO kdao = new KyjHelloBoardDAOImpl();
					System.out.println("kdao >>> : " + kdao);
					KyjHelloBoardVO kvo = null;
					kvo = new KyjHelloBoardVO();
					
					kvo.setKsubject(ksubject);
					kvo.setKname(kname);
					kvo.setKpw(kpw);
					kvo.setKmemo(kmemo);
					kvo.setKimage(kimage);
					kvo.setKdeleteyn("Y");
					
					int nCnt = kdao.insertKyjHelloBoard(kvo);
					ArrayList<KyjHelloBoardVO> aListAll = kdao.selectKyjHelloBoard();
					if(nCnt>=1){
						System.out.println("�۾��� ����");
						request.setAttribute("aList", aListAll);
						RequestDispatcher dispatcher = request.getRequestDispatcher("board/boardList.jsp");
						dispatcher.forward(request, response);
					}else{
						System.out.println("�۾��� ����");
						response.sendRedirect("board/boardInsert.jsp");
					}
				} // "i".equal
				if("S".equals(isudType.toUpperCase())){
					kno = mr.getParameter("chkInKno");
					System.out.println("kno >>> : " + kno);
					KyjHelloBoardDAO kdao = new KyjHelloBoardDAOImpl();
					KyjHelloBoardVO kvo = null;
					kvo = new KyjHelloBoardVO();
					kvo.setKno(kno);
					ArrayList<KyjHelloBoardVO> aList = kdao.selectKyjBoard(kvo);
					System.out.println("aList >>> : " + aList);
					if(aList.size()==0){
						System.out.println("�������� �ʴ� �Խù� ��ȣ�Դϴ�");
					}else{
						System.out.println("[log]�˻� ����");
						request.setAttribute("aList", aList);
						RequestDispatcher dispatcher = request.getRequestDispatcher("board/boardSelect.jsp");
						dispatcher.forward(request, response);
					}
				} // "S".equal
				if("A".equals(isudType.toUpperCase())){
					KyjHelloBoardDAO kdao = new KyjHelloBoardDAOImpl();
					System.out.println("kdao >>> : " + kdao);
					ArrayList<KyjHelloBoardVO> aListAll = kdao.selectKyjHelloBoard();
					if(aListAll.size()==0){
						System.out.println("�����Ͱ� �����ϴ�");
					}else{
						System.out.println("[log] �˻�����");
						request.setAttribute("aList", aListAll);
						RequestDispatcher dispatcher = request.getRequestDispatcher("board/boardList.jsp");
						dispatcher.forward(request, response);
					}
				} // "A".equal
				if("U".equals(isudType.toUpperCase())){								
					kno = mr.getParameter("chkInKno");
					System.out.println("kno >>> : " + kno);
					KyjHelloBoardDAO kdao = new KyjHelloBoardDAOImpl();
					KyjHelloBoardVO kvo = null;
					kvo = new KyjHelloBoardVO();
					kvo.setKno(kno);
					ArrayList<KyjHelloBoardVO> aList = kdao.selectKyjBoard(kvo);
					System.out.println("aList >>> : " + aList);
					if(aList.size()==0){
						System.out.println("�������� �ʴ� �Խù� ��ȣ�Դϴ�");
					}else{
						System.out.println("[log]�˻� ����");
						request.setAttribute("aList", aList);
						RequestDispatcher dispatcher = request.getRequestDispatcher("board/boardUpdate.jsp");
						dispatcher.forward(request, response);
					}
				} // "U".equal
				
				if("USELECT".equals(isudType.toUpperCase())){								
					kno = mr.getParameter("kno");
					System.out.println("kno >>> : " + kno);
					KyjHelloBoardDAO kdao = new KyjHelloBoardDAOImpl();
					KyjHelloBoardVO kvo = null;
					kvo = new KyjHelloBoardVO();
					kvo.setKno(kno);
					ArrayList<KyjHelloBoardVO> aList = kdao.selectKyjBoard(kvo);
					System.out.println("aList >>> : " + aList);
					if(aList.size()==0){
						System.out.println("�������� �ʴ� �Խù� ��ȣ�Դϴ�");
					}else{
						System.out.println("[log]�˻� ����");
						request.setAttribute("aList", aList);
						RequestDispatcher dispatcher = request.getRequestDispatcher("board/boardUpdate.jsp");
						dispatcher.forward(request, response);
					}
				} // "U".equal
				
				if("UOK".equals(isudType.toUpperCase())){
					System.out.println("ISUD_TYPE >>> : " + isudType);
					kno = mr.getParameter("kno");
					Enumeration files = mr.getFileNames();
					String file1 = (String)files.nextElement();
					filename1 = mr.getFilesystemName(file1);
					ksubject = mr.getParameter("ksubject");
					kname = mr.getParameter("kname");
					kpw = mr.getParameter("kpw");
					kmemo = mr.getParameter("kmemo");
					kimage = "../kyj/"+FilePath.DOWNLOAD_FILEPATH+filename1;
					System.out.println("filename1 >>> : " + filename1);
					System.out.println(kno+"/"+kpw+"/"+kname+"/"+
							ksubject+"/"+kmemo+"/"+kimage);
					if(filename1==null){
						KyjHelloBoardDAO kdao = new KyjHelloBoardDAOImpl();
						KyjHelloBoardVO kvo = null;
						kvo = new KyjHelloBoardVO();
						kvo.setKno(kno);
						kvo.setKname(kname);
						kvo.setKpw(kpw);
						kvo.setKsubject(ksubject);
						kvo.setKmemo(kmemo);
						
						int nCntU = kdao.updateKyjHelloBoard_1(kvo);
						ArrayList<KyjHelloBoardVO> aList = kdao.selectKyjBoard(kvo);
						if (nCntU == 1){
							System.out.println(" ���� ���� �������� ������ ");
							request.setAttribute("aList", aList);
							RequestDispatcher dispatcher = request.getRequestDispatcher("board/boardSelect.jsp");
							dispatcher.forward(request, response);
						}else{
							out.println(" �Խù� ���� ���� ���� �������� ������  ");
							out.println("<script>alert('���� ����! ��й�ȣ�� �ٸ��ϴ�');</script>");
				
			//				request.setAttribute("aList", aList);
			//				RequestDispatcher dispatcher = request.getRequestDispatcher("board/boardUpdate.jsp");
			//				dispatcher.forward(request, response);
							
						}
					}else{
						KyjHelloBoardDAO kdao = new KyjHelloBoardDAOImpl();
						KyjHelloBoardVO kvo = null;
						kvo = new KyjHelloBoardVO();
						kvo.setKno(kno);
						kvo.setKname(kname);
						kvo.setKpw(kpw);
						kvo.setKsubject(ksubject);
						kvo.setKmemo(kmemo);
						kvo.setKimage(kimage);
						
						int nCntU = kdao.updateKyjHelloBoard(kvo);
						ArrayList<KyjHelloBoardVO> aList = kdao.selectKyjBoard(kvo);
						if (nCntU == 1){
							System.out.println(" ���� ���� �������� ������ ");
							request.setAttribute("aList", aList);
							RequestDispatcher dispatcher = request.getRequestDispatcher("board/boardSelect.jsp");
							dispatcher.forward(request, response);
						}else{
							out.println(" �Խù� ���� ���� ���� �������� ������  ");
							out.println("<script>alert('���� ����! ��й�ȣ�� �ٸ��ϴ�');</script>");
							
						}
					}
				}
				if("D".equals(isudType.toUpperCase())){
					System.out.println("ISUD_TYPE >>> : " + isudType);
					kno = mr.getParameter("kno");
					kpw = mr.getParameter("kpw");
					System.out.println("kno + kpw>>> : " + kno + " + " + kpw);
					KyjHelloBoardDAO kdao = new KyjHelloBoardDAOImpl();
					KyjHelloBoardVO kvo = null;
					kvo = new KyjHelloBoardVO();
					kvo.setKno(kno);
					kvo.setKpw(kpw);
					int nCntU = kdao.deleteKyjHelloBoard(kvo);
					if (nCntU == 1){
						System.out.println(" ���� ���� �������� ������ ");
						ArrayList<KyjHelloBoardVO> aListAll = kdao.selectKyjHelloBoard();
						if(aListAll.size()==0){
							System.out.println("�����Ͱ� �����ϴ�");
						}else{
							System.out.println("[log] �˻�����");
							request.setAttribute("aList", aListAll);
							RequestDispatcher dispatcher = request.getRequestDispatcher("board/boardList.jsp");
							dispatcher.forward(request, response);
						}
					}else{
						out.println(" ���� �������� ������ ");
					}
				} // "D".equal
				
			} // if isudtype!=null
		} // end of multipart/form-data if��
		
		
		else if(!request.getContentType().toLowerCase().startsWith("multipart/form-data")){
			String memberType = request.getParameter("memberType");
			System.out.println("memberType >>> : " + memberType);
			if(memberType.equals("kPwCheck")){
//			if("kPwCheck".equals(memberType.toUpperCase())){
				String kno_ = request.getParameter("kno");
				String kpw_ = request.getParameter("kpw");
				System.out.println("kpw >>> : " + kno_ );
				System.out.println("kno >>> : " + kpw_ );
				
				KyjHelloBoardDAO kdao = new KyjHelloBoardDAOImpl();
				KyjHelloBoardVO kvo = null;
				kvo = new KyjHelloBoardVO();
				kvo.setKpw(kpw_);
				kvo.setKno(kno_);
				
				int nCnt = 0;
				boolean bFlag = false;
				
				nCnt = kdao.pwValCheck(kvo);
				
				if(nCnt==0){
					System.out.println("(log) kPwCheck. ����̾Ȱ��Ƽ� �����Ұ�");
					bFlag = false;
					System.out.println("(log) kPwCheck."+  bFlag);
				}else if (nCnt>0){
					System.out.println("(log) kPwCheck.  ����̰���");
					bFlag = true;
					System.out.println("(log) kPwCheck."+  bFlag);
				}
				
				request.setAttribute("bFlag", new Boolean(bFlag));
				RequestDispatcher dispatcher = request.getRequestDispatcher("/board/ajaxXml.jsp");
				dispatcher.forward(request, response);
			}
		}
	} // end of doPost
} // end of servlet
