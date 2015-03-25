/*
 *  Copyright (c) 2015 Michal Ďuračík
 */
package sk.uniza.fri.duracik2.dis.des.core.elements;

import java.util.logging.Level;
import java.util.logging.Logger;
import sk.uniza.fri.duracik2.dis.des.core.AEvent;
import sk.uniza.fri.duracik2.dis.des.core.ASimulation;

/**
 *
 * @author Unlink
 */
public class DelayEvent extends AEvent {
	
	private long aDelay;
	private double aNext;

	public DelayEvent(long paDelay, double paNext, double paTime) {
		super(paTime);
		this.aDelay = paDelay;
		this.aNext = paNext;
	}

	@Override
	public void execute(ASimulation paSimulation) {
		try {
			Thread.sleep(aDelay);
		}
		catch (InterruptedException ex) {
		}
		if (paSimulation.hasDelay()) {
			appendTime(aNext);
			paSimulation.planEvent(this);
		}
	}

	public void setDelay(long paDelay) {
		this.aDelay = paDelay;
	}

	public void setNext(double paNext) {
		this.aNext = paNext;
	}
	
}
