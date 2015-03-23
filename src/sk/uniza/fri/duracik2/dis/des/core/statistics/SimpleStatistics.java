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
	
	private double aMin;
	
	private double aMax;

	public SimpleStatistics() {
		clear();
	}

	public void addToStatistics(double paValue) {
		aCount++;
		aSum += paValue;
		aSquareSum += paValue * paValue;
		aMax = Double.max(aMax, paValue);
		aMin = Double.min(aMin, paValue);
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
	
	public double getLow(double paLevel) {
		double a = 1 - ((1 - paLevel)/2);
		return getValue() - StatUtil.getInvCDF(a, true)*(getDeviation()/Math.sqrt(aCount-1));
	}
	
	public double getHigh(double paLevel) {
		double a = 1 - ((1 - paLevel)/2);
		return getValue() + StatUtil.getInvCDF(a, true)*(getDeviation()/Math.sqrt(aCount-1));
	}

	public final void clear() {
		aSum = 0;
		aSquareSum = 0;
		aCount = 0;
		aMin = Double.MAX_VALUE;
		aMax = Double.MIN_VALUE;
	}

	public double getMin() {
		return aMin;
	}

	public double getMax() {
		return aMax;
	}
}
