package Demo;

import org.testng.annotations.Test;

public class Class3 {
	@Test(description = "Signup Free Bluetooth Device")
	public void verifySignupWithFreeBluetoothDevice() throws Exception {
		System.out.println("Signup Free Bluetooth Device");
	}

	@Test(description = "Signup Free Plus Paid Bluetooth Device")
	public void verifySignpuWithFreePlusPaidBluetoothDevice() throws Exception {
		System.out.println("Signup Free Plus Paid Bluetooth Device");
	}

	@Test(description = "Signup Free Bluetooth Device and the Activate personal device which contain no invoice")
	public void verifyActivateCellularDeviceWhichContainNoInvoiceUnderFreeBluetoothAccount() throws Exception {
		System.out.println("Signup Free Bluetooth Device and the Activate personal device which contain no invoice");
	}
}
