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
			// ���Ͼ��ε�
			MultipartRequest mr = new MultipartRequest(request,
														upLoadPath,
														size,
														"EUC-KR",
														new DefaultFileRenamePolicy());
			// isudType null Ȯ��
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
					// ������ Ȯ��
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
							System.out.println("[log] �Է� ����");
							
							if(true){
								KyjMemberDAO kdao_ = new KyjMemberDAOImpl();
								KyjMemberVO _kvo = null;
								_kvo = new KyjMemberVO();
								_kvo.setKid(kid);
								ArrayList<KyjMemberVO> aList_ = kdao_.searchKyjMember(_kvo);
								if(aList_.size()==0){
									System.out.println("�������� �ʴ� ȸ���Դϴ�");
								}else{
									System.out.println("[log] �˻� ����");
									request.setAttribute("aList",aList);
									RequestDispatcher dispatcher = request.getRequestDispatcher("member/selectKyjMember.jsp");
									dispatcher.forward(request, response);
								}
							}							
						}else{
							System.out.println("[log] �Է� ����");
							out.println("<script>alert('ȸ������ ����');</script>");
							response.sendRedirect("/kyj/member/insertKyjMember.jsp");
						}
					} // bflag if��
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
						System.out.println("�������� �ʴ� ȸ���Դϴ�");
						out.println("<script>alert('�������� �ʴ� ȸ���Դϴ�'); location.href='/kyj/member/insertKyjMember.jsp';</script>");
				//		response.sendRedirect("/kyj/member/insertKyjMember.jsp");
					}else{
						System.out.println("[log] �˻� ����");
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
						System.out.println("ȸ���� �����ϴ�");
						response.sendRedirect("/kyj/member/insertKyjMember.jsp");
					}else{
						System.out.println("[log] �˻� ����");
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
					// ������ Ȯ��
					System.out.println(kid+"/"+kpw+"/"+kname+"/"+
									   khp+"/"+kbirth+"/"+kmail+"/"+
									   kpostno+"/"+kjuso+"/"+kjuso1+"/"+
									   kimage);
					// �̹��� ���� ������ ������ ���
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
								System.out.println("[log] ������Ʈ ����!");
								request.setAttribute("aList",aList);
								RequestDispatcher dispatcher = request.getRequestDispatcher("member/updateKyjMember.jsp");
								dispatcher.forward(request, response);
							}else{
								System.out.println("[log] ������Ʈ ����!");
								response.sendRedirect("/kyj/member/insertKyjMember.jsp");
							}
						}
					// �̹��� ������ ���� ���� ���
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
								System.out.println("[log] ������Ʈ ����!");
								request.setAttribute("aList",aList);
								RequestDispatcher dispatcher = request.getRequestDispatcher("member/updateKyjMember.jsp");
								dispatcher.forward(request, response);
							}else{
								System.out.println("[log] ������Ʈ ����!");
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
						System.out.println("Ż�� �Ϸ�!");
						RequestDispatcher dispatcher = request.getRequestDispatcher("/member/deleteKyjMember.jsp");
						dispatcher.forward(request, response);
					}else{
						System.out.println("[log] ������������ ȸ���̰ų� Ż�� �����߽��ϴ�.");
						response.sendRedirect("/kyj/member/insertKyjMember.jsp");
					}
				} // if=d
				
				
			}else{
				System.out.println("isudType�� null��");
			}	
			
			
			
		}else if (!request.getContentType().toLowerCase().startsWith("multipart/form-data")){
			// �α���
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
				
				// �鿣��biz�� �ִ� DAO�� ����Ʈ �����۾�
				// id pw �Լ��� �ȸ����� ���⼭ ����µ�..?
				// value ȣ���� �� ������ null üũ �� �ϱ�
				// http://localhost:8088/babyWeb/login.jsp
				// http://localhost:8088/babyWeb/login.jsp?id=aa&pw=bb <-���� ��Ʈ������ key&value ������ ������ �׽�Ʈ
				KyjMemberDAO kdao = new KyjMemberDAOImpl();
				KyjMemberVO kvo = null;
				kvo = new KyjMemberVO();
				kvo.setKid(logid);
				kvo.setKpw(logpw);
				
				ArrayList<KyjMemberVO> aList = kdao.loginKyjMember(kvo);
				System.out.println("" + aList.size());
				
				if(aList!=null && aList.size()>0){
					System.out.println("�α��� ����!!");
					out.println("kid >>> : " + kid);
					HttpSession hs = request.getSession();
					hs.setAttribute("id", "����������ʹ�");
					String id = (String)hs.getAttribute("id");
					System.out.println("���Ǻο� id >>> : " + hs.getId());
					System.out.println("���Ǻο�  value >>> : " + id);
					String ksession = hs.getId();
					System.out.println("ksession >>> : " + ksession);
					System.out.println("logid >>> : " + kvo.getKid());
					request.setAttribute("aList", aList);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/index_mem.jsp");
					dispatcher.forward(request, response);
				}else{ 
					System.out.println("�α��� ����!!");
					response.sendRedirect("/kyj/member/login.jsp");
				} // else if �� �� else �� ��
			} //if�� �б� ������
			
			
			
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
					System.out.println("(log) jqueryIdCheck. ���̵� ���� ����");
					bFlag = true;
					System.out.println("(log) jqueryIdCheck."+  bFlag);
				}else if (nCnt>0){
					System.out.println("(log) jqueryIdCheck.  ���̵� ���� ����");
					bFlag = false;
					System.out.println("(log) jqueryIdCheck."+  bFlag);
				}
				
				request.setAttribute("bFlag", new Boolean(bFlag));
				RequestDispatcher dispatcher = request.getRequestDispatcher("/member/kyjCheckXml.jsp");
				dispatcher.forward(request, response);
			}
			
			
			
		} // else if ��	
	} // dopost ��
		public void doGet(HttpServletRequest request, 
						  HttpServletResponse response) 
				   throws ServletException, IOException {
				doPost(request, response);
	} // doget ��
}
