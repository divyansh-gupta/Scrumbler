package com.example.scrumber;

import android.graphics.Bitmap;
import android.graphics.Paint;

// -------------------------------------------------------------------------
/**
 * Write a one-sentence summary of your class here. Follow it with additional
 * details about its purpose, what abstraction it represents, and how to use it.
 *
 * @author Divyansh Gupta (divyg)
 * @version April 19, 2015
 */
public class TileImageCreateTask extends BitmapTextWriteTask {

	private TileView tileView;

	// ----------------------------------------------------------
	/**
	 * Create a new TileImageCreateTask object.
	 *
	 * @param tView
	 * @param image
	 * @param paint
	 */
	public TileImageCreateTask(TileView tView, Bitmap image, Paint paint) {
		super(image, paint);
		this.tileView = tView;
	}

	// ----------------------------------------------------------
	@Override
	protected void onPostExecute(TextImage tImage) {
		super.onPostExecute(tImage);
		TileImageCreator.cacheImage(tImage);
		TileImageCreator.setTileViewImage(tileView, tImage);
	}
}