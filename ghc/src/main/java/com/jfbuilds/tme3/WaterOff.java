/**
 * File Name: WaterOff.java<br>
 * Jean-francois Nepton<br>
 * COMP 308 Java for Programmers<br>
 * Cordinator: Dr. Xiaokun Zhang<br>
 * Student ID# 2358976<br>
 * Created: Jan 12, 2015
 */
package com.jfbuilds.tme3;

import java.io.Serializable;

/**
 * WaterOff Event to turn water off
 * <p>
 * Methods to perform core action of turning the waater off and overridden
 * toString methods to return human readable information for the event
 * 
 * @author Jean-francois Nepton
 * @version %I%, %G%
 * @since 1.0
 */
public class WaterOff extends Event implements Serializable {

	/**
	 * Used for serialization
	 */
	private static final long serialVersionUID = -3487789349647201188L;

	/**
	 * Main Constructor
	 * 
	 * @param delayTime
	 *            time delay until event will be scheduled
	 */
	public WaterOff(GreenhouseControls controller, long delayTime) {
		super(controller, delayTime);
	}

	/**
	 * @see com.jfbuilds.tme3.Event#action()
	 */
	@Override
	public void action() {
		// Put hardware control code here.
		getController().setWater(false);
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Greenhouse water is off";
	}
}
