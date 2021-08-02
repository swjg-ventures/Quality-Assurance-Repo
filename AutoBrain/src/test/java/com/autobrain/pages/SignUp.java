package com.autobrain.pages;

import org.testng.annotations.Test;
import com.autobrain.base.Base;
import com.autobrain.base.SignUpBase;
import com.autobrain.models.SignupModel;

public class SignUp extends Base {

	@Test
	public void signupBluetoothDeviceBoughtFromABWebsite() throws Exception {

		SignupModel signupModel = new SignupModel();
		signupModel.setAccount_type("bluetooth");
		signupModel.setBluetooth_is("free");
		signupModel.setPersonal_plan("vip");
		signupModel.setChoose_personal_billing_interval("monthly");
		signupModel.setPricing_plan("Personal Tier Free, Price: $49.97, Monthly: $0.00, Free Days: 90");
		signupModel.setSet_esf(false);

		SignUpBase signUpBase = new SignUpBase(signupModel);

		synchronized (signUpBase.LockObject) {

			signUpBase.orderDeviceFromWebSite();
		}
		signUpBase.signup();

		signUpBase.EsfExemptionsSetup();

		signUpBase.step1Setup(signupModel.getAll_Devices_No().get(0));

		signUpBase.choosePricingPlanAndAddCardDetails();

	}

	@Test
	public void signupPersonalDeviceBoughtFromABWebsite() throws Exception {

		SignupModel signupModel = new SignupModel();
		signupModel.setTotal_bought_devices(1);
		signupModel.setAccount_type("personal");
		signupModel.setPersonal_plan("vip");
		signupModel.setChoose_personal_billing_interval("monthly");
		signupModel.setSet_esf(false);

		SignUpBase signUpBase = new SignUpBase(signupModel);

		synchronized (signUpBase.LockObject) {
			signUpBase.orderDeviceFromWebSite();
		}

		signUpBase.signup();

		signUpBase.EsfExemptionsSetup();

		signUpBase.step1Setup(signupModel.getAll_Devices_No().get(0));

		signUpBase.choosePricingPlanAndAddCardDetails();

		signUpBase.step2Setup();

		signUpBase.step3Setup();

		signUpBase.step4Setup();

		signUpBase.step5FinishSetup();

		Thread.sleep(2500);

		// If more than one device then activate new device
		if (signupModel.getAll_Devices_No().size() > 1) {

			for (int i = 1; i < signupModel.getAll_Devices_No().size(); i++) {
				signUpBase.activateNewDevice(signupModel.getAll_Devices_No().get(i));
			}
		}
		softassert.assertAll();
	}

	@Test
	public void signupBusinessDeviceBoughtFromABWebsite() throws Exception {

		SignupModel signupModel = new SignupModel();
		signupModel.setTotal_bought_devices(1);
		signupModel.setAccount_type("business");
		signupModel.setPersonal_plan("vip");
		signupModel.setChoose_business_billing_interval("yearly");
		signupModel.setSet_esf(false);

		SignUpBase signUpBase = new SignUpBase(signupModel);

		synchronized (signUpBase.LockObject) {
			signUpBase.orderDeviceFromWebSite();
		}

		signUpBase.signup();

		signUpBase.EsfExemptionsSetup();

		signUpBase.step1Setup(signupModel.getAll_Devices_No().get(0));

		signUpBase.choosePricingPlanAndAddCardDetails();

		signUpBase.step2Setup();

		signUpBase.step3Setup();

		signUpBase.step4Setup();

		signUpBase.step5FinishSetup();

		Thread.sleep(2500);

		// If more than one device then activate new device
		if (signupModel.getAll_Devices_No().size() > 1) {

			for (int i = 1; i < signupModel.getAll_Devices_No().size(); i++) {
				signUpBase.activateNewDevice(signupModel.getAll_Devices_No().get(i));
			}
		}
		softassert.assertAll();
	}

