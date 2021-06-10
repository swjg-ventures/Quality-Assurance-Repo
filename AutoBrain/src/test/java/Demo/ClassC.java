package Demo;

import org.testng.annotations.Test;

import com.autobrain.base.Base;
import com.autobrain.models.SignupModel;
import com.autobrain.pages.SignupWithRetailerDevice;

public class ClassC extends Base {
	@Test(description = "Bought personal from retailer")
	public void verifySignupWithPersonalDeviceBoughtFromRetailer() throws Exception {
		SignupModel SignupModel = new SignupModel();

		SignupModel.setTotal_bought_devices(1);
		SignupModel.setAccount_type("personal");
		SignupModel.setPersonal_plan("moneysaver");
		SignupModel.setChoose_personal_billing_interval("monthly");
		SignupModel.setPricing_plan("90 days personal plan");
		SignupModel.setSet_esf(false);

		SignupWithRetailerDevice ss = new SignupWithRetailerDevice(SignupModel);
		ss.signupWithRetailerDevice();
	}

	@Test(description = "Bought business from retailer")
	public void verifySignupWithBusinessDeviceBoughtFromRetailer() throws Exception {
		SignupModel SignupModel = new SignupModel();

		SignupModel.setTotal_bought_devices(1);
		SignupModel.setAccount_type("business");
		SignupModel.setPersonal_plan("vip");
		SignupModel.setChoose_business_billing_interval("monthly");
		SignupModel.setPricing_plan("Plan Type: Business Referral, Cost: $19.99, Monthly: $14.99, Free Days: 14");
		SignupModel.setSet_esf(false);

		SignupWithRetailerDevice ss = new SignupWithRetailerDevice(SignupModel);
		ss.signupWithRetailerDevice();
	}
}
