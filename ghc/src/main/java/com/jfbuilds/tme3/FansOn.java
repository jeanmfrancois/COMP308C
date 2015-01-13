/**
 * File Name: FansOn.java<br>
 * Jean-francois Nepton<br>
 * COMP 308 Java for Programmers<br>
 * Cordinator: Dr. Xiaokun Zhang<br>
 * Student ID# 2358976<br>
 * Created: Jan 12, 2015
 */
package com.jfbuilds.tme3;

import java.io.Serializable;

/**
 * FansOn Event to turn fans on
 * <p>
 * Methods to perform core action of turning the fans on and overridden toString
 * methods to return human readable information for the event
 * 
 * @author Jean-francois Nepton
 * @version %I%, %G%
 * @since 1.0
 */
public class FansOn extends Event implements Serializable {

	/**
	 * Used for serialization
	 */
	private static final long serialVersionUID = -330484887591869815L;

	/**
	 * Main Constructor
	 * 
	 * @param delayTime
	 *            time delay until event will be scheduled
	 */
	public FansOn(GreenhouseControls controller, long delayTime) {
		super(controller, delayTime);
	}

	/**
	 * @see com.jfbuilds.tme3.Event#action()
	 */
	@Override
	public void action() {
		// Put hardware control code here to
		// physically turn on the fans.
		getController().setFans(true);
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Fans are on";
	}
}