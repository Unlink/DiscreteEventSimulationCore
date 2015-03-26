/*
 *  Copyright (c) 2015 Michal Ďuračík
 */
package sk.uniza.fri.duracik2.dis.des.core.elements;

import java.util.Queue;
import sk.uniza.fri.duracik2.dis.des.core.ASimulation;

/**
 *
 * @author Unlink
 * @deprecated
 */
public class NamedResource extends AResource {

	private final String aName;

	public NamedResource(ASimulation paSimulation, Queue<QueueNode> paQueue, String paName) {
		super(paSimulation, paQueue);
		aName = paName;
	}

	public NamedResource(ASimulation paSimulation, String paName) {
		super(paSimulation);
		aName = paName;
	}

	public String getName() {
		return aName;
	}

}
