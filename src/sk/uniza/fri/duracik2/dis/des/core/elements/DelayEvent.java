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
public class DelayEvent extends ASystemEvent {

	private long aDelay;
	private double aSkip;

	public DelayEvent(long paDelay, double paSkip, double paTime) {
		super(paTime);
		this.aDelay = paDelay;
		this.aSkip = paSkip;
	}

	@Override
	public void execute(ASimulation paSimulation) {
		try {
			Thread.sleep(aDelay);
		}
		catch (InterruptedException ex) {
		}
		paSimulation._onTimeChanged();
		if (paSimulation.hasDelay(this)) {
			appendTime(aSkip);
			paSimulation.planEvent(this);
		}
	}

	public void setDelay(long paDelay) {
		this.aDelay = paDelay;
	}

	public void setSkip(double paSkip) {
		this.aSkip = paSkip;
	}

}
