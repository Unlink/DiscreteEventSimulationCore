/*
 *  Copyright (c) 2015 Michal Ďuračík
 */
package sk.uniza.fri.duracik2.dis.generators;

/**
 *
 * @author Unlink
 */
public class TrueFalseGenerator implements IGenerator {

	private IGenerator aGenerator;

	private double aBreakValue;

	public TrueFalseGenerator(IGenerator paGenerator, double paBreakValue) {
		this.aGenerator = paGenerator;
		this.aBreakValue = paBreakValue;
	}

	public TrueFalseGenerator(double paBreakValue) {
		this.aBreakValue = paBreakValue;
		this.aGenerator = new UniformGenerator(1);
	}

	public TrueFalseGenerator(double paBreakValue, long paSeed) {
		this.aBreakValue = paBreakValue;
		this.aGenerator = new UniformGenerator(1, paSeed);
	}

	@Override
	public double next() {
		return (aGenerator.next() >= aBreakValue) ? 0 : 1;
	}

	@Override
	public int nextInt() {
		return (int) next();
	}

}
