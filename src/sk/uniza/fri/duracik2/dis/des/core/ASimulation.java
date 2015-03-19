/*
 *  Copyright (c) 2015 Michal Ďuračík
 */
package sk.uniza.fri.duracik2.dis.des.core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
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

	private double aSimulationTime;

	private final PriorityQueue<AEvent> aEventQueue;

	private final EntityStatistics aStatistics;

	protected final HashMap<Object, IGenerator> aGenerators;
	
	private double aSimulationSpeed;
	
	private boolean aPlanned;
	
	private HashMap<Class, List<ISimulationListener>> aListeners;

	public ASimulation(double paBegin) {
		aSimulationTime = paBegin;
		aEventQueue = new PriorityQueue<>(5);
		aStatistics = new EntityStatistics();
		aGenerators = new HashMap<>();
		aSimulationSpeed = Double.POSITIVE_INFINITY;
		aPlanned = false;
		aListeners = new HashMap<>();
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
			if (aListeners.containsKey(event.getClass())) {
				for (ISimulationListener l : aListeners.get(event.getClass())) {
					l.handleStateChange(this, event);
				}
			}
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
	
	/**
	 * Nastaví rýchlosť simulácie
	 * @param paValue 
	 */
	public void changeSpeed(double paValue) {
		synchronized (this) {
			if (paValue < Double.MAX_VALUE && !aPlanned) {
				planEvent(new DelayEvent(aSimulationTime));
			}
			aSimulationSpeed = paValue;
		}
	}
	
	public double getSpeed() {
		return aSimulationSpeed;
	}
	
	/**
	 * Pridá listener na eventy
	 * @param paListener Listener
	 * @param paEvents Pole Class Eventov, na ktoré bude listener počúvať
	 */
	public void addSimulationListener(ISimulationListener paListener, Class... paEvents) {
		for (Class e : paEvents) {
			if (!aListeners.containsKey(e)) {
				aListeners.put(e, new LinkedList<>());
			}
			aListeners.get(e).add(paListener);
		}
	}

}
