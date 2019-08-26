package com.testcases;

import org.testng.annotations.Test;

import com.pages.ForgotPassword;

public class MainTest extends ForgotPassword {

	// LOGIN
	@Test(priority = 1)
	public void VerifyBlankLogin() throws Exception {
		BlankLogin();
	}

	// LOGIN
	@Test(priority = 2)
	public void VerifyInvalidLogin() throws Exception {
		InvalidLogin();
	}

	// LOGIN
	@Test(priority = 3)
	public void VerifyValidLogin() throws Exception {
		ValidLogin();
	}

	// LOGIN
	@Test(priority = 4)
	public void VerifyLogoutUser() throws Exception {
		LogoutUser();
	}

	// SIGNUP
	@Test(priority = 5)
	public void VerifyValidSignup() throws Exception {
		ValidSignup();
	}

	// FORGOT PASSWORD
	@Test(priority = 6)
	public void VerifyEnteringRegisteredEmailInForgotPasswordField() throws Exception {
		RegisteredEmail();
	}

	// FORGOT PASSWORD
	@Test(priority = 7)
	public void VerifyEnteringInvalidEmailInForgotPasswordFiled() throws Exception {
		EnterInvalidEmail();
	}

	// FORGOT PASSWORD
	@Test(priority = 8)
	public void VerifyForgotPasswordCancelButtonRedirection() throws Exception {
		CancelBtn();
	}

	// SIGNUP- TERMS AND CONDITIONS LINK
	@Test(priority = 9)
	public void VerifyTermsAndConditionsLinkOnSignupPage() throws Exception {
		TermsandConditions();
	}

	// SIGNUP- PRIVACY POLICY LINK
	@Test(priority = 10)
	public void VerifyPrivacyPolicyLinkOnSignupPage() throws Exception {
		PrivayPolicy();
	}

	// SINGUP- CHAT BUTTON FUNCTIONALITY
	@Test(priority = 11)
	public void VerifyChatButtonFunctionality() throws Exception {
		ChatButton();
	}

	// MANAGE ACCOUNT- UPDATE CONTACT INFO
	@Test(priority = 12)
	public void VerifyUpdatingContactInfo() throws Exception {
		UpdatingContactInfo();
	}

	// MANAGE ACCOUNT- CHANGE PASSWORD
	@Test(priority = 13)
	public void VerifyChangePassword() throws Exception {
		ChangePasswordWIthBlankDetails();
		ChangePasswordWIthWrongDetails();
		ChangePasswordWIthCorrectDetails();
	}
	
	
	

}
