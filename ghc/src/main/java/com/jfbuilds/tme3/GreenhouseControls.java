// : innerclasses/GreenhouseControls.java
// This produces a specific application of the
// control system, all in a single class. Inner
// classes allow you to encapsulate different
// functionality for each type of event.
// From 'Thinking in Java, 4th ed.' (c) Bruce Eckel 2005
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
/***********************************************************************
 * Adapated for COMP308 Java for Programmer, SCIS, Athabasca University
 * Assignment: TME3
 * 
 * @author: Steve Leung
 * @date : Oct 21, 2005
 */
/**
 * File Name: GreenhouseControls.java<br>
 * Jean-francois Nepton<br>
 * COMP 308 Java for Programmers<br>
 * Cordinator: Dr. Xiaokun Zhang<br>
 * Student ID# 2358976<br>
 * Created: Nov 3, 2015
 */
package com.jfbuilds.tme3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * GreenhouseControls is used to simulate the events of a Greenhouse control
 * device
 * <p>
 * Several fields exist to signify certain even conditions such as light be on
 * or off, themostat settings, error code for failures and location of an events
 * file
 * <p>
 * Methods to print proper usage, parse events, removal of events, load external
 * events, log severe errors, create a dump file, and saving state
 * 
 * @author Jean-francois Nepton
 * @version %I%, %G%
 * @since 1.0
 */
