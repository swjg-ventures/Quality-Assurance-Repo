package com.maven.testcases;

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
import com.pages.DeviceReplacement;
import com.pages.DownloadApp;
import com.pages.ForgotPassword;
import com.pages.MarkReplacedDeviceForReuse;
import com.pages.Modes;
import com.pages.Signup;
import com.pages.SignupWithPrepaidDevice;
import com.pages.TermsAndConditions;
import com.pages.VehicleProfile;

public class DemoTest extends Base {

	@Test(priority = 1)
	public void VerifyPersonlDeviceSignup() throws Exception {
		Signup a = new Signup();
		a.signup("personal");

	}

	@Test(priority = 2)
	public void VerifyBusinessDeviceSignup() throws Exception {
		Signup a = new Signup();
		a.signup("business");

	}

	@Test(priority = 3)
	public void VerifyRetailerCellularDeviceSignup() throws Exception {
		SignupWithPrepaidDevice order = new SignupWithPrepaidDevice();
		order.signupWithPrepaidDevice("personal", "WYLESS", "Standard", "90 days personal plan", "false");
	}

	@Test(priority = 4)
	public void VerifyRetailerFreeBluetoothDeviceSignup() throws Exception {
		SignupWithPrepaidDevice order = new SignupWithPrepaidDevice();
		order.signupWithPrepaidDevice("personal", "FREE_1", "Autobrain_Bluetooth_1",
				"Personal Tier Free, Price: $49.97, Monthly: $0.00, Free Days: 90", "free");
	}

	@Test(priority = 5)
	public void VerifyRetailerPaidBluetoothDeviceSignup() throws Exception {
		SignupWithPrepaidDevice order = new SignupWithPrepaidDevice();
		order.signupWithPrepaidDevice("personal", "FREE_1", "Autobrain_Bluetooth_1",
				"Personal Tier Free, Price: $49.97, Monthly: $0.00, Free Days: 90", "paid");
	}

	@Test(priority = 4)
	public void verifyDeviceReplacement() throws Exception {
		DeviceReplacement l = new DeviceReplacement();
		l.Device_replacement();

	}

	@Test(priority = 5)
	public void verifyMarkReplacedDeviceForReuse() throws Exception {
		MarkReplacedDeviceForReuse l = new MarkReplacedDeviceForReuse();
		l.markReplacedDeviceForReuse();
	}

	@Test(priority = 6)
	public void verifyCancelSubscription() throws Exception {
		CancelSubscription l = new CancelSubscription();
		l.cancelSubscription();
	}

//	@Test(priority = 7)
	public void verifyAlertSettings() throws Exception {
		AlertSettings l = new AlertSettings();
		l.alertSettings();
	}

	@Test(priority = 8)
	public void verifyBeepCarpool() throws Exception {
		BeepCarpool l = new BeepCarpool();
		l.beepCarpool();
	}

//	@Test(priority = 9)
	public void verifyCarGroups() throws Exception {
		CarGroups l = new CarGroups();
		l.carGroups();
	}

//	@Test(priority = 10)
	public void verifyChangePassword() throws Exception {
		ChangePassword l = new ChangePassword();
		l.changePassword();
	}

//	@Test(priority = 11)
	public void verifyCustomerInfo() throws Exception {
		CustomerInfo l = new CustomerInfo();
		l.customerInfo();
	}

//	@Test(priority = 12)
	public void verifyDownloadApp() throws Exception {
		DownloadApp l = new DownloadApp();
		l.downloadApp();
	}

//	@Test(priority = 13)
	public void verifyForgotPassword() throws Exception {
		ForgotPassword l = new ForgotPassword();
		l.forgotPassword("junking4946@yopmail.com");
	}

//	@Test(priority = 14)
	public void verifyUpdateVehicleProfile() throws Exception {
		VehicleProfile l = new VehicleProfile();
		l.VehicleProfileInfo();
	}

//	@Test(priority = 15)
	public void verifyCarFinder() throws Exception {
		CarFinder s = new CarFinder();
		s.carFinder();
	}

//	@Test(priority = 16)
	public void verifyTermsAndPrivacyPolicy() throws Exception {
		TermsAndConditions s = new TermsAndConditions();
		s.termsAndPrivacy();
	}

//	@Test(priority = 17)
	public void verifyCreateGeoFences() throws Exception {
		Modes m = new Modes();
		m.createGeoFence();
	}

//	@Test(priority = 18)
	public void verifyCallEmergency() throws Exception {
		CallEmergency l = new CallEmergency();
		l.callEmergency();
	}

//	@Test(priority = 19)
	public void verifyCustomerService() throws Exception {
		CustomerService l = new CustomerService();
		l.customerService();
	}
}
