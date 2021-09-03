package com.autobrain.models;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.autobrain.base.Base;

public class SignupModel {

	// For properties
	private static Properties prop;

	// Mark devices sold and shipped file
	public static File csvFile = new File("Files\\Mark_Device_Sold_and_Shipped.csv");

	// To store all devices created from worker panel
	private ArrayList<String> All_Devices_No;

	// For card details
	private static String f_name = "Test";
	private static String l_name = "Demo";
	private static String street = "935 Gravier Place, Suite 1160, New Orleans, LA.";
	private static String city = "Boca";
	private static String state = "Florida";
	private static String zip = "70112";
	private static String phone = "1236547890";
	private static String card_name = "Demo card";
	private static String card_cvv = "555";
	private static String card_no = "4242424242424242";

	// For vehicle info
	public static String carname1 = "Testing car";
	public static String carname2 = "Demo car";
	public static String caricon1 = "//div[@class='grid']/div[1]";
	public static String caricon2 = "//div[@class='grid']/div[10]";
	private static String year1 = "2015";
	private static String year2 = "2018";
	private static String make1 = "SKODA";
	private static String make2 = "VOLVO";
	private static int model1 = 2;
	private static int model2 = 6;
	private static String vin1 = "DHSJ879373J738H";
	private static String vin2 = "MCSJ879373J736G";
	private static String licenceno1 = "TY78";
	private static String licenceno2 = "TY89";
	private static String effectivedate1 = "//div[@class='text-input'][2]/div/div[2]/div/span[contains(text(),'25')]";
	private static String effectivedate2 = "//div[@class='text-input'][2]/div/div[2]/div/span[contains(text(),'26')]";
	private static String expiredate1 = "//div[@class='text-input'][3]/div/div[2]/div/span[contains(text(),'28')]";
	private static String expiredate2 = "//div[@class='text-input'][3]/div/div[2]/div/span[contains(text(),'27')]";
	private static String insurancecmpy1 = "Demo";
	private static String insurancecmpy2 = "Test";
	private static String policyno1 = "55512";
	private static String policyno2 = "44125";

	// Others
	private String account_type;
	private String bluetooth_signup_tier = "none";
	private boolean bluetooth_upgraded = false;
	private String owner_email;
	private String device_no;
	private int random_int;
	private int total_bought_devices = 1;
	private String personal_plan;
	private String personal_billing_interval;
	private String choose_business_billing_interval;
	private String pricing_plan;
	private boolean set_esf;
	private boolean confirmation_email;

	public boolean isBluetooth_upgraded() {
		return bluetooth_upgraded;
	}

	public void setBluetooth_upgraded(boolean bluetooth_upgraded) {
		this.bluetooth_upgraded = bluetooth_upgraded;
	}

	public String getBluetooth_signup_tier() {
		return bluetooth_signup_tier;
	}

	public void setBluetooth_signup_tier(String bluetooth_signup_tier) {
		this.bluetooth_signup_tier = bluetooth_signup_tier;
	}

	public String getPricing_plan() {
		return pricing_plan;
	}

	public void setPricing_plan(String pricing_plan) {
		this.pricing_plan = pricing_plan;
	}

	public boolean isConfirmation_email() {
		return confirmation_email;
	}

	public void setConfirmation_email(boolean confirmation_email) {
		this.confirmation_email = confirmation_email;
	}

	public ArrayList<String> getAll_Devices_No() {
		return All_Devices_No;
	}

	public void setAll_Devices_No(ArrayList<String> all_Devices_No) {
		All_Devices_No = all_Devices_No;
	}

	public boolean isSet_esf() {
		return set_esf;
	}

	public void setSet_esf(boolean set_esf) {
		this.set_esf = set_esf;
	}

	public String getChoose_business_billing_interval() {
		return choose_business_billing_interval;
	}

