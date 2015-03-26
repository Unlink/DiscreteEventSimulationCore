/*
 *  Copyright (c) 2015 Michal Ďuračík
 */
package ds_udalostna;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import sk.uniza.fri.duracik2.dis.des.core.statistics.SimpleStatistics;

/**
 *
 * @author Unlink
 */
public class StatsTest {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner s = new Scanner(new File("src/ds_udalostna/data.txt"));
		SimpleStatistics ss = new SimpleStatistics();
		while (s.hasNextDouble()) {
			ss.addToStatistics(s.nextDouble());
		}
		System.out.println("Priemer:" + ss.getValue());
		System.out.println("Odchylka:" + ss.getDeviation());
		System.out.println("Min:" + ss.getLow(0.95));
		System.out.println("Max:" + ss.getHigh(0.95));
	}

}
