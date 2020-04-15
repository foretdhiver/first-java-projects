package com.spring.ge.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.spring.ge.controller.EaController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class EaFileReadUtil {
	
	private static Logger logger=Logger.getLogger(EaController.class);
	
	public static void readFile(HttpServletRequest request
			                   ,HttpServletResponse response) throws IOException
	{
		logger.info("[log] FileReadUtil.readFile 진입 >>> ");

		request.setCharacterEncoding("EUC-KR");
		String fileName = (String)request.getAttribute("ea_file");
		String filePath = (String)request.getAttribute("filePath");
		
//		fileName = fileName.replace("eaupload//", "");
		
		logger.info("fileName, filePath : " + fileName + ", " + filePath);
		
	//	String filePath1 = filePath+"//"+fileName;
		
		String realFilePath = filePath+"//"+fileName ;
				
		logger.info("filePath : " + realFilePath);
		File file = new File(realFilePath);
		
		if (file.isDirectory()){
			return;
		}
		
		long fileSize = file.length();
		if (fileSize > Integer.MAX_VALUE){
			System.out.println("File size is too big. >>> : ");
			return;
		}
		
		 // mimetype
		String mimeType;
		String disposition = "filename=\"" + realFilePath + "\"";
		
		if(filePath.toLowerCase().endsWith("xml")){
			mimeType = "text/xml;charset=euc-kr";
		}else if(realFilePath.toLowerCase().endsWith("doc")){
			mimeType = "application/msword";
		}else if(realFilePath.toLowerCase().endsWith("xls")){
			mimeType = "application/vnd.ms-excel";
		}else if(realFilePath.toLowerCase().endsWith("xlsx")){
			mimeType = "application/x-msexcel";
		}else if(realFilePath.toLowerCase().endsWith("hwp")){
			mimeType = "application/octet-stream";
		}else if(realFilePath.toLowerCase().endsWith("gif")){
			mimeType = "image/gif";
		}else if(realFilePath.toLowerCase().endsWith("jpg")){			
			mimeType = "image/jpeg";
		}else{
			mimeType = "application/octet-stream";
		}
		
		response.setBufferSize(0);
		
//		// set headers
//		final String userAgent = request.getHeader("User-Agent");
//		if (null != userAgent && 
//			(userAgent.indexOf("MSIE 10.0") > -1 || userAgent.indexOf("MSIE 11.0") > -1)){
//			response.setHeader("Content-Disposition", "filename=\"" + filePath + "\"");
//		}else{
//			disposition = 
//				mimeType.equals("application/octet-stream") ? "attachment" : "inline";
//			
//			response.setHeader("Content-Disposition", 
//				disposition + ";filename=\"" + filePath + "\"");
//		}
		
		fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
		response.setHeader("Content-Disposition","attachment; filename=" + fileName + ";");
		
//		response.setHeader("Content-Disposition", disposition);
		response.setHeader("Content-Transfer-Encoding", "7bit");
		response.setHeader("Accept-Ranges", "bytes");
		response.setContentLength((int)fileSize);
		response.setHeader("Connection", "close");
		response.setContentType(mimeType + ";charset=EUC-KR");

		FileInputStream in = new FileInputStream(file);
		
		final int BUF_SIZE = 8 * 1024;
		final OutputStream out = response.getOutputStream();
		final byte[] buf = new byte[BUF_SIZE];
		int n;
		
		while(-1 != (n = in.read(buf)))
		{
			out.write(buf, 0, n);
		}
		out.flush();
		logger.info("[log] FileReadUtil.readFile 종료 <<< ");
	}
}
