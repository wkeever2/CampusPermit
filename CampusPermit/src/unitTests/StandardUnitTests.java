package unitTests;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import utils.OutOfRangeException;
import wkeever.campus.permit.CommuterPermit;
import wkeever.campus.permit.Permit;
import wkeever.campus.permit.ResidentPermit;

public class StandardUnitTests 
{
	private List<UnitTest> tests = new ArrayList<>();
	private boolean allPassed = false;
	
	public StandardUnitTests() throws OutOfRangeException
	{
		Permit firstPermit = new ResidentPermit(Permit.VehicleMultiplier.CAR, false, 6);
		Permit secondPermit = new CommuterPermit(Permit.VehicleMultiplier.SUV, true, 2);
		Permit thirdPermit = new ResidentPermit(Permit.VehicleMultiplier.MOTORCYCLE, true, 12); //I'm not sure how you'd carpool like this, but its an option!
		Permit fourthPermit = new ResidentPermit(Permit.VehicleMultiplier.SUV, false, 11);
		Permit fifthPermit = new CommuterPermit(Permit.VehicleMultiplier.CAR, true, 9);
		
		//Add 'em all
		tests.add(new UnitTest(firstPermit, new BigDecimal(270.00), new BigDecimal(283.50)));
		tests.add(new UnitTest(secondPermit, new BigDecimal(61.58), new BigDecimal(64.66)));
		tests.add(new UnitTest(thirdPermit, new BigDecimal(340.20), new BigDecimal(357.21)));
		tests.add(new UnitTest(fourthPermit, new BigDecimal(569.25), new BigDecimal(597.71)));
		tests.add(new UnitTest(fifthPermit, new BigDecimal(240.97), new BigDecimal(253.02)));
	}
	
	public String evaluateAll()
	{
		String str = "";
		int count = 0;
		for(int i = 0 ; i < tests.size() ; i++)
		{
			str += "Test #" + i + ". " + tests.get(i).toString() + "\n";
			if(tests.get(i).evaluateTest())
			{
				count++;
			}
		}
		str += "\nResults: Passed " + count + " of " + tests.size();
		if(count == tests.size()) 
		{
			allPassed = true;
		}
		return str;
	}
	
	public boolean getResult()
	{
		return allPassed;
	}
}
