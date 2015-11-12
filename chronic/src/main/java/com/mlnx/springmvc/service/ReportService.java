package com.mlnx.springmvc.service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;

import com.mlnx.chronic.entity.BloodPressure;
import com.mlnx.chronic.entity.TBloodPressureCollection;
import com.mlnx.chronic.entity.TReport;
import com.mlnx.chronic.mapper.TBloodPressureCollectionMapper;
import com.mlnx.chronic.mapper.TBloodPressureSettingMapper;
import com.mlnx.chronic.mapper.TReportMapper;
import com.mlnx.chronic.mapper.TUserExtMapper;
import com.mlnx.chronic.repo.BloodPressureRepository;
import com.mlnx.chronic.vo.UserSetting;
import com.mlnx.springmvc.util.PropertiyUtil;

public class ReportService {

	@Autowired
	private TUserExtMapper userExtMapper;

	@Autowired
	private TBloodPressureSettingMapper tBloodPressureSettingMapper;

	@Autowired
	private TBloodPressureCollectionMapper tBloodPressureCollectionMapper;

	@Autowired
	private TReportMapper tReportMapper;

	@Autowired
	private BloodPressureRepository bloodPressureRepository;

	private Integer schedule;

	private String default_title;

	private String default_body;

	private String default_author;

	private String default_suggest;

	private ScheduledThreadPoolExecutor scheduler = new ScheduledThreadPoolExecutor(
			1);

	public void repeat(Runnable event, long initialDelay, long period) {
		scheduler.scheduleAtFixedRate(event, initialDelay, period,
				TimeUnit.SECONDS);
	}

	@PostConstruct
	public void start() {
		//定期执行任务
//		loadConfig(PropertiyUtil.serviceProperties);
//		GetUsers getUsers = new GetUsers();
//		repeat(getUsers, 0, schedule);
	}

	@PreDestroy
	public void destroy() {
		scheduler.shutdownNow();
	}

	class GetUsers implements Runnable {

		public void run() {
			try {
				List<UserSetting> list = tBloodPressureSettingMapper
						.selectAllSetting();// 找出所有用户的设置情况，和最后一次报告的时间
				Date now = new Date();
				TReport report = new TReport(default_title, default_body,
						default_author, default_suggest);
				for (UserSetting setting : list) {
					// 获取最后一次报告时间、为空就生成一则报告、非空就判断是否到了出报告时间
					if (setting.getTime() != null) {
						// 判断是否该出报告 当前时间-最后一次报告时间 > = 周期 *24*3600*1000
						if (now.getTime() - setting.getTime().getTime() >= (setting
								.getPeriodic() * 24 * 3600 * 1000)) {
							// TODO 扫描数据，采集数据，准备出报告
							collection(setting, setting.getTime(), now);
						}
					} else {
						report.setUserId(setting.getUserId());
						tReportMapper.insert(report);
					}

				}
				System.out.println("扫描了一次");
			} catch (Exception e) {
				System.out.println("扫描出现异常："+e.getMessage());
				throw new RuntimeException(e);
			}

		}

	}

	private void collection(UserSetting setting, Date time, Date now) {
		// 统计血压
		Iterator<BloodPressure> bloodPressures = bloodPressureRepository
				.findByPatientIdAndDateTimeRange(setting.getPatient_id(), time,
						now);
		int normal = 0;
		int high = 0;
		int low = 0;
		int total = 0;
		while (bloodPressures.hasNext()) {
			BloodPressure bp = bloodPressures.next();
			if (bp.getValue_diastolic() < setting.getLow()) {
				low++;
			}
			if (bp.getValue_systolic() > setting.getHigh()) {
				high++;
			}
			if (bp.getValue_diastolic() >= setting.getLow()
					&& bp.getValue_systolic() <= setting.getHigh()) {
				normal++;
			}
			total++;
		}
		TBloodPressureCollection blpCollection = new TBloodPressureCollection(
				high, low, normal, total, setting.getUserId());
		tBloodPressureCollectionMapper.insert(blpCollection);

		// 统计完血压数据，做出报告
		String title = "mlnx血压报告";
		String author = "mlnx-system";
		String body = String
				.format("您本次报告共统计数据%d条，其中超过设置的收缩压值的次数为%d次，低于设置的舒张压的次数为%d次，介于2者之间的次数为%d次",
						total, high,
						low, normal);
		String suggest = "建议平时多测量";
		TReport record = new TReport(title, body, author, suggest);
		record.setUserId(setting.getUserId());
		tReportMapper.insert(record);
	}


	private void loadConfig(String serviceProperties) {
		Properties p = PropertiyUtil
				.loadProperty(PropertiyUtil.serviceProperties);
		schedule = Integer.parseInt(p.getProperty("service.report.schedule"));
		default_title = p.getProperty("service.report.default.title");
		default_body = p.getProperty("service.report.default.body");
		default_author = p.getProperty("service.report.default.author");
		default_suggest = p.getProperty("service.report.default.suggest");
		System.out.println(String.format(
				"title:%s,body:%s,author:%s,suggest:%s", default_title,
				default_body, default_author, default_suggest));
	}

}
