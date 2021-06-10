package com.autobrain.pages;

import com.autobrain.base.Signup;
import com.autobrain.models.SignupModel;

public class SignupWithBoughtDeviceFromABWebsite extends Signup {

	public SignupWithBoughtDeviceFromABWebsite(SignupModel signupModel) {
		super(signupModel);
	}

	// MAIN METHOD
	public void signupWithBoughtDeviceFromABWebsite() throws Exception {

		synchronized (LockObject) {
			orderDeviceFromWebSite();
		}

		signup();

		EsfExemptionsSetup();

		step1Setup(signupModel.getAll_Devices_No().get(0));

		choosePricingPlanAndAddCardDetails();

		step2Setup();

		step3Setup();

		step4Setup();

		step5FinishSetup();

		Thread.sleep(2500);

		// If more than one device then activate new device
		if (signupModel.getAll_Devices_No().size() > 1) {

			for (int i = 1; i < signupModel.getAll_Devices_No().size(); i++) {
				activateNewDevice(signupModel.getAll_Devices_No().get(i));
			}
		}
		softassert.assertAll();

	}

}
