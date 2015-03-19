/*
 *  Copyright (c) 2015 Michal Ďuračík
 */
package sk.uniza.fri.duracik2.dis.des.core.statistics;

/**
 *
 * @author Unlink
 */
public class WeightedStatistics {
	
	private double aSum;
	
	private double aCount;

	public WeightedStatistics() {
		this.aSum = 0;
		this.aCount = 0;
	}
	
	public void addToStatistics(double paValue, double paWeight) {
		aSum += paValue*paWeight;
		aCount += paWeight;
	}
	
	public double getValue() {
		return aSum / aCount;
	}
	
}
