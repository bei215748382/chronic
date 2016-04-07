package com.mlnx.chronic.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * 全路径的文件上传
 * 
 * @author bwh
 * 
 */
public class FileUtils {

	private static String getRealPath(HttpServletRequest request) {
		String path = request.getSession().getServletContext().getRealPath("/");
		int a = path.indexOf("webapps");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String today = sdf.format(new Date());
		String realPath = path.substring(0, a + 7) + "/upload/" + today;// 指定项目同级下的一个目录，永久保存,根据日期分包，有利于提高i/o性能
		return realPath;
	}

	/**
	 * 根据表单提交多媒体文件，保存图片
	 * 
	 * @param request
	 * @param file
	 * @return
	 */
	public static String savePic(HttpServletRequest request, MultipartFile file) {
		String realPath = getRealPath(request);
		String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
		File targetFile = new File(realPath, fileName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		try {
			file.transferTo(targetFile);// 保存
		} catch (Exception e) {
			e.printStackTrace();
		}
		return realPath + "/" + fileName;
	}

	/**
	 * 根据输入流保存图片
	 * 
	 * @param request
	 * @param in
	 * @return
	 * @throws Exception
	 */
	public static String savePic(HttpServletRequest request, InputStream in)
			throws Exception {
		String realPath = getRealPath(request);
		String fileName = UUID.randomUUID() + ".png";
		File targetDir = new File(realPath);
		if (!targetDir.exists()) {
			targetDir.mkdirs();
		}
		File targetFile = new File(realPath, fileName);
		if (!targetFile.exists()) {
			targetFile.createNewFile();
		}
		FileOutputStream fileStream = null;
		try {
			fileStream = new FileOutputStream(targetFile);
			byte[] b = new byte[1024];
			int i = 0;
			while ((i = in.read(b)) > 0) {
				fileStream.write(b, 0, i);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			fileStream.close();
		}
		return realPath + "/" + fileName;
	}

	/**
	 * 根据表单提交文件和版本号，保存app
	 * 
	 * @param request
	 * @param file
	 * @param version
	 * @return
	 */
	public static String saveApp(HttpServletRequest request,
			MultipartFile file, String version) {
		String realPath = getRealPath(request) + "/app";
		String fileName = file.getOriginalFilename();
		File targetFile = new File(realPath, fileName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		try {
			file.transferTo(targetFile); // 保存
		} catch (Exception e) {
			e.printStackTrace();
		}
		return realPath + "/" + fileName;
	}

	/**
	 * 根据文件名和版本号，下载app
	 * 
	 * @param request
	 * @param fileName
	 * @param version
	 * @return
	 */
	public static InputStream downloadApp(HttpServletRequest request,
			String fileName, String version) {
		String realPath = getRealPath(request) + "/app";
		File targetFile = new File(realPath, fileName);
		try {
			InputStream inputStream = new FileInputStream(targetFile);
			return inputStream;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据包名保存app
	 * 
	 * @param request
	 * @param file
	 * @param name
	 * @param version
	 * @return
	 */
	public static String saveApp(HttpServletRequest request,
			MultipartFile file, String name, String version) {
		String realPath = getRealPath(request) + "/app";
		File targetFile = new File(realPath, name);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		try {
			file.transferTo(targetFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return realPath + "/" + name;
	}

	/**
	 * 根据文件名和版本号，下载app
	 * 
	 * @param request
	 * @param fileName
	 * @param version
	 * @return
	 */
	public static InputStream downloadLastVersionApp(
			HttpServletRequest request, String fileName, String filePath) {
		String path = request.getSession().getServletContext().getRealPath("/");
		int a = path.indexOf("webapps");
		String realPath = path.substring(0, a + 7) + filePath;// 指定项目同级下的一个目录，永久保存,根据日期分包，有利于提高i/o性能
		File targetFile = new File(realPath);
		try {
			InputStream inputStream = new FileInputStream(targetFile);
			return inputStream;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String uploadFiles(CommonsMultipartFile[] files,
			HttpServletRequest request) {
		return null;
	}
}
