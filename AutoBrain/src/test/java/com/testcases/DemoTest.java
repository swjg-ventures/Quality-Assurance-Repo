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
import com.pages.CustomerInfo;
import com.pages.CustomerService;
import com.pages.Delete_Devices_From_Panel;
import com.pages.DeviceReplacement;
import com.pages.DownloadApp;
import com.pages.ForgotPassword;
import com.pages.GetAnotherDeviceFromWeb;
import com.pages.ManageAccount;
import com.pages.MarkReplacedDeviceForReuse;
import com.pages.Modes;
import com.pages.Signup;
import com.pages.SignupWithPrepaidDevice;
import com.pages.VehicleProfile;

import Demo.Testing;

public class DemoTest extends Base {

//	@Test(priority = 1)
	public void VerifyPersonlDeviceSignup() throws Exception {
		Signup a = new Signup();
		a.signup("personal");

	}

//	@Test(priority = 2)
	public void VerifyBusinessDeviceSignup() throws Exception {
		Signup a = new Signup();
		a.signup("business");

	}

	// NEED TO UPDATE THE CELLULAR AND MODEL TYPE BC OF BLUETOOTHE
	@Test(priority = 3)
	public void VerifyRetailerCellularDeviceSignup() throws Exception {
		SignupWithPrepaidDevice order = new SignupWithPrepaidDevice();
		order.signupWithPrepaidDevice("personal", "SPRINT", "Standard", "90 days personal plan", "false");
	}

//	@Test(priority = 4)
	public void VerifyRetailerFreeBluetoothDeviceSignup() throws Exception {
		SignupWithPrepaidDevice order = new SignupWithPrepaidDevice();
		order.signupWithPrepaidDevice("personal", "FREE_1", "Autobrain_Bluetooth_1",
				"Personal Tier Free, Price: $49.97, Monthly: $0.00, Free Days: 90", "free");
	}

//	@Test(priority = 5)
	public void VerifyRetailerPaidBluetoothDeviceSignup() throws Exception {
		SignupWithPrepaidDevice order = new SignupWithPrepaidDevice();
		order.signupWithPrepaidDevice("personal", "FREE_1", "Autobrain_Bluetooth_1",
				"Personal Tier Free, Price: $49.97, Monthly: $0.00, Free Days: 90", "paid");
	}

//	@Test(priority = 6)
	public void verifyDeviceReplacement() throws Exception {
		DeviceReplacement l = new DeviceReplacement();
		l.Device_replacement();
	}

//	@Test(priority = 7)
	public void verifyMarkReplacedDeviceForReuse() throws Exception {
		MarkReplacedDeviceForReuse l = new MarkReplacedDeviceForReuse();
		l.markReplacedDeviceForReuse();
	}

//	@Test(priority = 1)
	public void deleteDevices() throws Exception {
		Delete_Devices_From_Panel d = new Delete_Devices_From_Panel();
		d.delete_devices();
	}

//	@Test(priority = 1)
	public void ValidateTests() throws Exception {
		ChangePassword d = new ChangePassword();
		d.changePassword();
	}

}
