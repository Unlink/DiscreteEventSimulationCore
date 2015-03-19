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

	public DelayEvent(double paTime) {
		super(paTime);
	}

	@Override
	public void execute(ASimulation paSimulation) {
		appendTime(paSimulation.getSpeed()*0.2);
		try {
			Thread.sleep(200);
		}
		catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
	
}
