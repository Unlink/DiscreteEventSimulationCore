/*
 *  Copyright (c) 2015 Michal Ďuračík
 */
package sk.uniza.fri.duracik2.dis.des.core.elements;

import sk.uniza.fri.duracik2.dis.des.core.ASimulation;

/**
 *
 * @author Unlink
 * @internal
 */
public class EndReplicationEvent extends ASystemEvent {

	public EndReplicationEvent(double paTime) {
		super(paTime);
	}

	@Override
	public void execute(ASimulation paSimulation) {
		paSimulation._onReplicationDone();
		appendTime(paSimulation.getReplicationLength());
		paSimulation.planEvent(this);
	}

}
