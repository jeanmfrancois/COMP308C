/**
 * File Name: PowerOn.java<br>
 * Jean-francois Nepton<br>
 * COMP 308 Java for Programmers<br>
 * Cordinator: Dr. Xiaokun Zhang<br>
 * Student ID# 2358976<br>
 * Created: Jan 12, 2015
 */
package com.jfbuilds.tme3;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * PowerOn corrects power failure issue
 * <p>
 * fix turns the power on and zeros out error code
 * 
 * @author Jean-francois Nepton
 * @version %I%, %G%
 * @since 1.0
 */
public class PowerOn implements Fixable {

	private GreenhouseControls controller;

	/**
	 * Default Constructor which should not be called
	 */
	@SuppressWarnings("unused")
	private PowerOn() {
	}

	/**
	 * Main Constructor Sets the controller and creates a FixWindow object
	 * 
	 * @param controller
	 *            Calls the Fix
	 */
	public PowerOn(GreenhouseControls controller) {
		setController(controller);
	}

	/**
	 * @return the controller
	 */
	@Override
	public GreenhouseControls getController() {
		return controller;
	}

	/**
	 * @param controller
	 *            the controller to set
	 */
	@Override
	public void setController(GreenhouseControls controller) {
		this.controller = controller;
	}

	/**
	 * @see com.jfbuilds.tme3.Fixable#fix()
	 */
	@Override
	public void fix() {
		getController().removeEvent("PowerOut");
		getController().setPoweron(true);
		getController().setErrorCode(0);
		log();
	}

	/**
	 * @see com.jfbuilds.tme3.Fixable#log()
	 */
	@Override
	public void log() {
		Logger logger = Logger.getLogger("Power On Failure Fix");
		FileHandler fileHandler;
		try {
			fileHandler = new FileHandler("fix.log");
			logger.addHandler(fileHandler);
			SimpleFormatter formatter = new SimpleFormatter();
			fileHandler.setFormatter(formatter);
			logger.info("System has recovered from a power failure error.");
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
	}
}