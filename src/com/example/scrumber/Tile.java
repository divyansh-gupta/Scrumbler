package com.example.scrumber;

/**
 * // -------------------------------------------------------------------------
 * /** Write a one-sentence summary of your class here. Follow it with
 * additional details about its purpose, what abstraction it represents, and how
 * to use it.
 *
 * @author Divyansh Gupta (divyg) and Sean Crenshaw (seanpc9)
 * @version Nov 15, 2014
 */
public class Tile {

	private Location location;

	// ----------------------------------------------------------
	/**
	 * Create a new Tile object.
	 *
	 * @param tLocation is the Location object for the Tile.
	 */
	public Tile(Location tLocation) {
		location = tLocation;
	}

	// ----------------------------------------------------------
	/**
	 * Create a new Tile Object.
	 *
	 * @param x is the x coordinate for the Tile.
	 * @param y is the y coordinate for the Tile.
	 */
	public Tile(int x, int y) {
		this(new Location(x, y));
	}

	// ----------------------------------------------------------
	/**
	 * Create a new Tile Object.
	 */
	public Tile() {
		this(-1, -1);
	}

	// ----------------------------------------------------------
	/**
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}

	// ----------------------------------------------------------
	/**
	 * @param location the location to set
	 */
	public void setLocation(Location location) {
		this.location = location;
	}

	// ----------------------------------------------------------
	/**
	 * Get the value of the Tile.
	 *
	 * @return the value of the Tile.
	 */
	public String value() {
		return " ";
	}

	// ----------------------------------------------------------
	/**
	 * Place a description of your method here.
	 *
	 * @return the value of the Tile from an internal standpoint. Override in
	 *         subclasses.
	 */
	protected String getInternalValue() {
		return " ";
	}
}