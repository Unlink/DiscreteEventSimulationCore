/*
 *  Copyright (c) 2015 Michal Ďuračík
 */
package sk.uniza.fri.duracik2.dis.des.core;

import sk.uniza.fri.duracik2.dis.des.core.timming.ITime;
import sk.uniza.fri.duracik2.dis.des.core.timming.Time;
import java.util.HashMap;
import java.util.PriorityQueue;
import sk.uniza.fri.duracik2.dis.des.core.elements.AEntity;
import sk.uniza.fri.duracik2.dis.des.core.statistics.EntityStatistics;
import sk.uniza.fri.duracik2.dis.generators.IGenerator;

/**
 *
 * @author Unlink
 */
public abstract class ASimulation {

	private ITime aSimulationTime;

	private final PriorityQueue<AEvent> aEventQueue;
	
	private final EntityStatistics aStatistics;
	
	protected final HashMap<Class, IGenerator> aGenerators;

	public ASimulation(ITime paBegin) {
		aSimulationTime = paBegin;
		aEventQueue = new PriorityQueue<>(5);
		aStatistics = new EntityStatistics();
		aGenerators = new HashMap<>();
	}

	/**
	 * Naplánuje udalosť
	 * @param paEvent 
	 */
	public void planEvent(AEvent paEvent) {
		aEventQueue.add(paEvent);
	}

	/**
	 * Spustí simuláciu na požadovaný časime 
	 * @param paEnd
	 */
	public void runSimulation(ITime paEnd) {
		while (aEventQueue.size() > 0) {
			AEvent event = aEventQueue.poll();
			if (event.getTime().compareTo(paEnd) > 0) {
				break;
			}
			aSimulationTime = event.getTime();
			event.execute(this);
		}
	}

	/**
	 * Vráti aktuálny simulačný čas
	 * @return 
	 */
	public ITime getSimulationTime() {
		return aSimulationTime;
	}
	
	/**
	 * Zruší entitu zo systému (pre štatistické účely)
	 * @param paEntity 
	 */
	public void destroyEntity(AEntity paEntity) {
		aStatistics.process(paEntity, aSimulationTime);
	}
	
	/**
	 * Vráti generátor pre daný typ eventu
	 * @param paEventClass Class alebo instancia
	 * @return 
	 */
	public IGenerator getGenerator(Object paEventClass) {
		if (paEventClass instanceof Class) {
			return aGenerators.get((Class) paEventClass);
		}
		else {
			return aGenerators.get(paEventClass.getClass());
		}
	}

	/**
	 * Vráti entity štatistiku
	 * @return 
	 */
	public EntityStatistics getStatistics() {
		return aStatistics;
	}
	
}
