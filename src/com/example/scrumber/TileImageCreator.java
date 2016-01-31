package com.example.scrumber;

import android.graphics.Color;
import android.graphics.BitmapFactory;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import sofia.graphics.Image;
import java.util.HashMap;

// -------------------------------------------------------------------------
/**
 * Write a one-sentence summary of your class here. Follow it with additional
 * details about its purpose, what abstraction it represents, and how to use it.
 *
 * @author Divyansh Gupta (divyg)
 * @version April 8, 2015
 */
public class TileImageCreator {

	private static HashMap<String, TextImage> imagesCreated = new HashMap<String, TextImage>();

	// ----------------------------------------------------------
	/**
	 * @param tile
	 * @param tileView
	 * @param textToAdd
	 * @param context
	 */
	public static void createImageWithText(TileView tileView, String textToAdd,
	    Context context) {
		if (imagesCreated.containsKey(textToAdd)) {
			Image createdImage = new Image(imagesCreated.get(textToAdd));
			setTileViewImage(tileView, new TextImage(createdImage, textToAdd));
			return;
		}
		Bitmap image = BitmapFactory.decodeResource(context.getResources(),
		    R.drawable.blank_tile).copy(Bitmap.Config.ARGB_8888, true);
		// Bitmap image = tileView.getImage().asBitmap();
		Paint paint = tileView.getPaint(context);
		if (textToAdd.matches("[-+]?\\d*\\.?\\d+")) { // Text is numeric
			paint.setColor(Color.BLUE);
		} else {
			 paint.setColor(Color.GRAY);
		}
		new TileImageCreateTask(tileView, image, paint).execute(textToAdd);
	}

	// ----------------------------------------------------------
	/**
	 * Place a description of your method here.
	 *
	 * @param tImage
	 */
	public static void cacheImage(TextImage tImage) {
		imagesCreated.put(tImage.getImageText(), tImage);
	}

	// ----------------------------------------------------------
	/**
	 * Place a description of your method here.
	 *
	 * @param tileView
	 * @param image
	 */
	public static void setTileViewImage(TileView tileView, TextImage image) {
		if (tileView != null) {
			tileView.setImage(image);
		}
	}
}