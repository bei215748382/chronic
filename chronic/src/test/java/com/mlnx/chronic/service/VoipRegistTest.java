package com.mlnx.chronic.service;

import java.util.UUID;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mlnx.chronic.exception.RegisterException;
import com.mlnx.chronic.mapper.TVoipAccountMapper;
import com.mlnx.chronic.mapper.TestBase;
import com.mlnx.chronic.util.RegistVoip;

public class VoipRegistTest extends TestBase {
	
	@Autowired
	private TVoipAccountMapper tVoipAccountMapper;

	@Test
	public void test() throws RegisterException {
		for(int i = 0 ;i<30;i++){
			RegistVoip.regist(i, UUID.randomUUID().toString(), tVoipAccountMapper);
		}
	}

	@Test
	public void test2(){
		int i = 9;
		while(true){
			if(i % 4 == 1 && i % 5 == 4 && i % 6 == 3  && i % 7 == 5 && i % 8 == 1) {
				break;
			}
			i*=9;
		}
		System.out.println(i);
	}
}
