package com.privateblog.common.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import com.privateblog.config.ConstantProperties;
import com.privateblog.model.ImageUploadResult;

public class FileUtils {

	public static ImageUploadResult ImgUpload(MultipartFile image ) throws IllegalStateException, IOException {
		ImageUploadResult result = new ImageUploadResult();
		
		
		//文件上传的地址
        String path;
		try {
			path = ResourceUtils.getURL("classpath:").getPath()+ConstantProperties.imgUpload;
			
			 String realPath = path.replace('/', '\\').substring(1,path.length());
		     //用于查看路径是否正确
		     System.out.println(realPath);
		      
		     //获取文件的名称
	         String fileName = image.getOriginalFilename();
	         //获取文件名        
	         String caselsh = fileName.substring(0,fileName.lastIndexOf("."));
	         //获取文件名后缀 
	         String suffix  = fileName.substring(fileName.lastIndexOf(".")+1);
	         
	         // 添加后缀UUID
	         fileName = caselsh + UUID.randomUUID().toString() + "." + suffix;
	         
	         
	         //限制文件上传的类型
	         String contentType = image.getContentType();
	         if("image/jpeg".equals(contentType) || "image/jpg".equals(contentType) ) {
	        	 File file = new File(realPath,fileName);
	        	//完成文件的上传
	        	 image.transferTo(file);
	        	 result.fileName = fileName;
	        	 result.urlPath = "/user/images/"+fileName;
	        	 
	         }
	         
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
       
		return result;
	}
}
