/*
 *  Copyright (c) 2015 Michal Ďuračík
 */
package ds_udalostna;

import sk.uniza.fri.duracik2.dis.des.core.timming.ITime;
import sk.uniza.fri.duracik2.dis.des.core.timming.SimpleUnit;
import sk.uniza.fri.duracik2.dis.des.core.timming.Time;

/**
 *
 * @author Unlink
 */
public class TimeTest {
	public static void main(String[] args) {
		ITime cas = new Time(0);
		cas = cas.plus(new Time(10));
		cas = cas.plus(new Time(1, SimpleUnit.MINUTE));
		System.out.println(cas);
		cas = cas.divide(2);
		System.out.println(cas);
		cas = cas.plus(new Time(100));
		System.out.println(cas);
		System.out.println(cas.convertTo(SimpleUnit.HOUR));
		cas = cas.minus(new Time(35));
		System.out.println(cas);
	}
}
