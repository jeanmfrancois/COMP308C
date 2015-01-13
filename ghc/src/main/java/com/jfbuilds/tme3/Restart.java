/**
 * File Name: Restart.java<br>
 * Jean-francois Nepton<br>
 * COMP 308 Java for Programmers<br>
 * Cordinator: Dr. Xiaokun Zhang<br>
 * Student ID# 2358976<br>
 * Created: Jan 12, 2015
 */
package com.jfbuilds.tme3;

import java.io.Serializable;

/**
 * Restart Event to set restart the system
 * <p>
 * Methods to perform core action of setting restarting the system and
 * overridden toString methods to return human readable information for the
 * event
 * 
 * @author Jean-francois Nepton
 * @version %I%, %G%
 * @since 1.0
 */
public class Restart extends Event implements Serializable {

	/**
	 * Used for serialization
	 */
	private static final long serialVersionUID = 294492641628392864L;

	/**
	 * Main Constructor
	 * 
	 * @param delayTime
	 *            time delay until event will be scheduled
	 * @param filename
	 *            name of file containing events to be added to system
	 */
	public Restart(GreenhouseControls controller, long delayTime, String filename) {
		super(controller, delayTime);
		getController().setEventsFile(filename);
	}

	/**
	 * @see com.jfbuilds.tme3.Event#action()
	 */
	@Override
	public void action() {
		getController().loadEvents(getController().getEventsFile());
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Restarting system";
	}
}
