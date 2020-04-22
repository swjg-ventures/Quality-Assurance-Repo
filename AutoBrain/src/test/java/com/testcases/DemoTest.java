package com.testcases;

import org.testng.annotations.Test;
import com.base.Base;
import com.pages.Delete_Devices_From_Panel;
import com.pages.Manually_Create_Invoice_And_Signup;
import com.pages.Register;






public class DemoTest extends Base {




	
 		@Test(priority = 1)
		public void VerifyRegister1() throws Exception {
			Register a = new Register();
			a.register();
		}

	
		
//		@Test(priority = 1)		
		public void Manually_Create_Invoice_And_Signup() throws Exception {
			Manually_Create_Invoice_And_Signup order = new Manually_Create_Invoice_And_Signup();
			order.RegisterWithCustomerInvoice();
		}
		
		
		
		
//		@Test(priority = 1)		
		public void Dlt_Devices() throws Exception {
			Delete_Devices_From_Panel order = new Delete_Devices_From_Panel();
			order.delete_devices();
		}
		

	
}
