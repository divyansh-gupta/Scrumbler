package com.example.scrumber;

import android.app.Activity;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextSwitcher;

// -------------------------------------------------------------------------
/**
 * Write a one-sentence summary of your class here. Follow it with additional
 * details about its purpose, what abstraction it represents, and how to use it.
 *
 * @author Divyansh Gupta (divyg)
 * @version April 6, 2015
 */

public class AnimatedTextSwitcher extends TextSwitcher {

	// ----------------------------------------------------------
	/**
	 * Create a new AnimatedScoreView object.
	 *
	 * @param context
	 */
	public AnimatedTextSwitcher(Context context) {
		this(context, null);
		// TODO Auto-generated constructor stub
	}

	// ----------------------------------------------------------
	/**
	 * Create a new AnimatedScoreView object.
	 *
	 * @param context
	 * @param attrs
	 */
	public AnimatedTextSwitcher(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray attributes = context.getTheme().obtainStyledAttributes(
		    attrs, R.styleable.CountDownTimerView, 0, 0);
		this.createTextViews(attributes);
	}

	// ----------------------------------------------------------
	/**
	 * Create two new Text View objects for the animated TextSwitcher to use.
	 * Either use R.id.'s of two TextViews passed in using attribute parameters,
	 * TextViewCurrent & TextViewNext, or if not defined, creates two generic
	 * TextViews to use.
	 *
	 * @param attributes
	 */
	public void createTextViews(TypedArray attributes) {
		int CurrentViewId = attributes.getInteger(
		    R.styleable.CountDownTimerView_millisInFuture, -1);
		int nextViewId = attributes.getInteger(
		    R.styleable.CountDownTimerView_millisInFuture, -1);
		TextView textViewCurrent = (TextView) ((Activity) this.getContext()).findViewById(CurrentViewId);
		TextView textViewNext = (TextView) ((Activity) this.getContext()).findViewById(nextViewId);
		if (textViewCurrent == null) {
			this.setFactory(new ViewFactory() {
				public View makeView() {
					TextView myText = new TextView(AnimatedTextSwitcher.this
					    .getContext());
					myText.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
					myText.setTextSize(42);
					myText.setTextColor(Color.RED);
					return myText;
				}
			});
		}
		if (textViewCurrent == null) {

		}

	}
}