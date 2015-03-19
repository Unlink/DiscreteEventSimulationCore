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
	
	private SimpleStatistics aStatistics;

	public EntityStatistics() {
		aStatistics = new SimpleStatistics();
	}

	/**
	 * Spracuje odchod entity zo systému
	 *
	 * @param paEntity
	 * @param paTime
	 */
	public void process(AEntity paEntity, double paTime) {
		aStatistics.addToStatistics(paTime - paEntity.getCreationTime());
	}

	/**
	 * Priemerný čas entity v systéme
	 *
	 * @return
	 */
	public double getTimeInSystem() {
		return aStatistics.getValue();
	}

	/**
	 * Vráti počet entit, ktoré odyšli zo systému
	 *
	 * @return
	 */
	public int getNumberEntities() {
		return aStatistics.getCount();
	}

}
