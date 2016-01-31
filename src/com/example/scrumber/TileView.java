package com.example.scrumber;

import sofia.graphics.Image;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.Paint;
import com.example.scrumber.OperationTile.OperationType;
import sofia.graphics.RectangleShape;

// -------------------------------------------------------------------------
/**
 * Write a one-sentence summary of your class here. Follow it with additional
 * details about its purpose, what abstraction it represents, and how to use it.
 *
 * @author Divyansh Gupta (divyg)
 * @version Nov 15, 2014
 */
public class TileView extends RectangleShape {

	// ----------------------------------------------------------
	private Location location;

	// ~ Constructors ----------------------------------------------

	// ----------------------------------------------------------
	/**
	 * Create a new TileView object.
	 *
	 * @param left is the left-hand boundary of the TileView, in pixels.
	 * @param top is the top boundary of the TileView, in pixels.
	 * @param right is the right-hand boundary of the TileView, in pixels.
	 * @param bottom is the bottom boundary of the TileView, in pixels.
	 * @param nLoc is the location of the Tile that this TileView will present.
	 */
	public TileView(float left, float top, float right, float bottom,
	    Location nLoc) {
		super(left, top, right, bottom);
		location = nLoc;
	}

	// ----------------------------------------------------------
	/**
	 * Create a new TileView object.
	 *
	 * @param left is the left-hand boundary of the TileView, in pixels.
	 * @param top is the top boundary of the TileView, in pixels.
	 * @param right is the right-hand boundary of the TileView, in pixels.
	 * @param bottom is the bottom boundary of the TileView, in pixels.
	 */
	public TileView(float left, float top, float right, float bottom) {
		this(left, top, right, bottom, new Location(-1, -1));
	}

	// ----------------------------------------------------------
	/**
	 * Change the Tile that this TileView represents. Changes the look of the
	 * TileView to accurately represent the newTile.
	 *
	 * @param tile is the Tile that this TileView will represent once the
	 */
	public void changeTile(Tile tile) {
		String strPic = "";
		if (tile instanceof NumberTile) {
			NumberTile numTile = (NumberTile) tile;
			strPic = String.valueOf(numTile.getTileNum());
			if (strPic.charAt(0) == '-') {
				strPic = strPic.substring(1, strPic.length());
				strPic = "negtile_" + strPic;
			} else {
				strPic = "tile_" + strPic;
			}
		} else if (tile instanceof OperationTile) {
			OperationTile oTile = (OperationTile) tile;
			if (oTile.getOperationType() == OperationType.ADDITION) {
				strPic = "plus_tile";
			} else if (oTile.getOperationType() == OperationType.SUBTRACTION) {
				strPic = "minus_tile";
			} else if (oTile.getOperationType() == OperationType.EQUAL) {
				strPic = "equals_tile";
			}
		} else {
			strPic = "blank_tile";
		}
		this.setImage(strPic);
	}

	// ----------------------------------------------------------
	/**
	 * Place a description of your method here.
	 *
	 * @param tile
	 * @param context
	 */
	public void changeTileText(Tile tile, Context context) {
		String strPic = tile.value();
		TileImageCreator.createImageWithText(this, strPic, context);
	}

	// ----------------------------------------------------------
	/**
	 * Get the Location of the Tile that this TileView represents.
	 *
	 * @return the tileLocation
	 */
	public Location getTileLocation() {
		return location;
	}

	// ----------------------------------------------------------
	/**
	 * Place a description of your method here.
	 * @param context
	 * @return
	 */
	public TileViewPaint getPaint(Context context) {
		return new TileViewPaint(context);
	}

	// ----------------------------------------------------------
	/**
	 * Place a description of your method here.
	 * @return
	 */
	public String getViewText() {
		Image viewImage = this.getImage();
		if (viewImage != null && viewImage instanceof TextImage) {
			TextImage tViewImage = (TextImage) viewImage;
			return tViewImage.getImageText();
		}
		return "";
	}

	// -------------------------------------------------------------------------
	/**
	 * Write a one-sentence summary of your class here. Follow it with
	 * additional details about its purpose, what abstraction it represents, and
	 * how to use it.
	 *
	 * @author Divyansh Gupta
	 * @version April 9, 2015
	 */
	public static class TileViewPaint extends Paint {

		private final String TITLE_FONT_PATH = "fonts/chalk_it_up_font.ttf";
		private final int fontSize = 180;

		// ----------------------------------------------------------

		/**
		 * Create a new TileViewPaint object.
		 *
		 * @param context
		 */
		public TileViewPaint(Context context) {
			super(Paint.ANTI_ALIAS_FLAG);
			float scale = context.getResources().getDisplayMetrics().density;
			Typeface custom_font = Typeface.createFromAsset(
			    context.getAssets(), TITLE_FONT_PATH);
			this.setTypeface(custom_font);
			this.setTextSize((int) fontSize * scale);
		}
	}
}