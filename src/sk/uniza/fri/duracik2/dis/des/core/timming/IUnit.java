/*
 *  Copyright (c) 2015 Michal Ďuračík
 */
package sk.uniza.fri.duracik2.dis.des.core.timming;

/**
 *
 * @author Unlink
 */
public interface IUnit {

	/**
	 * Pomenovanie
	 *
	 * @return
	 */
	public String name();

	/**
	 * Skratka
	 *
	 * @return
	 */
	public String abbreviation();

	/**
	 * Vráti čas v základnej jednotke (sekunda)
	 *
	 * @param paTime
	 * @return
	 */
	public double convertToBase(ITime paTime);

	/**
	 * Načíta čas s danou jednotkou z času v základnej hodnote
	 *
	 * @param paTime
	 * @return
	 */
	public ITime convertFromBase(double paTime);

}
