/*
 *  Copyright (c) 2015 Michal Ďuračík
 */
package ds_udalostna;

import sk.uniza.fri.duracik2.dis.generators.IGenerator;
import sk.uniza.fri.duracik2.dis.generators.TrueFalseGenerator;

/**
 *
 * @author Unlink
 */
public class TrueFalseTest {
	
	public static void main(String[] args) {
		double sum = 0;
		IGenerator g = new TrueFalseGenerator(0.12);
		for (int i = 0; i < 100000; i++) {
			sum += g.next();
		}
		System.out.println(sum);
	}
	
}
