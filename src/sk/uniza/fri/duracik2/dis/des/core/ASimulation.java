/*
 *  Copyright (c) 2015 Michal Ďuračík
 */
package sk.uniza.fri.duracik2.dis.des.core;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import sk.uniza.fri.duracik2.dis.des.core.elements.*;
import sk.uniza.fri.duracik2.dis.des.core.statistics.EntityStatistics;
import sk.uniza.fri.duracik2.dis.generators.IGenerator;

/**
 *
 * @author Unlink
 */
public abstract class ASimulation {

	private volatile double aSimulationTime;

	private final double aWarmUp;

	private final double aReplicationLength;

	private int aReplicationNum;

	private final PriorityQueue<AEvent> aEventQueue;

	private final EntityStatistics aStatistics;

	protected final HashMap<Object, IGenerator> aGenerators;

	private DelayEvent aDelay;

	private volatile ESimulationState aState;

	private final List<ISimulationListener> aListeners;

	public ASimulation(double paWarmUp, double paReplicationLength) {
		aSimulationTime = 0;
		aWarmUp = paWarmUp;
		aReplicationLength = paReplicationLength;
		aReplicationNum = 0;
		aEventQueue = new PriorityQueue<>(5);
		aStatistics = new EntityStatistics();
		aGenerators = new HashMap<>();
		aListeners = new LinkedList<>();
		aDelay = null;
		aState = ESimulationState.INITIALIZED;
		planEvent(new EndWarmUpEvent(aWarmUp));
	}

	/**
	 * Naplánuje udalosť
	 *
	 * @param paEvent
	 */
	public final void planEvent(AEvent paEvent) {
		if (paEvent.getTime() < aSimulationTime) {
			throw new IllegalArgumentException("Plánovanie eventu do minulosti");
		}
		aEventQueue.add(paEvent);
	}

	/**
	 * Spustí simuláciu na požadovaný počet replikacii
	 *
	 * @param paReplikaci
	 */
	public void runSimulation(double paReplikaci) {
		if (aState != ESimulationState.INITIALIZED) {
			throw new IllegalStateException("Simuláciu nieje možné spustiť lebo je v stave " + aState);
		}
		setState(ESimulationState.WARMING);
		double paSimulationEnd = aWarmUp + aReplicationLength * paReplikaci;
		while (aEventQueue.size() > 0 && aState != ESimulationState.END) {
			AEvent event;
			synchronized (this) {
				while (aState == ESimulationState.PAUSED) {
					try {
						wait();
					}
					catch (InterruptedException ex) {
						ex.printStackTrace();
					}
				}
				event = aEventQueue.poll();
				if (Double.compare(event.getTime(), paSimulationEnd) > 0) {
					break;
				}
				aSimulationTime = event.getTime();
			}
			event.execute(this);
			if (aState == ESimulationState.RUNNING && !(event instanceof ASystemEvent)) {
				for (ISimulationListener l : aListeners) {
					l.onEventDone(event);
				}
			}
		}
		setState(ESimulationState.END);
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
	 * Nastaví delay event
	 *
	 * @param paDelay dĺžka uspania
	 * @param paSkip opakovanie
	 */
	public void setDelay(long paDelay, double paSkip) {
		synchronized (this) {
			if (aDelay == null) {
				aDelay = new DelayEvent(paDelay, paSkip, getTime() + paSkip);
				planEvent(aDelay);
			}
			else {
				aDelay.setDelay(paDelay);
				aDelay.setSkip(paSkip);
			}
		}
	}

	public void removeDelay() {
		aDelay = null;
	}

	public boolean hasDelay() {
		return aDelay != null;
	}
	
	public boolean hasDelay(DelayEvent paDelay) {
		return aDelay == paDelay;
	}

	public ESimulationState getState() {
		return aState;
	}

	public void stop() {
		setState(ESimulationState.END);
	}

	public void pause() {
		synchronized (this) {
			if (aState == ESimulationState.RUNNING) {
				setState(ESimulationState.PAUSED);
			}
		}
	}

	public void resume() {
		synchronized (this) {
			if (aState == ESimulationState.PAUSED) {
				setState(ESimulationState.RUNNING);
				this.notify();
			}
		}
	}

	/**
	 * @internal
	 */
	public void _onWarmupDone() {
		onWarmUpDone();
		for (ISimulationListener l : aListeners) {
			l.onWarmUpDone();
		}
		aReplicationNum = 1;
		setState(ESimulationState.RUNNING);
	}

	protected void onWarmUpDone() {
	}

	/**
	 * @internal
	 */
	public void _onReplicationDone() {
		onReplicationDone();
		for (ISimulationListener l : aListeners) {
			l.onReplicationDone();
		}
		aReplicationNum++;
	}

	protected void onReplicationDone() {
	}

	/**
	 * @internal
	 */
	public void _onTimeChanged() {
		for (ISimulationListener l : aListeners) {
			l.onTimeChanged();
		}
	}

	public double getWarmUp() {
		return aWarmUp;
	}

	public double getReplicationLength() {
		return aReplicationLength;
	}

	public int getReplicationNum() {
		return aReplicationNum;
	}
	
	public double getRelativeTime() {
		return aSimulationTime - aWarmUp - (aReplicationNum-1)*aReplicationLength;
	}

	private void setState(ESimulationState paState) {
		aState = paState;
		for (ISimulationListener l : aListeners) {
			l.onStateChanged();
		}
	}

	public void addSimulationListener(ISimulationListener paListener) {
		aListeners.add(paListener);
	}

	public void removeSimulationListener(ISimulationListener paListener) {
		aListeners.remove(paListener);
	}
}