	public void setChoose_business_billing_interval(String choose_business_billing_interval) {
		if (choose_business_billing_interval.contains("monthly")) {
			choose_business_billing_interval = "//div[@class='TpDbnpVtZG__uMj7UUtnd_0']/div[1]//button";
		}

		if (choose_business_billing_interval.contains("yearly")) {
			choose_business_billing_interval = "//div[@class='TpDbnpVtZG__uMj7UUtnd_0']/div[2]//button";
		}

		this.choose_business_billing_interval = choose_business_billing_interval;
	}

	public String getPersonal_plan() {
		return personal_plan;
	}

	public void setPersonal_plan(String personal_plan) {
		if (personal_plan.contains("shell")) {
			personal_plan = "//div[text()='Shell® | Fuel Rewards®']//following::button[1]";
		}
		
		if (personal_plan.contains("vip")) {
			personal_plan = "//div[text()='VIP']//following::button[1]";
		}

		if (personal_plan.contains("essential")) {
			personal_plan = "//div[text()='Essential']//following::button[1]";

		}

		if (personal_plan.contains("moneysaver")) {
			personal_plan = "//div[text()='Money Saver']//following::button[1]";
		}
		this.personal_plan = personal_plan;
	}

	public String getPersonal_billing_interval() {
		return personal_billing_interval;
	}

	public void setChoose_personal_billing_interval(String personal_billing_interval) {
		if (personal_billing_interval.contains("monthly")) {
			personal_billing_interval = "//div[@class='TpDbnpVtZG__uMj7UUtnd_0']/div[1]/button";
		}

		if (personal_billing_interval.contains("annual")) {
			personal_billing_interval = "//div[@class='TpDbnpVtZG__uMj7UUtnd_0']/div[2]/button";
		}
		this.personal_billing_interval = personal_billing_interval;
	}

