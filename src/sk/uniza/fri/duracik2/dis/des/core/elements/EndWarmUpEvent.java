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
public class EndWarmUpEvent extends ASystemEvent {

	public EndWarmUpEvent(double paTime) {
		super(paTime);
	}

	@Override
	public void execute(ASimulation paSimulation) {
		paSimulation._onWarmupDone();
		paSimulation.planEvent(new EndReplicationEvent(getTime() + paSimulation.getReplicationLength()));
	}

}
