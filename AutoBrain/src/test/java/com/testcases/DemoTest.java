package com.testcases;

import org.testng.annotations.Test;
import com.pages.AlertSettings;
import com.pages.BeepCarpool;
import com.pages.CallEmergency;
import com.pages.CarFinder;
import com.pages.CarGroups;
import com.pages.CustomerService;
import com.pages.DownloadApp;
import com.pages.GetAnotherDevice;
import com.pages.ListOfCars;
import com.pages.Modes;
import com.pages.Registration;
import com.pages.RoadsideAssistance;
import com.pages.StolenCar;
import com.pages.VehicleProfile;

import Demo.Ck;




public class DemoTest extends VehicleProfile {




	// ALERT SETTINGS MAIN MENU
		@Test(priority = 22)
		public void VerifyAlertSettings() throws Exception {
			DownloadApp a = new DownloadApp();
			a.DownloadAppLinks();
		}
		
		
	
}
