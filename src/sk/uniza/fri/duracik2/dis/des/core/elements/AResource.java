/*
 *  Copyright (c) 2015 Michal Ďuračík
 */
package sk.uniza.fri.duracik2.dis.des.core.elements;

import java.util.LinkedList;
import java.util.Queue;
import sk.uniza.fri.duracik2.dis.des.core.ASimulation;
import sk.uniza.fri.duracik2.dis.des.core.statistics.ResourceStatistics;
import sk.uniza.fri.duracik2.dis.des.core.statistics.ResourceStatisticsClosure;

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
		this(paSimulation, new LinkedList<QueueNode>());
	}

	protected AResource(ASimulation paSimulation, Queue<QueueNode> paQueue) {
		aSimulation = paSimulation;
		aOperated = null;
		aQueue = paQueue;
		aStatistics = new ResourceStatisticsClosure(paSimulation, paSimulation.getTime());
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
	 * Ak je paCount nastavený na true, pripočíta danej entite čakanie 0
	 *
	 * @param paEntity
	 * @param paCount
	 */
	public void porocessEntity(AEntity paEntity, boolean paCount) {
		if (!isFree()) {
			throw new ResourceException("Zdroj je obsadený, nieje možné spracovať dalšiu entitu");
		}
		if (paCount) {
			aStatistics.handleEntityWaitingEned(0); //Aj tá čo nečakala, čakala 0
		}
		aOperated = paEntity;
	}
	
	/**
	 * Nastaví zdroju spracovávanú entitu Len v prípade, ak je zdroj voľný
	 * Pripočíta čakanie entity rovne 0
	 * @param paEntity 
	 */
	public void porocessEntity(AEntity paEntity) {
		porocessEntity(paEntity, true);
	}

	/**
	 * Dokončí spracovávanie entity a uvoľní zdroj
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
		aQueue.add(new QueueNode(paEntity, aSimulation.getTime()));
		aStatistics.handleQueueChange(aQueue.size(), aSimulation.getTime());
	}
	
	/**
	 * Vyberie entitu z fronty v aktualnom simulačnom čase
	 *
	 * @return AEntity
	 */
	private AEntity getFromQueue() {
		QueueNode node = aQueue.remove();
		aStatistics.handleQueueChange(aQueue.size(), aSimulation.getTime());
		aStatistics.handleEntityWaitingEned(aSimulation.getTime() - node.getEnteredTime());
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
