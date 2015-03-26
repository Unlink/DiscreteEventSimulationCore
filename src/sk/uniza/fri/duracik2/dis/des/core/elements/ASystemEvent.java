/*
 *  Copyright (c) 2015 Michal Ďuračík
 */
package sk.uniza.fri.duracik2.dis.des.core.elements;

import sk.uniza.fri.duracik2.dis.des.core.AEvent;

/**
 *
 * @author Unlink
 */
public abstract class ASystemEvent extends AEvent {

	public ASystemEvent(double paTime, int paPriority) {
		super(paTime, paPriority);
	}

	public ASystemEvent(double paTime) {
		super(paTime);
	}

}
