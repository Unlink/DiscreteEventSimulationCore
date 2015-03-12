/*
 *  Copyright (c) 2015 Michal Ďuračík
 */
package sk.uniza.fri.duracik2.dis.des.core.elements;

/**
 *
 * @author Unlink
 */
public class QueueNode {

	private final AEntity aEntity;

	private final double aEnteredTime;

	public QueueNode(AEntity paEntity, double paEnteredTime) {
		this.aEntity = paEntity;
		this.aEnteredTime = paEnteredTime;
	}

	public AEntity getEntity() {
		return aEntity;
	}

	public double getEnteredTime() {
		return aEnteredTime;
	}

}
