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

	protected int aHandledEntities;

	protected double aLastChanged;

	protected final double aSimulationStart;

	protected double aQueueArea;

	protected int aLastSize;

	protected int aMaxQueue;
	
	private final SimpleStatistics aWaitStatistics;

	public ResourceStatistics(double paStart) {
		aHandledEntities = 0;
		aLastChanged = paStart;
		aSimulationStart = paStart;
		aQueueArea = 0;
		aLastSize = 0;
		aMaxQueue = 0;
		aWaitStatistics = new SimpleStatistics();
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
		aWaitStatistics.addToStatistics(paTime);
	}

	/**
	 * Vráti priemernú dĺžku frontu
	 *
	 * @return
	 */
	public double getQueueSize() {
		return aQueueArea  / (aLastChanged - aSimulationStart);
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
		return aWaitStatistics.getValue();
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
