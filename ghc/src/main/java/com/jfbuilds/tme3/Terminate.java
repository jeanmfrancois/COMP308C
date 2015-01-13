/**
 * File Name: Terminate.java<br>
 * Jean-francois Nepton<br>
 * COMP 308 Java for Programmers<br>
 * Cordinator: Dr. Xiaokun Zhang<br>
 * Student ID# 2358976<br>
 * Created: Jan 12, 2015
 */
package com.jfbuilds.tme3;

import java.io.Serializable;

/**
 * Terminating Event to stop the system and turn off
 * <p>
 * Methods to perform core action of turning off the system and overridden
 * toString methods to return human readable information for the event
 * 
 * @author Jean-francois Nepton
 * @version %I%, %G%
 * @since 1.0
 */
public class Terminate extends Event implements Serializable {

	/**
	 * Used for serialization
	 */
	private static final long serialVersionUID = -9093463126901097631L;

	/**
	 * Main Constructor
	 * 
	 * @param delayTime
	 *            time delay until event will be scheduled
	 */
	public Terminate(GreenhouseControls controller, long delayTime) {
		super(controller, delayTime);
	}

	/**
	 * @see com.jfbuilds.tme3.Event#action()
	 */
	@Override
	public void action() {
		System.exit(0);
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Terminating";
	}
}