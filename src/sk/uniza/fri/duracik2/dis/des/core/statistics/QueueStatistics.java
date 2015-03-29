/*
 *  Copyright (c) 2015 Michal Ďuračík
 */
package sk.uniza.fri.duracik2.dis.des.core.statistics;

/**
 *
 * @author Unlink
 */
public class QueueStatistics {
	
	protected double aLastChanged;

	protected double aSimulationStart;

	protected double aQueueArea;

	protected int aLastSize;

	protected int aMaxQueue;

	public QueueStatistics(double paStart) {
		aLastChanged = paStart;
		aSimulationStart = paStart;
		aQueueArea = 0;
		aLastSize = 0;
		aMaxQueue = 0;
	}
	
	public void handleQueueChange(int paSize, double paTime) {
		aMaxQueue = Math.max(aMaxQueue, paSize);
		double timeDiff = paTime - aLastChanged;
		aQueueArea += timeDiff * aLastSize;
		aLastChanged = paTime;
		aLastSize = paSize;
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
	 * Vráti priemernú dĺžku frontu s zohľadnením aktuálneho času
	 * 
	 * @param paTime Aktuálny čas
	 * @return
	 */
	public double getQueueSize(double paTime) {
		double diff = paTime - aLastChanged;
		return (aQueueArea + (diff * aLastSize)) / (paTime - aSimulationStart);
	}
	
	/**
	 * Vráti maximálnu veľkosť frotu
	 *
	 * @return
	 */
	public int getMaxQueue() {
		return aMaxQueue;
	}
	
	public void clear(double paTime) {
		aSimulationStart = paTime;
		aLastChanged = paTime;
		aQueueArea = 0;
		aMaxQueue = 0;
	}
	
}
