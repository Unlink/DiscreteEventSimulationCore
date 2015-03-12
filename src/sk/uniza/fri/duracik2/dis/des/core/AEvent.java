/*
 *  Copyright (c) 2015 Michal Ďuračík
 */
package sk.uniza.fri.duracik2.dis.des.core;

import sk.uniza.fri.duracik2.dis.des.core.timming.ITime;

/**
 *
 * @author Unlink
 */
public abstract class AEvent implements Comparable<AEvent> {

	private ITime aTime;

	private int aPriority;

	public AEvent(ITime paTime, int paPriority) {
		this.aTime = paTime;
		this.aPriority = paPriority;
	}

	public AEvent(ITime paTime) {
		this(paTime, 0);
	}

	public ITime getTime() {
		return aTime;
	}

	public void setTime(ITime time) {
		this.aTime = time;
	}

	@Override
	public int compareTo(AEvent paO) {
		int cmp1 = aTime.compareTo(paO.aTime);
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
