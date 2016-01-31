package com.example.scrumber;

import android.os.AsyncTask;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.Canvas;
import android.graphics.Paint;

// -------------------------------------------------------------------------
/**
 * Write a one-sentence summary of your class here. Follow it with additional
 * details about its purpose, what abstraction it represents, and how to use it.
 *
 * @author Divyansh Gupta (divyg)
 * @version April 9, 2015
 */
public class BitmapTextWriteTask extends AsyncTask<String, Void, TextImage> {

	private Bitmap image;
	private Paint imagePaint;

	// ----------------------------------------------------------
	/**
	 * Create a new BitmapTextWriteTask object.
	 *
	 * @param image
	 * @param paint
	 */
	public BitmapTextWriteTask(Bitmap image, Paint paint) {
		super();
		this.image = image;
		this.imagePaint = paint;
	}

	// ----------------------------------------------------------
	@Override
	protected TextImage doInBackground(String... params) {
		String textToAdd = params[0];
		Canvas canvas = new Canvas(image);
		// draw text to the Canvas center
		Rect bounds = new Rect();
		imagePaint.getTextBounds(textToAdd, 0, textToAdd.length(), bounds);
		int x = (image.getWidth() - bounds.width()) / 2;
		int y = (image.getHeight() + bounds.height()) / 2;
		canvas.drawText(textToAdd, x, y, imagePaint);
		return new TextImage(this.image, textToAdd);
	}

	// ----------------------------------------------------------
	@Override
	protected void onPostExecute(TextImage tImage) {
		// To be overwritten and extended
	}

	// ----------------------------------------------------------
	/**
	 * Place a description of your method here.
	 *
	 * @param textToAdd
	 */
	public void execute(String textToAdd) {
		super.execute(textToAdd);
	}

}
