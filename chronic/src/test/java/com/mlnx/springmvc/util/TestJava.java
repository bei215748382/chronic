package com.mlnx.springmvc.util;

import org.junit.Test;
import com.mlnx.chronic.entity.TUserExt;

public class TestJava {

	@Test
	public void test(){
		TUserExt user = new TUserExt();
		if(user.getPregnant()!=null &&user.getPregnant() == 0){
			System.out.println("sdfad");
		} else {
			System.out.println("bbbb");
		}
	}
}
