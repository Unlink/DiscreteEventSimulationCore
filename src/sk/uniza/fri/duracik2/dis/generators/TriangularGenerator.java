/*
 *  Copyright (c) 2015 Michal Ďuračík
 */
package sk.uniza.fri.duracik2.dis.generators;

/**
 *
 * @author Unlink
 */
public class TriangularGenerator extends AGenerator {

	private final double aA;
	private final double aB;
	private final double aC;

	/**
	 * @param paA Minimim
	 * @param paB Maximim
	 * @param paC Most probable
	 */
	public TriangularGenerator(double paA, double paB, double paC) {
		if (!(paA < paC && paC < paB)) {
			throw new IllegalArgumentException("Nesprávna kombinácia parametrov, požadované a < c < b");
		}
		this.aA = paA;
		this.aB = paB;
		this.aC = paC;
	}

	/**
	 * @param paA Minimim
	 * @param paB Maximim
	 * @param paC Most
	 * @param paSeed Seed for generator
	 */
	public TriangularGenerator(double paA, double paB, double paC, long paSeed) {
		super(paSeed);
		this.aA = paA;
		this.aB = paB;
		this.aC = paC;
	}

	@Override
	public double next() {
		double x = aRandom.nextDouble();
		if (x < (aC - aA) / (aB - aA)) {
			return aA + Math.sqrt(x * (aB - aA) * (aC - aA));
		}
		else {
			return aB - Math.sqrt((1 - x) * (aB - aA) * (aB - aC));
		}
	}

}
