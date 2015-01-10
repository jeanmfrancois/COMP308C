package com.jfbuilds.tme3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

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
public class GreenhouseControls extends Controller {

	private long initTime = System.currentTimeMillis();

	private boolean light = false;

	private boolean water = false;

	private boolean fans = false;

	// private boolean power = false;
	private String thermostat = "Day";

	private String eventsFile = "examples1.txt";

	public class LightOn extends Event {

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

	public class LightOff extends Event {

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

	public class WaterOn extends Event {

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

	public class WaterOff extends Event {

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

	public class FansOn extends Event {

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

	public class FansOff extends Event {

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

	public class ThermostatNight extends Event {

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

	public class ThermostatDay extends Event {

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
	public class Bell extends Event {

		private int rings;

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

	public class Restart extends Event {

		public Restart(long delayTime, String filename) {
			super(delayTime);
			eventsFile = filename;
		}

		@Override
		public void action() {
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

	public class Terminate extends Event {

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
	 * Print valid options to be supplied as arguments for application execution
	 */
	public static void printUsage() {
		System.out.println("Correct format: ");
		System.out.println("  java GreenhouseControls -f <filename>, or");
		System.out.println("  java GreenhouseControls -d dump.out");
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
			if (!(option.equals("-f")) && !(option.equals("-d"))) {
				System.out.println("Invalid option");
				printUsage();
			}
			GreenhouseControls gc = new GreenhouseControls();
			if (option.equals("-f")) {
				System.out.println("adding file");
				gc.loadEvents(filename);
			}
			gc.run();
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Invalid number of parameters");
			printUsage();
		}
	}

	/**
	 * Parses a line of text and extracts an Event name, delaytime, and optional
	 * argument
	 * 
	 * @param eventLine
	 *            The event line of text to parse
	 */
	public void parseEvent(String eventLine) {
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
	private void createEvent(String eventName, Long timeDelay, int arg) {
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
			// TODO add window malfunction event
			// addEvent(new WindowMalfunction(timeDelay));
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
} // /:~
