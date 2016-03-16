package com.mlnx.chronic.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mlnx.chronic.entity2.TApp;
import com.mlnx.chronic.service2.AppService;
import com.mlnx.chronic.util.ChronicResponse;
import com.mlnx.chronic.util.EnumCollection.ResponseCode;
import com.mlnx.chronic.util.FileUtil;

@Controller
@RequestMapping(value = "/app")
public class APPCol {

	@Autowired
	private AppService appService;

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(MultipartFile file,
			@RequestParam("name") String name,
			@RequestParam("version") String version,
		    @RequestParam("content") String content,
		    @RequestParam("stable") String stable, HttpServletRequest request,
			HttpServletResponse response) {
		TApp app = new TApp();
		app.setName(name);
		app.setStable(stable);
		app.setVersion(version);
		app.setContent(content);
		ChronicResponse cr = appService.save(request, file, app);
		if(cr.getResponseCode().equals(ResponseCode.UPLOAD_APP_SUCCESS.getCode())){
			return "app/success";
		} else {
			return "app/error";
		}
	}

	@RequestMapping("/download/{version:.*}/{file}")
	public void download(@PathVariable("file") String file,
			@PathVariable("version") String version,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		InputStream in = FileUtil.downloadApp(request, file, version);
		response.setContentType("application/x-msdownload;");
		response.setContentLength(in.available());
		java.io.BufferedInputStream bis = null;
		java.io.BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(in);
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
	}
	
	/**
	 * 客户端上床软件
	 * @param file
	 * @param name
	 * @param version
	 * @param content
	 * @param stable
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/upload/app", method = RequestMethod.POST)
	@ResponseBody
	public ChronicResponse uploadApp(MultipartFile file,
			@RequestHeader("name") String name,
			@RequestHeader("version") String version,
		    @RequestParam("content") String content,
		    @RequestParam("stable") String stable, HttpServletRequest request,
			HttpServletResponse response) {
		TApp app = new TApp();
		app.setName(name);
		app.setStable(stable);
		app.setVersion(version);
		app.setContent(content);
		return appService.save(request, file, app);
	}

	@RequestMapping(value = "/search/app", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> search(@RequestHeader("name") String name) {
		return appService.search(name);
	}

	@RequestMapping(value = "/download/app", method = RequestMethod.POST)
	public void downloadLastVersion(@RequestHeader("name") String name,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		InputStream in =  appService.download(request, name);
		response.setContentType("application/x-msdownload;");
		response.setContentLength(in.available());
		java.io.BufferedInputStream bis = null;
		java.io.BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(in);
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}

	}

	@RequestMapping("/view")
	public String appUpload() {
		return "app/upload";
	}
}
