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
public class EntityStatistics {
	
	private int aProcessed;
	
	private ITime aTime;

	public EntityStatistics() {
		aProcessed = 0;
		aTime = new Time(0);
	}
	
	/**
	 * Spracuje odchod entity zo systému
	 * @param paEntity
	 * @param paTime 
	 */
	public void process(AEntity paEntity, ITime paTime) {
		aProcessed++;
		aTime = aTime.plus(paTime.minus(paEntity.getCreationTime()));
	}
	
	/**
	 * Priemerný čas entity v systéme
	 * @return 
	 */
	public ITime getTimeInSystem() {
		return aTime.divide(aProcessed);
	}
	
	/**
	 * Vráti počet entit, ktoré odyšli zo systému
	 * @return 
	 */
	public int getNumberEntities() {
		return aProcessed;
	}
	
}
