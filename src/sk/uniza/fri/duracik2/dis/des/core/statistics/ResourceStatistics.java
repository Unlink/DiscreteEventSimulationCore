/*
 *  Copyright (c) 2015 Michal Ďuračík
 */
package sk.uniza.fri.duracik2.dis.des.core.statistics;

import sk.uniza.fri.duracik2.dis.des.core.elements.AEntity;

/**
 *
 * @author Unlink
 */
public class ResourceStatistics {

	private int aHandledEntities;

	private double aLastChanged;

	private final double aSimulationStart;

	private double aQueueArea;

	private int aLastSize;

	private double aWaitingTime;

	private int aWaitedEntities;

	private int aMaxQueue;

	public ResourceStatistics(double paStart) {
		aHandledEntities = 0;
		aLastChanged = paStart;
		aSimulationStart = paStart;
		aQueueArea = 0;
		aLastSize = 0;
		aWaitingTime = 0;
		aWaitedEntities = 0;
		aMaxQueue = 0;
	}

	public void handleQueueChange(int paSize, double paTime) {
		aMaxQueue = Math.max(aMaxQueue, paSize);
		double timeDiff = paTime - aLastChanged;
		aQueueArea += timeDiff * aLastSize;
		aLastChanged = paTime;
		aLastSize = paSize;
	}

	public void handleEntityServed(AEntity paEntity) {
		aHandledEntities++;
	}

	public void handleEntityWaitingEned(double paTime) {
		aWaitingTime += paTime;
		aWaitedEntities++;
	}

	/**
	 * Vráti priemernú dĺžku frontu
	 *
	 * @return
	 */
	public double getQueueSize() {
		return aQueueArea / (aLastChanged - aSimulationStart);
	}

	/**
	 * Vráti počet spravaných entít
	 *
	 * @return
	 */
	public int getHandledEntities() {
		return aHandledEntities;
	}

	/**
	 * Vráti priemernú dobu čakania vo fronte
	 *
	 * @return
	 */
	public double getWaintTime() {
		return aWaitingTime / aWaitedEntities;
	}

	/**
	 * Vráti maximálnu veľkosť frotu
	 *
	 * @return
	 */
	public int getMaxQueue() {
		return aMaxQueue;
	}
}
