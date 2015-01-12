// : innerclasses/controller/Event.java
// The common methods for any control event.
// From 'Thinking in Java, 4th ed.' (c) Bruce Eckel 2005
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
/***********************************************************************
 * Adapated for COMP308 Java for Programmer, SCIS, Athabasca University
 * Assignment: TME3
 * 
 * @author: Steve Leung
 * @date : Oct. 21, 2006 Description: Event abstract class
 */
/**
 * File Name: Event.java<br>
 * Jean-francois Nepton<br>
 * COMP 308 Java for Programmers<br>
 * Cordinator: Dr. Xiaokun Zhang<br>
 * Student ID# 2358976<br>
 * Created: Nov 3, 2015
 */
package com.jfbuilds.tme3;

import java.io.Serializable;

import com.jfbuilds.tme3.GreenhouseControls.ControllerException;

/**
 * Event is base class for any type of event that may occur within a Greenhouse
 * <p>
 * (description of core fields)
 * <p>
 * (description of core methods)
 * 
 * @author Jean-francois Nepton
 * @version %I%, %G%
 * @since 1.0
 */
public abstract class Event implements Serializable {

	/**
	 * Used for serialization
	 */
	private static final long serialVersionUID = -5210342678813445499L;

	private long eventTime;

	private long elapsedTime = 0;

	protected final long delayTime;

	/**
	 * Main Constructor
	 * 
	 * @param delayTime
	 *            time delay until event will be scheduled
	 */
	public Event(long delayTime) {
		this.delayTime = delayTime;
		start();
	}

	/**
	 * Getter for the time the event should be released
	 * 
	 * @return the time the event should be released
	 */
	public long getEventTime() {
		return eventTime;
	}

	/**
	 * Getter for the time that has already elapsed since the event was issued
	 * to be released
	 * 
	 * @return the elapsed time
	 */
	public long getElapsedTime() {
		return elapsedTime;
	}

	/**
	 * Setter for the time that has already passed since event was issued to be
	 * released
	 * 
	 * @param elapsedTime
	 *            the elapsed time to set for the event
	 */
	public void setElapsedTime(long elapsedTime) {
		this.elapsedTime = elapsedTime;
	}

	/**
	 * Starts the event, allows for restarting
	 */
	public void start() {
		eventTime = System.currentTimeMillis() + delayTime;
	}

	/**
	 * Check whether the system is ready to be released
	 * 
	 * @return whether current time exceeds event time
	 */
	public boolean ready() {
		return System.currentTimeMillis() >= eventTime;
	}

	/**
	 * Perform action of the event
	 * 
	 * @throws ControllerException
	 *             if a system failure event is executed
	 */
	public abstract void action() throws ControllerException;
} // /:~
