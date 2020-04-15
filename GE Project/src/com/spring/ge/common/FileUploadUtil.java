package com.spring.ge.common;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public abstract class FileUploadUtil {
	
	/*파일 업로드 메서드*/
	public static String fileUpload(MultipartFile file, HttpServletRequest request)
																throws IOException{
		System.out.println("fileUpload 호출 성공");
		System.out.println("file " + file);
		String real_name=null;
		
		//MultipartFile 클래스의 getFile() 메서드로 클라이언트가 업로드한 파일명
		String org_name = file.getOriginalFilename();
		System.out.println("org_name : " + org_name);
		
		//파일이 없으면
		if(org_name.length()==0){
			
			real_name = "boardDept_1582523831916_BoardDepartment.jpg";
			System.out.println("파일없음");
			return real_name;
		}
	
		//파일명 변경 (중복되지 않게)
		if(org_name != null && (!org_name.equals(""))){
			real_name = "boardDept_" + System.currentTimeMillis() +"_" + org_name;
			String docRoot = request.getSession().getServletContext().getRealPath("/uploadStorage");
					
			File fileDir = new File(docRoot);
			if(!fileDir.exists()){
				fileDir.mkdir();
			}
					
			File fileAdd = new File(docRoot+"/"+real_name);
			System.out.println("경로 : " + fileAdd);
					
			file.transferTo(fileAdd);
		}
		
		return real_name;	
	}
	
	/*파일 삭제 메서드*/
	public static void fileDelete(String fileName, HttpServletRequest request)
														throws IOException{
		System.out.println("fileDelete 호출 성공");
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
