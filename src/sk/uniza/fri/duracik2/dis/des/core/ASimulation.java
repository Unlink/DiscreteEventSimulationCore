/*
 *  Copyright (c) 2015 Michal Ďuračík
 */
package sk.uniza.fri.duracik2.dis.des.core;

import java.util.HashMap;
import java.util.PriorityQueue;
import sk.uniza.fri.duracik2.dis.des.core.elements.AEntity;
import sk.uniza.fri.duracik2.dis.des.core.elements.DelayEvent;
import sk.uniza.fri.duracik2.dis.des.core.statistics.EntityStatistics;
import sk.uniza.fri.duracik2.dis.generators.IGenerator;

/**
 *
 * @author Unlink
 */
public abstract class ASimulation {

	private volatile double aSimulationTime;

	private final PriorityQueue<AEvent> aEventQueue;

	private final EntityStatistics aStatistics;

	protected final HashMap<Object, IGenerator> aGenerators;
	
	private volatile DelayEvent aDelay;

	public ASimulation(double paBegin) {
		aSimulationTime = paBegin;
		aEventQueue = new PriorityQueue<>(5);
		aStatistics = new EntityStatistics();
		aGenerators = new HashMap<>();
		aDelay = null;
	}

	/**
	 * Naplánuje udalosť
	 *
	 * @param paEvent
	 */
	public void planEvent(AEvent paEvent) {
		aEventQueue.add(paEvent);
	}

	/**
	 * Spustí simuláciu na požadovaný časime
	 *
	 * @param paEnd
	 */
	public void runSimulation(double paEnd) {
		while (aEventQueue.size() > 0) {
			AEvent event;
			synchronized (this) {
				event = aEventQueue.poll();
				if (Double.compare(event.getTime(), paEnd) > 0) {
					break;
				}
				aSimulationTime = event.getTime();
			}
			event.execute(this);
		}
	}

	/**
	 * Vráti aktuálny simulačný čas
	 *
	 * @return
	 */
	public double getTime() {
		return aSimulationTime;
	}

	/**
	 * Zruší entitu zo systému (pre štatistické účely)
	 *
	 * @param paEntity
	 */
	public void destroyEntity(AEntity paEntity) {
		aStatistics.process(paEntity, aSimulationTime);
	}

	/**
	 * Vráti generátor pre daný typ eventu
	 *
	 * @param paKey
	 *
	 * @return
	 */
	public IGenerator getGenerator(Object paKey) {
		return aGenerators.get(paKey);
	}

	/**
	 * Vráti entity štatistiku
	 *
	 * @return
	 */
	public EntityStatistics getStatistics() {
		return aStatistics;
	}
	
	public void setDelay(long paDelay, double paRepeat) {
		synchronized (this) {
			if (aDelay == null){
				aDelay = new DelayEvent(paDelay, paRepeat, getTime()+paRepeat);
			}
			else {
				aDelay.setDelay(paDelay);
				aDelay.setNext(paRepeat);
			}
		}
	}
	
	public void removeDelay() {
		aDelay = null;
	}
	
	public boolean hasDelay() {
		return aDelay != null;
	}

}
