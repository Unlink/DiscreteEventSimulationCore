/*
 *  Copyright (c) 2015 Michal Ďuračík
 */
package sk.uniza.fri.duracik2.dis.des.core.elements;

/**
 *
 * @author Unlink
 */
public abstract class AEntity {

	private final double aCreationTime;

	public AEntity(double paCreationTime) {
		this.aCreationTime = paCreationTime;
	}

	/**
	 * Vráti čas vytvorenia entity
	 * @return 
	 */
	public double getCreationTime() {
		return aCreationTime;
	}

}
