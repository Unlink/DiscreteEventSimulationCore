/*
 *  Copyright (c) 2015 Michal Ďuračík
 */
package sk.uniza.fri.duracik2.dis.des.core.elements;

/**
 *
 * @author Unlink
 */
public class ResourceException extends RuntimeException {

	public ResourceException() {
	}

	public ResourceException(String paMessage) {
		super(paMessage);
	}

	public ResourceException(String paMessage, Throwable paCause) {
		super(paMessage, paCause);
	}
	
}
