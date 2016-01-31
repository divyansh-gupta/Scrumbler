package com.example.scrumber;

// -------------------------------------------------------------------------
/**
 *  Write a one-sentence summary of your class here.
 *  Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 *
 *  @author itdxg04
 *  @version Feb 14, 2015
 */

public interface Pausable {

	// ----------------------------------------------------------
	/**
	 * Place a description of your method here.
	 */
	public void pause();

	/**
	 * Place a description of your method here.
	 */
	public void resume();

	/**
	 * Place a description of your method here.
	 *
	 * @return whether or not the activity is paused.
	 */
	public boolean isPaused();

	/**
	 *
	 */
	public void onTick();

	/**
	 *
	 */
	public void onFinish();

}