public class GreenhouseControls extends Controller implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2636397787261306102L;

	@SuppressWarnings("unused")
	private boolean light = false;

	@SuppressWarnings("unused")
	private boolean water = false;

	@SuppressWarnings("unused")
	private boolean fans = false;

	@SuppressWarnings("unused")
	private boolean windowsok = true;

	@SuppressWarnings("unused")
	private boolean poweron = true;

	@SuppressWarnings("unused")
	private String thermostat = "Day";

	private int errorCode = 0;

	private String eventsFile = "";

	/**
	 * LightOn Event to turn lights on
	 * <p>
	 * Methods to perform core action of turning the light on and overridden
	 * toString methods to return human readable information for the event
	 * 
	 * @author Jean-francois Nepton
	 * @version %I%, %G%
	 * @since 1.0
	 */
	public class LightOn extends Event implements Serializable {

		/**
		 * Used for serialization
		 */
		private static final long serialVersionUID = -7070231203706395410L;

		/**
		 * Main Constructor
		 * 
		 * @param delayTime
		 *            time delay until event will be scheduled
		 */
		public LightOn(long delayTime) {
			super(delayTime);
		}

		/**
		 * @see com.jfbuilds.tme3.Event#action()
		 */
		@Override
		public void action() {
			// Put hardware control code here to
			// physically turn on the light.
			light = true;
		}

		/**
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "Light is on";
		}
	}

	/**
	 * LightOff Event to turn lights off
	 * <p>
	 * Methods to perform core action of turning the light off and overridden
	 * toString methods to return human readable information for the event
	 * 
	 * @author Jean-francois Nepton
	 * @version %I%, %G%
	 * @since 1.0
	 */
	public class LightOff extends Event implements Serializable {

		/**
		 * Used for serialization
		 */
		private static final long serialVersionUID = 8673663461171319350L;

		/**
		 * Main Constructor
		 * 
		 * @param delayTime
		 *            time delay until event will be scheduled
		 */
		public LightOff(long delayTime) {
			super(delayTime);
		}

		/**
		 * @see com.jfbuilds.tme3.Event#action()
		 */
		@Override
		public void action() {
			// Put hardware control code here to
			// physically turn off the light.
			light = false;
		}

		/**
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "Light is off";
		}
	}

	/**
	 * WaterOn Event to turn water on
	 * <p>
	 * Methods to perform core action of turning the water on and overridden
	 * toString methods to return human readable information for the event
	 * 
	 * @author Jean-francois Nepton
	 * @version %I%, %G%
	 * @since 1.0
	 */
	public class WaterOn extends Event implements Serializable {

		/**
		 * Used for serialization
		 */
		private static final long serialVersionUID = -2589085048638229674L;

		/**
		 * Main Constructor
		 * 
		 * @param delayTime
		 *            time delay until event will be scheduled
		 */
		public WaterOn(long delayTime) {
			super(delayTime);
		}

		/**
		 * @see com.jfbuilds.tme3.Event#action()
		 */
		@Override
		public void action() {
			// Put hardware control code here.
			water = true;
		}

		/**
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "Greenhouse water is on";
		}
	}

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
		public WaterOff(long delayTime) {
			super(delayTime);
		}

		/**
		 * @see com.jfbuilds.tme3.Event#action()
		 */
		@Override
		public void action() {
			// Put hardware control code here.
			water = false;
		}

		/**
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "Greenhouse water is off";
		}
	}

	/**
	 * FansOn Event to turn fans on
	 * <p>
	 * Methods to perform core action of turning the fans on and overridden
	 * toString methods to return human readable information for the event
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
		public FansOn(long delayTime) {
			super(delayTime);
		}

		/**
		 * @see com.jfbuilds.tme3.Event#action()
		 */
		@Override
		public void action() {
			// Put hardware control code here to
			// physically turn on the fans.
			fans = true;
		}

		/**
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "Fans are on";
		}
	}

	/**
	 * FansOff Event to turn fans off
	 * <p>
	 * Methods to perform core action of turning the fans off and overridden
	 * toString methods to return human readable information for the event
	 * 
	 * @author Jean-francois Nepton
	 * @version %I%, %G%
	 * @since 1.0
	 */
	public class FansOff extends Event implements Serializable {

		/**
		 * Used for serialization
		 */
		private static final long serialVersionUID = 3545151372363490730L;

		/**
		 * Main Constructor
		 * 
		 * @param delayTime
		 *            time delay until event will be scheduled
		 */
		public FansOff(long delayTime) {
			super(delayTime);
		}

		/**
		 * @see com.jfbuilds.tme3.Event#action()
		 */
		@Override
		public void action() {
			// Put hardware control code here to
			// physically turn off the fans.
			fans = false;
		}

		/**
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "Fans are off";
		}
	}

	/**
	 * Thermostat Night Event to set thermostat to Night setting
	 * <p>
	 * Methods to perform core action of setting the thermostat to night setting
	 * and overridden toString methods to return human readable information for
	 * the event
	 * 
	 * @author Jean-francois Nepton
	 * @version %I%, %G%
	 * @since 1.0
	 */
	public class ThermostatNight extends Event implements Serializable {

		/**
		 * Used for serialization
		 */
		private static final long serialVersionUID = 2986775111303640330L;

		/**
		 * Main Constructor
		 * 
		 * @param delayTime
		 *            time delay until event will be scheduled
		 */
		public ThermostatNight(long delayTime) {
			super(delayTime);
		}

		/**
		 * @see com.jfbuilds.tme3.Event#action()
		 */
		@Override
		public void action() {
			// Put hardware control code here.
			thermostat = "Night";
		}

		/**
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "Thermostat on night setting";
		}
	}

	/**
	 * Thermostat Day Event to set thermostat to Day setting
	 * <p>
	 * Methods to perform core action of setting the thermostat to day setting
	 * and overridden toString methods to return human readable information for
	 * the event
	 * 
	 * @author Jean-francois Nepton
	 * @version %I%, %G%
	 * @since 1.0
	 */
	public class ThermostatDay extends Event implements Serializable {

		/**
		 * Used for serialization
		 */
		private static final long serialVersionUID = -2698723301362506192L;

		/**
		 * Main Constructor
		 * 
		 * @param delayTime
		 *            time delay until event will be scheduled
		 */
		public ThermostatDay(long delayTime) {
			super(delayTime);
		}

		/**
		 * @see com.jfbuilds.tme3.Event#action()
		 */
		@Override
		public void action() {
			// Put hardware control code here.
			thermostat = "Day";
		}

		/**
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "Thermostat on day setting";
		}
	}

	/**
	 * Bell Event to set a bell and have it ring one or more times setting
	 * <p>
	 * A field for the amount of rings left
	 * <p>
	 * Methods to perform core action of adding a bell and inserting a new one
	 * into event list if multiple rings exist and overridden toString methods
	 * to return human readable information for the event
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
		public Bell(long delayTime) {
			super(delayTime);
		}

		/**
		 * Extended Constructor
		 * 
		 * @param delayTime
		 *            time delay until event will be scheduled
		 * @param rings
		 *            number of rings to perform bell event
		 */
		public Bell(long delayTime, int rings) {
			super(delayTime);
			this.rings = rings;
		}

		/**
		 * @see com.jfbuilds.tme3.Event#action()
		 */
		@Override
		public void action() {
			if (this.rings > 1)
				addEvent(new Bell(2000, --this.rings));
		}

		/**
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "Ring-a-ding(" + rings + ")";
		}
	}

	/**
	 * Restart Event to set restart the system
	 * <p>
	 * Methods to perform core action of setting restarting the system and
	 * overridden toString methods to return human readable information for the
	 * event
	 * 
	 * @author Jean-francois Nepton
	 * @version %I%, %G%
	 * @since 1.0
	 */
	public class Restart extends Event implements Serializable {

		/**
		 * Used for serialization
		 */
		private static final long serialVersionUID = 294492641628392864L;

		/**
		 * Main Constructor
		 * 
		 * @param delayTime
		 *            time delay until event will be scheduled
		 * @param filename
		 *            name of file containing events to be added to system
		 */
		public Restart(long delayTime, String filename) {
			super(delayTime);
			eventsFile = filename;
		}

		/**
		 * @see com.jfbuilds.tme3.Event#action()
		 */
		@Override
		public void action() {
			loadEvents(eventsFile);
		}

		/**
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "Restarting system";
		}
	}

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
		public Terminate(long delayTime) {
			super(delayTime);
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
		public WindowMalfunction(long delayTime) {
			super(delayTime);
		}

		/**
		 * @see com.jfbuilds.tme3.Event#action()
		 */
		@Override
		public void action() throws ControllerException {
			// Put hardware control code here to
			// physically turn off the fans.
			windowsok = false;
			throw new ControllerException(1);
		}

		/**
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "Window malfunction occurred";
		}
	}

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
		public PowerOut(long delayTime) {
			super(delayTime);
		}

		/**
		 * @see com.jfbuilds.tme3.Event#action()
		 */
		@Override
		public void action() throws ControllerException {
			// Put hardware control code here to
			// physically turn on the fans.
			poweron = false;
			throw new ControllerException(2);
		}

		/**
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "Power outage occurred";
		}
	}

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

		/**
		 * @see com.jfbuilds.tme3.Fixable#fix()
		 */
		@Override
		public void fix() {
			removeEvent("PowerOut");
			poweron = true;
			errorCode = 0;
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

		/**
		 * @see com.jfbuilds.tme3.Fixable#fix()
		 */
		@Override
		public void fix() {
			removeEvent("WindowMalfunction");
			windowsok = true;
			errorCode = 0;
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

	/**
	 * ControllerException is thrown when an error occurs in
	 * GreenhouseController
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

		/**
		 * @param e
		 *            code to set to signify error type
		 */
		public ControllerException(int e) {
			errorCode = e;
		}

		/**
		 * @see java.lang.Throwable#getMessage()
		 */
		@Override
		public String getMessage() {
			switch (errorCode) {
			case 1:
				return "The Greenhouse Controller has experienced a window malfunction error.";
			case 2:
				return "The Greenhouse Controller has experienced a power failure error.";
			default:
				return "An unknown error has occured.";
			}
		}
	}

	/**
	 * Print valid options to be supplied as arguments for application execution
	 */
	private static void printUsage() {
		System.out.println("Correct format: ");
		System.out.println("  java GreenhouseControls -f <filename>, or");
		System.out.println("  java GreenhouseControls -d dump.out");
	}

	/**
	 * Parses a line of text and extracts an Event name, delaytime, and optional
	 * argument
	 * 
	 * @param eventLine
	 *            The event line of text to parse
	 */
	private void parseEvent(String eventLine) {
		String eventName = null;
		Long timeDelay = null;
		int arg = 0;
		String[] tokens = eventLine.split(",");
		eventName = tokens[0].split("=")[1];
		timeDelay = Long.parseLong(tokens[1].split("=")[1]);
		if (tokens.length > 2 && tokens.length < 4) {
			arg = Integer.parseInt(tokens[2].split("=")[1]);
		}
		createEvent(eventName, timeDelay, arg);
	}

	/**
	 * Removes an event from the event list
	 * 
	 * @param eventName
	 *            name of the event to be removed
	 */
	private void removeEvent(String eventName) {
		ArrayList<Event> newList = new ArrayList<Event>();
		for (Event event : getEventList()) {
			String className = event.getClass().getSimpleName();
			if (!className.equals(eventName))
				newList.add(event);
		}
		setEventList(newList);
	}

	/**
	 * Loads events for a GreenhouseController
	 * 
	 * @param fileName
	 *            The name of the file containing events to be loaded
	 */
	private void loadEvents(String fileName) {
		File file = new File(fileName);
		try (Scanner scanner = new Scanner(file)) {
			while (scanner.hasNext()) {
				parseEvent(scanner.next());
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("Could not locate the file " + fileName);
			e.printStackTrace();
		}
	}

	/**
	 * Logs a severe type error such as a system failure event
	 * 
	 * @param message
	 *            to be used to describe the nature of the error
	 */
	private void severeLog(String message) {
		Logger logger = Logger.getLogger("Greenhouse Controller Log");
		FileHandler fileHandler;
		try {
			fileHandler = new FileHandler("error.log");
			logger.addHandler(fileHandler);
			SimpleFormatter formatter = new SimpleFormatter();
			fileHandler.setFormatter(formatter);
			logger.severe("System was shut down unexpectedly - " + message);
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Creates a dump file for later restoration of a GreenhouseContoller object
	 * after a system failure
	 * 
	 * @param gc
	 *            the GreenhouseControl object to be saved
	 * @throws IOException
	 *             if there is an issue saving the file to system
	 */
	private static void dumpGreenhouseControls(GreenhouseControls gc) throws IOException {
		try (FileOutputStream fos = new FileOutputStream("dump.out");
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(gc);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Saves the events state and records any time that has already occured for
	 * the event
	 * 
	 * @param greenhouseControls
	 *            object to save it's events state
	 */
	private static void saveEventsState(GreenhouseControls gc) {
		for (Event event : gc.getEventList()) {
			event.setElapsedTime(event.getEventTime() - System.currentTimeMillis());
		}
	}

	/*
	 * Returns the current error code, 0 if no error present
	 */
	int getError() {
		return errorCode;
	}

	/*
	 * Returns the appropriate fix to currect system failure issues.
	 */
	Fixable getFixable(int errorCode) {
		if (errorCode == 2)
			return new PowerOn();
		else
			return new FixWindow();
	}

	/**
	 * @see com.jfbuilds.tme3.Controller#shutdown(java.lang.String)
	 */
	@Override
	void shutdown(String errorMessage) {
		super.shutdown(errorMessage);
		severeLog(errorMessage);
		try {
			saveEventsState(this);
			dumpGreenhouseControls(this);
		} catch (IOException e) {
			System.out.println("Error in writing Greenhouse Controls dump file.");
			e.printStackTrace();
		}
		System.exit(0);
	}

	/**
	 * Creates an event based on supplied Event name, timeDelay, and optional
	 * argument
	 * 
	 * @param eventName
	 *            The name of the event to be checked against a switch statement
	 *            for corresponding event
	 * @param timeDelay
	 *            The delay of time before event is invoked
	 * @param arg
	 *            An optional argument supplied to event
	 */
	public void createEvent(String eventName, Long timeDelay, int arg) {
		switch (eventName) {
		case "LightOn":
			addEvent(new LightOn(timeDelay));
			break;
		case "LightOff":
			addEvent(new LightOff(timeDelay));
			break;
		case "WaterOn":
			addEvent(new WaterOn(timeDelay));
			break;
		case "WaterOff":
			addEvent(new WaterOff(timeDelay));
			break;
		case "FansOn":
			addEvent(new FansOn(timeDelay));
			break;
		case "FansOff":
			addEvent(new FansOff(timeDelay));
			break;
		case "ThermostatDay":
			addEvent(new ThermostatDay(timeDelay));
			break;
		case "ThermostatNight":
			addEvent(new ThermostatNight(timeDelay));
			break;
		case "Terminate":
			addEvent(new Terminate(timeDelay));
			break;
		case "WindowMalfunction":
			addEvent(new WindowMalfunction(timeDelay));
			break;
		case "PowerOut":
			addEvent(new PowerOut(timeDelay));
			break;
		case "Bell":
			if (arg != 0)
				addEvent(new Bell(timeDelay, arg));
			else
				addEvent(new Bell(timeDelay));
			break;
		default:
			System.out.println("Error in creating event: " + eventName + ", it will not be added to event list.");
		}
	}

	/**
	 * Entry point of application to check if file was supplied to run events or
	 * if a dump should be loaded for restoration
	 * 
	 * @param args
	 *            to either signify a filename for processing [-f] or a dump
	 *            file to restore from [-d]
	 */
	public static void main(String[] args) {
		try {
			String option = args[0];
			String filename = args[1];
			GreenhouseControls gc = null;
			if (!(option.equals("-f")) && !(option.equals("-d"))) {
				System.out.println("Invalid option");
				printUsage();
			}
			if (option.equals("-f")) {
				gc = new GreenhouseControls();
				gc.loadEvents(filename);
			} else {
				Restore restore = new Restore(filename);
				gc = restore.recoverGreenhouseControls();
				gc.getFixable(gc.errorCode).fix();
			}
			gc.run();
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Invalid number of parameters");
			printUsage();
		}
	}
} // /:~
