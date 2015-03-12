/*
 *  Copyright (c) 2015 Michal Ďuračík
 */
package sk.uniza.fri.duracik2.dis.des.core.timming;

/**
 *
 * @author Unlink
 */
public interface ITime extends Comparable<ITime> {

	/**
	 * Spočíta dva časy
	 * @param paTime
	 * @return
	 */
	public ITime plus(ITime paTime);

	/**
	 * Pripočíta zadaný počet časových jednotiek vzhľadom na aktuálnu jednotku
	 * @param paTime
	 * @return 
	 */
	public ITime plus(double paTime);
	
	/**
	 * Odpočíta dva časy
	 * @param paTime
	 * @return
	 */
	public ITime minus(ITime paTime);

	/**
	 * Odpočíta zadaný počet časových jednotiek vzhľadom na aktuálnu jednotku
	 * @param paTime
	 * @return 
	 */
	public ITime minus(double paTime);
	
	/**
	 * Vydelí daný čas zadanou hodnotou
	 * @param paValue
	 * @return 
	 */
	public ITime divide(double paValue);
	
	/**
	 * Vráti jednotku, v ktorej je meraný čas
	 * @return 
	 */
	public IUnit getUnit();
	
	/**
	 * Vráti hodnotu času v daných jednotkách
	 * @return 
	 */
	public double getTimeValue();
	
	/**
	 * Skonvertuje čas do základnej jednotky (sekundy)
	 * @return 
	 */
	public double toBaseTime();
	
	/**
	 * Skonvertuje čas do zadanej jednotky
	 * @param paUnit
	 * @return 
	 */
	public ITime convertTo(IUnit paUnit);

}
