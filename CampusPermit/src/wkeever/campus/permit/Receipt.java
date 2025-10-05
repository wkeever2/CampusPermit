package wkeever.campus.permit;

import java.math.BigDecimal;

public class Receipt 
{
	private Permit permit;
	
	public Receipt(Permit permit)
	{
		this.permit = permit;
	}
	
	public String generateReceipt()
	{
		String str = "";
		String numberOfMonths = "";
		
		if(permit instanceof ResidentPermit)
		{
			str += String.format("Resident Permit Base Price: $%.2f/month\n", ResidentPermit.basePrice);
			str += String.format("Vehicle Price Mulitplier: %.2f\n", ((ResidentPermit)permit).vehicleType.PRICE_MULTIPLER);
			if(((ResidentPermit)permit).isCarpool)
			{
				str += String.format("Carpool Price Mulitplier: %.2f\n", Permit.CARPOOL_DEDUCTION);
			}
			else
			{
				str += "NOT CARPOOLING\n";
			}
			numberOfMonths += "Number of months: " + ((ResidentPermit)permit).numberOfMonths + "\n";
		}
		else if(permit instanceof CommuterPermit)
		{
			str += String.format("Commuter Permit Base Price: $%.2f/month\n", CommuterPermit.basePrice.multiply(CommuterPermit.naturalDiscount));
			str += String.format("Vehicle Price Mulitplier: %.2f\n", ((CommuterPermit)permit).vehicleType.PRICE_MULTIPLER);
			if(((CommuterPermit)permit).isCarpool)
			{
				str += String.format("Carpool Price Mulitplier: %.2f\n", Permit.CARPOOL_DEDUCTION);
			}
			else
			{
				str += "NOT CARPOOLING\n";
			}
			numberOfMonths += "Number of months: " + ((CommuterPermit)permit).numberOfMonths + "\n";
		}
		BigDecimal pricePerMonth = permit.getPricePerMonth();
		str += String.format("Price per Month: $%.2f/month\n", pricePerMonth);
		str += numberOfMonths;
		BigDecimal subtotal = permit.getPrice();
		str += String.format("Subtotal: $%.2f\n", subtotal);
		BigDecimal campusFee = permit.getCampusFee();
		str += String.format("Campus Fee: $%.2f\n", campusFee);
		BigDecimal total = permit.getTotalPrice();
		str += String.format("Total: $%.2f\n", total);
		
		return str;
	}
}