	@Test
	public void signupPersonalRetailerDevice() throws Exception {

		SignupModel signupModel = new SignupModel();
		signupModel.setTotal_bought_devices(2);
		signupModel.setAccount_type("personal");
		signupModel.setPersonal_plan("moneysaver");
		signupModel.setChoose_personal_billing_interval("monthly");
		signupModel.setPricing_plan("365 days new plan");
		signupModel.setSet_esf(false);

		SignUpBase signUpBase = new SignUpBase(signupModel);

		synchronized (signUpBase.LockObject) {
			signUpBase.addDevicesInCsvFile();

			signUpBase.CreateInvoice();

			signUpBase.SubmitCsvFile();

			signUpBase.ChooseInvoicePricingPlanAndDistributionChannel();
		}

		signUpBase.signup();

		signUpBase.EsfExemptionsSetup();

		signUpBase.step1Setup(signupModel.getAll_Devices_No().get(0));

		signUpBase.choosePricingPlanAndAddCardDetails();

		signUpBase.step2Setup();

		signUpBase.step3Setup();

		signUpBase.step4Setup();

		signUpBase.step5FinishSetup();

		Thread.sleep(2500);

		// If more than one device then activate new device
		if (signupModel.getAll_Devices_No().size() > 1) {

			for (int i = 1; i < signupModel.getAll_Devices_No().size(); i++) {
				signUpBase.activateNewDevice(signupModel.getAll_Devices_No().get(i));
			}
		}
		softassert.assertAll();
	}

	@Test
	public void signupBusinessRetailerDevice() throws Exception {

		SignupModel signupModel = new SignupModel();
		signupModel.setTotal_bought_devices(1);
		signupModel.setAccount_type("business");
		signupModel.setPersonal_plan("vip");
		signupModel.setChoose_business_billing_interval("monthly");
		signupModel.setPricing_plan("Plan Type: Business Referral, Cost: $19.99, Monthly: $14.99, Free Days: 14");
		signupModel.setSet_esf(false);

		SignUpBase signUpBase = new SignUpBase(signupModel);

		synchronized (signUpBase.LockObject) {
			signUpBase.addDevicesInCsvFile();

			signUpBase.CreateInvoice();

			signUpBase.SubmitCsvFile();

			signUpBase.ChooseInvoicePricingPlanAndDistributionChannel();
		}

		signUpBase.signup();

		signUpBase.EsfExemptionsSetup();

		signUpBase.step1Setup(signupModel.getAll_Devices_No().get(0));

		signUpBase.choosePricingPlanAndAddCardDetails();

		signUpBase.step2Setup();

		signUpBase.step3Setup();

		signUpBase.step4Setup();

		signUpBase.step5FinishSetup();

		Thread.sleep(2500);

		// If more than one device then activate new device
		if (signupModel.getAll_Devices_No().size() > 1) {

			for (int i = 1; i < signupModel.getAll_Devices_No().size(); i++) {
				signUpBase.activateNewDevice(signupModel.getAll_Devices_No().get(i));
			}
		}
		softassert.assertAll();
	}

	@Test
	public void signupFreeBluetoothDevice() throws Exception {

		SignupModel signupModel = new SignupModel();
		signupModel.setAccount_type("personal");
		signupModel.setBluetooth_is("free");
		signupModel.setPersonal_plan("vip");
		signupModel.setChoose_personal_billing_interval("monthly");
		signupModel.setPricing_plan("Personal Tier Free, Price: $49.97, Monthly: $0.00, Free Days: 90");
		signupModel.setSet_esf(false);

		SignUpBase signUpBase = new SignUpBase(signupModel);

		synchronized (signUpBase.LockObject) {

			signUpBase.addDevicesInCsvFile();

			signUpBase.CreateInvoice();

			signUpBase.SubmitCsvFile();

			signUpBase.ChooseInvoicePricingPlanAndDistributionChannel();
		}
		signUpBase.signup();

		signUpBase.EsfExemptionsSetup();

		signUpBase.step1Setup(signupModel.getAll_Devices_No().get(0));

		signUpBase.choosePricingPlanAndAddCardDetails();

	}

