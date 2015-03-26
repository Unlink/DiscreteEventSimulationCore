/*
 *  Copyright (c) 2015 Michal Ďuračík
 */
package sk.uniza.fri.duracik2.dis.generators;

/**
 *
 * @author Unlink
 */
public interface IGenerator {

	/**
	 * Returns next value from generator
	 *
	 * @return
	 */
	public double next();

	/**
	 * Returns next integer value
	 *
	 * @return
	 */
	public int nextInt();

}
