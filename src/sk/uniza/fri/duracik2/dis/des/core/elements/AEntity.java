/*
 *  Copyright (c) 2015 Michal Ďuračík
 */
package sk.uniza.fri.duracik2.dis.des.core.elements;

import sk.uniza.fri.duracik2.dis.des.core.timming.ITime;

/**
 *
 * @author Unlink
 */
public abstract class AEntity {

	private final ITime aCreationTime;

	public AEntity(ITime paCreationTime) {
		this.aCreationTime = paCreationTime;
	}

	/**
	 * Vráti čas vytvorenia entity
	 * @return 
	 */
	public ITime getCreationTime() {
		return aCreationTime;
	}

}
