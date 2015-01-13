/**
 * File Name: PowerOut.java<br>
 * Jean-francois Nepton<br>
 * COMP 308 Java for Programmers<br>
 * Cordinator: Dr. Xiaokun Zhang<br>
 * Student ID# 2358976<br>
 * Created: Jan 12, 2015
 */
package com.jfbuilds.tme3;

import java.io.Serializable;

/**
 * Power Outage Event to signifies an error related to the power failure
 * <p>
 * Methods to perform core action of signifying a power outage failure and
 * overridden toString methods to return human readable information for the
 * event
 * 
 * @author Jean-francois Nepton
 * @version %I%, %G%
 * @since 1.0
 */
public class PowerOut extends Event implements Serializable {

	/**
	 * Used for serialization
	 */
	private static final long serialVersionUID = 8262582940776304830L;

	/**
	 * Main Constructor
	 * 
	 * @param delayTime
	 *            time delay until event will be scheduled
	 */
	public PowerOut(GreenhouseControls controller, long delayTime) {
		super(controller, delayTime);
	}

	/**
	 * @see com.jfbuilds.tme3.Event#action()
	 */
	@Override
	public void action() throws ControllerException {
		// Put hardware control code here to
		// physically turn on the fans.
		getController().setPoweron(false);
		throw new ControllerException(getController(), GreenhouseControls.POWER_FAIL);
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Power outage occurred";
	}
}