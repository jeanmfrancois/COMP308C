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
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class GreenhouseControls extends Controller implements Serializable {

	private long initTime = System.currentTimeMillis();

	private boolean light = false;

	private boolean water = false;

	private boolean fans = false;

	private boolean windowsok = true;

	private boolean poweron = true;

	private String thermostat = "Day";

	private int errorCode = 0;

	private String eventsFile = "";

	public class LightOn extends Event implements Serializable {

		public LightOn(long delayTime) {
			super(delayTime);
		}

		@Override
		public void action() {
			// Put hardware control code here to
			// physically turn on the light.
			light = true;
			System.out.println("Action to turn light on.. " + " @" + (System.currentTimeMillis() - initTime));
		}

		@Override
		public String toString() {
			return "Light is on.. " + " @" + (System.currentTimeMillis() - initTime);
		}
	}

	public class LightOff extends Event implements Serializable {

		public LightOff(long delayTime) {
			super(delayTime);
		}

		@Override
		public void action() {
			// Put hardware control code here to
			// physically turn off the light.
			light = false;
			System.out.println("Action to turn light off.. " + " @" + (System.currentTimeMillis() - initTime));
		}

		@Override
		public String toString() {
			return "Light is off.. " + " @" + (System.currentTimeMillis() - initTime);
		}
	}

	public class WaterOn extends Event implements Serializable {

		public WaterOn(long delayTime) {
			super(delayTime);
		}

		@Override
		public void action() {
			// Put hardware control code here.
			water = true;
		}

		@Override
		public String toString() {
			return "Greenhouse water is on";
		}
	}

	public class WaterOff extends Event implements Serializable {

		public WaterOff(long delayTime) {
			super(delayTime);
		}

		@Override
		public void action() {
			// Put hardware control code here.
			water = false;
		}

		@Override
		public String toString() {
			return "Greenhouse water is off";
		}
	}

	public class FansOn extends Event implements Serializable {

		public FansOn(long delayTime) {
			super(delayTime);
		}

		@Override
		public void action() {
			// Put hardware control code here to
			// physically turn on the fans.
			fans = true;
		}

		@Override
		public String toString() {
			return "Fans are on";
		}
	}

	public class FansOff extends Event implements Serializable {

		public FansOff(long delayTime) {
			super(delayTime);
		}

		@Override
		public void action() {
			// Put hardware control code here to
			// physically turn off the fans.
			fans = false;
		}

		@Override
		public String toString() {
			return "Fans are off";
		}
	}

	public class WindowMalfunction extends Event implements Serializable {

		public WindowMalfunction(long delayTime) {
			super(delayTime);
		}

		@Override
		public void action() throws ControllerException {
			// Put hardware control code here to
			// physically turn off the fans.
			windowsok = false;
			throw new ControllerException(1);
		}

		@Override
		public String toString() {
			return "Window malfunction occurred";
		}
	}

	public class PowerOut extends Event implements Serializable {

		public PowerOut(long delayTime) {
			super(delayTime);
		}

		@Override
		public void action() throws ControllerException {
			// Put hardware control code here to
			// physically turn on the fans.
			poweron = false;
			throw new ControllerException(2);
		}

		@Override
		public String toString() {
			return "Power outage oocurred";
		}
	}

	public class ThermostatNight extends Event implements Serializable {

		public ThermostatNight(long delayTime) {
			super(delayTime);
		}

		@Override
		public void action() {
			// Put hardware control code here.
			thermostat = "Night";
		}

		@Override
		public String toString() {
			return "Thermostat on night setting";
		}
	}

	public class ThermostatDay extends Event implements Serializable {

		public ThermostatDay(long delayTime) {
			super(delayTime);
		}

		@Override
		public void action() {
			// Put hardware control code here.
			thermostat = "Day";
		}

		@Override
		public String toString() {
			return "Thermostat on day setting";
		}
	}

	// An example of an action() that inserts a
	// new one of itself into the event list:
	public class Bell extends Event implements Serializable {

		int rings;

		public Bell(long delayTime) {
			super(delayTime);
		}

		public Bell(long delayTime, int rings) {
			super(delayTime);
			this.rings = rings;
			System.out.println("Bell will ring " + this.rings + " times.. ");
		}

		@Override
		public void action() {
			if (this.rings > 1)
				addEvent(new Bell(5000, --this.rings));
		}

		@Override
		public String toString() {
			return "Bing! bah bang (" + rings + ") @" + (System.currentTimeMillis() - initTime);
		}
	}

	public class Restart extends Event implements Serializable {

		public Restart(long delayTime, String filename) {
			super(delayTime);
			eventsFile = filename;
		}

		@Override
		public void action() {
			loadEvents(eventsFile);
			// addEvent(new ThermostatDay(0));
			// addEvent(new LightOn(5000));
			// addEvent(new WaterOff(8000));
			// addEvent(new ThermostatNight(10000));
			// addEvent(new Bell(5000, 6));
			// addEvent(new Bell(10000, 4));
			// addEvent(new WaterOn(6000));
			// addEvent(new LightOff(7000));
			// addEvent(new Terminate(24000));
		}

		@Override
		public String toString() {
			return "Restarting system";
		}
	}

	public class Terminate extends Event implements Serializable {

		public Terminate(long delayTime) {
			super(delayTime);
		}

		@Override
		public void action() {
			System.exit(0);
		}

		@Override
		public String toString() {
			return "Terminating";
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
		 * @param i
		 */
		public ControllerException(int i) {
			errorCode = i;
		}

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
		System.out.println("Tokens" + Arrays.toString(tokens));
		eventName = tokens[0].split("=")[1];
		timeDelay = Long.parseLong(tokens[1].split("=")[1]);
		System.out.println("length " + tokens.length);
		if (tokens.length > 2 && tokens.length < 4) {
			arg = Integer.parseInt(tokens[2].split("=")[1]);
		}
		System.out.println("Event Name: " + eventName + " - Time Delay: " + timeDelay + " Argument: " + arg);
		createEvent(eventName, timeDelay, arg);
	}

	private void removeEvent(String eventName) {
		// List<Event> oldList = getEventList();
		// setEventList(new ArrayList<Event>());
		// for (Event event : oldList) {
		// String className = event.getClass().getSimpleName();
		// if (!className.equals(eventName))
		// addEvent(event);
		// }
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

	private static void dumpGreenhouseControls(GreenhouseControls gc) throws IOException {
		try (FileOutputStream fos = new FileOutputStream("dump.out");
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(gc);
			System.out.printf("Greenhouse Controller has been dumped");
		} catch (IOException e) {
			e.printStackTrace();
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

	@Override
	void shutdown(String errorMessage) {
		super.shutdown(errorMessage);
		severeLog(errorMessage);
		try {
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
			System.out.println("Light on");
			addEvent(new LightOn(timeDelay));
			break;
		case "LightOff":
			System.out.println("Light off");
			addEvent(new LightOff(timeDelay));
			break;
		case "WaterOn":
			System.out.println("Water on");
			addEvent(new WaterOn(timeDelay));
			break;
		case "WaterOff":
			System.out.println("Water off");
			addEvent(new WaterOff(timeDelay));
			break;
		case "FansOn":
			System.out.println("Fans on");
			addEvent(new FansOn(timeDelay));
			break;
		case "FansOff":
			System.out.println("Fans off");
			addEvent(new FansOff(timeDelay));
			break;
		case "ThermostatDay":
			System.out.println("Thermo Day");
			addEvent(new ThermostatDay(timeDelay));
			break;
		case "ThermostatNight":
			addEvent(new ThermostatNight(timeDelay));
			System.out.println("Thermo Night");
			break;
		case "Terminate":
			System.out.println("Terminate");
			addEvent(new Terminate(timeDelay));
			break;
		case "WindowMalfunction":
			System.out.println("Window Malfunction");
			addEvent(new WindowMalfunction(timeDelay));
			break;
		case "PowerOut":
			System.out.println("Power Out");
			addEvent(new PowerOut(timeDelay));
			break;
		case "Bell":
			System.out.println("bell");
			if (arg != 0)
				addEvent(new Bell(timeDelay, arg));
			else
				addEvent(new Bell(timeDelay));
			break;
		default:
			System.out.println("error in creating event: " + eventName);
		}
	}

	// ---------------------------------------------------------
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
			// GreenhouseControls gc = new GreenhouseControls();
			if (option.equals("-f")) {
				gc = new GreenhouseControls();
				System.out.println("adding file");
				gc.loadEvents(filename);
			} else {
				Restore restore = new Restore(filename);
				gc = restore.recoverGreenhouseControls();
				gc.getFixable(gc.errorCode).fix();
				System.out.println("file restored..");
			}
			gc.run();
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Invalid number of parameters");
			printUsage();
		}
	}
} // /:~
