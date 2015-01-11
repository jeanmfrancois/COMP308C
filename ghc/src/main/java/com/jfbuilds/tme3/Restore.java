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

import com.jfbuilds.tme3.GreenhouseControls.Bell;

/**
 * Restore Used to restore the system after a system failure
 * <p>
 * (description of core fields)
 * <p>
 * (description of core methods)
 * 
 * @author Jean-francois Nepton
 * @version %I%, %G%
 * @since 1.0
 */
public class Restore {

	private String fileName;

	private GreenhouseControls gc = null;

	/**
	 * @param fileName
	 */
	public Restore(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return
	 */
	public GreenhouseControls recoverGreenhouseControls() {
		retrieveGreenhouseControls();
		recoverEventsState();
		return gc;
	}

	/**
	 * 
	 */
	public void retrieveGreenhouseControls() {
		try (FileInputStream fis = new FileInputStream(fileName); ObjectInputStream ois = new ObjectInputStream(fis)) {
			System.out.println("Deserialized Greenhouse Controls...");
			gc = (GreenhouseControls) ois.readObject();
		} catch (Exception e) {
			System.out.println("There was a problem deserializing Greenhouse Controls");
			System.out.println("System will use an empty Greenhouse Controls object");
			gc = new GreenhouseControls();
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	private void recoverEventsState() {
		List<Event> oldStateEventList = gc.getEventList();
		gc.setEventList(new ArrayList<Event>());
		for (Event event : oldStateEventList) {
			String className = event.getClass().getSimpleName();
			if (className.equals("Bell")) {
				Bell bell = (Bell) event;
				gc.createEvent(className, bell.delayTime, bell.rings);
			} else {
				gc.createEvent(className, event.delayTime, 0);
			}
		}
	}
}
