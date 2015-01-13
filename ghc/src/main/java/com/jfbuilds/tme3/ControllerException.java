/**
 * File Name: ControllerException.java<br>
 * Jean-francois Nepton<br>
 * COMP 308 Java for Programmers<br>
 * Cordinator: Dr. Xiaokun Zhang<br>
 * Student ID# 2358976<br>
 * Created: Jan 12, 2015
 */
package com.jfbuilds.tme3;

/**
 * ControllerException is thrown when an error occurs in GreenhouseController
 * 
 * @author Jean-francois Nepton
 * @version %I%, %G%
 * @since 1.0
 */
public class ControllerException extends Exception {

	/**
	 * Used for serialization
	 */
	private static final long serialVersionUID = 5176095538295379753L;

	private GreenhouseControls controller;

	/**
	 * @param errorCode
	 *            Code to set to signify error type
	 */
	public ControllerException(GreenhouseControls controller, int errorCode) {
		setController(controller);
		getController().setErrorCode(errorCode);
	}

	/**
	 * @return the controller
	 */
	public GreenhouseControls getController() {
		return controller;
	}

	/**
	 * @param controller
	 *            the controller to set
	 */
	public void setController(GreenhouseControls controller) {
		this.controller = controller;
	}

	/**
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		switch (getController().getErrorCode()) {
		case GreenhouseControls.WINDOW_FAIL:
			return "The Greenhouse Controller has experienced a window malfunction error.";
		case GreenhouseControls.POWER_FAIL:
			return "The Greenhouse Controller has experienced a power failure error.";
		default:
			return "An unknown error has occured.";
		}
	}
}
