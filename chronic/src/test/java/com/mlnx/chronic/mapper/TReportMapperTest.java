package com.mlnx.chronic.mapper;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mlnx.chronic.entity.TReport;

public class TReportMapperTest extends TestBase{
@Autowired
private TReportMapper tReportMapper;
	@Test
	public void testInsert() {
		TReport report = new TReport("标题","内容","mlxn-sy","建议平时多测量");
		report.setUserId(1);
		tReportMapper.insert(report);
	}

}
