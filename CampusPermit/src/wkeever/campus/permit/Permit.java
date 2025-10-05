package wkeever.campus.permit;

import java.math.BigDecimal;

import utils.Range;

/**
 * This is a simple interface that determines the basic ideas behind a permit
 */
public interface Permit 
{
	public static final BigDecimal CAMPUS_FEE = new BigDecimal(0.05);
	public static final BigDecimal CARPOOL_DEDUCTION = new BigDecimal(0.9);
	public static final Range validMonthRange = new Range(new BigDecimal(1), new BigDecimal(12));
	public static enum VehicleMultiplier 
	{
		CAR(new BigDecimal(1)),
		SUV(new BigDecimal(1.15)),
		MOTORCYCLE(new BigDecimal(0.7));
		
		public final BigDecimal PRICE_MULTIPLER; //In this case it is easier for this to be public
		private VehicleMultiplier(BigDecimal priceMultiplier)
		{
			this.PRICE_MULTIPLER = priceMultiplier;
		}
	}
	/**
	 * This function simply returns the price of the permit based on all of the useful information
	 * 
	 * In theory some order should be: permit base price * carMult * Carpool (y/n)
	 * 
	 */
	public abstract BigDecimal getPricePerMonth();
	/**
	 * This function simply returns the price of the permit based on all of the useful information
	 * 
	 * In theory some order should be: permit base price * carMult * Carpool (y/n) * numberOfmonths
	 */
	public abstract BigDecimal getPrice();
	/**
	 * Takes the price and multiplies the campus fee
	 * 
	 */
	public abstract BigDecimal getCampusFee();
	/**
	 * Combines the price and the campus fee
	 */
	public abstract BigDecimal getTotalPrice();
}
