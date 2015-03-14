/*
 *  Copyright (c) 2015 Michal Ďuračík
 */
package sk.uniza.fri.duracik2.dis.des.core.statistics;

import sk.uniza.fri.duracik2.dis.des.core.elements.AEntity;

/**
 *
 * @author Unlink
 */
public class EntityStatistics {

	protected int aProcessed;

	protected double aTime;

	public EntityStatistics() {
		aProcessed = 0;
		aTime = 0;
	}

	/**
	 * Spracuje odchod entity zo systému
	 *
	 * @param paEntity
	 * @param paTime
	 */
	public void process(AEntity paEntity, double paTime) {
		aProcessed++;
		aTime += paTime - paEntity.getCreationTime();
	}

	/**
	 * Priemerný čas entity v systéme
	 *
	 * @return
	 */
	public double getTimeInSystem() {
		return aTime / aProcessed;
	}

	/**
	 * Vráti počet entit, ktoré odyšli zo systému
	 *
	 * @return
	 */
	public int getNumberEntities() {
		return aProcessed;
	}

}
