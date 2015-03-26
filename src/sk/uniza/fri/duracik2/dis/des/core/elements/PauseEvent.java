/*
 *  Copyright (c) 2015 Michal Ďuračík
 */
package sk.uniza.fri.duracik2.dis.des.core.elements;

import sk.uniza.fri.duracik2.dis.des.core.ASimulation;
import sk.uniza.fri.duracik2.dis.des.core.ESimulationState;

/**
 *
 * @author Unlink
 * @internal
 */
public class PauseEvent extends ASystemEvent {

	public PauseEvent() {
		super(-1, 1000000);
	}

	@Override
	public void execute(ASimulation paSimulation) {
		synchronized (this) {
			if (paSimulation.getState() == ESimulationState.PAUSED) {
				try {
					this.wait();
				}
				catch (InterruptedException ex) {
				}
			}
		}
	}

}
