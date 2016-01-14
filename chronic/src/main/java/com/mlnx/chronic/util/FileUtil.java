package com.mlnx.chronic.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
	public static String savePic(HttpServletRequest request, MultipartFile file) {
		String path = request.getSession().getServletContext().getRealPath("/");
		int a = path.indexOf("webapps");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String today = sdf.format(new Date());
		String realPath = path.substring(0, a + 7) + "/upload/" + today;// 指定项目同级下的一个目录，永久保存,根据日期分包，有利于提高i/o性能
		String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
		// String fileName = new Date().getTime()+".jpg";
		File targetFile = new File(realPath, fileName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		// 保存
		try {
			file.transferTo(targetFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String pic = "/upload/" + today + "/" + fileName;
		return pic;
	}
	
	public static String savePic(HttpServletRequest request, InputStream in) throws Exception {
		String path = request.getSession().getServletContext().getRealPath("/");
		int a = path.indexOf("webapps");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String today = sdf.format(new Date());
		String realPath = path.substring(0, a + 7) + "/upload/" + today;// 指定项目同级下的一个目录，永久保存,根据日期分包，有利于提高i/o性能
		String fileName = UUID.randomUUID() +".png";
		// String fileName = new Date().getTime()+".jpg";
		File targetDir = new File(realPath);
		if(!targetDir.exists()){
			targetDir.mkdirs();
		}
		File targetFile = new File(realPath, fileName);
		if (!targetFile.exists()) {
			targetFile.createNewFile();
		}
		  FileOutputStream fileStream  =null;
		// 保存
		try {
			 /* 创建存取文件 */  
		    fileStream = new FileOutputStream(targetFile); 
		    byte[] b = new byte[1024];
		    int i = 0;
			while ((i = in.read(b)) > 0){
				/* 写入流 */  
				fileStream.write(b, 0, i);  
		    }
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			fileStream.close();
		}
		String pic = "/upload/" + today + "/" + fileName;
		return pic;
	}
}
