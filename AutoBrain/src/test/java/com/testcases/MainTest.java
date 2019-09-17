package com.testcases;

import org.testng.annotations.Test;

import com.pages.CallEmergency;
import com.pages.CarFinder;
import com.pages.CustomerService;
import com.pages.DownloadApp;
import com.pages.GetAnotherDevice;
import com.pages.ListOfCars;
import com.pages.StolenCar;
import com.pages.Timeline;
import com.pages.VehicleProfile;

public class MainTest extends VehicleProfile {

	// LOGIN
	@Test(priority = 1)
	public void VerifyValidLogin() throws Exception {
		login();
	}

	// LOGOUT
	@Test(priority = 2)
	public void VerifyLogoutUser() throws Exception {
		LogoutUser();
	}

	// SIGNUP
//	@Test(priority = 3)
	public void VerifyValidSignup() throws Exception {
		ValidSignup();
	}

	// FORGOT PASSWORD
	@Test(priority = 4)
	public void VerifyEnteringRegisteredEmailInForgotPasswordField() throws Exception {
		RegisteredEmail();
	}

	// SIGNUP- TERMS AND CONDITIONS LINK
	@Test(priority = 5)
	public void VerifyTermsAndConditionsLinkOnSignupPage() throws Exception {
		TermsandConditions();
	}

	// SIGNUP- PRIVACY POLICY LINK
	@Test(priority = 6)
	public void VerifyPrivacyPolicyLinkOnSignupPage() throws Exception {
		PrivayPolicy();
	}

	// SINGUP- CHAT BUTTON FUNCTIONALITY
	@Test(priority = 7)
	public void VerifyChatButtonFunctionality() throws Exception {
		ChatButton();
	}

	// MANAGE ACCOUNT- UPDATE CONTACT INFO
	@Test(priority = 8)
	public void VerifyUpdatingContactInfo() throws Exception {
		UpdatingContactInfo();
	}

	// MANAGE ACCOUNT- CHANGE PASSWORD
	@Test(priority = 9)
	public void VerifyChangePassword() throws Exception {
		ChangePasswordWIthCorrectDetails();
	}

	// MANAGE ACCOUNT- ADD CREDIT CARD
	@Test(priority = 10)
	public void VerifyAddNewCreditCard() throws Exception {
		AddNewCreditCard();
	}

	// MANAGE ACCOUNT- UPDATE CREDIT CARD
	@Test(priority = 11)
	public void VerifyUpdateCreditCard() throws Exception {
		UpdateCreditCard();
	}

	// MANAGE ACCOUNT- CUSTOMER INFO
	@Test(priority = 12)
	public void VerifyCustomerInfo() throws Exception {
		CustomerInfo();
	}

	// MANAGE ACCOUNT- PRINT ROADSIDE CARD
	@Test(priority = 13)
	public void VerifyPrintRoadsideCard() throws Exception {
		PrintRoadsideCard();
	}

	// MENU- VEHICLE PROFILE
	@Test(priority = 14)
	public void VerifyVehicleProfile() throws Exception {
		VehicleProfileInfo();
	}

	// DOWNLOAD APP
	@Test(priority = 15)
	public void VerifyDownloadApp() throws Exception {
		DownloadApp dapp = new DownloadApp();
		dapp.DownloadAppLinks();
	}

	// GET ANOTHER DEVICE
	@Test(priority = 16)
	public void VerifyBuyDevice() throws Exception {
		GetAnotherDevice buy = new GetAnotherDevice();
		buy.BuyDevice();
	}

	// TIMELINE BY DATE
	@Test(priority = 17)
	public void VerifyTimeline() throws Exception {
		Timeline t = new Timeline();
		t.Timeline_By_Date();
	}

	// LIST OF CARS IN HEADER
	@Test(priority = 18)
	public void VerifyChangingCarFromDropdownList() throws Exception {
		ListOfCars cars = new ListOfCars();
		cars.ChangeCar();
	}

	// CAR FINDER MAIN MENU
	@Test(priority = 19)
	public void VerifyCarFinder() throws Exception {
		CarFinder car = new CarFinder();
		car.car_finder();
	}

	// CUSTOMER SERVICE MAIN MENU
	@Test(priority = 20)
	public void VerifyCustomerService() throws Exception {
		CustomerService cst = new CustomerService();
		cst.customer_service();
	}

	// CALL EMERGENCY MAIN MENU
	@Test(priority = 21)
	public void VerifyCallEmergency() throws Exception {
		CallEmergency c = new CallEmergency();
		c.call_emergency();
	}

	// STOLEN CAR MAIN MENU
	@Test(priority = 22)
	public void VerifyStolenCar() throws Exception {
		StolenCar c = new StolenCar();
		c.stolen_car();
	}

}
