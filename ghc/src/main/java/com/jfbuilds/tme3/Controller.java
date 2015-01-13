// : innerclasses/controller/Controller.java
// The reusable framework for control systems.
// From 'Thinking in Java, 4th ed.' (c) Bruce Eckel 2005
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
/***********************************************************************
 * Adapated for COMP308 Java for Programmer, SCIS, Athabasca University
 * Assignment: TME3
 * 
 * @author: Steve Leung
 * @date : Oct 21, 2006
 */
/**
 * File Name: Controller.java<br>
 * Jean-francois Nepton<br>
 * COMP 308 Java for Programmers<br>
 * Cordinator: Dr. Xiaokun Zhang<br>
 * Student ID# 2358976<br>
 * Created: Nov 3, 2015
 */
package com.jfbuilds.tme3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller base class and framework for a control unit
 * <p>
 * Initiation time of the system and an event list of controller events are
 * related fields
 * <p>
 * Methods to add events, get the event list, run the controller and for
 * emergency shutdown exist for class
 * 
 * @author Jean-francois Nepton
 * @version %I%, %G%
 * @since 1.0
 */
public class Controller implements Serializable {

	/**
	 * Used for serialization
	 */
	private static final long serialVersionUID = 4320323286381869030L;

	long initTime;

	private List<Event> eventList = new ArrayList<Event>();

	/**
	 * Adds an event to the stored event list
	 * 
	 * @param event
	 *            the event to be added
	 */
	public void addEvent(Event event) {
		eventList.add(event);
	}

	/**
	 * Getter for retrieving the stored event list
	 * 
	 * @return the stored event list
	 */
	public List<Event> getEventList() {
		return eventList;
	}

	/**
	 * Setter for the event list to another event list
	 * 
	 * @param newList
	 *            the new list to be stored
	 */
	public void setEventList(ArrayList<Event> newList) {
		eventList = newList;
	}

	/**
	 * Runs the controller when called, performing actions when they are ready
	 */
	public void run() {
		initTime = System.currentTimeMillis();
		while (eventList.size() > 0)
			// Make a copy so you're not modifying the list
			// while you're selecting the elements in it:
			for (Event event : new ArrayList<Event>(eventList))
				if (event.ready()) {
					System.out.println(event);
					try {
						event.action();
					} catch (ControllerException e) {
						shutdown(e.getMessage());
					}
					eventList.remove(event);
				}
	}

	/**
	 * Shutdown the system usually due to an emergency shutdown
	 * 
	 * @param errorMessage
	 *            to be logged for detailing cause of error
	 */
	void shutdown(String errorMessage) {
		System.out.println("System will be shut down...");
	}
} // /:~
