package com.mlnx.chronic.controller;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mlnx.chronic.ecg.ECGDeviceConstant;
import com.mlnx.chronic.ecg.RealTimeEcgRequest;
import com.mlnx.chronic.ecg.RealTimeEcgResponse;

@Controller
@RequestMapping(value = "/ecg")
public class EcgCol {

	@RequestMapping("/index")
	public String index() {
		return "ecg";
	}

	@RequestMapping("/y")
	@ResponseBody
	public Double getY() throws IOException {
		Double y = null;
		RealTimeEcgRequest rtecg = new RealTimeEcgRequest();
		RealTimeEcgResponse response = rtecg.getFirstResponse();
		try {
			Integer bpmTemp = response.getHeartRate();
			if (bpmTemp != null) {
				if (bpmTemp.intValue() != 0)
					System.out.println("heart rate:" + bpmTemp);
			}
			DataInputStream dataIn = response.getDataInputStream();
			try {
				while (true) {
					int value = dataIn.readShort() & 0xFFFF;
					y = (double) ECGDeviceConstant.ECGConvertor(value);
					System.out.println("y值为：" + y);
				}

			} catch (Exception e) {
				System.out.println("No more data to read");
				// countDown = 10;
			} finally {
				dataIn.close();
			}
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return y;
	}

	@RequestMapping("/rt")
	public String rt() {
		return "rt";
	}

	@RequestMapping("/rt/data")
	public void data(HttpServletResponse response) throws IOException {
		response.setContentType("text/event-stream");
		response.setHeader("Cache-Control", "no-cache");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		for (int i = 0; i < 10; i++) {
			writer.write("data " + i + " : " + System.currentTimeMillis()
					+ "\n\n");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		writer.close();
	}
}
