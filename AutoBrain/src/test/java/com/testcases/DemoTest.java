package com.testcases;

import org.testng.annotations.Test;
import com.base.Base;
import com.pages.CancelSubscription;
import com.pages.Signup;
import com.pages.SignupWithManualInvoice;
import com.pages.VehicleProfile;


public class DemoTest extends Base {

//	@Test(priority = 1)
	public void VerifyRegister1() throws Exception {
		Signup a = new Signup();
		a.signup("personal");

	}

//	@Test(priority = 1)
	public void Manually_Create_Invoice_And_Signup() throws Exception {
		SignupWithManualInvoice order = new SignupWithManualInvoice();
		order.signupWithManualInvoice("personal");
	}


	@Test(priority = 1)
	public void verifyCarFinder() throws Exception {
		VehicleProfile l = new VehicleProfile();
		l.VehicleProfileInfo();
	}

//	@Test(priority = 1)
	public void verifyCancelSubscription() throws Exception {
		CancelSubscription l = new CancelSubscription();
		l.cancelSubscription();
	}

}
