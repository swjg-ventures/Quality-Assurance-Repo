package com.autobrain.testcases;

import org.testng.annotations.Test;
import com.autobrain.pages.AlertSettings;
import com.autobrain.pages.BeepCarpool;
import com.autobrain.pages.CallEmergency;
import com.autobrain.pages.CarFinder;
import com.autobrain.pages.CarGroups;
import com.autobrain.pages.ChangePassword;
import com.autobrain.pages.CustomerInfo;
import com.autobrain.pages.CustomerService;
import com.autobrain.pages.DownloadApp;
import com.autobrain.pages.GetAnotherDeviceFromWeb;
import com.autobrain.pages.RoadsideAssistance;
import com.autobrain.pages.VehicleProfile;

public class MainTest extends VehicleProfile {

	// LOGIN
	@Test(priority = 1)
	public void VerifyValidLogin() throws Exception {
		login("john@examplecom", "welcome");
	}

	// LOGOUT
	@Test(priority = 2)
	public void VerifyLogoutUser() throws Exception {
		logout();
	}

	// FORGOT PASSWORD
	@Test(priority = 3)
	public void VerifyEnteringRegisteredEmailInForgotPasswordField() throws Exception {

	}

	// SIGNUP- TERMS AND CONDITIONS LINK
	@Test(priority = 4)
	public void VerifyTermsAndConditionsLinkOnSignupPage() throws Exception {

	}

	// SIGNUP- PRIVACY POLICY LINK
	@Test(priority = 5)
	public void VerifyPrivacyPolicyLinkOnSignupPage() throws Exception {

	}

	// SINGUP- CHAT BUTTON FUNCTIONALITY
	@Test(priority = 6)
	public void VerifyChatButtonFunctionality() throws Exception {

	}

	// MANAGE ACCOUNT- CHANGE PASSWORD
	@Test(priority = 7)
	public void VerifyChangePassword() throws Exception {
		ChangePassword c = new ChangePassword();
		c.changePassword();
	}

	// MANAGE ACCOUNT- UPDATE CONTACT INFO
	@Test(priority = 8)
	public void VerifyUpdatingContactInfo() throws Exception {

	}

	// MANAGE ACCOUNT- ADD CREDIT CARD
	@Test(priority = 9)
	public void VerifyAddNewCreditCard() throws Exception {

	}

	// MANAGE ACCOUNT- UPDATE CREDIT CARD
	@Test(priority = 10)
	public void VerifyUpdateCreditCard() throws Exception {

	}

	// MANAGE ACCOUNT- CUSTOMER INFO
	@Test(priority = 11)
	public void VerifyCustomerInfo() throws Exception {
		CustomerInfo c = new CustomerInfo();
		c.customerInfo();
	}

	// MANAGE ACCOUNT- PRINT ROADSIDE CARD
	@Test(priority = 12)
	public void VerifyPrintRoadsideCard() throws Exception {

	}

	// MENU- VEHICLE PROFILE
	@Test(priority = 13)
	public void VerifyVehicleProfile() throws Exception {
		VehicleProfileInfo();
	}

	// DOWNLOAD APP
	@Test(priority = 14)
	public void VerifyDownloadApp() throws Exception {
		DownloadApp dapp = new DownloadApp();
		dapp.downloadApp();
	}

	// GET ANOTHER DEVICE
	@Test(priority = 15)
	public void VerifyBuyDevice() throws Exception {
		GetAnotherDeviceFromWeb buy = new GetAnotherDeviceFromWeb();
		buy.getAnotherDeviceFromWeb();
	}

	// CAR FINDER MAIN MENU
	@Test(priority = 18)
	public void VerifyCarFinder() throws Exception {
		CarFinder car = new CarFinder();
		car.carFinder();
	}

	// CUSTOMER SERVICE MAIN MENU
	@Test(priority = 19)
	public void VerifyCustomerService() throws Exception {
		CustomerService cst = new CustomerService();
		cst.customerService();
	}

	// CALL EMERGENCY MAIN MENU
	@Test(priority = 20)
	public void VerifyCallEmergency() throws Exception {
		CallEmergency c = new CallEmergency();
		c.callEmergency();
	}

	// STOLEN CAR MAIN MENU
	@Test(priority = 21)
	public void VerifyStolenCar() throws Exception {

	}

	// ALERT SETTINGS MAIN MENU
	@Test(priority = 22)
	public void VerifyAlertSettings() throws Exception {
		AlertSettings a = new AlertSettings();
		a.alertSettings();
	}

	// CAR GROUPS
	@Test(priority = 23)
	public void VerifyCarGroups() throws Exception {
		CarGroups a = new CarGroups();
		a.carGroups();
	}

	// BEEP CARPOOL
	@Test(priority = 24)
	public void VerifyBeepCarPool() throws Exception {
		BeepCarpool a = new BeepCarpool();
		a.beepCarpool();
	}

	// MODES
	@Test(priority = 25)
	public void VerifyModes() throws Exception {

	}

	// ROADSIDE ASSISTANCE
	@Test(priority = 26)
	public void VerifyRoadsideAssistance() throws Exception {
		RoadsideAssistance a = new RoadsideAssistance();
		a.roadside_assistance();
	}

}
