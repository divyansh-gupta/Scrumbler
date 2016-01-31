package com.example.scrumber;

import android.graphics.Bitmap;
import sofia.graphics.Image;

// -------------------------------------------------------------------------
/**
 *  Write a one-sentence summary of your class here.
 *  Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 *
 *  @author Divyansh Gupta (divyg)
 *  @version April 18, 2015
 */

public class TextImage extends Image {

	private String text;

	// ----------------------------------------------------------
	/**
	 * Create a new TextImage object.
	 * @param bitmap
	 * @param imageText
	 */
	public TextImage(Bitmap bitmap, String imageText) {
		super(bitmap);
		this.text = imageText;
	}

	// ----------------------------------------------------------
	/**
	 * Create a new TextImage object.
	 * @param fileName
	 * @param imageText
	 */
	public TextImage(String fileName, String imageText) {
		super(fileName);
		this.text = imageText;
	}

	// ----------------------------------------------------------
	/**
	 * Create a new TextImage object.
	 * @param other
	 * @param imageText
	 */
	public TextImage(Image other, String imageText) {
		super(other);
		this.text = imageText;
	}

	// ----------------------------------------------------------
	/**
	 * Place a description of your method here.
	 *
	 * @return the Image's text
	 */
	public String getImageText() {
		return text;
	}

	// ----------------------------------------------------------
	/**
	 *
	 *
	 * @param text
	 */
	public void setImageText(String text) {
		this.text = text;
	}

}
