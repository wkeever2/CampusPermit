package utils;

import java.math.BigDecimal;

public class Range 
{
	private BigDecimal min;
	private BigDecimal max;
	
	public Range(BigDecimal min, BigDecimal max)
	{
		this.min = min;
		this.max = max;
	}
	
	public boolean withInRange(BigDecimal value)
	{
		return value.compareTo(min) >= 0 && value.compareTo(max) <= 0;
	}

	public BigDecimal getMin() {
		return min;
	}

	public BigDecimal getMax() {
		return max;
	}
}
