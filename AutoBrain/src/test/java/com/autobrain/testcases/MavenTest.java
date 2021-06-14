package com.autobrain.testcases;

import org.testng.annotations.Test;
import com.autobrain.base.Base;
import com.autobrain.models.SignupModel;
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
import com.autobrain.pages.TermsAndConditions;
import com.autobrain.pages.VehicleProfile;

public class MavenTest extends Base {





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
