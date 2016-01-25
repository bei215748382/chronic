package com.mlnx.chronic.mapper;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mlnx.chronic.entity.TAddress;

public class TAddressMapperTest extends TestBase{
	
	@Autowired
	private TAddressMapper tAddressMapper;

	@Test
	public void test() {
		String name = "地区";
		for(int i = 0;i<10;i++){
			TAddress address = new TAddress();
			address.setName(name+i);
			tAddressMapper.insert(address);
		}
	}

}
