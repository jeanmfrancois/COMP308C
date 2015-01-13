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
import java.lang.reflect.InvocationTargetException;
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
	 * Used for serialization
	 */
	private static final long serialVersionUID = -2636397787261306102L;

	/**
	 * Constant to signify a window failure error
	 */
	public static final int WINDOW_FAIL = 1;

	/**
	 * Constant to signify a power failure error
	 */
	public static final int POWER_FAIL = 2;

	private boolean light = false;

	private boolean water = false;

	private boolean fans = false;

	private boolean windowsok = true;

	private boolean poweron = true;

	private String thermostat = "Day";

	private int errorCode = 0;

	private String eventsFile = "";

	/**
	 * @return the light
	 */
	public boolean isLight() {
		return light;
	}

	/**
	 * @param light
	 *            the light to set
	 */
	public void setLight(boolean light) {
		this.light = light;
	}

	/**
	 * @return the water
	 */
	public boolean isWater() {
		return water;
	}

	/**
	 * @param water
	 *            the water to set
	 */
	public void setWater(boolean water) {
		this.water = water;
	}

	/**
	 * @return the fans
	 */
	public boolean isFans() {
		return fans;
	}

	/**
	 * @param fans
	 *            the fans to set
	 */
	public void setFans(boolean fans) {
		this.fans = fans;
	}

	/**
	 * @return the windowsok
	 */
	public boolean isWindowsok() {
		return windowsok;
	}

	/**
	 * @param windowsok
	 *            the windowsok to set
	 */
	public void setWindowsok(boolean windowsok) {
		this.windowsok = windowsok;
	}

	/**
	 * @return the poweron
	 */
	public boolean isPoweron() {
		return poweron;
	}

	/**
	 * @param poweron
	 *            the poweron to set
	 */
	public void setPoweron(boolean poweron) {
		this.poweron = poweron;
	}

	/**
	 * @return the thermostat
	 */
	public String getThermostat() {
		return thermostat;
	}

	/**
	 * @param thermostat
	 *            the thermostat to set
	 */
	public void setThermostat(String thermostat) {
		this.thermostat = thermostat;
	}

	/**
	 * @return the errorCode
	 */
	public int getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode
	 *            the errorCode to set
	 */
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return the eventsFile
	 */
	public String getEventsFile() {
		return eventsFile;
	}

	/**
	 * @param eventsFile
	 *            the eventsFile to set
	 */
	public void setEventsFile(String eventsFile) {
		this.eventsFile = eventsFile;
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
	 * Removes an event from the event list
	 * 
	 * @param eventName
	 *            name of the event to be removed
	 */
	void removeEvent(String eventName) {
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
	void loadEvents(String fileName) {
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

	/*
	 * Returns the appropriate fix to currect system failure issues.
	 */
	Fixable getFixable() {
		if (getErrorCode() == GreenhouseControls.POWER_FAIL)
			return new PowerOn(this);
		else
			return new FixWindow(this);
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
		try {
			Event event;
			Class<?> objectClass = Class.forName("com.jfbuilds.tme3." + eventName);
			if (arg != 0) {
				event = (Event) objectClass.getConstructors()[1].newInstance(new Object[] { this, timeDelay, arg });
			} else {
				// System.out
				// .println("Number of arguments:" +
				// objectClass.getConstructors()[0].getParameterTypes().length);
				event = (Event) objectClass.getConstructors()[0].newInstance(new Object[] { this, timeDelay });
			}
			addEvent(event);
			//
			//
			// addEvent(event);
			// switch (eventName) {
			// case "LightOn":
			// // addEvent(new LightOn(this, timeDelay));
			//
			// + );
			// addEvent((Event) objectClass.getConstructors()[0].newInstance(new
			// Object[] { this, timeDelay }));
			// // Event event = (Event)
			// // objectClass.getConstructors().newInstance(new Object[] {
			// // this, timeDelay });
			// // addEvent((Event)
			// // objectClass.getConstructors()[1].newInstance(new Object[] {
			// // this, timeDelay }));
			// break;
			// case "LightOff":
			// addEvent(new LightOff(this, timeDelay));
			// break;
			// case "WaterOn":
			// addEvent(new WaterOn(this, timeDelay));
			// break;
			// case "WaterOff":
			// addEvent(new WaterOff(this, timeDelay));
			// break;
			// case "FansOn":
			// addEvent(new FansOn(this, timeDelay));
			// break;
			// case "FansOff":
			// addEvent(new FansOff(this, timeDelay));
			// break;
			// case "ThermostatDay":
			// addEvent(new ThermostatDay(this, timeDelay));
			// break;
			// case "ThermostatNight":
			// addEvent(new ThermostatNight(this, timeDelay));
			// break;
			// case "Terminate":
			// addEvent(new Terminate(this, timeDelay));
			// break;
			// case "WindowMalfunction":
			// addEvent(new WindowMalfunction(this, timeDelay));
			// break;
			// case "PowerOut":
			// addEvent(new PowerOut(this, timeDelay));
			// break;
			// case "Bell":
			// if (arg != 0)
			// addEvent(new Bell(this, timeDelay, arg));
			// else
			// addEvent(new Bell(this, timeDelay));
			// break;
			// default:
			// System.out.println("Error in creating event: " + eventName +
			// ", it will not be added to event list.");
			// }
		} catch (IllegalArgumentException | SecurityException | ClassNotFoundException | InstantiationException
				| IllegalAccessException | InvocationTargetException e) {
			// catch (IllegalArgumentException | SecurityException |
			// ClassNotFoundException | InstantiationException
			// | IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
				gc.getFixable().fix();
			}
			gc.run();
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Invalid number of parameters");
			printUsage();
		}
	}
} // /:~
