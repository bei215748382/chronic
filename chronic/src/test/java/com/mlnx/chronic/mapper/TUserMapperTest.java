package com.mlnx.chronic.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mlnx.chronic.vo.UserVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:applicationContext.xml" })
public class TUserMapperTest {
	@Autowired
	private TUserMapper tUserMapper;
	@Autowired
	private TReportMapper tReportMapper;

	@Test
	public void selectTReportsByPrimaryKey() {
		UserVo userVo = tUserMapper.selectTReportsByPrimaryKey(12);
		userVo.gettReports();
		
	}

}
