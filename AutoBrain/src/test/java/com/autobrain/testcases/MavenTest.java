package com.autobrain.testcases;

import org.testng.annotations.Test;

import com.autobrain.base.Base;
import com.autobrain.pages.AlertSettings;
import com.autobrain.pages.BeepCarpool;
import com.autobrain.pages.CallEmergency;
import com.autobrain.pages.CancelSubscription;
import com.autobrain.pages.CarFinder;
import com.autobrain.pages.CarGroups;
import com.autobrain.pages.ChangePassword;
import com.autobrain.pages.CustomerInfo;
import com.autobrain.pages.CustomerService;
import com.autobrain.pages.DownloadApp;
import com.autobrain.pages.ForgotPassword;
import com.autobrain.pages.MarkReplacedDeviceForReuse;
import com.autobrain.pages.Modes;
import com.autobrain.pages.TermsAndConditions;
import com.autobrain.pages.VehicleProfile;

import Demo.DeviceReplacement;
import Demo.Signup;
import Demo.SignupWithPrepaidDevice;

public class MavenTest extends Base {

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
	public void VerifyRetailerSignup() throws Exception {
		SignupWithPrepaidDevice order = new SignupWithPrepaidDevice();
		order.signupWithPrepaidDevice("personal");
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
