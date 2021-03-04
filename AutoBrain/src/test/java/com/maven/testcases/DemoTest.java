package com.maven.testcases;

import org.testng.annotations.Test;
import com.base.Base;
import com.pages.CancelSubscription;
import com.pages.CarFinder;
import com.pages.Modes;
import com.pages.Signup;
import com.pages.SignupWithPrepaidDevice;
import com.pages.TermsAndConditions;
import com.pages.VehicleProfile;

public class DemoTest extends Base {

//	@Test(priority = 1)
	public void VerifyPersonalAccountRegistration() throws Exception {
		Signup a = new Signup();
		a.signup("personal");

	}

//	@Test(priority = 2)
	public void VerifyBusinessAccountRegistration() throws Exception {
		Signup a = new Signup();
		a.signup("business");

	}

//	@Test(priority = 3)
	public void VerifySignupWithPrepaidDevice() throws Exception {
		SignupWithPrepaidDevice order = new SignupWithPrepaidDevice();
		order.signupWithPrepaidDevice("personal");
	}

//	@Test(priority = 4)
	public void verifyUpdateVehicleProfile() throws Exception {
		VehicleProfile l = new VehicleProfile();
		l.VehicleProfileInfo();
	}

	@Test(priority = 5)
	public void verifyCancelSubscription() throws Exception {
		CancelSubscription l = new CancelSubscription();
		l.cancelSubscription();
	}

//	@Test(priority = 6)
	public void verifyCarFinder() throws Exception {
		CarFinder s = new CarFinder();
		s.carFinder();
	}

//	@Test(priority = 7)
	public void verifyTermsAndPrivacyPolicy() throws Exception {
		TermsAndConditions s = new TermsAndConditions();
		s.termsAndPrivacy();
	}

//	@Test(priority = 8)
	public void verifyCreateGeoFences() throws Exception {
		Modes m = new Modes();
		m.createGeoFence();
	}

}
