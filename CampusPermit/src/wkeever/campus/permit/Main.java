package wkeever.campus.permit;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

import unitTests.StandardUnitTests;
import utils.OutOfRangeException;
import wkeever.campus.permit.Permit.VehicleMultiplier;

public class Main {

	public static void main(String[] args) 
	{
		// Make sure to verify the whole system is working:
		try
		{
			File unitTestOutputFile = new File("UnitTests.txt");
			if(!unitTestOutputFile.exists())
			{
				unitTestOutputFile.createNewFile();
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(unitTestOutputFile));
			
			StandardUnitTests tester = new StandardUnitTests();
			writer.write(tester.evaluateAll());
			
			if(!tester.getResult())
			{
				//We have an issue so exit the program
				System.out.println("Error detected! Exiting the program! Please review the Readme.md for more information!");
				System.exit(1);
			}
			writer.close();
		}
		catch(OutOfRangeException e) //If this triggers we definitely know something is wrong!
		{
			e.printStackTrace();
			System.exit(1);
		}
		catch(IOException e) //If this triggers we definitely know something is wrong!
		{
			e.printStackTrace();
			System.exit(1);
		}

		
		//Now let the user do their work:
		System.out.println("Welcome to the campus permit application!");
		Scanner sc = new Scanner(System.in);
		
		//No loop seems to be necessary here but if there was a loop needed, we'd insert it here:
		
		boolean typedCorrectly = false;
		String type = "";
		String carType = "";
		String carpool = "";
		int months = 1;
		do
		{
			System.out.println("Are you a commuter or a resident? (R/C)");
			type = sc.nextLine().toUpperCase();
			if(type.equals("R") || type.equals("C"))
			{
				typedCorrectly = true;
			}
			else
			{
				System.out.println("Not a valid character!!");
			}
		}
		while(!typedCorrectly);
		
		typedCorrectly = false;
		do
		{
			System.out.println("What type of car do you drive? ([C]ar, [S]UV, [M]otorcycle)");
			carType = sc.nextLine();
			if(carType.equals("C") || carType.equals("S") || carType.equals("M"))
			{
				typedCorrectly = true;
			}
			else
			{
				System.out.println("Not a valid character!!");
			}
		}
		while(!typedCorrectly);
		
		typedCorrectly = false;
		do
		{
			System.out.println("Will you carpool? (Y/N)");
			carpool = sc.nextLine();
			if(carpool.equals("Y") || carpool.equals("N"))
			{
				typedCorrectly = true;
			}
			else
			{
				System.out.println("Not a valid character!!");
			}
		}
		while(!typedCorrectly);
		
		typedCorrectly = false;
		do
		{
			System.out.println("For how many months will you be needing this permit? (1-12 Months)");
			String monthsString = sc.nextLine();
			//Convert months to an integer:
			try
			{
				months = Integer.valueOf(monthsString.strip()); //Get rid of trailing white spaces
			}
			catch(NumberFormatException e)
			{
				//This means the string is not an int!!
				months = -1;
				System.out.println("Please enter an integer!!");
			}
			if(Permit.validMonthRange.withInRange(new BigDecimal(months)))
			{
				typedCorrectly = true;
			}
			else
			{
				System.out.println("Not within range!!");
			}
		}
		while(!typedCorrectly);
		
		//Now finally make it so that the user can actually make the permit:
		Permit permit = null;
		VehicleMultiplier vehicleType = VehicleMultiplier.CAR;
		if(carType.equals("S"))
		{
			vehicleType = VehicleMultiplier.SUV;
		}
		else if(carType.equals("M"))
		{
			vehicleType = VehicleMultiplier.MOTORCYCLE;
		}
		boolean isCarpool = carpool.equals("Y");
		
		try
		{
			if(type.equals("R"))
			{
				permit = new ResidentPermit(vehicleType, isCarpool, months);
			}
			else //We know this has to be the commuter one:
			{
				permit = new CommuterPermit(vehicleType, isCarpool, months);
			}
		}
		catch(OutOfRangeException e)
		{
			//This will not happen but if it does, just tell the user we had an error with the number of months and to try again at a later time:
			System.out.println("There is an issue on our end handling the number of months. Please try again at a later time");
			System.exit(1);
		}
		//Now we can give them their receipt:
		Receipt receipt = new Receipt(permit);
		System.out.println(receipt.generateReceipt());
	}

}
