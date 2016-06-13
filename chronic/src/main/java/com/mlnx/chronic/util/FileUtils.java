package com.mlnx.chronic.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

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

	/**
	 * 上传多文件
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public static List<String> uploadFiles(HttpServletRequest request,
			HttpServletResponse response) throws IllegalStateException,
			IOException {
		List<String> list = new ArrayList<String>();
		// 创建一个通用的多部分解析器
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 判断 request 是否有文件上传,即多部分请求
		if (multipartResolver.isMultipart(request)) {
			// 转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 取得request中的所有文件名
			Iterator<String> iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				// 记录上传过程起始时的时间，用来计算上传时间
				int pre = (int) System.currentTimeMillis();
				// 取得上传文件
				MultipartFile file = multiRequest.getFile(iter.next());
				if (file != null) {
					// 取得当前上传文件的文件名称
					String myFileName = file.getOriginalFilename();
					// 如果名称不为“”,说明该文件存在，否则说明该文件不存在
					if (myFileName.trim() != "") {
						String realPath = getRealPath(request);
						String fileName = UUID.randomUUID() + "_"
								+ file.getOriginalFilename();// 重命名文件名
						File targetFile = new File(realPath, fileName);
						if (!targetFile.exists()) {
							targetFile.mkdirs();
						}
						list.add(fileName);
						try {
							file.transferTo(targetFile);// 保存
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				// 记录上传该文件后的时间
				int finaltime = (int) System.currentTimeMillis();
				System.out.println("上传一个文件的时间：" + (finaltime - pre) + "ms");
			}
		}
		return list;
	}
}