	@Test
	public void signupFreePlusPaidBluetoothDevice() throws Exception {

		SignupModel signupModel = new SignupModel();

		signupModel.setAccount_type("personal");
		signupModel.setBluetooth_is("free_plus_paid");
		signupModel.setPersonal_plan("vip");
		signupModel.setChoose_personal_billing_interval("monthly");
		signupModel.setPricing_plan("Personal Tier Free, Price: $49.97, Monthly: $0.00, Free Days: 90");
		signupModel.setSet_esf(false);

		SignUpBase signUpBase = new SignUpBase(signupModel);

		synchronized (signUpBase.LockObject) {

			signUpBase.addDevicesInCsvFile();

			signUpBase.CreateInvoice();

			signUpBase.SubmitCsvFile();

			signUpBase.ChooseInvoicePricingPlanAndDistributionChannel();
		}
		signUpBase.signup();

		signUpBase.EsfExemptionsSetup();

		signUpBase.step1Setup(signupModel.getAll_Devices_No().get(0));

		signUpBase.choosePricingPlanAndAddCardDetails();

		// If more than one device then activate new device
		if (signupModel.getBluetooth_is().equals("free_plus_paid")) {

			// Logout current user
			signUpBase.login.logout();

			// Change blue-tooth type for upgraded device
			signupModel.setBluetooth_is("upgraded_device");
			// Order to ship upgraded device
			signUpBase.orderToShip();

			// Set blue-tooth type back to default
			signupModel.setBluetooth_is("free_plus_paid");
			// Login registered user
			signUpBase.login.login(signupModel.getOwner_email(), "welcome");

			signUpBase.activateNewDevice(signupModel.getAll_Devices_No().get(1));

		}

	}

//	@Test
	public void ActivateCellularDeviceWhichContainNoInvoiceUnderFreeBluetoothAccount() throws Exception {

		SignupModel signupModel = new SignupModel();
		signupModel.setAccount_type("personal");
		signupModel.setBluetooth_is("free");
		signupModel.setPersonal_plan("vip");
		signupModel.setChoose_personal_billing_interval("monthly");
		signupModel.setPricing_plan("Personal Tier Free, Price: $49.97, Monthly: $0.00, Free Days: 90");
		signupModel.setSet_esf(false);

		SignUpBase signUpBase = new SignUpBase(signupModel);

		synchronized (signUpBase.LockObject) {

			signUpBase.addDevicesInCsvFile();

			signUpBase.CreateInvoice();

			signUpBase.SubmitCsvFile();

			signUpBase.ChooseInvoicePricingPlanAndDistributionChannel();
		}
		signUpBase.signup();

		signUpBase.EsfExemptionsSetup();

		signUpBase.step1Setup(signupModel.getAll_Devices_No().get(0));

		signUpBase.choosePricingPlanAndAddCardDetails();

		// Add device which having no pricing plan
		signupModel.setBluetooth_is("Upgraded");
		signUpBase.login.logout();
		signUpBase.login.login("john@example.com", "welcome");
		signUpBase.createDeviceFromPanel();
		VisibilityOfElementByXpath("//a[contains(text(),'Log Out')]", 15).click();
		getDriver().navigate().to(url);
		signUpBase.login.login(signupModel.getOwner_email(), "welcome");
		Thread.sleep(500);
		signUpBase.activateNewDevice(signupModel.getAll_Devices_No().get(1));

		// If more than one device then activate new device
		if (signupModel.getBluetooth_is().equals("free_plus_paid")) {

			// Logout current user
			signUpBase.login.logout();

			// Change blue-tooth type for upgraded device
			signupModel.setBluetooth_is("upgraded_device");
			// Order to ship upgraded device
			signUpBase.orderToShip();

			// Set blue-tooth type back to default
			signupModel.setBluetooth_is("free_plus_paid");
			// Login registered user
			signUpBase.login.login(signupModel.getOwner_email(), "welcome");

			signUpBase.activateNewDevice(signupModel.getAll_Devices_No().get(1));

		}

	}

}
