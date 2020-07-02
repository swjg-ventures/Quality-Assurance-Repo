package com.maven.testcases;

import org.testng.annotations.Test;
import com.base.Base;
import com.pages.CancelSubscription;
import com.pages.Login;
import com.pages.Signup;
import com.pages.SignupWithPrepaidDevice;
import com.pages.VehicleProfile;

public class DemoTest extends Base {

	@Test(priority = 1)
	public void VerifyPersonalAccountRegistration() throws Exception {
		Signup a = new Signup();
		a.signup("personal");

	}

	@Test(priority = 2)
	public void VerifyBusinessAccountRegistration() throws Exception {
		Signup a = new Signup();
		a.signup("business");

	}

	@Test(priority = 3)
	public void VerifySignupWithPrepaidDevice() throws Exception {
		SignupWithPrepaidDevice order = new SignupWithPrepaidDevice();
		order.signupWithPrepaidDevice("personal");
	}

	@Test(priority = 4)
	public void verifyUpdateVehicleProfile() throws Exception {
		VehicleProfile l = new VehicleProfile();
		l.VehicleProfileInfo();
	}

	@Test(priority = 5)
	public void verifyCancelSubscription() throws Exception {
		CancelSubscription l = new CancelSubscription();
		l.cancelSubscription();
	}

}
