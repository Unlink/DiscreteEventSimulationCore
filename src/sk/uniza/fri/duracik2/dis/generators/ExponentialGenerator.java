/*
 *  Copyright (c) 2015 Michal Ďuračík
 */
package sk.uniza.fri.duracik2.dis.generators;

/**
 *
 * @author Unlink
 */
public class ExponentialGenerator extends AGenerator {

	private final double aLambda;

	public ExponentialGenerator(double paE) {
		if (paE <= 0) {
			throw new IllegalArgumentException("Stredná hodnota musí byť > ako 0, '" + paE + "' zadané");
		}
		this.aLambda = 1 / paE;
	}

	public ExponentialGenerator(double paLambda, long paSeed) {
		super(paSeed);
		this.aLambda = 1 / paLambda;
	}

	@Override
	public double next() {
		return Math.log(1 - aRandom.nextDouble()) / (-aLambda);
	}

}
