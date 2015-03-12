/*
 *  Copyright (c) 2015 Michal Ďuračík
 */
package sk.uniza.fri.duracik2.dis.des.core.timming;

/**
 *
 * @author Unlink
 */
public class Time implements ITime {

	private final double aTime;

	private final IUnit aUnit;
	
	public Time(double paTime, IUnit paUnit) {
		this.aTime = paTime;
		this.aUnit = paUnit;
	}

	/**
	 * Vytvorí novú časovú jednotku v sekundách
	 *
	 * @see SimpleUnit.SECOND
	 * @param paTime
	 */
	public Time(double paTime) {
		this(paTime, SimpleUnit.SECOND);
	}

	@Override
	public double getTimeValue() {
		return aTime;
	}

	@Override
	public IUnit getUnit() {
		return aUnit;
	}

	@Override
	public ITime plus(ITime paTime) {
		//Ak majú rovnaku jednotku, netreba konvertovať
		if (getUnit() == paTime.getUnit()) {
			return new Time(getTimeValue() + paTime.getTimeValue(), aUnit);
		}
		else {
			return aUnit.convertFromBase(toBaseTime() + paTime.toBaseTime());
		}
	}

	@Override
	public ITime plus(double paTime) {
		return new Time(getTimeValue() + paTime, aUnit);
	}

	@Override
	public ITime minus(ITime paTime) {
		//Ak majú rovnaku jednotku, netreba konvertovať
		if (getUnit() == paTime.getUnit()) {
			return new Time(getTimeValue() - paTime.getTimeValue(), aUnit);
		}
		else {
			return aUnit.convertFromBase(toBaseTime() - paTime.toBaseTime());
		}
	}

	@Override
	public ITime minus(double paTime) {
		return new Time(getTimeValue() - paTime, aUnit);
	}

	@Override
	public ITime divide(double paValue) {
		return new Time(aTime / paValue, aUnit);
	}

	@Override
	public int compareTo(ITime paTime) {
		//Ak majú rovnaku jednotku, netreba konvertovať
		if (getUnit() == paTime.getUnit()) {
			return Double.compare(aTime, paTime.getTimeValue());
		}
		else {
			return Double.compare(toBaseTime(), paTime.toBaseTime());
		}
	}

	@Override
	public double toBaseTime() {
		return aUnit.convertToBase(this);
	}

	@Override
	public String toString() {
		return String.format("%.6f", aTime) + aUnit.abbreviation();
	}

	@Override
	public ITime convertTo(IUnit paUnit) {
		return paUnit.convertFromBase(toBaseTime());
	}
	
}
