package com.autobrain.pages;

import org.testng.annotations.Test;
import com.autobrain.base.Base;
import com.autobrain.base.SignUpBase;
import com.autobrain.models.SignupModel;

public class SignUp extends Base {

	@Test(description = "Bought bluetooth unit from AB website and then signup with free tier fist and after that request for an upgrade"
			+ "unit")
	public void signupBluetoothUnitWithFreePlanAndActivateRequestedUpgradeUnit() throws Exception {

		SignupModel signupModel = new SignupModel();
		signupModel.setAccount_type("bluetooth");
		signupModel.setBluetooth_signup_tier("free");
		signupModel.setPersonal_plan("vip");
		signupModel.setChoose_personal_billing_interval("monthly");
		signupModel.setPricing_plan("Personal Tier Free, Price: $49.97, Monthly: $0.00, Free Days: 90");
		signupModel.setSet_esf(false);

		SignUpBase signUpBase = new SignUpBase(signupModel);

		synchronized (signUpBase.LockObject) {

			signUpBase.orderDeviceFromWebSite();
		}
		signUpBase.signup();

		signUpBase.esfExemptionsSetup();

		signUpBase.step1Setup(signupModel.getAll_Devices_No().get(0));

		signUpBase.choosePricingPlanAndAddCardDetails();
		signUpBase.upgradeRequest();

	}

	@Test(description = "Bought bluetooth unit from AB website and then signup with paid tier fist and after that activate requested upgrade"
			+ "unit")
	public void signupBluetoothUnitWithPaidPlanAndActivateRequestedUpgradeUnit() throws Exception {

		SignupModel signupModel = new SignupModel();
		signupModel.setAccount_type("bluetooth");
		signupModel.setBluetooth_signup_tier("paid");
		signupModel.setPersonal_plan("vip");
		signupModel.setChoose_personal_billing_interval("monthly");
		signupModel.setPricing_plan("Personal Tier Free, Price: $49.97, Monthly: $0.00, Free Days: 90");
		signupModel.setSet_esf(false);

		SignUpBase signUpBase = new SignUpBase(signupModel);

		synchronized (signUpBase.LockObject) {

			signUpBase.orderDeviceFromWebSite();
		}
		signUpBase.signup();

		signUpBase.esfExemptionsSetup();

		signUpBase.step1Setup(signupModel.getAll_Devices_No().get(0));

		signUpBase.choosePricingPlanAndAddCardDetails();

		// Logout current user
		signUpBase.login.logout();

		// Change blue-tooth type for upgraded device
		signupModel.setBluetooth_upgraded(true);

		// Order to ship upgraded device
		signUpBase.orderToShip();

		// Login registered user
		signUpBase.login.login(signupModel.getOwner_email(), "welcome");

		signUpBase.activateNewDevice(signupModel.getAll_Devices_No().get(1));

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

		signUpBase.esfExemptionsSetup();

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

		signUpBase.esfExemptionsSetup();

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
		signupModel.setTotal_bought_devices(1);
		signupModel.setAccount_type("personal");
		signupModel.setPersonal_plan("shell");
		signupModel.setChoose_personal_billing_interval("monthly");
		signupModel.setPricing_plan("365 days new plan");
		signupModel.setSet_esf(false);

		SignUpBase signUpBase = new SignUpBase(signupModel);

		synchronized (signUpBase.LockObject) {
			signUpBase.addDevicesInCsvFile();

			signUpBase.createInvoice();

			signUpBase.submitCsvFile();

			signUpBase.chooseInvoicePricingPlanAndDistributionChannel();
		}

		signUpBase.signup();

		signUpBase.esfExemptionsSetup();

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

			signUpBase.createInvoice();

			signUpBase.submitCsvFile();

			signUpBase.chooseInvoicePricingPlanAndDistributionChannel();
		}

		signUpBase.signup();

		signUpBase.esfExemptionsSetup();

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

	@Test(description = "Bought bluetooth unit from retailer and signup with free plan first and then requested for an upgrade unit")
	public void signupRetailerBluetoothUnitWithFreePlanAndActivateRequestedUpgradeUnit() throws Exception {

		SignupModel signupModel = new SignupModel();
		signupModel.setAccount_type("personal");
		signupModel.setBluetooth_signup_tier("free");
		signupModel.setPersonal_plan("vip");
		signupModel.setChoose_personal_billing_interval("monthly");
		signupModel.setPricing_plan("Personal Tier Free, Price: $49.97, Monthly: $0.00, Free Days: 90");
		signupModel.setSet_esf(false);

		SignUpBase signUpBase = new SignUpBase(signupModel);

		synchronized (signUpBase.LockObject) {

			signUpBase.addDevicesInCsvFile();

			signUpBase.createInvoice();

			signUpBase.submitCsvFile();

			signUpBase.chooseInvoicePricingPlanAndDistributionChannel();
		}
		signUpBase.signup();

		signUpBase.esfExemptionsSetup();

		signUpBase.step1Setup(signupModel.getAll_Devices_No().get(0));

		signUpBase.choosePricingPlanAndAddCardDetails();
		
		signUpBase.upgradeRequest();

	}

	@Test(description = "Bought bluetooth unit from retailer and then signup with paid plan and then activated upgraded cellular unit")
	public void signupRetailerBluetoothUnitWithPaidPlanAndActivateRequestedUpgradeUnit() throws Exception {

		SignupModel signupModel = new SignupModel();

		signupModel.setAccount_type("personal");
		signupModel.setBluetooth_signup_tier("paid");
		signupModel.setPersonal_plan("vip");
		signupModel.setChoose_personal_billing_interval("monthly");
		signupModel.setPricing_plan("Personal Tier Free, Price: $49.97, Monthly: $0.00, Free Days: 90");
		signupModel.setSet_esf(false);

		SignUpBase signUpBase = new SignUpBase(signupModel);

		synchronized (signUpBase.LockObject) {

			signUpBase.addDevicesInCsvFile();

			signUpBase.createInvoice();

			signUpBase.submitCsvFile();

			signUpBase.chooseInvoicePricingPlanAndDistributionChannel();
		}
		signUpBase.signup();

		signUpBase.esfExemptionsSetup();

		signUpBase.step1Setup(signupModel.getAll_Devices_No().get(0));

		signUpBase.choosePricingPlanAndAddCardDetails();

		// Logout current user
		signUpBase.login.logout();

		// Change blue-tooth type for upgraded device
		signupModel.setBluetooth_upgraded(true);

		// Order to ship upgraded device
		signUpBase.orderToShip();

		// Login registered user
		signUpBase.login.login(signupModel.getOwner_email(), "welcome");

		signUpBase.activateNewDevice(signupModel.getAll_Devices_No().get(1));

	}

}
