package com.example.scrumber;

// -------------------------------------------------------------------------
/**
 * Represents a single Move by the player on the Board.
 *
 * @author
 * @version Nov 16, 2014
 */
public class Move {

	private Tile tile;
	private Location location;

	/**
	 * Create a new Move object.
	 *
	 * @param tileMoved is the Tile that was moved.
	 * @param atLocation is the Location the tile was moved to.
	 */
	public Move(Tile tileMoved, Location atLocation) {
		tile = tileMoved;
		location = atLocation;
	}

	// ----------------------------------------------------------
	/**
	 * @return the tile
	 */
	public Tile getTile() {
		return tile;
	}

	// ----------------------------------------------------------
	/**
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}
}
