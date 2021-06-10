package Demo;

import org.testng.annotations.Test;

import com.autobrain.base.Base;
import com.autobrain.models.SignupModel;
import com.autobrain.pages.SignupWithBoughtDeviceFromABWebsite;
import com.autobrain.pages.SignupWithRetailerDevice;

public class ClassA extends Base {
	@Test(description = "Bought personal from AB website")
	public void verifySignupWithPersonalDeviceBoughtFromAB() throws Exception {
		SignupModel SignupModel = new SignupModel();

		SignupModel.setTotal_bought_devices(1);
		SignupModel.setAccount_type("personal");
		SignupModel.setPersonal_plan("vip");
		SignupModel.setChoose_personal_billing_interval("monthly");
		SignupModel.setSet_esf(false);

		SignupWithBoughtDeviceFromABWebsite ss = new SignupWithBoughtDeviceFromABWebsite(SignupModel);
		ss.signupWithBoughtDeviceFromABWebsite();
	}

	@Test(description = "Bought business from AB website")
	public void verifySignupWithBusinessDeviceBoughtFromAB() throws Exception {

		SignupModel SignupModel = new SignupModel();

		SignupModel.setTotal_bought_devices(1);
		SignupModel.setAccount_type("business");
		SignupModel.setPersonal_plan("vip");
		SignupModel.setChoose_business_billing_interval("yearly");
		SignupModel.setSet_esf(false);

		SignupWithBoughtDeviceFromABWebsite ss = new SignupWithBoughtDeviceFromABWebsite(SignupModel);
		ss.signupWithBoughtDeviceFromABWebsite();
	}
}
