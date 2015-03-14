/*
 *  Copyright (c) 2015 Michal Ďuračík
 */
package sk.uniza.fri.duracik2.dis.des.core.statistics;

import sk.uniza.fri.duracik2.dis.des.core.ASimulation;

/**
 *
 * @author Unlink
 */
public class ResourceStatisticsClosure extends ResourceStatistics {

	private final ASimulation aSimulation;

	public ResourceStatisticsClosure(ASimulation paSimulation, double paStart) {
		super(paStart);
		this.aSimulation = paSimulation;
	}
	
	@Override
	public double getQueueSize() {
		double diff = aSimulation.getTime() - aLastChanged;
		return (aQueueArea + (diff * aLastSize)) / (aSimulation.getTime() - aSimulationStart);
	}
	
}
