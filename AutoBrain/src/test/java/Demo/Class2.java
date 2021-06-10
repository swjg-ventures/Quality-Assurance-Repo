package Demo;

import org.testng.annotations.Test;


public class Class2 {
	@Test(description = "Bought personal from retailer")
	public void verifySignupWithPersonalDeviceBoughtFromRetailer() throws Exception {
		System.out.println("Bought personal from retailer");
	}

	@Test(description = "Bought business from retailer")
	public void verifySignupWithBusinessDeviceBoughtFromRetailer() throws Exception {
		System.out.println("Bought business from retailer");
	}
}
