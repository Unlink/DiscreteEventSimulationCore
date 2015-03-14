/*
 *  Copyright (c) 2015 Michal Ďuračík
 */
package sk.uniza.fri.duracik2.dis.des.core;

import sk.uniza.fri.duracik2.dis.des.core.elements.AEntity;

/**
 *
 * @author Unlink
 */
public abstract class AEntityEvent extends AEvent {

	private AEntity aEntity;

	public AEntityEvent(AEntity paEntity, double paTime, int paPriority) {
		super(paTime, paPriority);
		this.aEntity = paEntity;
	}

	public AEntityEvent(AEntity paEntity, double paTime) {
		super(paTime);
		this.aEntity = paEntity;
	}

	/**
	 * Vráti entitu asociovanú s týmto eventom
	 *
	 * @return
	 */
	protected AEntity getEntity() {
		return aEntity;
	}

	/**
	 * Nastaví entitu eventu
	 *
	 * @param paEntity
	 */
	protected void setEntity(AEntity paEntity) {
		this.aEntity = paEntity;
	}

}
