package com.spring.ge.common;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public abstract class FileUploadUtil {
	
	/*���� ���ε� �޼���*/
	public static String fileUpload(MultipartFile file, HttpServletRequest request)
																throws IOException{
		System.out.println("fileUpload ȣ�� ����");
		System.out.println("file " + file);
		String real_name=null;
		
		//MultipartFile Ŭ������ getFile() �޼���� Ŭ���̾�Ʈ�� ���ε��� ���ϸ�
		String org_name = file.getOriginalFilename();
		System.out.println("org_name : " + org_name);
		
		//������ ������
		if(org_name.length()==0){
			
			real_name = "boardDept_1582523831916_BoardDepartment.jpg";
			System.out.println("���Ͼ���");
			return real_name;
		}
	
		//���ϸ� ���� (�ߺ����� �ʰ�)
		if(org_name != null && (!org_name.equals(""))){
			real_name = "boardDept_" + System.currentTimeMillis() +"_" + org_name;
			String docRoot = request.getSession().getServletContext().getRealPath("/uploadStorage");
					
			File fileDir = new File(docRoot);
			if(!fileDir.exists()){
				fileDir.mkdir();
			}
					
			File fileAdd = new File(docRoot+"/"+real_name);
			System.out.println("��� : " + fileAdd);
					
			file.transferTo(fileAdd);
		}
		
		return real_name;	
	}
	
	/*���� ���� �޼���*/
	public static void fileDelete(String fileName, HttpServletRequest request)
														throws IOException{
		System.out.println("fileDelete ȣ�� ����");
		boolean result = false;
		String docRoot = request.getSession().getServletContext().getRealPath("/uploadStorage");
		
		File fileDelete = new File(docRoot+""+fileName);
		System.out.println("fileDelete >>> " + fileDelete);
		
		if(!fileDelete.exists() && fileDelete.isFile()){
			result = fileDelete.delete();
		}
		
		System.out.println("result >>> " + result);
	}

}
