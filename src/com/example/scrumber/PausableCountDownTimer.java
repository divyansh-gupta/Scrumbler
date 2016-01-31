package com.example.scrumber;

import org.apache.commons.lang3.time.DurationFormatUtils;

import android.annotation.SuppressLint;
import android.os.CountDownTimer;

// -------------------------------------------------------------------------
/**
 * A CountDownTimer implementation that is Pausable, Resumable, and abstract.
 *
 * @author Divyansh Gupta
 * @version Feb 2, 2015
 */

public abstract class PausableCountDownTimer {

	private long millisInFuture;
	private long countDownInterval;
	private long millisRemaining;
	private boolean isPaused;

	private CountDownTimer timer;

	// ----------------------------------------------------------
	/**
	 * Create a new PausableCountDownTimer object.
	 *
	 * @param millisInFuture is the amount of time the PausableCountDownTimer
	 *            will run before executing onFinish().
	 * @param countDownInterval is the intervals on which to run the onTick()
	 *            method.
	 */
	public PausableCountDownTimer(long millisInFuture, long countDownInterval) {
		super();
		isPaused = true;
		this.millisInFuture = millisInFuture;
		this.millisRemaining = this.millisInFuture;
		this.countDownInterval = countDownInterval;
	}

	// ----------------------------------------------------------
	/**
	 * Creates the CountDownTimer.
	 */
	private void createCountDownTimer() {
		timer = new CountDownTimer(millisRemaining, countDownInterval) {

			@Override
			public void onFinish() {
				PausableCountDownTimer.this.onFinish();
			}

			@Override
			public void onTick(long millisUntilFinished) {
				millisRemaining = millisUntilFinished;
				PausableCountDownTimer.this.onTick(millisUntilFinished);
			}

		};
	}

	// ----------------------------------------------------------
	/**
	 * Executes this method when CountDown finishes.
	 */
	public abstract void onFinish();

	// ----------------------------------------------------------
	/**
	 * Repeatedly executes this method on the interval passed in.
	 *
	 * @param interval is the interval on which to execute this method.
	 */
	public abstract void onTick(long interval);

	// ----------------------------------------------------------
	/**
	 * Returns a human readable representation of how many milliseconds are
	 * remaining on the clock in the format: HH:MM:SS
	 */
	@SuppressLint("DefaultLocale")
	@Override
	public String toString() {
		long millis = this.millisRemaining;
		int seconds = (int) (millis / 1000) % 60;
		int minutes = (int) ((millis / (1000 * 60)) % 60);
		int hours = (int) ((millis / (1000 * 60 * 60)) % 24);

		String ms = String.format("%02d:%02d:%02d", hours, minutes, seconds);
		return ms;
	}

	// ----------------------------------------------------------
	/**
	 * Cancel the count-down.
	 */
	public final void cancel() {
		if (timer != null) {
			timer.cancel();
		}
		this.millisRemaining = 0;
	}

	// ----------------------------------------------------------
	/**
	 * Start or Resume the count-down.
	 *
	 * @return CountDownTimerPausable current instance
	 */
	public synchronized final PausableCountDownTimer start() {
		if (isPaused) {
			createCountDownTimer();
			timer.start();
			isPaused = false;
		}
		return this;
	}

	// ----------------------------------------------------------
	/**
	 * Pauses the CountDownTimerPausable, so it could be resumed(start) later
	 * from the same point where it was paused.
	 *
	 * @throws IllegalStateException
	 */
	public void pause() throws IllegalStateException {
		if (!isPaused) {
			timer.cancel();
		} else {
			throw new IllegalStateException(
			    "CountDownTimerPausable is already in pause state "
			        + "start counter before pausing it.");
		}
		isPaused = true;
	}

	// ----------------------------------------------------------
	/**
	 * Place a description of your method here.
	 *
	 * @param timeFormat
	 * @return
	 */
	public String getRemainingTime(String timeFormat) {
		return DurationFormatUtils.formatDuration(this.millisRemaining,
		    timeFormat);
	}

	// ----------------------------------------------------------
	/**
	 * Place a description of your method here.
	 *
	 * @param timeFormat
	 * @param millisRemaining
	 * @return
	 */
	public static String getRemainingTime(String timeFormat,
	    long millisRemaining) {
		return DurationFormatUtils.formatDuration(millisRemaining, timeFormat);
	}

	// ----------------------------------------------------------
	/**
	 * Get the pause state of the PausableCountDownTimer.
	 *
	 * @return whether or not the PausableCountDownTimer is paused or not.
	 */
	public boolean isPaused() {
		return isPaused;
	}

	// ----------------------------------------------------------
	/**
	 * Get the number of milliseconds remaining on the clock.
	 *
	 * @return the millisRemaining is the amount of time, in milliseconds,
	 *         remaining on the clock.
	 */
	public long getMillisRemaining() {
		return millisRemaining;
	}

	// ----------------------------------------------------------
	/**
	 * Set the new milliseconds remaining on the clock.
	 *
	 * @param millisRemaining is the millisRemaining to set.
	 */
	public void setMillisRemaining(long millisRemaining) {
		this.millisRemaining = millisRemaining;
		if (!isPaused) {
			this.pause();
		}
		//this.start();
	}

	// ----------------------------------------------------------
	/**
	 * Get whether or not the PausableCountDownTimer is finished counting down.
	 *
	 * @return true if count down is finished. Otherwise, false.
	 */
	public boolean isFinished() {
		return millisRemaining == 0;
	}
}