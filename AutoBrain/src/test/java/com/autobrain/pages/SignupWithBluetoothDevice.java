package com.autobrain.pages;

import com.autobrain.base.Base;
import com.autobrain.models.SignupModel;

public class SignupWithBluetoothDevice {
	com.autobrain.pages.SignupWithBoughtDeviceFromABWebsite signup;
	com.autobrain.pages.SignupWithRetailerDevice retailer_signup;
	com.autobrain.pages.Login login;

	public SignupWithBluetoothDevice() {
		login = new Login();
		signup = new SignupWithBoughtDeviceFromABWebsite();
		retailer_signup = new SignupWithRetailerDevice();
	}

	public void signupWithBluetoothDevice(String account_type, String bluetooth_is, String set_plan,
			String set_billing_interval, String set_pricing_plan, boolean set_esf) throws Exception {

		// Set-up data
		SignupModel.setConfirmation_email(true);
		SignupModel.setTotal_bought_devices(1);
		SignupModel.setAccount_type(account_type);
		SignupModel.setBluetooth_is(bluetooth_is);
		SignupModel.setPersonal_plan(set_plan);
		SignupModel.setChoose_personal_billing_interval(set_billing_interval);
		SignupModel.setChoose_business_billing_interval(set_billing_interval);
		SignupModel.setPricing_plan(set_pricing_plan);
		SignupModel.setSet_esf(set_esf);

		retailer_signup.addDevicesInCsvFile();

		retailer_signup.CreateInvoice();

		retailer_signup.SubmitCsvFile();

		retailer_signup.ChooseInvoicePricingPlanAndDistributionChannel();

		signup.signup();

		signup.EsfExemptionsSetup();

		signup.step1Setup(SignupModel.getAll_Devices_No().get(0));

		signup.choosePricingPlanAndAddCardDetails();

		// If more than one device then activate new device
		if (bluetooth_is.equals("free_plus_paid")) {

			// Logout current user
			login.logout();

			// Change blue-tooth type for upgraded device
			SignupModel.setBluetooth_is("upgraded_device");
			// Order to ship upgraded device
			signup.orderToShip();

			// Set blue-tooth type back to default
			SignupModel.setBluetooth_is("free_plus_paid");
			// Login registered user
			login.login(SignupModel.getOwner_email(), "welcome");

			// Close the blue-tooth instruction popup
			Base b = new Base();
			b.VisibilityOfElementByXpath("//button[contains(text(),'GOT IT')]", 10).click();
			signup.activateNewDevice(SignupModel.getAll_Devices_No().get(1));

		}

	}

}
