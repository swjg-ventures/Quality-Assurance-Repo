package com.testcases;

import org.testng.annotations.Test;
import com.base.Base;
import com.pages.CancelSubscription;
import com.pages.Delete_Devices_From_Panel;
import com.pages.Modes;
import com.pages.Signup;
import com.pages.SignupWithPrepaidDevice;
import com.pages.VehicleProfile;


public class DemoTest extends Base {

	@Test(priority = 1)
	public void VerifyRegister1() throws Exception {
		Signup a = new Signup();
		a.signup("business");

	}

	//NEED TO UPDATE THE CELLULAR AND MODEL TYPE BC OF BLUETOOTHE	
//	@Test(priority = 1)
	public void Manually_Create_Invoice_And_Signup() throws Exception {
		SignupWithPrepaidDevice order = new SignupWithPrepaidDevice();
		order.signupWithPrepaidDevice("personal");
	}

//	@Test(priority = 1)
	public void verifyVehicleProfile() throws Exception {
		VehicleProfile l = new VehicleProfile();
		l.VehicleProfileInfo();
	}

//	@Test(priority = 1)
	public void verifyCancelSubscription() throws Exception {
		CancelSubscription l = new CancelSubscription();
		l.cancelSubscription();
	}

	
//	@Test(priority = 1)
	public void verifyCarFinder() throws Exception {
		Modes m = new Modes();
		m.createGeoFence();
	}
	
//	@Test(priority = 1)
	public void deleteDevices() throws Exception {
		Delete_Devices_From_Panel d = new Delete_Devices_From_Panel();
		d.delete_devices();
	}
	

}
