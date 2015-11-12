package com.mlnx.chronic.vo;

import java.util.List;

import com.mlnx.chronic.entity.TReport;
import com.mlnx.chronic.entity.TUser;

public class UserVo extends TUser {
	
	private List<TReport> tReports;

	public List<TReport> gettReports() {
		return tReports;
	}

	public void settReports(List<TReport> tReports) {
		this.tReports = tReports;
	}

	
}
