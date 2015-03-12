/*
 *  Copyright (c) 2015 Michal Ďuračík
 */
package sk.uniza.fri.duracik2.dis.des.core.elements;

import java.util.LinkedList;
import java.util.Queue;
import sk.uniza.fri.duracik2.dis.des.core.ASimulation;
import sk.uniza.fri.duracik2.dis.des.core.statistics.ResourceStatistics;

/**
 *
 * @author Unlink
 */
public abstract class AResource {

	private AEntity aOperated;

	private final Queue<QueueNode> aQueue;

	private final ASimulation aSimulation;

	protected ResourceStatistics aStatistics;

	public AResource(ASimulation paSimulation) {
		aSimulation = paSimulation;
		aOperated = null;
		aQueue = new LinkedList<>();
		aStatistics = new ResourceStatistics(paSimulation.getSimulationTime());
	}

	protected AResource(ASimulation paSimulation, Queue<QueueNode> paQueue) {
		aSimulation = paSimulation;
		aOperated = null;
		aQueue = paQueue;
		aStatistics = new ResourceStatistics(paSimulation.getSimulationTime());
	}

	/**
	 * Vráti dostupnosť zdroja
	 *
	 * @return
	 */
	public boolean isFree() {
		return aOperated == null;
	}

	/**
	 * Vráti aktuálne spracovávanú entitu
	 *
	 * @return
	 */
	public AEntity getOperated() {
		return aOperated;
	}

	/**
	 * Nastaví zdroju spracovávanú entitu Len v prípade, ak je zdroj voľný
	 *
	 * @param paEntity
	 */
	public void porocessEntity(AEntity paEntity) {
		if (!isFree()) {
			throw new ResourceException("Zdroj je obsadený, nieje možné spracovať dalšiu entitu");
		}
		aStatistics.handleEntityWaitingEned(0); //Aj tá čo nečakala, čakala 0
		aOperated = paEntity;
	}

	/**
	 * Dokončí spracovávanie entity
	 *
	 * @return
	 */
	public AEntity doneEntity() {
		if (aOperated == null) {
			throw new ResourceException("Zdroj nieje obsadený");
		}
		aStatistics.handleEntityServed(aOperated); //Entita bola spracovaná
		AEntity entity = aOperated;
		aOperated = null;
		return entity;
	}

	/**
	 * Začne spracovávať entitu z frontu
	 *
	 * @return
	 */
	public AEntity porocessNext() {
		if (aOperated != null) {
			throw new ResourceException("Zdroj je obsadený, nieje možné spracovať dalšiu entitu");
		}
		aOperated = getFromQueue();
		return aOperated;
	}

	/**
	 * Prida entitu do fronty v aktualnom simulačnom čase
	 *
	 * @param paEntity
	 */
	public void addToQueue(AEntity paEntity) {
		aQueue.add(new QueueNode(paEntity, aSimulation.getSimulationTime()));
		aStatistics.handleQueueChange(aQueue.size(), aSimulation.getSimulationTime());
	}

	/**
	 * Vyberie entitu z fronty v aktualnom simulačnom čase
	 *
	 * @return
	 */
	private AEntity getFromQueue() {
		QueueNode node = aQueue.remove();
		aStatistics.handleQueueChange(aQueue.size(), aSimulation.getSimulationTime());
		aStatistics.handleEntityWaitingEned(aSimulation.getSimulationTime() - node.getEnteredTime());
		return node.getEntity();
	}

	/**
	 * Vráti dĺžku frontu
	 *
	 * @return
	 */
	public int getQueueSize() {
		return aQueue.size();
	}

	protected ASimulation getSimulation() {
		return aSimulation;
	}

	/**
	 * Vráti štatistiku pre zdroj
	 *
	 * @return
	 */
	public ResourceStatistics getStatistics() {
		return aStatistics;
	}
}
