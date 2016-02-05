package com.mlnx.springmvc.util;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;

public class RealTimeEcgRequest {

	public RealTimeEcgRequest() {

	}

	public RealTimeEcgResponse get() {
		return getFirstResponse();
	}

	RealTimeEcgResponse getFirstResponse() {
		InputStream is = null;
		HttpURLConnection connection = null;
		RealTimeEcgResponse rtecg = new RealTimeEcgResponse(this);
		try {
			String httpUrl = "http://121.40.137.14:8080/pms-server/rest/rtecg/40024";
			URL url = new URL(httpUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("X-MLNX-ECG-Lead",
					"I,II,III,aVR,aVL,aVF,V1,V2,V3,V4,V5,V6");
			connection.connect();
			return setValues(connection, rtecg, is);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				connection.disconnect();
			}
		}
		return null;

	}

	RealTimeEcgResponse getNextResponse(String token) {
		InputStream is = null;
		HttpURLConnection connection = null;
		RealTimeEcgResponse rtecg = new RealTimeEcgResponse(this);
		try {
			String httpUrl = "http://121.40.137.14:8080/pms-server/rest/rtecg/40024";
			URL url = new URL(httpUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("X-MLNX-ECG-Lead",
					"I,II,III,aVR,aVL,aVF,V1,V2,V3,V4,V5,V6");
			connection
					.setRequestProperty(HttpConstants.ECG_TOKEN_HEADER, token);
			connection.connect();
			return setValues(connection, rtecg, is);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				connection.disconnect();
			}
		}
		return null;
	}

	private RealTimeEcgResponse setValues(HttpURLConnection connection,
			RealTimeEcgResponse rtecg, InputStream is) throws IOException {
		Integer patientId = null;
		Integer pose = null;
		Date lastEmergencyCallTime = null;
		String patientIdStr = connection
				.getHeaderField(HttpConstants.PATIENT_ID_HEADER);
		String poseStr = connection
				.getHeaderField(HttpConstants.ACCELERATION_HEADER);
		String lastEmergencyCallTimeStr = connection
				.getHeaderField(HttpConstants.CALL_TIME_HEADER);
		if (patientIdStr != null) {
			patientId = Integer.parseInt(patientIdStr);
		}
		List<EcgLead> leadList = EcgLead.split(connection
				.getHeaderField(HttpConstants.ECG_LEAD_HEADER));
		String token = connection
				.getHeaderField(HttpConstants.ECG_TOKEN_HEADER);
		if (poseStr != null) {
			pose = Integer.parseInt(poseStr);
		}
		Integer fidelity = Integer.parseInt(connection
				.getHeaderField(HttpConstants.ECG_OVERBIAS_HEADER));
		Integer heartRate = Integer.parseInt(connection
				.getHeaderField(HttpConstants.HEART_RATE_HEADER));
		Integer batteryRemaining = Integer.parseInt(connection
				.getHeaderField(HttpConstants.BATTERY_REMAINING_HEADER));
		Integer signalStrength = Integer.parseInt(connection
				.getHeaderField(HttpConstants.SIGNAL_STRENGTH_HEADER));
		Integer connectivity = Integer.parseInt(connection
				.getHeaderField(HttpConstants.ECG_ELECTRODE_HEADER));
		if (lastEmergencyCallTimeStr != null) {
			lastEmergencyCallTime = new Date(
					Long.parseLong(lastEmergencyCallTimeStr));
		}
		is = connection.getInputStream();
		rtecg.setPatientId(patientId);
		rtecg.setToken(token);
		rtecg.setLeadList(leadList);
		rtecg.setIn(new DataInputStream(is));
		rtecg.setHeartRate(heartRate);
		rtecg.setPose(pose);
		rtecg.setBatteryRemaining(batteryRemaining);
		rtecg.setSignalStrength(signalStrength);
		rtecg.setFidelity(fidelity);
		rtecg.setConnectivity(connectivity);
		rtecg.setLastEmergencyCallTime(lastEmergencyCallTime);
		System.out.println(token);
		return rtecg;
	}

	public static void main(String[] args) throws IOException {
		RealTimeEcgRequest rtecg = new RealTimeEcgRequest();
		RealTimeEcgResponse response = rtecg.get();
		while (true) {
			try {
				Integer bpmTemp = response.getHeartRate();
				if(bpmTemp!=null)
				{
					if(bpmTemp.intValue()!=0)
					System.out.println("heart rate:"+bpmTemp);
				}
				DataInputStream dataIn = response.getDataInputStream();
				try {
					while (true){
						int value = dataIn.readShort() & 0xFFFF;
						System.out.println(value);
					}
					
				} catch (Exception e) {
					System.out.println("No more data to read");
					//countDown = 10;
				}
				finally{
					dataIn.close();
				}
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			response = response.next();// //may throw ServerProcessingException
		}  
	}
}
