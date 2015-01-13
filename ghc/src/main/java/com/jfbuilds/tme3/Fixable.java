/**
 * File Name: Fixable.java<br>
 * Jean-francois Nepton<br>
 * COMP 308 Java for Programmers<br>
 * Cordinator: Dr. Xiaokun Zhang<br>
 * Student ID# 2358976<br>
 * Created: Nov 3, 2015
 */
package com.jfbuilds.tme3;

/**
 * Fixable is an interface used to create a repairing object due to system
 * failure
 * 
 * @author Jean-francois Nepton
 * @version %I%, %G%
 * @since 1.0
 */
public interface Fixable {

	/**
	 * Turns the Power on, fix window and zeros out error codes
	 */
	void fix();

	/**
	 * Logs to a text file in current directory, identify time and nature of the
	 * fix
	 */
	void log();

	/**
	 * Set associated GreenhouseController which called the Fixable object
	 * 
	 * @param controller
	 *            the GreenhouseController to be set
	 */
	void setController(GreenhouseControls controller);

	/**
	 * Get associated GreenhouseController which called the Fixable object
	 * 
	 * @param controller
	 * @return the controller
	 */
	GreenhouseControls getController();
}
