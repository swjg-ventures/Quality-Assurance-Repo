package com.autobrain.testcases;

import org.testng.annotations.Test;
import com.autobrain.base.Base;
import com.autobrain.pages.MarkReplacedDeviceForReuse;


public class DemoTest extends Base {



	

	@Test(priority = 8)
	public void verifyMarkReplacedDeviceForReuse() throws Exception {
		MarkReplacedDeviceForReuse l = new MarkReplacedDeviceForReuse();
		l.markReplacedDeviceForReuse();
	}

}
