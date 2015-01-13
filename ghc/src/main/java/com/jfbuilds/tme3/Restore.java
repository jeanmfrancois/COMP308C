/**
 * File Name: Restore.java<br>
 * Jean-francois Nepton<br>
 * COMP 308 Java for Programmers<br>
 * Cordinator: Dr. Xiaokun Zhang<br>
 * Student ID# 2358976<br>
 * Created: Nov 11, 2015
 */
package com.jfbuilds.tme3;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Restore Used to restore the system after a system failure
 * <p>
 * fileName is used to refer to the restoration file location gc referes to the
 * related GreenhousControls object associated to restoration
 * <p>
 * Methods exist to deserialize the the recovered GreenhouseControls object
 * 
 * @author Jean-francois Nepton
 * @version %I%, %G%
 * @since 1.0
 */
public class Restore {

	private String fileName;

	private GreenhouseControls gc = null;

	/**
	 * Core constructor to create a restore object
	 * 
	 * @param fileName
	 *            is supplied to denote the file to restore
	 */
	public Restore(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Main process to retrieve the GreenhouseControls, recover it's state and
	 * return it to calling method
	 * 
	 * @return the recovered Greenhouse Controls object
	 */
	public GreenhouseControls recoverGreenhouseControls() {
		retrieveGreenhouseControls();
		recoverEventsState();
		return gc;
	}

	/**
	 * Retrieves the GreenhouseControls object
	 */
	public void retrieveGreenhouseControls() {
		try (FileInputStream fis = new FileInputStream(fileName); ObjectInputStream ois = new ObjectInputStream(fis)) {
			gc = (GreenhouseControls) ois.readObject();
		} catch (Exception e) {
			System.out.println("There was a problem deserializing Greenhouse Controls");
			System.out.println("System will use an empty Greenhouse Controls object");
			gc = new GreenhouseControls();
			e.printStackTrace();
		}
	}

	/**
	 * Restores the state of the recovered GreenhouseControls object
	 */
	private void recoverEventsState() {
		List<Event> oldStateEventList = gc.getEventList();
		gc.setEventList(new ArrayList<Event>());
		for (Event event : oldStateEventList) {
			String className = event.getClass().getSimpleName();
			if (className.equals("Bell")) {
				Bell bell = (Bell) event;
				gc.createEvent(className, bell.delayTime - bell.getElapsedTime(), bell.rings);
			} else {
				gc.createEvent(className, event.delayTime - event.getElapsedTime(), 0);
			}
		}
	}
}
