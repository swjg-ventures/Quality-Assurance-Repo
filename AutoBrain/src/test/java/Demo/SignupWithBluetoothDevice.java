package Demo;
//package com.autobrain.pages;
//
//import com.autobrain.base.SignUpBase;
//import com.autobrain.models.SignupModel;
//
//public class SignupWithBluetoothDevice extends SignUpBase {
//
//	public SignupWithBluetoothDevice(SignupModel signupModel) {
//		super(signupModel);
//	}
//
//	public void signupWithBluetoothDevice(boolean AddCellularDevicewithoutPricingPlan) throws Exception {
//
//		synchronized (LockObject) {
//
//			addDevicesInCsvFile();
//
//			CreateInvoice();
//
//			SubmitCsvFile();
//
//			ChooseInvoicePricingPlanAndDistributionChannel();
//		}
//		signup();
//
//		EsfExemptionsSetup();
//
//		step1Setup(signupModel.getAll_Devices_No().get(0));
//
//		choosePricingPlanAndAddCardDetails();
//
//		// Add device which having no pricing plan
//		if (AddCellularDevicewithoutPricingPlan) {
//			signupModel.setBluetooth_is("Upgraded");
//			login.logout();
//			login.login("john@example.com", "welcome");
//			createDeviceFromPanel();
//			VisibilityOfElementByXpath("//a[contains(text(),'Log Out')]", 15).click();
//			getDriver().navigate().to(url);
//			login.login(signupModel.getOwner_email(), "welcome");
//			Thread.sleep(500);
//			activateNewDevice(signupModel.getAll_Devices_No().get(1));
//		}
//
//		// If more than one device then activate new device
//		if (signupModel.getBluetooth_is().equals("free_plus_paid")) {
//
//			// Logout current user
//			login.logout();
//
//			// Change blue-tooth type for upgraded device
//			signupModel.setBluetooth_is("upgraded_device");
//			// Order to ship upgraded device
//			orderToShip();
//
//			// Set blue-tooth type back to default
//			signupModel.setBluetooth_is("free_plus_paid");
//			// Login registered user
//			login.login(signupModel.getOwner_email(), "welcome");
//
//			activateNewDevice(signupModel.getAll_Devices_No().get(1));
//
//		}
//
//	}
//
//}
