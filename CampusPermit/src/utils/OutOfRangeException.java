package utils;

public class OutOfRangeException extends Exception 
{
	public OutOfRangeException(String indexName, Range range)
	{
		super("Out of range for range " + indexName + " Please stay within the range of " + range.getMin() + " to " + range.getMax() + " inclusively");
	}
}
