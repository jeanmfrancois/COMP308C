/**
 * File Name: FixWindow.java<br>
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
 * FixWindow corrects window failure issue
 * <p>
 * fix fixes the window and zeros out error code
 * 
 * @author Jean-francois Nepton
 * @version %I%, %G%
 * @since 1.0
 */
public class FixWindow implements Fixable {

	private GreenhouseControls controller;

	/**
	 * Default Constructor which should not be called
	 */
	@SuppressWarnings("unused")
	private FixWindow() {
	}

	/**
	 * Main Constructor Sets the controller and creates a FixWindow object
	 * 
	 * @param controller
	 *            Calls the Fix
	 */
	public FixWindow(GreenhouseControls controller) {
		setController(controller);
	}

	/**
	 * @see com.jfbuilds.tme3.Fixable#setController(com.jfbuilds.tme3.GreenhouseControls)
	 */
	@Override
	public void setController(GreenhouseControls controller) {
		this.controller = controller;
	}

	/**
	 * @see com.jfbuilds.tme3.Fixable#getController()
	 */
	@Override
	public GreenhouseControls getController() {
		return controller;
	}

	/**
	 * @see com.jfbuilds.tme3.Fixable#fix()
	 */
	@Override
	public void fix() {
		getController().removeEvent("WindowMalfunction");
		getController().setWindowsok(true);
		getController().setErrorCode(0);
		log();
	}

	/**
	 * @see com.jfbuilds.tme3.Fixable#log()
	 */
	@Override
	public void log() {
		Logger logger = Logger.getLogger("Window Malfunction Failure Fix");
		FileHandler fileHandler;
		try {
			fileHandler = new FileHandler("fix.log");
			logger.addHandler(fileHandler);
			SimpleFormatter formatter = new SimpleFormatter();
			fileHandler.setFormatter(formatter);
			logger.info("System has recovered from a window malfunction error.");
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
	}
}
