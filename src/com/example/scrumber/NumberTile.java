package com.example.scrumber;

/**
 * // -------------------------------------------------------------------------
 * /** Write a one-sentence summary of your class here. Follow it with
 * additional details about its purpose, what abstraction it represents, and how
 * to use it.
 *
 * @author Sean Crenshaw and Divyansh Gupta (divyg)
 * @version Nov 15, 2014
 */
public class NumberTile extends Tile {

	private int tileNum;

	// ----------------------------------------------------------
	/**
	 * Create a new NumberTile object.
	 *
	 * @param number is the number which the tile represents.
	 * @param tileLocation is the Location of the Tile.
	 */
	public NumberTile(int number, Location tileLocation) {
		super(tileLocation);
		setTileNum(number);
	}

	// ----------------------------------------------------------
	/**
	 * Get the number that the NumberTile represents.
	 *
	 * @return the number that the NumberTile represents.
	 */
	public int getTileNum() {
		return tileNum;
	}

	// ----------------------------------------------------------
	/**
	 * Sets the number that the NumberTile represents.
	 *
	 * @param newTileNum is the new number that the NumberTile will represent
	 *            after this method is invoked.
	 */
	public void setTileNum(int newTileNum) {
		this.tileNum = newTileNum;
	}

	// ----------------------------------------------------------
	/**
	 * Get the value of the NumberTile.
	 */
	@Override
	public String value() {
		return "" + Integer.toString(tileNum);
	}

	// ----------------------------------------------------------
	/**
	 *
	 */
	protected String getInternalValue() {
		String val = this.value();
		if (val.length() > 0 && val.charAt(0) == '-') {
			return val.replaceFirst("-", "0");
		}
		return val;
	}
}