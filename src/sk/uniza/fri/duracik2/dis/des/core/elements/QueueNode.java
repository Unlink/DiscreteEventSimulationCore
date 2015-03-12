/*
 *  Copyright (c) 2015 Michal Ďuračík
 */
package sk.uniza.fri.duracik2.dis.des.core.elements;

import sk.uniza.fri.duracik2.dis.des.core.timming.ITime;

/**
 *
 * @author Unlink
 */
public class QueueNode {

	private final AEntity aEntity;

	private final ITime aEnteredTime;

	public QueueNode(AEntity paEntity, ITime paEnteredTime) {
		this.aEntity = paEntity;
		this.aEnteredTime = paEnteredTime;
	}

	public AEntity getEntity() {
		return aEntity;
	}

	public ITime getEnteredTime() {
		return aEnteredTime;
	}

}
