package com.example.scrumber;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ToggleButton;

// -------------------------------------------------------------------------
/**
 * Write a one-sentence summary of your class here. Follow it with additional
 * details about its purpose, what abstraction it represents, and how to use it.
 *
 * @author Divyansh Gupta
 * @version Nov 16, 2014
 */
public class HandButton extends ToggleButton {

	private Tile tile;

	// ----------------------------------------------------------
	/**
	 * Create a new HandButton object.
	 *
	 * @param context
	 */
	public HandButton(Context context) {
		super(context);
	}

	// ----------------------------------------------------------
	/**
	 * Create a new HandButton object.
	 *
	 * @param context
	 * @param attrs
	 */
	public HandButton(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	// ----------------------------------------------------------
	/**
	 * Create a new HandButton object.
	 *
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public HandButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	// ----------------------------------------------------------
	/**
	 * Get the Tile that the HandButton represents.
	 *
	 * @return the tile
	 */
	public Tile getTile() {
		return tile;
	}

	// ----------------------------------------------------------
	/**
	 * Set the Tile that the HandButton represents.
	 *
	 * @param tile the tile to set
	 */
	public void setTile(Tile tile) {
		this.tile = tile;
	}

	// ----------------------------------------------------------
	/**
	 * Sets all the text for the HandButton - in the off, on, and un-clicked
	 * position.
	 *
	 * @param text is the text to set for the HandButton.
	 */
	public void setAllText(String text) {
		this.setText(text);
		this.setTextOff(text);
		this.setTextOn(text);
	}
}