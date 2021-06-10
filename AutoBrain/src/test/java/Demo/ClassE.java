package Demo;

import org.testng.annotations.Test;

import com.autobrain.base.Base;
import com.autobrain.models.SignupModel;
import com.autobrain.pages.SignupWithBluetoothDevice;

public class ClassE extends Base {
	@Test(description = "Signup Free Bluetooth Device")
	public void verifySignupWithFreeBluetoothDevice() throws Exception {
		SignupModel SignupModel = new SignupModel();

		SignupModel.setAccount_type("personal");
		SignupModel.setBluetooth_is("free");
		SignupModel.setPersonal_plan("vip");
		SignupModel.setChoose_personal_billing_interval("monthly");
		SignupModel.setPricing_plan("Personal Tier Free, Price: $49.97, Monthly: $0.00, Free Days: 90");
		SignupModel.setSet_esf(false);

		SignupWithBluetoothDevice ss = new SignupWithBluetoothDevice(SignupModel);
		ss.signupWithBluetoothDevice(false);
	}

	@Test(description = "Signup Free Plus Paid Bluetooth Device")
	public void verifySignpuWithFreePlusPaidBluetoothDevice() throws Exception {
		SignupModel SignupModel = new SignupModel();

		SignupModel.setAccount_type("personal");
		SignupModel.setBluetooth_is("free_plus_paid");
		SignupModel.setPersonal_plan("vip");
		SignupModel.setChoose_personal_billing_interval("monthly");
		SignupModel.setPricing_plan("Personal Tier Free, Price: $49.97, Monthly: $0.00, Free Days: 90");
		SignupModel.setSet_esf(false);

		SignupWithBluetoothDevice ss = new SignupWithBluetoothDevice(SignupModel);
		ss.signupWithBluetoothDevice(false);
	}

	@Test(description = "Signup Free Bluetooth Device and the Activate personal device which contain no invoice")
	public void verifyActivateCellularDeviceWhichContainNoInvoiceUnderFreeBluetoothAccount() throws Exception {
		SignupModel SignupModel = new SignupModel();

		SignupModel.setAccount_type("personal");
		SignupModel.setBluetooth_is("free");
		SignupModel.setPersonal_plan("vip");
		SignupModel.setChoose_personal_billing_interval("monthly");
		SignupModel.setPricing_plan("Personal Tier Free, Price: $49.97, Monthly: $0.00, Free Days: 90");
		SignupModel.setSet_esf(false);

		SignupWithBluetoothDevice ss = new SignupWithBluetoothDevice(SignupModel);
		ss.signupWithBluetoothDevice(true);
	}
}
