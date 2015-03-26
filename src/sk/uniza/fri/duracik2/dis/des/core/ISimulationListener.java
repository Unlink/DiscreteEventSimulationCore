/*
 *  Copyright (c) 2015 Michal Ďuračík
 */
package sk.uniza.fri.duracik2.dis.des.core;

/**
 *
 * @author Unlink
 */
public interface ISimulationListener {

	public void onWarmUpDone();

	public void onReplicationDone();

	public void onStateChanged();

	public void onEventDone(AEvent paEvent);

}
