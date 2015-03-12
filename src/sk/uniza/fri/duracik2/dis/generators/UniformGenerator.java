/*
 *  Copyright (c) 2015 Michal Ďuračík
 */
package sk.uniza.fri.duracik2.dis.generators;

/**
 *
 * @author Unlink
 */
public class UniformGenerator extends AGenerator {

	private final double aMin;

	private final double aMax;
	
	public UniformGenerator(double paMin, double paMax) {
		if (paMin >= paMax) {
			throw new IllegalArgumentException("Maximálna hodnota musí byť väčšia ako minimálna, zadané min = '" + paMin + "', max = '" + paMax + "'");
		}
		this.aMin = paMin;
		this.aMax = paMax;
	}

	public UniformGenerator(double paMin, double paMax, long paSeed) {
		super(paSeed);
		if (paMin >= paMax) {
			throw new IllegalArgumentException("Maximálna hodnota musí byť väčšia ako minimálna, zadané min = '" + paMin + "', max = '" + paMax + "'");
		}
		this.aMin = paMin;
		this.aMax = paMax;
	}

	public UniformGenerator(double paMax) {
		this(0, paMax);
	}

	public UniformGenerator(double paMax, long paSeed) {
		this(0, paMax, paSeed);
	}

	@Override
	public double next() {
		return aMin + (aRandom.nextDouble() * (aMax - aMin));
	}

	@Override
	public int nextInt() {
		return (int) (aMin + (aRandom.nextInt((int) aMax)));
	}

}