	public String getAccount_type() {
		return account_type;
	}

	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}

	public String getOwner_email() {
		return owner_email;
	}

	public void setOwner_email(String owner_email) {
		this.owner_email = owner_email;
	}

	// Shuffling vehicle info variables

	// Car name
	static String Scarname[] = { carname1, carname2 };
	private static String carname = Scarname[new Random().nextInt(Scarname.length)];

	// Car vin
	static String Svin[] = { vin1, vin2 };
	private static String vin = Svin[new Random().nextInt(Svin.length)];

	// Car icon
	static String Scaricon[] = { caricon1, caricon2 };
	private static String caricon = Scaricon[new Random().nextInt(Scaricon.length)];

	// Year
	static String Syear[] = { year1, year2 };
	private static String year = Syear[new Random().nextInt(Syear.length)];

	// Make
	static String Smake[] = { make1, make2 };
	private static String make = Smake[new Random().nextInt(Smake.length)];

	// Model
	static int Smodel[] = { model1, model2 };
	private static int model = Smodel[new Random().nextInt(Smodel.length)];

	// License
	static String Slicenceno[] = { licenceno1, licenceno2 };
	private static String licenceno = Slicenceno[new Random().nextInt(Slicenceno.length)];

	// Effective date
	static String Seffectivedate[] = { effectivedate1, effectivedate2 };
	private static String effectivedate = Seffectivedate[new Random().nextInt(Seffectivedate.length)];

	// Expire date
	static String Sexpiredate[] = { expiredate1, expiredate2 };
	private static String expiredate = Sexpiredate[new Random().nextInt(Sexpiredate.length)];

	// Insurance company
	static String Sinsurancecmpy[] = { insurancecmpy1, insurancecmpy2 };
	private static String insurancecmpy = Sinsurancecmpy[new Random().nextInt(Sinsurancecmpy.length)];

	// Insurance company
	static String Spolicyno[] = { policyno1, policyno2 };
	private static String policyno = Spolicyno[new Random().nextInt(Spolicyno.length)];

	// Add device button
	@FindBy(xpath = "//a[contains(text(),'Add device #')]")
	public List<WebElement> add_device_btn;

	// Number of input devices
	@FindBy(xpath = "//input[@placeholder='Enter device number']")
	public List<WebElement> no_of_input_devices;

	public static String getF_name() {
		return f_name;
	}

	public static void setF_name(String f_name) {
		SignupModel.f_name = f_name;
	}

	public static String getL_name() {
		return l_name;
	}

	public static void setL_name(String l_name) {
		SignupModel.l_name = l_name;
	}

	public static String getStreet() {
		return street;
	}

	public static void setStreet(String street) {
		SignupModel.street = street;
	}

	public static String getCity() {
		return city;
	}

	public static void setCity(String city) {
		SignupModel.city = city;
	}

	public static String getState() {
		return state;
	}

	public static void setState(String state) {
		SignupModel.state = state;
	}

	public static String getZip() {
		return zip;
	}

	public static void setZip(String zip) {
		SignupModel.zip = zip;
	}

	public static String getPhone() {
		return phone;
	}

	public static void setPhone(String phone) {
		SignupModel.phone = phone;
	}

	public static String getCard_name() {
		return card_name;
	}

	public static void setCard_name(String card_name) {
		SignupModel.card_name = card_name;
	}

	public static String getCard_cvv() {
		return card_cvv;
	}

	public static void setCard_cvv(String card_cvv) {
		SignupModel.card_cvv = card_cvv;
	}

	public static String getCard_no() {
		return card_no;
	}

	public static void setCard_no(String card_no) {
		SignupModel.card_no = card_no;
	}

	public static String getCarname() {
		return carname;
	}

	public static void setCarname(String carname) {
		SignupModel.carname = carname;
	}

	public static String getVIN() {
		return vin;
	}

	public static void setVIN(String vIN) {
		vin = vIN;
	}

	public static String getCaricon() {
		return caricon;
	}

	public static void setCaricon(String caricon) {
		SignupModel.caricon = caricon;
	}

	public static String getCar_hybrid_status() {
		Base base = new Base();

		if (base.VisibilityOfElementByXpath("//div[@class='_3fRlA9ofZ3wg1XWxsL0y2l_0']/parent::div/div/span", 5)
				.getText().equals("ON")) {
			return "ON";
		} else {
			return "OFF";
		}

	}

	public static String getYear() {
		return year;
	}

	public static void setYear(String year) {
		SignupModel.year = year;
	}

	public static String getMake() {
		return make;
	}

	public static void setMake(String make) {
		SignupModel.make = make;
	}

	public static int getModel() {
		return model;
	}

	public static void setModel(int model) {
		SignupModel.model = model;
	}

	public static String getLicenceno() {
		return licenceno;
	}

	public static void setLicenceno(String licenceno) {
		SignupModel.licenceno = licenceno;
	}

	public static String getEffectivedate() {
		return effectivedate;
	}

	public static void setEffectivedate(String effectivedate) {
		SignupModel.effectivedate = effectivedate;
	}

	public static String getExpiredate() {
		return expiredate;
	}

	public static void setExpiredate(String expiredate) {
		SignupModel.expiredate = expiredate;
	}

	public static String getInsurancecmpy() {
		return insurancecmpy;
	}

	public static void setInsurancecmpy(String insurancecmpy) {
		SignupModel.insurancecmpy = insurancecmpy;
	}

	public static String getPolicyno() {
		return policyno;
	}

	public static void setPolicyno(String policyno) {
		SignupModel.policyno = policyno;
	}

	public String getDevice_no() {
		return device_no;
	}

	public void setDevice_no(String device_no) {
		this.device_no = device_no;
	}

	public int getRandom_int() {
		return random_int;
	}

	public void setRandom_int(int random_int) {
		this.random_int = random_int;
	}

	public int getTotal_bought_devices() {
		return total_bought_devices;
	}

	public void setTotal_bought_devices(int total_bought_devices) {
		this.total_bought_devices = total_bought_devices;
	}

	public static Properties getProp() {
		return prop;
	}

	public static void setProp(Properties prop) {
		SignupModel.prop = prop;
	}

}
