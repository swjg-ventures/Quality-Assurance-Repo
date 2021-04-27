package com.autobrain.testcases;

import org.testng.annotations.Test;
import com.autobrain.base.Base;
import com.autobrain.pages.DeviceReplacement;
import com.autobrain.pages.MarkReplacedDeviceForReuse;
import com.autobrain.pages.SignupWithBluetoothDevice;
import com.autobrain.pages.SignupWithBoughtDeviceFromABWebsite;
import com.autobrain.pages.SignupWithRetailerDevice;


public class DemoTest extends Base {




	@Test(priority = 1)
	public void ValidatePersonalBoughtDeviceTests() throws Exception {
		SignupWithBoughtDeviceFromABWebsite ss = new SignupWithBoughtDeviceFromABWebsite();
		ss.signupWithBoughtDeviceFromABWebsite(1, "personal", "vip", "yearly", false);
	}

//	@Test(priority = 2)
	public void ValidateBusinessBoughtDeviceTests() throws Exception {
		SignupWithBoughtDeviceFromABWebsite ss = new SignupWithBoughtDeviceFromABWebsite();
		ss.signupWithBoughtDeviceFromABWebsite(1, "business", "vip", "yearly", false);
	}

//	@Test(priority = 3)
	public void ValidatePersonalRetailerTests() throws Exception {
		SignupWithRetailerDevice ss = new SignupWithRetailerDevice();
		ss.signupWithRetailerDevice(1, "personal", "vip", "monthly", "90 days personal plan", false);
	}

//	@Test(priority = 4)
	public void ValidateBusinessRetailerTests() throws Exception {
		SignupWithRetailerDevice ss = new SignupWithRetailerDevice();
		ss.signupWithRetailerDevice(2, "business", "vip", "monthly",
				"Plan Type: Business Referral, Cost: $19.99, Monthly: $14.99, Free Days: 14", false);
	}

//	@Test(priority = 5)
	public void ValidateFreeBluetoothTests() throws Exception {
		SignupWithBluetoothDevice ss = new SignupWithBluetoothDevice();
		ss.signupWithBluetoothDevice("personal", "free", "vip", "monthly",
				"Personal Tier Free, Price: $49.97, Monthly: $0.00, Free Days: 90", false);
	}

//	@Test(priority = 6)
	public void ValidatePaidBluetoothTests() throws Exception {
		SignupWithBluetoothDevice ss = new SignupWithBluetoothDevice();
		ss.signupWithBluetoothDevice("personal", "free_plus_paid", "vip", "monthly",
				"Personal Tier Free, Price: $49.97, Monthly: $0.00, Free Days: 90", false);
	}

//	@Test(priority = 7)
	public void ValidateDeviceReplacement() throws Exception {
		DeviceReplacement n = new DeviceReplacement();
		n.deviceReplacement();
	}

//	@Test(priority = 8)
	public void verifyMarkReplacedDeviceForReuse() throws Exception {
		MarkReplacedDeviceForReuse l = new MarkReplacedDeviceForReuse();
		l.markReplacedDeviceForReuse();
	}

}
