package unitTests;

import java.math.BigDecimal;
import java.math.MathContext;

import wkeever.campus.permit.Permit;

public class UnitTest 
{
	private Permit testPermit;
	private BigDecimal expectedValue;
	private BigDecimal expectedTotal;
	
	public UnitTest(Permit testPermit, BigDecimal expectedValue, BigDecimal expectedTotal)
	{
		this.testPermit = testPermit;
		this.expectedValue = expectedValue;
		this.expectedTotal = expectedTotal;
	}
	
	public boolean evaluateTest()
	{
		//Parse to two decimal places:
		BigDecimal expect = expectedValue.round(new MathContext(2));
		BigDecimal result = testPermit.getPrice().round(new MathContext(2));
		BigDecimal expect2 = expectedTotal.round(new MathContext(2));
		BigDecimal result2 = testPermit.getTotalPrice().round(new MathContext(2));
		return expect.compareTo(result) == 0 && expect2.compareTo(result2) == 0;
	}
	
	@Override 
	public String toString()
	{
		String str = "Expected: " + String.format("%.2f", expectedValue) + " Recieved: " + String.format("%.2f", testPermit.getPrice());
		str += " Expected: " + String.format("%.2f", expectedTotal) + " Recieved: " + String.format("%.2f", testPermit.getTotalPrice());
		if(evaluateTest())
		{
			str += " Test Passed!";
		}
		else
		{
			str += " Test Failed!";
		}
		return str;
	}
}
