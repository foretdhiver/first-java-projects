<%@ page import="java.io.FileInputStream"%>
<%@ page import="java.io.File"%>
<%@ page import="java.io.*" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
  	request.setCharacterEncoding("EUC-KR"); // 
  	
    String filename = request.getParameter("pbfilepath");
    System.out.println(" >>> "+filename);
    
    String         savepath = "upload";
    ServletContext context  = this.getServletContext();
    String DownloadPath    = context.getRealPath(savepath);
    String FilePath        = DownloadPath + "\\" + filename;
    
    byte [] b = new byte[4096];
    File oFile = new File(FilePath);
    
    FileInputStream fis = new FileInputStream(oFile);
    
    String sMimeType = getServletContext().getMimeType(FilePath);
    System.out.println("MimeType: " +  sMimeType); 
    
    if( sMimeType == null ) sMimeType = "application/octet-stream";
    response.setContentType(sMimeType);
    
 
    String sEncoding = new String(filename.getBytes("EUC-KR"), "8859_1");
    response.setHeader("Content-Disposition", 
        "attachment; filename=" + sEncoding);
    out.clear();

    out=pageContext.pushBody(); 

    ServletOutputStream out2 = response.getOutputStream();
    int numRead;
    
    while((numRead = fis.read(b, 0, b.length))!= -1) {
        out2.write(b, 0, numRead);
    }
  
    out2.flush();
    out2.close();
    fis.close();    
%>
