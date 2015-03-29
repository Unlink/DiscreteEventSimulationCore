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

	private final SimpleStatistics aWaitStatistics;
	
	private final QueueStatistics aQueueStatistics;

	public ResourceStatistics(double paStart) {
		aHandledEntities = 0;
		aWaitStatistics = new SimpleStatistics();
		aQueueStatistics = new QueueStatistics(paStart);
	}

	public void handleQueueChange(int paSize, double paTime) {
		aQueueStatistics.handleQueueChange(paSize, paTime);
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
		return aQueueStatistics.getQueueSize();
	}
	
	/**
	 * Vráti priemernú dĺžku frontu s zohľadnením aktuálneho času
	 * 
	 * @param paTime Aktuálny čas
	 * @return
	 */
	public double getQueueSize(double paTime) {
		return aQueueStatistics.getQueueSize(paTime);
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
	public double getWaitTime() {
		return aWaitStatistics.getValue();
	}

	/**
	 * Vráti maximálnu veľkosť frotu
	 *
	 * @return
	 */
	public int getMaxQueue() {
		return aQueueStatistics.getMaxQueue();
	}

	public void clear(double paTime) {
		aWaitStatistics.clear();
		aHandledEntities = 0;
		aQueueStatistics.clear(paTime);
	}
	
}
