package wkeever.campus.permit;

import java.math.BigDecimal;

import utils.OutOfRangeException;

public class CommuterPermit implements Permit
{
	public static final BigDecimal basePrice = new BigDecimal(35);
	public static final BigDecimal naturalDiscount = new BigDecimal(0.85);

	public final VehicleMultiplier vehicleType;
	public final boolean isCarpool;
	public final int numberOfMonths;
	
	public CommuterPermit(VehicleMultiplier vehicleType, boolean isCarpool, int numberOfMonths) throws OutOfRangeException
	{
		this.vehicleType = vehicleType;
		this.isCarpool = isCarpool;
		if(!validMonthRange.withInRange(new BigDecimal(numberOfMonths)))
		{
			throw new OutOfRangeException("Number of Months", validMonthRange);
		}
		this.numberOfMonths = numberOfMonths;
	}
	
	@Override
	public BigDecimal getPricePerMonth()
	{
		BigDecimal price = basePrice.multiply(naturalDiscount);
		//Now do the natural calculations:
		price = price.multiply(vehicleType.PRICE_MULTIPLER);
		if(isCarpool)
		{
			price = price.multiply(CARPOOL_DEDUCTION);
		}
		return price;
	}
	
	@Override
	public BigDecimal getPrice()
	{
		BigDecimal price = getPricePerMonth().multiply(new BigDecimal(numberOfMonths));
		return price;
	}
	
	@Override
	public BigDecimal getCampusFee()
	{
		return CAMPUS_FEE.multiply(getPrice());
	}
	
	@Override
	public BigDecimal getTotalPrice()
	{
		return getCampusFee().add(getPrice());
	}
}