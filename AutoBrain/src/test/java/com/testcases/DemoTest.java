package com.testcases;

import org.testng.annotations.Test;
import com.base.Base;
import com.pages.Login;
import com.pages.Signup;
import com.pages.SignupWithManualInvoice;

public class DemoTest extends Base {

//		@Test(priority = 1)
	public void VerifyRegister1() throws Exception {
		Signup a = new Signup();
		a.signup();

	}

	@Test(priority = 1)
	public void Manually_Create_Invoice_And_Signup() throws Exception {
		SignupWithManualInvoice order = new SignupWithManualInvoice();
		order.signupWithManualInvoice();
	}
	
//	@Test(priority = 1)
	public void verifyLogin() throws Exception {
		Login l = new Login();
		l.login("john@examplecom", "welcomee");
	}

}
