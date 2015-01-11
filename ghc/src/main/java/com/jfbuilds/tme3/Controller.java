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
package com.jfbuilds.tme3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.jfbuilds.tme3.GreenhouseControls.ControllerException;

public class Controller implements Serializable {

	// A class from java.util to hold Event objects:
	private List<Event> eventList = new ArrayList<Event>();

	public void addEvent(Event c) {
		eventList.add(c);
	}

	public void run() {
		while (eventList.size() > 0)
			// Make a copy so you're not modifying the list
			// while you're selecting the elements in it:
			for (Event event : new ArrayList<Event>(eventList))
				if (event.ready()) {
					System.out.println(event);
					try {
						event.action();
					} catch (ControllerException e) {
						// TODO Auto-generated catch block
						shutdown(e.getMessage());
					}
					eventList.remove(event);
				}
	}

	/**
	 * Shutdown the system
	 * 
	 * @param errorMessage
	 *            to be logged for detailing cause of error
	 */
	void shutdown(String errorMessage) {
		System.out.println("System will be shut down...");
	}
} // /:~
