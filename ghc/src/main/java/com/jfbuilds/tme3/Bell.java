/**
 * File Name: Bell.java<br>
 * Jean-francois Nepton<br>
 * COMP 308 Java for Programmers<br>
 * Cordinator: Dr. Xiaokun Zhang<br>
 * Student ID# 2358976<br>
 * Created: Jan 12, 2015
 */
package com.jfbuilds.tme3;

import java.io.Serializable;

/**
 * Bell Event to set a bell and have it ring one or more times setting
 * <p>
 * A field for the amount of rings left
 * <p>
 * Methods to perform core action of adding a bell and inserting a new one into
 * event list if multiple rings exist and overridden toString methods to return
 * human readable information for the event
 * 
 * @author Jean-francois Nepton
 * @version %I%, %G%
 * @since 1.0
 */
public class Bell extends Event implements Serializable {

	/**
	 * Used for serialization
	 */
	private static final long serialVersionUID = -3020843878313098886L;

	int rings;

	/**
	 * Main Constructor
	 * 
	 * @param delayTime
	 *            time delay until event will be scheduled
	 */
	public Bell(GreenhouseControls controller, long delayTime) {
		super(controller, delayTime);
	}

	/**
	 * Extended Constructor
	 * 
	 * @param delayTime
	 *            time delay until event will be scheduled
	 * @param rings
	 *            number of rings to perform bell event
	 */
	public Bell(GreenhouseControls controller, long delayTime, int rings) {
		super(controller, delayTime);
		this.rings = rings;
	}

	/**
	 * @see com.jfbuilds.tme3.Event#action()
	 */
	@Override
	public void action() {
		if (this.rings > 1)
			getController().addEvent(new Bell(getController(), 2000, --this.rings));
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Ring-a-ding(" + rings + ")";
	}
}
