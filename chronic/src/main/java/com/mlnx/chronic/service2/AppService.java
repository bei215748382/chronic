package com.mlnx.chronic.service2;

import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.mlnx.chronic.entity2.TApp;
import com.mlnx.chronic.util.ChronicResponse;

public interface AppService {
	public ChronicResponse save(HttpServletRequest request, MultipartFile file, TApp app);
	
	public Map<String,Object> search(String name);
	
	public InputStream download(HttpServletRequest request,String name);
}
