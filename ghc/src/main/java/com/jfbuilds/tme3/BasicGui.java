/**
 * File Name: BasicGui.java<br>
 * Jean-francois Nepton<br>
 * COMP 308 Java for Programmers<br>
 * Cordinator: Dr. Xiaokun Zhang<br>
 * Student ID# 2358976<br>
 * Created: Jan 14, 2015
 */
package com.jfbuilds.tme3;

/**
 * BasicGui Graphical User Interface used to control a GreenhouseControls
 * object.
 * <p>
 * (description of core fields)
 * <p>
 * (description of core methods)
 * 
 * @author Jean-francois Nepton
 * @version %I%, %G%
 * @since 1.0
 */
public interface BasicGui {

	// TODO Associate a keyboard shortcut with each of the above menu items.
	// TODO Design each window of the GUI must have a scrollable text area, a
	// set of 5 buttons, a pulldown menu and a popup menu.
	// TODO Create a pulldown menu consisting of 5 sub menus: New, Close, Open,
	// Restore, Exit
	// TODO implement createNewWindow
	/**
	 * Creates a new GUI window. The new GUI window should associate with
	 * another GreenhouseControls object.
	 */
	void createNewWindow(String winName);

	// TODO implement closeWindow
	/**
	 * Closes the current window. If the GreenhouseControls object is running, a
	 * warning message should be displayed to ask the user to confirm closing of
	 * the window. If the current window is the only window opened, exit the
	 * application.
	 */
	void closeWindow(String winName);

	// TODO implement openEvents
	/**
	 * Opens an events file. It should bring up a file dialog and let the user
	 * chooses an event file. If the chosen file is not a valid event file,
	 * display an appropriate error message.
	 */
	void openEvents();

	// TODO implement restore
	/**
	 * Opens a dump.out file and restore the GreenhouseControls object. It
	 * should bring up a file dialog and let the user choose a dump.out file. If
	 * the chosen file is not a valid dump.out file, display an appropriate
	 * error message. This option should be disabled if the GreenhouseControls
	 * is running.
	 */
	void restore();

	// TODO implement exit
	/**
	 * Exit the application. If any of the opened GUIs is running a
	 * GreenhouseControls object, display a warning message to ask the user to
	 * confirm the exit.
	 */
	void exit();

	// TODO implement startGHC
	/**
	 * Start to run a GreenhouseControls object. This button should be disabled
	 * if the GreenhouseControls object is running.
	 */
	void startGHC();

	// TODO implement restartGHC
	/**
	 * Add a Restart object to rerun the current event file. This button should
	 * be disabled if no events file is read, or if the GreenhouseControls
	 * object is running.
	 */
	void restartGHC();

	// TODO implement terminate
	/**
	 * Add a Terminate event to the running GreenhouseControls object. It should
	 * bring up a dialog to prompt for the delay time in milliseconds. This
	 * button should be disabled if the GreenhouseControls object is not
	 * running.
	 */
	void terminate();

	// TODO implement suspend
	/**
	 * Suspends all running event threads. This button should be disabled if the
	 * GreenhouseControls object is not running.
	 */
	void suspend();

	// TODO implement resume
	/**
	 * Resume all suspended event threads. This button should be disabled if the
	 * GreenhouseControls object is running.
	 */
	void resume();

	// TODO implement showPopupMenu
	/**
	 * The popup menu should contain the following 5 submenus: Start, Restart,
	 * Terminate, Suspend, and Resume, with the same functionalities as the
	 * buttons.
	 */
	void showPopupMenu();
}
