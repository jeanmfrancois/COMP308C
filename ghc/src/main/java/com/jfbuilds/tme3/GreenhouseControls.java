package com.jfbuilds.tme3;

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

		private String name = "No Name";

		public Bell(long delayTime) {
			super(delayTime);
		}

		public Bell(long delayTime, int rings, String name) {
			super(delayTime);
			this.rings = rings;
			this.name = name;
			System.out.println("Bell will ring " + this.rings + " times.. ID:" + name);
		}

		@Override
		public void action() {
			if (this.rings > 1)
				addEvent(new Bell(5000, --this.rings, name));
		}

		@Override
		public String toString() {
			return "Bing! bah bang (" + rings + ") for " + name + " @" + (System.currentTimeMillis() - initTime);
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
			addEvent(new LightOn(5000));
			// addEvent(new WaterOff(8000));
			// addEvent(new ThermostatNight(10000));
			addEvent(new Bell(5000, 6, "Jean-a-ring"));
			addEvent(new Bell(10000, 4, "Dad-a-ring"));
			// addEvent(new WaterOn(6000));
			addEvent(new LightOff(7000));
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

	public static void printUsage() {
		System.out.println("Correct format: ");
		System.out.println("  java GreenhouseControls -f <filename>, or");
		System.out.println("  java GreenhouseControls -d dump.out");
	}

	// ---------------------------------------------------------
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
				gc.addEvent(gc.new Restart(0, filename));
			}
			gc.run();
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Invalid number of parameters");
			printUsage();
		}
	}
} // /:~
