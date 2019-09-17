package com.testcases;

import org.testng.annotations.Test;

import com.pages.CallEmergency;
import com.pages.CarFinder;
import com.pages.CustomerService;
import com.pages.Modes;
import com.pages.StolenCar;
import com.pages.VehicleProfile;


public class DemoTest extends VehicleProfile {


		
		
//		@Test(priority = 17)
		public void VerifyCallEmergency() throws Exception {
			CallEmergency c = new CallEmergency();
			c.call_emergency();
		}
		
//		@Test(priority = 18)
		public void VerifyStolenCar() throws Exception {
			StolenCar c = new StolenCar();
			c.stolen_car();
		}
		
		@Test(priority = 19)
		public void VerifyMode() throws Exception {
			Modes m = new Modes();
			m.mode();
		}
		
	
}
