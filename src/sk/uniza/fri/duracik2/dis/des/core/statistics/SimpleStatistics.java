/*
 *  Copyright (c) 2015 Michal Ďuračík
 */
package sk.uniza.fri.duracik2.dis.des.core.statistics;

/**
 *
 * @author Unlink
 */
public class SimpleStatistics {

	private double aSum;

	private double aSquareSum;

	private int aCount;

	public SimpleStatistics() {
		clear();
	}

	public void addToStatistics(double paValue) {
		aCount++;
		aSum += paValue;
		aSquareSum += paValue * paValue;
	}
	
	public int getCount() {
		return aCount;
	}
	
	public double getValue() {
		return aSum / aCount;
	}
	
	public double getDeviation() {
		return (aSquareSum/aCount) - Math.pow((aSum/aCount), 2); 
	}
	
	public double getMin(double paLevel) {
		double a = 1 - ((1 - paLevel)/2);
		return getValue() - StatUtil.getInvCDF(a, true)*(getDeviation()/Math.sqrt(aCount-1));
	}
	
	public double getMax(double paLevel) {
		double a = 1 - ((1 - paLevel)/2);
		return getValue() - StatUtil.getInvCDF(a, true)*(getDeviation()/Math.sqrt(aCount-1));
	}

	public final void clear() {
		aSum = 0;
		aSquareSum = 0;
		aCount = 0;
	}
}
