/**
 * File Name: WindowMalfunction.java<br>
 * Jean-francois Nepton<br>
 * COMP 308 Java for Programmers<br>
 * Cordinator: Dr. Xiaokun Zhang<br>
 * Student ID# 2358976<br>
 * Created: Jan 12, 2015
 */
package com.jfbuilds.tme3;

import java.io.Serializable;

/**
 * WindowMalfunction Event to signifies an error related to the windows
 * interface
 * <p>
 * Methods to perform core action of signifying a window malfunction and
 * overridden toString methods to return human readable information for the
 * event
 * 
 * @author Jean-francois Nepton
 * @version %I%, %G%
 * @since 1.0
 */
public class WindowMalfunction extends Event implements Serializable {

	/**
	 * Used for serialization
	 */
	private static final long serialVersionUID = -2960841477738825910L;

	/**
	 * Main Constructor
	 * 
	 * @param delayTime
	 *            time delay until event will be scheduled
	 */
	public WindowMalfunction(GreenhouseControls controller, long delayTime) {
		super(controller, delayTime);
	}

	/**
	 * @see com.jfbuilds.tme3.Event#action()
	 */
	@Override
	public void action() throws ControllerException {
		// Put hardware control code here to
		// physically turn off the fans.
		getController().setWindowsok(false);
		throw new ControllerException(getController(), GreenhouseControls.WINDOW_FAIL);
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Window malfunction occurred";
	}
}