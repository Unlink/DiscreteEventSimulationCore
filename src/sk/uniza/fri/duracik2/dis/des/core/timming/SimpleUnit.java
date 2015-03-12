/*
 *  Copyright (c) 2015 Michal Ďuračík
 */
package sk.uniza.fri.duracik2.dis.des.core.timming;

/**
 *
 * @author Unlink
 */
public class SimpleUnit implements IUnit {

	public static final IUnit SECOND = new SimpleUnit("Sekunda", "sec", 1);

	public static final IUnit MINUTE = new SimpleUnit("Minúta", "min", 60);

	public static final IUnit HOUR = new SimpleUnit("Hodina", "hod", 60 * 60);

	public static final IUnit DAY = new SimpleUnit("Deň", "d", 60 * 60 * 24);

	private final String aName;

	private final String aAbbreviation;

	private final double aMultiplier;

	public SimpleUnit(String paName, String paAbbreviation, double paMultiplier) {
		this.aName = paName;
		this.aAbbreviation = paAbbreviation;
		this.aMultiplier = paMultiplier;
	}

	@Override
	public String name() {
		return aName;
	}

	@Override
	public String abbreviation() {
		return aAbbreviation;
	}

	@Override
	public double convertToBase(ITime paTime) {
		if (paTime.getUnit() != this) {
			throw new IllegalConversionException("Nemôžem skonvertovať hodnotu " + paTime.getUnit().abbreviation());
		}
		return paTime.getTimeValue() * aMultiplier;
	}

	@Override
	public ITime convertFromBase(double paTime) {
		return new Time(paTime * (1 / aMultiplier), this);
	}

}
