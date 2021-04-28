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
import com.autobrain.pages.DeviceReplacement;
import com.autobrain.pages.DownloadApp;
import com.autobrain.pages.ForgotPassword;
import com.autobrain.pages.MarkReplacedDeviceForReuse;
import com.autobrain.pages.Modes;
import com.autobrain.pages.SignupWithBluetoothDevice;
import com.autobrain.pages.SignupWithBoughtDeviceFromABWebsite;
import com.autobrain.pages.SignupWithRetailerDevice;
import com.autobrain.pages.TermsAndConditions;
import com.autobrain.pages.VehicleProfile;

public class MavenTest extends Base {

	@Test(priority = 1)
	public void ValidatePersonalBoughtDeviceTests() throws Exception {
		SignupWithBoughtDeviceFromABWebsite ss = new SignupWithBoughtDeviceFromABWebsite();
		ss.signupWithBoughtDeviceFromABWebsite(1, "personal", "vip", "yearly", false);
	}

	@Test(priority = 2)
	public void ValidateBusinessBoughtDeviceTests() throws Exception {
		SignupWithBoughtDeviceFromABWebsite ss = new SignupWithBoughtDeviceFromABWebsite();
		ss.signupWithBoughtDeviceFromABWebsite(1, "business", "vip", "yearly", false);
	}

	@Test(priority = 3)
	public void ValidatePersonalRetailerTests() throws Exception {
		SignupWithRetailerDevice ss = new SignupWithRetailerDevice();
		ss.signupWithRetailerDevice(1, "personal", "vip", "monthly", "90 days personal plan", false);
	}

	@Test(priority = 4)
	public void ValidateBusinessRetailerTests() throws Exception {
		SignupWithRetailerDevice ss = new SignupWithRetailerDevice();
		ss.signupWithRetailerDevice(2, "business", "vip", "monthly",
				"Plan Type: Business Referral, Cost: $19.99, Monthly: $14.99, Free Days: 14", false);
	}

	@Test(priority = 5)
	public void ValidateFreeBluetoothTests() throws Exception {
		SignupWithBluetoothDevice ss = new SignupWithBluetoothDevice();
		ss.signupWithBluetoothDevice("personal", "free", "vip", "monthly",
				"Personal Tier Free, Price: $49.97, Monthly: $0.00, Free Days: 90", false);
	}

	@Test(priority = 6)
	public void ValidatePaidBluetoothTests() throws Exception {
		SignupWithBluetoothDevice ss = new SignupWithBluetoothDevice();
		ss.signupWithBluetoothDevice("personal", "free_plus_paid", "vip", "monthly",
				"Personal Tier Free, Price: $49.97, Monthly: $0.00, Free Days: 90", false);
	}

	@Test(priority = 7)
	public void ValidateDeviceReplacement() throws Exception {
		DeviceReplacement n = new DeviceReplacement();
		n.deviceReplacement();
	}

	@Test(priority = 8)
	public void verifyMarkReplacedDeviceForReuse() throws Exception {
		MarkReplacedDeviceForReuse l = new MarkReplacedDeviceForReuse();
		l.markReplacedDeviceForReuse();
	}

	@Test(priority = 9)
	public void verifyCancelSubscription() throws Exception {
		CancelSubscription l = new CancelSubscription();
		l.cancelSubscription();
	}

	@Test(priority = 10)
	public void verifyAlertSettings() throws Exception {
		AlertSettings l = new AlertSettings();
		l.alertSettings();
	}

	@Test(priority = 11)
	public void verifyBeepCarpool() throws Exception {
		BeepCarpool l = new BeepCarpool();
		l.beepCarpool();
	}

	@Test(priority = 12)
	public void verifyCarGroups() throws Exception {
		CarGroups l = new CarGroups();
		l.carGroups();
	}

	@Test(priority = 13)
	public void verifyChangePassword() throws Exception {
		ChangePassword l = new ChangePassword();
		l.changePassword();
	}

	@Test(priority = 14)
	public void verifyCustomerInfo() throws Exception {
		CustomerInfo l = new CustomerInfo();
		l.customerInfo();
	}

	@Test(priority = 15)
	public void verifyDownloadApp() throws Exception {
		DownloadApp l = new DownloadApp();
		l.downloadApp();
	}

	@Test(priority = 16)
	public void verifyForgotPassword() throws Exception {
		ForgotPassword l = new ForgotPassword();
		l.forgotPassword("junking4946@yopmail.com");
	}

	@Test(priority = 17)
	public void verifyUpdateVehicleProfile() throws Exception {
		VehicleProfile l = new VehicleProfile();
		l.VehicleProfileInfo();
	}

	@Test(priority = 18)
	public void verifyCarFinder() throws Exception {
		CarFinder s = new CarFinder();
		s.carFinder();
	}

	@Test(priority = 19)
	public void verifyTermsAndPrivacyPolicy() throws Exception {
		TermsAndConditions s = new TermsAndConditions();
		s.termsAndPrivacy();
	}

	@Test(priority = 20)
	public void verifyCreateGeoFences() throws Exception {
		Modes m = new Modes();
		m.createGeoFence();
	}

	@Test(priority = 21)
	public void verifyCallEmergency() throws Exception {
		CallEmergency l = new CallEmergency();
		l.callEmergency();
	}

	@Test(priority = 22)
	public void verifyCustomerService() throws Exception {
		CustomerService l = new CustomerService();
		l.customerService();
	}
}
