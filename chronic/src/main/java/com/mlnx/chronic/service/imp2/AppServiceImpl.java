package com.mlnx.chronic.service.imp2;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mlnx.chronic.entity2.TApp;
import com.mlnx.chronic.mapper2.TAppMapper;
import com.mlnx.chronic.service2.AppService;
import com.mlnx.chronic.util.ChronicResponse;
import com.mlnx.chronic.util.FileUtil;
import com.mlnx.chronic.util.EnumCollection.ResponseCode;
import com.mlnx.chronic.util.StringUtil;

@Service
public class AppServiceImpl implements AppService {

	@Autowired
	private TAppMapper tAppMapper;

	@Override
	public ChronicResponse save(HttpServletRequest request, MultipartFile file,
			TApp app) {
		try {
			TApp tApp = tAppMapper.selectByName(app);//根据app名称和版本查找
			if (file != null && file.getOriginalFilename() != "") {
				String appPath = FileUtil.saveApp(request, file, app.getName(),
						app.getVersion());
				app.setPath(appPath);
				if(tApp !=null ){//如果名称和版本存在记录，就覆盖它
					tAppMapper.updateByPrimaryKey(tApp);
				} else {//否则就新增记录
					tAppMapper.insert(app);
				}
				return new ChronicResponse(ResponseCode.UPLOAD_APP_SUCCESS);
			} else {
				return new ChronicResponse(ResponseCode.UPLOAD_APP_FILE_NONE);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ChronicResponse(ResponseCode.UPLOAD_APP_ERROR);
		}

	}

	@Override
	public Map<String, Object> search(String name) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			TApp app = tAppMapper.searchLastVersionByName(name);
			map.put(StringUtil.responseCode,
					ResponseCode.SEARCH_APP_INFO_SUCCESS.getCode());
			map.put(StringUtil.responseMsg,
					ResponseCode.SEARCH_APP_INFO_SUCCESS.getMsg());
			map.put(StringUtil.responseObj, app);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			map.put(StringUtil.responseCode,
					ResponseCode.SEARCH_APP_INFO_ERROR.getCode());
			map.put(StringUtil.responseMsg,
					ResponseCode.SEARCH_APP_INFO_ERROR.getMsg());
			return map;
		}

	}

	@Override
	public InputStream download(HttpServletRequest request, String name) {
		TApp app = tAppMapper.searchLastVersionByName(name);
		String filePath = app.getPath();
		return FileUtil.downloadLastVersionApp(request, name, filePath);
	}

}
