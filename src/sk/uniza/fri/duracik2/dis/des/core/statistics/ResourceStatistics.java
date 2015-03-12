/*
 *  Copyright (c) 2015 Michal Ďuračík
 */
package sk.uniza.fri.duracik2.dis.des.core.statistics;

import sk.uniza.fri.duracik2.dis.des.core.timming.ITime;
import sk.uniza.fri.duracik2.dis.des.core.elements.AEntity;
import sk.uniza.fri.duracik2.dis.des.core.timming.Time;

/**
 *
 * @author Unlink
 */
public class ResourceStatistics {

	private int aHandledEntities;
	
	private ITime aLastChanged;
	
	private final ITime aSimulationStart;
	
	private double aQueueArea;
	
	private int aLastSize;
	
	private ITime aWaitingTime;
	
	private int aWaitedEntities;
	
	private int aMaxQueue;

	public ResourceStatistics(ITime paStart) {
		aHandledEntities = 0;
		aLastChanged = paStart;
		aSimulationStart = paStart;
		aQueueArea = 0;
		aLastSize = 0;
		aWaitingTime = new Time(0);
		aWaitedEntities = 0;
		aMaxQueue = 0;
	}
	
	public void handleQueueChange(int paSize, ITime paTime) {
		aMaxQueue = Math.max(aMaxQueue, paSize);
		double timeDiff = paTime.minus(aLastChanged).toBaseTime();
		aQueueArea += timeDiff*aLastSize;
		aLastChanged = paTime;
		aLastSize = paSize;
	}
	
	public void handleEntityServed(AEntity paEntity) {
		aHandledEntities++;
	}
	
	public void handleEntityWaitingEned(ITime paTime) {
		aWaitingTime = aWaitingTime.plus(paTime);
		aWaitedEntities++;
	}
	
	/**
	 * Vráti priemernú dĺžku frontu
	 * @return 
	 */
	public double getQueueSize() {
		return aQueueArea / (aLastChanged.minus(aSimulationStart).toBaseTime());
	}

	/**
	 * Vráti počet spravaných entít
	 * @return 
	 */
	public int getHandledEntities() {
		return aHandledEntities;
	}
	
	/**
	 * Vráti priemernú dobu čakania vo fronte
	 * @return 
	 */
	public ITime getWaintTime() {
		return aWaitingTime.divide(aWaitedEntities);
	}
	
	/**
	 * Vráti maximálnu veľkosť frotu
	 * @return 
	 */
	public int getMaxQueue() {
		return aMaxQueue;
	}
}
