/*
 *  Copyright (c) 2015 Michal Ďuračík
 */
package sk.uniza.fri.duracik2.dis.generators;

import java.util.Random;

/**
 *
 * @author Unlink
 */
public abstract class AGenerator implements IGenerator {

	protected Random aRandom;

	public AGenerator() {
		aRandom = new Random();
	}

	public AGenerator(long seed) {
		aRandom = new Random(seed);
	}

	/**
	 * Dummy implementation of nextInt()
	 *
	 * @return
	 */
	@Override
	public int nextInt() {
		return (int) next();
	}
}
