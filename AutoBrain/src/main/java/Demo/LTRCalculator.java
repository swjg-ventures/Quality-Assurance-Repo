package Demo;

import java.text.DecimalFormat;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.pages.Login;

public class LTRCalculator extends Login {
	SoftAssert softassert = new SoftAssert();

	public void ltrCalculator() throws Exception {

		driver.get("https://app.autobrain.com/");
		login("rajesh.kumar@beepermd.com", "welcome");

		driver.navigate().to("https://app.autobrain.com/worker/monthly_reports/ltr");

		// Enter distribution ID
		VisibilityOfElementByXpath("//input[@id='distribution_channel_id']", 15).sendKeys("520");

		// Click on search button
		List<WebElement> distribution_search_btn = VisibilityOfAllElementsByXpath("//input[@name='commit'] ", 15);
		distribution_search_btn.get(1).click();

		// Wait until the data not loaded successfully
		boolean data_loaded = VisibilityOfElementByXpath("//input[@class='btn btn-sm btn-danger']", 80).isDisplayed();
		Assert.assertEquals(data_loaded, true, "After click on the search button, data not loaded successfully!");

		amountCollected();
		System.out.println("----------------");

//		currentDevicesCount();
//		System.out.println("----------------");

		cancelledSubscriptions();
		System.out.println("----------------");

		trialingSubscriptions();
		System.out.println("----------------");

		activeSubscriptions();
		System.out.println("----------------");

		totalSubscriptions();
		System.out.println("----------------");

		activations();
		System.out.println("----------------");

		cancelledDevicesWithReturn();
		System.out.println("----------------");

		cancelledDevicesWithESFPaid();
		System.out.println("----------------");

		cancelledDevicesUpaidAndUnreturned();
		System.out.println("----------------");

		softassert.assertAll();
	}

	public void amountCollected() {

		// Total collected amount
		String totals = VisibilityOfElementByXpath("//tbody/tr[1]/td[2]", 15).getText().replace("$", "");
		double total_income = Double.parseDouble(totals);
		System.out.println("Total amount: " + total_income);

		// Adding monthly income
		List<WebElement> collecting_all_amount = VisibilityOfAllElementsByXpath("//tbody/tr/td[2]", 15);

		double sum = 0;
		for (int i = 1; i < collecting_all_amount.size(); i++) {
			String result = collecting_all_amount.get(i).getText().replace("$", "");
			double monthly_income = Double.parseDouble(result);
			sum = sum + monthly_income;
		}

		// Rounding the sum of monthly income
		DecimalFormat df2 = new DecimalFormat("#.##");
		String round = df2.format(sum);
		double all_months_cal_income = Double.parseDouble(round);
		System.out.println("All months calculated amount is: " + all_months_cal_income);
		softassert.assertEquals(total_income, all_months_cal_income,
				"Amount Collected--> Total and calculated value is not same!");
	}

	public void currentDevicesCount() {

		ltrCustomCalculator("Current Devices Count", "//tbody/tr[1]/td[3]", "//tbody/tr/td[3]");
	}

	public void cancelledSubscriptions() {

		ltrCustomCalculator("Cancelled Subscription", "//tbody/tr[1]/td[4]", "//tbody/tr/td[4]");
	}

	public void trialingSubscriptions() {

		ltrCustomCalculator("Trialing Subscription", "//tbody/tr[1]/td[5]", "//tbody/tr/td[5]");
	}

	public void activeSubscriptions() {

		ltrCustomCalculator("Active Subscription", "//tbody/tr[1]/td[6]", "//tbody/tr/td[6]");
	}

	public void totalSubscriptions() {
		ltrCustomCalculator("Total Subscription", "//tbody/tr[1]/td[7]", "//tbody/tr/td[7]");
	}

	public void activations() {
		ltrCustomCalculator("Activations", "//tbody/tr[1]/td[8]", "//tbody/tr/td[8]");
	}

	public void cancelledDevicesWithReturn() {
		ltrCustomCalculator("Cancelled Devices with Return", "//tbody/tr[1]/td[13]", "//tbody/tr/td[13]");
	}

	public void cancelledDevicesWithESFPaid() {
		ltrCustomCalculator("Cancelled Devices with ESF Paid", "//tbody/tr[1]/td[14]", "//tbody/tr/td[14]");
	}

	public void cancelledDevicesUpaidAndUnreturned() {
		ltrCustomCalculator("Cancelled Devices Unpaid & Unreturned", "//tbody/tr[1]/td[15]", "//tbody/tr/td[15]");
	}

	// Custom calculator
	public void ltrCustomCalculator(String methodName, String total_xpath, String monthly_xpath) {
		System.out.println(methodName);
		// Total
		String tot = VisibilityOfElementByXpath(total_xpath, 15).getText();
		int total = Integer.parseInt(tot);
		System.out.println("Total: " + total);

		// Monthly
		List<WebElement> monthly_ele = VisibilityOfAllElementsByXpath(monthly_xpath, 15);

		int sum = 0;
		for (int i = 1; i < monthly_ele.size(); i++) {
			String result = monthly_ele.get(i).getText();
			int monthly = Integer.parseInt(result);
			sum = sum + monthly;
		}
		System.out.println("Monthly: " + sum);
		softassert.assertEquals(total, sum,
				methodName + " --> Total active subscriptions and monthly active subscriptions count is not same!");

	}

}
