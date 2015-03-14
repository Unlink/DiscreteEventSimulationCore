/*
 *  Copyright (c) 2015 Michal Ďuračík
 */
package sk.uniza.fri.duracik2.dis.des.core;

import sk.uniza.fri.duracik2.dis.des.core.elements.AResource;

/**
 *
 * @author Unlink
 */
public abstract class AResourceEvent extends AEvent {

	private AResource aResource;

	public AResourceEvent(AResource paResource, double paTime, int paPriority) {
		super(paTime, paPriority);
		this.aResource = paResource;
	}

	public AResourceEvent(AResource paResource, double paTime) {
		super(paTime);
		this.aResource = paResource;
	}

	protected AResource getResource() {
		return aResource;
	}

	protected void setResource(AResource paResource) {
		this.aResource = paResource;
	}
	
	
	
}
