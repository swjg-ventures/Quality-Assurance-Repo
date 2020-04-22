package Demo;


import java.util.ArrayList;
import java.util.Random;

import org.testng.annotations.Test;


public class Random_Data  {

	String carname1 = "Demo", caricon1 = "//div[@class='grid']/div[1]", status1 = "//div[@class='_3fRlA9ofZ3wg1XWxsL0y2l_0']/parent::div", 
			year1 = "2015", make1 = "SKODA", VIN1 = "DHSJ879373J738H"; int model1 = 2;	
			
	String carname2 = "Demo_Car_Test", caricon2 = "//div[@class='grid']/div[10]", status2 = "//div[@class='_3fRlA9ofZ3wg1XWxsL0y2l_0']/parent::div", 
			year2 = "2018", make2 = "VOLVO", VIN2 = "MCSJ879373J736G"; int model2 = 6;

	String model_selected, Insurance_Eff_Date, Insurance_Exp_Date, car_Reg_exp;
	
	//Shuffling variables		
	
	//Car name
	String Scarname[]  = {carname1, carname2};
	String carname = Scarname[new Random().nextInt(Scarname.length)];
	
	//Car VIN
	String Svin[]  = {VIN1, VIN2};
	String VIN = Svin[new Random().nextInt(Scarname.length)];
	
	
	//Car icon
	String Scaricon[]  = {caricon1, caricon2};
	String caricon = Scaricon[new Random().nextInt(Scaricon.length)];
	
	
	//Year
	String Syear[]  = {year1, year2};
	String year = Syear[new Random().nextInt(Syear.length)];
	
	//Make
	String Smake[]  = {make1, make2};
	String make = Smake[new Random().nextInt(Smake.length)];
	
	//Model
	int Smodel[]  = {model1, model2};
	int model = Smodel[new Random().nextInt(Smodel.length)];	
	
	
	
	
	
	@Test
public void step2() throws Exception {
	
	
	ArrayList<String> arr1 = new ArrayList<String>();
	arr1.add(carname1);
	arr1.add(VIN1);
	arr1.add(caricon1);
	arr1.add(year1);

	ArrayList<String> arr2 = new ArrayList<String>();
	arr2.add(carname2);
	arr2.add(VIN2);
	arr2.add(caricon2);
	arr2.add(year2);
	
	ArrayList<String> arr3 = new ArrayList<String>();
	arr3.add("car3");
	arr3.add("vin3");
	arr3.add("3caricon");
	arr3.add("year3");
	Random rand = new Random();
		
	
	String Da[]  = {arr1.toString(), arr2.toString(), arr3.toString() };
	
	String D = Da[rand.nextInt(Da.length)];

	
	String array[] = D.split(",");
	
	System.out.println(String.valueOf(array[0]).replace("[", "").trim());
	System.out.println(String.valueOf(array[1]).trim());
	System.out.println(String.valueOf(array[2]).trim());
	System.out.println(String.valueOf(array[3]).replace("]", "").trim());
  
	
	
	
}














}
