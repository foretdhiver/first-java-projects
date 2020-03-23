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
import kyj.member.dao.KyjMemberDAO;
import kyj.member.dao.KyjMemberDAOImpl;
import kyj.member.vo.KyjMemberVO;
/**
 * Servlet implementation class MemberControllerServlet
 */
@WebServlet("/MemberControllerServlet")
public class MemberControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, 
					   HttpServletResponse response) 
			  throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("EUC-KR");
		response.setContentType("text/html;charset=EUC-KR");
		PrintWriter out = response.getWriter();
		String upLoadPath = FilePath.UPLOAD_FILEPATH;
		String downLoadPath = FilePath.DOWNLOAD_FILEPATH;
		int size = 10*1024*1024;
		String filename1 = "";
		String kid = "";
		String kpw = "";
		String kpw_ = "";
		String kname ="";
		String khp = "";
		String kbirth = "";
		String kmail = "";
		String kpostno = "";
		String kjuso = "";
		String kimage = "";
		String kjuso1 = "";
		String memberType = "";
		

		
		
		
		if(request.getContentType().toLowerCase().startsWith("multipart/form-data")){
			// 파일업로드
			MultipartRequest mr = new MultipartRequest(request,
														upLoadPath,
														size,
														"EUC-KR",
														new DefaultFileRenamePolicy());
			// isudType null 확인
			String isudType = mr.getParameter("ISUD_TYPE");
			System.out.println("isudType >>> : " + isudType);
			if(isudType!=null){
				if("I".equals(isudType.toUpperCase())){
					 Enumeration files = mr.getFileNames();
					 String file1 = (String)files.nextElement();
					 filename1 = mr.getFilesystemName(file1);
					 kid = mr.getParameter("kid");
					 kpw = mr.getParameter("kpw");
					 kname = mr.getParameter("kname");
					 khp = mr.getParameter("khp");
					 kbirth = mr.getParameter("kbirth");
					 kmail = mr.getParameter("kmail")+ mr.getParameter("kEmailAdd");
					 kpostno = mr.getParameter("kpostno");
					 kjuso = mr.getParameter("kjuso");
					 kjuso1 = mr.getParameter("kjuso1");
					 kimage = "../kyj/"+FilePath.DOWNLOAD_FILEPATH+filename1;				
			//		 kimage = filename1;	
					System.out.println("filename1 >>> : " + filename1);
					// 데이터 확인
					System.out.println(kid+"/"+kpw+"/"+kname+"/"+
									   khp+"/"+kbirth+"/"+kmail+"/"+
									   kpostno+"/"+kjuso+"/"+kjuso1+"/"+
									   kimage);
					
					KyjMemberDAO kdao = new KyjMemberDAOImpl();
					KyjMemberVO kvo = null;
					kvo = new KyjMemberVO();
					kvo.setKid(kid);
					kvo.setKpw(kpw);
					kvo.setKname(kname);
					kvo.setKbirth(kbirth);
					kvo.setKhp(khp);
					kvo.setKemail(kmail);
					kvo.setKpostno(kpostno);
					kvo.setKjuso(kjuso);
					kvo.setKjuso1(kjuso1);
					kvo.setKimage(kimage);
					
					boolean bFlag = kdao.insertKyjMember(kvo);
					
					if(bFlag){
						ArrayList<KyjMemberVO> aList = kdao.searchKyjMember(kvo);
						int aListSize=aList.size();
						if(aListSize>0){
							System.out.println("[log] 입력 성공");
							
							if(true){
								KyjMemberDAO kdao_ = new KyjMemberDAOImpl();
								KyjMemberVO _kvo = null;
								_kvo = new KyjMemberVO();
								_kvo.setKid(kid);
								ArrayList<KyjMemberVO> aList_ = kdao_.searchKyjMember(_kvo);
								if(aList_.size()==0){
									System.out.println("존재하지 않는 회원입니다");
								}else{
									System.out.println("[log] 검색 성공");
									request.setAttribute("aList",aList);
									RequestDispatcher dispatcher = request.getRequestDispatcher("member/selectKyjMember.jsp");
									dispatcher.forward(request, response);
								}
							}							
						}else{
							System.out.println("[log] 입력 실패");
							out.println("<script>alert('회원가입 실패');</script>");
							response.sendRedirect("/kyj/member/insertKyjMember.jsp");
						}
					} // bflag if문
				} // if=i
				
				if("S".equals(isudType.toUpperCase())){
					kid = mr.getParameter("kid");
					System.out.println("kid >>> : " + kid);
					KyjMemberDAO kdao = new KyjMemberDAOImpl();
					KyjMemberVO _kvo = null;
					_kvo = new KyjMemberVO();
					_kvo.setKid(kid);
					ArrayList<KyjMemberVO> aList = kdao.searchKyjMember(_kvo);
					System.out.println("aList >>> : " + aList);
					if(aList.size()==0){
						System.out.println("존재하지 않는 회원입니다");
						out.println("<script>alert('존재하지 않는 회원입니다'); location.href='/kyj/member/insertKyjMember.jsp';</script>");
				//		response.sendRedirect("/kyj/member/insertKyjMember.jsp");
					}else{
						System.out.println("[log] 검색 성공");
						request.setAttribute("aList", aList);
//						response.sendRedirect("/member/selectKyjMember.jsp?kmem=");
						RequestDispatcher dispatcher = request.getRequestDispatcher("/member/selectKyjMember.jsp");
						dispatcher.forward(request, response);
					}
				} // if=s
				
				if("A".equals(isudType.toUpperCase())){
					KyjMemberDAO kdao = new KyjMemberDAOImpl();
					ArrayList<KyjMemberVO> aList = kdao.selectKyjMember();
					request.setAttribute("aList", aList);
					if(aList.size()==0){
						System.out.println("회원이 없습니다");
						response.sendRedirect("/kyj/member/insertKyjMember.jsp");
					}else{
						System.out.println("[log] 검색 성공");
						RequestDispatcher dispatcher = request.getRequestDispatcher("/member/selectAllKyjMember.jsp");
						dispatcher.forward(request, response);
					}
				} // if=a
				
				if("U".equals(isudType.toUpperCase())){
					Enumeration files = mr.getFileNames();
					String file1 = (String)files.nextElement();
					 filename1 = mr.getFilesystemName(file1);
					 kid = mr.getParameter("kid");
					 kpw = mr.getParameter("kpw");
					 kname = mr.getParameter("kname");
					 khp = mr.getParameter("khp");
					 kbirth = mr.getParameter("kbirth");
					 kmail = mr.getParameter("kmail")+ mr.getParameter("kEmailAdd");
					 kpostno = mr.getParameter("kpostno");
					 kjuso = mr.getParameter("kjuso");
					 kjuso1 = mr.getParameter("kjuso1");
					 kimage = "../kyj/"+FilePath.DOWNLOAD_FILEPATH+filename1;				
			//		 kimage = filename1;	
					 
					System.out.println("filename1 >>> : " + filename1);
					// 데이터 확인
					System.out.println(kid+"/"+kpw+"/"+kname+"/"+
									   khp+"/"+kbirth+"/"+kmail+"/"+
									   kpostno+"/"+kjuso+"/"+kjuso1+"/"+
									   kimage);
					// 이미지 파일 수정을 안했을 경우
					if(filename1==null){
						KyjMemberDAO kdao = new KyjMemberDAOImpl();
						KyjMemberVO kvo = null;
						kvo = new KyjMemberVO();
						kvo.setKid(kid);
						kvo.setKpw(kpw);
						kvo.setKname(kname);
						kvo.setKbirth(kbirth);
						kvo.setKhp(khp);
						kvo.setKemail(kmail);
						kvo.setKpostno(kpostno);
						kvo.setKjuso(kjuso);
						kvo.setKjuso1(kjuso1);
						
						
						boolean bFlag = kdao.updateKyjMember1(kvo);
						if(bFlag){
							ArrayList<KyjMemberVO> aList=kdao.searchKyjMember(kvo);
							int aListSize=aList.size();
							if(aListSize>0){
								System.out.println("[log] 업데이트 성공!");
								request.setAttribute("aList",aList);
								RequestDispatcher dispatcher = request.getRequestDispatcher("member/updateKyjMember.jsp");
								dispatcher.forward(request, response);
							}else{
								System.out.println("[log] 업데이트 실패!");
								response.sendRedirect("/kyj/member/insertKyjMember.jsp");
							}
						}
					// 이미지 파일을 수정 했을 경우
					}else{
						KyjMemberDAO kdao = new KyjMemberDAOImpl();
						KyjMemberVO kvo = null;
						kvo = new KyjMemberVO();
						kvo.setKid(kid);
						kvo.setKpw(kpw);
						kvo.setKname(kname);
						kvo.setKbirth(kbirth);
						kvo.setKhp(khp);
						kvo.setKemail(kmail);
						kvo.setKpostno(kpostno);
						kvo.setKjuso(kjuso);
						kvo.setKjuso1(kjuso1);
						kvo.setKimage(kimage);
						
						boolean bFlag = kdao.updateKyjMember(kvo);
						if(bFlag){
							ArrayList<KyjMemberVO> aList=kdao.searchKyjMember(kvo);
							int aListSize=aList.size();
							if(aListSize>0){
								System.out.println("[log] 업데이트 성공!");
								request.setAttribute("aList",aList);
								RequestDispatcher dispatcher = request.getRequestDispatcher("member/updateKyjMember.jsp");
								dispatcher.forward(request, response);
							}else{
								System.out.println("[log] 업데이트 실패!");
								response.sendRedirect("/kyj/member/insertKyjMember.jsp");
							}
						}
					}
					
				}// if=u
				
				if("D".equals(isudType.toUpperCase())){
					kid = mr.getParameter("kid");
					System.out.println("kid >>> : " + kid);
					KyjMemberDAO kdao = new KyjMemberDAOImpl();
					KyjMemberVO kvo = null;
					kvo = new KyjMemberVO();
					kvo.setKid(kid);
					boolean bFlag = kdao.deleteKyjMember(kvo);
					if(bFlag){
						System.out.println("탈퇴 완료!");
						RequestDispatcher dispatcher = request.getRequestDispatcher("/member/deleteKyjMember.jsp");
						dispatcher.forward(request, response);
					}else{
						System.out.println("[log] 존재하지않은 회원이거나 탈퇴에 실패했습니다.");
						response.sendRedirect("/kyj/member/insertKyjMember.jsp");
					}
				} // if=d
				
				
			}else{
				System.out.println("isudType이 null임");
			}	
			
			
			
		}else if (!request.getContentType().toLowerCase().startsWith("multipart/form-data")){
			// 로그인
			memberType = request.getParameter("memberType");
			System.out.println("memberType >>> : " + memberType);
			
			
			if(memberType.equals("L")){		
	//		if("L".equals(memberType.toUpperCase())){
				String logid = request.getParameter("Iid");
			 	String logpw = request.getParameter("Ipw");
			 	System.out.println("logid >>> : " + logid);
				System.out.println("logpw >>> : " + logpw);	
				System.out.println(logid);
				System.out.println(logpw);
				
				// 백엔드biz에 있는 DAO와 프론트 연결작업
				// id pw 함수는 안만들었어서 여기서 만드는듯..?
				// value 호출할 때 데이터 null 체크 꼭 하기
				// http://localhost:8088/babyWeb/login.jsp
				// http://localhost:8088/babyWeb/login.jsp?id=aa&pw=bb <-쿼리 스트링으로 key&value 데이터 들어오나 테스트
				KyjMemberDAO kdao = new KyjMemberDAOImpl();
				KyjMemberVO kvo = null;
				kvo = new KyjMemberVO();
				kvo.setKid(logid);
				kvo.setKpw(logpw);
				
				ArrayList<KyjMemberVO> aList = kdao.loginKyjMember(kvo);
				System.out.println("" + aList.size());
				
				if(aList!=null && aList.size()>0){
					System.out.println("로그인 성공!!");
					out.println("kid >>> : " + kid);
					HttpSession hs = request.getSession();
					hs.setAttribute("id", "아집에가고싶다");
					String id = (String)hs.getAttribute("id");
					System.out.println("세션부여 id >>> : " + hs.getId());
					System.out.println("세션부여  value >>> : " + id);
					String ksession = hs.getId();
					System.out.println("ksession >>> : " + ksession);
					System.out.println("logid >>> : " + kvo.getKid());
					request.setAttribute("aList", aList);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/index_mem.jsp");
					dispatcher.forward(request, response);
				}else{ 
					System.out.println("로그인 실패!!");
					response.sendRedirect("/kyj/member/login.jsp");
				} // else if 문 안 else 문 끝
			} //if문 분기 끝지점
			
			
			
			if(memberType.equals("kIdCheck")){
				
			//	String kid = request.getParameter("kid");
				System.out.println("kid >>> : " + kid );
				
				KyjMemberDAO kdao = new KyjMemberDAOImpl();
				KyjMemberVO kvo = null;
				kvo = new KyjMemberVO();
				kvo.setKid(kid);
				
				int nCnt = 0;
				boolean bFlag = false;
				
				nCnt = kdao.idValueCkeck(kvo);
				
				if(nCnt==0){
					System.out.println("(log) jqueryIdCheck. 아이디 생성 가능");
					bFlag = true;
					System.out.println("(log) jqueryIdCheck."+  bFlag);
				}else if (nCnt>0){
					System.out.println("(log) jqueryIdCheck.  아이디 생성 ㄴㄴ");
					bFlag = false;
					System.out.println("(log) jqueryIdCheck."+  bFlag);
				}
				
				request.setAttribute("bFlag", new Boolean(bFlag));
				RequestDispatcher dispatcher = request.getRequestDispatcher("/member/kyjCheckXml.jsp");
				dispatcher.forward(request, response);
			}
			
			
			
		} // else if 끝	
	} // dopost 끝
		public void doGet(HttpServletRequest request, 
						  HttpServletResponse response) 
				   throws ServletException, IOException {
				doPost(request, response);
	} // doget 끝
}
