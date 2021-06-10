package Demo;

import org.testng.annotations.Test;

public class Class1 {
	@Test(description = "Bought personal from AB website")
	public void verifySignupWithPersonalDeviceBoughtFromAB() throws Exception {
		System.out.println("Bought personal from AB website");
	}

	@Test(description = "Bought business from AB website")
	public void verifySignupWithBusinessDeviceBoughtFromAB() throws Exception {
		System.out.println("Bought business from AB website");
		
	}
}
