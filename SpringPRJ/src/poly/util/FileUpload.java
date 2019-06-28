package poly.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
 
 
public class FileUpload {
    String fileName = "";
    
    public Map<String,Object> fileUpload(MultipartHttpServletRequest request,
                                        MultipartFile uploadFile) {
        String path = "";
        String fileName = "";
        String extension = "";
        
        Map<String, Object> map = new HashMap<>();
        
        OutputStream out = null;
        PrintWriter printWriter = null;
        
        try {
            fileName = uploadFile.getOriginalFilename();
            byte[] bytes = uploadFile.getBytes();
//            String uploadPath = request.getSession().getServletContext().getRealPath("/");  // 현재 프로젝트 위치
            String uploadPath = "C:\\Users\\data21\\git\\Project\\SpringPRJ\\WebContent\\WEB-INF";
            String attachPath = "\\upload\\";
            path = uploadPath+attachPath;
            
            System.out.println("fileUpload fileName : " + fileName);
            map.put("originalFileName", fileName);
            int i = fileName.lastIndexOf('.');
            if (i > 0) {
                extension = fileName.substring(i+1); //extension은 확장자
            }
            System.out.println(extension);
            map.put("extension", extension);
            System.out.println("fileUpload uploadPath : " + path);
            
            File file = new File(path);
            
//          경로 폴더 없다면 만들기
            if(!file.exists()){
    			file.mkdirs();
  		  	}
          
//          파일명이 중복으로 존재할 경우
            if (fileName != null && !fileName.equals("")) {
                if (file.exists()) {
//                    파일명 앞에 업로드 시간 초단위로 붙여 파일명 중복을 방지
                    fileName = System.currentTimeMillis() + "_" + fileName;
                    
                    file = new File(path + fileName);
                }
            }
            
            System.out.println("fileUpload final fileName : " + fileName);
            map.put("fileName", fileName);
            System.out.println("fileUpload file : " + file.getPath());
            map.put("path", file.getPath());
            out = new FileOutputStream(file);
            
            System.out.println("fileUpload out : " + out);
            
            out.write(bytes);//파일 등록
            
            System.out.println("fileUpload fileSize : " + file.length());
            map.put("fileSize",file.length());
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (printWriter != null) {
                    printWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            
        }
        
        
        
       
        
        return map;
    }
    
    
}