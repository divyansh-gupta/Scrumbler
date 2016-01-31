package com.example.scrumber;

import android.content.res.TypedArray;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

// -------------------------------------------------------------------------
/**
 * An Android ButtonView that displays a CountDownTimer and supports onClick
 * pausing and resuming.
 *
 * @author Divyansh Gupta
 * @version Feb 3, 2015
 */

public class CountDownTimerView extends Button implements
    OnClickListener {

	private PausableCountDownTimer timer;
	private Pausable screen;

	private String format;
	private final String DEFAULT_FORMAT = "HH:mm:ss";
	private final long DEFAULT_COUNTDOWN_TIME = 60000 * 2; // 2 Minutes
	private final long DEFAULT_COUNTDOWN_INTERVAL = 500; // 0.5 Second

	// ----------------------------------------------------------
	/**
	 * Create a new CountDownTimerView object.
	 *
	 * @param context is the Context the view running in, through which it can
	 *            access the current theme, resources, etc.
	 */
	public CountDownTimerView(Context context) {
		this(context, null, 0);
	}

	// ----------------------------------------------------------
	/**
	 * Create a new CountDownTimerView object.
	 *
	 * @param context is the Context the view running in, through which it can
	 *            access the current theme, resources, etc.
	 * @param attrs are the attributes of the XML tag that is inflating the
	 *            view.
	 */
	public CountDownTimerView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	// ----------------------------------------------------------
	/**
	 * Create a new CountDownTimerView object.
	 *
	 * @param context is the Context the view running in, through which it can
	 *            access the current theme, resources, etc.
	 * @param attrs are the attributes of the XML tag that is inflating the
	 *            view.
	 * @param defStyle is an attribute in the current theme that contains a
	 *            reference to a style resource that supplies default values for
	 *            the view. Can be 0 to not look for defaults.
	 */
	@SuppressWarnings("deprecation")
	public CountDownTimerView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.setBackgroundDrawable(null);

		TypedArray attributes = context.getTheme().obtainStyledAttributes(
		    attrs, R.styleable.CountDownTimerView, 0, 0);

		long millisInFuture = attributes.getInteger(
		    R.styleable.CountDownTimerView_millisInFuture,
		    (int) DEFAULT_COUNTDOWN_TIME);

		long countDownInterval = attributes.getInteger(
		    R.styleable.CountDownTimerView_millisInFuture,
		    (int) DEFAULT_COUNTDOWN_INTERVAL);

		this.format = attributes
		    .getString(R.styleable.CountDownTimerView_displayFormat);
		attributes.recycle();

		timer = new PausableCountDownTimer(millisInFuture, countDownInterval) {

			@Override
			public void onFinish() {
				CountDownTimerView.this.onFinish();
			}

			@Override
			public void onTick(long interval) {
				int rInterval = (int)(interval/1000.0 + 0.5) * 1000;
				String timeFormat = CountDownTimerView.this.getFormat();
				String remainingTime = getRemainingTime(timeFormat, rInterval);
				CountDownTimerView.this.setText(remainingTime);
				CountDownTimerView.this.onTick(interval);
			}

		};
	}

	// ----------------------------------------------------------
	/**
	 * Place a description of your method here.
	 *
	 * @param millisRemaining
	 */
	protected void onTick(long millisRemaining) {
		screen.onTick();
	}

	// ----------------------------------------------------------
	/**
	 * Place a description of your method here.
	 */
	protected void onFinish() {
		screen.onFinish();
	}

	// ----------------------------------------------------------
	/**
	 * @return the screen
	 */
	public Pausable getScreen() {
		return screen;
	}

	// ----------------------------------------------------------
	/**
	 * @param screen the screen to set
	 */
	public void setScreen(Pausable screen) {
		if (this.isPaused()) {
			this.screen = screen;
		} else {
			throw new IllegalStateException(
			    "PausableActivity cannot be set while timer is running!");
		}
	}

	// ----------------------------------------------------------
	/**
	 * When View is clicked: if the timer is paused, it will start, and if it is
	 * not paused, it will pause.
	 */
	@Override
	public void onClick(View clicked) {
		if (timer.isPaused()) {
			this.start();
		} else if (!timer.isPaused()) {
			this.pause();
		}
	}

	// ----------------------------------------------------------
	/**
	 * Returns a human readable representation of the time on the
	 * CountDownTimerView.
	 *
	 * @return the human readable representation of the time on the
	 *         CountDownTimerView.
	 */
	@Override
	public String toString() {
		return timer.toString();
	}

	// ----------------------------------------------------------
	/**
	 * Place a description of your method here.
	 *
	 * @param newFormat
	 */
	public void setFormat(String newFormat) {
		this.format = newFormat;
	}

	// ----------------------------------------------------------
	/**
	 * Place a description of your method here.
	 *
	 * @return
	 */
	public String getFormat() {
		if (format != null) {
			return format;
		}
		return DEFAULT_FORMAT;
	}

	// ----------------------------------------------------------
	/**
	 * Place a description of your method here.
	 */
	public void start() {
		timer.start();
		if (screen != null) {
			screen.resume();
		}
	}

	// ----------------------------------------------------------
	/**
	 * Place a description of your method here.
	 *
	 * @return
	 */
	public boolean isPaused() {
		return timer.isPaused();
	}

	// ----------------------------------------------------------
	/**
	 * Place a description of your method here.
	 */
	public void pause() {
		timer.pause();
		if (screen != null) {
			screen.pause();
		}
	}

	// ----------------------------------------------------------
	/**
	 * Place a description of your method here.
	 *
	 * @param millisRemaining
	 */
	public void setMillisRemaining(long millisRemaining) {
		timer.setMillisRemaining(millisRemaining);
	}

	// ----------------------------------------------------------
	/**
	 * Get whether or not the PausableCountDownTimer is finished counting down.
	 *
	 * @return true if count down is finished. Otherwise, false.
	 */
	public boolean isFinished() {
		return timer.isFinished();
	}
}