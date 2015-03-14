/*
 *  Copyright (c) 2015 Michal Ďuračík
 */
package sk.uniza.fri.duracik2.dis.des.core;

/**
 *
 * @author Unlink
 */
public abstract class AEvent implements Comparable<AEvent> {

	private double aTime;

	private int aPriority;

	public AEvent(double paTime, int paPriority) {
		this.aTime = paTime;
		this.aPriority = paPriority;
	}

	public AEvent(double paTime) {
		this(paTime, 0);
	}

	public double getTime() {
		return aTime;
	}

	public void setTime(double time) {
		this.aTime = time;
	}
	
	/**
	 * Pripočíta čas k danemu eventu
	 * @param time 
	 */
	protected void appendTime(double time) {
		this.aTime += time;
	}

	@Override
	public int compareTo(AEvent paO) {
		int cmp1 = Double.compare(aTime, paO.aTime);
		if (cmp1 == 0) {
			return -Integer.compare(aPriority, paO.aPriority);
		}
		else {
			return cmp1;
		}
	}

	/**
	 * Spracuje udalosť
	 * @param paSimulation Simulačný kontext
	 */
	public abstract void execute(ASimulation paSimulation);

}
