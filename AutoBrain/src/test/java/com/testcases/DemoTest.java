package com.testcases;

import org.testng.annotations.Test;
import com.base.Base;
import com.pages.AlertSettings;
import com.pages.BeepCarpool;
import com.pages.CallEmergency;
import com.pages.CancelSubscription;
import com.pages.CarFinder;
import com.pages.CarGroups;
import com.pages.ChangePassword;
import com.pages.CustomerService;
import com.pages.Delete_Devices_From_Panel;
import com.pages.DownloadApp;
import com.pages.ForgotPassword;
import com.pages.GetAnotherDevice;
import com.pages.ListOfCars;
import com.pages.ManageAccount;
import com.pages.Modes;
import com.pages.Signup;
import com.pages.SignupWithPrepaidDevice;
import com.pages.VehicleProfile;


public class DemoTest extends Base {

//	@Test(priority = 1)
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
	public void verifyCarGroups() throws Exception {
		CarGroups l = new CarGroups();
		l.cargroups();
	}

	@Test(priority = 1)
	public void verifyDownloadApp() throws Exception {
		Modes l = new Modes();
		l.createGeoFence();
	}

	
//	@Test(priority = 1)
	public void deleteDevices() throws Exception {
		Delete_Devices_From_Panel d = new Delete_Devices_From_Panel();
		d.delete_devices();
	}
	

}
